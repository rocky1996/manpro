<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>ACAT项目管理</title>
	</head>
	<body style="text-align: center">
		<br/>
		<h1>ACAT项目管理</h1>
		<br/>
		<table align="center" border="0" width="88%">
			<c:if test="${sessionScope.u == null }">
				<div>
					<a href="${pageContext.request.contextPath }/user/skip1"><button>注册</button></a>
					<a href="${pageContext.request.contextPath }/user/skip2"><button>登录</button></a>
				</div>
			</c:if>
			<c:if test="${sessionScope.u != null }">
				<div>
					<strong>欢迎您:${sessionScope.u.nick }</strong>
					<a href="${pageContext.request.contextPath }/user/logout"><button>退出</button></a>
				</div>
			</c:if>
		</table>
		${uu}
	</body>
</html>