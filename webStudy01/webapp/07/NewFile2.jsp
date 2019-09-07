<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../../js/jquery-3.4.1.min.js"></script>

<style type="text/css">
li {
	margin-top: 10px;
	margin-right: 10px;
	padding: 5px 0px 5px 5px;
}

li.dir {
	cursor: pointer;
}

ul {
	border: 2px solid black;
	width: 20%;
	height: 500px;
	overflow: auto;
}

ul#srcArea {
	float: left;
}

form#commandForm {
	float: left;
	width: 50%;
	height: 500px;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}

ul#targetArea {
	float: right;
}

.active {
	border: 2px solid blue;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>

	<form id="explorerForm">
		좌패널 위치 : <input type="text" name="src" id="src" value="" readonly />
		우패널 위치 : <input type="text" name="target" id="target" value=""
			readonly />
	</form>

	<ul id="srcArea">

		<%
			String dir = "D:\\A_TeachingMaterial\\7.JspSpring\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\webStudy01";

			java.io.File f = new java.io.File(dir);
			if (f.exists()) {
				String[] filelist = f.list();
				for (int i = 0; i < filelist.length; i++) {
					java.io.File f2 = new java.io.File(dir + "/" + filelist[i]);

					%><li><%= f2.getName()%> </li>
				<% }
			}
		%>

		

	</ul>
	<form action="?" id="commandForm" method="post">
		<p>
			좌패널 위치 : <input type="text" name="src" id="src" value="" readonly />
			우패널 위치 : <input type="text" name="target" id="target" value=""
				readonly />
		</p>
		<p>
			선택한 파일 : <input type="text" name="srcFile" id="srcFile" value=""
				required readonly />
		</p>
		<p>
			<label><input type="radio" name="command" value="COPY"
				required />복사</label> <label><input type="radio" name="command"
				value="MOVE" required />이동</label> <label><input type="radio"
				name="command" value="DELETE" required />삭제</label> <input type="submit"
				value="명령 수행" />
		</p>
	</form>
	<ul id="targetArea">
		<li class="dir" id="01">01</li>
		<li class="dir" id="02">02</li>
		<li class="dir" id="03">03</li>
		<li class="dir" id="04">04</li>
		<li class="dir" id="05">05</li>
		<li class="dir" id="06">06</li>
		<li class="file" id="gugudan.jsp">gugudan.jsp</li>
		<li class="dir" id="images">images</li>
		<li class="dir" id="js">js</li>
		<li class="dir" id="META-INF">META-INF</li>
		<li class="dir" id="WEB-INF">WEB-INF</li>

	</ul>
	<script type="text/javascript">
		var explorerForm = $("#explorerForm");
		var commandForm = $("#commandForm");
		var src = $("#src");
		var target = $("#target");
		var srcFile = $("#srcFile");
		$("ul#srcArea>li.file").on("click", function() {
			var file = $(this).prop("id");
			$(this).siblings("li").removeClass("active");
			$(this).toggleClass("active");
			if ($(this).hasClass("active")) {
				srcFile.val(file);
			} else {
				srcFile.val("");
			}
		});
		$("ul").on("dblclick", "li", function() {
			var thisId = $(this).prop("id");
			var parentId = $(this).parent().prop("id");
			if (parentId == "srcArea") {
				src.val(thisId);
			} else if (parentId == "targetArea") {
				target.val(thisId);
			}
			explorerForm.submit();
		});
	</script>
</body>
</html>










