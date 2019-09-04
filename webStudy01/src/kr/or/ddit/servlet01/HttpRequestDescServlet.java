package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * HTTP request 패키징 방식
 * 1. Request Line : URL Method Protocol/ver
 * 		(getRequestURI,getMethod,getProtocol)
 * 
 * 				** Method : 현재 요청의 목적, 요청 패키징 방식
 * 				1) GET(Read) : 일반적으로 클라이언트의 요청을 기본 Get 방식.
 * 				2) POST(Create) : 서버상에 새로운 리소스 생성 
 * 									**** Request Body 영역 생성
 * 				3) PUT(Update) : 서버상의 리소스 갱신
 * 				4) DELETE(Delete) : 서버상의 리소스 삭제
 * 					Header,option,trace,connect,patch
 * 
 * 2. Request Header : 전송 데이터(클라이언트)에 대한 부가정보(meta data)
 * 					 : 일반적으로 요청의 모든 정보는 문자열의 형태로 전달 됨.
 * 					** 문자열 데이터에 특수문자가 포함된 경우.
 * 					RFC2396 규약에 따라 %인코딩(URL인코딩) 방식으로 인코딩 되어 전달 됨.
 * 		(String value = getHeader(headeraName)
 * 		Enumeration(:collection view) getHeaderNames)
 * 
 * 
 * 3. Request (Message/Content) Body : Method 가 POST일 경우 사용.
 * 									 : 서버로 전송할 컨텐츠가 포함됨
 * 
 * 		*** 요청 파라미터를 확보하는 방법
 * 	1.method GET : Request Line을 통해 전달됨.
 * 		URL?queryString
 * 		queryString:section1&section2&section3...
 * 			name=value
 * 		ex) gugudan?minDan=2&maxDan=4
 * 		
 * 	2.Method POST : RequstBody를 통해 전달됨
 * 		parameterMap을 통해 모든 요청 파라미터를 확보함.
 * 		String getParameter(name)
 * 		String[] getParameterValues(name)
 * 		Map(String,String[]) getParameterMap()
 * 		Enumeration<String> getParameterNames()
 * 
 * 	** 특수 문자가 포함된 파라미터는 ? %인코딩 된 파라미터,,
 * 		1. POST : setCharacterEncoding("UTF-8") - BODY 영역의 decoding 방식
 * 		2. GET  : server 설정 필요 
 * 				server.xml : http connector의 설정 변경
 * 				- URIEncoding :  charset 이 고정됨.
 * 				- useBodyEncodingForURI="true" : get메소드에서 setCharacterEncoding를 사용함
 * 						
 * 	*** HttpServletRequest : http 프로토콜에 따라 패키징된 요청에 대한 정보를 캡슐화한 객체(middleware==tomcat에 의해 수행). 
 * 							 클라이언트와 요청에 대한 모든 정보가 포함됨
 *
 */
@WebServlet("/httpRequest")
public class HttpRequestDescServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// Request Line
		String requestURI = req.getRequestURI();
		String requestMethod = req.getMethod();
		String Protocol = req.getProtocol();

		System.out.println(requestURI);
		System.out.println(requestMethod);
		System.out.println(Protocol);

		// request header
		Enumeration<String> en = req.getHeaderNames();
		while (en.hasMoreElements()) {
			String headerName = (String) en.nextElement();
			String headerValue = req.getHeader(headerName);
			System.out.printf("%s : %s \n", headerName, headerValue);
		}
		
		//request body : POST 일때만 생성 사실 여기는 doget이라서 못 씀.. 그리고 존재하지 않아서 크기도 0
		InputStream is = req.getInputStream();
		System.out.println(is.available());
		//요청 파라미터를 확보하는 방법
		//get : line 을 통해 전달
		//post : body를 통해 전달
		//파라미터 명 == 입력태그의 name 속성
		//파라미터는 특별한 설정이 없는 한 문자열로 전송 됨.
		
		String value =req.getParameter("");
		//하나의 파라미터명으로 여러개의 값이 전송될때 사용
		String[] values =req.getParameterValues("");
		//key: 파라미터 명 ,value :파라미터 값
		Map<String, String[]> pMap=req.getParameterMap();
		Enumeration<String> names= req.getParameterNames();//map의 key만 가져올 수 있음..ㅎ

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
