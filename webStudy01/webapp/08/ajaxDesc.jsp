<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"> </script>
<title>Insert title here</title>
</head>
<body>
	<pre>
	클라이언트 현재 시간 : <span id="client"></span>
	서버 현재 시간 : <span id="server"></span>
</pre>
	<script type="text/javascript">
	var client = document.querySelector('#client');
	var server = document.querySelector('#server');
	setInterval(() => {
		var now = new Date();
		client.innerHTML =now;
		
	$.ajax({
		url:"getServerTime.jsp",
		//method:"get", 기본이라 생략 가능
		//data:"", get이라 굳이 줄 것이 없음 있다면 "param=value&param2=value2"
		dataType:"json",	//Accept:application/json ==Content-Type :application/json 
		success:function(resp){
			server.innerHTML=resp.time;
		},
		error:function(errorResp){
			console.log(errorResp.status);
		}
	});
	}, 1000);
	
	
</script>
</body>
</html>