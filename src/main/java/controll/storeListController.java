package controll;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDao;

/**
 * Servlet implementation class storeListController
 */
@WebServlet("/storeListController")
public class storeListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StoreDao storeDao = new StoreDao();
		List<Map<String,Object>> list =storeDao.selectStoreList();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/view/Store/StoreList.jsp").forward(request, response);
	}


}
