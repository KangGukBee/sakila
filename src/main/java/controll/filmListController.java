package controll;

import java.util.*;
import vo.*;
import dao.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class filmList
 */
@WebServlet("/filmListController")
public class filmListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage=1; // ���� ������ = 1
		if(request.getParameter("currentPage")!=null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int RowPerPage = 10; //������ �� ������ ��
		int BeginRow= (currentPage-1)*RowPerPage; //�����Ͱ� ������ ��ġ
		
		List<Film> list= new ArrayList<>();
		FilmDao filmdao=new FilmDao();
		list= filmdao.selectFilmListByPage(BeginRow, RowPerPage);
		//������ ������ 
		int lastPage=0;
		int totalRow=filmdao.totalRowDao();
		System.out.println(totalRow+"currentPage �� ������");
		if(totalRow%RowPerPage!=0){
			lastPage=totalRow/RowPerPage;
		}else{
			lastPage+=1;
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/view/filmList.jsp").forward(request, response);
		
	}


}
