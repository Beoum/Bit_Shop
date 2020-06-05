package spring.service.lifecycle;

import org.springframework.beans.factory.FactoryBean;

import spring.service.domain.User;

public class UserFactoryBean implements FactoryBean{

	public UserFactoryBean() {
		System.out.println("::" + getClass().getName() + "디폴트 생성자....");
	}

	@Override
	public User getObject() throws Exception {
		System.out.println("::" + getClass().getName() + ".getObject()");
		return new User();
	}

	@Override
	public Class getObjectType() {
		System.out.println("::" + getClass().getName() + ".getObjectType()");
		return User.class;
	}

	@Override
	public boolean isSingleton() {
		System.out.println("::" + getClass().getName() + ".isSigleton()");
		return false;
	}
	
	

}
