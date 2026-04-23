package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayAll")
public class DisplayAll extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String url="jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user="root";
		String password="Root";
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out=resp.getWriter();

		
	    try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,password);
			ps=con.prepareStatement("select * from employee");
			ResultSet rs=ps.executeQuery();
			
			out.println("<html>");
			out.println("<body>");
            out.println("<link rel='stylesheet' href=css/DisplayAll.css>");
            out.println("<table id='table'>");
            out.println("<tr><th id='tr1' colspan='7;'>Employees</th></tr>");
            out.println("<tr><th class='th1'>ID</th><th class='th1'>Name</th><th class='th1'>Mobile</th><th class='th1'>Department</th><th class='th1'>Salary</th><th class='th1'>Update</th><th class='th1'>Remove</th></tr>");
            out.println("<div id='error-message' class='erm'></div>");
            out.println("<script>");
            out.println("const params=new URLSearchParams(window.location.search);");
            out.println("const error=params.get('error');");
            out.println("if(error){");
            out.println("	document.getElementById('error-message').innerHTML= error;");
            out.println("}");
            out.println("</script>");		

			
			while(rs.next())
			{	
	            out.println("<tr><form action='updateRecord' method='get'><td class='td'><input type='number' name='id' value="+rs.getInt(1)+"></td><td class='td'><input type='text' name='name' value="+rs.getString(2)+"></td><td class='td'><input type='tel' name='mobile' value="+rs.getLong(3)+"></td><td class='td'><input type='text' name='department' value="+rs.getString(4)+"></td><td class='td'><input type='number' name='salary' value="+rs.getDouble(5)+"></td><td class='td'><button id='b1'>Edit</button></form></td><td class='td'><form action='removeRecord' method='get'><input type='number' name='id' value="+rs.getInt(1)+" hidden><button id='b2'>Delete</button></form></td></tr>");	
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		}
	    catch(ClassNotFoundException | SQLException e)
	    {
	    	RequestDispatcher rd=req.getRequestDispatcher("hr");
			out.println("<h1>Database Related Error</h1>");
			rd.include(req, resp);
	    }
	}
}
