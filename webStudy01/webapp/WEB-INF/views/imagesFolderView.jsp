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
	<%=request.getContextPath()%>
	<%
		String[] imagefiles = (String[]) request.getAttribute("imagefiles");
		String[] targetfiles = (String[]) request.getAttribute("targetfiles");
	%>
	<form method="post">
		<select name="filename" required>
			<option value>이미지 선택</option>
			<%
				for (String name : imagefiles) {
			%>
			<option><%=name%></option>
			<%
				}
			%>


		</select> <br> <input type="radio" value="copy" name="command" required />복사
		<input type="radio" value="move" name="command" required /> 이동 <input
			type="radio" value="delete" name="command" required />삭제 <br> <input
			type="submit" value="명령처리" />
	</form>

	<ul>
		<%
			for (String file : targetfiles) {
		%><li><%=file%></li>
		<%
			}
		%>
	</ul>
</body>
</html>