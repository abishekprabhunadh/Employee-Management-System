package hr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogOut extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession sn=req.getSession();
		sn.invalidate();
		PrintWriter out=resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1 style='color:aqua;'>Logout Successful</h1>");
		out.println("</body>");
		out.println("<html>");
		RequestDispatcher rd=req.getRequestDispatcher("HRLoginOrSignUp.html");
		rd.include(req, resp);
	}

}
