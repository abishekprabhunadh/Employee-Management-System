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

@WebServlet("/add")
public class AddEmployee extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		String department=req.getParameter("department");
		double salary=Double.parseDouble(req.getParameter("salary"));
		
		
		String url="jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user="root";
		String pass="Root";
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out=resp.getWriter();
		out.println("<html>");
		out.println("<body>");

		if(id!=0 && name!=null && mobile!=0 && department!=null && salary!=0)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url,user,pass);
				ps=con.prepareStatement("insert into employee values(?,?,?,?,?)");
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setLong(3, mobile);
				ps.setString(4, department);
				ps.setDouble(5, salary);

				int res=ps.executeUpdate();
				if(res==1)
				{

					resp.sendRedirect("Add.html?error=Employee Added Successfully");
					
				}
				else
				{				
					resp.sendRedirect("Add.html?error=Employee Adding UnSuccessful");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				RequestDispatcher rd=req.getRequestDispatcher("hr");
				out.println("<h1>Database Related Error</h1>");
				rd.include(req, resp);
			}
		}
		else
		{
			out.println("<h1>Some Data Is Missing</h1>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
