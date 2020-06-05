package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
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
		SearchVO searchVO=new SearchVO();
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		HashMap<String, Object> map = service.getPurchaseList(searchVO, ((UserVO)session.getAttribute("user")).getUserId());
		UserVO userVO = userService.getUser(((UserVO)session.getAttribute("user")).getUserId());
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("userVO", userVO);
		return "forward:/purchase/listPurchase.jsp";
	}

}
