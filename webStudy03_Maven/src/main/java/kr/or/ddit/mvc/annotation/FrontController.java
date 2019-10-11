package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

public class FrontController extends HttpServlet {
	private IHandlerMapper handlerMapper;
	private IHandlerInvoker handlerInvoker;
	private IViewProcessor viewProcessor;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		String packages = config.getInitParameter("basePackages");
		String[] basePackages = packages.split("\\s+");
		handlerMapper = new HandlerMapper(basePackages);
		handlerInvoker = new HandlerInvoker();
		viewProcessor = new ViewProcessor();
		viewProcessor.setPrefix(config.getInitParameter("prefix"));
		viewProcessor.setSuffix(config.getInitParameter("suffix"));
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 템플릿, hook메소드 호출안해
		// 대신에 뒷단에서 실행되는 메소드 호출해
		// 인터페이스 가져와야헤
		// 상단, 하단에서 반복되는거 뺄려고 만든 메섣,

		req.setCharacterEncoding("UTF-8");
		URIMappingInfo handlerinfo = null;
		// 핸들러 호출해서 뷰네임 받아와야함
		// 1.핸들러 매퍼로 핸들러 찾기2. 3.해당 메소드업으면 여기까지 예외 올수있어 -illegal~
		try {
			handlerinfo = handlerMapper.findCommandHandler(req);
			
		} catch (IllegalArgumentException e) { // 메소드 없어
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, e.getMessage());
			return;
		}
		if (handlerinfo == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "제공하지 않는 서비스 입니다."); // 404
			return;
		}

		String viewName = null;
		try {
			viewName = handlerInvoker.invokeHandler(handlerinfo, req, resp);
			if(viewName==null && !resp.isCommitted()) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "viewName이 없음");
				return;
			}else if(viewName!=null) {
				viewProcessor.viewProcess(viewName, req, resp);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}

	}
}
