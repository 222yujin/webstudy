<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	필요한 구구단의 최소단과 최대단 입력
	<!-- method="post"를 사용하면 url에 클라이언트의 정보가 출력되지않는다 -->
	<form action="<%=request.getContextPath() %>/module/layout.do" method="post">
		<!-- form 서버로 데이터를 보내기위한 유일한,
			클라이언트가 submit를 누르는 순간 서버로 데이터가 간다.action입력데이터의 처리자-->
		 <input type="number" name="minDan" />
		 <input type="number" name="maxDan" />
		 <input type="hidden" name="command" value="gugudanProcess" />
		 <input type="submit" value="전송" />
	</form>
</body>
</html>