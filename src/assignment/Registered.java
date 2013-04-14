package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Servlet implementation class Registered
 */
@WebServlet("/Registered")
public class Registered extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string =
			//"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111f.cs.columbia.edu:1521/ADB";
			"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
			private Connection conn;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registered() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String apartment="";
	 	String street="";
	 	String city="";
	 	String state="";
	 	String postal_code="";
	 	PrintWriter pw = response.getWriter();
	 	
	
		Enumeration<String> paramNames = request.getParameterNames();
		
        while(paramNames.hasMoreElements()) {
        	String paramName = paramNames.nextElement();
        	if(paramName.equals("apartment")){
        		apartment=request.getParameterValues(paramName)[0];
        	}else if(paramName.equals("street")){
        		street=request.getParameterValues(paramName)[0];
        	}else if(paramName.equals("city")){
        		city=request.getParameterValues(paramName)[0];
        	}else if(paramName.equals("state")){
        		state=request.getParameterValues(paramName)[0];
        	}else if(paramName.equals("postal_code")){
        		postal_code=request.getParameterValues(paramName)[0];
        	}
        }
        
    	try {
			if (conn == null) {
	// Create a OracleDataSource instance and set URL
		OracleDataSource ods = new OracleDataSource();
		ods.setURL(connect_string);
		conn = ods.getConnection();
			}
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select max(adid) as max from address");
			int maxADID;
			if(rset.next()){
				maxADID = rset.getInt("max");
			}else{
				maxADID = 0;
			}
			maxADID++;
			
			stmt.executeQuery("insert into address(adid,apartment, state, city, street,postal_code,cid) values("+"'"+maxADID+"','"+apartment+"','"+state+"','"+city+"','"+street+"','"+postal_code+"','"+login.cid+"')");
			stmt.executeQuery("update customer_at set adid="+maxADID+" where cid='"+login.cid+"'"); 
		
			conn.close();
		
		//ResultSet rest2 = stmt.executeQuery("Select s.cid as cid, s.quantity as num, ")
		
		//ResultSet rset2 = stmt.executeQuery("select ")
		
	} catch (SQLException e) {
		pw.println("It cannot happen!");
	}
		
		/*String fullname="";
		 	String username="";
		 	String passcode="";
		 	String phonenumber="";
		 	String email="";
		 	PrintWriter pw = response.getWriter();
		 	
		
			Enumeration<String> paramNames = request.getParameterNames();
			
	        while(paramNames.hasMoreElements()) {
	        	String paramName = paramNames.nextElement();
	        	if(paramName.equals("fullname")){
	        		fullname=request.getParameterValues(paramName)[0];
	        	}else if(paramName.equals("username")){
	        		username=request.getParameterValues(paramName)[0];
	        	}else if(paramName.equals("passcode")){
	        		passcode=request.getParameterValues(paramName)[0];
	        	}else if(paramName.equals("phonenumber")){
	        		phonenumber=request.getParameterValues(paramName)[0];
	        	}else if(paramName.equals("email")){
	        		email=request.getParameterValues(paramName)[0];
	        	}
	        }
	        login.cid = username;
	        
	        	try {
	    			if (conn == null) {
	    	// Create a OracleDataSource instance and set URL
	    		OracleDataSource ods = new OracleDataSource();
	    		ods.setURL(connect_string);
	    		conn = ods.getConnection();
	    			}
	    			Statement stmt = conn.createStatement();
	    			ResultSet rset = stmt.executeQuery("insert into customer_at(cid, cname, tel, email,password) values("+"'"+username+"','"+fullname+"','"+phonenumber+"','"+email+"','"+passcode+"')");
	    			
	    			conn.close();
			    } catch (SQLException e) {
					pw.println(e.getMessage());
				}*/
	
				pw.println("<html>");
				pw.println("<head><title>Registered</title></head>");
				pw.println("<H1>Successful registered<BR></H1>");
				pw.println("<hr />");
			

				pw.println("<body><BR>");
				pw.println("<FORM ACTION=match METHOD=POST >");
		
				pw.println("<INPUT TYPE=SUBMIT VALUE=Continue>");
				pw.println("</FORM>");
				
	}
}