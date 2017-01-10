package com.gengu.test;

import java.sql.*;
public class CreateDerbyDB
{
	/////////////////////////////////////////////////////////////////////
	//
	// CONSTANTS
	//
	/////////////////////////////////////////////////////////////////////
	private static final String DB_NAME = "GenGuDB";
	private static final String DB_OWNER = "xushuo";
	private static final String DB_OWNER_PASSWORD = "xushuo";
	private static final String READER = "guest";
	private static final String READER_PASSWORD = "guest";
	private static final String WRITER = "writer";
	private static final String WRITER_PASSWORD = "writer";
	/////////////////////////////////////////////////////////////////////
	//
	// ENTRY POINT
	//
	/////////////////////////////////////////////////////////////////////
	public static void main(String... args)
	{
		CreateDerbyDB demo = new CreateDerbyDB(true);
		demo.execute();
	}

	/////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTOR
	//
	/////////////////////////////////////////////////////////////////////
	private CreateDerbyDB(boolean runningEmbedded)
	{
	}

	/////////////////////////////////////////////////////////////////////
	//
	// 创建主流程
	//
	/////////////////////////////////////////////////////////////////////
	/**
	 * Run all of the experiments
	 */
	private void execute()
	{
		try
		{
			//系统里设置一下用户名密码的记录存在DB里面
			String authenticationProvider = "NATIVE:" + DB_NAME + ":LOCAL";
			println("Setting authentication provider to " + authenticationProvider);
			System.setProperty("derby.authentication.provider", authenticationProvider);
			//创建数据库(加密/用户名/密码/连接数据库的密码)
			Connection dboConn = createDatabase();
			//创建人员
			createUsers(dboConn);
			//设置数据库的设置不被系统设置覆盖
			executeCommand(dboConn,"CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.propertiesOnly','true')");
			//给人员相应权限
			executeCommand(dboConn, "CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY( 'derby.database.fullAccessUsers','xushuo')");
			createTable(dboConn);
			cleanUpAndShutDown();
		} catch (Exception e)
		{
			errorPrintAndExit(e);
		}
	}

	/**
	 * Create more users. Note that the credentials for the Database Owner were
	 * stored in the database automatically when the database was created.
	 */
	public void createUsers(Connection conn) throws SQLException
	{
		println("Storing some sample users in the database.");
		PreparedStatement ps = prepare(conn, "call syscs_util.syscs_create_user( ?, ? )");
		createUser(ps, READER, READER_PASSWORD);
		createUser(ps, WRITER, WRITER_PASSWORD);
		ps.close();
	}

	private void createUser(PreparedStatement ps, String userName, String password) throws SQLException
	{
		println("Creating user " + userName);
		ps.setString(1, userName);
		ps.setString(2, password);
		ps.execute();
	}
	
	

	/**
	 * Create roles and grant them privileges.
	 */
	private void createRoles(Connection conn) throws SQLException
	{
		println("Creating roles and granting privileges to them...");
		executeCommand(conn, "CREATE ROLE adder");
		executeCommand(conn, "CREATE ROLE viewer");
		executeCommand(conn, "GRANT adder TO " + WRITER);
		executeCommand(conn, "GRANT viewer TO " + READER);
	}

	/**
	 * Create and populate a table and grant privileges related to it.
	 */
	private void createTable(Connection conn) throws SQLException
	{
		println("Creating table PURCHASE...");
		executeCommand(conn, "CREATE TABLE PURCHASELIST(PurchaseID VARCHAR(20) NOT NULL,"
				+ "CreateTime DATE,"
				+ "OrderTime DATE,"
				+ "Supplier VARCHAR(30),"
				+ "Classification VARCHAR(30),"
				+ "Model VARCHAR(30),"
				+ "Factory VARCHAR(30),"
				+ "UnitPrice DOUBLE,"
				+ "BatchLot VARCHAR(30),"
				+ "Quantity DOUBLE,"
				+ "TotalPrice DOUBLE,"
				+ "PickingAddress VARCHAR(200),"
				+ "Distrabution VARCHAR(1),"
				+ "Car VARCHAR(20),"
				+ "TansCost INT,"
				+ "PRIMARY KEY (PurchaseID))");
		println("Creating table CLASSIFICATION...");
		executeCommand(conn, "CREATE TABLE CLASSIFICATION(Name VARCHAR(30) NOT NULL,"
				+ "PRIMARY KEY (Name))");
		println("Creating table MODEL...");
		executeCommand(conn, "CREATE TABLE MODEL(InternalName VARCHAR(30) NOT NULL,"
				+ "Name VARCHAR(30),"
				+ "Classification VARCHAR(30),"
				+ "PRIMARY KEY (InternalName))");
		println("Creating table FACTORY...");
		executeCommand(conn, "CREATE TABLE FACTORY(Name VARCHAR(30) NOT NULL,"
				+ "PRIMARY KEY (Name))");
		executeCommand(conn, "CREATE TABLE SUPPLIER(Name VARCHAR(30) NOT NULL,"
				+ "PRIMARY KEY (Name))");
	}

	/**
	 * 删除某个人员对这个数据库的进入权限
	 */
	public void dropUsers(Connection conn) throws SQLException
	{
		println("Dropping sample users from the database...");
		PreparedStatement ps = prepare(conn, "call syscs_util.syscs_drop_user( ? )");
		dropUser(ps, READER);
		dropUser(ps, WRITER);
		ps.close();
	}

	private void dropUser(PreparedStatement ps, String userName) throws SQLException
	{
		println("Dropping user " + userName);
		ps.setString(1, userName);
		ps.execute();
	}

	/**
	 * 删除自定义角色
	 */
	private void dropRoles(Connection conn) throws SQLException
	{
		println("Dropping roles...");
		executeCommand(conn, "DROP ROLE adder");
		executeCommand(conn, "DROP ROLE viewer");
	}

	/**
	 * 删除表单
	 */
	private void dropTable(Connection conn) throws SQLException
	{
		executeCommand(conn, "DROP TABLE accessibletbl");
	}

	/**
	 * Try to connect without supplying credentials
	 */
	private void tryToConnectWithoutCredentials() throws Exception
	{
		println("Trying to connect without supplying credentials...");
		try
		{
			getConnection(null, null);
			println("ERROR: Unexpectedly connected to database " + DB_NAME);
			cleanUpAndShutDown();
		} catch (SQLException e)
		{
			if (e.getSQLState().equals("08004"))
			{
				println("As expected, could not get a connection without " + "supplying credentials.");
			} else
			{
				errorPrintAndExit(e);
			}
		}
	}

	/**
	 * Verify that the READER user can select but not insert
	 */
	private void verifyReaderPrivileges() throws Exception
	{
		Connection readerConn = getConnection(READER, READER_PASSWORD);
		println("Setting role to VIEWER");
		executeCommand(readerConn, "SET ROLE VIEWER");
		readRow(readerConn); // should succeed
		try
		{
			writeRow(readerConn);
			println("ERROR: Unexpectedly allowed to insert into table");
			cleanUpAndShutDown();
		} catch (SQLException e)
		{
			if (e.getSQLState().equals("42500"))
			{
				println("As expected, failed to insert row.");
			} else
			{
				errorPrintAndExit(e);
			}
		}
		readerConn.close();
	}

	/**
	 * Verify that the WRITER can read and write but not delete
	 */
	private void verifyWriterPrivileges() throws Exception
	{
		Connection writerConn = getConnection(WRITER, WRITER_PASSWORD);
		// set role to ADDER
		println("Setting role to ADDER");
		executeCommand(writerConn, "SET ROLE ADDER");
		// should succeed
		readRow(writerConn);
		writeRow(writerConn);
		try
		{
			deleteRow(writerConn); // should fail
			println("ERROR: Unexpectedly allowed to DELETE.");
			cleanUpAndShutDown();
		} catch (SQLException e)
		{
			if (e.getSQLState().equals("42500"))
			{
				println("As expected, failed to delete rows.");
			} else
			{
				errorPrintAndExit(e);
			}
		}
		writerConn.close();
	}

	private void readRow(Connection conn) throws SQLException
	{
		PreparedStatement ps = prepare(conn, "SELECT * FROM sysadm.accessibletbl");
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			println("Value of sysadm.accessibletbl/textcol = " + rs.getString(1));
		}
		rs.close();
		ps.close();
	}

	private void writeRow(Connection conn) throws SQLException
	{
		executeCommand(conn, "INSERT INTO sysadm.accessibletbl VALUES('guest')");
	}

	private void deleteRow(Connection conn) throws SQLException
	{
		executeCommand(conn, "DELETE FROM sysadm.accessibletbl");
	}

	/////////////////////////////////////////////////////////////////////
	//
	// SQL HELPERS
	//
	/////////////////////////////////////////////////////////////////////
	/**
	 * Execute a statement
	 */
	private void executeCommand(Connection conn, String text) throws SQLException
	{
		PreparedStatement ps = prepare(conn, text);
		ps.execute();
		ps.close();
	}

	/**
	 * Prepare a statement
	 */
	private PreparedStatement prepare(Connection conn, String text) throws SQLException
	{
		println(" Preparing: " + text);
		return conn.prepareStatement(text);
	}

	/////////////////////////////////////////////////////////////////////
	//
	// CONNECTION MANAGEMENT
	//
	/////////////////////////////////////////////////////////////////////
	/**
	 * 创建数据库
	 * 'jdbc:derby:GenGuDB;create=true;dataEncryption=true;user=xushuo;password=xushuo;bootPassword=Aa123456';
	 */
	private Connection createDatabase() throws SQLException
	{
		String connectionURL = "jdbc:derby:GenGuDB;create=true;dataEncryption=true;user=xushuo;password=xushuo;bootPassword=Aa123456";
		println("Creating database via this URL: " + connectionURL);
		return DriverManager.getConnection(connectionURL);
	}

	/**
	 * Shut down the engine and exit.
	 */
	private void cleanUpAndShutDown() throws Exception
	{
		// the engine should only be brought down locally
		shutdownEngine();
		System.exit(1);
	}

	private void shutdownEngine()
	{
		String shutdownURL = getConnectionURL(null, DB_OWNER, DB_OWNER_PASSWORD, false, true);
		try
		{
			println("Shutting down engine via this URL: " + shutdownURL);
			DriverManager.getConnection(shutdownURL);
		} catch (SQLException se)
		{
			if (se.getSQLState().equals("XJ015"))
			{
				println("Derby engine shut down normally");
			} else
			{
				printSQLException(se);
			}
		}
	}

	/**
	 * Get a connection to the database
	 */
	private Connection getConnection(String userName, String password) throws SQLException
	{
		String connectionURL = getConnectionURL(DB_NAME, userName, password, false, false);
		println("Getting connection via this URL: " + connectionURL);
		return DriverManager.getConnection(connectionURL);
	}

	private String getConnectionURL(String dbName, String userName, String password, boolean createDB, boolean shutdownDB)
	{
		String connectionURL = "jdbc:derby:";
		if (dbName != null)
		{
			connectionURL = connectionURL + DB_NAME;
		}
		if (userName != null)
		{
			connectionURL = connectionURL + ";user=" + userName;
		}
		if (password != null)
		{
			connectionURL = connectionURL + ";password=" + password;
		}
		if (createDB)
		{
			connectionURL = connectionURL + ";create=true";
		}
		if (shutdownDB)
		{
			connectionURL = connectionURL + ";shutdown=true";
		}
		return connectionURL;
	}

	/////////////////////////////////////////////////////////////////////
	//
	// DIAGNOSTIC PRINTING
	//
	/////////////////////////////////////////////////////////////////////
	/**
	 * Report exceptions and exit.
	 */
	private void errorPrintAndExit(Throwable e)
	{
		if (e instanceof SQLException)
		{
			printSQLException((SQLException) e);
		} else
		{
			println("A non-SQL error occurred.");
			e.printStackTrace();
		}
		System.exit(1);
	}

	/**
	 * Print a list of SQLExceptions.
	 */
	private void printSQLException(SQLException sqle)
	{
		while (sqle != null)
		{
			println("\n---SQLException Caught---\n");
			println(" SQLState: " + (sqle).getSQLState());
			println(" Severity: " + (sqle).getErrorCode());
			println(" Message: " + (sqle).getMessage());
			sqle.printStackTrace();
			sqle = sqle.getNextException();
		}
	}

	/**
	 * Print a diagnostic line to the console
	 */
	private static void println(String text)
	{
		System.out.println(text);
	}
}