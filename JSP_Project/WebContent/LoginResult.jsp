<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:if test="${param.LOGINRESULT == -10}">
 ID가 없습니다.
</c:if>
<c:if test="${param.LOGINRESULT == -20}">
 패스워드가 틀렸습니다.
</c:if>


