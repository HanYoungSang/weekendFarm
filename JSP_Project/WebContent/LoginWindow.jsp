<table>
	<tr>
		<form action="member-login" method="post" onsubmit="CURRENT_URL.value=window.location.href">
		<td>E-mail : </td>
		<td><input type="text" name="EMAIL" width="15"> </td>
		<td>Password : </td>
		<td><input type="password" name="PASSWORD" width="15"> </td>
		<td>
			<input type="hidden" name="CURRENT_URL">
			<input type="submit" value="LOGIN">
		</td>
		<td width="200" align="center" valign="middle">
			<jsp:include page="LoginResult.jsp?LoginResult=${LoginResult}"></jsp:include></td>	
		</form>
		
	</tr> 
	
</table>
