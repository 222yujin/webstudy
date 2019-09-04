<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
	<%
		String[] images =(String[]) request.getAttribute("images");
	%>
	<form action="<%=request.getContextPath()%>/image.do">
		<select name="image" onchange="document.forms[0].submit();">
			<option>이미지 선택</option>
			<%
				for (String name : images) {
			%>
			<option value=<%=name %> ><%=name%></option>
			<%
				}
			%>


		</select>
	</form>
</body>
</html>