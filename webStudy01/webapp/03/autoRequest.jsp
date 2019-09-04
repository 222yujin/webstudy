<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/mystyle.css">
<meta charset="UTF-8">
<%-- <meta http-equiv="Refresh" content="10; url=https://www.naver.com"> --%>
<title>03/autoRequest.jsp</title>
</head>
<body>
	<h4>자동 요청을 발생시키는 방법</h4>
	
	<span id="secArea">10</span>초 뒤에 네이버로 이동함
	<pre>
		1. 서버사이드 방식 : response header 중 Refresh 사용
		
		<%
			//response.setIntHeader("Refresh", 1);
			Date now = new Date();
		%>
		<%=now %>
		2. 클라이언트 사이드 방식
			1) HTML : meta 태그 이용.
			2) Javascript :setInterval/setTimeout,location.reload()
	</pre>
	<script type="text/javascript">
		/* var span = document.querySelector("#secArea");
		var seconds = 10;
		var jobId=setInterval(()=> {
			span.innerHTML = --seconds;
			if(seconds==0)clearInterval(jobId);
		}, 1000); */
		var test = 23;
		 setTimeout(function(){
			 test2 = 89;
	          //location.reload();
	          //window.history.back();
	          window.history.go(1);
	         },1000);

	</script>
</body>
</html>














