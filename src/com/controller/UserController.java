package com.controller;

import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bean.*;
import com.dao.*;



@WebServlet("/UserController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, // 512MB
maxFileSize = 1024 * 1024 * 512, // 512MB
maxRequestSize = 1024 * 1024 * 512) // 512MB
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("mobiledata")) {
			System.out.println("Call");
			String cat=request.getParameter("cat");
			request.setAttribute("cat", cat);
			request.getRequestDispatcher("Samsung.jsp").forward(request, response);
		
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("Register")) {
			System.out.println("Register Call");
			String email = request.getParameter("email");
			boolean isAvail = Auth.isEmailUsed(email);
			if(isAvail) {
			request.setAttribute("error", "E-Mail is Already in used");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		}
		else {
				User u = new User();
				u.setFname(request.getParameter("fname"));

				u.setLname(request.getParameter("lname"));

				u.setAddress(request.getParameter("address"));

				u.setMobile(request.getParameter("mobile"));
				u.setEmail(request.getParameter("email"));
				u.setPassword(request.getParameter("password"));
				u.setCpassword(request.getParameter("cpassword"));
				System.out.println(request.getParameter("password"));
				u.setUsertype(request.getParameter("usertype"));
				
				UserDao.RegisterUser(u);
				response.sendRedirect("login.jsp");
				
			}

		}
		
		//----------------add-category--------------//
		else if (action.equalsIgnoreCase("Addcategory")) {
			System.out.println("Register Call");
			
				Addcategory add = new Addcategory();
				
				request.setAttribute("acatt", add);
				
				add.setCategory(request.getParameter("t1"));
						
				UserDao.Addcategories(add);
				response.sendRedirect("category_show.jsp");
				
			}
		//------------------add-product----------------//
		else if (action.equalsIgnoreCase("Addproduct")) {
			System.out.println("Register Call");
			
			Addproduct pro = new Addproduct();
			String savePath2 = "C:\\Users\\charv\\Desktop\\Charvee LRA\\Project 2021\\Reference_Images";
	        File imgSaveDir=new File(savePath2);
	        if(!imgSaveDir.exists()){
	            imgSaveDir.mkdir();
	        }
			
			Part img = request.getPart("pic");
			String imgName=extractfilename(img);
			 	
			img.write(savePath2 + File.separator + imgName);
				   // String imgPath= savePath2 + File.separator + imgName ;
				pro.setProductcategory(request.getParameter("category"));
				
				pro.setProductname(request.getParameter("p3"));
				pro.setProductquantity(request.getParameter("p4"));
				pro.setProductprise(request.getParameter("p5"));
				pro.setProductdescription(request.getParameter("p6"));
				pro.setProductimage(imgName);
							
				UserDao.Addproducts(pro);
				response.sendRedirect("product_show.jsp");
				
			}
		
		
		//-----------------User-view-delete-----------------//	
		
				else if(action.equalsIgnoreCase("Delete"))
				{
					System.out.println("User Controller Called");
					Integer sid = Integer.parseInt(request.getParameter("sid"));
					System.out.println(sid);
					if(sid != null)
					{
						User u = UserDao.findProductById(sid);
						if(u!=null)
						{
							boolean isDeleted = UserDao.deleteProduct(u);
							if(isDeleted)
							{
								response.sendRedirect("user_show.jsp");
							}else {
								request.setAttribute("error", "User is not deleted | Please Try Again");
								request.getRequestDispatcher("user_show.jsp").forward(request, response);
							}
						}else {
							response.sendRedirect("user_show.jsp");
						}
						
					}else {
						response.sendRedirect("user_show.jsp");
					}
				}
				//-------------feedback-delete-----------------//
				else if(action.equalsIgnoreCase("Deletes"))
				{
					System.out.println("User Controller Called");
					int fid = Integer.parseInt(request.getParameter("fid"));
					System.out.println(fid);
					/*if(fid!= null)
					{*/
						contact u = UserDao.findfeedbackById(fid);
						System.out.println("USER U"+u);
						if(u!=null)
						{
						 UserDao.deletefeedback(u);
						 response.sendRedirect("feedback_show.jsp");
						/*	if(isDeleted)
							{
								response.sendRedirect("feedback_show.jsp");
							}else {
								request.setAttribute("error", "feedback is not deleted | Please Try Again");
								request.getRequestDispatcher("feedback_show.jsp").forward(request, response);
							}*/
						}else {
						//	response.sendRedirect("feedback_show.jsp");
							System.out.println("not delet");
						}
					}/*else
					{
						response.sendRedirect("feedback_show.jsp");

					}}*/
						
						
					
				
				
				//-------------Update-delete-category------------//
				else if(action.equalsIgnoreCase("update"))
				{
					int cid = Integer.parseInt(request.getParameter("cid"));
					Addcategory add = UserDao.findCategoryById(cid);
					if(add!=null)
					{
						request.setAttribute("acat", add);
						request.getRequestDispatcher("update_product_category.jsp").forward(request, response);
					}else {
						response.sendRedirect("category_show.jsp");
					}
				}
				
				else if(action.equalsIgnoreCase("updatecategory"))
				{
					Integer cid = Integer.parseInt(request.getParameter("cid"));
					String category = request.getParameter("cat");
					String subcategory=request.getParameter("scat");
					
					
					
					if(cid == null && category == null && subcategory == null) {
						response.sendRedirect("update_product_category.jsp");
					}
					else 
					{
						Addcategory add = new Addcategory();
						add.setCid(cid);
						add.setCategory(category);
					


						if(add!=null)
						{
							boolean isUpdated = UserDao.updateCategory(add);
							if(isUpdated)
							{
								response.sendRedirect("category_show.jsp");
							}else {
								request.setAttribute("error", "Category is not updated | Please Try Again");
								request.getRequestDispatcher("update_product_category.jsp").forward(request, response);
							}
						}
						
				}
				}


				else if(action.equalsIgnoreCase("Deletee"))
				{
					Integer cid = Integer.parseInt(request.getParameter("cid"));
					if(cid != null)
					{
						Addcategory add = UserDao.findProductsById(cid);
						if(add!=null)
						{
							boolean isDeleted = UserDao.deleteProducts(add);
							if(isDeleted)
							{
								response.sendRedirect("category_show.jsp");
							}else {
								request.setAttribute("error", "Category is not deleted | Please Try Again");
								request.getRequestDispatcher("category_show.jsp").forward(request, response);
							}
						}else {
							response.sendRedirect("category_show.jsp");
						}
						
					}else {
						response.sendRedirect("category_show.jsp");
					}
				}
				
		//-------------Update-delete-product------//				
				else if(action.equalsIgnoreCase("updatee"))
				{
					int pid = Integer.parseInt(request.getParameter("pid"));
					Addproduct pro = UserDao.findProductssById(pid);
					if(pro!=null)
					{
						request.setAttribute("acatt", pro);
						request.getRequestDispatcher("update_product.jsp").forward(request, response);
					}else {
						response.sendRedirect("product_show.jsp");
					}
				}
				

				else if(action.equalsIgnoreCase("updateproduct"))
				{
					String savePath2 = "C:\\Users\\charv\\Desktop\\Charvee LRA\\Project 2021\\Reference_Images";
			        File imgSaveDir=new File(savePath2);
			        if(!imgSaveDir.exists()){
			            imgSaveDir.mkdir();
			        }
					
					Part img = request.getPart("pic");
					String imgName=extractfilename(img);
					 	
					img.write(savePath2 + File.separator + imgName);
					    
					   // String imgPath= savePath2 + File.separator + imgName ;
					Integer pid = Integer.parseInt(request.getParameter("pid"));
					String productcategory = request.getParameter("category");
					String productname = request.getParameter("z3");
					String productquantity=request.getParameter("z4");
					String productprise = request.getParameter("z5");
					String productdescription=request.getParameter("z6");
					
					
					
					
					
					Addproduct pro = new Addproduct();
					
					pro.setPid(pid);
					pro.setProductcategory(productcategory);
					pro.setProductname(productname);
					pro.setProductquantity(productquantity);
					pro.setProductprise(productprise);
					pro.setProductdescription(productdescription);
					pro.setProductimage(imgName);


					if(pro!=null)
					{
						boolean isUpdated = UserDao.updateProducts(pro);
						
						if(isUpdated)
						{
							response.sendRedirect("product_show.jsp");
						}else {
							request.setAttribute("error", "Product is not updated | Please Try Again");
							request.getRequestDispatcher("product_show.jsp").forward(request, response);
						}
					}
				}

				
				
				else if(action.equalsIgnoreCase("deleteee"))
				{
					Integer pid = Integer.parseInt(request.getParameter("pid"));
					if(pid != null)
					{
						Addproduct pro = UserDao.findProductssById(pid);
						if(pro!=null)
						{
							boolean isDeleted = UserDao.deleteProductss(pro);
							if(isDeleted)
							{
								response.sendRedirect("product_show.jsp");
							}else {
								request.setAttribute("error", "Product is not deleted | Please Try Again");
								request.getRequestDispatcher("product_show.jsp").forward(request, response);
							}
						}else {
							response.sendRedirect("product_show.jsp");
						}
						
					}else {
						response.sendRedirect("product_show.jsp");
					}
				}
				
		//----------------contact-feedback-----------------//
		     	else if(action.equalsIgnoreCase("Send Feedback"))
				{
					
					System.out.println("call");
					contact c=new contact();
					//c.setSid(Integer.parseInt(request.getParameter("sid")));
					c.setName(request.getParameter("Name"));
					c.setEmail(request.getParameter("Email"));
					c.setMsg(request.getParameter("Message"));
					
					UserDao.contacts(c);
					response.sendRedirect("index.jsp");
					
					
					
					
					
				}
						
		//----------------forgot-password-----//
			
		else if(action.equalsIgnoreCase("Reset password"))
		{
			String email=request.getParameter("email");
			User u = null;
			u=UserDao.checkEmail(email);
			//System.out.println("\n Email id is:"+u.getEmail());
			if(u==null)
			{
				//System.out.println(p.getFname());
				request.setAttribute("invalidemail", "Email Address Not Valid");
				request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
			}
			else
			{
				
				String emailid=u.getEmail();
				String username=u.getFname();
				final String Senderid ="charvee1p@gmail.com";
				final String password = "8444kimi";

				Properties props = new Properties();
				props.put("mail.smtp.host", "true");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");

				Session session = Session.getInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(Senderid, password);
					}
				});

				try {
					Random rand = new Random();
					int otp = rand.nextInt(900000) + 100000;
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(Senderid));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailid, true));// to
																									// mail
																									// address.
					message.setSubject("OTP Request");
					
					String msg1 = "<!DOCTYPE html><html><head></head><body><center><div style='background-color:#f8f8f8; width:500px; height:200px'><div style='background-color:#00e58b; width:500px; height:50px'><h3 style='color:white; padding-top:10px;'>EmailDemo.com </h3></div><p style='color:gray; margin-left:-300px;'>Dear "
							+ username + ",</p><br><p style='color:gray; margin-top:-10px;'>" + otp
							+ "  is your One Time Password.Do NOT share this code with anyone for security reasons.this is valid for 10 minutes.</p><div></center></body></html>";
					message.setContent(msg1, "text/html; charset=utf-8");
			
					Transport.send(message);

					HttpSession otpsession = request.getSession();
					otpsession.setAttribute("otp", otp);
					otpsession.setMaxInactiveInterval(10 * 60); /*Session Set for 10 minutes*/
					otpsession.setAttribute("UserData", u);
					request.getRequestDispatcher("SendOTP.jsp").forward(request, response);

				} catch (Exception e) {
					request.setAttribute("SendOtpError", "Otp Not Send");
					request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
					e.printStackTrace();
				}
			}
			
		}
		
		else if(action.equalsIgnoreCase("Verify"))
		{
			HttpSession session=request.getSession(false);
			String G_otp=String.valueOf(session.getAttribute("otp"));
			String E_otp=request.getParameter("EnterOTP");
			
			if(G_otp.equalsIgnoreCase(E_otp))
			{
				request.setAttribute("Otpmatch", "OTP Match");
				request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("notmatch", "OTP Not Match");
				request.getRequestDispatcher("SendOTP.jsp").forward(request, response);
			}
			
			
			
		}
		else if(action.equalsIgnoreCase("Confirm"))
		{
			String pswd=request.getParameter("password");
			User u=new User();
			HttpSession session=request.getSession(false);
			u=(User)session.getAttribute("UserData");
			u.setPassword(pswd);
			u.setEmail(u.getEmail());
			
			int r=UserDao.resetPassword(u);
			if(r>0)
			{
				response.sendRedirect("index.jsp");
				System.out.println("Password Recovery Success!");
			}
			else
			{
				System.out.println("Error");
			}
			
			
		}

				

				}


	private String extractfilename(Part file) {
	    String cd = file.getHeader("content-disposition");
	    String[] items = cd.split(";");
	    for (String string : items) {
	        if (string.trim().startsWith("filename")) {
	            return string.substring(string.indexOf("=") + 2, string.length()-1);
	        }
	    }
	    return "";
	}
				}

				
				
				

			

