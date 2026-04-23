package hr;

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
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class Profile extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession sn=req.getSession();
		String name=(String)sn.getAttribute("name");
		
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
			ps=con.prepareStatement("select * from hr where name=?");
		    ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			
			out.println("<html>");
			out.println("<body>");
			out.println("<link rel='stylesheet' href=css/Profile.css>");

			
			if(rs.next())
			{
				out.println("<div id='error-message' class='erm'></div>");
				out.println("<script>");
				out.println("const params=new URLSearchParams(window.location.search);");
				out.println("const error=params.get('error');");
				out.println("if(error){");
				out.println("	document.getElementById('error-message').innerHTML= error;");
				out.println("}");
				out.println("</script>");				
				out.println("<table id='table'>");
				out.println("<tr><th id='tr1' colspan='7;'>HR</th></tr>");
				out.println("<tr><th class='th1'>ID</th><th class='th1'>Name</th><th class='th1'>Email</th><th class='th1'>Password</th><th class='th1'>Mobile</th><th class='th1'>Update</th><th class='th1'>Remove</th></tr>");
	            out.println("<tr><form action='updateProfile' method='get'><td class='td'><input type='number' name='id' value="+rs.getInt(1)+"></td><td class='td'><input type='text' name='name' value="+rs.getString(2)+"></td><td class='td'><input type='email' name='email' value="+rs.getString(3)+"></td><td class='td'><input type='password' name='password' value="+rs.getString(4)+"></td><td class='td'><input type='tel' name='mobile' value="+rs.getLong(5)+"></td><td class='td'><button id='b1'>Edit</button></form></td><td class='td'><form action='deleteProfile' method='post'><input type='number' name='id' value="+rs.getInt(1)+" hidden><button id='b2'>Delete</button></form></td></tr>");	
	            out.println("</table>");
	            
			}			
			else
			{
				out.println("<h5>");
				out.println("Details Not Found, Try Again");
				out.println("</h5>");
			}
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
