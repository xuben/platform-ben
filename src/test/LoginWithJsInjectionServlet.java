package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * input name example
 * "/><script>alert("js injection");</script>
 * @author xuben
 *
 */

@WebServlet("/login2")
public class LoginWithJsInjectionServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");
		if(uname.equals("ben") && passwd.equals("ben"))
		{
			response.sendRedirect("welcome.html");
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "login";
		out.println("<HTML>\n" + "<HEAD><TITLE>" + title
				+ "</TITLE></HEAD>\n" + "<BODY>\n"
				+ "<form action=\"login\" method=\"post\">\n"
				+ "username:<input type=\"text\" name=\"uname\" value=\""
				+ uname//StringEscapeUtils.escapeHtml4(uname)
				+"\"/>\n"
				+ "<br/>"
				+ "password:<input type=\"password\" name=\"passwd\"/>"
				+ "<br/>"
				+ "<input type=\"submit\"/>"
				+ "</form>"
				+ "<font color=\"red\">error username or password</font>"
				+ "</BODY></HTML>");
	}
}
