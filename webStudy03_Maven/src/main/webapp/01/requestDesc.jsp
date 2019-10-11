<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/requestDesc.jsp</title>
<script type="text/javascript">
	console.log("test");
	//console.log(request);
</script>
</head>
<body>
	<h4>HttpServletRequest 의 메소드</h4>
	<pre>
		characterEncoding : <%=request.getCharacterEncoding()%>
		ContentLength : <%=request.getContentLength()%>
		ContentType : <%=request.getContentType()%> : body message mime
		ContextPath : <%=request.getContextPath()%> **** client side 절대 경로에 사용
		RequestURI : <%=request.getRequestURI()%>
		
		Server Info
		LocalAddr : <%=request.getLocalAddr()%> 
		LocalName : <%=request.getLocalName()%> 
		LocalPort : <%=request.getLocalPort()%>
		
		Client Info
		RemoteAddr : <%=request.getRemoteAddr()%>
		RemoteHost : <%=request.getRemoteHost()%>
		RemotePort : <%=request.getRemotePort()%>
		QueryString : <%=request.getQueryString()%>
		ServerName : <%=request.getServerName()%>
		ServerPort : <%=request.getServerPort()%>
		ServletContext hashCode : <%=request.getServletContext().hashCode()%>
		ServletContext ContextPath : <%=request.getServletContext().getContextPath()%>
	</pre>
	<!--  <img src="../images/Jellyfish.jpg"> -->
	<img src="<%=request.getContextPath()%>/images/Jellyfish.jpg">

</body>
</html>