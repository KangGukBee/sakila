package controll;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RentalDao;

/**
 * Servlet implementation class rentalSearchController
 */
@WebServlet("/rentalSearchController")
public class rentalSearchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/rentalSearchForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int currentPage=1; // 현재페이지
		if(request.getParameter("currentPage") != null) {  // 이전 , 다음 버튼을 이용하여 접속
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage+ "현재 페이지");
		
		int rowPerPage=1;
		int beginRow=(currentPage-1) *rowPerPage;

		
		int storeId=Integer.parseInt(request.getParameter("storeId"));
		String customerName = request.getParameter("customerName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		request.setAttribute("storeId", storeId);
		request.setAttribute("customerName", customerName);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);



		
		//
		RentalDao rentaldao= new RentalDao();
		List<Map<String,Object>> list = rentaldao.selectReantalSearchList(beginRow,rowPerPage,storeId, customerName, startDate, endDate);
		request.setAttribute("list", list);
		
		//마지막 페이지
		int lastPage=0;
		int totalRow = rentaldao.totalRowDao(storeId, customerName, startDate, endDate);
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/view/rentalSearchAction.jsp").forward(request, response);
	}

}
