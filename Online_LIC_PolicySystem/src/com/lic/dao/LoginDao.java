package com.lic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lic.bean.LoginBean;
import com.lic.bean.PermissionBean;



public class LoginDao {

public LoginBean getUserByLoginDetails(String username, String password,
Connection con) {
// TODO Auto-generated method stub
LoginBean log=new LoginBean();
try{
try(PreparedStatement pst=con.prepareStatement("SELECT * FROM `tbl_user_master` WHERE `user_name`=? and `password`=? and `status`=?"))
{
pst.setString(1,username);
pst.setString(2, password);
pst.setInt(3,1);

try(ResultSet rs=pst.executeQuery())
{
if(rs.next())
{
log.setUid(rs.getString("user_id"));
log.setName(rs.getString("name"));
log.setAddress(rs.getString("address"));
log.setContact_no(rs.getString("contact_no"));
log.setRole(rs.getString("role"));

}

}
try(PreparedStatement pst1=con.prepareStatement("select company from tbl_company_master"))
{
try(ResultSet rs1=pst1.executeQuery())
{
if(rs1.next())
{
log.setCompany(rs1.getString(1));
}
}

}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}

return log;
}

public List<PermissionBean> getPermissionByRole(String role, Connection con) {
// TODO Auto-generated method stub
List<PermissionBean>plist=new ArrayList<>();
try {
if(role!=null){


try(PreparedStatement pst=con.prepareStatement("SELECT * FROM `tbl_permission_master` where  status=1 and permission_id in ("+role+")"))
{
try(ResultSet rs=pst.executeQuery())
{
while (rs.next()) {
PermissionBean pb=new PermissionBean();
pb.setRid(rs.getString("permission_id"));
pb.setPermission(rs.getString("permission"));
pb.setUrl(rs.getString("url"));
pb.setType(rs.getInt("type"));
plist.add(pb);
}
}
}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}

return plist;
}

}

