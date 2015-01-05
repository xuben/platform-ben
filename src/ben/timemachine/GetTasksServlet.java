package ben.timemachine;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ben.Constants;
import ben.timemachine.bean.Task;

@WebServlet("/time-machine/tasks")
public class GetTasksServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Task[]tasks = new Task[4];
		for(int i = 0;i < tasks.length;i++){
			tasks[i] = new Task();
			tasks[i].setName(""+i);
			tasks[i].setInfo(""+i);
			tasks[i].setType(i);
			tasks[i].setState(i);
			tasks[i].setDate(new Date());
		}
		request.setAttribute("tasks", tasks);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.URL_TIME_MACHINE_TASKS_PAGE);
		dispatcher.forward(request, response);
	}
}
