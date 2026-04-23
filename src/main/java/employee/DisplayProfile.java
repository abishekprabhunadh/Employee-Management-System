package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/displayProfile")
public class DisplayProfile extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession sn=req.getSession();
		String name=(String)sn.getAttribute("name");
		
		String url="jdbc:mysql://localhost:3306/hrproject";
		String user="root";
		String password="Root";
		
		Connection con=null;
		PreparedStatement ps=null;

		
	    try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,password);
			ps=con.prepareStatement("select * from hr where name=?");
		    ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			
			out.println("<html>");
			out.println("<body>");
			
			if(rs.next())
			{
				out.println("<form action='updateProfile' method='post'>");
				out.println("Id <input type='number' name='id' value="+rs.getInt(1)+" hidden><br>");
				out.println("Name <input type='text' name='name' value="+rs.getString(2)+"><br>");
				out.println("Email <input type='email' name='email' value="+rs.getString(3)+"><br>");
				out.println("Password <input type='password' name='password' value="+rs.getString(4)+"><br>");
				out.println("Mobile <input type='tel' name='mobile' value="+rs.getLong(5)+"><br>");
				out.println("<button>Update</button><br></form><form action='deleteProfile' method='post'><input type='number' name='id' value='"+rs.getInt(1)+"'><button>Delete</button></form>");	
				
			}
			else
			{
				out.println("<h5>");
				out.println("Not Found");
				out.println("</h5>");
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
