package com.gengu.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.gengu.dao.MaterialModelDao;

public class MaterialInfoService
{
	private MaterialModelDao materialModelDao;
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final MaterialInfoService single = new MaterialInfoService();

	/**
	 * 单例模式
	 * @return
	 */
	public static MaterialInfoService getInstance()
	{
		return single;
	}
	private MaterialInfoService(){
		materialModelDao=MaterialModelDao.getInstance();
	};
	public String createMaterial(String strMaterial)
	{
		String strResult="OK";
		try
		{
			materialModelDao.createMaterial(strMaterial);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return "创建材料失败";
		}
		return strResult;
	}
	public String deleteMaterial(String strMaterial)
	{
		String strResult="OK";
		try
		{
			materialModelDao.deleteMaterial(strMaterial);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return "创建牌号失败";
		}
		return strResult;
	}
	/**
	 * 创建牌号,创建的Map要和数据库对应
	 * @param strModel
	 * @param strMaterial
	 * @return
	 */
	public String createModel(String strModel,String strMaterial)
	{
		String strResult="OK";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("InternalName",strMaterial+"_"+strModel);
		map.put("Name",strModel);
		map.put("Classification",strMaterial);
		try
		{
			materialModelDao.createModel(map);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return "创建牌号失败";
		}
		return strResult;
	}
	public String deleteModel(String strClass,List<String> modelList)
	{
		String strResult="OK";
		try
		{
			materialModelDao.deleteModels(strClass, modelList);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return "删除牌号失败";
		}
		return strResult;
	}
	public List<String> getAllClassification()
	{
		try
		{
			return materialModelDao.getAllClassification();
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public List<String> getModelsFromClassification(String strClass)
	{
		try
		{
			return materialModelDao.getModelsFromClassification(strClass);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
