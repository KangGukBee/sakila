package controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActorInfoDao;
import vo.ActorInfo;

/**
 * Servlet implementation class actorController
 */
@WebServlet("/actorController")
public class actorController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1; // ����������
		if(request.getParameter("currentPage") != null) {  // ���� , ���� ��ư�� �̿��Ͽ� ����
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage+ "���� ������");
		

		int rowPerPage = 10; // ������ �� ������ ����
		int beginRow = (currentPage-1)*rowPerPage; // ������ ������ ��ȣ
		
		//Actorinfo.vo ������
		ActorInfoDao actorinfodao= new ActorInfoDao();
		List<ActorInfo> list = new ArrayList<>();
		list=actorinfodao.selectActorInfoListByPage(beginRow, rowPerPage);
		
		//totalRow
		int	totalRow   = actorinfodao.totalRowDao();
		//������ ������
		int lastPage = 0;
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage",lastPage);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/actorList.jsp").forward(request, response);
		
		
	}


}
