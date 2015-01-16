package ben.timemachine;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ben.timemachine.bean.Task;

@WebServlet("/time-machine/task/remove")
public class RemoveTaskServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String taskIdStr = request.getParameter("id");
		long taskId = 0;
		try{
			taskId = Long.parseLong(taskIdStr);
		}catch(Exception e){
		}
		
		//非法输入
		if(taskId == 0){
		}else{
			HttpSession session = request.getSession();
			if(session != null){
				List<Task>tasks = (List<Task>)session.getAttribute("tasks");
				if(tasks != null){
					for(int i = tasks.size() - 1;i >= 0;i--){
						Task task = tasks.get(i);
						if(task.getId() == taskId){
							tasks.remove(task);
							break;
						}
					}
					session.setAttribute("tasks", tasks);
				}
			}
		}
	}
}
