package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.pool.OracleDataSource;
/**
* Servlet implementation class OracleServlet
*/
@WebServlet("/Onesolution")
public class Onesolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string = 
	"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;
	
	/**
	* @see HttpServlet#HttpServlet()
	*/
	public Onesolution() {
	super();
	// TODO Auto-generated constructor stub
	 }
	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		try { 
			if (conn == null) {
				// Create a OracleDataSource instance and set URL
				OracleDataSource ods = new OracleDataSource();
				ods.setURL(connect_string); 
				conn = ods.getConnection();
			}
			Statement stmt = conn.createStatement();
		
			ResultSet rset = stmt.executeQuery("select t.tname as tname, p.pname as name, p.age as age from player p, contain c, team t, consistof a where c.tid=t.tid and c.pid=p.pid and t.tid=a.tid1 and a.mid=201");
			response.setContentType("text/html");
			pw.println("<html>");
			pw.println("<head><title>Visiting team infos</title></head>");
			pw.println("<H2>Show All Players infos in Two Teams<BR></H2>");
			pw.println("<body><BR>");
			pw.println("<table border=1 width=500 cellspacing=5");
			pw.println("<tr bgcolor=yellow><th>Team Status</th> <th>Teamname</th> <th>Playername</th> <th>Age</th></tr>");
			
			while (rset.next()) {
				pw.println("<tr bgcolor=ffffff>");
				pw.println("<td>" + "Visiting Team" + "</td><td>"+ rset.getString("tname") +"</td><td>"+ rset.getString("name") +"</td><td>"+ rset.getFloat("age") +"</td>");	
				pw.println("</tr>");
			}
			ResultSet qset = stmt.executeQuery("select t.tname as tname, p.pname as name, p.age as age from player p, contain c, team t, consistof a where c.tid=t.tid and c.pid=p.pid and t.tid=a.tid2 and a.mid=201");
			while (qset.next()) {
				pw.println("<tr bgcolor=ffffff>");
				pw.println("<td>" + "Home Team" + "</td><td>"+ qset.getString("tname") +"</td><td>"+ qset.getString("name") +"</td><td>"+ qset.getFloat("age") +"</td>");
				pw.println("</tr>");
			}
			pw.println("</body></html>");
			conn.close();
		 } catch (SQLException e) {
			 pw.println(e.getMessage());
		 }
		 pw.close();
	}
	
	
	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	// TODO Auto-generated method stub
	}
}