<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>파일 업로드 처리</h4>
	<pre>
   1. client side
      1) form :
           method  = "post" : body 형성
           enctype = "multipart/form-data"  : body가 multi part로 구성됨.
                    input 1개당 1part로 나눠짐
                                 각 part를 통해 input태그 하나의 데이터가 전송됨.
                     part name : input 태그의 name 속성. 
   2. server side
   		1) servlet 등록시 multipart-config 설정 추가(Part API 사용,Parameter사용)
   			:web.xml(multipart-config)
   			 @WebServlet(@MultipartConfig)
   		2) 저장위치
   			middle tier(WAS):
   				Web resource,Classpath resource, FileSystem resource
   			third tier(DB) :LOB
   		3) 저장명 : 웹 취약성제거(보안), 중복이름 --> 저장명 생성(UUID)
   		4) 메타데이터 추출 : DB저장
   		5) stream copy (upload)
</pre>
	<form action="<c:url value='/file/upload.do'/>" method="post"enctype="multipart/form-data">
		<input type="text" name="uploader" />
		<input type="file" name="uploadFile" /> 
		<input type="file" name="uploadFile" /> 
		<input type="file" name="uploadFile" /> 
		<input type="submit" value="업로드" />
	</form>
	
	<c:if test="${not empty uploader }">
		<div style="border: 1px solid black;">
			${uploader }
			<c:forEach items="${saveFiles }" var="saveFileURL">
				<img src='<c:url value="${saveFileURL }"/>'>
			</c:forEach>
				<c:remove var="uploader" scope="session" />
				<c:remove var="saveFiles" scope="session" />
		</div>
	</c:if>
</body>
</html>