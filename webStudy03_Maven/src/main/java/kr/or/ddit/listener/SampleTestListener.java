package kr.or.ddit.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class SampleTestListener
 *
 */
@WebListener
public class SampleTestListener implements ServletContextListener, HttpSessionListener, ServletRequestListener,
		ServletContextAttributeListener, HttpSessionAttributeListener, ServletRequestAttributeListener {

	private static Logger logger = LoggerFactory.getLogger(SampleTestListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("서버 구동 시작");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("어플리케이션 종료");

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String id = se.getSession().getId();
		logger.info("{} 세션시작",id);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String id = se.getSession().getId();
		logger.info("{} 세션소멸",id);

	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		
		

	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		logger.info("session scope add : {}",name);

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		logger.info("session scope remove : {}",name);

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub

	}

}
