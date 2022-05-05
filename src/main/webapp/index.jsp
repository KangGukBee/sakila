<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>INDEX</h1>
	<ol>
		<li><a href="<%=request.getContextPath()%>/storeListController">storeList</a></li>
		<li><a href="<%=request.getContextPath()%>/staffListController">staffList</a></li>
		<!-- view 7개 리스트 -->
		<li><a href="<%=request.getContextPath()%>/actorController">actorInfoList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/customerController">customerList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/filmListController">filmList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/nicerButSlowerFilmListController">nicerButSlowerFilmList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/salesByFilmCategoryControlelr">salesByFilmCategory(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/filmNotInStockController">filmNotInStockController(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/rewardsReportController">rewardsReportController(view)</a></li>
		<!--검색 페이지 -->
		<li><a href="<%=request.getContextPath()%>/filmSearchAction.jsp">필름 상세 검색</a>
		<li><a href="<%=request.getContextPath() %>/rentalSearchController">대여 상세 검색</a></li>
	</ol>
	
	<h1>통계 데이터</h1>
</body>
</html>
