<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String message = (String) session.getAttribute("message");
	//flash attribute
	if (StringUtils.isNotBlank(message)) {
		session.removeAttribute("message");
	%>
		<script type="text/javascript">
			alert("<%=message%>");
			
		</script>
	<% 
	}
	
	String saveId =(String) request.getAttribute("saveId");
%>
<body>
	<form method="post">
		<ul>
			<li>
			 아이디 : <input type="text" name="mem_id" value="<%=Objects.toString(saveId,"") %>">
			</li>
			<li>
			 비밀번호 : <input type="text" name="mem_pass">
			 <input type="checkbox" name="idSave" value="idSave" 
			 <%=StringUtils.isNotBlank(saveId)? "checked" : "" %>
			 />아이디기억하기
			 <input type="submit" value="로그인" />
			</li>
			
		</ul>
	</form>
</body>
</html>