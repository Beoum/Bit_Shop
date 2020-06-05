package spring.service.domain;

//FileName : Client.java	==> Client ¡§∫∏∏¶ ∞Æ¥¬ Bean
public class  Client{
	///Field
	private String name;
	private String addr;
	private int age;
	private String [] info = { "¡∂º±","¿«¿˚" };
	
	///Constructor
	public Client(){
		this.name = "»´±Êµø";		
		this.addr ="«—æÁ";		
		this.age = 100;
		System.out.println("Client default Constructor »£√‚µ ");
	}

	///Method getter/setter
	public String getName(){	
		System.out.println("Client getName() »£√‚µ ");
		return this.name;
	}
	public String getAddr(){	
		System.out.println("Client getAddr() »£√‚µ ");
		return this.addr ;
	}
	public int	 getAge(){	
		System.out.println("Client getAge() »£√‚µ ");
		return this.age;
	}
	public String[] getInfo(){
		System.out.println("Client getInfo »£√‚µ ");
		return this.info;
	}
	public void setName(String name){	
		System.out.println("Client setName() »£√‚µ ");
		 this.name = name;
	}
	public void setAddr(String addr){	
		System.out.println("Client setAddr() »£√‚µ ");
		 this.addr = addr;
	}
	public void	 setAge(int age){	
		System.out.println("Client setAge() »£√‚µ ");
		 this.age = age;
	}
	public void setInfo(String[] info){
		System.out.println("Client setInfo »£√‚µ ");
		 this.info = info;
	}
}