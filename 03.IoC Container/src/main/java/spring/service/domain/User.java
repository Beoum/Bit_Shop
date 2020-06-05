package spring.service.domain;

import java.io.Serializable;

/* 
 * FileName : User.java 
 *  -  User �� ���� id / pwd / age �� ���� Value Object
 *  -  User �� ���� id / pwd / age �� ���� domain / command Object
 *  -  User �� ���� id / pwd / age �� ���� POJO  
*/ 
public class User implements Serializable {
	
	///Field
    private String userId; 			/* ȸ�� ID */
    private String password;     /* ��й�ȣ */
    private int age;    					/* ���� */ 
    
    ///Constructor
    public User() {
		System.out.println("\n::"+getClass().getName()+" ����Ʈ ������....");
	}
	public User(int age, String userId) {
		System.out.println("\n::"+getClass().getName()+" age,userId ���� �޴� ������....");
		this.age = age;
		this.userId = userId;
	}
	public User(int age, String password, String userId) {
		System.out.println("\n::"+getClass().getName()+"age,password,userId ���� �޴� ������");
		this.age = age;
		this.password = password;
		this.userId = userId;
	}
	
	///Method (getter/setter)
	public String getUserId(){
		return this.userId;
	}
	public void setUserId( String userId ){
	   System.out.println("::"+getClass().getName()+".setUserId()");
	   this.userId= userId;
	}
	public String getPassword(){
	   return this.password;
	}
	public void setPassword( String password ){
	   System.out.println("::"+getClass().getName()+".setPassword()");		
	   this.password= password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
        System.out.println("::"+getClass().getName()+".setAge()");
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", age="+ age + "]";
	}
	
}//end of class