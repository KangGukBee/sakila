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
		//���� ������
		int currentPage =1;
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage=10; // �������� ������ ����
		int BeginPage=(currentPage-1)*rowPerPage; // ���� ������ ��ġ
		statsDataDao statsdatadao = new statsDataDao();
		//���� �Ѿ�
		List<Map<String,Object>> amountList = statsdatadao.amountByCustomer(BeginPage,rowPerPage);
		//�Ӵ�� �� ��ȭ ����
		List<Map<String,Object>> rateList=statsdatadao.selectRate();
		//��� �� ��ȭ ����
		List<Map<String,Object>> languageList=statsdatadao.selectLanguage();
		
		//������ ������ 
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
