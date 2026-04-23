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
import javax.servlet.http.HttpSession;

@WebServlet("/deleteProfile")
public class DeleteProfile extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String url = "jdbc:mysql://localhost:3306/employeemanagementsystem";
		String user = "root";
		String pass = "Root";

		Connection con = null;
		PreparedStatement ps = null;
		PrintWriter out=resp.getWriter();

		out.println("<html>");
		out.println("<body>");

		int id = Integer.parseInt(req.getParameter("id"));
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			ps = con.prepareStatement("delete from hr where id=?");
			ps.setInt(1, id);
			
			int res = ps.executeUpdate();
			if (res == 1)
			{
				resp.sendRedirect("HRLoginOrSignUp.html?error=Deleted Successfully");
			}
			else
			{
				resp.sendRedirect("profile?error=Deleting UnSuccessful");
			}

		}
		catch (ClassNotFoundException | SQLException e)
		{
			RequestDispatcher rd=req.getRequestDispatcher("profile");
			out.println("<h1>Database Related Error</h1>");
			rd.include(req, resp);
		}
		out.println("</html>");
		out.println("</body>");

	}
}
