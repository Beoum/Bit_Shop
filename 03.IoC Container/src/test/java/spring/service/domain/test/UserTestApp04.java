package spring.service.domain.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.service.domain.User;

/*
 * FileName : UserTestApp04.java
 */
public class UserTestApp04 {
	
	///Main Method
	public static void main(String[] args){
	
		//1. ApplicatiContext 이용 meta-data 에 선언적,서술적 기술된 instance 생성 및 의존성주입
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("/config/userservice04.xml");

		//2. Container 로 부터 각각의 id 를 갖는 instance Look Up 
		//	- Dependency Lookup / Dependency Injection 확인...
		System.out.println("\n=======================");
		User user01 = (User)factory.getBean("user01");
		System.out.println(user01);
		
		System.out.println("======================="); 
		User user02 = (User)factory.getBean("user02");
		System.out.println(user02);
		
		System.out.println("=======================");
		User user03 = (User)factory.getBean("user03");
		System.out.println(user03);
		
		System.out.println("=======================");
		User user04 = (User)factory.getBean("user04");
		System.out.println(user04);
	
		System.out.println("=======================");
		User user05 = (User)factory.getBean("user05");
		System.out.println(user05);

	}
	
}//end of class