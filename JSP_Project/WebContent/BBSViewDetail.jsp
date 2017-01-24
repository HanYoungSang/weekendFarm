<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bbsItem" class="project.BBSItem" />
<jsp:setProperty property="seqNo" name="bbsItem" value="${param.SEQ_NO }"/>
<% bbsItem.readDB(); %>

<h3>게시글 읽기</h3>

[제목] ${bbsItem.title} <br> 
[작성자] ${bbsItem.writer} <br>
[작성일시] ${bbsItem.date} ${bbsItem.time} <br>
--------------------------------------- <br>
${bbsItem.content}
<br><p>
<A href="bbs-list?LAST_SEQ_NO=${param.LIST_SEQ }" > [목록으로] </A> <p>
<%-- <jsp:getProperty property="seqNo" name="bbsItem"/> <br> --%>
<%-- <jsp:getProperty property="title" name="bbsItem"/> <br> --%>
<%-- <jsp:getProperty property="writer" name="bbsItem"/> <br> --%>
<%-- <jsp:getProperty property="date" name="bbsItem"/> <br> --%>
<%-- <jsp:getProperty property="content" name="bbsItem"/> <br> --%>