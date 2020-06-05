<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"  %>

<%@ page import="com.model2.mvc.service.product.vo.ProductVO" %>
<%@ page import="com.model2.mvc.common.*" %>    
<%@ page import="com.model2.mvc.common.util.CommonUtil" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List<ProductVO> list = (List<ProductVO>)request.getAttribute("map");
	Page resultPage = (Page)request.getAttribute("resultPage");
	Search searchVO=(Search)request.getAttribute("searchVO");
	
	String searchCondition = CommonUtil.null2str(searchVO.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(searchVO.getSearchKeyword());
%>   
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=euc-kr"></meta>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=<%= request.getParameter("menu") %>" method="post">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						<c:if test="${!empty param.menu}">
							<c:choose>
								<c:when test="${param.menu == 'search' }">
									��ǰ�����ȸ
								</c:when>
								<c:when test="${param.menu == 'manage' }">
									��ǰ����
								</c:when>
							</c:choose>
						</c:if>
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<%
			if(searchVO.getSearchCondition() != null) {
		%>
				<td align="right">
					<select name="searchCondition" class="ct_input_g" style="width:80px">
		<%
				if(searchVO.getSearchCondition().equals("0")){
		%>
					<option value="0" selected>��ǰ��ȣ</option>
					<option value="1">��ǰ��</option>
					<option value="2">��ǰ����</option>
		<%
				}else if(searchVO.getSearchCondition().equals("1")){
		%>		
					<option value="0">��ǰ��ȣ</option>
					<option value="1" selected>��ǰ��</option>
					<option value="2">��ǰ����</option>
		<%
				}else if(searchVO.getSearchCondition().equals("2")){
		%>
					<option value="0">��ǰ��ȣ</option>
					<option value="1">��ǰ��</option>
					<option value="2" selected>��ǰ����</option>		
		<%
				}
			}else{
		%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
					<option value="0">��ǰ��ȣ</option>
					<option value="1">��ǰ��</option>
					<option value="2">��ǰ����</option>	
			</select>

		<%
			}
		%>
		<%
			if(request.getParameter("searchKeyword") == null){
		%>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
		<%
			}else{
		%>
			
			<input type="text" name="searchKeyword" value="<%= searchKeyword%>" class="ct_input_g" style="width:200px; height:19px" >
		<%
			}
		%>
		
		
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1');">�˻�</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >��ü <%= resultPage.getTotalCount() %> �Ǽ�, ���� <%= resultPage.getCurrentPage() %> ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	
	
<%-- 	<% 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			ProductVO vo = (ProductVO)list.get(i);
	%>		
	<tr class="ct_list_pop">
		<td align="center"><%= no-- %></td>
		<td></td>
			<%
				if(request.getParameter("menu").equals("search")){
					if(vo.getProTranCode().trim().equals("0")) {
			%>
						<td align="left"><a href="/getProduct.do?prodNo=<%= vo.getProdNo()%>&menu=<%= request.getParameter("menu")%>"><%= vo.getProdName() %></a></td>
			<%
					}else{
			%>
						<td align="left"><%= vo.getProdName() %></td>						
			<%
					}
				} else if(request.getParameter("menu").equals("manage")){
					if(vo.getProTranCode().trim().equals("0")){

			%>		
				<td align="left"><a href="/updateProductView.do?prodNo=<%= vo.getProdNo()%>&menu=<%= request.getParameter("menu")%>"><%= vo.getProdName() %></a></td>
			<%
					}else{
			%>						
					<td align="left"><a href="/getProduct.do?prodNo=<%= vo.getProdNo()%>&menu=<%= request.getParameter("menu")%>"><%= vo.getProdName() %></a></td>
			<%	
					}
				}
			%>
		<td></td>
		<td align="left"><%= vo.getPrice() %></td>
		<td></td>
		<td align="left"><%= vo.getRegDate() %></td>
		<td></td>
		<td align="left">

 	 		<%
				if(request.getParameter("menu").equals("manage")){
					if(vo.getProTranCode().trim().equals("1")) { // �ǸŻ��� ���߿� �����丵�ϸ� �����ϼ���.
			%>
						���ſϷ�<a href="/updateTranCodeByProd.do?prodNo=<%= vo.getProdNo()%>&tranCode=2">����ϱ�</a>
			<%
					}else if(vo.getProTranCode().trim().equals("2")){
			%>
						�����
			<%
					}else if(vo.getProTranCode().trim().equals("3")){
			%>
						��ۿϷ�
			<%
					}else{
			%>
						�Ǹ���
			<%			
					}
					
				}else{
					if(vo.getProTranCode().trim().equals("0")) {
			%>
						�Ǹ���
			<%
					}else{
			%>
						������				
			<%		
					}
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
</table> --%>
	<c:forEach var="i" begin="0" end="2">
		<tr class="ct_list_pop">
		<td align="center">${i }</td>
			<td></td>
 		<c:if test="${!empty param.menu }">
				<c:if test="${param.menu == 'manage' }">
					<c:choose>
						<c:when test="${map[i].proTranCode == '0' }">
							<td align="left"><a href="/updateProductView.do?prodNo=${map[i].prodNo }&menu=<%= request.getParameter("menu")%>">${map[i].prodName }</a></td>
						</c:when>
						<c:otherwise>
							<td align="left"><a href="/getProduct.do?prodNo=${map[i].prodNo }&menu=<%= request.getParameter("menu")%>">${map[i].prodName }</a></td>
						</c:otherwise>
					</c:choose>	
				</c:if>
				<c:if test="${param.menu == 'search' }">
					<c:choose>
						<c:when test="${map[i].proTranCode == '0' }">
							<td align="left"><a href="/getProduct.do?prodNo=${map[i].prodNo }&menu=<%= request.getParameter("menu")%>">${map[i].prodName }</a></td>
						</c:when>
					</c:choose>
				</c:if>
			</c:if>  
		</c:forEach>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			 <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					�� ����
			<% }else{ %>
					<a href="javascript:fncGetUserList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetUserList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					���� ��
			<% }else{ %>
					<a href="javascript:fncGetUserList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
			<% } %>

	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>