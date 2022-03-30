 package com.dao;

import java.util.*;

import org.hibernate.*;

import com.bean.*;
import com.util.*;

public class Auth {
	
	@SuppressWarnings("unchecked")
	public static User Login(User u) {
		Session session = Userutil.cc();
		List<User> users = null;
		try {
			String hql = "from User as u where u.email = :email and u.password = :password";
			Query q = session.createQuery(hql);
			q.setParameter("email", u.getEmail());
			q.setParameter("password", u.getPassword());
		//	q.setParameter("usertype", u.getUsertype());
			
			users = q.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		if (users.size() > 0) {
			return (User) users.get(0);
		} else {
			return null;
		}

	}
	
	public static boolean isEmailUsed(String email) {
		boolean isEmailUsed = false;
		Session session = Userutil.cc();
		try {
			String hql = "from User as u where u.email = :email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			List<User> list = query.list();
			if(list.size() > 0)
			{
				isEmailUsed = true;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
		
		return isEmailUsed;
	}

}
