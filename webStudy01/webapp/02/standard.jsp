<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
	<h4>JSP 표준 구성 요소</h4>
		<div style="background-color:yellow;">
	<%=pageContext.getAttribute("pageAttr") %><br>
	<%=pageContext.getAttribute("requestAttr",pageContext.REQUEST_SCOPE) %><br>
	<%=session.getAttribute("sessionAttr") %><br>
	<%=application.getAttribute("applicationAttr") %><br>
	</div>
	<pre>
		1. 정적 텍스트 : 텍스트,html,css,javascript  클라이언트에서 동작
		2. JSP 스크립트 요소 : <% %>
				1) directive(지시자) :&lt;%@ 지시자명 속성들... %&gt;
					- page(required) : JSP 페이지에 대한 설정(전처리) 정보를 명시
					- include(optional) : 정적 내포
					- taglib(optional) : 태그 라이브러리를 사용할때.
				2) scriptlet(스크립틀릿요소) : <% //java code%>
				<%
					System.out.println("어디에 출력?");
					String today = new Date().toString();
				%>
				3) expression(표현식) : <%=today%>
									: 응답 데이터로 출력할 요소에 사용.
				4) declaration(선언부)  : 전역변수나 메소드 선언에 사용.
				 <%! 
				 	public Date globalToday = new Date();
					public void test(){};
				 %>
				5) comment(주석) : <%-- --%>
					- client side : HTML,JavaScript
					- server side : Java,JSP
					<!-- html주석 -->
					
					<script >
					//스크립트주석
					</script>
					
					<% //자바주석 %>
					
					<%-- <% jsp주석%> --%>
					
					<%-- <%
					if(1==1)
					throw new NullPointerException("강제 발생 예외");
					%>
			 --%>
		3. 기본 객체(9개)
		4. jsp 액션태그
		5. EL(표현언어)		
		6. JSTL	
	</pre>
</body>
</html>