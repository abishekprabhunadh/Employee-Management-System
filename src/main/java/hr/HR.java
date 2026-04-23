package hr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/hr")
public class HR extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession sn=req.getSession();
		String name=(String)sn.getAttribute("name");
		PrintWriter out=resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<link rel='stylesheet' href=css/MainPage.css>");
		out.println("<h1 id='heading'> Welcome "+name+"</h1>");
		out.println("<form action='profile' method='get'><button id='bt1'>Profile</button></form>");		
		out.println("<fieldset id='field'>");
		out.println("<br>");		
		out.println("<form action=''><div class='anchors'><a href='Add.html' style='text-decoration:none;' class='a'>Add Employee</a></div></form>");	
		out.println("<form action=''><div class='anchors'><a href='Update.html' style='text-decoration:none;' class='a'>Update Employee</a></div></form>");		
		out.println("<form action=''><div class='anchors'><a href='DisplayOne.html' style='text-decoration:none;' class='a'>Display One Employee</a></div></form>");		
		out.println("<form action=''><div class='anchors'><a href='Remove.html' style='text-decoration:none;' class='a'>Remove Employee</a></div></form>");		
		out.println("<form action=''><div class='d'><a href='DED.html' style='text-decoration:none;' class='a ac'>Display/Edit/Delete<br><p id='all'>All Employees</p></a></div></form>");	
		out.println("<form action='logout' method='post'><button id='bt'>LogOut</button></form>");				
		out.println("</fieldset>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
