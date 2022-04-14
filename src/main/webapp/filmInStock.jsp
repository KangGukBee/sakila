<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	
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

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FilmInStock</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/filmInStock.jsp" method="post">
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