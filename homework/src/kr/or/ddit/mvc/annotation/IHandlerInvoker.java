package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandlerInvoker {
 public String invokeHandler(URIMappingInfo mappinginfo, HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
