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

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Constants.URL_SIGNUP_PAGE);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String password = request.getParameter("passwd");
		String password2 = request.getParameter("passwd2");
		String email = request.getParameter("email");
		int errorCode = 0;
		if(!ServletUtil.isInvalidInput(uname) || !ServletUtil.isInvalidInput(password) 
				|| !ServletUtil.isInvalidInput(password2) || !ServletUtil.isInvalidInput(email)){
			errorCode = ServletUtil.ERROR_SIGNUP_INVALID_INPUT;
		}else if(!password.equals(password2)){
			errorCode = ServletUtil.ERROR_SINGUP_PASSWORD_INCONSISTENT;
		}
		
		if(errorCode > 0){//注册失败
			//设置错误码
			String errorMsg = ServletUtil.getErrorMsg(errorCode);
			request.setAttribute("errorMsg", errorMsg);
			//设置输入的用户名和邮件
			request.setAttribute("username", uname);
			request.setAttribute("email", email);
			//转发到注册表单
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.URL_SIGNUP_PAGE);
			dispatcher.forward(request, response);
		}else{//注册成功
//			response.setHeader("Refresh", "3;url="+Constants.URL_ROOT_PAGE);
//			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.URL_SIGNUP_SUCCESS_PAGE);
//			dispatcher.forward(request, response);
			//自动登录
			HttpSession session = request.getSession();
			if(session != null){
				User user = new User();
				user.setName(uname);
				session.setAttribute("user", user);
			}
			//延迟跳转
			response.sendRedirect(Constants.URL_SIGNUP_SUCCESS_PAGE);
		}
	}
}
