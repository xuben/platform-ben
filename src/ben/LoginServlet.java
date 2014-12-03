package ben;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");
		String authcode = request.getParameter("authcode");
		//错误信息
		String errorMsg;
		Object rightAuthcode = request.getSession().getAttribute("authcode");
		if(rightAuthcode == null || !rightAuthcode.toString().equalsIgnoreCase(authcode)){
			//验证码错误
			errorMsg = "error authcode";
		}else if(uname.equals("ben") && passwd.equals("ben")){//验证用户名密码
			//设置登录状态
			HttpSession session = request.getSession();
			if(session != null){
				session.setAttribute("login", true);
			}
			
			response.sendRedirect("welcome.html");
			return;
		}else{//用户名密码错误
			errorMsg = "error username or password";
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "login";
		out.println("<HTML>\n" + "<HEAD><TITLE>" + title
				+ "</TITLE></HEAD>\n" + "<BODY>\n"
				+ "<form action=\"login\" method=\"post\">\n"
				+ "username:<input type=\"text\" name=\"uname\" value=\""
				//html encoding
				+ StringEscapeUtils.escapeHtml4(uname)
				+"\"/>\n"
				+ "<br/>"
				+ "password:<input type=\"password\" name=\"passwd\"/>"
				+ "<br/>"
				+ "authcode:<img src=\"authcode\"/>"
				+ "<input type=\"text\" name=\"authcode\" style=\"width:80px;height:20px;\"/>"
				+ "<br/>"
				+ "<input type=\"submit\"/>"
				+ "</form>"
				+ "<font color=\"red\">"+errorMsg+"</font>"
				+ "</BODY></HTML>");
	}
}
