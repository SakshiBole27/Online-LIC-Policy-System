package com.lic.connection;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ManageConnection {

static Logger log=Logger.getLogger(Connection.class.getName());


public static Connection  getConnection() {
Connection con=null;

try{
Context initCtx=new InitialContext();
Context envCtx=(Context) initCtx.lookup("java:comp/env");

DataSource ds;
ds=(DataSource) envCtx.lookup("jdbc/Online_Lic");

return (Connection) ds.getConnection();

}
catch(Exception e)
{
e.printStackTrace();
}
return con;

}}
