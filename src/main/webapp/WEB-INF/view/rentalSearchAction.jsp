<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	int currentPage=(Integer)request.getAttribute("currentPage");
	int lastPage=(Integer)request.getAttribute("lastPage");
	List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
	int storeId=(Integer)request.getAttribute("storeId");
	String customerName=(String)request.getAttribute("customerName");
	String startDate=(String)request.getAttribute("startDate");
	String endDate=(String)request.getAttribute("endDate");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>검색 결과 리스트</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Rental_ID</th>
				<th>Rental_Date</th>
				<th>Inventroy_id</th>
				<th>Customer_id</th>
				<th>Return_Date</th>
				<th>Staff_id</th>
				<th>Last_Update</th>
				<th>CustomerName</th>
				<th>Store_Id</th>
				<th>Film_ID</th>
				<th>Title</th>
			</tr>
		</thead>
		<tbody>
		<%
			for(Map<String,Object> m : list){
		%>
				<tr>
					<td><%=m.get("rental_id") %></td>
					<td><%=m.get("rental_date") %></td>
					<td><%=m.get("inventory_id") %></td>
					<td><%=m.get("customer_id") %></td>
					<td><%=m.get("return_date") %></td>
					<td><%=m.get("staff_id") %></td>
					<td><%=m.get("last_update") %></td>
					<td><%=m.get("customerName") %></td>
					<td><%=m.get("storeId") %></td>
					<td><%=m.get("filmId") %></td>
					<td><%=m.get("title") %></td>
				</tr>	
		<% 
			}
		%>
			</tbody>
	</table>
	<%
		if(currentPage > 1) { 
	%>
			<a href="<%=request.getContextPath()%>/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&startDate=<%=startDate%>&endDate=<%=endDate%>" class="btn btn-outline-secondary">이전</a>
	<%	
		}
	%>
	
	<%
		
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&startDate=<%=startDate%>&endDate=<%=endDate%>" class="btn btn-outline-secondary">다음</a>
	<%		
		}
	%>
</body>
</html>