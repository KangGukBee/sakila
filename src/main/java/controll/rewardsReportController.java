package controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.rewardsReportDao;

/**
 * Servlet implementation class rewardsReportController
 */
@WebServlet("/rewardsReportController")
public class rewardsReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rewardsReportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String,Object>> list =new ArrayList<>();
		rewardsReportDao rewardsreportdao = new rewardsReportDao();
		int count=0;
		
		int purchases=0;
		if(request.getParameter("purchases")!=null){
			purchases=Integer.parseInt(request.getParameter("purchases"));
			System.out.println(purchases+"controller");
		}
		double purchased=0.00;
		if(request.getParameter("purchased")!=null){
			purchased=Double.parseDouble(request.getParameter("purchased"));
			System.out.println(purchased+"controller");
		}
		Map<String,Object> map=rewardsreportdao.reward(purchases, purchased);
		
		list =(List<Map<String,Object>>)map.get("list");
		
		if(request.getParameter("purchases")!=null && request.getParameter("purchased")!=null){	
			count=(Integer)map.get("count");		
		}
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("/WEB-INF/view/rewardsReport.jsp").forward(request, response);;
		
	}

}
