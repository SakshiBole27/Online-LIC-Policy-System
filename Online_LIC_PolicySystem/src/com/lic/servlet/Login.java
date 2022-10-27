package com.lic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lic.bean.LoginBean;
import com.lic.bean.PermissionBean;
import com.lic.controller.Controller;

/**
* Servlet implementation class Login
*/
@WebServlet("/Login")
public class Login extends HttpServlet {
private static final long serialVersionUID = 1L;
/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub

String username=request.getParameter("username");
String password=request.getParameter("password");


LoginBean log=new Controller().getUserByLoginDetails(username,password);

if (log.getUid()!=null) {

HttpSession sec=request.getSession(true);
sec.setMaxInactiveInterval(60*60*24);

sec.setAttribute("user_id",log.getUid());

List<PermissionBean>plist=new Controller().getPermissionByRole(log.getRole());

sec.setAttribute("role",plist);
sec.setAttribute("name",log.getName());
sec.setAttribute("company", log.getCompany());
request.getRequestDispatcher("adminhome.jsp").forward(request, response);

}
else
{
request.setAttribute("msg","You Have No Permission.. Please Check Login Details..");
request.getRequestDispatcher("login.jsp").forward(request, response);
}

}

}
