package com.lic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lic.bean.PolicyBean;
import com.lic.util.Constant;
import com.lic.util.Util;


public class PolicyDao
{
public List<PolicyBean> getCatagoryList(int status,Connection con) {
// TODO Auto-generated method stub
List<PolicyBean>cList=new ArrayList<>();
try (PreparedStatement pst=con.prepareStatement("SELECT * FROM `tbl_catagory_master` WHERE `status`=? order by catagory")){
pst.setInt(1,status);
try (ResultSet rs=pst.executeQuery()){
while (rs.next()) {
PolicyBean cb=new PolicyBean();
cb.setCatgory_id(rs.getInt("catagory_id"));
cb.setCatagory(Constant.upperCaseWords(rs.getString("catagory")));
cb.setUid(rs.getString("uid"));
cb.setStatus(rs.getString("status"));
cList.add(cb);
}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return cList;
}

public void deleteCatagory(String cid, String status, Connection con)
{
int sta=0;
if (status.equalsIgnoreCase("0")) {
sta=1;
}
try (PreparedStatement pst=con.prepareStatement("update tbl_catagory_master set status=? where catagory_id=?")){
pst.setInt(1,sta);
pst.setString(2,cid);
pst.executeUpdate();
} catch (Exception e) {
e.printStackTrace();
// TODO: handle exception
}
}

public void saveCategory(PolicyBean cb, Connection con) {
// TODO Auto-generated method stub
try (PreparedStatement pst=con.prepareStatement("INSERT INTO `tbl_catagory_master`(`catagory_id`, `catagory`, `status`, `uid`, `date`) VALUES (?,?,?,?,?)")){
pst.setInt(1,0);
pst.setString(2,Constant.upperCaseWords(cb.getCatagory()));
pst.setString(3,"1");
pst.setString(4,cb.getUid());
pst.setString(5,new Util().convertDatabaseDate(cb.getDate()));
pst.executeUpdate();
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void updateCategory(PolicyBean cb, Connection con) {
// TODO Auto-generated method stub
try (PreparedStatement pst=con.prepareStatement("update  `tbl_catagory_master` set `catagory`=?, `date`=? where catagory_id=? ")){
pst.setString(1,Constant.upperCaseWords(cb.getCatagory()));
pst.setString(2,new Util().convertDatabaseDate(cb.getDate()));
pst.setInt(3,cb.getCatgory_id());
pst.executeUpdate();
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public PolicyBean getCatagoryByName(Connection con, String cid) {
// TODO Auto-generated method stub
PolicyBean cb=new PolicyBean();
try (PreparedStatement pst=con.prepareStatement("SELECT * FROM `tbl_catagory_master` WHERE `status`=? and catagory=?")){
pst.setInt(1,1);
pst.setString(2,cid);
try (ResultSet rs=pst.executeQuery()){
while (rs.next()) {

cb.setCatgory_id(rs.getInt("catagory_id"));
cb.setCatagory(rs.getString("catagory"));
cb.setUid(rs.getString("uid"));
cb.setStatus(rs.getString("status"));
cb.setDate(new Util().convertClientDate(rs.getString("date")));
}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return cb;
}

public PolicyBean getCatagoryListById(Connection con, String cid) {
// TODO Auto-generated method stub
PolicyBean cb=new PolicyBean();
try (PreparedStatement pst=con.prepareStatement("SELECT * FROM `tbl_catagory_master` WHERE `status`=? and catagory_id=?")){
pst.setInt(1,1);
pst.setString(2,cid);
try (ResultSet rs=pst.executeQuery()){
while (rs.next()) {

cb.setCatgory_id(rs.getInt("catagory_id"));
cb.setCatagory(rs.getString("catagory"));
cb.setUid(rs.getString("uid"));
cb.setStatus(rs.getString("status"));
cb.setDate(new Util().convertClientDate(rs.getString("date")));
}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return cb;
}
}
