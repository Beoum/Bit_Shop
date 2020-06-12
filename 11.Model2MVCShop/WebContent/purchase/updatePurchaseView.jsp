<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script type="text/javascript" src="../javascript/calendar.js"></script>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
		body {
            padding-top : 50px;
        }
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
		// =========== "날짜 선택" ========
		$( function() {
		    $( "#datepicker" ).datepicker({
		      showOn: "button",
		      buttonImage: "../images/ct_icon_date.gif",
		      buttonImageOnly: true,
		      dateFormat: 'yymmdd'
		    });
		});
	
	
		//============= "수정"  Event 연결 =============
		$( function() {
		    $("button").on("click", function(){
		    	$("form").attr("method" , "POST").attr("action" , "/purchase/updatePurchase?tranNo=${param.tranNo }").submit();
		    });
		});
		
		
		//============= "취소"  Event 처리 및  연결 =============
		$(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]").on("click" , function() {
				history.back();
			});
		});	
		
		// validation check 추가하세요?
				
	</script>
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-center">
	       <h3 class=" text-info">구매 정보 수정</h3>
	    </div>
	    
	    <!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal">
		<input type="hidden" name="currentPage" value="${param.currentPage }"/>
		  <div class="form-group">
		    <label for="password" class="col-sm-offset-1 col-sm-3 control-label">구매자아이디</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="buyerId" value="${purchase.buyer.userId}" disabled>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">구매방법</label>
		    <div class="col-sm-4">
		      	<select 	name="paymentOption" class="form-control">
				<option value="1" selected="selected">현금구매</option>
				<option value="2">신용구매</option>
			</select>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label class="col-sm-offset-1 col-sm-3 control-label">받는사람 이름</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="receiverName" value="${purchase.receiverName }">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">받는사람 연락처</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="receiverPhone"  value="${purchase.receiverPhone}">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">받는 주소지</label>
		     <div class="col-sm-2">
		     	<input type="text" name="divyAddr" class="form-control" value="${purchase.divyAddr }"/>
		    </div>
		   </div>
		  <br/>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">구매요청사항</label>
		     <div class="col-sm-2">
		     	<input type="text" name="divyRequest" class="form-control" value="${purchase.divyRequest }"/>
		    </div>
			</div>
				    		  
		  <br/>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">배송희망 날짜</label>
		     <div class="col-sm-2">
		     	<input id="datepicker" type="text" class="form-control" name="divyDate" value="${purchase.divyDate }">
		    </div>	
		    </div>
		
			<br/>
		
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >수 &nbsp;정</button>
			  <a class="btn btn-primary btn" href="#" role="button">취 &nbsp;소</a>
		    </div>
		  </div>
		  
		</form>
		<!-- form Start /////////////////////////////////////-->
	    
 	</div>
	<!--  화면구성 div Start /////////////////////////////////////-->
 	
</body>

</html>