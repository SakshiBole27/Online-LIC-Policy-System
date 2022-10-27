package com.lic.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.lic.bean.BranchBean;
import com.lic.bean.CompanyBean;
import com.lic.bean.CustomerBean;
import com.lic.bean.LoginBean;
import com.lic.bean.PermissionBean;
import com.lic.bean.PolicyBean;
import com.lic.bean.SubPolicyBean;
import com.lic.connection.ManageConnection;
import com.lic.dao.CompanyDao;
import com.lic.dao.CustomerDao;
import com.lic.dao.LoginDao;
import com.lic.dao.PolicyDao;
import com.lic.dao.ProductDao;
import com.lic.dao.UserDao;



public class Controller {

public LoginBean getUserByLoginDetails(String username, String password) {
// TODO Auto-generated method stub
LoginBean log=new LoginBean();
try (Connection con=ManageConnection.getConnection()){
log=new LoginDao().getUserByLoginDetails(username,password,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return log;
}

public List<PermissionBean> getPermissionByRole(String role) {
// TODO Auto-generated method stub
List<PermissionBean>plist=null;
try (Connection con=ManageConnection.getConnection()){
plist=new LoginDao().getPermissionByRole(role,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return plist;
}

public List<BranchBean> getUserList() {
// TODO Auto-generated method stub
List<BranchBean>ulist=new ArrayList<>();
try (Connection con=ManageConnection.getConnection()){

ulist=new UserDao().getUserList(con);

return ulist;
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return ulist;
}

public List<PermissionBean> getPermissionByUserId(String uid) {
// TODO Auto-generated method stub
List<PermissionBean>list=new ArrayList<>();
try (Connection con=ManageConnection.getConnection()){

UserDao ud=new UserDao();
list=ud.getPermissionByUserId(uid,con);

} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}

return list;
}

public void addUser(BranchBean ubean) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
UserDao ud=new UserDao();
ud.addUser(ubean,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void deleteUser(String userid, String status) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
new UserDao().deleteUser(userid,status,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public BranchBean getUserById(String userid) {
// TODO Auto-generated method stub
BranchBean ub=new BranchBean();
try (Connection con=ManageConnection.getConnection()){
ub=	new UserDao().getUserById(userid,con );
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return ub;
}

public void updateUser(BranchBean ubean) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
UserDao ud=new UserDao();
ud.updateUser(ubean,con);

}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}
public List<SubPolicyBean> getAllProductList(int status) {
// TODO Auto-generated method stub
List<SubPolicyBean>plist=new ArrayList<>();
try (Connection con=ManageConnection.getConnection()){
plist=new ProductDao().getAllProductList(con,status);
/*plist=new ProductDao().generateBarcode(con,status);*/
}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return plist;
}

public PolicyBean getCatagoryByName(String catid)
{
PolicyBean cb=new PolicyBean();
try(Connection con=ManageConnection.getConnection())
{
cb=new PolicyDao().getCatagoryByName(con, catid);
}
catch(Exception ex)
{
ex.printStackTrace();
}
return cb;
}

public void deleteProduct(String pid, String status) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
new ProductDao().deleteProduct(pid,status,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void saveProduct(SubPolicyBean p) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
new ProductDao().saveProduct(p,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public SubPolicyBean getProductById(String pid) {
// TODO Auto-generated method stub
SubPolicyBean p=new SubPolicyBean();
try (Connection con=ManageConnection.getConnection()){
p=new ProductDao().getProductById(pid,con);
}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return p;
}

public void updateProduct(SubPolicyBean p) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
new ProductDao().updateProduct(p,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}


public List<PolicyBean> getCatagoryList(int st) {
// TODO Auto-generated method stub
List<PolicyBean>cList=new ArrayList<>();
try (Connection con=ManageConnection.getConnection()){
cList=new PolicyDao().getCatagoryList(st,con);
} catch (Exception e) {
// TODO: handle exception
}
return cList;
}

public void deleteCatagory(String cid, String status)
{
try (Connection con=ManageConnection.getConnection())
{
new PolicyDao().deleteCatagory(cid,status,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void saveCategory(PolicyBean cb) {
// TODO Auto-generated method stub
try(Connection con=ManageConnection.getConnection()){
new PolicyDao().saveCategory(cb,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void updateCategory(PolicyBean cb) {
// TODO Auto-generated method stub
try(Connection con=ManageConnection.getConnection()){
new PolicyDao().updateCategory(cb,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public PolicyBean getCatagoryListById(String cid) {
// TODO Auto-generated method stub
PolicyBean cList=new PolicyBean();
try (Connection con=ManageConnection.getConnection()){
cList=new PolicyDao().getCatagoryListById(con,cid);
} catch (Exception e) {
// TODO: handle exception
}
return cList;
}

public CompanyBean getCompanyInformation() {
// TODO Auto-generated method stub
CompanyBean cb=new CompanyBean();
try(Connection con=ManageConnection.getConnection()){
cb=new CompanyDao().getCompanyInformation(con);

}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return cb;
}
public List<CustomerBean> getCustomerList(int st) {
// TODO Auto-generated method stub
List<CustomerBean>clist=new ArrayList<>();
try (Connection con=ManageConnection.getConnection()){
clist=new CustomerDao().getCustomerList(st,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return clist;
}

public void saveCustomer(CustomerBean c) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
new CustomerDao().saveCustomer(c,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void updateCustomer(CustomerBean c) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
new CustomerDao().updateCustomer(c,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void deleteCustomer(String sid, String status) {
// TODO Auto-generated method stub
try (Connection con=ManageConnection.getConnection()){
new CustomerDao().deleteCustomer(sid,status,con);
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}


public CustomerBean getCustomerById(String cid) {
// TODO Auto-generated method stub
CustomerBean c=new CustomerBean();
try (Connection con=ManageConnection.getConnection()){
c=new CustomerDao().getCustomerById(cid, con);
}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return c;
}
}

