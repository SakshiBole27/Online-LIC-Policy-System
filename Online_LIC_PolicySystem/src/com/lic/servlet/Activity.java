package com.lic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
* Servlet implementation class Activity
*/
@WebServlet("/Activity")
public class Activity extends HttpServlet {
private static final long serialVersionUID = 1L;

/**
* @see HttpServlet#HttpServlet()
*/
public Activity() {
super();
// TODO Auto-generated constructor stub
}

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
HttpSession sec=request.getSession();
String uid=(String) sec.getAttribute("user_id");
String page="index.jsp";
String action=request.getParameter("action");

if(uid==null)
{
response.sendRedirect("login.jsp");
}

else if (action.equalsIgnoreCase("Home")) {
page="adminhome.jsp";
}

request.getRequestDispatcher(page).forward(request, response);
}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
}

}

