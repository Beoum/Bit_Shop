package spring.service.lifecycle.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.service.domain.User;
import spring.service.lifecycle.UserFactoryBean;

/*
 * FileName : LifeCycle02TestApp.java 
 */
public class LifeCycle03TestApp {

	///main Method
	public static void main(String[] args) throws Exception {
		
		ApplicationContext factory =
					new ClassPathXmlApplicationContext(
														new String[] {"/config/lifecycle03service.xml"}			
																							);

		System.out.println("\n==================================");		
		User user01 = (User)factory.getBean("userFactoryBean");
		System.out.println( user01);
		System.out.println( user01.hashCode());
		
		System.out.println("\n==================================");				
		User user02 = (User)factory.getBean("userFactoryBean");
		System.out.println( user02 );
		System.out.println( user02.hashCode() );
		
		System.out.println("\n==================================");
		//FactoryBean 자체의 instance 얻고 싶다면 &  붙여서 사용
		UserFactoryBean userFactoryBean = (UserFactoryBean)factory.getBean("&userFactoryBean");
		System.out.println( userFactoryBean );
	}
}//end of class