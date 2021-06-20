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

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	private static  Logger logger=Logger.getLogger(FirstServlet.class);
	
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
		//read form1/req1  data
		String pname=req.getParameter("pname");
		String pfname=req.getParameter("pfname");
		String paddrs=req.getParameter("paddrs");
		String ms=req.getParameter("ms");
		logger.debug("rew parmaeter are gathered");
		if(ms==null)
			ms="single";
		//Generate form2 dynamically based on the marital status
		if(ms.equalsIgnoreCase("married")) {
		      pw.println("<h1 style='color:red;text-align:center'>Submit Married life Details");
		      pw.println("<form action='secondurl' method='Post'>");
		       pw.println("<table  bgcolor='pink' align='center'> ");
		       pw.println("<tr><td> spouse name::</td><td> <input type='text' name='f2t1'>  </td> </tr>");
		       pw.println("<tr><td>No.of kids:: </td><td> <input type='text' name='f2t2'>  </td> </tr>");
		       pw.println("<tr><td colspan='2'><input type='submit' value='submit'></td></tr>");
		       pw.println("<tr><td'><input type='hidden' name='hname' value='"+pname+"' ></td><td><input type='hidden' name='hfname' value='"+pfname+"''></td></tr>");
		       pw.println("<tr><td'><input type='hidden' name='haddrs' value='"+paddrs+"' ></td><td'><input type='hidden' name='hms' value='"+ms+"''></td></tr>");
		       pw.println("</table>");
		      pw.println("</from>");
		}
		else {
			  pw.println("<h1 style='color:red;text-align:center'>submit  Bachelor life Details");
		      pw.println("<form action='secondurl' method='Post'>");
		       pw.println("<table  bgcolor='grey' align='center'> ");
		       pw.println("<tr><td> When do want to marry?::</td><td> <input type='text' name='f2t1'>  </td> </tr>");
		       pw.println("<tr><td>Why do want to marry? </td><td> <input type='text' name='f2t2'>  </td> </tr>");
		       pw.println("<tr><td colspan='2'><input type='submit' value='submit'></td></tr>");
		       pw.println("<tr><td'><input type='hidden' name='hname' value='"+pname+"' ></td><td><input type='hidden' name='hfname' value='"+pfname+"''></td></tr>");
		       pw.println("<tr><td'><input type='hidden' name='haddrs' value='"+paddrs+"' ></td><td><input type='hidden' name='hms' value='"+ms+"''></td></tr>");
		       pw.println("</table>");
		      pw.println("</from>");
		}
		logger.info("Dynamic form is generted");
		//close stream
		pw.close();
		logger.debug("PrintWrier stream is closed");
		logger.debug("End of doGet(-,-) method");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.debug("start of doPost(-,-) method");
		doGet(req, res);
		logger.debug("End of doPost(-,-) method");
	}

}
