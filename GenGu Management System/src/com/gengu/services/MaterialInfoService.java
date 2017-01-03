package com.gengu.services;

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
		new MaterialModelDao().createMaterial(strMaterial);
		return strResult;
	}
	public String deleteMaterial(String strMaterial)
	{
		String strResult="OK";
		
		return strResult;
	}
	public String createModel(String strModel,String strMaterial)
	{
		String strResult="OK";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("InternalName",strMaterial+"_"+strModel);
		map.put("Name",strModel);
		map.put("Classification",strMaterial);
		new MaterialModelDao().createModel(map);
		return strResult;
	}
	public String deleteModel(String strModel)
	{
		String strResult="OK";
		
		return strResult;
	}
	public List<String> getAllClassification()
	{
		return new MaterialModelDao().getAllClassification();
	}
	public List<String> getModelsFromClassification(String strClass)
	{
		return new MaterialModelDao().getModelsFromClassification(strClass);
	}
}
