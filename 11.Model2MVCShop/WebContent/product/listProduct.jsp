<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
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
	
		// 검색 / page 두가지 경우 모두  Event  처리 
		function fncGetUserList(currentPage) {
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/product/listProduct?menu=${param.menu}&orderBy=${param.orderBy }").submit();
		}
		
		
		// 검색  Event  처리 
		 $(function() {
			 $( "button.btn.btn-default" ).on("click" , function() {
				 var keyword = $("input[name='searchKeyword']").val();
				 if(keyword.length == 0){
					 alert('검색키워드를 입력해주세요!');
					 history.back();
				 }
				fncGetUserList(1);
			});
			
			 $("input[name='searchKeyword']").on("keydown" , function(key) {
				 if (key.keyCode == 13) {
					 var keyword = $("input[name='searchKeyword']").val();
					 if(keyword.length == 0){
						 alert('검색키워드를 입력해주세요!');
						 history.back();
					 }
					fncGetUserList(1);
				}
			});
		 });
		
		function fncGetProduct(prodNo) {
			 self.location ="/product/getProduct?prodNo="+prodNo+"&menu=${param.menu}&currentPage=${resultPage.currentPage}&orderBy=${param.orderBy }";
		};
		
		// userId 에 회원정보보기  Event  처리 (double Click)
		 $(function() {
			 
			$(  "td:nth-child(2)" ).on("click" , function() {

				var string = $( this ).text().trim();
				var prodNo = string.substring(string.indexOf("&")-5, string.indexOf("&"));
				if(!$("#"+prodNo+"" ).html().length){
						$.ajax( 
								{
									url : "/product/json/getProduct?prodNo="+prodNo ,
									method : "GET" ,
									dataType : "json" ,
									headers : {
										"Accept" : "application/json",
										"Content-Type" : "application/json"
									},
									success : function(JSONData , status) {
										var link = "<br/><h6><u><a id='detailButton' onclick='javascript:fncGetProduct("+prodNo+")'>상세보기</a></u></h6>";
										var img = "<img src='"+ JSONData.fileName +"' width='100' height='100'/>";
										var displayValue = "<h6>"
																	+"상품설명 : "+JSONData.prodDetail+"<br/>"
																	+"상품이미지 : <br/>" + img
																	+"</h6>";
	
										$("h6").remove();
										$(  "td:nth-child(3) td" ).remove("br");
										$( "#"+prodNo+"" ).html(displayValue);
										$( "#"+prodNo+"" ).parent().next().append(link);
									}
							});
				}else{
					$("h6").remove();
					$(  "td:nth-child(3) *" ).remove("br");
				}
				
			});
			
			$(function (){
				$("h3").on("click", function(){
					 self.location = "/product/listProduct?menu=${param.menu}";
				})
			})
			
			
			$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
			$("h7").css("color" , "red");
			
			$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
		});	
	
	</script>
	
</head>

<body>
	
	<jsp:include page="/layout/toolbar.jsp" />
	
	<div class="container">
	
		<div class="page-header text-info">
	       <h3>${param.menu == 'search' ? '상품목록조회':'상품관리' }</h3>
	    </div>
	    
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0" ${!empty search.searchCondition && search.searchCondition==0 ? "selected":"" }>상품번호</option>
						<option value="1" ${!empty search.searchCondition && search.searchCondition==1 ? "selected":"" }>상품명</option>
						<option value="2" ${!empty search.searchCondition && search.searchCondition==2 ? "selected":"" }>상품가격</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
					<c:if test="${empty param.searchKeyword }">
						<input type="text" name="searchKeyword"  class="form-control" >
					</c:if>
					<c:if test="${!empty param.searchKeyword }">
						<input type="text" name="searchKeyword" value="${param.searchKeyword }" class="form-control" >
					</c:if>
				  </div>
				  
				  <button type="button" class="btn btn-default">검색</button>
				  
				  <input type="hidden" id="currentPage" name="currentPage" value="${resultPage.currentPage}"/>
				  
				</form>
	    	</div>
	    	
		</div>
		      
      	<c:if test="${fn:length(list) != 0}">
			<tr>
					<div float:right>
					<c:if test="${empty param.searchKeyword}">
						<u><a href="/product/listProduct?orderBy=DESC&menu=${param.menu }">가격높은순</a></u>
						<u><a href="/product/listProduct?orderBy=ASC&menu=${param.menu }">가격낮은순</a></u></div>
					</c:if>	
					<c:if test="${!empty param.searchKeyword}">
						<u><a href="/product/listProduct?orderBy=DESC&menu=${param.menu }&searchKeyword=${param.searchKeyword}&searchCondition=${search.searchCondition}">가격높은순</a></u>
						<u><a href="/product/listProduct?orderBy=ASC&menu=${param.menu }&searchKeyword=${param.searchKeyword}&searchCondition=${search.searchCondition}">가격낮은순</a></u>
					</c:if>	
					<c:if test="${!empty param.menu }">
						<input type="hidden" name="orderBy" value="${param.orderBy }"/>
					</c:if>
			</tr>
      </c:if>
		
      <table class="table table-hover table-striped" >

        <thead>
          <tr>
            <th class="text-center">No</th>
            <th class="text-center">상품명<br/><h7>(상세보기 : 상품명클릭)</h7></th>
            <th class="text-center">가격</th>
            <th class="text-center">등록일</th>
            <th class="text-center">현재상태</th>
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="product" items="${list}" varStatus="status">
		  <c:set var="i" value="${ i+1 }" />
			<tr>
			  <td class="text-center">${ resultPage.totalCount - (i-1) - (resultPage.currentPage-1) * resultPage.pageSize }</td>
			  <td class="text-center"  title="Click : 상품 정보 확인">${product.prodName}<span style="display:none;">${product.prodNo}&tranNo=${product.proTranCode }&menu=${param.menu }</span><h7 id="${product.prodNo }"></h7></td>
			  <td class="text-center">${product.price}</td>
			  <td class="text-center">${product.regDate}</td>
			  <td class="text-center">
				<c:if test="${param.menu == 'manage' }">
					<c:choose>
						<c:when test="${list[i].proTranCode.charAt(0) == '1'.charAt(0) }">
								구매완료<br/><u><a>배송하기</a></u><span style="display:none;">prodNo=${product.prodNo }&tranCode=2&currentPage=${resultPage.currentPage}&searchKeyword=${param.searchKeyword}&menu=${param.menu}</span>
						</c:when>
						<c:when test="${product.proTranCode.charAt(0) == '2'.charAt(0) }">배송중</c:when>
						<c:when test="${product.proTranCode.charAt(0) == '3'.charAt(0) }">배송완료</c:when>
						<c:otherwise>
							판매중
						</c:otherwise>
					</c:choose>
				</c:if>	
				<c:if test="${param.menu == 'search' }">
					<c:choose>
						<c:when test="${product.proTranCode.charAt(0) == '0'.charAt(0) }">
							판매중
						</c:when>
						<c:otherwise>
							재고없음
						</c:otherwise>
					</c:choose>
				</c:if>	
				</td>	
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
 	</div>
	<jsp:include page="../common/pageNavigator_new.jsp"/>
</body>

</html>