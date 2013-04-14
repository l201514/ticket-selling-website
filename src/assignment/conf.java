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
* Servlet implementation class OracleServlet
*/
@WebServlet("/conf")
public class conf extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String connect_string = 
"jdbc:oracle:thin:yz2516/zhyl33633258@//w4111g.cs.columbia.edu:1521/ADB";
private Connection conn;

/**
* @see HttpServlet#HttpServlet()
*/
public conf() {
super();
// TODO Auto-generated constructor stub
 }
/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
//PrintWriter pw = new PrintWriter(response.getOutputStream());
	PrintWriter pw = response.getWriter();
try { 
if (conn == null) {
// Create a OracleDataSource instance and set URL
OracleDataSource ods = new OracleDataSource();
ods.setURL(connect_string); 
conn = ods.getConnection();
}
Statement stmt = conn.createStatement();

ResultSet rset = stmt.executeQuery("select c.cname as cname, c.tel as tel, c.email as email from Customer_at c where c.cid=4321");
response.setContentType("text/html");
pw.println("<html>");
pw.println("<head><title>Visiting team infos</title></head>");
pw.println("<H2>Show All Players infos in Two Teams<BR></H2>");
pw.println("<body><BR>");
pw.println("<table border=1 width=500 cellspacing=5");
pw.println("<tr bgcolor=yellow><th>Team Status</th> <th>Teamname</th> <th>Playername</th> <th>Age</th></tr>");

while (rset.next()) {

pw.println("<tr bgcolor=ffffff>");
pw.println("<td>" + "customer stuff" + "</td><td>"+ rset.getString("cname") +"</td><td>"+ rset.getString("tel") +"</td><td>"+ rset.getString("email") +"</td>");


pw.println("</tr>");
}
ResultSet qset = stmt.executeQuery("select a.state as state,a.city as city,a.street as street,a.apartment as apartment ,a.postal_code as postal_code from Address a where a.cid=4321");
response.setContentType("text/html");
pw.println("<html>");

pw.println("<body><BR>");
pw.println("<table border=1 width=500 cellspacing=5");
pw.println("<tr bgcolor=pink><th>address</th> <th>Statename</th> <th>Cityname</th> <th>Street</th> <th>Apartment</th> <th>postal_code</th></tr>");
while (qset.next()) {

pw.println("<tr bgcolor=ffffff>");
pw.println("<td>" + "Address stuff" + "</td><td>"+ qset.getString("state") +"</td><td>"+ qset.getString("city") +"</td><td>"+ qset.getString("street") +"</td><td>"+ qset.getString("apartment") +"</td><td>"+ qset.getString("postal_code") +"</td>");


pw.println("</tr>");
}


pw.println("</body></html>");
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
	/*PrintWriter pw = response.getWriter();
	try { 
	if (conn == null) {
	// Create a OracleDataSource instance and set URL
	OracleDataSource ods = new OracleDataSource();
	ods.setURL(connect_string); 
	conn = ods.getConnection();
	}
	Statement stmt = conn.createStatement();

	ResultSet rset = stmt.executeQuery("select c.cname as cname, c.tel as tel, c.email as email from Customer_at c where c.cid=4321");
	response.setContentType("text/html");
	pw.println("<html>");
	pw.println("<head><title>Visiting team infos</title></head>");
	pw.println("<H2>Show All Players infos in Two Teams<BR></H2>");
	pw.println("<body><BR>");
	pw.println("<table border=1 width=500 cellspacing=5");
	pw.println("<tr bgcolor=yellow><th>Team Status</th> <th>Teamname</th> <th>Playername</th> <th>Age</th></tr>");

	while (rset.next()) {

	pw.println("<tr bgcolor=ffffff>");
	pw.println("<td>" + "customer stuff" + "</td><td>"+ rset.getString("cname") +"</td><td>"+ rset.getString("tel") +"</td><td>"+ rset.getString("email") +"</td>");


	pw.println("</tr>");
	}
	ResultSet qset = stmt.executeQuery("select a.state as state,a.city as city,a.street as street,a.apartment as apartment ,a.postal_code as postal_code from Address a where a.cid=4321");
	response.setContentType("text/html");
	pw.println("<html>");

	pw.println("<body><BR>");
	pw.println("<table border=1 width=500 cellspacing=5");
	pw.println("<tr bgcolor=pink><th>address</th> <th>Statename</th> <th>Cityname</th> <th>Street</th> <th>Apartment</th> <th>postal_code</th></tr>");
	while (qset.next()) {

	pw.println("<tr bgcolor=ffffff>");
	pw.println("<td>" + "Address stuff" + "</td><td>"+ qset.getString("state") +"</td><td>"+ qset.getString("city") +"</td><td>"+ qset.getString("street") +"</td><td>"+ qset.getString("apartment") +"</td><td>"+ qset.getString("postal_code") +"</td>");


	pw.println("</tr>");
	}


	pw.println("</body></html>");
	 } catch (SQLException e) {
	pw.println(e.getMessage());
	 }
	 pw.close();
	}
*/

}
}