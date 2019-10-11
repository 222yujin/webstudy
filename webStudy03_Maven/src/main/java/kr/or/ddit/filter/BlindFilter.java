package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlindFilter implements Filter{
	private static Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	
	//차단대상의 ip,reason
	private Map<String, String> blindMap;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 필터생성",getClass().getSimpleName());
		blindMap = new HashMap<String, String>();
		blindMap.put("192.168.206.7", "슬기");
		blindMap.put("127.0.0.1", "유진1");
		blindMap.put("0:0:0:0:0:0:0:1", "유진2");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String clientIP = request.getRemoteAddr();
		
		boolean pass = true;
		String messagePage = "/14/messagePage.jsp";
		String uri = req.getRequestURI();
		
		if (!uri.contains(messagePage)) {
			if (blindMap.containsKey(clientIP)) {
				pass = false;
			}
		}
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			req.getSession().setAttribute("message", blindMap.get(clientIP));
			resp.sendRedirect(req.getContextPath()+messagePage);
			
		}
		
		
	}

	@Override
	public void destroy() {
		logger.info("{} 필터소멸",getClass().getSimpleName());
		
	}

}
