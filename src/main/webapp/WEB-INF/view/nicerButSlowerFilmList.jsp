<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	List<nicerbutslowerfilmList> list=(List<nicerbutslowerfilmList>)request.getAttribute("list");
	int currentPage=(Integer)request.getAttribute("currentPage");
	int lastPage=(Integer)request.getAttribute("lastPage");
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<Form method="post">
		<h1>Best 판매 내역</h1>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>FID</th>
					<th>Title</th>
					<th>Description</th>
					<th>Category</th>
					<th>Price</th>
					<th>Length</th>
					<th>Rating</th>
					<th>Actors</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(nicerbutslowerfilmList n : list){
				%>
					<tr>
						<td><%=n.getFid() %></td>
						<td><%=n.getTitle() %></td>
						<td><%=n.getDescription() %></td>
						<td><%=n.getCategory() %></td>
						<td><%=n.getPrice() %></td>
						<td><%=n.getLength() %></td>
						<td><%=n.getRating() %></td>
						<td><%=n.getActors() %></td>
					</tr>
				<% 
					}
				%>
			</tbody>
		</table>
	</Form>
	<%
		if(currentPage > 1) { 
	%>
			<a href="<%=request.getContextPath()%>/nicerButSlowerFilmListController?currentPage=<%=currentPage-1%>" class="btn btn-outline-secondary">이전</a>
	<%	
		}
	%>
	
	<%
		
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/nicerButSlowerFilmListController?currentPage=<%=currentPage+1%>" class="btn btn-outline-secondary">다음</a>
	<%		
		}
	%>
</body>
</html>