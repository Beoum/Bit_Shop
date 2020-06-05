package spring.service.lifecycle;

public class LifeCycle02 {

	private static LifeCycle02 lifeCycle;
	
	public LifeCycle02() {
		// TODO Auto-generated constructor stub
	}
	
	public static LifeCycle02 getInstance() {
		System.out.println("\n::LifeCycle02.getInstance()");
		if(lifeCycle == null) {
			lifeCycle = new LifeCycle02();
		}
		return lifeCycle;
	}
		
}
