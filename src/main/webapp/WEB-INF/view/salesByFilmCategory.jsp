<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%	
	List<salesbyfilmcategory> list =(List<salesbyfilmcategory>)request.getAttribute("list");
	int currentPage=(Integer)request.getAttribute("curretPage");
	int lastPage=(Integer)request.getAttribute("lastPage");
	
	
	
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