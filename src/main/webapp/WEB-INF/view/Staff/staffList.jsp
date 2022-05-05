<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	List<Map<String,Object>> list =(List<Map<String,Object>>)request.getAttribute("list");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>staffId</th> <!-- 매니저 번호 -->
				<th>Name</th>  <!-- 이름 -->
				<th>Address</th> <!-- 주소 -->
				<th>city</th> <!-- 사는 도시 -->
				<th>country</th> <!--  사는 국가 -->
				<th>Email</th> <!-- 이메일 주소 -->
				<th>phone</th> <!--  핸드폰 번호 -->
				<th>storeId</th>	 <!--  근무하는 가게 -->
			</tr>
		</thead>
		<tbody>
			<%
				for(Map m : list){
			%>
				<tr>
					<td><%=m.get("staffId")%></td>
					<td><%=m.get("Name")%></td>
					<td><%=m.get("address")%></td>
					<td><%=m.get("city")%></td>
					<td><%=m.get("country")%></td>
					<td><%=m.get("phone") %></td>
					<td><%=m.get("email") %></td>
					<td><%=m.get("storeId")%></td>
				</tr>
			<% 
				}
			
			%>		
		</tbody>
	</table>
</body>
</html> 