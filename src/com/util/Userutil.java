package com.util;

import org.hibernate.Session;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bean.Addcategory;
import com.bean.Addproduct;
import com.bean.Admin;
import com.bean.User;
import com.bean.cartItems;
import com.bean.contact;
import com.bean.orderDetails;
import com.bean.orderItems;

public class Userutil {

	
	
	public static Session cc() {
		Session session = null;
		SessionFactory sf = null;
		try {
			sf = new Configuration()
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(Addcategory.class)
					.addAnnotatedClass(Addproduct.class)
					.addAnnotatedClass(Admin.class)
					.addAnnotatedClass(contact.class)
					.addAnnotatedClass(cartItems.class)
					.addAnnotatedClass(orderItems.class)
					.addAnnotatedClass(orderDetails.class)
					.configure()
					.buildSessionFactory();
			
			session = sf.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;

	}

}
