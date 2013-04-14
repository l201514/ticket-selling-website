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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string = 
			//"jdbc:oracle:thin:mw2860/rYxtjJKs@//w4111f.cs.columbia.edu:1521/ADB";
			"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		PrintWriter pw = new PrintWriter(response.getOutputStream());
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
			pw.println("<head><title>Registration</title></head>");
			pw.println("<H1>Registration<BR></H1>");
			pw.println("<hr />");
			pw.println("<H2> *fields are required<BR></H2>");

			pw.println("<body><BR>");
			pw.println("<FORM ACTION=address METHOD=POST >");
			pw.println("<p>*Fullname: <input type=text name=fullname size=40 maxlength=512></p>");
			
			pw.println("<p>*Username: <input type=text name=username size=40 maxlength=512></p>");
			pw.println("<p>*Password: <input type=password name=passcode size=40 maxlength=512></p>");
			
			pw.println("<p>*Phone Number: <input type=text name=phonenumber size=40 maxlength=512></p>");
			pw.println("<p>*Email Address: <input type=text name=email size=40 maxlength=512></p>");
			pw.println("<INPUT TYPE=SUBMIT VALUE=Submit>");
			pw.println("</FORM>");
			
			pw.println("</body></html>");
			conn.close();
			} catch (SQLException e) {
				pw.println(e.getMessage());
			}
				pw.close();
	}

}
