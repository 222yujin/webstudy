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
	String uri = "/images";
	String path = application.getRealPath(uri);
	File file1 = new File(path);

	String[] list = file1.list((dir, name) -> {
		return name.endsWith(".jpg");
	});


%>

<%=path %>
	<form method="post">
		<select name="filename">
			<%
				for (String name : list) {
			%><option>
				<%=name%>
			</option>
			<%
				}
			%>


		</select> 
		<br>
		<input type="radio" value="copy" name="command" />copy 
		<input type="radio" value="move" name="command" /> move
		<input type="radio" value="delete" name="command" />delete
		<br>
		<input type="submit" value="명령처리"/>
	</form>
</body>
</html>