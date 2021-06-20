//SelectTest2.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  SelectTest2
{ 
	 private static Logger logger=LoggerFactory.getLogger(SelectTest2.class); 
	
    public static   void main(String args[]){
        logger.debug("SelectTest:: start of main(-) method");
    	 Connection con=null;
    	 Statement st=null;
    	 ResultSet rs=null;
    	  try {
		    //Load jdbc driver class
			       Class.forName("oracle.jdbc.driver.OracleDriver");
			       logger.debug("com.nt.jdbc.SelectTest:: JDBC driver driver class loaded");
             //establish the connection  (Road)
			      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					                                                                                "system", "manager");
			      logger.info("com.nt.jdbc.SelectTest:: Connection is established with DB s/w");
			//create   JDBC Statement  object	  (vechicle)
			       if(con!=null) {
			           st=con.createStatement();
			           logger.debug("com.nt.jdbc.SelectTest: JDBC Statement object is created");
			       }
			//Send and execute SQL SELECT Query in Db s/w  and  get JDBC ResultSet object
			       if(st!=null) {
			         rs= st.executeQuery("SELECT  * FROM STUDENT");
			         logger.debug("com.nt.jdbc.SelectTest: SQL query is send to Db s/w for execution and ResultSet obj is generated");
			       }

			       if(rs!=null) {
				 //process  the ResultSet object
				     while(rs.next()!=false){   // while(rs.next()==true)
						 // System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"  "+ rs.getString(3)+"   "+rs.getFloat(4));  
						  //System.out.println(rs.getInt("SNO")+"  "+rs.getString("SNAME")+"  "+ rs.getString("SADD")+"   "+rs.getFloat("AVG"));  
						  //System.out.println(rs.getString("SNO")+"  "+rs.getString("SNAME")+"  "+ rs.getString("SADD")+"   "+rs.getString("AVG"));  
						  System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+ rs.getString(3)+"   "+rs.getString(4));  
						  logger.warn("com.nt.jdbc.SelectTest:: The records are ResultSet obj are retrived using getStrting(-) for all cols ..change them accordingly");
					 }//while
				     logger.debug("com.nt.jdbc.SelectTest::ResultSet obj is processed");
			       }//if
    	  }//try
    	  catch(SQLException se) {
    		  se.printStackTrace();
    		  logger.error("com.nt.jdbc.SelectTest:: known DB Problem ::"+se.getMessage()+ " SQL error code"+se.getErrorCode());
    	  }
    	  catch(Exception e) {
    		  e.printStackTrace();
    		  logger.error("com.nt.jdbc.SelectTest:: unknown  Problem "+e.getMessage());
    	  }
    	  finally {
    		  logger.debug("com.nt.jdbc.SelectTest: Closing JDBC objs");
    		  //close jdbc objs
    		  try {
    		  if(rs!=null)
    			  rs.close();
    		  logger.debug("com.nt.jdbc.SelectTest: ResultSet obj is closed");
    		  }
    		  catch(SQLException se) {
    			  se.printStackTrace();
    			  logger.error("com.nt.jdbc.SelectTest: Problem in closing ResultSet obj "+se.getMessage());
    		  }
    		  try {
        		  if(st!=null)
        			  st.close();
        		  logger.debug("com.nt.jdbc.SelectTest: Statement  obj is closed");
        		  }
        		  catch(SQLException se) {
        			  se.printStackTrace();
        			  logger.error("com.nt.jdbc.SelectTest: Problem in closing Statement obj "+se.getMessage());
        		  }
    		  try {
        		  if(con!=null)
        			  con.close();
        		  logger.debug("com.nt.jdbc.SelectTest:  Connection  obj is closed");
        		  }
        		  catch(SQLException se) {
        			  se.printStackTrace();
        			  logger.error("com.nt.jdbc.SelectTest: Problem in closing Connection obj "+se.getMessage());
        		  }
    	  }//finally

    	  logger.debug("com.nt.jdbc.SelectTest: end of main(-) method");
	}//main
}///class
