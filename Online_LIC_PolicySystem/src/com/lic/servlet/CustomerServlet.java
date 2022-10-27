package com.lic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lic.bean.CustomerBean;
import com.lic.controller.Controller;

/**
* Servlet implementation class CustomerServlet
*/
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub

HttpSession sec=request.getSession();
String user_id=(String) sec.getAttribute("user_id");

String action=request.getParameter("action");
if(user_id==null)
{
response.sendRedirect("login.jsp");
}

else if (action.equalsIgnoreCase("manageCustomer")) {
List<CustomerBean>clist1=new Controller().getCustomerList(1);
request.setAttribute("clist1",clist1);
List<CustomerBean>clist2=new Controller().getCustomerList(0);
request.setAttribute("clist2",clist2);
request.getRequestDispatcher("listCustomer.jsp").forward(request, response);
}
else if (action.equalsIgnoreCase("addCustomer")) {
request.getRequestDispatcher("customerSignUp.jsp").forward(request, response);
}

else if (action.equalsIgnoreCase("deleteCustomer")) {
String sid=request.getParameter("sid");
String status=request.getParameter("status");
new Controller().deleteCustomer(sid,status);
response.sendRedirect("CustomerServlet?action=manageCustomer");
}

else if (action.equalsIgnoreCase("editCustomer")) {
String sid=request.getParameter("sid");
CustomerBean customer=new Controller().getCustomerById(sid);
request.setAttribute("customer",customer);
request.getRequestDispatcher("customerSignUp.jsp").forward(request, response);
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
String customer_name=request.getParameter("name");
String birthdate=request.getParameter("birth_date");
String address=request.getParameter("address");
String mobile=request.getParameter("contact");
String gender=request.getParameter("gender");
String income=request.getParameter("income");
String age=request.getParameter("age");
String height=request.getParameter("height");
String weight=request.getParameter("weight");
String qualification=request.getParameter("qualification");
String nationality=request.getParameter("nationality");
String username=request.getParameter("username");
String password=request.getParameter("password");
String status=request.getParameter("status");
CustomerBean c=new CustomerBean();
c.setName(customer_name.toUpperCase());
c.setBirthdate(birthdate);
c.setAddress(address);
c.setMobile(mobile);
c.setGender(gender);
c.setIncome(income);
c.setAge(age);
c.setHeight(height);
c.setWeight(weight);
c.setQualification(qualification);
c.setNationality(nationality);
c.setUsername(username);
c.setPassword(password);
c.setStatus(status);
c.setUserid(uid);
if (cid.isEmpty()) {
new Controller().saveCustomer(c);
}
else {
c.setCustomerid(cid);
new Controller().updateCustomer(c);
}

response.sendRedirect("CustomerServlet?action=manageCustomer");


}

}

