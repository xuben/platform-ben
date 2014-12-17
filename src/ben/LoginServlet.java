package ben;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");
		String authcode = request.getParameter("authcode");
		HttpSession session = request.getSession();
		//错误信息
		int errorCode = 0;
		Object rightAuthcode = request.getSession().getAttribute("authcode");
		if(rightAuthcode == null || !rightAuthcode.toString().equalsIgnoreCase(authcode)){
			//验证码错误
			errorCode = ServletUtil.ERROR_LOGIN_AUTHCODE;
		}else if(uname.equals("ben") && passwd.equals("ben")){//验证用户名密码
		}else{//用户名密码错误
			errorCode = ServletUtil.ERROR_LOGIN_VALIDATION;
		}
		//设置错误码和用户名
		if(session != null){
			session.setAttribute("errorCode", errorCode);
			session.setAttribute("uname", uname);
		}
		//登录错误,需要重新登录
		if(errorCode > 0){
			response.sendRedirect("login.jsp");
		}else{
			//设置登录状态
			if(session != null){
				session.setAttribute("login", true);
			}
			response.sendRedirect("welcome.html");
		}
	}
}
