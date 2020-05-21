<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.util.*"  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=euc-kr"></meta>
<title>상품 목록조회</title>

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

<form name="detailForm" action="/listProduct.do?menu=${param.menu }" method="post">
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
									상품목록조회
								</c:when>
								<c:when test="${param.menu == 'manage' }">
									상품관리
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

			<td align="right">
				<select name="searchCondition" class="ct_input_g" style="width:80px">
					<option value="0" ${!empty search.searchCondition && search.searchCondition==0 ? "selected":"" }>상품번호</option>
					<option value="1" ${!empty search.searchCondition && search.searchCondition==1 ? "selected":"" }>상품명</option>
					<option value="2" ${!empty search.searchCondition && search.searchCondition==2 ? "selected":"" }>상품가격</option>
				</select>
		<c:if test="${empty param.searchKeyword }">
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
		</c:if>
		<c:if test="${!empty param.searchKeyword }">
			<input type="text" name="searchKeyword" value="${param.searchKeyword }" class="ct_input_g" style="width:200px; height:19px" >
		</c:if>
		
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1');">검색</a>
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
		<td colspan="11" >전체 ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage } 페이지
			<div float:right>
			<c:if test="${empty param.searchKeyword}">
				<u><a href="/listProduct.do?orderBy=DESC&menu=${param.menu }">가격높은순</a></u>
				<u><a href="/listProduct.do?orderBy=ASC&menu=${param.menu }&">가격낮은순</a></u></div>
			</c:if>	
			<c:if test="${!empty param.searchKeyword}">
				<u><a href="/listProduct.do?orderBy=DESC&menu=${param.menu }&searchKeyword=${param.searchKeyword}&searchCondition=${searchVO.searchCondition}">가격높은순</a></u>
				<u><a href="/listProduct.do?orderBy=ASC&menu=${param.menu }&searchKeyword=${param.searchKeyword}&searchCondition=${searchVO.searchCondition}">가격낮은순</a></u>
			</c:if>	
			<c:if test="${!empty param.menu }">
				<input type="hidden" name="orderBy" value="${param.orderBy }"/>
			</c:if>
		</td>
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
	<c:forEach var="i" begin="0" end="${fn:length(list) - 1}">
	
		<tr class="ct_list_pop">
		<td align="center">${i + 1 }</td>
			<td></td>
 		<c:if test="${!empty param.menu }">
				<c:if test="${param.menu == 'manage' }">
					<c:choose>
						<c:when test="${list[i].proTranCode == '0' }">
							<td align="left"><a href="/updateProductView.do?prodNo=${list[i].prodNo }&menu=${param.menu}">${list[i].prodName }</a></td>
						</c:when>
						<c:otherwise>
							<td align="left"><a href="/getProduct.do?prodNo=${list[i].prodNo }&menu=${param.menu}">${list[i].prodName }</a></td>
						</c:otherwise>
					</c:choose>	
				</c:if>
				<c:if test="${param.menu == 'search' }">
				
					<c:choose>
						<c:when test="${list[i].proTranCode == '0' }">
							<td align="left"><a href="/getProduct.do?prodNo=${list[i].prodNo }&menu=${param.menu}">${list[i].prodName }</a></td>
						</c:when>
						<c:otherwise>
							<td align="left">${list[i].prodName }</td>
						</c:otherwise>
					</c:choose>
				</c:if>
				<td></td>
				<td align="left">${list[i].price }</td>
				<td></td>
				<td align="left">${list[i].regDate }</td>
				<td></td>
				<td align="left">
				<c:if test="${param.menu == 'manage' }">
					<c:choose>
						<c:when test="${list[i].proTranCode.charAt(0) == '1'.charAt(0) }">
							구매완료<a href="/updateTranCodeByProd.do?prodNo=${list[i].prodNo }&tranCode=2">배송하기</a>
						</c:when>
						<c:when test="${list[i].proTranCode.charAt(0) == '2'.charAt(0) }">배송중</c:when>
						<c:when test="${list[i].proTranCode.charAt(0) == '3'.charAt(0) }">배송완료</c:when>
						<c:otherwise>
							판매중
						</c:otherwise>
					</c:choose>
				</c:if>	
				<c:if test="${param.menu == 'search' }">
					<c:choose>
						<c:when test="${list[i].proTranCode.charAt(0) == '0'.charAt(0) }">
							판매중
						</c:when>
						<c:otherwise>
							재고없음
						</c:otherwise>
					</c:choose>
				</c:if>	
					</td>	
				</tr>
			</c:if>  
		</c:forEach>
		

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			 <input type="hidden" id="currentPage" name="currentPage" value="1"/>
			 
			<jsp:include page="../common/pageNavigator.jsp"/>	
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>