<%@page import="java.util.Objects"	%>
<%@page import="java.io.File"		%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일</title>
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
<%!
	private String getId(File file) {
		//
		/*
         * String getRealPath(path) : 시스템 루트 경로에서부터 파라미터(path)로 전달한 경로가 포함한 경로를 문자열로 반환
         * 							: 집에서 확인한 결과 아래와 같은 path 출력
         *							: C:\jsp\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\webStudy01\
         * String getAbsolutePath() : 파일 절대경로를 문자열로 반환()
         *							: 집에서 확인한 결과 아래와 같은 path 출력
         *							: C:\jsp\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\webStudy01\01
		 */
		String contextRealPath = getServletContext().getRealPath("");
		String fileAbsolutePath = file.getAbsolutePath();
		
		//폴더(파일) 절대경로의 문자열이 시스템 루트 경로의 문자열보다 큰 경우 
		return fileAbsolutePath.length() > contextRealPath.length()
			   //폴더(파일) 절대경로의 문자열을 시스템 루트 경로의 문자열로 길이만큼 자른뒤  \을 /로 변경
			 ? fileAbsolutePath.substring(contextRealPath.length()).replace(File.separatorChar, '/')
			 : "";
	}
%>
<%
	//파라미터로 넘어온 값 세팅
	String src     = Objects.toString(request.getParameter("src"    ), "");
	String target  = Objects.toString(request.getParameter("target" ), "");
	String srcFile = Objects.toString(request.getParameter("srcFile"), "");
%>

	<form id="explorerForm">
		좌패널 위치 : <input type="text" name="src"    id="src"    value="<%=src%>"    readonly />
		우패널 위치 : <input type="text" name="target" id="target" value="<%=target%>" readonly />
	</form>

	<%-- 왼쪽 폴더(파일) 목록 --%>
	<ul id="srcArea">
		<%
			//newFile1.java에서 leftFiles라는 이름으로 넘겨준 데이터 받기
			File[] leftFiles = (File[]) request.getAttribute("leftFiles");
		
			//넘겨받은 데이터 폴더(파일) 목록 화면 출력
			for (File leftFile : leftFiles) {
				String leftFileName = leftFile.getName();	//폴더(파일)명
				String leftFileId   = getId(leftFile);		//폴더(파일)절대경로에서 시스템 루트 경로를 뺀 나머지
				
				//파라미터로 받은 src의 값이 폴더(파일)절대경로에서 시스템 루트 경로를 뺀 나머지 문자열 길이보다 큰 경우 '..' 아니면 폴더(파일)명 설정
				leftFileName = src.length() > leftFileId.length() ? ".." : leftFileName;
				
				//폴더(파일)절대경로에서 시스템 루트 경로를 뺀 나머지 문자열과 scrFile이 같다몉 active 아니면, 공백 값 설정
				String active = leftFileId.equals(srcFile) ? " active" : "";
			
		%>
			<%-- leftFile이 디렉토리(폴더)인 경우 dir, 아닌 경우 file class에 추가 --%>
			<%-- 위에서 세팅한 active 변수 class에 추가 --%>
			<%-- li태그의 id는 파일ID로, 값은 파일명으로 설정하여 화면 출력 --%>
			<li class="<%=leftFile.isDirectory() ? "dir" : "file"%><%=active%>" id="<%=leftFileId%>"><%=leftFileName%></li>
		<%
			}
		%>
	</ul>
	<form action="?" id="commandForm" method="post">
		<p>
			좌패널 위치 : <input type="text" name="src"    id="src"    value="<%=src%>"    readonly /> 
			우패널 위치 : <input type="text" name="target" id="target" value="<%=target%>" readonly />
		</p>
		<p>
			선택한 파일 : <input type="text" name="srcFile" id="srcFile" value="" required readonly />
		</p>
		<p>
			<label>
				<input type="radio" name="command" value="COPY" required />복사
			</label>
			<label>
				<input type="radio" name="command" value="MOVE" required />이동
			</label>
			<label>
				<input type="radio" name="command" value="DELETE" required />삭제
			</label>
			
			<input type="submit" value="명령 수행" />
		</p>
	</form>
	<ul id="targetArea">
		<%
			//newFile1.java에서 rightFiles 이름으로 넘겨준 데이터 받기
			File[] rightFiles = (File[]) request.getAttribute("rightFiles");
		
			//넘겨받은 데이터 폴더(파일) 목록 화면 출력
			for (File rightFile : rightFiles) {
				String rightFileName = rightFile.getName();	//폴더(파일)명
				String rightFileId   = getId(rightFile);	//폴더(파일)절대경로에서 시스템 루트 경로를 뺀 나머지
				
				//파라미터로 받은 target의 값이 폴더(파일)절대경로에서 시스템 루트 경로를 뺀 나머지 문자열 길이보다 큰 경우 '..' 아니면 폴더(파일)명 설정
				rightFileName = target.length() > rightFileId.length() ? ".." : rightFileName;
		%>
			<%-- rightFile이 디렉토리(폴더)인 경우 dir, 아닌 경우 file class에 추가 --%>
			<%-- li태그의 id는 파일ID로, 값은 파일명으로 설정하여 화면 출력 --%>
			<li class="<%=rightFile.isDirectory() ? "dir" : "file"%>" id="<%=rightFileId%>"><%=rightFileName%></li>
		<%
			}
		%>
	</ul>

	<script type="text/javascript">
		var explorerForm = $("#explorerForm");
		var commandForm  = $("#commandForm");
		
		var src     = $("#src");
		var target  = $("#target");
		var srcFile = $("#srcFile");
		
		//ul 태그 dbclick 이벤트 추가 및 동적으로 생성된 li태그에 dbclick 이벤트 추가 
		$("ul").on("dblclick", "li", function(){
			   var thisId   = $(this).prop("id");
			   var parentId = $(this).parent().prop("id");
			   
			   if(parentId == "srcArea"){
			      src.val(thisId);
			   }else if(parentId == "targetArea"){
			      target.val(thisId);
			   }
			   
			   explorerForm.submit();
			});
		
		//ul 태그 중 id가 srcArea이고 바로 하위 자식 li의 클래스가 file인 태그 click 이벤트 추가
		$("ul#srcArea>li.file").on("click", function(){
		   var file = $(this).prop("id");
		   
		   $(this).siblings("li").removeClass("active");
		   $(this).toggleClass("active");
		   if($(this).hasClass("active")){
		      srcFile.val(file);   
		   }else{
		      srcFile.val("");
		   }
		});	
	</script>
</body>
</html>