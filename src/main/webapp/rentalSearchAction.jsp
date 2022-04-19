<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	int currentPage=1; // 현재페이지
	if(request.getParameter("currentPage") != null) {  // 이전 , 다음 버튼을 이용하여 접속
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+ "현재 페이지");
	
	int rowPerPage=1;
	int beginRow=(currentPage-1) *rowPerPage;
	
	int storeId=Integer.parseInt(request.getParameter("storeId"));
	String customerName = request.getParameter("customerName");
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");


	
	//
	RentalDao rentaldao= new RentalDao();
	List<Map<String,Object>> list = rentaldao.selectReantalSearchList(beginRow,rowPerPage,storeId, customerName, startDate, endDate);
	
	//마지막 페이지
	int lastPage=0;
	int totalRow = rentaldao.totalRowDao(storeId, customerName, startDate, endDate);
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
	}
	
	
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