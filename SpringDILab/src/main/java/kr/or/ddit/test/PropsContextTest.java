package kr.or.ddit.test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.vo.DbInfoVO;
import kr.or.ddit.vo.PropertyVO;

public class PropsContextTest {
	public static void main(String[] args) {
		Properties systemProps =  System.getProperties();
		
		for (Entry<Object, Object> entry :systemProps.entrySet()) {
			System.out.printf("%s : %s \n",entry.getKey(),entry.getValue());
		}
		
		Map<String, String> env=System.getenv();	
		
		for (Entry<String, String> entry :env.entrySet()) {
			System.err.printf("%s : %s \n",entry.getKey(),entry.getValue());
		}
		
		ConfigurableApplicationContext container = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/conf/props-context.xml");
		
		PropertyVO vo1 =container.getBean("propertyVO1",PropertyVO.class);
//		PropertyVO vo2  =container.getBean("propertyVO2",PropertyVO.class);
		
		System.out.println(vo1);
//		System.out.println(vo2);
		
		DbInfoVO info1=container.getBean("dbInfoVO1",DbInfoVO.class);
		DbInfoVO info2=container.getBean("dbInfoVO2",DbInfoVO.class);
		
		System.out.println(info1);
		System.out.println(info2);
	}
}
