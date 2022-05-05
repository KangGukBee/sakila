<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	int filmId=(Integer)request.getAttribute("filmId");
	int storeId=(Integer)request.getAttribute("storeId");
	List<Integer> list =(List<Integer>)request.getAttribute("list");
	int count=(Integer)request.getAttribute("count");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FilmInStock</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/filmInStockController" method="get">
		<h1>FilmInStock</h1>
		<table border="1">
			<tr>
					<td>FilmId : </td>
					<td><input type="number" name="filmId"></td>
			</tr>
			<tr>
					<td>StoreId : </td>
					<td><input type="number" name="storeId"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">검색</button></td>
			</tr>
		</table>
	</form>
	<table border="1">
	
			
			<tr>
				<td><%=storeId%>번 가게의 <%=filmId%>번 영화의 갯수는 <%=count %>개 입니다.<td>
			</tr>
			
			<% 
				
				for(int id : list){
			%>
					<tr>
						<td><%=id %></td>
					</tr>
			<%
				}
			%>
		
	
	
	</table>
</body>
</html>