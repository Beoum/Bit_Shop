<%@ page contentType="text/html;charset=euc-kr" %>

<html>
	<head><title> @MVC Test View Page</title></head>
	<body>
	
		<hr/>
		
		<h3>reaquestScope 에 저장된 내용 추출</h3><br>
		requestScope.userId ==>  ${ requestScope.userId }<br/>
		requestScope.user.userId ==> ${ requestScope.user.userId }<br/>
		requestScope.password ==> ${ requestScope.password }<br/>
		requestScope.user.password ==> ${ requestScope.user.password }<br/>
		requestScope.message ==> ${ requestScope.message }<br/>
		
		<hr/>
		
		<h3>sessionScope 에 저장된 내용 추출</h3><br>
		sessionScope.userId ==> ${ sessionScope.userId }<br/>
		sessionScope.user.userId ==> ${ sessionScope.user.userId }<br/>
		sessionScope.password ==> ${ sessionScope.password }<br/>
		sessionScope.user.password ==> ${ sessionScope.user.password }<br/>
		
		<hr/>
		
		<h3>request 의 parameter  전달되는 내용 추출</h3><br>
		param.message ==> ${ param.message }<br/>
		
		<br/><br/>
		
		<a href = "logonViewModelAndView.do">logonViewModelAndView.do</a><br/><br/>
		<a href = "logonViewString.do">logonViewString.do</a>
		
	</body>
</html>