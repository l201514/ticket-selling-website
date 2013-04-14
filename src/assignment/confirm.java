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
 * Servlet implementation class confirm
 */
@WebServlet("/confirm")
public class confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string = 
			//"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111f.cs.columbia.edu:1521/ADB";
			"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirm() {
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
		
Enumeration<String> paramNames = request.getParameterNames();
		
		
		
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
		
        }
		
        
        PrintWriter pw = new PrintWriter(response.getOutputStream());
        /*try { 
    		if (conn == null) {
    		// Create a OracleDataSource instance and set URL
    		OracleDataSource ods = new OracleDataSource();
    		ods.setURL(connect_string); 
    		conn = ods.getConnection();
    		}
    		Statement stmt = conn.createStatement();

    		ResultSet rset = stmt.executeQuery("select c.cname as cname , c.tel as tel,c.email as email from customer_at c where c.cid=4321");
    		response.setContentType("text/html");
    		pw.println("<html>");
    		pw.println("<head><title>Team name and IDs</title></head>");
    		pw.println("<H1>Show All Matches Information<BR></H1>");
    		pw.println("<body><BR>");
        
        
        }
        
        */
        
        
        /*try {
			if (conn == null) {
	    	// Create a OracleDataSource instance and set URL
	    		OracleDataSource ods = new OracleDataSource();
	    		ods.setURL(connect_string);
	    		conn = ods.getConnection();
			}
			Statement stmt = conn.createStatement();
        }
		
        	
    	 	
        	catch (SQLException e) {
    			System.out.print("You already sold it.");
    		}
		PrintWriter pw = response.getWriter();
		/*
		
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
        
        */
        //PrintWriter pw = response.getWriter();
        	
        
        
        
        
        
        try {
    			if (conn == null) {
    	// Create a OracleDataSource instance and set URL
    		OracleDataSource ods = new OracleDataSource();
    		ods.setURL(connect_string);
    		conn = ods.getConnection();
    			}
    			Statement stmt = conn.createStatement();
    			/*ResultSet rset = stmt.executeQuery("select max(adid) as max from address");
    			int maxADID;
    			if(rset.next()){
    				maxADID = rset.getInt("max");
    			}else{
    				maxADID = 0;
    			}
    			maxADID++;
    			*/
    			//stmt.executeQuery("insert into address(adid,apartment, state, city, street,postal_code,cid) values("+"'"+maxADID+"','"+apartment+"','"+state+"','"+city+"','"+street+"','"+postal_code+"','"+login.cid+"')");
    			//stmt.executeQuery("insert into customer_at(adid) values("+"'"+maxADID+"') where cid='"+login.cid+"'"); 
    			ResultSet rset1 = stmt.executeQuery("select c.cname as cname , c.tel as tel,c.email as email from customer_at c where c.cid=4321");
    			//ResultSet rset3 = stmt.executeQuery("select a.state as state,a.city as city,a.street as street,a.apartment as apartment ,a.postal_code as postal_code from address a where a.cid="+login.cid);
    			//ResultSet rset2 = stmt.executeQuery("select m.mid as mid, m.mdate as mdate, s.quantity as quantity, s.type as type from match_hold m, choose ch, selec s where m.mid=ch.mid and ch.cid='"+login.cid+"' and s.cid=ch.cid");
    			//if(rset1.next()){
	    			pw.println("<html>");
					pw.println("<head><title>Confirm</title></head>");
					pw.println("<H1>Your order has been confirmed<BR></H1>");
					pw.println("<hr />");
				
					pw.println("<H2>Custmer Information<BR></H2>");
					pw.println("<body><BR>");
	
					pw.println("<p>Full Name:" + rset1.getString("cname") + "</p>");
					pw.println("<p>Phone Number:" + rset1.getString("tel") + "</p>");
					pw.println("<p>Email:" + rset1.getString("email") + "</p>");
					//pw.println("<p>State:" + rset3.getString("state") + "</p>");
					//pw.println("<p>City:" + rset3.getString("city") + "</p>");
					//pw.println("<p>Street:" + rset3.getString("street") + "</p>");
					//pw.println("<p>Apartment:" + rset3.getString("apartment") + "</p>");
					//pw.println("<p>Postcode:" + rset3.getString("postal_code") + "</p>");
					//pw.println("</body>");
					//pw.println("<H3>Order Information<BR></H3>");
					//pw.println("<p>mid:" + rset2.getString("mid") + "</p>");
					//pw.println("<p>Date:" + rset2.getString("mdate") + "</p>");
					//pw.println("<p>Quantity:" + rset2.getString("quantity") + "</p>");
					//pw.println("<p>Type:" + rset2.getString("type") + "</p>");

					
					conn.close();
    			//}
    			//ResultSet rest2 = stmt.executeQuery("Select s.cid as cid, s.quantity as num, ")
    			
    			//ResultSet rset2 = stmt.executeQuery("select ")
				
        	} catch (SQLException e) {
				pw.println("It cannot happen!");
			}
        	
			
	

	}
}
