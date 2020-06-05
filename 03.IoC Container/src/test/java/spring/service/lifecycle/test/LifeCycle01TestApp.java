package spring.service.lifecycle.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.service.lifecycle.LifeCycle01;

/*
 * FileName : LifeCycle01TestApp.java
 */
public class LifeCycle01TestApp {
	
	///main Method
	public static void main(String[] args) throws Exception {
		
		ApplicationContext factory =
			new ClassPathXmlApplicationContext("/config/lifecycle01service.xml");
		
		/*
			//==> 아래와 같이 MetaData를 여러 가지 방법을 사용 할 수 있다. 
			ApplicationContext factory =
						new ClassPathXmlApplicationContext(
																					new String[]{
																						"/config/userservice.xml",
																						"/config/bbsservice.xml"	}			
																								);
			
			ApplicationContext factory =
						new ClassPathXmlApplicationContext("/config/*.xml");
		*/

		System.out.println("\n==================================");		
		LifeCycle01 lifeCycle01 = (LifeCycle01)factory.getBean("lifeCycle01");
		System.out.println( lifeCycle01 );
		
		//==> 인스턴스가 하나 있것 확인  확인 .... (scope="singleton" default)
		LifeCycle01 lifeCycle02 = (LifeCycle01)factory.getBean("lifeCycle02");
		System.out.println( lifeCycle02 );
		
		//==> 인스턴스가 하나 있것 확인  확인 .... (scope="singleton" default)
		LifeCycle01 lifeCycle03 = (LifeCycle01)factory.getBean("lifeCycle02");
		System.out.println( lifeCycle03 );
		
		System.out.println("==================================");	
		//==> id = lifeCycle03의 scope =  prototype 
		//==> :: Look up 마다 인스턴스 생성 확인
		//==> :: lazily loading 확인
		LifeCycle01 lifeCycle04 = (LifeCycle01)factory.getBean("lifeCycle04");
		System.out.println( lifeCycle04 );
		
		LifeCycle01 lifeCycle05 = (LifeCycle01)factory.getBean("lifeCycle04");
		System.out.println( lifeCycle05 );
		
		System.out.println("\n==================================");	
		//==> destroy() Method 는 자동으로 호출되지 않는다.
		//==> 단독 Application의 경우 종료지점이 여러곳 일 수 있음으로
		//==> 각가의 종료시점에 따라 호출을 해주어야 한다.
		//==> Console 에서 destroy() 호출확인
		((ClassPathXmlApplicationContext)factory).registerShutdownHook();
		
	}
	
}//end of class