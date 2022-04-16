<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	int currentPage=1; // 현재 페이지 = 1
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int RowPerPage = 10; //페이지 당 데이터 수
	int BeginRow= (currentPage-1)*RowPerPage; //데이터가 시작할 위치
	
	List<CustomerInfo> list= new ArrayList<>();
	customerInfoDao customerinfodao = new customerInfoDao();
	list= customerinfodao.SelectCustomerByListPage(BeginRow, RowPerPage);
	//마지막 페이지 
	int lastPage=0;
	int totalRow=customerinfodao.totalRowDao();
	
	if(totalRow%RowPerPage!=0){
		lastPage=totalRow/RowPerPage;
	}else{
		lastPage+=1;
	}
	

%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<meta charset="UTF-8">
<title>CUSTOMER LIST</title>
</head>
<body>
	<Form method="post">
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Address</th>
					<th>zipCode</th>
					<th>Phone</th>
					<th>City</th>
					<th>Country</th>
					<th>Notes</th>
					<th>SID</th>
				</tr>
			</thead>
			<tbody>
					<%
						for(CustomerInfo c : list){
							System.out.println(c.getID());
					%>	
						<tr>
							<td><%=c.getID() %></td>
							<td><%=c.getName() %></td>
							<td><%=c.getAddress() %></td>
							<td><%=c.getZipCode() %></td>
							<td><%=c.getPhone() %></td>
							<td><%=c.getCity()%></td>
							<td><%=c.getCountry() %></td>
							<td><%=c.getNotes() %></td>
							<td><%=c.getSID() %></td>
						</tr>
					<%
						}
					%>
			</tbody>
		
		</table>
	</Form>

	<%
		if(currentPage > 1) { 
	%>
			<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage-1%>" class="btn btn-outline-secondary">이전</a>
	<%	
		}
	%>
	
	<%
		
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-outline-secondary">다음</a>
	<%		
		}
	%>

</body>
</html>