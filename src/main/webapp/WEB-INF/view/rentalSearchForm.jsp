<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="dao.*" %>
    <%@ page import="java.util.*" %>
    
<%
	StoreDao storedao = new StoreDao();
	List<Integer> storeIdList = storedao.selectStoreIdList();
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>대여 상세 검색</h1>
	<form action="<%=request.getContextPath()%>/rentalSearchController" method="post">
		<table class="table table-hover">
			<tr>
				<td>스토어 ID</td>
				
				<td>
					<%
						for(int i:storeIdList){
					%>
							<div><input type="radio" name="storeId" value="<%=i%>"> <%=i%>번 가게</div>
					<%
						}
					%>
				</td>
			</tr>
			<!--  고객 이름 검색 -->
			<tr>
				<td>고객 이름</td>
				<td>
					<input type="text" name="customerName">
				</td>
			</tr>
			<tr>
				<!-- 대여 일자 -->
				<td>대여 일자</td>
				<td>
					<input type="date" NAME="startDate"> ~ <input type="date" name="endDate">
				</td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit" class="btn btn-info">검색</button></td>
			</tr>
		</table>
	</form>
</body>
</html>