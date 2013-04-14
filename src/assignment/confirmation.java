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
 * Servlet implementation class confirmation
 */
@WebServlet("/confirmation")
public class confirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string =
			//"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111f.cs.columbia.edu:1521/ADB";
			"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmation() {
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
		// TODO Auto-generated method stub
	/*	
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
		
		
		
		*/
		
		
		
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		try { 
		if (conn == null) {
		// Create a OracleDataSource instance and set URL
		OracleDataSource ods = new OracleDataSource();
		ods.setURL(connect_string); 
		conn = ods.getConnection();
		}
		Statement stmt = conn.createStatement();

		ResultSet rset = stmt.executeQuery("select cname, email from customer_at c where cid=4321");
		if(rset.next()){
		pw.println("<html>");
		pw.println("<head><title>Confirmation</title></head>");
		pw.println("<H1> Customer Information<BR></H1>");
		pw.println("<body><BR>");
		pw.println("<p>Full Name:" + rset.getString("cname") + "</p>");
		//pw.println("<p>Phone Number:" + rset.getInt("tel") + "</p>");
		pw.println("<p>Email:" + rset.getString("email") + "</p>");
		pw.println("</body></html>");
		}
		} catch (SQLException e) {
			pw.println("It cannot happen!");
		}

	}
}
