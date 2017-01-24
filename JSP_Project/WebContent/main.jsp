<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트 주말 농장에 오신 걸 환영합니다.</title>
</head>
<body>
<!-- <h2> 메인 페이지</h2> -->

<table border=1>
	<tr>
		<td width=1500 valign="top" height=80> 
		<table border=0>
			<tr height=50>
				<td width="300" align="center" valign="middle"> 
					<a href="main.jsp">메인 페이지</a></td>
				<td width="300" align="center" valign="middle"> 
					<a href="main.jsp?BODY_PATH=Intro.html">회사 소개</a></td>
				<td width="300" align="center" valign="middle"> 
					<a href="resource-view">예약 하기</a></td>
				<td width="300" align="center" valign="middle"> 
					<a href="bbs-list">
<!-- 					<a href="main.jsp?BODY_PATH=BBSWrite.jsp"> -->
					게시판</a></td>	
				<td width="300" align="center" valign="middle"> 
					<c:choose>
					<c:when test="${sessionScope.LOGIN_ID == null }">
						<a href="main.jsp?BODY_PATH=MemberJoin.jsp"> 회원가입 </a>
					</c:when>
					<c:otherwise>
						<a href="main.jsp?BODY_PATH=MemberModify.jsp"> 회원수정 </a>
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr height="25">
			<td colspan="5"> 
				<c:choose>
					<c:when test="${sessionScope.LOGIN_ID == null }">
						<jsp:include page="LoginWindow.jsp"></jsp:include>
					</c:when>
					<c:otherwise>
						<jsp:include page="LogoutWindow.jsp"></jsp:include>
					</c:otherwise>
				</c:choose>
			</td>
			</tr>
		</table>
		
		</td>
	</tr>
	<tr>
		<td width="1500" valign="top" height="500" align="center">
			<c:if test="${param.BODY_PATH==null}">
				<jsp:include page="/main_bottom.html"></jsp:include>
			</c:if>
			<c:if test="${param.BODY_PATH!=null}">
				<jsp:include page="${param.BODY_PATH}"></jsp:include>
			</c:if>
		</td>
	</tr>

</table>
<h5> Copyright@1234-5678 by 한영상</h5>
</body>
</html>