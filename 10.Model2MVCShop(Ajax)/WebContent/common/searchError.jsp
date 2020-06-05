<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<html>
	<body>
	
		<h3>검색 조건이 잘못 되었습니다.</h3>
		검색한 내용 : ${search.searchKeyword }<br/>
		검색한 조건 : ${search.searchCondition == 0? "상품번호":"" }
		${search.searchCondition == 1? "상품명":"" }
		${search.searchCondition == 2? "가격<br/> 정확한 가격을 입력하셔야합니다.":"" }
	</body>
</html>