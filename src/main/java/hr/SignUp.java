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

@WebServlet("/signUp")
public class SignUp extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		
		
		String url="jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user="root";
		String pass="Root";
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out=resp.getWriter();
		out.println("<html>");
		out.println("<body>");

		if(id!=0 && name!=null && email!=null && password!=null && mobile!=0)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url,user,pass);
				ps=con.prepareStatement("insert into hr values(?,?,?,?,?)");
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, password);
				ps.setLong(5, mobile);

				int res=ps.executeUpdate();
				if(res==1)
				{

					resp.sendRedirect("HRLogin.html?error=HR Details Added Succesful");
					
				}
				else
				{				
					resp.sendRedirect("HRLogin.html?error=HR Details Adding UnSuccesful");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				resp.sendRedirect("HRLoginOrSignUp.html?error=Database Related Error");
			}
		}
		else
		{
			resp.sendRedirect("HRSignUp.html?error=Some Data Is Missing, Try Again");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
