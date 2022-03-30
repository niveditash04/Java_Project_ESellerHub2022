package com.dao;

import java.util.List;







import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.Addcategory;
import com.bean.Addproduct;
import com.bean.User;
import com.bean.cartItems;
import com.bean.contact;
import com.bean.orderDetails;
import com.bean.orderItems;
import com.util.Userutil;


public class UserDao {

	public static void RegisterUser(User un) {
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.save(un);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}

	}
	public static List<User> getAlluser() {
		Session session = null;

		List<User> list = new ArrayList<User>();
		try {
			session = Userutil.cc();
			//String hql = "FROM user";
			/*Query q = session.createQuery(hql);
			list = q.list();
			*/
			list = session.createQuery("FROM User").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
	//-----------------------category---------//
	
	public static void Addcategories(Addcategory add) {
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.save(add);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Addcategory> getAllusers() {
		Session session = null;

		@SuppressWarnings("unused")
		List<Addcategory> list = new ArrayList<Addcategory>();
		try {
			session = Userutil.cc();
			//String hql = "FROM user";
			/*Query q = session.createQuery(hql);
			list = q.list();
			*/
			list = session.createQuery("FROM Addcategory").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
	//-------------------------product---------------------//
	public static void Addproducts(Addproduct pro) {
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.save(pro);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Addproduct> getAlluserss() {
		Session session = null;

		@SuppressWarnings("unused")
		List<Addproduct> list = new ArrayList<Addproduct>();
		try {
			session = Userutil.cc();
			//String hql = "FROM user";
			/*Query q = session.createQuery(hql);
			list = q.list();
			*/
			list = session.createQuery("FROM Addproduct").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	
	public static User findProductById(int sid) {
		User u =null;
		Session session = Userutil.cc();
		try {
			u = session.get(User.class, sid);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return u;
	}
	public static contact findfeedbackById(int fid) {
		contact u =null;
		Session session = Userutil.cc();
		try {
			u = session.get(contact.class, fid);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return u;
	}
	
	public static Addcategory findCategoryById(int sid) {
		Addcategory u =null;
		Session session = Userutil.cc();
		try {
			u = session.get(Addcategory.class, sid);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return u;
	}


	public static boolean deleteProduct(User p) {
		boolean isDeleted = true;
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.delete(p);
			tr.commit();
		}catch(Exception ex)
		{
			isDeleted = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return isDeleted;
	}

	@SuppressWarnings("unchecked")
	public static List<Addproduct> getAllProductByCatName(String cname)
	{	
		System.out.println("cname is... "+cname);
		List<Addproduct> listProducts=null;
		Session session= Userutil.cc();
		try
		{
			String hql = "from Addproduct a where a.productcategory= :cname";
			System.out.println(hql);
			
			Query query = session.createQuery(hql);
			query.setParameter("cname", cname);
			listProducts = query.list();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    finally 
	    {
		//session.close();
           }
		return listProducts;
	}
	

	public static Addcategory findProductsById(int cid) {
		Addcategory add =null;
		Session session = Userutil.cc();
		try {
			add = session.get(Addcategory.class, cid);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return add;
	}
	
	public static boolean updateCategory(Addcategory add) {
		boolean isUpdated = true;
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.update(add);
			tr.commit();
		}catch(Exception ex)
		{
			isUpdated = false;
			ex.printStackTrace();
		}
		return isUpdated;
	}
	

	public static boolean deleteProducts(Addcategory p) {
		boolean isDeleted = true;
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.delete(p);
			tr.commit();
		}catch(Exception ex)
		{
			isDeleted = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return isDeleted;
	}

	public static Addproduct findProductssById(int pid) {
		Addproduct pro =null;
		Session session = Userutil.cc();
		try {
			pro = session.get(Addproduct.class, pid);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return pro;
	}
	
	public static boolean updateProducts(Addproduct pro) {
		boolean isUpdated = true;
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.update(pro);
			tr.commit();
		}catch(Exception ex)
		{
			isUpdated = false;
			ex.printStackTrace();
		}
		return isUpdated;
	}

	public static boolean deleteProductss(Addproduct p) {
		boolean isDeleted = true;
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.delete(p);
			tr.commit();
		}catch(Exception ex)
		{
			isDeleted = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return isDeleted;
	}
	//-------------------forgot-password-----------------------//
public static User checkEmail(String email) {
		
		Session session=Userutil.cc();
		Transaction tr = session.beginTransaction();
//		User u=session.get(User.class,email);
		String hql = "from User as u where u.email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		List<User> list = query.list();
//		tr.commit();
		session.close();
		User u = list.get(0);
		return u;
	}
	
	public static int resetPassword(User u) {
	
		int n=1;
		Session session=Userutil.cc();
		Transaction tr = session.beginTransaction();
		session.update(u);
		tr.commit();
		session.close();
		
		
		return n;
	}
	
	
	//--------------cart-----------------//
	public static int getProductQuantity(int productId) {
		Addproduct p = null;
		Session session = Userutil.cc();
		try {
			p = (Addproduct)session.get(Addproduct.class, productId);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return Integer.parseInt(p.getProductquantity());
	}
	public static boolean addToCart(cartItems ci) {
		boolean isAdded = true;
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.save(ci);
			tr.commit();
			reduceQuantity(ci.getProductId(), ci.getQuantity());
		}catch(Exception ex) {
			isAdded = false;
			ex.printStackTrace();
		}
		return isAdded;
	}
	private static void reduceQuantity(int productId, int quantity) {
		
		Session session = Userutil.cc();
		Addproduct p = findProductssById(productId);
		Integer updatedquantity = Integer.parseInt(p.getProductquantity()) - quantity;
		p.setProductquantity(updatedquantity.toString());
		try {
			Transaction tr = session.beginTransaction();
			session.update(p);
			tr.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	
	public static List<cartItems> getCartItems(int uid){
		Session session = Userutil.cc();
		List<cartItems> list = null;
		try {
			String hql = "from cartItems as c where c.userID = :uid";
			Query query = session.createQuery(hql);
			query.setParameter("uid", uid);
			list = query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	
	public static List<Addproduct> getProducts(String min, String max){
		List<Addproduct> list=null;
		Session session = Userutil.cc();
		try {
			String hql = "from Addproduct as a where a.productprise between :min and :max";
			//String hql = "from Addproduct as a where a.productprise >= :min and a.productprise < :max";
			Query query = session.createQuery(hql);
			query.setParameter("min", min);
			query.setParameter("max", max);
			list = query.list();
		}catch(Exception ex) {
			
		}finally {
			session.close();
		}
		return list;
	}
	public static void RemoveFromCart(int cartId) {
		
		Session session = Userutil.cc();
		cartItems c = findByCartId(cartId);
		try {
			Transaction tr = session.beginTransaction();
			session.delete(c);
			tr.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	public static cartItems findByCartId(int cartId) {
		cartItems c = null;
		Session session = Userutil.cc();
		
		try {
			c = session.get(cartItems.class, cartId);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return c;
	}
	
	//----------------------------------------------//
	public static User findUserByEmail(String email) {
		User u = null;
		Session session = Userutil.cc();
		try {
			String hql = "from User as u where u.email = :email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			List<User> list = query.list();
			if(list.get(0) != null) {
				u = list.get(0);
			}
		}catch(Exception ex) {
			
		}finally {
			session.close();
		}
		
		return u;
	}

	public static List<cartItems> findCartItemsByUserId(int uid){
		List<cartItems> list = null;
		Session session = Userutil.cc();
		try {
			String hql = "from cartItems as c where c.userID = :uid";
			Query query = session.createQuery(hql);
			query.setParameter("uid", uid);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return list;
	}

	public static boolean createOrder(List<cartItems> list, String txnid, int uid, String amount) {
		boolean isAdded = true;
		System.out.println(txnid + " " + uid + " " + amount);
		orderDetails o = new orderDetails();
		o.setTxnID(txnid);
		o.setUserID(uid);
		o.setAmount(amount);
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.save(o);
			tr.commit();
			int orderId = o.getOrderID();
//			System.out.println("Order id in createOrder " + orderId);
			isAdded = addOrderPrdouct(list, orderId);
			if(!isAdded) {
				Transaction tr1 = session.beginTransaction();
				session.delete(o);
				tr1.commit();
			}
		}catch(Exception ex) {
			isAdded = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return isAdded;
	}
	private static boolean addOrderPrdouct(List<cartItems> list, int orderID) {
		boolean isAdded = true;
		Session session = Userutil.cc();
		try {
			for(cartItems ci: list) {
				Transaction tr = session.beginTransaction();
				orderItems oi = new orderItems();
				oi.setOrderID(orderID);
//				System.out.println("OrderID" + orderID);
				oi.setProductId(ci.getProductId());
				oi.setQuantity(ci.getQuantity());
				session.save(oi);
				tr.commit();
				Transaction tr1 = session.beginTransaction();
				session.delete(ci);
				tr1.commit();
			}
		}catch(Exception ex) {
			isAdded = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return isAdded;
	}
	
	@SuppressWarnings("unchecked")
	public static List<orderDetails> getOrders(){
		List<orderDetails> list = null;
		Session session = Userutil.cc();
		try {
			String hql = "from orderDetails";
			Query query = session.createQuery(hql);
			list = query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return list;
	}
	
	public static orderDetails getOrderByOrderId(int oid) {
		orderDetails od = null;
		Session session = Userutil.cc();
		try {
			od = session.get(orderDetails.class, oid);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return od;
	}
	
	public static List<orderItems> getOrderItemsByOrderId(int oid){
		List<orderItems> list = null;
		Session session = Userutil.cc();
		try {
			String hql = "from orderItems as o where o.orderID = :oid";
			Query query = session.createQuery(hql);
			query.setParameter("oid", oid);
			list = query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public static Addproduct getProdcutById(int pid) {
		Addproduct p = null;
		Session session = Userutil.cc();
		try {
			p = session.get(Addproduct.class, pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}

//--------------contact-feedback-------------//
	public static void contacts(contact c) {
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.save(c);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		
	}
	@SuppressWarnings("unchecked")
	public static List<contact> getcontact() {
		Session session = null;

		List<contact> list = new ArrayList<contact>();
		try {
			session = Userutil.cc();
			//String hql = "FROM user";
			/*Query q = session.createQuery(hql);
			list = q.list();
			*/
			list = session.createQuery("FROM contact").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
	
	
	

	public static void deletefeedback(contact u) {
		//boolean isDeleted = true;
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.delete(u);
			tr.commit();
		}catch(Exception ex)
		{
			//isDeleted = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		//return isDeleted;
	
	}
	//-------------------------------------------//
	
	public static User findUserById(int userId) {
		User u = null;
		Session session = Userutil.cc();
		try {
			u = session.get(User.class, userId);
		}catch(Exception ex) {
			
		}finally {
			session.close();
		}
		
		return u;
	}
	public static orderDetails findOrderById(int oid) {
		orderDetails o = null;
		Session session = Userutil.cc();
		try {
			o = session.get(orderDetails.class, oid);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return o;
	}
	public static void updateOrder(orderDetails o) {
		Session session = Userutil.cc();
		try {
			Transaction tr = session.beginTransaction();
			session.update(o);
			tr.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	public static List<orderDetails> findOrdersByUserId(int userId){
		List<orderDetails> list = null;
		Session session = Userutil.cc();
		try {
			String hql = "from orderDetails as o where o.userID = :uid";
			Query query = session.createQuery(hql);
			query.setParameter("uid", userId);
			list = query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}



}