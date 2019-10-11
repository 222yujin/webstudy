
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<c:set var="message"></c:set>

<c:if test="${not empty sessionScope.message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>



<body>
	<form method="post">
	<c:set var="saveId" value="${cookie.idCookie.value }"></c:set>
		<ul>
			<li>
			 아이디 : <input type="text" name="mem_id" value="${saveId }">
			</li>											
			<li>
			 비밀번호 : <input type="text" name="mem_pass">
			 <input type="checkbox" name="idSave" value="idSave" 
			 ${not empty saveId ? "checked" : "" }
			 />아이디기억하기
			 <input type="submit" value="로그인" />
			</li>
			
		</ul>
	</form>
</body>
</html>