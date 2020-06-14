<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script type="text/javascript" src="../javascript/calendar.js"></script>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
	<style>
		body {
            padding-top : 50px;
        }
    </style>
    
	<script type="text/javascript">
		// 날짜 선택
		$( function() {
		    $( "#datepicker" ).datepicker({
		      showOn: "button",
		      buttonImage: "../images/ct_icon_date.gif",
		      buttonImageOnly: true,
		      dateFormat: 'yymmdd'
		    });
		});
	
	
		// 수정  Event 연결 
		$( function() {
		    $("#submitButton").on("click", function(){
		    	$("form").attr("method" , "POST").attr("action" , "/purchase/addPurchase").submit();
		    });
		});
		
		
		// 취소  Event 처리 및  연결
		$(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]").on("click" , function() {
				history.back();
			});
		});	
	</script>
	
</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />
	
	<div class="container">
	
		<div class="page-header text-center">
	       <h3 class=" text-info">구매정보 등록</h3>
	       <h5 class="text-muted">구매정보</h5>
	    </div>
	    
		<form class="form-horizontal">
			<input type="hidden" name="userId" value="${user.userId }" />
		  <div class="form-group">
		    <label for="password" class="col-sm-offset-1 col-sm-3 control-label">상품번호</label>
		    <div class="col-sm-4">
		      ${param.prodNo }
		      <input type="hidden" name="prodNo" value="${param.prodNo }"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">상품명</label>
		    <div class="col-sm-4">
		      ${product.prodName }
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">상품상세정보</label>
		    <div class="col-sm-4">
		      ${product.prodDetail }
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label class="col-sm-offset-1 col-sm-3 control-label">제조일자</label>
		    <div class="col-sm-4">
		      ${product.manuDate }
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">가격</label>
		    <div class="col-sm-4">
		      ${product.price }
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">등록일자</label>
		    <div class="col-sm-4">
		      ${product.regDate }
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">구매자아이디</label>
		    <div class="col-sm-4">
		      <input type="text" name="userId" value="${user.userId }" class="form-control" disabled/>
		    </div>
		  </div>

		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">구매방법</label>
		    <div class="col-sm-4">
		     <select 	name="paymentOption"		class="form-control" >
				<option value="1" selected="selected">현금구매</option>
				<option value="2">신용구매</option>
			</select>
		    </div>
		  </div>
		  		  		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">받는사람</label>
		    <div class="col-sm-4">
		      <input type="text" name="receiverName" 	class="form-control" value="${user.userName }" />
		    </div>
		  </div>		  		  		  
		  		  		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">받는사람 연락처</label>
		    <div class="col-sm-4">
		     <input 	type="text" name="receiverPhone" class="form-control" value="${user.phone }" />
		    </div>
		  </div>			  		  		  
		  		  		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">배송지</label>
		    <div class="col-sm-4">
		     <input 	type="text" name="divyAddr" class="form-control" value="${user.addr }" />
		    </div>
		  </div>			  		  		  
		  		  		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">구매요청사항</label>
		    <div class="col-sm-4">
		     <input		type="text" name="divyRequest" 	class="form-control"/>
		    </div>
		  </div>			  		  		  
		  		  		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">배송희망일자</label>
		    <div class="col-sm-4">
		  		<input id="datepicker" type="text" class="form-control" name="divyDate">
		    </div>
		  </div>			  
		  
		  
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary" id="submitButton" >구 &nbsp;매</button>
			  <a class="btn btn-danger btn" href="#" role="button">취 &nbsp;소</a>
		    </div>
		  </div>
		</form>
	    
 	</div>
 	
</body>

</html>