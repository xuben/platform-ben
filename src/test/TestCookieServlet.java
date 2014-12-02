package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testcookie")
public class TestCookieServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[]cookies = request.getCookies();
		int count = 1;
		if(cookies != null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("count")){
					count = Integer.parseInt(cookie.getValue()) + 1;
					break;
				}
			}
		}
		Cookie newCookie = new Cookie("count", count+"");
		newCookie.setMaxAge(60*60*24);
		response.addCookie(newCookie);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("you have visited " + count + " times");
	}
}
