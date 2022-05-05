package controll;

import java.io.IOException;
import java.util.*;
import dao.*;
import vo.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class statsDataController
 */
@WebServlet("/statsDataController")
public class statsDataController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//현재 페이지
		int currentPage =1;
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage=10; // 페이지당 데이터 갯수
		int BeginPage=(currentPage-1)*rowPerPage; // 시작 데이터 위치
		statsDataDao statsdatadao = new statsDataDao();
		//고객별 총액
		List<Map<String,Object>> amountList = statsdatadao.amountByCustomer(BeginPage,rowPerPage);
		//임대료 별 영화 갯수
		List<Map<String,Object>> rateList=statsdatadao.selectRate();
		//언어 별 영화 갯수
		List<Map<String,Object>> languageList=statsdatadao.selectLanguage();
		
		//마지막 페이지 
		int lastPage=0;
		int totalRow = statsdatadao.totalRowDao();
		
		if(totalRow%rowPerPage !=0){
			lastPage=totalRow/rowPerPage;
		}else{
			lastPage+=1;
		}
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("lastPage",lastPage);
		request.setAttribute("amountList",amountList);
		request.setAttribute("rateList",rateList);
		request.setAttribute("languageList",languageList);
		
		request.getRequestDispatcher("WEB-INF/view/statsData.jsp").forward(request, response);
	}


}
