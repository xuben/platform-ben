package ben.timemachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ben.timemachine.bean.Task;

@WebServlet("/time-machine/tasks")
public class GetTasksServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Task>tasks = (List<Task>)session.getAttribute("tasks");
		if(tasks == null){
			tasks = new ArrayList<Task>();
			session.setAttribute("tasks", tasks);
		}
		JSONArray array = new JSONArray();
		
		for(Task task:tasks){
			JSONObject obj = new JSONObject();
			obj.put("date", task.getDate().toString());
			obj.put("name", task.getName());
			obj.put("subName", task.getSubName());
			obj.put("info", task.getInfo());
			obj.put("type", task.getType());
			obj.put("state", task.getState());
			array.add(obj);
		}
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(array.toJSONString());
	}
}
