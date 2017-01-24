<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그아웃</title>
</head>
<body>
<form action="member-logout" method="get" onsubmit="CURRENT_URL.value=window.location.href">
		안녕하세요. ${sessionScope.LOGIN_NAME }님  &nbsp; &nbsp;
		<input type="hidden" name="CURRENT_URL">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>