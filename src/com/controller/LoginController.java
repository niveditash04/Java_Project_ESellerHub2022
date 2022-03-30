package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.*;
import com.dao.*;
import com.util.*;

@WebServlet("/LoginController")

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action != null) {
			if (action.equalsIgnoreCase("login")) {

				String email = request.getParameter("email");
				String password = request.getParameter("password");
				//String usertype = request.getParameter("usertype");
				

				if (!(email.trim().equalsIgnoreCase("") || password.trim().equalsIgnoreCase(""))) {
					User u = new User();
					u.setEmail(email);
					u.setPassword(password);
					//u.setUsertype(usertype);
					User user = Auth.Login(u);

					if (user != null) {
						HttpSession session = request.getSession();
						session.setAttribute("User", user);
						if(user.getUsertype().equalsIgnoreCase("admin")) {
							response.sendRedirect("admindash.jsp");
						}else {
						//request.getRequestDispatcher("index.jsp").forward(request, response);
						response.sendRedirect("home.jsp");
						}
					} 
					else 
					{
						 out.println("<script type=\"text/javascript\">");
						 out.println("alert('User or password incorrect');");
						 out.println("location='login.jsp';");
						 out.println("</script>");		
					}
				}
			}
		}
		else {
			response.sendRedirect("Login.jsp");
		}
	}
}
