package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;
import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;

public class ListPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PurchaseService service = new PurchaseServiceImpl();
		UserService userService = new UserServiceImpl();
		Search searchVO=new Search();
		
		int currentPage=1;
		
		if(request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals("")){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		searchVO.setCurrentPage(currentPage);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		searchVO.setPageSize(pageSize);
		
		System.out.println(searchVO);
		
		// query return
		HashMap<String, Object> map = service.getPurchaseList(searchVO, ((User)session.getAttribute("user")).getUserId());
		User userVO = userService.getUser(((User)session.getAttribute("user")).getUserId());
		
		// paging
				Page resultPage	= 
						new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);

		request.setAttribute("map", map.get("list")); 
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("userVO", userVO);
		request.setAttribute("resultPage", resultPage);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
