<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ page import="com.model2.mvc.service.product.vo.ProductVO" %>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO" %>
<%@ page import="com.model2.mvc.common.*" %> 
<%@ page import="com.model2.mvc.common.util.CommonUtil" %>

<!DOCTYPE html>
<%
	List<PurchaseVO> list = (List<PurchaseVO>)request.getAttribute("map");

	Search searchVO=(Search)request.getAttribute("searchVO");
	User userVO = (User)request.getAttribute("userVO");
	Page resultPage = (Page)request.getAttribute("resultPage");
	
	String searchCondition = CommonUtil.null2str(searchVO.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(searchVO.getSearchKeyword());
%>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;		
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 <%= resultPage.getTotalCount() %> 건수, 현재 <%= resultPage.getCurrentPage() %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<% 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			PurchaseVO purchaseVO = (PurchaseVO)list.get(i);
	%>		



	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%= purchaseVO.getTranNo() %>"><%= no -- %></a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=<%= purchaseVO.getBuyer().getUserId() %>"><%= purchaseVO.getBuyer().getUserId() %></a>
		</td>
		<td></td>
		<td align="left"><%= userVO.getUserName() %></td>
		<td></td>
		<td align="left"><%= userVO.getPhone() %></td>
		<td></td>
		<td align="left">
	  	<%
			if(purchaseVO.getTranCode().trim().equals("3") ){

		%> 
				배송완료</td>
		<%
			}else if(purchaseVO.getTranCode().trim().equals("2")){
		%>
			 	배송중</td>
		<%
			}else{
		%>
				구매완료</td>
		<%
			}
		%>
		<td></td>
	 	<td align="left"><!-- status에 따라 분기 나누세요. -->
	 		<%
	 			if(purchaseVO.getTranCode().trim().equals("3")){
	 		%>
	 	
	 		<%
	 			}else if(purchaseVO.getTranCode().trim().equals("2")){
	 		%>
	 	
				<a href="/updateTranCode.do?tranNo=<%= purchaseVO.getTranNo() %>&tranCode=3">물건도착</a>
			<%
	 			}
			%>
			
		</td> 
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<%
		}
	%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
			 <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetUserList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetUserList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetUserList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %>
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>