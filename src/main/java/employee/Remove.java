package employee;

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

@WebServlet("/remove")
public class Remove extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String url="jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user="root";
		String pass="Root";
	
		int id=Integer.parseInt(req.getParameter("id"));
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out=resp.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		
		
		
	    try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			ps=con.prepareStatement("delete from employee where id=?");
			ps.setInt(1, id);
			int res=ps.executeUpdate();
			if(res==1)
			{
				resp.sendRedirect("Remove.html?error=Employee Removed");
			}
			else
			{				
				resp.sendRedirect("Remove.html?error=Employee Removing Unsuccessful");
			}
			
		}
	    catch(ClassNotFoundException | SQLException e)
	    {
	    	RequestDispatcher rd=req.getRequestDispatcher("hr");
			out.println("<h1>Database Related Error</h1>");
			rd.include(req, resp);
		}

		out.println("</html>");
		out.println("</body>");
	}
	

}
