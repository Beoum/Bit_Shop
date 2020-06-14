<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
<div class="container text-center">
		 <nav>
		  <ul class="pagination" >
		  	<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
		 		<li class="disabled">
			</c:if>
			<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
				<li>
			</c:if>
				<c:if test="${!(resultPage.currentPage < resultPage.pageUnit + 1)}">
			      <a href="javascript:fncGetUserList('${ resultPage.beginUnitPage-1}')" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
		      		</a>
		      	</c:if>
		    </li>
		    
			<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
				
				<c:if test="${ resultPage.currentPage == i }">
				    <li class="active">
				    	<a href="javascript:fncGetUserList('${ i }');">${ i }<span class="sr-only">(current)</span></a>
				    </li>
				</c:if>	
				
				<c:if test="${ resultPage.currentPage != i}">	
					<li>
						<a href="javascript:fncGetUserList('${ i }');">${ i }</a>
					</li>
				</c:if>
			</c:forEach>
		    
		     <c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
		  		<li class="disabled">
			</c:if>
			<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
				<li>
			</c:if>
			<c:if test="${resultPage.endUnitPage < resultPage.maxPage }">
		      <a href="javascript:fncGetUserList('${resultPage.endUnitPage+1}')" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		     </c:if>
		    </li>
		  </ul>
		</nav>
		
</div>
