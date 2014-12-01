package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuben
 *
 */
@WebServlet("/generate-excel")
public class GenerateExcelServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/vnd.ms-excel");
		PrintWriter out = response.getWriter();
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 5; column++) {
				if (column == 4) {
					out.println(Math.random());
				} else {
					out.print(Math.random() + "\t");
				}
			}
		}
	}
}
