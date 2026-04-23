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

@WebServlet("/login")
public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		String url="jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user="root";
		String pass="Root";
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out=resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		

		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			ps=con.prepareStatement("select * from hr");
	
			ResultSet rs=ps.executeQuery();
			boolean isEmail=false;
			while(rs.next())
			{
				if(rs.getString(3).equals(email))
				{
					isEmail=true;
					if(rs.getString(4).equals(password))
					{
						HttpSession sn=req.getSession();
					    sn.setAttribute("name",rs.getString(2));
						RequestDispatcher rd=req.getRequestDispatcher("hr");
						rd.forward(req, resp);
					}
					else
					{
						resp.sendRedirect("HRLogin.html?error=Invalid Password");
					}
				}
			}
			if(isEmail==false)
			{
				resp.sendRedirect("HRLogin.html?error=Invalid Email");
			}
		}
		catch(ClassNotFoundException | SQLException e)
		{
			resp.sendRedirect("HRLoginOrSignUp.html?error=Database Related Error");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
