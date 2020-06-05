<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>

<html>
<head>
<title>���� �����ȸ</title>

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
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage } ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:forEach var="i" begin="0" end="${fn:length(list) - 1}">
		<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=${list[i].tranNo }&currentPage=${resultPage.currentPage}">${list[i].purchaseProd.prodName }</a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${list[0].buyer.userId}">${list[i].buyer.userId }</a>			
		</td>
		<td></td>
		<td align="left">${list[i].buyer.userId }</td>
		<td></td>
		<td align="left">${list[i].receiverPhone }</td>		
		<td></td>
		<td align="left">
			<c:choose>
				<c:when test="${list[i].tranCode.charAt(0)=='3'.charAt(0) }">��ۿϷ�<td></c:when>
				<c:when test="${list[i].tranCode.charAt(0)=='2'.charAt(0) }">�����<td></c:when>
				<c:otherwise>���ſϷ�<td></c:otherwise>
			</c:choose>
	 		<td align="left"><!-- status�� ���� �б� ��������. -->
			<c:choose>
				<c:when test="${list[i].tranCode.charAt(0)=='3'.charAt(0) }"/>
				<c:when test="${list[i].tranCode.charAt(0)=='2'.charAt(0) }">
					<a href="/updateTranCode.do?tranNo=${list[i].tranNo }&tranCode=3&currentPage=${resultPage.currentPage }">���ǵ���</a>
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

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>