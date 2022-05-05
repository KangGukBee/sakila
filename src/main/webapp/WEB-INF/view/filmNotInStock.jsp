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
<title>FilmNotInStock</title>
</head>
<body>
	<Form method="get" action="<%=request.getContextPath()%>/filmNotInStockController">
		<h1>재고 현황</h1>
		<table border="1">
			<tr>
				<td>FilmID:</td>
				<td><input type="number" name="filmId"></td>
			</tr>
			<tr>
				<td>StoreID:</td>
				<td><input type="number" name="storeId"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">검색</button>
				 </td>
			</tr>
		</table>
	</Form>
	<table class="table table-hover">
			<%
			
			if(request.getParameter("filmId")!=null && request.getParameter("storeId")!=null){

			%>
					<tr>
						<td><%=storeId%>번 가게의 <%=filmId %>번 영화의 팔린 재고는 <%=count%>개 입니다.</td>
					</tr>
					<%
						for(int i : list){
					%>
							<tr>
								<td><%=i%></td>
							</tr>
					<% 
						}
					%>
			<% 
				}
			
			%>
		</table>

</body>
</html>