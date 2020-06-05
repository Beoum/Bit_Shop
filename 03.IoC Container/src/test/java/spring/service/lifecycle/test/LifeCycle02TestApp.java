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
		//==> �����ڸ� ���� ������ �ƴ�, �ν��Ͻ��� �����ϴ� factory �� ���ؼ� ���� Ȯ��  
		//==> �ν��Ͻ��� �ϳ� �ְ� Ȯ��  (scope="singleton" ȭ ������ ȿ��)
		LifeCycle02 lifeCycle06 = (LifeCycle02)factory.getBean("lifeCycle06");
		System.out.println( lifeCycle06 );
		
		LifeCycle02 lifeCycle07 = (LifeCycle02)factory.getBean("lifeCycle06");
		System.out.println( lifeCycle07 );
		
		System.out.println("\n==================================");	
		//==> destroy() Method �� �ڵ����� ȣ����� �ʴ´�.
		//==> �ܵ� Application�� ��� ���������� ������ �� �� ��������
		//==> ������ ��������� ���� ȣ���� ���־�� �Ѵ�.
		//==> Console ���� destroy() ȣ��Ȯ��
		((ClassPathXmlApplicationContext)factory).registerShutdownHook();
	}
}//end of class