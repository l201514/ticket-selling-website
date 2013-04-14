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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String connect_string =
			//"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111f.cs.columbia.edu:1521/ADB";
			"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;
	public static String cid = null;
    /**
     * @see HttpServlet#HttpServlet()
     */

        // TODO Auto-generated constructor stub   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		PrintWriter pw = response.getWriter();
	 	
		
		Enumeration<String> paramNames = request.getParameterNames();
		String username="";
    	String passcode="";
        while(paramNames.hasMoreElements()) {
        	
        	String paramName = paramNames.nextElement();
        	 if(paramName.equals("username")){
        		username=request.getParameterValues(paramName)[0];
        	}else if(paramName.equals("passcode")){
        		passcode=request.getParameterValues(paramName)[0];
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
    			ResultSet rset = stmt.executeQuery("select cid, password from customer_at where cid='"+username+"' and password='"+passcode+"'");
    			if(rset.next())
    			{
    				cid = username;
    				pw.println("<html>");
    				pw.println("<head><title>Login</title></head>");
    				pw.println("<H1>Successful login<BR></H1>");
    				pw.println("<hr />");
		

					pw.println("<body><BR>");
					pw.println("<FORM ACTION=match METHOD=POST>");
			
					pw.println("<INPUT TYPE=SUBMIT VALUE=Continue>");
					pw.println("</FORM>");
					pw.println("</body></html>");
    			}
    			else
    			{
    				cid = null;
    				pw.println("<html>");
    				pw.println("<head><title>Login</title></head>");
    				pw.println("<H1>Invalid username or password!<BR></H1>");
    				pw.println("<hr />");
		

					pw.println("<body><BR>");
					pw.println("<FORM ACTION=m METHOD=POST>");
			
					pw.println("<INPUT TYPE=SUBMIT VALUE=Back!>");
					pw.println("</FORM>");
					pw.println("</body></html>");
					conn.close();
    			}
		    } catch (SQLException e) {
				pw.println("It cannot happen");
			}
        	
        
        	
	}

}
