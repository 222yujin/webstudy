r<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/mystyle.css">
<title>06/sessionDesc.jsp</title>
</head>
<body>
	<h4>HttpSession</h4>
	<pre>
	생성시점 : <%=new Date(session.getCreationTime())%>
	세션 아이디 : <%=session.getId()%>
	세션의 라이프 사이클에 대한 수업.
	</pre>
</body>
</html>