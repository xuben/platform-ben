package ben;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//判断是否已登录
		Boolean login = false;
		if(session != null){
			login = (Boolean)session.getAttribute("login");
		}
		
		if(login != null && login == true){//已登录
			response.sendRedirect("welcome.html");
		}else{//未登录
			response.sendRedirect("login.jsp");
		}
	}
}
