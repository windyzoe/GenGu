package com.gengu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

public class MaterialModelDao
{
	public void createMaterial(String strMaterial) throws SQLException
	{
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			List<String> strNameList=new ArrayList<>();
			strNameList.add("name");
			List<Object> strValueList=new ArrayList<Object>();
			strValueList.add(strMaterial);
			String strSQL=DaoUtil.getInstance().getSQLString("insert into CLASSIFICATION", strNameList);
			jdbcUtil.updateByPreparedStatement(strSQL, strValueList);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
	}
	public List<String> getAllClassification() throws SQLException
	{
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<String> strClassList=new ArrayList<>();
		try
		{
			List<Map<String, Object>> templist =jdbcUtil.findResult("select * from classification", null);
			for (Map<String, Object> map : templist)
			{
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					//System.out.println(entry.getValue().toString());
					strClassList.add(entry.getValue().toString());
				}
			}
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return strClassList;
	}
	public List<String> getModelsFromClassification(String strClass) throws SQLException
	{
		List<String> strModels=new ArrayList<>();
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			List<String> params = new ArrayList<>();
			params.add(strClass);
			List<Map<String, Object>> templist =jdbcUtil.findResult("select Name from model where Classification=?", params);
			for (Map<String, Object> map : templist)
			{
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					strModels.add(entry.getValue().toString());
				}
			}
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return strModels;
	}
	public void createModel(Map<String, Object> map) throws SQLException
	{
		List<String> strNameList=new ArrayList<>();
		List<Object> strValueList=new ArrayList<>();
		for (Map.Entry<String, Object> entry : map.entrySet())
		{
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			strNameList.add(entry.getKey());
			strValueList.add(entry.getValue());
		}
		String strSQL =DaoUtil.getInstance().getSQLString("insert into model", strNameList);
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			jdbcUtil.updateByPreparedStatement(strSQL, strValueList);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
	}
	public void deleteModels(String strClass ,List<String> modelList) throws SQLException
	{
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		String strSQL ="delete from MODEL where Name=? and Classification='"+strClass+"'";
		List<List<?>> obList=new ArrayList<>();
		for (String string : modelList)
		{
			List<Object> tempList=new ArrayList<>();
			tempList.add(string);
			obList.add(tempList);
		}
		try
		{
			jdbcUtil.updateManyByPreparedStatement(strSQL, obList);
		} catch (SQLException e)
		{
			throw e;
			
		} finally
		{
			jdbcUtil.releaseConn();
		}
	}
}
