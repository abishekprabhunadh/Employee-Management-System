package hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateProfile")
public class UpdateProfile extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String url="jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user="root";
		String pass="Root";
		
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out=resp.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		
		
		
	    try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			ps=con.prepareStatement("update hr set name=?,email=?,password=?,mobile=? where id=?");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setLong(4, mobile);
			ps.setInt(5, id);
			int res=ps.executeUpdate();
			if(res==1)
			{
				
				resp.sendRedirect("profile?error=Update Successful");
			}
			else
			{				
				resp.sendRedirect("profile?error=Updating Unsuccessful");
			}
			
		}
	    catch(ClassNotFoundException | SQLException e)
	    {
	    	RequestDispatcher rd=req.getRequestDispatcher("profile");
			out.println("<h1>Database Related Error</h1>");
			rd.include(req, resp);
		}

		out.println("</html>");
		out.println("</body>");
	}
	

}
