<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.util.*"  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=euc-kr"></meta>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
	
	$(function(){
		
		//������
		if($("td[id='test']").text().replace(/[^0-9,]/g,'').split(",")[1] % 2 != 0){
			$("td[id='bold']").css("background-color", "pink");
		}else{
			$("td[id='other']").css("background-color", "pink");
		}
		
		$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
			//Debug..
// 			var string = $( this ).text().trim().substring($( this ).text().trim().length-5);
			var string = $( this ).text().trim();
			var tranNo = string.substring(string.indexOf("tranNo")+7, string.indexOf("menu")-1);
			var menu = string.substring(string.indexOf("menu")+5);
			var prodNo = string.substring(string.indexOf("&")-5, string.indexOf("&"));
			
			// menu���� �������������� ��ư �������� �ʰ� ����
			if(menu != ""){
				if(tranNo == 0 && menu=='manage'){
	 				self.location ="/product/updateProductView?prodNo="+prodNo;
				}else{
					self.location ="/product/getProduct?prodNo="+prodNo;
				}
			}
		});
		
		$(".ct_list_pop td:nth-child(9)").on("click", function(){
			var string = $(this).text().trim();
			var before = string.substring(string.indexOf("prodNo"), string.indexOf("tranCode=")+10)+ "&currentPage=";
			var after = string.substring(string.indexOf("tPage")+6);

			if(string.length > 4){
 				self.location="/purchase/updateTranCodeByProd?" + before + after;				
			}
		});
		
	})
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct?menu=${param.menu }" method="post">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
							${param.menu == 'search' ? '��ǰ�����ȸ':'��ǰ����' }
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
					<option value="0" ${!empty search.searchCondition && search.searchCondition==0 ? "selected":"" }>��ǰ��ȣ</option>
					<option value="1" ${!empty search.searchCondition && search.searchCondition==1 ? "selected":"" }>��ǰ��</option>
					<option value="2" ${!empty search.searchCondition && search.searchCondition==2 ? "selected":"" }>��ǰ����</option>
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
		<td colspan="11" id="test">��ü ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage } ������
			<div float:right>
			<c:if test="${empty param.searchKeyword}">
				<u><a href="/product/listProduct?orderBy=DESC&menu=${param.menu }">���ݳ�����</a></u>
				<u><a href="/product/listProduct?orderBy=ASC&menu=${param.menu }">���ݳ�����</a></u></div>
			</c:if>	
			<c:if test="${!empty param.searchKeyword}">
				<u><a href="/product/listProduct?orderBy=DESC&menu=${param.menu }&searchKeyword=${param.searchKeyword}&searchCondition=${search.searchCondition}">���ݳ�����</a></u>
				<u><a href="/product/listProduct?orderBy=ASC&menu=${param.menu }&searchKeyword=${param.searchKeyword}&searchCondition=${search.searchCondition}">���ݳ�����</a></u>
			</c:if>	
			<c:if test="${!empty param.menu }">
				<input type="hidden" name="orderBy" value="${param.orderBy }"/>
			</c:if>
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��<br/><h7>(�󼼺��� : ��ǰ��Ŭ��)</h7></td>
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
	<c:forEach var="i" begin="0" end="${fn:length(list) - 1}">
	
	<tr class="ct_list_pop">
		<td align="center" ${(i+1) % 2 != 0? "id='bold'":"id='other'"}>${i + 1 }</td>
			<td></td>
 		<c:if test="${!empty param.menu }">
				<c:if test="${param.menu == 'manage' }">
					<c:choose>
						<c:when test="${list[i].proTranCode == '0' }">
								<td align="left" ${(i+1) % 2 == 0 || i == 1? "id='bold'":"id='other'"}>${list[i].prodName }<span style="display:none;">${list[i].prodNo}&tranNo=${list[i].proTranCode }&menu=${param.menu }</span></td>
						</c:when>
						<c:otherwise>
							<td align="left" ${(i+1) % 2 == 0 || i == 1? "id='bold'":"id='other'"}>${list[i].prodName }<span style="display:none;">${list[i].prodNo}&tranNo=${list[i].proTranCode }&menu=${param.menu }</span></td>
						</c:otherwise>
					</c:choose>	
				</c:if>
				<c:if test="${param.menu == 'search' }">
				
					<c:choose>
						<c:when test="${list[i].proTranCode == '0' }">
							<td align="left" ${(i+1) % 2 == 0 || i == 1? "id='bold'":"id='other'"}>${list[i].prodName }<span style="display:none;">${list[i].prodNo}&tranNo=${list[i].proTranCode }&menu=${param.menu }</span></td>
						</c:when>
						<c:otherwise>
							<td align="left" ${(i+1) % 2 == 0 || i == 1? "id='bold'":"id='other'"}>${list[i].prodName }</td>
						</c:otherwise>
					</c:choose>
				</c:if>
				<td></td>
				<td align="left" ${(i+1) % 2 != 0? "id='bold'":"id='other'"}>${list[i].price }</td>
				<td></td>
				<td align="left" ${(i+1) % 2 == 0 || i == 1? "id='bold'":"id='other'"}>${list[i].regDate }</td>
				<td></td>
				<td align="left" ${(i+1) % 2 != 0? "id='bold'":"id='other'"}>
				<c:if test="${param.menu == 'manage' }">
					<c:choose>
						<c:when test="${list[i].proTranCode.charAt(0) == '1'.charAt(0) }">
								���ſϷ�<br/><u>����ϱ�</u><span style="display:none;">prodNo=${list[i].prodNo }&tranCode=2&currentPage=${resultPage.currentPage}&searchKeyword=${param.searchKeyword}&menu=${param.menu}</span>
						</c:when>
						<c:when test="${list[i].proTranCode.charAt(0) == '2'.charAt(0) }">�����</c:when>
						<c:when test="${list[i].proTranCode.charAt(0) == '3'.charAt(0) }">��ۿϷ�</c:when>
						<c:otherwise>
							�Ǹ���
						</c:otherwise>
					</c:choose>
				</c:if>	
				<c:if test="${param.menu == 'search' }">
					<c:choose>
						<c:when test="${list[i].proTranCode.charAt(0) == '0'.charAt(0) }">
							�Ǹ���
						</c:when>
						<c:otherwise>
							������
						</c:otherwise>
					</c:choose>
				</c:if>	
					</td>	
				</c:if>  
			</tr>
		</c:forEach>
		

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			 <input type="hidden" id="currentPage" name="currentPage" value="${resultPage.currentPage}"/>
			 
			<jsp:include page="../common/pageNavigator.jsp"/>	
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>