package com.gengu.test;

// does not use derby.properties
import java.io.PrintWriter;
import java.sql.*;
import org.apache.derby.drda.NetworkServerControl;

/*
* <p>
* This program showcases how SQL authorization is automatically turned
* on when you run with NATIVE authentication. You can run this program
* either embedded or client server.
* </p>
*
* <p>
* Here's how you compile the program:
* </p>
*
* <pre>
* javac -cp ${DERBY_LIB}/derbynet.jar NativeAuthenticationExample.java
* </pre>
*
* <p>
* Here's how you run the program embedded:
* </p>
*
* <pre>
* java -cp ${DERBY_LIB}/derby.jar:. NativeAuthenticationExample embedded
* </pre>
*
* <p>
* Here's how you run the program client/server:
* </p>
*
* <pre>
* java -cp \
*
${DERBY_LIB}/derby.jar:${DERBY_LIB}/derbynet.jar:${DERBY_LIB}/
derbyclient.jar:. \
* NativeAuthenticationExample client
* </pre>
*/
public class testForJDBC
{
	/////////////////////////////////////////////////////////////////////
	//
	// CONSTANTS
	//
	/////////////////////////////////////////////////////////////////////
	private static final String DB_NAME = "nativeAuthDB";
	// stored as SYSADM
	private static final String DB_OWNER = "sysadm";
	private static final String DB_OWNER_PASSWORD = "shh123ihtybb87m";
	private static final String USER_WITHOUT_ROLE = "NOACC";
	private static final String USER_WITHOUT_ROLE_PASSWORD = "ajaxj3x9";
	private static final String READER = "GUEST";
	private static final String READER_PASSWORD = "java5w6x";
	private static final String WRITER = "SQLSAM";
	private static final String WRITER_PASSWORD = "light8q9bulb";
	private static final String EMBEDDED = "embedded";
	private static final String CLIENT = "client";
	/////////////////////////////////////////////////////////////////////
	//
	// STATE
	//
	/////////////////////////////////////////////////////////////////////
	private boolean _runningEmbedded;
	private NetworkServerControl _server;

	/////////////////////////////////////////////////////////////////////
	//
	// ENTRY POINT
	//
	/////////////////////////////////////////////////////////////////////
	public static void main(String... args)
	{
		testForJDBC demo = parseArgs(args);
		if (demo != null)
		{
			demo.execute();
		} else
		{
			println("Bad command line args.");
		}
	}

	private static testForJDBC parseArgs(String... args)
	{
		if ((args == null) || (args.length != 1))
		{
			return null;
		}
		String mode = args[0];
		if (EMBEDDED.equals(mode))
		{
			return new testForJDBC(true);
		} else if (CLIENT.equals(mode))
		{
			return new testForJDBC(false);
		} else
		{
			return null;
		}
	}

	/////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTOR
	//
	/////////////////////////////////////////////////////////////////////
	private testForJDBC(boolean runningEmbedded)
	{
		_runningEmbedded = runningEmbedded;
	}

	/////////////////////////////////////////////////////////////////////
	//
	// FEATURE SHOWCASE
	//
	/////////////////////////////////////////////////////////////////////
	/**
	 * Run all of the experiments
	 */
	private void execute()
	{
		try
		{
			String authenticationProvider = "NATIVE:" + DB_NAME + ":LOCAL";
			// this turns on NATIVE authentication as well as
			// SQL authorization
			println("Setting authentication provider to " + authenticationProvider);
			System.setProperty("derby.authentication.provider", authenticationProvider);
			if (!_runningEmbedded)
			{
				startServer();
			}
			Connection dboConn = createDatabase();
			createUsers(dboConn);
			createRoles(dboConn);
			createTable(dboConn);
			tryToConnectWithoutCredentials(); // should fail
			// a valid user can connect even if they haven't been
			// assigned any roles
			getConnection(USER_WITHOUT_ROLE, USER_WITHOUT_ROLE_PASSWORD);
			verifyReaderPrivileges();
			verifyWriterPrivileges();
			println("Using Database Owner connection again");
			dropTable(dboConn);
			dropRoles(dboConn);
			dropUsers(dboConn);
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
		createUser(ps, USER_WITHOUT_ROLE, USER_WITHOUT_ROLE_PASSWORD);
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
		execute(conn, "CREATE ROLE adder");
		execute(conn, "CREATE ROLE viewer");
		execute(conn, "GRANT adder TO " + WRITER);
		execute(conn, "GRANT viewer TO " + READER);
	}

	/**
	 * Create and populate a table and grant privileges related to it.
	 */
	private void createTable(Connection conn) throws SQLException
	{
		println("Creating table accessibletbl...");
		execute(conn, "CREATE TABLE accessibletbl(textcol VARCHAR(6))");
		execute(conn, "INSERT INTO accessibletbl VALUES('hello')");
		println("Granting select/insert privileges to adder...");
		execute(conn, "GRANT SELECT, INSERT ON accessibletbl TO adder");
		println("Granting select privileges to viewer");
		execute(conn, "GRANT SELECT ON accessibletbl TO viewer");
	}

	/**
	 * Drop users except for Database Owner.
	 */
	public void dropUsers(Connection conn) throws SQLException
	{
		println("Dropping sample users from the database...");
		PreparedStatement ps = prepare(conn, "call syscs_util.syscs_drop_user( ? )");
		dropUser(ps, USER_WITHOUT_ROLE);
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
	 * Drop roles.
	 */
	private void dropRoles(Connection conn) throws SQLException
	{
		println("Dropping roles...");
		execute(conn, "DROP ROLE adder");
		execute(conn, "DROP ROLE viewer");
	}

	/**
	 * Drop the table.
	 */
	private void dropTable(Connection conn) throws SQLException
	{
		execute(conn, "DROP TABLE accessibletbl");
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
		execute(readerConn, "SET ROLE VIEWER");
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
		execute(writerConn, "SET ROLE ADDER");
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
		execute(conn, "INSERT INTO sysadm.accessibletbl VALUES('guest')");
	}

	private void deleteRow(Connection conn) throws SQLException
	{
		execute(conn, "DELETE FROM sysadm.accessibletbl");
	}

	/////////////////////////////////////////////////////////////////////
	//
	// SQL HELPERS
	//
	/////////////////////////////////////////////////////////////////////
	/**
	 * Execute a statement
	 */
	private void execute(Connection conn, String text) throws SQLException
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
	 * Create the database
	 */
	private Connection createDatabase() throws SQLException
	{
		String connectionURL = getConnectionURL(DB_NAME, DB_OWNER, DB_OWNER_PASSWORD, true, false);
		println("Creating database via this URL: " + connectionURL);
		return DriverManager.getConnection(connectionURL);
	}

	/**
	 * Shut down the engine and exit.
	 */
	private void cleanUpAndShutDown() throws Exception
	{
		// Shut down the server before the engine. this is so that
		// we can authenticate the shutdown credentials in the
		// booted database.
		if (_server != null)
		{
			stopServer();
		}
		// the engine should only be brought down locally
		_runningEmbedded = true;
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
		String connectionURL = _runningEmbedded ? "jdbc:derby:" : "jdbc:derby://localhost:1527/";
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
	// SERVER MANAGEMENT
	//
	/////////////////////////////////////////////////////////////////////
	/**
	 * Start the Derby server
	 */
	private void startServer() throws Exception
	{
		_server = new NetworkServerControl(DB_OWNER, DB_OWNER_PASSWORD);
		println("Starting the Derby server...");
		_server.start(new PrintWriter(System.out));
		// pause to let the server come up
		Thread.sleep(5000L);
	}

	/**
	 * Shut down the Derby server
	 */
	private void stopServer() throws Exception
	{
		println("Stopping the Derby server...");
		_server.shutdown();
		// pause to let the server come down
		Thread.sleep(5000L);
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