<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	//현재 페이지
	int currentPage =(Integer)request.getAttribute("currentPage");
	int lastPage=(Integer)request.getAttribute("lastPage");
	List<Map<String,Object>> amountList = (List<Map<String,Object>>)request.getAttribute("amountList");
	//임대료 별 영화 갯수
	List<Map<String,Object>> rateList=(List<Map<String,Object>>)request.getAttribute("rateList");
	//언어 별 영화 갯수
	List<Map<String,Object>> languageList= (List<Map<String,Object>>)request.getAttribute("languageList");

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
 		<a href="<%=request.getContextPath()%>/statsDataController?currentPage=<%=currentPage+1%>">다음</a>
 <% 
 	}
 %>
  <%
 	if(currentPage>1){
 %>
 		<a href="<%=request.getContextPath()%>/statsDataController?currentPage=<%=currentPage-1%>">이전</a>
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