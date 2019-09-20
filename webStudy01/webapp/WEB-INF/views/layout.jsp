
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" buffer="20kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"> </script>
 <script type="text/javascript">
      $.getContextPath = function(){
    	  return  "<%=request.getContextPath()%>";
      }
      </script>

</head>
<body>
	<div id="header">
		<jsp:include page="/includee/header.jsp" /> 
		<%-- <iframe src="<%=request.getContextPath()%>/includee/header.jsp"></iframe> --%>
	</div>
	<div id="leftMenu">
		<jsp:include page="/includee/left.jsp" />
		<%-- <iframe src="<%=request.getContextPath()%>/includee/left.jsp"></iframe> --%>
	</div>
	<div id="contents">
		<%
		String includePage = (String) request.getAttribute("page");
		if(StringUtils.isBlank(includePage)){
			%>
		1.server side 모듈화 : action 태그
		2.client side 모듈화 : iframe
		
		<%
		}else{
			//pageContext.include(includePage);
		
		%>
		<jsp:include page="<%=includePage %>" />
		<%
		}
		%>
		
	</div> 
	<div id="footer">
		 <jsp:include page="/includee/footer.jsp" /> 
		<%-- <iframe src="<%=request.getContextPath()%>/includee/footer.jsp"></iframe> --%>
	</div>
</body>
</html>