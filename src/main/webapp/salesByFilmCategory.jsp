<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%	
	//현재 페이지 
	int currentPage=1;
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	int rowPerPage = 10; // 페이지 당 데이터
	int BeginRow= (currentPage-1) *rowPerPage;//시작 데이터 위치
	Salesbyfilmcategory sales =new Salesbyfilmcategory();
	List<salesbyfilmcategory> list = sales.selectSales(BeginRow,rowPerPage);
	
	//totalRow
	int	totalRow   = sales.totalRowDao();
	//마지막 페이지
	int lastPage = 0;
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
	}
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<table border="1">
			<thead>
				<tr>
					<th>store</th>
					<th>manager</th>
					<th>total_sales</th>
				</tr>
			</thead>
			<tbody>
				<% 
					for(salesbyfilmcategory s : list){
						
					
				%>
						<tr>
							<td><%=s.getStore() %></td>
							<td><%=s.getManager() %></td>
							<td><%=s.getTotalSales() %></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</form>
</body>
</html>