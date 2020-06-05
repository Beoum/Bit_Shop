package spring.service.lifecycle.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.service.lifecycle.LifeCycle01;
import spring.service.lifecycle.LifeCycle02;

/*
 * FileName : LifeCycle02TestApp.java 
 */
public class LifeCycle02TestApp {

	///main Method
	public static void main(String[] args) throws Exception {
		
		ApplicationContext factory =
					new ClassPathXmlApplicationContext(	"/config/lifecycle02service.xml");
		
		System.out.println("\n==================================");		
		LifeCycle01 lifeCycle05 = (LifeCycle01)factory.getBean("lifeCycle05");
		System.out.println( lifeCycle05 );
	
		System.out.println("\n==================================");		
		//==> 생성자를 통한 생성이 아닌, 인스턴스를 생성하는 factory 를 통해서 생성 확인  
		//==> 인스턴스가 하나 있것 확인  (scope="singleton" 화 동일한 효과)
		LifeCycle02 lifeCycle06 = (LifeCycle02)factory.getBean("lifeCycle06");
		System.out.println( lifeCycle06 );
		
		LifeCycle02 lifeCycle07 = (LifeCycle02)factory.getBean("lifeCycle06");
		System.out.println( lifeCycle07 );
		
		System.out.println("\n==================================");	
		//==> destroy() Method 는 자동으로 호출되지 않는다.
		//==> 단독 Application의 경우 종료지점이 여러곳 일 수 있음으로
		//==> 각가의 종료시점에 따라 호출을 해주어야 한다.
		//==> Console 에서 destroy() 호출확인
		((ClassPathXmlApplicationContext)factory).registerShutdownHook();
	}
}//end of class