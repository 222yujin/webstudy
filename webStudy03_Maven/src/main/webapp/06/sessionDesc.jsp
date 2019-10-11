<%@page import="java.util.Date"%>
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
	세션 : 
		- 시간 : 유저가 어플리케이션을 의미있게 사용하고 있는 기간(사용시작~종료,WEB)
		- 통로 : 한 세션내에서 두 피어사이에 설정된 유일한 통로(데이터베이스)	
	
	세션의 생명주기(lifecycle)
		시작 : 최초의 요청이 서버에 전달될때.
		 세션 프로퍼티들 : 생성시점,식별자(세션아이디),마지막 요청시점,
		 	생성시점 : <%=new Date(session.getCreationTime())%>
			세션 아이디 : <%=session.getId()%>
			마지막요청 시점 : <%=new Date(session.getLastAccessedTime())%>
			만료시간 : <%=session.getMaxInactiveInterval()%>s
			<%
			session.setMaxInactiveInterval(120);
			%>
			재설정후:<%=session.getMaxInactiveInterval()%>s
		
		<a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">세션 유지를 위한 링크</a>
	 한 세션내에서 발생하는 요청을 식별하는 방법
	 	session tracking mode
	 	1)Cookie : JSESSIONID 같은 세션을 식별자를 포함하는 쿠키를 전송하고 저장하고 재전송하는 방법으로 식별
	 	2)URL: JSESSIONID 세션 파라미터를 전송하여 세션을 식별(보안 취약성,URL rewriting 문제)
	 	3)SSL(Secure Socket Layer) : 두 피어 사이의 데이터가 암호화되어 전달되며, 암호문내에 세션 식별자 포함.
	 							   : 인증서가 필요함 (openSSL). 
	 
	 종료 : 
			1) 세션 만료시간(ex 30분) 이내에 새로운 요청이 발생하지 않을때.
			2) 브라우저 종료 : 만료시간이 지난 후 만료
			3) 특정 쿠키 삭제시 : 만료시간이 지난 후 만료
			4) 명시적인 로그아웃(invalidate) : 서버사이드코드
			
			<%
				session.invalidate();//세션안에 저장되어있던 거 다 지움(아ㅏ이디 계속 바뀜 서버에서 계속 만료 시켜서)
			%>
		

	
	
	</pre>
</body>
</html>