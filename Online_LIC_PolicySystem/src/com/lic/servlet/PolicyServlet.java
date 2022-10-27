package com.lic.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lic.bean.PolicyBean;
import com.lic.controller.Controller;

/**
* Servlet implementation class CatagoryServlet
*/
@WebServlet("/PolicyServlet")
public class PolicyServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
HttpSession sec=request.getSession();
String uid=(String) sec.getAttribute("user_id");
String action=request.getParameter("action");
if(uid==null)
{
response.sendRedirect("login.jsp");
}

else if (action.equalsIgnoreCase("managePolicy"))
{
List<PolicyBean>clist=new Controller().getCatagoryList(1);
request.setAttribute("clist", clist);

List<PolicyBean>clist1=new Controller().getCatagoryList(0);
request.setAttribute("clist1", clist1);

request.getRequestDispatcher("listPolicy.jsp").forward(request, response);
}

else if (action.equalsIgnoreCase("deletePolicy"))
{
String status=request.getParameter("status");
String cid=request.getParameter("cid");
new Controller().deleteCatagory(cid,status);
response.sendRedirect("PolicyServlet?action=managePolicy");
}
else if (action.equalsIgnoreCase("addPolicy")) {
request.getRequestDispatcher("addPolicy.jsp").forward(request, response);
}

else if (action.equalsIgnoreCase("editPolicy"))
{
String yid=request.getParameter("cid");
PolicyBean cb=new Controller().getCatagoryListById(yid);
request.setAttribute("cb",cb);
request.getRequestDispatcher("addPolicy.jsp").forward(request, response);
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
HttpSession sec=request.getSession();
String uid=(String) sec.getAttribute("user_id");
String cid=request.getParameter("cid");
String name=request.getParameter("name");
name = new String(name.getBytes("ISO-8859-1"),"UTF8");
String date=request.getParameter("date");
String hsn=request.getParameter("hsn");

PolicyBean cb=new PolicyBean();

cb.setCatagory(name);
cb.setDate(date);
cb.setUid(uid);
if(cid.isEmpty())
{
new Controller().saveCategory(cb);
}
else {
cb.setCatgory_id(Integer.parseInt(cid));
new Controller().updateCategory(cb);
}
response.sendRedirect("PolicyServlet?action=managePolicy");
}

}
