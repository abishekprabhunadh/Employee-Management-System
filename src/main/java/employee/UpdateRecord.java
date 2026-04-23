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

@WebServlet("/updateRecord")
public class UpdateRecord extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String url="jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user="root";
		String pass="Root";
	
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		String department=req.getParameter("department");
		double salary=Double.parseDouble(req.getParameter("salary"));
		
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out=resp.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		
		
		
	    try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			ps=con.prepareStatement("update employee set name=?,mobile=?,department=?,salary=? where id=?");
			ps.setString(1, name);
			ps.setLong(2, mobile);
			ps.setString(3, department);
			ps.setDouble(4, salary);
			ps.setInt(5, id);
			int res=ps.executeUpdate();
			if(res==1)
			{
				resp.sendRedirect("displayAll?error=Employee Details Updated");
			}
			else
			{				
				resp.sendRedirect("displayAll?error=Employee Details Updating Unsuccessful");
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
