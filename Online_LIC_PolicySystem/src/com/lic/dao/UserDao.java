package com.lic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lic.bean.PermissionBean;
import com.lic.bean.BranchBean;


public class UserDao {

public List<BranchBean> getUserList(Connection con) {
// TODO Auto-generated method stub
List<BranchBean>ulist=new ArrayList<>();
try {
try(PreparedStatement pst=con.prepareStatement("SELECT * FROM `tbl_user_master`"))
{

try(ResultSet rs=pst.executeQuery())
{
while (rs.next()) {
BranchBean ub=new BranchBean();

ub.setUid(rs.getString("user_id"));
ub.setName(rs.getString("name"));
ub.setAddress(rs.getString("address"));
ub.setContact(rs.getString("contact_no"));
ub.setRole(rs.getString("role"));
ub.setStatus(rs.getString("status"));
ulist.add(ub);
}
}
}

return ulist;
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return ulist;
}

public List<PermissionBean> getPermissionByUserId(String uid, Connection con) {
// TODO Auto-generated method stub
String role=null;
try(PreparedStatement pst =con.prepareStatement("SELECT * FROM `tbl_user_master` WHERE `user_id`=?")){
pst.setString(1,uid);
try(ResultSet rs=pst.executeQuery())
{
if(rs.next())
{
role=rs.getString("role");
}
}
}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}



List<PermissionBean>list=new ArrayList<>();

try (PreparedStatement pst=con.prepareStatement("SELECT p.permission_id, p.permission, p.url FROM tbl_permission_master p WHERE  p.status=? and p.`permission_id` in("+role+") ")){
pst.setInt(1,1);



try (ResultSet rs=pst.executeQuery()){
while (rs.next()) {
PermissionBean pb=new PermissionBean();

pb.setRid(rs.getString("permission_id"));
pb.setPermission(rs.getString("permission"));
pb.setUrl(rs.getString("url"));
list.add(pb);
}
}

} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return list;
}

public void addUser(BranchBean ubean, Connection con) {
// TODO Auto-generated method stub
try (PreparedStatement pst=con.prepareStatement("INSERT INTO `tbl_user_master`(`user_id`, `name`, `address`, `contact_no`, `user_name`, `password`, `status`, `role`) VALUES (?,?,?,?,?,?,?,?)")){
pst.setInt(1,0);
pst.setString(2,ubean.getName());
pst.setString(3,ubean.getAddress());
pst.setString(4,ubean.getContact());
pst.setString(5,ubean.getUname());
pst.setString(6,ubean.getPassword());
pst.setString(7,"1");
pst.setString(8,ubean.getRole());
pst.executeUpdate();
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void deleteUser(String userid, String status, Connection con) {
// TODO Auto-generated method stub
int sta=0;
if (status.equalsIgnoreCase("0")) {
sta=1;
}

try (PreparedStatement p=con.prepareStatement("UPDATE `tbl_user_master` SET `status`=? WHERE `user_id`=?")){
p.setInt(1,sta);
p.setString(2,userid);
p.executeUpdate();
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public BranchBean getUserById(String userid, Connection con) {
// TODO Auto-generated method stub
BranchBean ub=new BranchBean();

try (PreparedStatement pst=con.prepareStatement("select * from tbl_user_master where user_id=?")){
pst.setString(1,userid);
try(ResultSet rs=pst.executeQuery())
{
while (rs.next()) {
ub.setUid(rs.getString("user_id"));
ub.setName(rs.getString("name"));
ub.setAddress(rs.getString("address"));
ub.setContact(rs.getString("contact_no"));
ub.setRole(rs.getString("role"));
ub.setStatus(rs.getString("status"));
ub.setUname(rs.getString("user_name"));
ub.setPassword(rs.getString("password"));
}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return ub;

}

public void updateUser(BranchBean ubean, Connection con) {
// TODO Auto-generated method stub
try (PreparedStatement pst=con.prepareStatement("UPDATE `tbl_user_master` SET `name`=?,`address`=?,`contact_no`=?,`user_name`=?,`password`=?,`status`=?,`role`=? WHERE `user_id`=?")){

pst.setString(1,ubean.getName());
pst.setString(2,ubean.getAddress());
pst.setString(3,ubean.getContact());
pst.setString(4,ubean.getUname());
pst.setString(5,ubean.getPassword());
pst.setString(6,"1");
pst.setString(7,ubean.getRole());
pst.setString(8,ubean.getUid());
pst.executeUpdate();
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}

}

}

