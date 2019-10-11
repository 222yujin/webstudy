<%@page import="kr.or.ddit.servlet01.CalculateServlet.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
과제명 : 사칙연산기
1.homework 컨텍스트로 프로젝트 생성
2. homework에서 작업 
3. 연산의 결과는 plain 으로. 33*4=132 -->
	<form action="<%=request.getContextPath() %>/calculator" method="post">
	연산을 수행하는 클라이언트의 이름 : <input type="text" name="name" />
	<br />
		<input type="number" name="leftOp" />
		 <select name="operator">
		 <option value>연산자 선택</option>
		 <%
		 	for(OperatorType tmp:OperatorType.values()){
		 		%>
		 		<option value="<%=tmp.name()%>"><%=tmp.getSign() %></option>
		 		<% 
		 	}
		 %>
			
		</select> 
		<input type="number" name="rightOp" />
		<button type="submit">=</button>
	</form>

</body>
</html>