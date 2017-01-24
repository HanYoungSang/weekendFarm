<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<p>
<center>
<form action="member-join" method="post">

이메일 : <input type="text" name="EMAIL"> <p/>
이 름 :   <input type="text" name="NAME"> <p/>
패스워드 : <input type="password" name="PASSWORD"> <p/>

<jsp:include page="MemberJoinResult.jsp?JOINRESULT=${param.JOINRESULT}"></jsp:include>
<input type="submit" value="회원가입"> &nbsp;
<input type="reset" value="취소">

</form>
</center>
