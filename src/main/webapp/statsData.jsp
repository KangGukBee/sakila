<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	//현재 페이지
	int currentPage =1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int rowPerPage=10; // 페이지당 데이터 갯수
	int BeginPage=(currentPage-1)*rowPerPage; // 시작 데이터 위치
	statsDataDao statsdatadao = new statsDataDao();
	//고객별 총액
	List<Map<String,Object>> amountList = statsdatadao.amountByCustomer(BeginPage,rowPerPage);
	//임대료 별 영화 갯수
	List<Map<String,Object>> rateList=statsdatadao.selectRate();
	//언어 별 영화 갯수
	List<Map<String,Object>> languageList=statsdatadao.selectLanguage();
	//마지막 페이지 
	int lastPage=0;
	int totalRow = statsdatadao.totalRowDao();
	
	if(totalRow%rowPerPage !=0){
		lastPage=totalRow/rowPerPage;
	}else{
		lastPage+=1;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통계 데이터</title>
</head>
<body>
<!--고객별 총액 -->
 <table border="1">
 	<h1>고객 별 총 액수</h1>
	<thead>
	 	<tr>
	 		<td>CustomerId</td>
	 		<td>name</td>
	 		<td>total</td>
	 	</tr>
	 </thead>
	 <tbody>
	 	<%
	 		for(Map m : amountList){
	 	%>
	 			<tr>
	 				<td><%=m.get("customerId") %></td>
	 				<td><%=m.get("name") %></td>
	 				<td><%=m.get("total") %></td>
	 			</tr>
	 	<% 	
	 		}
	 	%>
	 </tbody>
 </table>
 
 <%
 	if(lastPage>currentPage){
 %>
 		<a href="<%=request.getContextPath()%>/statsData.jsp?currentPage=<%=currentPage+1%>">다음</a>
 <% 
 	}
 %>
  <%
 	if(currentPage>1){
 %>
 		<a href="<%=request.getContextPath()%>/statsData.jsp?currentPage=<%=currentPage-1%>">이전</a>
 <% 
 	}
 %>
 <!--임대료 별 영화 갯수 -->
 <table border="1">
 	<h1>고객 별 총 영화 갯수</h1>
 	<thead>
 		<tr>
 			<th>Rate</th>
 			<th>Count</th>
 		</tr>
 	</thead>
 	<tbody>
 		<%
 			for(Map r : rateList){ 
	 	%>
 			<tr>
	 			<td><%=r.get("rate") %></td>
	 			<td><%=r.get("cnt") %></td>
	 		</tr>
	 	<% 
 			}
	 	%>
 	</tbody>
 </table>
 <!--언어 별 영화 갯수 -->
 <table border="1">
 	<h1>언어 별 총 영화 갯수</h1>
 	<thead>
 		<tr>
 			<th>Language Name</th>
 			<th>Count</th>
 		</tr>
 	</thead>
 	<tbody>
 		<%
 			for(Map l : languageList){ 
	 	%>
 			<tr>
	 			<td><%=l.get("name") %></td>
	 			<td><%=l.get("cnt")%></td>
	 		</tr>
	 	<% 
 			}
	 	%>
 </tbody>
 </table>
</body>
</html>