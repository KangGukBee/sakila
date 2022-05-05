<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%
	int currentPage=(Integer)request.getAttribute("currentPage");
	int lastPage=(Integer)request.getAttribute("lastPage");
	List<Film> list =(List<Film>)request.getAttribute("list");
	
	
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
		if(currentPage > 1) { 
	%>
			<a href="<%=request.getContextPath()%>/filmListController?currentPage=<%=currentPage-1%>" class="btn btn-outline-secondary">이전</a>
	<%	
		}
	%>
	
	<%
		
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/filmListController?currentPage=<%=currentPage+1%>" class="btn btn-outline-secondary">다음</a>	
	<%		
		}
	%>


</body>
</html>