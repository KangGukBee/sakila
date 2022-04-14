<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%
	int currentPage=1; //현재 페이지
	
	if(request.getParameter("currentPage")!=null){//이전 다음 페이지를 통해 접속
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int rowPerPage=10; //데이터 갯수
	int BeginRow =(currentPage-1)*rowPerPage; // 시작할 데이터 위치
	
	FilmDao filmdao = new FilmDao();
	List<Film> list =filmdao.selectFilmListByPage(BeginRow,rowPerPage);
	
	//마지막 페이지
	int totalRow=filmdao.totalRowDao();
	int lastPage = 0;
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
	}
	
	
	FilmDao fd = new FilmDao();
	int filmId=0;
	int storeId=0;
	if(request.getParameter("filmId")!=null && request.getParameter("storeId")!=null){
		filmId=Integer.parseInt(request.getParameter("filmId"));
		storeId = Integer.parseInt(request.getParameter("storeId"));
	}
	Map<String,Object> map = fd.filmInStockCall(filmId, storeId);
	
	List<Integer> stockList = (List<Integer>)(map.get("list"));
	int count = (Integer)map.get("count");
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<th>FID</th>
			<th>title</th>
			<th>description</th>
			<th>category</th>
			<th>price</th>
			<th>length</th>
			<th>rating</th>
			<th>actors</th>
		</thead>
		<tbody>
			
				<%
					for(Film f : list){
				%>
					<tr>
						<td><%=f.getFID() %></td>
						<td><%=f.getTitle() %></td>
						<td><%=f.getDescription() %></td>
						<td><%=f.getCategory() %></td>
						<td><%=f.getPrice() %></td>
						<td><%=f.getLength() %></td>
						<td><%=f.getRating() %></td>
						<td><%=f.getActors() %></td>
					</tr>
				<% 	
					}
				
				
				%>
		</tbody>
	</table>


	<%
			if(currentPage>1){
	%>
				<a href="<%=request.getContextPath() %>/filmList.jsp?currentPage=<%=currentPage-1%>">이전</a>
	<% 	
			}
	
	%>
	<%
			if(currentPage<lastPage){
	%>
			<a href="<%=request.getContextPath() %>/filmList.jsp?currentPage=<%=currentPage+1%>">다음</a>
	<% 	
		}

	%>


</body>
</html>