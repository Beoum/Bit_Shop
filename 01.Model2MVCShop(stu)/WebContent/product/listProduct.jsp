<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.ProductVO" %>
<%@ page import="com.model2.mvc.common.*" %>    

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>

<% request.setCharacterEncoding("EUC-KR"); %>
<% response.setContentType("text/html; charset=EUC-KR"); %>


<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	
	int total=0;
	ArrayList<ProductVO> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<ProductVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
			totalPage += 1;
	}
%>   
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=euc-kr"></meta>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetProductList(){
	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=search" method="post">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						<%
							if(request.getParameter("menu").equals("search")){
						%>
							상품 목록조회
						<%
							} else if(request.getParameter("menu").equals("manage")){
						%>
							상품 관리
						<%
							}
						%>
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
					<option value="0" selected>상품번호</option>
					<option value="1">상품명</option>
					<option value="2">상품가격</option>
		<%
				}else if(searchVO.getSearchCondition().equals("1")){
		%>		
					<option value="0">상품번호</option>
					<option value="1" selected>상품명</option>
					<option value="2">상품가격</option>
		<%
				}else if(searchVO.getSearchCondition().equals("2")){
		%>
					<option value="0">상품번호</option>
					<option value="1">상품명</option>
					<option value="2" selected>상품가격</option>		
		<%
				}
			}else{
		%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
					<option value="0">상품번호</option>
					<option value="1">상품명</option>
					<option value="2">상품가격</option>	
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
			
			<input type="text" name="searchKeyword" value="<%= new String(request.getParameter("searchKeyword").getBytes("ISO-8859-1"), "euc-kr")%>" class="ct_input_g" style="width:200px; height:19px" >
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
						<a href="javascript:fncGetProductList();">검색</a>
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
		<td colspan="11" >전체 <%= total %> 건수, 현재 <%= currentPage %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<% 	
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
					if(vo.getProTranCode().trim().equals("1")) { // 판매상태 나중에 리펙토링하면 수정하세요.
						System.out.println("tranCode : "+vo.getProTranCode());
			%>
						구매완료<a href="/updateTranCodeByProd.do?prodNo=<%= vo.getProdNo()%>&tranCode=2">배송하기</a>
			<%
					}else if(vo.getProTranCode().trim().equals("2")){
			%>
						배송중
			<%
					}else if(vo.getProTranCode().trim().equals("3")){
			%>
						배송완료
			<%
					}else{
			%>
						판매중
			<%			
					}
					
				}else{
					if(vo.getProTranCode().trim().equals("0")) {
			%>
						판매중
			<%
					}else{
			%>
						재고없음				
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
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<%
			System.out.println("total: " + total);
			System.out.println("totalpage: " + totalPage);
			int nowPage = 0;
			int row = 0;
			if(request.getParameter("page") != null){
				nowPage = Integer.parseInt(request.getParameter("page"));
			}else{
				nowPage = 1;
			}
			if(nowPage / 5 < 0){
				row = 1;
			}else{
				row = (int)Math.ceil(((double)nowPage)/5);
				System.out.println("row : "+row);
			}
			
			int startNum = 0;
			if(nowPage <= totalPage){
				if(row == 1){
					startNum = 1;
				}else{
					startNum = (row-1) * 4 + row;
				}
		%>
				
		<%
			if(row != 1){
		%>
			<a href="/listProduct.do?page=<%= (row-2) * 4 + row-1%>&menu=<%= request.getParameter("menu")%>">이전</a>
		<%
			}
		%>
		
		<%				
			if(row*5+1 < totalPage){
				System.out.println("일반 마지막말고!");
				for(int i=startNum;i <= row*5;i++){
		%>
				<a href="/listProduct.do?page=<%=i%>&menu=<%= request.getParameter("menu")%>"><%=i %></a>
		<%
					}
			}else{
				System.out.println("else 실행");
				for(int i = startNum ; i <= totalPage ; i ++){
					System.out.println("else문 안의 for문 실행");
		%>
				<a href="/listProduct.do?page=<%=i%>&menu=<%= request.getParameter("menu")%>"><%=i %></a>	
		<%						
					}
				}
			}
		%>

		
		

		<%
			if(row*5+1 < totalPage){
		%>
			<a href="/listProduct.do?page=<%= row*5+1%>&menu=<%= request.getParameter("menu")%>">다음</a>
		<%
			}
		%>
		
		
		
	<여기부터 다른거임> <%
			if(request.getParameter("searchCondition") == null && request.getParameter("searchKeyword") == null){
		%>
		
		<%
			for(int i=1;i<=totalPage;i++){
		%>
			<a href="/listProduct.do?page=<%=i%>&menu=<%= request.getParameter("menu")%>"><%=i %></a>
		<%
				}
			}else{
				for(int i=1;i<=totalPage;i++){
		%>	
			 <a href="/listProduct.do?page=<%=i%>&searchKeyword=<%= request.getParameter("searchKeyword")%>&menu=<%= request.getParameter("menu")%>&searchCondition=<%= request.getParameter("searchCondition") %>"><%=i %></a>
		<%
			System.out.println("searchKeyword입니다!! : "+request.getParameter("searchKeyword"));
				}
			}
		%> 
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>