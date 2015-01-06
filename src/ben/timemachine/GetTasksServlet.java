package ben.timemachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/time-machine/tasks")
public class GetTasksServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		JSONArray array = new JSONArray();
		for(int i = 0;i < 4;i++){
			JSONObject obj = new JSONObject();
			obj.put("date", new Date().toString());
			obj.put("name", ""+i);
			obj.put("info", ""+i);
			obj.put("type", i);
			obj.put("state", i);
			array.add(obj);
		}
		PrintWriter writer = response.getWriter();
		writer.print(array.toJSONString());
	}
}
