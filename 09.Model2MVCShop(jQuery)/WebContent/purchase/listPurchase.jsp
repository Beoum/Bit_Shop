<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;		
		document.detailForm.submit();
	}
	
	$(function(){
		$(".getPurchase").on("click", function(){
			var queryString = $("#getPurchaseInfo").text();
			var tranNo = queryString.substring(queryString.indexOf("tranNo")+7, queryString.indexOf("tPage")-1);
			var query = "/purchase/getPurchase?tranNo="+tranNo+"&current"+queryString.substring(queryString.indexOf("Page"));
			if(query.length >= 48){
				self.location=query;	
			}
		});
		
		$(".getUser").on("click", function(){
			location.href="/user/getUser?userId="+$(this).val();
		});
		
		$(".updateCode").on("click", function(){
			var origin = $(this).text().trim();
			if(origin.length > 10){
				var cutName = origin.substring(4, origin.indexOf("tPage")-1)+"&currentPage=";
				var pageNum = origin.substring(origin.indexOf("tPage")+6);
				
				location.href=cutName+pageNum;
			}
		});
		
	
	});
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/purchase/listPurchase" method="post">

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
		<td colspan="11">전체 ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">제품명</td>
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
	<c:forEach var="i" begin="0" end="${fn:length(list) - 1}">
		<tr class="ct_list_pop">
		<td align="center" class="getPurchase">
			${list[i].purchaseProd.prodName }
			<span style="display:none;" id="getPurchaseInfo">/purchase/getPurchase?tranNo=${list[i].tranNo }&currentPage=${resultPage.currentPage}</span>
		</td>
		<td></td>
		<td align="left" class="getUser">
			${list[i].buyer.userId }			
		</td>
		<td></td>
		<td align="left">${list[i].buyer.userId }</td>
		<td></td>
		<td align="left">${list[i].receiverPhone }</td>		
		<td></td>
		<td align="left">
			<c:choose>
				<c:when test="${list[i].tranCode.charAt(0)=='3'.charAt(0) }">배송완료<td></c:when>
				<c:when test="${list[i].tranCode.charAt(0)=='2'.charAt(0) }">배송중<td></c:when>
				<c:otherwise>구매완료<td></c:otherwise>
			</c:choose>
	 		<td align="left" class="updateCode">
			<c:choose>
				<c:when test="${list[i].tranCode.charAt(0)=='3'.charAt(0) }"/>
				<c:when test="${list[i].tranCode.charAt(0)=='2'.charAt(0) }">
				물건도착
				<span style="display:none;">/purchase/updateTranCode?tranNo=${list[i].tranNo }&tranCode=3&currentPage=${resultPage.currentPage }</span>
				</c:when>
			</c:choose>
			</td>
	</c:forEach>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
			 <input type="hidden" id="currentPage" name="currentPage" value=""/>
				<jsp:include page="../common/pageNavigator.jsp"/>	
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>