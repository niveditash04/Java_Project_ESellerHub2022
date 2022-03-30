package com.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Admin;

@WebServlet("/AdminloginController")
public class AdminloginController extends HttpServlet {
	Admin admin = null;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action != null) {
			
			if(action.equalsIgnoreCase("login"))
			{
				
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println(email);
			System.out.println(password);

			if (email.equals("admin@gmail.com") && password.equals("admin@1234")) {
				response.sendRedirect("admindash.jsp");

			}
			else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('User or password incorrect');");
				out.println("location='adminlogin.jsp';");
				out.println("</script>");
			}
		}
		
		else if(action.equalsIgnoreCase("logout"))
		{
			HttpSession session = request.getSession();
			session.invalidate();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('logout sucessfully');");
			out.println("location='adminlogin.jsp';");
			out.println("</script>");
		    //response.sendRedirect("adminlogin.jsp");
			
		}

		else {

			response.sendRedirect("adminlogin.jsp");
		}
	}
	}
}


