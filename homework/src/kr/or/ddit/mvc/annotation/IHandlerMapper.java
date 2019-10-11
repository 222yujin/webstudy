package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

/**
 *	HandlerMappe의 역할
 *	1. @commandHandler/@URLMapping 를 가진 클래스와 메소드에 대한ㅇ 정보를 수집
 *		--> handlerMap 생성
 *	2. 요청에 대한 핸들러를 찾아서 반환
 *
 */
public interface IHandlerMapper {
	public URIMappingInfo findCommandHandler(HttpServletRequest req);
}
