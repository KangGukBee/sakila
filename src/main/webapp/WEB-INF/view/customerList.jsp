<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	int currentPage=(Integer)request.getAttribute("currentPage");
	int lastPage=(Integer)request.getAttribute("lastPage");
	List<CustomerInfo> list=(List<CustomerInfo>)request.getAttribute("list");

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
		<h1>고객 리스트</h1>
		<table class="table table-hover">
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
			<a href="<%=request.getContextPath()%>/customerController?currentPage=<%=currentPage-1%>" class="btn btn-outline-secondary">이전</a>
	<%	
		}
	%>
	
	<%
		
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/customerController?currentPage=<%=currentPage+1%>" class="btn btn-outline-secondary">다음</a>
	<%		
		}
	%>

</body>
</html>