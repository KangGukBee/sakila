package controll;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StaffDao;

/**
 * Servlet implementation class staffListController
 */
@WebServlet("/staffListController")
public class staffListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StaffDao staffDao= new StaffDao();
		List<Map<String,Object>> list = staffDao.selectStaffList();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/Staff/staffList.jsp").forward(request, response);
	}

}
