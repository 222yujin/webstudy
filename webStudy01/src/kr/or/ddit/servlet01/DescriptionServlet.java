package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** alt + shift + j
 * 
 * Servlet : 웹 상에서 동작할 수 있는 자바 객체에 대한 모든 정의를 가진 스펙.
 * 
 * 서블릿 스펙에 따른 모듈을 개발하는 단계
 * 1. HttpServlet 하위 구현체 정의
 * 		: 필요한 callback 메소드를 overriding.
 * 		callback 메소드란? 
 * 			: 특정 이벤트가 발생한 경우 시스템에 의해 자동 호출되는 구조(tomcat이 만들어준다)
 * 
 * 2. 컴파일 => 해당 컨텍스트의 classpath에 배치
 * 			(/WEB-INF/classes)
 * 
 * 3. web.xml에 등록/매핑
 * 		@WebServlet(servlet 3.0부터 가능)
 * 		** 등록 설정 종류 
 * 		asyncSupported - 동기 처리,비동기 처리
 * 		initParams - 서블릿 객체 생성 후 초기화 시점에 파라미터를 전달
 * 		loadOnStartup - 설정된 순서에 따라 서버가 구동되면 자동으로 서블릿 객체가 생성됨
 * 
 * 4. 서버(WAS,Servlet Container)를 재구동.
 * 
 * ** 서블릿의 콜백 메서드 종류
 * 1. lifecycle callback : 생명주기 내에서 단 한번 실행되는 메소드(init,destroy)
 * 2. request callback (***): template method pattern
 * 			1) service : 요청을 받고, 요청의 조건(request method)를 판단하여, 
 * 						해당 callback(doXXX)메소드를 호출하는 역할
 * 						template method
 * 			2) doXXX : 단일책임의 원칙을 적용하여 메소드 구조를 분리함.
 * 						hook method
 * 
 * ** Servlet Container (Web Application Server,tomcat)
 * 		: Servlet의 lifecycle을 관리하며, 매핑된 요청이 발생하면, 
 * 			해당 서블릿의 콜백을 호출하여 요청에대한 처리를 하는 주체.
 * 
 * ** JSP Container (WAS,tomcat)
 * 		:  JSP의 lifecycle을 관리하며, 매핑된 요청이 발생하면, 
 * 			해당 서블릿의 콜백을 호출하여 요청에대한 처리를 하는 주체.
 * 
 * SOLID
 */
//@WebServlet("/desc")
public class DescriptionServlet extends HttpServlet {
	
	public DescriptionServlet() {
		super();
		System.out.printf("%s 생성\n",this.getClass().getName());//객체 하나만 생성
	}
	
	String paramValue;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		 paramValue = config.getInitParameter("param");
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.printf("%s 객체소멸",getClass().getName());
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("service 메서드 실행 시작");
		super.service(arg0, arg1);	//없으면 doGet이 실행이 안 됨
		System.out.println("service 메서드 실행 종료");
		System.out.println(getServletContext().hashCode());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)//hook메소드 끌어와서 실행시킴
			throws ServletException, IOException {
		System.out.printf(" %s 가 실행중.\n",Thread.currentThread().getName());
		System.out.printf("전달된 초기화 파라미터 값 : %s \n",paramValue);

	}
	
	
}
