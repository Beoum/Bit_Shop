<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
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
	
		//�˻� / page �ΰ��� ��� ���  Event  ó��	
		function fncGetUserList(currentPage) {
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/purchase/listPurchase").submit();
		}
		
		function fncGetPurchase(tranNo){
			self.location="/purchase/getPurchase?tranNo="+tranNo + "&currentPage=${resultPage.currentPage}";
		}
		
		// �������� ���� Event  ó��(Click)	
		 $(function() {
			$( "td:nth-child(1)" ).on("click" , function() {
 				var queryString = $(this).text();
				var tranNo = queryString.substring(queryString.indexOf("tranNo")+7, queryString.indexOf("tPage")-1);
				var paymentOption = "����";
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
										paymentOption = "�ſ�";
									}
									var link = "<br/><h6><u><a id='detailButton' onclick='javascript:fncGetPurchase("+tranNo+")'>�󼼺���</a></u></h6>";
									var displayValue = "<h6>"
																+"�޴»�� : "+JSONData.receiverName+"<br/>"
																+"�޴��ּ� : "+JSONData.divyAddr+"<br/>"
																+"���űݾ� : "+JSONData.purchaseProd.price+"<br/>"
																+"���Ź�� : "+paymentOption+"<br/>"
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
	       <h3>���� �����ȸ</h3>
	    </div>
	    
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
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
            <th align="center">��ǰ��<br/><h7>(�󼼺��� : ��ǰ��Ŭ��)</h7></th>
            <th align="left">��ȭ��ȣ</th>
            <th align="left">�����Ȳ</th>
            <th align="left">��������</th>
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="purchase" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="left"  title="Click : ��ǰ ���� Ȯ��">${purchase.purchaseProd.prodName}<span style="display:none;" class="getPurchaseInfo">/purchase/getPurchase?tranNo=${purchase.tranNo }&currentPage=${resultPage.currentPage}</span><h7 id="${purchase.tranNo }"></h7></td>
			  <td align="left">${purchase.receiverPhone }</td>
			  <td align="left">
				<c:choose>
					<c:when test="${purchase.tranCode.charAt(0)=='3'.charAt(0) }">��ۿϷ�</c:when>
					<c:when test="${purchase.tranCode.charAt(0)=='2'.charAt(0) }">�����</c:when>
					<c:otherwise>���ſϷ�</c:otherwise>
				</c:choose>
		 		<td align="left" class="updateCode">
				<c:choose>
					<c:when test="${purchase.tranCode.charAt(0)=='3'.charAt(0) }"/>
					<c:when test="${purchase.tranCode.charAt(0)=='2'.charAt(0) }">
					<u><a>���ǵ���</a></u>
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