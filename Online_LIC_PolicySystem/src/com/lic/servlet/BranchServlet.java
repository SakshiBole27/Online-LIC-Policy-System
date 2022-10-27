package com.lic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lic.bean.PermissionBean;
import com.lic.bean.BranchBean;
import com.lic.controller.Controller;


/**
* Servlet implementation class UserServlet
*/
@WebServlet("/BranchServlet")
public class BranchServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
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
else if(action.equalsIgnoreCase("manageBranch"))
{
List<BranchBean>ulist=new Controller().getUserList();
request.setAttribute("ulist",ulist);
request.getRequestDispatcher("listBranch.jsp").forward(request, response);
}
else if (action.equalsIgnoreCase("addBranch")) {
List<PermissionBean>plist=new Controller().getPermissionByUserId(uid);
request.setAttribute("plist",plist);
request.getRequestDispatcher("addBranch.jsp").forward(request, response);
}
else if (action.equalsIgnoreCase("deleteBranch")) {
String userid=request.getParameter("uid");
String status=request.getParameter("status");
new Controller().deleteUser(userid,status);
response.sendRedirect("BranchServlet?action=manageBranch");

}
else if (action.equalsIgnoreCase("editBranch")) {
String userid=request.getParameter("uid");
BranchBean editUser=new BranchBean();
editUser=new Controller().getUserById(userid);

List<PermissionBean>plist=new Controller().getPermissionByUserId(uid);
request.setAttribute("plist",plist);

request.setAttribute("editUser", editUser);
request.getRequestDispatcher("addBranch.jsp").forward(request, response);
}
else {
response.sendRedirect("login.jsp");
}


}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
String name=request.getParameter("name");
String address=request.getParameter("address");
String contact=request.getParameter("contact");
String username=request.getParameter("username");
String password=request.getParameter("password");
String permission_txt=request.getParameter("permission_txt");
String uid=request.getParameter("uid");

BranchBean ubean=new BranchBean();
ubean.setName(name.toUpperCase());
ubean.setAddress(address);
ubean.setContact(contact);
ubean.setUname(username);
ubean.setPassword(password);
ubean.setRole(permission_txt);

Controller control=new Controller();

if(uid.isEmpty()){
control.addUser(ubean);
}
else {
ubean.setUid(uid);
control.updateUser(ubean);
}
response.sendRedirect("BranchServlet?action=manageBranch");
}
}

