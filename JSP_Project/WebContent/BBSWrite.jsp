<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>title</title>
</head>
<body>
<h1>게시판 글쓰기</h1>
<form action="bbs-post" method="post">
	제목 : <input type="text" name="title"> <br/><br/>
	<textarea rows="5" cols="70" name="content"></textarea>
<p>
<c:if test="${sessionScope.LOGIN_ID != null }">
	<input type="submit" value="저장"> &nbsp;
</c:if>
<c:if test="${sessionScope.LOGIN_ID == null }">
	<input type="submit" value="로그인해주세요" disabled> &nbsp;
</c:if>
	<input type="reset" value="취소">
</form>
<form action="bbs-list" method="get">
<input type="submit" value="목록으로">
</form>
</body>
</html>