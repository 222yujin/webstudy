package kr.or.ddit.test;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.vo.CollectionDIVO;

public class CollectionDIContextTest {
	public static void main(String[] args) {
		try (ConfigurableApplicationContext container
				= new ClassPathXmlApplicationContext("kr/or/ddit/conf/collectionDI-context.xml");
		) {
			CollectionDIVO vo1 = container.getBean("vo2",CollectionDIVO.class);
			System.out.println(Arrays.toString(vo1.getArray()));
			System.out.println(vo1.getList());
			System.out.println(vo1.getSet());
			System.out.println(vo1.getMap());
			System.out.println(vo1.getProps());
		}
	}
}
