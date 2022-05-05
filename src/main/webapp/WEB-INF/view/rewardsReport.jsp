<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%
	
	List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
	
	int count =(Integer)request.getAttribute("count");
	


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>RewardsReport</h1>
	<form method="get" action="<%=request.getContextPath() %>/rewardsReportController">
		<table border="1">
			<tr>
				<td>최소 구매 횟수</td>
				<td><input type="text" name="purchases"></td>
			</tr>
			<tr>
				<td>최소 구매 가격</td>
				<td><input type="text" name="purchased"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">등록</button></td>
			</tr>
		</table>
		<table border="1">
			<thead>
				<tr>
					<th>customerId</th>
					<th>store_id</th>
					<th>first_name</th>
					<th>last_name</th>
					<th>email</th>
					<th>address_id</th>
					<th>active</th>
					<th>create_date</th>
					<th>last_update</th>
				</thead>
			<tbody>
				<%
					for(Map m : list){ 
				%>
						<tr>
							<td><%=m.get("customer_id") %></td>
							<td><%=m.get("store_id") %></td>
							<td><%=m.get("first_name") %></td>
							<td><%=m.get("last_name") %></td>
							<td><%=m.get("email") %></td>
							<td><%=m.get("address_id") %></td>
							<td><%=m.get("active") %></td>
							<td><%=m.get("create_date") %></td>
							<td><%=m.get("last_update") %></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</form>
</body>
</html>