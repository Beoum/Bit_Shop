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
			//==> �Ʒ��� ���� MetaData�� ���� ���� ����� ��� �� �� �ִ�. 
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
		
		//==> �ν��Ͻ��� �ϳ� �ְ� Ȯ��  Ȯ�� .... (scope="singleton" default)
		LifeCycle01 lifeCycle02 = (LifeCycle01)factory.getBean("lifeCycle02");
		System.out.println( lifeCycle02 );
		
		//==> �ν��Ͻ��� �ϳ� �ְ� Ȯ��  Ȯ�� .... (scope="singleton" default)
		LifeCycle01 lifeCycle03 = (LifeCycle01)factory.getBean("lifeCycle02");
		System.out.println( lifeCycle03 );
		
		System.out.println("==================================");	
		//==> id = lifeCycle03�� scope =  prototype 
		//==> :: Look up ���� �ν��Ͻ� ���� Ȯ��
		//==> :: lazily loading Ȯ��
		LifeCycle01 lifeCycle04 = (LifeCycle01)factory.getBean("lifeCycle04");
		System.out.println( lifeCycle04 );
		
		LifeCycle01 lifeCycle05 = (LifeCycle01)factory.getBean("lifeCycle04");
		System.out.println( lifeCycle05 );
		
		System.out.println("\n==================================");	
		//==> destroy() Method �� �ڵ����� ȣ����� �ʴ´�.
		//==> �ܵ� Application�� ��� ���������� ������ �� �� ��������
		//==> ������ ��������� ���� ȣ���� ���־�� �Ѵ�.
		//==> Console ���� destroy() ȣ��Ȯ��
		((ClassPathXmlApplicationContext)factory).registerShutdownHook();
		
	}
	
}//end of class