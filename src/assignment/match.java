package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Servlet implementation class match
 */
@WebServlet("/match")
public class match extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string = 
			//"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111f.cs.columbia.edu:1521/ADB";
			"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public match() {
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
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		try { 
		if (conn == null) {
		// Create a OracleDataSource instance and set URL
		OracleDataSource ods = new OracleDataSource();
		ods.setURL(connect_string); 
		conn = ods.getConnection();
		}
		Statement stmt = conn.createStatement();

		ResultSet rset = stmt.executeQuery("select m.mid as mid, t1.tname as Visit, t2.tname as Home, s.sname as sname from team t1, team t2, consistof c, Match_hold m, Stadium s where t1.tid=c.tid1 and t2.tid=c.tid2 and m.mid=c.mid and s.sid=m.sid");
		response.setContentType("text/html");
		pw.println("<html>");
		pw.println("<head><title>Team name and IDs</title></head>");
		pw.println("<H1>Show All Matches Information<BR></H1>");
		pw.println("<body><BR>");
		pw.println("<FORM ACTION=ticket METHOD=POST >");
		pw.println("<table border=1 width=800 cellspacing=5");
		pw.println("<tr bgcolor=green><th>Selection</th><th>Match Event</th><th>Date</th> <th>Visiting team</th> <th>Home team</th> <th>Holding Stadium</th> <th>Infos</th></tr>");
		
		while (rset.next()) {

			pw.println("<tr bgcolor=ffffff>");
			pw.println("<td><INPUT TYPE=CHECKBOX NAME="+rset.getString("mid")+"></td>");	
			pw.println("<td>" + "Football" + "</td><td>" +rset.getString("mid") +"</td><td>" + rset.getString("Visit") + "</td><td>" + rset.getString("Home")+
					"</td><td>" + rset.getString("sname") + "<td>"+ "<a href=" + '"' + "http://localhost:9080/m/Onesolution" + '"'  +"target=" + '"' + "_blank" + '"'
					+ ">Information</a>" + "</td>");
			
			pw.println("</tr>");
		}
		pw.println("<INPUT TYPE=SUBMIT VALUE=NEXT>");
		pw.println("</FORM>");
		pw.println("</body></html>");
		conn.close();
		 } catch (SQLException e) {
		pw.println(e.getMessage());
		 }
		 pw.close();
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
			ResultSet rset = stmt.executeQuery("select mid,mname,mid from match_hold");
			 
			pw.println("<html>");
			pw.println("<head><title>Employee Table Servle Sample</title></head>");
			pw.println("<H1>Ticket Selling Website <BR></H1>");
			pw.println("<hr />");
			pw.println("<H2>Match Option <BR></H2>");

			pw.println("<body><BR>");
			pw.println("<FORM ACTION=ticket METHOD=POST >");


		while (rset.next()) {
			pw.println("<INPUT TYPE=CHECKBOX NAME="+rset.getString("mid")+">");	
			pw.println (rset.getString("mid")+"  "+rset.getString("mname")+"  "+rset.getString("mid") + "<BR>");
			}
			pw.println("<INPUT TYPE=SUBMIT VALUE=NEXT>");
			pw.println("</FORM>");
			
			pw.println("</body></html>");
			} catch (SQLException e) {
				pw.println(e.getMessage());
			}
				pw.close();
	}*/

}
