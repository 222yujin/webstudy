<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" buffer="10kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/mystyle.css">
<title>05/bufferDesc.jsp</title>
</head>
<body>
	<h4>버퍼(Buffer)</h4>
	<pre>
	: 두 피어 사이에서 발생하는 속도 차이를 보완하기 위한 임시 저장 영역.
	 JSP 웹 모듈에서 기본 버퍼의 크기 :8kb
	 버퍼를 제어하고 상태를 확인할때 사용하는 JspWriter out.
	 
	 버퍼의 크기 : <%=out.getBufferSize() %>bytes
	 잔여 버퍼의 크기 : <%=out.getRemaining() %>bytes
	 
	 
	 ** 주의 점
	         : 버퍼를 방출하기 전까지는 상태코드나 메타데이터를 변경할 수 있다.
                 버퍼가 방출된 이후에는 forward 이동이나 예외에 대한 처리가 불가능하다
	 
	 <%
	 	for(int i=0; i<=100; i++){
	 		out.println(i+"번째 반복");
	 		if(!out.isAutoFlush() && out.getRemaining()<20){
	 			out.flush();
	 		}
	 		
	 		if(i==98){
	 			String path="/02/standard.jsp";
	 			RequestDispatcher rd= request.getRequestDispatcher(path);
	 			rd.include(request, response);
	 		}
	 	}
	 %>
</pre>
</body>
</html>