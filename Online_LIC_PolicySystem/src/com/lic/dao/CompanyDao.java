package com.lic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lic.bean.CompanyBean;

public class CompanyDao {

public CompanyBean getCompanyInformation(Connection con) {
// TODO Auto-generated method stub
CompanyBean cb=new CompanyBean();
try (PreparedStatement pst=con.prepareStatement("SELECT cm.*, sm.state, sm.state_tin FROM `tbl_company_master` cm, tbl_statecode_master sm where cm.stateid=sm.sid")){
try(ResultSet rs=pst.executeQuery())
{
while (rs.next()) {
cb.setCom_id(rs.getString("com_id"));
cb.setCompany(rs.getString("company"));
cb.setContact_person(rs.getString("contact_person"));
cb.setDesignation(rs.getString("designation"));
cb.setContact_no(rs.getString("contact_no"));
cb.setGstin_uid(rs.getString("gstin_uid"));
cb.setStateid(rs.getString("stateid"));
cb.setShipping_address(rs.getString("shipping_address"));
cb.setStatecode(rs.getString("state_tin"));
cb.setState(rs.getString("state"));
cb.setBank(rs.getString("bankname"));
cb.setBranch(rs.getString("branch"));
cb.setAccholder(rs.getString("accountholder"));
cb.setAccountno(rs.getString("accountno"));
cb.setIfsc(rs.getString("ifsc"));
}
}
} catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return cb;
}

}


