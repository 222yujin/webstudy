package kr.or.ddit.idol.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.dao.IdolSearchDAO_Oracle;
import kr.or.ddit.idol.service.IIdolSearchService;
import kr.or.ddit.idol.service.IdolSearchServiceImpl;

public class IdolSearchView {
	public static void main(String[] args) {
		ApplicationContext container = new ClassPathXmlApplicationContext("kr/or/ddit/idol/conf/idol-context.xml");
		
		IIdolSearchService service = container.getBean(IIdolSearchService.class);
		String code = "I001";

		String info = service.readIdol(code);
		System.out.println(info);
	}
}
