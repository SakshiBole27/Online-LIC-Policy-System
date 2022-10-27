package com.lic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.lic.bean.CustomerBean;
import com.lic.util.Constant;
import com.lic.util.Util;
public class CustomerDao {

public List<CustomerBean> getCustomerList(int status, Connection con) {
// TODO Auto-generated method stub
List<CustomerBean>clist=new ArrayList<>();
try (PreparedStatement p=con.prepareStatement("SELECT * FROM tbl_customer_master where status=?  order by name"))
{
p.setInt(1,status);
try(ResultSet rs=p.executeQuery())
{
while (rs.next()) {
CustomerBean cb=new CustomerBean();
cb.setCustomerid(rs.getString("customerid"));
cb.setName(rs.getString("name"));
cb.setBirthdate(new Util().convertClientDate(rs.getString("birthdate")));
cb.setAddress(rs.getString("address"));
cb.setMobile(rs.getString("mobile"));
cb.setGender(rs.getString("gender"));
cb.setIncome(rs.getString("income"));
cb.setAge(rs.getString("age"));
cb.setHeight(rs.getString("height"));
cb.setWeight(rs.getString("weight"));
cb.setQualification(rs.getString("qualification"));
cb.setNationality(rs.getString("nationality"));
cb.setUsername(rs.getString("username"));
cb.setPassword(rs.getString("password"));
cb.setStatus(rs.getString("status"));
cb.setUserid(rs.getString("userid"));
cb.setDate(new Util().convertClientDate(rs.getString("date")));
clist.add(cb);
}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return clist;
}


public CustomerBean getCustomerById(String string, Connection con) {
// TODO Auto-generated method stub
CustomerBean cb=new CustomerBean();

try (PreparedStatement p=con.prepareStatement("SELECT * FROM `tbl_customer_master` where `customerid`=?")){
p.setString(1,string);
try(ResultSet rs=p.executeQuery())
{
while (rs.next()) {
cb.setCustomerid(rs.getString("customerid"));
cb.setName(rs.getString("name"));
cb.setBirthdate(new Util().convertClientDate(rs.getString("birthdate")));
cb.setAddress(rs.getString("address"));
cb.setMobile(rs.getString("mobile"));
cb.setGender(rs.getString("gender"));
cb.setIncome(rs.getString("income"));
cb.setAge(rs.getString("age"));
cb.setHeight(rs.getString("height"));
cb.setWeight(rs.getString("weight"));
cb.setQualification(rs.getString("qualification"));
cb.setNationality(rs.getString("nationality"));
cb.setUsername(rs.getString("username"));
cb.setPassword(rs.getString("password"));
cb.setStatus(rs.getString("status"));
cb.setUserid(rs.getString("userid"));
cb.setDate(new Util().convertClientDate(rs.getString("date")));
}
}
}
catch (Exception e) {
// TODO: handle exception

e.printStackTrace();
}

return cb;
}

public void saveCustomer(CustomerBean c, Connection con) {
// TODO Auto-generated method stub
try(PreparedStatement pst=con.prepareStatement("INSERT INTO `tbl_customer_master` (customerid,name,birthdate,address,mobile,gender,income,age,height,weight,qualification,nationality,username,password,status,userid,date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"))
{
pst.setInt(1,0);
pst.setString(2,Constant.upperCaseWords(c.getName()));
pst.setString(3,new Util().convertDatabaseDate(c.getBirthdate()));
pst.setString(4,Constant.upperCaseWords(c.getAddress()));
pst.setString(5,c.getMobile());
pst.setString(6,c.getGender());
pst.setString(7,c.getIncome());
pst.setString(8,c.getAge());
pst.setString(9,c.getHeight());
pst.setString(10,c.getWeight());
pst.setString(11,c.getQualification());
pst.setString(12,c.getNationality());
pst.setString(13,c.getUsername());
pst.setString(14,c.getPassword());
pst.setInt(15, 1);
pst.setString(16, c.getUserid());
pst.setString(17,new Util().convertDatabaseDate(new Util().todayDate()));
pst.executeUpdate();
}catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void updateCustomer(CustomerBean c, Connection con) {
// TODO Auto-generated method stub
try(PreparedStatement pst=con.prepareStatement("UPDATE `tbl_customer_master` SET name=?,birthdate=?,address=?,mobile=?,gender=?,income=?,age=?,height=?,weight=?,qualification=?,nationality=?,username=?,password=?,status=?,userid=?,date=? WHERE customerid=?"))
{
pst.setString(1,Constant.upperCaseWords(c.getName()));
pst.setString(2,new Util().convertDatabaseDate(c.getBirthdate()));
pst.setString(3,Constant.upperCaseWords(c.getAddress()));
pst.setString(4,c.getMobile());
pst.setString(5,c.getGender());
pst.setString(6,c.getIncome());
pst.setString(7,c.getAge());
pst.setString(8,c.getHeight());
pst.setString(9,c.getWeight());
pst.setString(10,c.getQualification());
pst.setString(11,c.getNationality());
pst.setString(12,c.getUsername());
pst.setString(13,c.getPassword());
pst.setInt(14, 1);
pst.setString(15, c.getUserid());
pst.setString(16,new Util().convertDatabaseDate(new Util().todayDate()));
pst.setString(17, c.getCustomerid());
pst.executeUpdate();
} catch (Exception e) {
e.printStackTrace();
}
}

public void deleteCustomer(String sid, String status, Connection con) {
// TODO Auto-generated method stub
int sta=0;
if (status.equalsIgnoreCase("0")) {
sta=1;
}

try (PreparedStatement p=con.prepareStatement("UPDATE `tbl_customer_master` SET `status`=? WHERE `customerid`=?")){
p.setInt(1,sta);
p.setString(2,sid);
p.executeUpdate();
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}
}
