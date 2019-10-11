package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


@WebServlet("/module/layout.do")
public class PageModulization extends HttpServlet{
   Properties service;
   @Override
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      //"service" : web.xml에 초기 설정
      String serviceFile = getServletContext().getInitParameter("service");
      service = new Properties();
      InputStream in = getClass().getResourceAsStream(serviceFile); 
      try {
         service.loadFromXML(in);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
   
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      1. command 파라미터
      String command = req.getParameter("command");
      int status = 200;
      if(StringUtils.isNotBlank(command)) {
//      2. 있으면??? 제공할 수 있는 서비스가 있는지 확인 : 명령의 식별자와 해당 명령의 URI를 객체화
//      3. 정상 서비스 요청 스코프를 통해 layout에 보내줌
         
         String uri = null;
         if(service.containsKey(command)) {
            uri = service.getProperty(command);
            req.setAttribute("page", uri);
         }else {
            status = HttpServletResponse.SC_NOT_FOUND;
         }

         
      }
      
      if(status == 200) {
         String viewName = "/WEB-INF/views/layout.jsp";
         req.getRequestDispatcher(viewName).forward(req, resp);
         
      }else {
//      4. 없으면 404
         resp.sendError(status);
      }
   }
   
}