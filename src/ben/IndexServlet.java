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

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//判断是否已登录
		User user = null;
		if(session != null){
			user = (User)session.getAttribute("user");
		}
		
		String url;
		if(user != null){//已登录
			url = Constants.URL_WELCOME_PAGE;
		}else{//未登录
			url = Constants.URL_LOGIN_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request,  response);
	}
}
