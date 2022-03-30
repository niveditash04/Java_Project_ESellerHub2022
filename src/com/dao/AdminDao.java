package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bean.*;
//import com.util.DBConnection;
import com.util.Userutil;

public class AdminDao 
{
	public static List<Addcategory> getAllCategory()
	{
		List<Addcategory> list = null;
		try {
			Session session = Userutil.cc();
			String hql = "From Addcategory";
			System.out.println(hql);
			list = session.createQuery(hql).list();
			session.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	
	public static List<Addproduct> getAllProduct()
	{
		List<Addproduct> list = null;
		try {
			Session session = Userutil.cc();
			String hql = "from Addproduct where";
			list = session.createQuery(hql).list();
			session.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	



}
