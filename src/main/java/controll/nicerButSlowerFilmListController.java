package controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.nicerButSlowerFilmList;
import vo.nicerbutslowerfilmList;

/**
 * Servlet implementation class nicerButSlowerFilmListController
 */
@WebServlet("/nicerButSlowerFilmListController")
public class nicerButSlowerFilmListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<nicerbutslowerfilmList> list = new ArrayList<>();
		//1페이지
		int currentPage=1;
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage=10;
		int BeginRow=(currentPage-1)*rowPerPage;
		
		nicerButSlowerFilmList slower = new nicerButSlowerFilmList();
		list = nicerButSlowerFilmList.SelectByNicer(BeginRow,rowPerPage);

		
		//마지막 페이지
		int lastPage=0;
		int totalRow=slower.totalRow();
		
		if(totalRow%rowPerPage!=0){
			lastPage=totalRow/rowPerPage;
		}else{
			lastPage+=1;
		}
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage",lastPage);
		
		request.getRequestDispatcher("/WEB-INF/view/nicerButSlowerFilmList.jsp").forward(request, response);
		
	}

}
