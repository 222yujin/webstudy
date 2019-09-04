<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/mystyle.css">
<title>Insert title here</title>
</head>
<body>
	<h4>PageContext 기본객체</h4>
	<pre>
	: 하나의 JSP 페이지에 대한 모든 정보를 가진 객체
	
	1. 나머지 8개의 기본 객체를 확보
		<%=request == pageContext.getRequest()%>
		<%=response == pageContext.getResponse()%>
		<%=request.getContextPath()%> ${pageContext.request.contextPath}
		
	2. Scope를 접근할때
		<%
 	//request.setAttribute("attr", "value"); 
 	pageContext.setAttribute("attr", "value", pageContext.REQUEST_SCOPE);
 %>
		<%=request.getAttribute("attr")%>
		<%=pageContext.getAttribute("attr", pageContext.REQUEST_SCOPE)%>
		
	3. 흐름제어(flow Control) : request dispatch 방식
		<%
			String path = "/02/standard.jsp";
			//RequestDispatcher rd = request.getRequestDispatcher(path);
			//rd.forward(request, response); 
			//pageContext.forward(path);
			//rd.include(request, response);
			//pageContext.include(path); //얘는 중간에 요청 들어오면 출력하다가 그거 출력하고 다시 하던거 함
		%>
	4. 에러데이터 확보 
</pre>
</body>
</html>