package com.model2.mvc.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class RequestMapping {
	
	private static RequestMapping requestMapping;
	private Map<String, Action> map;
	private Properties properties;
	
	private RequestMapping(String resources) {
		map = new HashMap<String, Action>();
		InputStream in = null;
		try{
			System.out.println("RequestMapping start getClass: " + getClass());
			in = getClass().getClassLoader().getResourceAsStream(resources);
			properties = new Properties();
			properties.load(in);
		}catch(Exception ex){
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :"  + ex);
		}finally{
			if(in != null){
				try{ in.close(); } catch(Exception ex){}
			}
		}
	}
	
	public synchronized static RequestMapping getInstance(String resources){
		if(requestMapping == null){
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}
	
	public Action getAction(String path){
		Action action = map.get(path); // key를 입력받아 value 반환
		if(action == null){
			String className = properties.getProperty(path); // properties에서 일치하는 값을 찾아서 반환
			System.out.println("prop : " + properties);
			System.out.println("path : " + path);			
			System.out.println("className : " + className);
			className = className.trim();// 공백 없이 반환
			try{
				Class c = Class.forName(className); // 물리적인 클래스 파일명을 인자로 넣어주면 이에 해당하는 클래스를 반환해줌
				Object obj = c.newInstance(); // path에 해당하는 클래스를 생성
				if(obj instanceof Action){ // Action.java 들은 Action을 상속받아 구현되기때문에
					map.put(path, (Action)obj);// class type이라면 map에 정보 추가
					action = (Action)obj;
				}else{
					throw new ClassCastException("Class형변환시 오류 발생  ");
				}
			}catch(Exception ex){
				System.out.println(ex);
				throw new RuntimeException("Action정보를 구하는 도중 오류 발생 : " + ex);
			}
		}
		return action; // 실행할 클래스 리턴
	}
}