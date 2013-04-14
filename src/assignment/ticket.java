package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Servlet implementation class ticket
 */
@WebServlet("/ticket")
public class ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string =
			//"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111g.cs.columbia.edu:1521/ADB";
			"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
			private Connection conn;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ticket() {
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
		ArrayList<String> nameList = new ArrayList<String>();
		while(paramNames.hasMoreElements()) {
	        	nameList.add(paramNames.nextElement());
	        }
		PrintWriter pw = response.getWriter();
	    pw.println("<html>");
		pw.println("<body><BR>");
	
		try {
			if (conn == null) {
	// Create a OracleDataSource instance and set URL
		OracleDataSource ods = new OracleDataSource();
		ods.setURL(connect_string);
		conn = ods.getConnection();
			}
			Statement stmt = conn.createStatement();
			
			ResultSet rset = stmt.executeQuery("select ttid, vlocation, vprice from vip");
			String vprice="";
			if(rset.next())	vprice = rset.getString("vprice");
					
			String rprice="";
			ResultSet regular = stmt.executeQuery("select ttid ,rlocation,rprice from regular");
			if(regular.next()) rprice = regular.getString("rprice");
 
		    
			pw.println("<html>");
			pw.println("<head><title>Ticket</title></head>");
			pw.println("<H1>Ticket Selling Website <BR></H1>");
			pw.println("<hr />");
			pw.println("<H2>Ticket Option <BR></H2>");

			pw.println("<body><BR>");
			pw.println("<FORM ACTION=finall METHOD=POST >");

			for(String s:nameList){
				
				pw.println("<p>"+ s +" VIP price:"+ vprice +"<input type=text name="+s+",vipNum size=10 maxlength=512>Num of ticket</p>");
				pw.println("<p>"+ s +" regular price:"+ rprice +"<input type=text name="+s+",regNum size=10 maxlength=512>Num of ticket</p>");
			}
			pw.println("<INPUT TYPE=SUBMIT VALUE=SUBMIT>");
			pw.println("</FORM>");
			
			
			
			pw.println("</body></html>");
			conn.close();
			}
		
		
		
		catch (SQLException e) {
				pw.println(e.getMessage());
			}
				pw.close();
	}
		

}
