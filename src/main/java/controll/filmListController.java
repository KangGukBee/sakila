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
		int filmId=0;
		int storeId=0;
		if(request.getParameter("filmId")!=null && request.getParameter("storeId")!=null){
			 filmId= Integer.parseInt(request.getParameter("filmId"));
			 storeId=Integer.parseInt(request.getParameter("storeId"));
		}
		Map<String,Object> map =new HashMap<String,Object>();
		FilmInStock filminstock=new FilmInStock();
		map=filminstock.filmInStockCall(filmId,storeId);
		List<Integer> list =new ArrayList<Integer>();
		
		list = (List<Integer>)map.get("list");
		
		int count=(Integer)map.get("count");
		
		request.setAttribute("filmId", filmId);
		request.setAttribute("storeId", storeId);
		request.setAttribute("list",list);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("/WEB-INF/view/filmNotInStock.jsp").forward(request, response);
		
		
	}


}
