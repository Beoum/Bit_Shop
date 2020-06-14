package com.model2.mvc.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspectJ {

	public LogAspectJ() {
		System.out.println("\nCommon :: "+this.getClass()+"\n");
	}
	
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
			
		System.out.println("");
		System.out.println("[Around before] 타�┛늘�.메서드 :"+
													joinPoint.getTarget().getClass().getName() +"."+
													joinPoint.getSignature().getName());
		if(joinPoint.getArgs().length !=0){
			System.out.println("[Around before]method에 전달되는 인자 : "+ joinPoint.getArgs()[0]);
		}
		// 타겟 객체의 Method 를 호출 하는 부분 
		Object obj = joinPoint.proceed();

		System.out.println("[Around after] 타겟 객체return value  : "+obj);
		System.out.println("");
		
		return obj;
	}
	
}//end of class