<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	List<nicerbutslowerfilmList> list = new ArrayList<>();
	//1페이지
	int currentPage=1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int rowPerPage=10;
	int BeginRow=(currentPage-1)*rowPerPage;
	
	nicerButSlowerFilmList slower = new nicerButSlowerFilmList();
	list = nicerButSlowerFilmList.SelectByNicer(BeginRow,rowPerPage);
	
	//마지막 페이지
	int lastPage=0;
	int totalRow=slower.totalRow();
	
	if(totalRow%rowPerPage!=0){
		lastPage=totalRow/rowPerPage;
	}else{
		lastPage+=1;
	}
	
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
		<table border="1">
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
						<td></td>
						<td></td>
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
			<a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage-1%>" class="btn btn-outline-secondary">이전</a>
	<%	
		}
	%>
	
	<%
		
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-outline-secondary">다음</a>
	<%		
		}
	%>
</body>
</html>