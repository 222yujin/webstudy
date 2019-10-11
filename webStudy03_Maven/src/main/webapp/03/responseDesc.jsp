<%@ page language="java"
	pageEncoding="UTF-8"%>
<%
	//response.setContentType("text/plain");
	response.setHeader("contentType", "text/html");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/mystyle.css">
<title>03/responseDesc.jsp</title>
</head>
<body>
	<h4>Http 프로토콜의 응답 데이터 패키징 방식</h4>
	<pre>
	1. Response Line : Status code(응답 상태 코드) Protocol
		** status code: 명령에 처리 결과를 의미하는 숫자 체계
		100 (since 1.1) : ING....(websocket)
			http : stateless(무상태, 비상태유지)
		200 : OK(success)
		300 : 완전한 처리를 위해 사용자의 추가 액션이 필요한 상황
			304(Not Modified),302/307(Moved)
		400 : client side fail ,
			400(Bad Request),404(Not Found),405(Method Not Allowed),415(Media Not Supported)
			403(Forbidden),401(UnAuthorized)
		500 : server side fail , 500(Internal Server Error) 
		
		HttpServletResponse.setStatus/sendError(**)
		
<!-- 		1xx (정보): 요청을 받았으며 프로세스를 계속한다
			2xx (성공): 요청을 성공적으로 받았으며 인식했고 수용하였다
			3xx (리다이렉션): 요청 완료를 위해 추가 작업 조치가 필요하다
			4xx (클라이언트 오류): 요청의 문법이 잘못되었거나 요청을 처리할 수 없다
			5xx (서버 오류): 서버가 명백히 유효한 요청에 대해 충족을 실패했다 -->
 	2. Response Header :response body 의 데이터에 대한 메타 데이터.
 		1) MIME 변경 : Content-Type
 			- page 지시자의 contentType 속성
 			- response.setContentType
 			- response.setHeader
 		2) 캐시 제어 : Pragma(1.0), Cache-Control(1.1), Expires(all)
 		<%
 			response.setHeader("Pragma", "no-cache");
 			response.setHeader("Cache-Control", "no-cache");
 			response.addHeader("Cache-Control", "no-store");
 			response.setDateHeader("Expires", 0);
 		%>
 			
 		3) auto request : Refresh
 			: <a href="<%=request.getContextPath() %>/03/autoRequest.jsp">/03/autoRequest.jsp 참고</a>
 		4) *** 페이지 이동 : Location, 302/307
 			<a href="${pageContext.request.contextPath}/05/flowControl.jsp">/05/flowControl.jsp 참고</a>
 	
	3. Response Body(message Body)
	</pre>
	<img src="<%=request.getContextPath() %>/images/Jellyfish.jpg"/>
</body>
</html>