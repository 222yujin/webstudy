package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInvoker implements IHandlerInvoker {

	@Override
	public String invokeHandler(URIMappingInfo mappinginfo, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Object handler = mappinginfo.getCommandHandler();
		
		Method handlerMtd = mappinginfo.getHandlermethod();
		String logicalViewName = (String)handlerMtd.invoke(handler, req, resp);
		
		return logicalViewName;
	}
}
