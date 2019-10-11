<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/mystyle.css">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4>웹 어플리케이션에서의 흐름제어(Flow Control)</h4>
<pre>
	Http의 특성 : Stateless(연결을 끊어서 정보를 남기지 않음)
		conntectless : 사용이 끝나면 연결 통로를 닫음(정보가 남지 않음)
		conntectFull : 반대	
		
	1. Request Dispatch(서버사이드 위임방식,요청분기)(C-A-B-C)
		: 서버사이드 내에서만 이동하는 위임처리 방식으로 원본 요청을 가지고 분기하는 방식.
		1) forward(C-A-B-C) : 분기한 이후 도착지에서만 응답 전송(완전한 위임)(B)
		2) include(C-A-B-A-C) : 분기한 도착지의 결과 데이터를 가지고 복귀(A+B)
		
	2. Redirect(클라이언트가 요청하면 서버가 바뀐주소를 알려준다,다시 그 주소로 요청)(C-A-C-B)
		: 최초의 요청에 대한 응답이 이동 전에 먼저 발생하고,
		  해당 응답에는 Body가 없는 대신, Line(302/307)과 Header(Location)가 응답으로 전송
		  최종적으로 클라이언트쪽에서 Location 방향으로 새로운 요청을 발생시켜서 이동.
		  
		<%
			//1. Request dispatch, forward
			String path ="/02/standard.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			//rd.forward(request, response);
			
			//2.Request dispatch, include
			//rd.include(request, response);
			
			//3.Redirect
			String location=request.getContextPath()+ path;
			response.sendRedirect(location);
		%>  
	  
</pre>
</body>
</html>