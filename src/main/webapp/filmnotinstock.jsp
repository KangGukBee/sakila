<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	int filmId= 0;
	int storeId=0;
	
	if(request.getParameter("filmId")!=null && request.getParameter("storeId")!=null){
		 filmId= Integer.parseInt(request.getParameter("filmId"));
		 storeId=Integer.parseInt(request.getParameter("storeId"));
	}
	
	Map<String,Object> map = new HashMap<String,Object>();
	List<Integer> list = new ArrayList<Integer>();
	
	filmNotInStock filmnotinstock = new filmNotInStock();
	
	map= filmnotinstock.callFilmNot(filmId, storeId);
	
	list = (List<Integer>)(map.get("list"));
	int count = (Integer)(map.get("count"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FilmNotInStock</title>
</head>
<body>
	<Form method="post" action="<%=request.getContextPath()%>/filmnotinstock.jsp">
		<table border="1">
			<tr>
				<td>FilmID:</td>
				<td><input type="number" name="filmId"></td>
			</tr>
			<tr>
				<td>StoreID:</td>
				<td><input type="number" name="storeId"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">검색</button>
				 </td>
			</tr>
			<%
			
			if(request.getParameter("filmId")!=null && request.getParameter("storeId")!=null){

			%>
					<tr>
						<td><%=storeId%>번 가게의 <%=filmId %>번 영화의 팔린 재고는 <%=count%>개 입니다.</td>
					</tr>
					<%
						for(int i : list){
					%>
							<tr>
								<td><%=i%></td>
							</tr>
					<% 
						}
					%>
			<% 
				}
			
			%>
		</table>

	</Form>
</body>
</html>