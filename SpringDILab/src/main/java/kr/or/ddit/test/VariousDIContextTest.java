package kr.or.ddit.test;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import kr.or.ddit.vo.VariousDIVO;

public class VariousDIContextTest {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext container 
		= new GenericXmlApplicationContext("classpath:kr/or/ddit/conf/variousDI-context.xml");
		
		VariousDIVO vo1 =container.getBean("variousDIVO1",VariousDIVO.class);
		VariousDIVO vo2 =container.getBean("variousDIVO2",VariousDIVO.class);
		
		System.out.println(vo1==vo2);
		System.out.println(vo1);
		System.out.println(vo1.getFile().exists());
		System.out.println(vo1.getFile().getAbsolutePath());

//		모든 컨테이너 구현체는 그 자체로 ResourceLoader 임.
//		smart resource loader 를 사용시,
//		classpath: ,file:,http:(URI scheme) 의 접두어 형태로 
//		리소스의 종류를 식별하여 로딩할 수 있음
		
		Resource res1= container.getResource("file://d:/contents/Desert.jpg");
		System.out.println(res1.getClass().getSimpleName());
		Resource res2=container.getResource("classpath:kr/or/ddit/images/green.jpg");
		System.out.println(res2.getClass().getSimpleName());
		Resource res3= container.getResource("file://d:/contents/Desert.jpg");
		System.out.println(res3.getClass().getSimpleName());
		Resource res4=container.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		System.out.println(res4.getClass().getSimpleName());
		System.out.println(res4.contentLength());
		System.out.println(res4.exists());
		System.out.println(res4.getFilename());
		System.out.println(res1.getFile());
	}
}
