package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.pool.OracleDataSource;
/**
* Servlet implementation class OracleServlet
*/
@WebServlet("/Matchoption")
public class MatchOption extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String connect_string =
"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111g.cs.columbia.edu:1521/ADB";
private Connection conn;
public MatchOption() {
super();
// TODO Auto-generated constructor stub
}

public void init(ServletConfig config) throws ServletException {
}


protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
}

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
response)
*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter pw = response.getWriter();
		try {
			if (conn == null) {
	// Create a OracleDataSource instance and set URL
		OracleDataSource ods = new OracleDataSource();
		ods.setURL(connect_string);
		conn = ods.getConnection();
			}
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select mid,mname,mdate from match_hold");
			 
			pw.println("<html>");
			pw.println("<head><title>Employee Table Servle Sample</title></head>");
			pw.println("<H1>Ticket Selling Website <BR></H1>");
			pw.println("<hr />");
			pw.println("<H2>Match Option <BR></H2>");

			pw.println("<body><BR>");
			pw.println("<FORM ACTION=ticket METHOD=POST >");


		while (rset.next()) {
			pw.println("<INPUT TYPE=CHECKBOX NAME="+rset.getString("mid")+">");	
			pw.println (rset.getString("mid")+"  "+rset.getString("mname")+"  "+rset.getString("mdate") + "<BR>");
			}
			pw.println("<INPUT TYPE=SUBMIT VALUE=NEXT>");
			pw.println("</FORM>");
			
			pw.println("</body></html>");
			} catch (SQLException e) {
				pw.println(e.getMessage());
			}
				pw.close();
	}
}

