<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(function() {
		$('select[name=sesMember]').on('change', function(event) {
			var form = $(this).parents("form")
			form.submit();
		})

	})
</script>
</head>
<body>
	<%
		Map<String, String[]> memberMap = new LinkedHashMap<>();
		memberMap.put("a001", new String[] { "바다", "/ses/bada.jsp" });
		memberMap.put("a002", new String[] { "유진", "/ses/yujin.jsp" });
		memberMap.put("a003", new String[] { "슈", "/ses/sue.jsp" });

		
		
	%>

	<form action="<%=request.getContextPath()%>/sesProcess">
		<select name="sesMember">

			<%
				for (String key : memberMap.keySet()) {
			%><option  value=<%=key%>><%=memberMap.get(key)[0]%></option>
			<%
				}
			%>

		</select>
	</form>


	<!-- 
A. memberMap을 이용해서 동적 option구성
B.submit버튼을 대신하여  option선택시 코드로 submit 이벤트를 trigger시킴
C. 전송후 서버사이드에서 sesMember 파라미터에 따라 다음 특성으로 개인페이지를 서비스함.

1.사용자는 멤버의 개인페이지에 대한 정보를 모름.
2.사용자는 모든 요청이 sesForm.jsp에 의해 처리되는걸로 착각함
3.각 멤버의 개인 페이지에서는 sesForm 으로 전달된 파라미터를 확인할 수 있도록 함 

4. 멤버의 개인페이지 결과 화면에서 다른 멤버 선택할 수 있도록

***

1.현재 요청에 포함된 모든 데이터를 삭제하고 도착지로 이동
2. 클라이언트는 최종적으로 멤버의 개인페이지의 위치를 인지할 수 있음-->
</body>
</html>