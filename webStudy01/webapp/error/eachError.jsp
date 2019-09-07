<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>에러를 처리할 목적의 페이지</h4>
	<pre>
	<%
		ErrorData ed = pageContext.getErrorData();
	%>
	<%=exception.getMessage()%>
	에러 발생 위치 : <%=ed.getRequestURI()%>
	에러 상태 코드 : <%=ed.getStatusCode()%>
	에러 :<%=ed.getThrowable()%>
	
	나 오늘 집 가서 백순대볶음 먹을거야,,
	맥주도 먹어야지 난 어른이니깐!
	언니랑 같이 먹으면 언니는 매운거 좋아하니깐 청양고추도 썰어줘야겠다..!
	그리고 강아지 산책도 가야지,,,, 오늘 집에 일찍 가고 싶다 진짜 한번만,,!
	아악 뭐라고 써야하지 흑흑 배고파... 아니 배는 안 고픈데...그냥,, 
	근데 진짜 웃긴게 나 진짜 이제 안 먹고 싶어 백순대 왜지?!?!
	수요일까지는 미친듯이 먹고 싶었는데
	막상 시키니깐 안 먹고 싶음 ㅋ 개 짜증
	그리고 어제 꼬막 삶은거 뭔가 좀 마음에 안 들어서 화가 난다!!!!!!!!!
</pre>
</body>
</html>