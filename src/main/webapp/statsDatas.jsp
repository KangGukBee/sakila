<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	statsDataDao statsdatadao = new statsDataDao();
	//요일별 매장 매출
	List<Map<String,Object>> list = statsdatadao.selectDayPay();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <table>
	 	<thead>
	 		<tr>
	 			<th></th>
	 			<th></th>
	 		</tr>
	 	</thead>
	 </table>
</body>
</html>