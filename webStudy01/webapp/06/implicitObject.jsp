<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/implicitObject.jsp</title>
<link rel="stylesheet" href="../css/mystyle.css">
</head>
<body>
	<h4>기본객체(내장객체)</h4>
	<pre>
	:JSP 컨테이너에 의해 서블릿 소스가 파싱될때 기본 제공되는 객체 (_JSPService 메소드의 지역변수 형태)
	** PageContext pageContext <a href="<%=request.getContextPath() %>/06/pageContext.jsp"> 참고</a> /한 jsp파일당 한개
	HttpServletRequest request : http 프로토콜의 요청에 대한 정보를 캡슐화 		/요청이 들어올때마다
	HttpServletResponse response : http 프로토콜의 응답에 대한 정보를 캡슐화	 /요청이 들어올때마다
	JSPWriter out : 한 JSP 페이지의 출력 버퍼에 대한 정보를 캡슐화				 /
	HTTPSession session : 한 유저가 한 브라우저를 이용한 상황에서 해당 유저에 대한 정보를 캡슐화 /사람+브라우저
					세션 : 유저가 어플리케이션을 사용하고 있는 기간(시간),
					 해당 기간내에 두 피어 사이에 의미있게 형성된 통로
					 http stateless의 단점을 보완하기 위한 최소한의 상태 정보를 서버상에서 유지.
					 <a href="${pageContext.request.contextPath}/06/sessionDesc.jsp">/06/sessionDesc.jsp</a>
	ServletContext application : 어플리케이션(컨텍스트)과 서버에 대한 정보를 캡슐화./싱글톤
			<a href="<%=request.getContextPath() %>/07/applicationDesc.jsp">/07/applicationDesc.jsp 참고</a>
	ServletConfig config : 서블릿에 대한 메타데이터 객체
	Object page : this. 현재페이지의 인스턴스
	Throwable exception : 발생한 예외나 에러에 대한 정보를 캡슐화
	
	ServletContext --> <%=application.hashCode() %>
</pre>
</body>
</html>