package spring.service.domain.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.service.domain.User;

/*
 * FileName : UserTestApp02.java
 */
public class UserTestApp02 {
	
	///Main Method
	public static void main(String[] args){
		
		//1. ApplicatiContext �̿� meta-data �� ������,������ ����� instance ���� �� ����������
		//    - meta-data �� ���ȭ �� �� �ִ�. 
		ApplicationContext factory =
				new ClassPathXmlApplicationContext(
																				new String[] {	
																										"/config/userservice01.xml",
																										"/config/userservice02.xml"	
																										}			
																						);

		//2. Container �� ���� ������ id �� ���� instance Look Up 
		//	- Dependency Lookup / Dependency Injection Ȯ��...
		System.out.println("\n=======================");
		User user05 = (User)factory.getBean("user05");
		System.out.println(user05);
		
		System.out.println("=======================");
		User user06 = (User)factory.getBean("user06");
		System.out.println(user06);
		
		System.out.println("=======================");
		User user07 = (User)factory.getBean("user07");
		System.out.println(user07);
		
		System.out.println("=======================");
		User user08 = (User)factory.getBean("user08");
		System.out.println(user08);
		System.out.println("=======================");
		
	}
	
}//end of class