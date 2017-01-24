<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<center>
	<h2>게시판</h2>
	<table border="1" cellpading="5" cellspacing="0">
		<tr>
			<td width=40 align="center" > 순번 </td>
			<td width=300> 제목</td>
			<td width=80 align="center"> 작성자 </td>
			<td width=160 align="center"> 작성일자</td>
<!-- 			<td width=70 align="center"> 작성시간</td> -->
			<td width=50 align="center"> 조회수</td>
		</tr>
		
		<c:forEach var="cnt" begin="0" end="${BBS_LIST.listSize -1}">
		<tr>
		<td align="center"> ${BBS_LIST.seqNo[cnt]}  </td>
		<td> 
		<a href="main.jsp?BODY_PATH=BBS-READ?SEQ_NO=${BBS_LIST.seqNo[cnt]}&LIST_SEQ=${BBS_LIST.seqNo[0]+1}">  
			${BBS_LIST.title[cnt]} 
			</a>
			</td>
		<td align="center"> ${BBS_LIST.writer[cnt]} </td>
		<td align="center"> ${BBS_LIST.date[cnt]} ${BBS_LIST.time[cnt]} </td>
<%-- 		<td align="center"> ${BBS_LIST.time[cnt]} </td> --%>
		<td align="center"> ${BBS_LIST.count[cnt]} </td>
		</tr>
		</c:forEach>
	</table>
	<table border="0">
	<tr>
	<td width="650">&nbsp;</td>
	</tr>
	<tr>
		<td align="right">
			<a href="main.jsp?BODY_PATH=BBSWrite.jsp"> 글쓰기</a>
		</td>
	</tr>
	</table>
	
	
	<!-- <A href="bbs-list" > 처음 페이지 </A> -->
	<br/>
	<c:if test="${!BBS_LIST.firstPage }">
	<A href="bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[0] + 6}" > 이전 페이지 </A>
	</c:if>
	
	<c:if test="${!BBS_LIST.lastPage }">
<%-- 	<A href="bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[0] + 6}" > 이전 페이지 </A> --%>
		<A href="bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[BBS_LIST.listSize -1]}" > 다음 페이지 </A>
	</c:if>	
	<%-- <A href="bbs-list?LAST_SEQ_NO=${ (BBS_LIST.listSize - 1) %5}"> 마지막 페이지 </A> --%>

</center>

