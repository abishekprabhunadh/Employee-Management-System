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

@WebServlet("/displayOne")
public class DisplayOne extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String url="jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user="root";
		String password="Root";
		
		int id=Integer.parseInt(req.getParameter("id"));

		Connection con=null;
		PreparedStatement ps=null;

		
	    try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,password);
			ps=con.prepareStatement("select * from employee where id=?");
		    ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			
			out.println("<html>");
			out.println("<body>");
			out.println("<link rel='stylesheet' href=css/UpdateEmployee.css>");
			
			if(rs.next())
			{
				out.println("<fieldset id='field'>");
				out.println("<label for='' class='lab'>Employee ID</label>");
				out.println("<input type='number' name='id' value="+rs.getInt(1)+"> <br>");
				
				out.println("<label for='' class='lab'>Employee Name</label>");
				out.println("<input type='text' name='name' value="+rs.getString(2)+"> <br>");
				
				out.println("<label for='' class='lab'>Employee Mobile</label>");
				out.println("<input type='tel' name='mobile' value="+rs.getLong(3)+"> <br>");
				
				out.println("<label for='' class='lab'>Employee Department</label>");
				out.println("<input type='text' name='department' value="+rs.getString(4)+"> <br>");
				
				out.println("<label for='' class='lab'>Employee Salary</label>");
				out.println("<input type='number' name='salary' value="+rs.getLong(5)+"> <br>");
				out.println("</fieldset>");
			}
			else
			{
				resp.sendRedirect("DisplayOne.html?error=Employee Not Found");
			}
			out.println("</body>");
			out.println("</html>");
		}
	    catch(ClassNotFoundException | SQLException e)
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
	}
}
