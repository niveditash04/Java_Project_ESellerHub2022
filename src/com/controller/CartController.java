package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.cartItems;
import com.dao.UserDao;


@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null) {
			if(action.equalsIgnoreCase("Add to cart"))
			{
				int productId = Integer.parseInt(request.getParameter("pid"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				int userId = Integer.parseInt(request.getParameter("userid"));
				int amount = Integer.parseInt(request.getParameter("amount"));
				
				
				int availQuantity = UserDao.getProductQuantity(productId);
				if(quantity > availQuantity)
				{
					request.setAttribute("error", "There is only " + availQuantity + " in stock");
					request.getRequestDispatcher("cart.jsp").forward(request, response);
				}else {
					cartItems ci = new cartItems();
					ci.setProductId(productId);
					ci.setQuantity(quantity);
					ci.setUserID(userId);
					ci.setAmount(amount);
					
					boolean isAdded = UserDao.addToCart(ci);
					if(isAdded) {
						response.sendRedirect("cart.jsp");
					}else {
						request.setAttribute("error", "Please Try again later");
						request.getRequestDispatcher("cart.jsp").forward(request, response);
					}
				}
			}else if(action.equalsIgnoreCase("Remove")) {
				int cartId = Integer.parseInt(request.getParameter("cid"));
				
				UserDao.RemoveFromCart(cartId);
				response.sendRedirect("cart.jsp");
			}
		}else {
			response.sendRedirect("login.jsp");
		}
	}

}
