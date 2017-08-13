<%@page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<title>注册用户</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath }/user/register" method="post">
			<div>
				<tr>
					<td>账号:</td>
					<td><input type="text" name="account" value="${formbean.account }">${formbean.errors.account }</td><br/>
				</tr>
			</div>
			<div>
				<tr>
					<td>邮箱:</td>
					<td><input type="text" name="email" value="${formbean.email }">${formbean.errors.email }</td><br/>
				</tr>
			</div>
			<div>
				<tr>
					<td>昵称:</td>
					<td><input type="text" name="nick" value="${formbean.nick }">${formbean.errors.nick }</td>
				</tr>
			</div>
			<div>
				<tr>
					<td>密码:</td>
					<td><input type="password" name="password" value="${formbean.password }">${formbean.errors.password }</td><br/>
				</tr>
			</div>
			<div>
				<tr>
					<td>确认密码:</td>
					<td><input type="password" name="password2" value="${formbean.password2 }">${formbean.errors.password2 }</td><br/>
				</tr>
			</div>
				<tr>
					<td><input type="submit" value="注册"/></td>
					<td><input type="reset" value="重置"/></td>
				</tr>
			</div>
		</form>
		${uu }
	</body>
</html>
