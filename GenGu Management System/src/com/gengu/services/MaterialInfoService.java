package com.gengu.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.gengu.dao.MaterialModelDao;

public class MaterialInfoService
{
	public String createMaterial(String strMaterial)
	{
		String strResult="OK";
		try
		{
			new MaterialModelDao().createMaterial(strMaterial);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return "��������ʧ��";
		}
		return strResult;
	}
	public String deleteMaterial(String strMaterial)
	{
		String strResult="OK";
		try
		{
			new MaterialModelDao().deleteMaterial(strMaterial);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return "�����ƺ�ʧ��";
		}
		return strResult;
	}
	/**
	 * �����ƺ�,������MapҪ�����ݿ��Ӧ
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
			new MaterialModelDao().createModel(map);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return "�����ƺ�ʧ��";
		}
		return strResult;
	}
	public String deleteModel(String strClass,List<String> modelList)
	{
		String strResult="OK";
		try
		{
			new MaterialModelDao().deleteModels(strClass, modelList);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return "ɾ���ƺ�ʧ��";
		}
		return strResult;
	}
	public List<String> getAllClassification()
	{
		try
		{
			return new MaterialModelDao().getAllClassification();
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
			return new MaterialModelDao().getModelsFromClassification(strClass);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
