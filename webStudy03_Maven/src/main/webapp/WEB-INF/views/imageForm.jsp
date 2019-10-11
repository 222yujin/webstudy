<%@page import="java.util.Objects"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js">
	
</script>
<%
		String[] images = (String[]) request.getAttribute("images");
		String imageName = (String)request.getAttribute("imageName");
	%>
<script>
	$(function(){
		var imageArea = $("#imageArea");
		var pattern = '<img src="<%=request.getContextPath()%>/image.do?image=%V" />'
		$("[name='image']").on("change", function() {
			let imageName = $(this).val();
			imageArea.append(pattern.replace("%V", imageName));

		});

		imageArea.on("click", 'img', function() {
			$(this).remove();

		})
	$("[name='image']").val("<%=Objects.toString(imageName,"")%>");
		$("[name='image']").trigger("change");
	})
	

</script>

<style>
img {
	width: 100px;
	height: 100px;
}
</style>
</head>
<body>
	
	<form action="<%=request.getContextPath()%>/image.do">
		<select name="image">
			
			<%
				for (String name : images) {
			%>
			<option value=<%=name%>><%=name%></option>
			<%
				}
			%>


		</select>
	</form>
	<div id="imageArea">
	
	
	</div>
	
	
</body>
</html>