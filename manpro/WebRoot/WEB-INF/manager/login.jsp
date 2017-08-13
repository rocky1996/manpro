<%@page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<title>登录页面</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath }/user/login" method="post">
			账号:<input type="text" name="account"><br/>
			密码:<input type="password" name="password"><br/>
			<input type="checkbox" name="autologin">下次自动登录<br/>
			<input type="submit" value="登录"/>
		</form>
		${uu}
	</body>
</html>