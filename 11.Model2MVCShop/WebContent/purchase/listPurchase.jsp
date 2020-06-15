<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<style>
	  body {
            padding-top : 50px;
        }
    </style>
    
	<script type="text/javascript">
	
		//검색 / page 두가지 경우 모두  Event  처리	
		function fncGetUserList(currentPage) {
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/purchase/listPurchase").submit();
		}
		
		function fncGetPurchase(tranNo){
			self.location="/purchase/getPurchase?tranNo="+tranNo + "&currentPage=${resultPage.currentPage}";
		}
		
		// 구매정보 보기 Event  처리(Click)	
		 $(function() {
			$( "td:nth-child(1)" ).on("click" , function() {
 				var queryString = $(this).text();
				var tranNo = queryString.substring(queryString.indexOf("tranNo")+7, queryString.indexOf("tPage")-1);
				var paymentOption = "현금";
				if(!$("#"+tranNo+"" ).html().length){
					$.ajax( 
							{
								url : "/purchase/json/getPurchase?tranNo="+tranNo,
								method : "GET" ,
								dataType : "json" ,
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
								success : function(JSONData , status) {
									if(JSONData.paymentOption == 1){
										paymentOption = "신용";
									}
									var link = "<br/><h6><u><a id='detailButton' onclick='javascript:fncGetPurchase("+tranNo+")'>상세보기</a></u></h6>";
									var displayValue = "<h6>"
																+"받는사람 : "+JSONData.receiverName+"<br/>"
																+"받는주소 : "+JSONData.divyAddr+"<br/>"
																+"구매금액 : "+JSONData.purchaseProd.price+"<br/>"
																+"구매방법 : "+paymentOption+"<br/>"
																+"</h6>";
	
									$("h6").remove();
									$(  "td:nth-child(2) *" ).remove("br");
									$( "#"+tranNo+"" ).html(displayValue);
									$( "#"+tranNo+"" ).parent().next().append(link);
								}
						});
				}else{
					$("h6").remove();
					$(  "td:nth-child(3) *" ).remove("br");
				}


			});
					
			
			$(".updateCode").on("click", function(){
				var origin = $(this).text().trim();
				if(origin.length > 10){
					var cutName = origin.substring(4, origin.indexOf("tPage")-1)+"&currentPage=";
					var pageNum = origin.substring(origin.indexOf("tPage")+6);
 					location.href=cutName+pageNum;
				}
			});
			
			$( "td:nth-child(1)" ).css("color" , "red");
			
		});	
			
		$("h7").css("color" , "red");
		
		$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");

	
	</script>
	
</head>

<body>
	<jsp:include page="/layout/toolbar.jsp" />
	
	<div class="container">
	
		<div class="page-header text-info">
	       <h3>구매 목록조회</h3>
	    </div>
	    
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
				  				  
				  
				  <input type="hidden" id="currentPage" name="currentPage" value="${resultPage.currentPage}"/>
				  
				</form>
	    	</div>
	    	
		</div>
      <table class="table table-hover table-striped" >

        <thead>
          <tr>
            <th align="center">제품명<br/><h7>(상세보기 : 상품명클릭)</h7></th>
            <th align="left">전화번호</th>
            <th align="left">배송현황</th>
            <th align="left">정보수정</th>
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="purchase" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="left"  title="Click : 상품 정보 확인">${purchase.purchaseProd.prodName}<span style="display:none;" class="getPurchaseInfo">/purchase/getPurchase?tranNo=${purchase.tranNo }&currentPage=${resultPage.currentPage}</span><h7 id="${purchase.tranNo }"></h7></td>
			  <td align="left">${purchase.receiverPhone }</td>
			  <td align="left">
				<c:choose>
					<c:when test="${purchase.tranCode.charAt(0)=='3'.charAt(0) }">배송완료</c:when>
					<c:when test="${purchase.tranCode.charAt(0)=='2'.charAt(0) }">배송중</c:when>
					<c:otherwise>구매완료</c:otherwise>
				</c:choose>
		 		<td align="left" class="updateCode">
				<c:choose>
					<c:when test="${purchase.tranCode.charAt(0)=='3'.charAt(0) }"/>
					<c:when test="${purchase.tranCode.charAt(0)=='2'.charAt(0) }">
					<u><a>물건도착</a></u>
					<span style="display:none;">/purchase/updateTranCode?tranNo=${purchase.tranNo }&tranCode=3&currentPage=${resultPage.currentPage }</span>
					</c:when>
				</c:choose>
				</td>
          </c:forEach>
        
        </tbody>
      
      </table>
 	</div>
	<jsp:include page="../common/pageNavigator_new.jsp"/>
</body>

</html>