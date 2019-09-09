<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  name="logoutform" action="${pageContext.request.contextPath }/logout" method="post">

	</form>
	<%
		String authMember = (String) session.getAttribute("authMember");

		if (authMember == null) {
	%>
	<a href="<%=request.getContextPath()%>/login">로그인</a>
	<%
		} else {
	%>

	<%=authMember%>님
	<a href="#" onclick="document.logoutform.submit();">로그아웃</a>
	<%
		}
	%>
</body>
</html>