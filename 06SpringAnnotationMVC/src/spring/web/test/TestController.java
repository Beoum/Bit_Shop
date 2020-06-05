package spring.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

//@Controller
//@RequestMapping("/test/")
public class TestController {
	
	public TestController() {
		System.out.println(":: TestController default Constructor call");
	}
	
	@RequestMapping("/testView01.do")
	public ModelAndView testView01() {
		System.out.println("[testView01() start.......]");
		
		return new ModelAndView("/test/testView.jsp");
	}
	
	@RequestMapping("/testView02.do")
	public String testView02() {
		System.out.println("[testView02() start.......]");
		
		return "/test/testView.jsp";
	}
	
	@RequestMapping("/testView03.do")
	public String testView03(@RequestParam("abc") int no,
								@RequestParam("def") String name) {
		System.out.println("[testView03() start.......]");
		
		System.out.println("no : " + no + " ==== name : " + name);
		
		return "/test/testView.jsp";
	}
	
	@RequestMapping("/testView04.do")
	public String testView04(@RequestParam("abc") int no,
								@RequestParam(value = "def", required=false) String name) {
		System.out.println("[testView04() start.......]");
		
		System.out.println("no : " + no + " ==== name : " + name);
		
		return "/test/testView.jsp";
	}
	
	@RequestMapping("/testView05.do")
	public String testView05(@RequestParam(value = "abc", defaultValue="1") int no,
								@RequestParam(value = "def", required=false) String name) {
		System.out.println("[testView05() start.......]");
		
		System.out.println("no : " + no + " ==== name : " + name);
		
		return "/test/testView.jsp";
	}
}
