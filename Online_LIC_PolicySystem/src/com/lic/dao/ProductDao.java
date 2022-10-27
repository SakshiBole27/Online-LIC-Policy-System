package com.lic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lic.bean.PolicyBean;
import com.lic.bean.SubPolicyBean;
import com.lic.util.Constant;
import com.lic.util.Util;


public class ProductDao {

public List<SubPolicyBean> getAllProductList(Connection con, int status) {
// TODO Auto-generated method stub
List<SubPolicyBean>plist=new ArrayList<>();
try (PreparedStatement pst=con.prepareStatement("SELECT pm.*,tbl_catagory_master.catagory FROM tbl_catagory_master,`tbl_product_master` pm where pm.status=? and tbl_catagory_master.catagory_id=pm.catagory_id order by catagory")){
pst.setInt(1,status);
try(ResultSet rs=pst.executeQuery())
{
while (rs.next()) {
SubPolicyBean pb=new SubPolicyBean();
pb.setProduct_id(rs.getInt("product_id"));
pb.setProduct_name(rs.getString("product_name"));
pb.setDate(new Util().convertClientDate(rs.getString("date")));
pb.setStatus(rs.getString("status"));
pb.setUser_id(rs.getString("uid"));
pb.setCatagory(rs.getString("catagory"));
pb.setPolicy_desc(rs.getString("policy_desc"));
pb.setPolicy_no(rs.getString("policy_no"));
plist.add(pb);
}
}

} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}

return plist;
}

public void deleteProduct(String pid, String status, Connection con) {
// TODO Auto-generated method stub

int sta=0;
if (status.equalsIgnoreCase("0")) {
sta=1;
}

try (PreparedStatement p=con.prepareStatement("UPDATE `tbl_product_master` SET `status`=? WHERE `product_id`=?")){
p.setInt(1,sta);
p.setString(2,pid);
p.executeUpdate();
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public void saveProduct(SubPolicyBean p, Connection con) {
// TODO Auto-generated method stub
int gid=0;
try (PreparedStatement pst=con.prepareStatement("INSERT INTO `tbl_product_master`(`product_id`, `product_name`, `date`, `status`, `uid`,"
+ " `policy_desc`, catagory_id, policy_no) VALUES (?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)){
PolicyBean cb=new PolicyDao().getCatagoryByName(con,new String(p.getCatagory_id().getBytes("ISO-8859-1"),"UTF8"));
int catid=cb.getCatgory_id();
pst.setInt(1,0);
pst.setString(2,Constant.upperCaseWords(p.getProduct_name()));
pst.setString(3,new Util().convertDatabaseDate(p.getDate()));
pst.setInt(4,1);
pst.setString(5,p.getUser_id());
pst.setString(6,p.getPolicy_desc());
pst.setInt(7, catid);
pst.setString(8,p.getPolicy_no());
pst.executeUpdate();
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public SubPolicyBean getProductById(String pid, Connection con)
{
SubPolicyBean pb=new SubPolicyBean();
try (PreparedStatement pst=con.prepareStatement("SELECT pm.*, cm.catagory_id, cm.catagory FROM `tbl_product_master` pm, tbl_catagory_master cm where pm.catagory_id=cm.catagory_id and pm.product_id=?")){
pst.setString(1,pid);
try(ResultSet rs=pst.executeQuery())
{
while (rs.next()) {
pb.setProduct_id(rs.getInt("product_id"));
pb.setProduct_name(rs.getString("product_name"));
pb.setDate(new Util().convertClientDate(rs.getString("date")));
pb.setStatus(rs.getString("status"));
pb.setUser_id(rs.getString("uid"));
pb.setPolicy_desc(rs.getString("policy_desc"));
pb.setCatagory_id(rs.getString("catagory_id"));
pb.setCatagory(rs.getString("catagory"));
pb.setPolicy_no(rs.getString("policy_no"));
}
}
}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return pb;
}

public void updateProduct(SubPolicyBean p, Connection con) {
// TODO Auto-generated method stub
try (PreparedStatement pst=con.prepareStatement("UPDATE `tbl_product_master` SET `product_name`=?,`date`=?,`status`=?,`uid`=?, policy_desc=?, policy_no=? WHERE `product_id`=?")){

pst.setString(1,Constant.upperCaseWords(p.getProduct_name()));
pst.setString(2,new Util().convertDatabaseDate(p.getDate()));
pst.setInt(3,1);
pst.setString(4,p.getUser_id());
pst.setString(5,p.getPolicy_desc());
pst.setString(6, p.getPolicy_no());
pst.setInt(7, p.getProduct_id());
pst.executeUpdate();

} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

/*	public SubPolicyBean getProductByName(String pname, Connection con)
{
SubPolicyBean pb=new SubPolicyBean();
try (PreparedStatement pst=con.prepareStatement("SELECT pm.*, cm.catagory_id, cm.catagory FROM `tbl_product_master` pm, tbl_catagory_master cm,tbl_unit_master where cm.catagory_id=pm.catagory_id pm.product_name=?")){
pst.setString(1,pname);
try(ResultSet rs=pst.executeQuery())
{
while (rs.next())
{
pb.setProduct_id(rs.getInt("product_id"));
pb.setProduct_name(rs.getString("product_name"));
pb.setDate(new Util().convertClientDate(rs.getString("date")));
pb.setStatus(rs.getString("status"));
pb.setUser_id(rs.getString("uid"));
pb.setSales_rate(rs.getString("sales_rate"));
pb.setCatagory(rs.getString("catagory"));
pb.setPolicy_no(rs.getString("policy_no"));
}

}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}

return pb;
}*/

public int getproductMaxId(Connection con) {
// TODO Auto-generated method stub
int maxid=0;
try (PreparedStatement pst=con.prepareStatement("SELECT max(`product_id`) as maxid FROM `tbl_product_master`")){
try(ResultSet rs=pst.executeQuery())
{
while (rs.next()) {
maxid=rs.getInt("maxid");
}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return maxid;
}

/*	public SubPolicyBean getProductByNameAndCat(String category,String size, String pid, Connection con)
{
SubPolicyBean pb=new SubPolicyBean();
try (PreparedStatement pst=con.prepareStatement("SELECT pm.* , cm.catagory_id, cm.catagory, cm.hsn,tbl_unit_master.* FROM `tbl_product_master` pm , tbl_catagory_master cm,tbl_unit_master where pm.product_name=? and cm.catagory=? and pm.size=? and pm.catagory_id=cm.catagory_id and tbl_unit_master.unit_id=pm.unit_id")){
pst.setString(1,pid);
pst.setString(2,category);
pst.setString(3,size);

try(ResultSet rs=pst.executeQuery())
{
while (rs.next())
{
pb.setProduct_id(rs.getInt("product_id"));
pb.setProduct_name(rs.getString("product_name"));
pb.setDate(new Util().convertClientDate(rs.getString("date")));
pb.setStatus(rs.getString("status"));
pb.setUser_id(rs.getString("uid"));
pb.setPurchase_rate(rs.getString("purchase_rate"));
pb.setSales_rate(rs.getString("sales_rate"));
pb.setMrp(rs.getString("mrp"));
pb.setCgst(rs.getString("cgst"));
pb.setSgst(rs.getString("sgst"));
pb.setIgst(rs.getString("igst"));
pb.setHsn(rs.getString("hsn"));
pb.setBarcode(rs.getString("barcode"));
pb.setUnit_id(rs.getInt("unit_id"));
pb.setUnit(rs.getString("unit"));
pb.setSize(rs.getString("size"));
pb.setCatagory(rs.getString("catagory"));
}

}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}

return pb;
}*/

}
