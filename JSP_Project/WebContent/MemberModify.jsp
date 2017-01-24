<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>title</title>
</head>
<body>


<p>
<center>
<form action="member-modify" method="post">

이메일 : <input type="text" name="EMAIL" value="${LOGIN_ID}" readonly="readonly"> <p/>
이 름 :   <input type="text" name="NAME" value="${LOGIN_NAME}"> <p/>
패스워드 : <input type="password" name="PASSWORD"> <p/>

<jsp:include page="MemberJoinResult.jsp?JoinResult=${param.JOINRESULT}"></jsp:include>
<input type="submit" value="수정"> &nbsp;
<input type="reset" value="취소">

</form>
</center>

</body>
</html>