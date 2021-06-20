package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
       	private static  Logger logger=Logger.getLogger(SecondServlet.class);
       	public void init() {
    		try {
    			//get ServletContext obj
    			ServletContext sc=getServletContext();
    			PropertyConfigurator.configure(sc.getRealPath("/")+"WEB-INF/classes/com/nt/commons/log4j.properties");
    			                                                 //E:/Tomcat9.x/webapps/HiddenFormFilesApp/WEB-INF/classes/com/nt/commons/log4j.properties
    			logger.debug("Log4j activated");
    		}
    		catch(Exception e){
    			logger.fatal("problem in log4j activation");
    		}
    	}
	public  void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.debug("start of doGet(-,-)");
		//get PrintWriter
		PrintWriter pw=res.getWriter();
		logger.debug("PrintWriter is accessed");
		//set response content type
		res.setContentType("text/html");
		logger.debug("MIME type is set");
		//read form1/req1  data from hidden boxes(Session tracking)
		String pname=req.getParameter("hname");
		String pfname=req.getParameter("hfname");
		String paddrs=req.getParameter("haddrs");
		String ms=req.getParameter("hms");
		logger.debug("req parmaeter are gathered");
		//read form2/req2 data
		String f2val1=req.getParameter("f2t1");
		String f2val2=req.getParameter("f2t2");
		logger.debug("req parmaeter are gathered (hidden boxes)");
		//generate dynamic web page displaying both form1/req1 and form2/req2 data
		//Display form1/req1 data 
		pw.println("<br><h1 style='color:red;text-align:center'>  Form1 / req1 data  "+pname+" ...."+pfname+"...."+paddrs+"....."+ms+"</h1>");
		pw.println("<br><h1 style='color:red;text-align:center'>  Form2 / req2 data  "+f2val1+" ...."+f2val2+"</h1>");
		logger.debug("dynamic web page is displayed");
		
		//add hyperlink
		pw.println("<a href='form.html'>home </a>");
		logger.debug("home hperlink is generated");
		//close stream
		pw.close();
		logger.debug("PrintWriter stream is closed");
		logger.debug("End of doGet(-,-) method");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.debug("start of doPost(-,-) method");
		doGet(req, res);
		logger.debug("End of doPost(-,-) method");
	}

}
