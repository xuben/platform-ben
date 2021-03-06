package ben;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ben.bean.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Constants.URL_ROOT_PAGE);
	}
	
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
		//登录错误,需要重新登录
		if(errorCode > 0){
			//设置错误信息
			request.setAttribute("uname", uname);
			request.setAttribute("errorMsg", ServletUtil.getErrorMsg(errorCode));
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.URL_LOGIN_PAGE);
			dispatcher.forward(request, response);
		}else{
			//设置登录状态
			if(session != null){
				User user = new User();
				user.setName(uname);
				session.setAttribute("user", user);
			}
			response.sendRedirect(Constants.URL_ROOT_PAGE);
		}
	}
}
