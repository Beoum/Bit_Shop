<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

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
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
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
		
		//============= 회원정보수정 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			 $( ".btn-primary" ).on("click" , function() {
				var menu = "${param.menu}";
				 if(menu == "search"){
					 location.href="/purchase/addPurchaseView?prodNo=${ product.prodNo}";	 
				 }else{
					 location.href="/product/updateProductView?prodNo=${ product.prodNo}";
				 }
 				 
			 });
			
			 $( ".btn-danger" ).on("click" , function() {
				 location.href="/product/listProduct?menu=${param.menu}&currentPage=${param.currentPage}&orderBy=${param.orderBy }";
			});
			 
			 $("#categoryList").on("click", function(){
				 var hierarchy = "카테고리 구조 : <br/><br/>";
				 $.ajax( 
							{
								url : "/category/json/getCategoryHierarchy?categoryId=${product.category.categoryId}",
								method : "GET" ,
								dataType : "json" ,
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
								success : function(JSONData , status) {
									for(var i = $(JSONData).length-1 ; i >= 0; i--){
										hierarchy += "<h7>"+JSONData[i].name + "</h7><br>";
									}
									$("#hierarchy").html(hierarchy);
								}
						}); 
			 });
		});
		
	</script>
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header">
	       <h3 class=" text-info">상품상세조회</h3>
	       <h5 class="text-muted">상픔 정보를 <strong class="text-danger">최신정보로 관리</strong>해 주세요.</h5>
	    </div>
	
		<div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>상품번호</strong></div>
			<div class="col-xs-8 col-md-4">${product.prodNo}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>카테고리</strong></div>
			<div class="col-xs-8 col-md-2" id="categoryList">${ product.category.name}<h6 id="hierarchy"></h6></div>
		</div>
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>상품명</strong></div>
			<div class="col-xs-8 col-md-4">${ product.prodName}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>상품이미지</strong></div>
			<div class="col-xs-8 col-md-4"><img 	src="${product.fileName}" width="500" height="500" align="center"/></div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>상품상세정보</strong></div>
			<div class="col-xs-8 col-md-4">${product.prodDetail }</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>제조일자</strong></div>
			<div class="col-xs-8 col-md-4">${product.manuDate }</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>가격</strong></div>
			<div class="col-xs-8 col-md-4">${product.price }</div>
		</div>
		
		<hr/>
		
	<c:if test="${!empty product.regDate }">
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>등록일자</strong></div>
			<div class="col-xs-8 col-md-4">${product.regDate }</div>
		</div>
	</c:if>
		
		<hr/>
		<div class="row">
	  		<div class="col-md-12 text-center ">
	  		<c:if test="${product.proTranCode == '0' && param.menu == 'manage'}">
	  			<button type="button" class="btn btn-primary">수정</button>
	  		</c:if>
	  		<c:if test="${product.proTranCode == '0' && param.menu == 'search'}">
	  			<button type="button" class="btn btn-primary">구매</button>
	  		</c:if>
	  			<button type="button" class="btn btn-danger">이전</button>
	  		</div>
		</div>
		<br/>
 	</div>
 	<!--  화면구성 div Start /////////////////////////////////////-->

</body>

</html>