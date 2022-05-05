package controll;

import java.io.IOException;
import java.util.List;

import vo.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Salesbyfilmcategory;

/**
 * Servlet implementation class salesByFilmCategoryController
 */
@WebServlet("/salesByFilmCategoryController")
public class salesByFilmCategoryController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���� ������ 
		int currentPage=1;
		if(request.getParameter("currentPage")!=null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10; // ������ �� ������
		int BeginRow= (currentPage-1) *rowPerPage;//���� ������ ��ġ
		Salesbyfilmcategory sales =new Salesbyfilmcategory();
		List<salesbyfilmcategory> list = sales.selectSales(BeginRow,rowPerPage);
		
		//totalRow
		int	totalRow   = sales.totalRowDao();
		//������ ������
		int lastPage = 0;
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/salesByFilmCategory.jsp").forward(request, response);
		
	}
}
