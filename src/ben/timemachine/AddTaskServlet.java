package ben.timemachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ben.ServletUtil;
import ben.timemachine.bean.Task;

@WebServlet("/time-machine/task/add")
public class AddTaskServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String subName = request.getParameter("subName");
		String info = request.getParameter("info");
		int type = 0;
		int state = 0;
		HttpSession session = request.getSession();
		try{
			type = Integer.parseInt(request.getParameter("type"));
			state = Integer.parseInt(request.getParameter("state"));
		}
		catch(Exception e){
		}
		//非法输入
		if(ServletUtil.isInvalidInput(name) || ServletUtil.isInvalidInput(subName)){
		}else{
			if (session != null) {
				Task task = new Task();
				task.setDate(new Date());
				task.setName(name);
				task.setSubName(subName);
				task.setInfo(info);
				task.setType(type);
				task.setState(state);
				List<Task>tasks = (List<Task>)session.getAttribute("tasks");
				if(tasks == null){
					tasks = new ArrayList<Task>();
				}
				tasks.add(task);
				session.setAttribute("tasks", tasks);
				
				JSONArray array = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("date", task.getDate().toString());
				obj.put("name", task.getName());
				obj.put("subName", task.getSubName());
				obj.put("info", task.getInfo());
				obj.put("type", task.getType());
				obj.put("state", task.getState());
				array.add(obj);
				response.setCharacterEncoding("utf-8");
				PrintWriter writer = response.getWriter();
				writer.print(array.toJSONString());
			}
		}
	}
}
