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
 * Servlet implementation class address
 */
@WebServlet("/address")
public class address extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string = 
			//"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111f.cs.columbia.edu:1521/ADB";
	"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public address() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String fullname="";
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
    			
    			//conn.close();
		    } catch (SQLException e) {
				pw.println(e.getMessage());
			}
		/*Enumeration<String> paramNames = request.getParameterNames();
		
		
		
        while(paramNames.hasMoreElements()) {
        	String paramName = paramNames.nextElement();
        	String mid = paramName.substring(0,paramName.indexOf(','));
        	String type = paramName.substring(paramName.indexOf(',')+1);
        	if(type.equals("vipNum")){
        		type = "vip";
        	}else{
        		type = "regular";
        	}
        	int num = Integer.parseInt(request.getParameterValues(paramName)[0]);
        	int maxTTID=0;
        	try {
    			if (conn == null) {
		    	// Create a OracleDataSource instance and set URL
		    		OracleDataSource ods = new OracleDataSource();
		    		ods.setURL(connect_string);
		    		conn = ods.getConnection();
    			}
    			Statement stmt = conn.createStatement();
    			ResultSet rset = stmt.executeQuery("select max(ttid) as max from ticket");
    			
    			if(rset.next()){
    				maxTTID = rset.getInt("max");
    			}else{
    				maxTTID = 0;
    			}
    			maxTTID++;
    			//conn.close();
        	}
    		catch (SQLException e) {
    			maxTTID++;
    		}
        	
        	try {
    			if (conn == null) {
		    	// Create a OracleDataSource instance and set URL
		    		OracleDataSource ods = new OracleDataSource();
		    		ods.setURL(connect_string);
		    		conn = ods.getConnection();
    			}
    			Statement stmt = conn.createStatement();
    			ResultSet rset=stmt.executeQuery("insert into ticket (ttid) values("+"'"+maxTTID+"')");
    			ResultSet rset2=stmt.executeQuery("insert into selec (cid, ttid,quantity,type) values("+"'"+login.cid+"','"+maxTTID+"','"+num+"','"+type+"')");
    			
    			ResultSet rset1=stmt.executeQuery("insert into choose(mid, cid) values("+"'"+mid+"','"+login.cid+"')"); 
    			
    			//conn.close();
        	}
    		catch (SQLException e) {
    			System.out.print("You already sold it.");
    		}
        	/*
        	try {
    			if (conn == null) {
		    	// Create a OracleDataSource instance and set URL
		    		OracleDataSource ods = new OracleDataSource();
		    		ods.setURL(connect_string);
		    		conn = ods.getConnection();
    			}
    			Statement stmt = conn.createStatement();
    			ResultSet rset=stmt.executeQuery("insert into selec (cid, ttid,quantity,type) values("+"'"+login.cid+"','"+maxTTID+"','"+num+"',,'"+type+"')");
    			
    			//ResultSet rset1=stmt.executeQuery("insert into choose(mid, cid) values("+"'"+mid+"','"+login.cid+"')"); 
    			//ResultSet rset2=stmt.executeQuery("insert into address(cid) values("+"'"+login.cid+"')"); 
        	}
    		catch (SQLException e) {
    			System.out.print("");
    		}
        	try {
    			if (conn == null) {
		    	// Create a OracleDataSource instance and set URL
		    		OracleDataSource ods = new OracleDataSource();
		    		ods.setURL(connect_string);
		    		conn = ods.getConnection();
    			}
    			Statement stmt = conn.createStatement();
    			//ResultSet rset=stmt.executeQuery("insert into selec(cid, ttid,quantity) values("+"'"+login.cid+"','"+ttid+"','"+num+"')");
    			
    			ResultSet rset1=stmt.executeQuery("insert into choose (mid, cid) values("+"'"+mid+"','"+login.cid+"')"); 
    			//ResultSet rset2=stmt.executeQuery("insert into address(cid) values("+"'"+login.cid+"')"); 
        	}
    		catch (SQLException e) {
    			System.out.print("");
    		}	*/
        
		//PrintWriter pw = new PrintWriter(response.getOutputStream());
		try {
			if (conn == null) {
	// Create a OracleDataSource instance and set URL
		OracleDataSource ods = new OracleDataSource();
		ods.setURL(connect_string);
		conn = ods.getConnection();
			}
			Statement stmt = conn.createStatement();
			
			
			//ResultSet rset = stmt.executeQuery("select mid,mname,mdate from match_hold");
			 
			pw.println("<html>");
			pw.println("<head><title>Address</title></head>");
			pw.println("<H1>Ticket Selling Website <BR></H1>");
			pw.println("<hr />");
			pw.println("<H2>Please give us your address<BR></H2>");

			pw.println("<body><BR>");
			pw.println("<FORM ACTION=Registered METHOD=POST >");
			pw.println("<p>Apartment: <input type=text name=apartment size=40 maxlength=512></p>");
			pw.println("<p>Street: <input type=text name=street size=40 maxlength=512></p>");
			pw.println("<p>City: <input type=text name=city size=40 maxlength=512></p>");
			pw.println("<p>State: <input type=text name=state size=40 maxlength=512></p>");
			pw.println("<p>Postal_code: <input type=text name=postal_code size=40 maxlength=512></p>");
			pw.println("<INPUT TYPE=SUBMIT VALUE=Next>");
			pw.println("</FORM>");
			
			pw.println("</body></html>");
			conn.close();
			} catch (SQLException e) {
				pw.println(e.getMessage());
			}
				pw.close();
        	
	}

}
