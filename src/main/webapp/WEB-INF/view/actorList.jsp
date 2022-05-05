<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import ="dao.*" %>
<%@ page import="java.util.*" %>
<%
	int currentPage=(Integer)request.getAttribute("currentPage");
	List<ActorInfo> list = (List<ActorInfo>)request.getAttribute("list");
	int lastPage =(Integer)request.getAttribute("lastPage");



	System.out.println(currentPage+"actorList.jsp");
	System.out.println(lastPage+"actorList.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<Form method="post">
		<table class="table table-hover" >
			<thead>
				<tr>
					<th>ActorId</th>
					<th>Name</th>
					<th>filmInfo</th>
				</tr>	
			</thead>
			<tbody>
			<%
				for(ActorInfo m : list){
			%>
				<tr>
					<td><%=m.getActorId() %></td>
					<td><%=m.getName() %></td>
					<td><%=m.getFilmInfo()%></td>
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
			<a href="<%=request.getContextPath()%>/actorController?currentPage=<%=currentPage-1%>" class="btn btn-outline-secondary">이전</a>
	<%	
		}
	%>
	
	<%
		
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/actorController?currentPage=<%=currentPage+1%>" class="btn btn-outline-secondary">다음</a>	
	<%		
		}
	%>
</body>
</html>