package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * return compressed data
 * 
 * @author xuben
 *
 */

@WebServlet("/compressed")
public class DataCompressionServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out;
		//enable check
		String flag = request.getParameter("disableGzip");
		Boolean disabled = flag != null && !flag.equalsIgnoreCase("false");
		// support check
		String acceptEncoding = request.getHeader("Accept-Encoding");
		Boolean support = acceptEncoding != null && acceptEncoding.contains("gzip");
		if (!disabled && support) {
			out = new PrintWriter(new GZIPOutputStream(
					response.getOutputStream()));
			response.setHeader("Content-Encoding", "gzip");
		} else {
			out = response.getWriter();
		}
		
		out.println("<HTML>\n" + "<HEAD><TITLE>" + "compressed"
				+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">\n"
				+ "<H1 ALIGN=\"CENTER\">" + "compressed" + "</H1>\n");
		String line = "Blah, blah, blah, blah, blah. "
				+ "Yadda, yadda, yadda, yadda.";
		for (int i = 0; i < 100000; i++) {
			out.println(line);
		}
		out.println("</BODY></HTML>");
		out.close();
	}
}
