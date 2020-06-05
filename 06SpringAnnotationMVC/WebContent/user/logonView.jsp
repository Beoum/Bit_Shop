<%@ page contentType="text/html;charset=euc-kr" %>

<html>
	<head><title>@MVC Test Page</title></head>
	<body>

		<!-- /////// ModelAndView return 하는 Controller 사용연습 /////// -->
<!--  		  <form  method="post" action="logon01.do"> -->
<!-- 		 <form  method="post" action="logon02.do">   -->
<!--  		<form  method="post" action="logon03.do">  -->
		<form  method="post" action="logon04.do">
		<!-- <form  method="post" action="logon05.do"> -->
		
		<!-- /////// String return 하는 Controller 사용연습 /////// -->
		<!-- <form  method="post" action="logon06.do"> -->
<!-- 		<form  method="post" action="logon07.do"> -->

			아  이  디 : <input type="text" name="userId" value=""><br/><br/>
			패스워드 : <input type="text" name="password" value=""><br/><br/>
			<input type="submit" name="submit" value="Enter"/>

		</form>
		
	</body>
</html>