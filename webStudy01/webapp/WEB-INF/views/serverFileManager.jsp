<%@page import="kr.or.ddit.enums.CommandType"%>
<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.servlet03.FileWrapper"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
li.active {
	background-color: blue;
}
</style>
</head>
<body>
	<%
		List<FileWrapper> leftFiles = (List) request.getAttribute("leftFiles");
		List<FileWrapper> rightFiles = (List) request.getAttribute("rightFiles");
		List<FileWrapper> srcFiles = (List) request.getAttribute("srcFiles");

		String leftSrc = request.getParameter("leftSrc");
		String rightTarget = request.getParameter("rightTarget");
	%>
	<form id="serverFileForm">
		<input type="text" readonly name="leftSrc" id="leftSrc"
			value="<%=Objects.toString(leftSrc, "")%>" /> <input type="text"
			readonly name="rightTarget" id="rightTarget"
			value="<%=Objects.toString(rightTarget, "")%>" />
	</form>

	<ul id="leftArea">
		<%
			for (FileWrapper tmp : leftFiles) {
		%>
		<li id="<%=tmp.getId()%>"
			class="<%=tmp.isDirectory() ? "dir" : "file"%>"><%=tmp.getDisplayName()%></li>
		<%
			}
		%>
	</ul>
	<form method="post" action="?">
		<input type="text" readonly name="leftSrc" id="leftSrc"
			value="<%=Objects.toString(leftSrc, "")%>" /> <input type="text"
			readonly name="rightTarget" id="rightTarget"
			value="<%=Objects.toString(rightTarget, "")%>" /> <input type="text"
			readonly name="srcFile" id="srcFile"
			value%=<%=Objects.toString(srcFiles, "")%> />

		<%
			for (CommandType command : CommandType.values()) {
		%>
		<input type="radio" required name="command"
			value="<%=command.name()%>" />
		<%=command.name()%>
		<%
			}
		%>
		<input type="submit" value="전송" />
	</form>
	<ul id="rightArea">
		<%
			for (FileWrapper tmp : rightFiles) {
		%>
		<li id="<%=tmp.getId()%>"
			class="<%=tmp.isDirectory() ? "dir" : "file"%>"><%=tmp.getDisplayName()%></li>
		<%
			}
		%>

	</ul>
	<script type="text/javascript">
		var leftSrc = $('#leftSrc');
		var rightTarget = $('#rightTarget')
		var serverFileForm = $("#serverFileForm");
		var srcFile = $("#srcFile");
		$('li').css({cursor:'pointer'});
		$(".dir").on("dblclick", function() {
			if ($(this).parent().prop("id") == 'leftArea') {
				leftSrc.val($(this).prop("id"));

			} else {
				rightTarget.val($(this).prop("id"));
			}
			//serverFileForm.submit();
			//ajax 코드를넣으라는데,,?
		});

		$("#leftArea>.file").on("click", function() {
			$(this).siblings("li").removeClass("active");
			$(this).toggleClass("active");
			if ($(this).hasClass("active")) {

				srcFile.val($(this).prop("id"));
			} else {
				srcFile.val("");
			}

		})
	</script>
</body>
</html>
