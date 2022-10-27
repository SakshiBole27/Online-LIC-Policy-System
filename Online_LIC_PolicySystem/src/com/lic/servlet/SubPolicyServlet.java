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
import com.lic.bean.SubPolicyBean;
import com.lic.controller.Controller;

/**
* Servlet implementation class ProductServlet
*/
@WebServlet("/SubPolicyServlet")
public class SubPolicyServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
HttpSession sec=request.getSession();
String uid=(String) sec.getAttribute("user_id");
String action=request.getParameter("action");

if(uid==null)
{

response.sendRedirect("login.jsp");
}
else if (action.equalsIgnoreCase("manageSubPolicy")) {
List<SubPolicyBean>plist=new Controller().getAllProductList(1);
request.setAttribute("plist",plist);

List<SubPolicyBean>plist1=new Controller().getAllProductList(0);
request.setAttribute("plist1",plist1);
request.getRequestDispatcher("listSubPolicy.jsp").forward(request, response);

}

else if (action.equalsIgnoreCase("manageFrontPolicy"))
{
List<SubPolicyBean>plist=new Controller().getAllProductList(1);
request.setAttribute("plist",plist);
request.getRequestDispatcher("frontPolicies.jsp").forward(request, response);
}

else if (action.equalsIgnoreCase("manageSubPolicy")) {
List<SubPolicyBean>plist=new Controller().getAllProductList(1);
request.setAttribute("plist",plist);

}

else if (action.equalsIgnoreCase("addSubPolicy"))
{
List<PolicyBean>clist=new Controller().getCatagoryList(1);
request.setAttribute("clist",clist);
request.getRequestDispatcher("addSubPolicy.jsp").forward(request, response);
}

else if (action.equalsIgnoreCase("deleteSubPolicy")) {
String pid=request.getParameter("pid");
String status=request.getParameter("status");

new Controller().deleteProduct(pid,status);

response.sendRedirect("SubPolicyServlet?action=manageSubPolicy");

}

else if (action.equalsIgnoreCase("editSubPolicy"))
{
String pid=request.getParameter("pid");
SubPolicyBean product=new Controller().getProductById(pid);

List<PolicyBean>clist=new Controller().getCatagoryList(1);
request.setAttribute("clist",clist);
request.setAttribute("product",product);
request.getRequestDispatcher("addSubPolicy.jsp").forward(request, response);

}

else if (action.equalsIgnoreCase("editFrontSubPolicy"))
{
String pid=request.getParameter("pid");
SubPolicyBean product=new Controller().getProductById(pid);

List<PolicyBean>clist=new Controller().getCatagoryList(1);
request.setAttribute("clist",clist);
request.setAttribute("product",product);
request.getRequestDispatcher("frontPolicyDetails.jsp").forward(request, response);

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
String pid=request.getParameter("pid");
String product_name=request.getParameter("name");
product_name = new String(product_name.getBytes("ISO-8859-1"),"UTF8");
String date=request.getParameter("date");
String policy_desc=request.getParameter("policy_desc");
String catid=request.getParameter("category");
String policy_no=request.getParameter("policy_no");

SubPolicyBean p=new SubPolicyBean();
p.setProduct_name(product_name);
p.setDate(date);
p.setPolicy_desc(policy_desc);
p.setUser_id(uid);
p.setCatagory_id(catid);
p.setPolicy_no(policy_no);
if (pid.isEmpty()) {
new Controller().saveProduct(p);
}
else {
p.setProduct_id(Integer.parseInt(pid));
new Controller().updateProduct(p);
}
response.sendRedirect("SubPolicyServlet?action=manageSubPolicy");
}}
