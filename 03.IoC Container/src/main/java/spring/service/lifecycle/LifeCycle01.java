package spring.service.lifecycle;

import org.springframework.beans.factory.BeanNameAware;

public class LifeCycle01 implements BeanNameAware{

	public LifeCycle01() {
		System.out.println("\n::" + getClass().getName() + "디폴트 생성자...");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("::" + getClass().getName() + ".setBeanName() start...");
		System.out.println("xml에 정의 된 beanName: " + name);
		System.out.println("::" + getClass().getName() + ".setBeanName() end...");
	}
	
	public void init() {
		System.out.println("::"+ getClass().getName() + ".init()");
	}
	
	public void destroy() {
		System.out.println("::"+ getClass().getName() + ".destroy()");
	}
}
