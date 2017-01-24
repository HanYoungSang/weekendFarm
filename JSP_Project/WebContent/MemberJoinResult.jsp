<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<c:if test="${param.JOINRESULT == -1}">
<br>
ID, 패스워드, 이름이 없습니다.
<p>
</c:if>

<c:if test="${param.JOINRESULT == -2}">
<br>
가입된 E-MAIL 주소입니다.
<p>
</c:if>
<c:if test="${param.JOINRESULT == -3}">
<br>
수정 중 오류가 발생했습니다.
<p>
</c:if>
<c:if test="${param.JOINRESULT == 1}">
<br>
정상적으로 수정되었습니다.
<p>
</c:if>
