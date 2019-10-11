<%@page import="java.io.InputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/mystyle.css">
<title>07/applicationDesc.jsp</title>
</head>
<body>
	<h4>ServletContext application</h4>
	<pre>
		: 서블릿의 어플리케이션과 서버에 대한 정보를 가진 객체.
		 컨텍스트 하나를 기준으로 싱글톤의 형태 
		 ServletContext --> <%=application.hashCode()%>
		 <a href="<%=request.getContextPath()%>/gugudan">구구단서블릿</a>
		 
		 1. 서버의 정보 확보 : <%=application.getServerInfo()%>
		 서블릿 스펙 버전:<%=application.getMajorVersion() + "." + application.getMinorVersion()%>
		 
		 2.로그 기록(logging)
			 시스템 분석 용도로도 쓰임
		 <%
		 	application.log("디버깅 목적으로 기록.");
		 %>
		 3. 웹 리소스 확보
		 	 파일시스템 절대경로 getRealPath(uri)
		 	 URL getResource(uri);
		 	 입력스트림 (InputStream) getResourceAsStream(uri);
		 	 MimeText getMimeType(파일명); <%=application.getMimeType("Desert.jpg") %>
		 ** 리소스를 확보하는 방법
		 1. File System Resource
		 2. Classpath Resource
		 3. Web Resourse
		 
		  <%
		 	File file1 = new File("D:/contents/Desert.jpg"); //File System Resource
		 	DescriptionServlet.class.getResource("Desert.jpg"); //Classpath Resource
		 	String uri = "/images/Desert.jpg"; 
		 	//String path = application.getRealPath(uri);

		 	//File srcFile = new File(path);
		 	String path = application.getResource(uri).getFile();

		 	String targetUri = "/07/Desert.jpg";
		 	String targetPath = application.getRealPath(targetUri);
		 	File targetFile = new File(targetPath);

		 	//Stream copy
		 	try (
		 			//FileInputStream input = new FileInputStream(srcFile);
		 			 InputStream input1 = application.getResourceAsStream(uri);
		 			FileOutputStream output = new FileOutputStream(targetFile);)
		 	{
		 		IOUtils.copy(input1, output);
		 	}
		 %>
		 <%=path%>
		 
		 
		 <img src="<%=request.getContextPath() + targetUri%>" />
	</pre>
</body>
</html>