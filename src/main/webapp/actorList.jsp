<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import ="dao.*" %>
<%@ page import="java.util.*" %>
<%
	
	int currentPage = 1; // 현재페이지
	if(request.getParameter("currentPage") != null) {  // 이전 , 다음 버튼을 이용하여 접속
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+ "현재 페이지");
	

	int rowPerPage = 10; // 페이지 당 데이터 갯수
	int beginRow = (currentPage-1)*rowPerPage; // 시작할 데이터 번호
	
	//Actorinfo.vo 데이터
	ActorInfoDao actorinfodao= new ActorInfoDao();
	List<ActorInfo> list = new ArrayList<>();
	list=actorinfodao.selectActorInfoListByPage(beginRow, rowPerPage);
	
	//totalRow
	int	totalRow   = actorinfodao.totalRowDao();
	//마지막 페이지
	int lastPage = 0;
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
	}

%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<Form method="post" action="<%=request.getContextPath() %>/actorOne">
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
			<a href="<%=request.getContextPath()%>/Actor/actorList.jsp?currentPage=<%=currentPage-1%>" class="btn btn-outline-secondary">이전</a>
	<%	
		}
	%>
	
	<%
		
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/Actor/actorList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-outline-secondary">다음</a>	
	<%		
		}
	%>
</body>
</html>