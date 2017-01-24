<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br>
<center>
<h2> 예약되지 않은 구역을 분양받을 수 있습니다. <br> </h2>
<h5>
[분양방법] <br>
미예약을 클릭하시면 예약이 완료됩니다. <br>
예약을 하시면 예약자 이름이 해당위치에 표시됩니다. <br/></br>
[취소방법] <br>
예약한 사용자 이름을 클릭하면 예약이 취소됩니다. <br>
 
</h5>


<table border="1">
<tr height="200">
<c:forEach var="cnt" begin="0" end="19">
	
		<td width="80" align="center" valign="middle"> 
		
<%-- 		${RESOURCE[cnt].seq} <br/> --%>
<%-- 		${RESOURCE[cnt].year} <br/> --%>
<%-- 		${RESOURCE[cnt].row} <br/> --%>
<%-- 		${RESOURCE[cnt].column} <br/> --%>
		<c:if test="${RESOURCE[cnt].book_yn!='Y'}">
			<a href="booking-resource?SEQ=${RESOURCE[cnt].seq}">
				미예약<br/> </a>
		</c:if>
<%-- 		${RESOURCE[cnt].book_yn} <br/> --%>
		<c:if test="${RESOURCE[cnt].book_yn=='Y'}">
			예약완료
		</c:if>
		<c:if test="${RESOURCE[cnt].book_seq != 0}">
<%-- 			${RESOURCE[cnt].book_seq} <br/> --%>
			${RESOURCE[cnt].final_mod_date} <br/>
			${RESOURCE[cnt].final_mod_time} <br/>
			<c:if test="${RESOURCE[cnt].final_person == sessionScope.LOGIN_ID}">
				<a href="BookingCancel?SEQ=${RESOURCE[cnt].seq}&BOOKSEQ=${RESOURCE[cnt].book_seq}"> 
			</c:if>
				예약자 : ${RESOURCE[cnt].final_person} <br/>
			<c:if test="${RESOURCE[cnt].final_person == sessionScope.LOGIN_ID}">
				</a> 
			</c:if>	

		</c:if>
		
		</td>
	<c:if test="${RESOURCE[cnt].column==10 and RESOURCE[cnt].row==1}">
		</tr><tr height="200">
	</c:if>
	

</c:forEach>
</tr>
</table>
</center>
