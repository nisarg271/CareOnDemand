<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
<h2>Voting Successful</h2>
<form:form method="post" action="voteScreen">
	<table>
		<!-- <tr>
			<td colspan="2">
				<input type="submit" value="Vote Again"/>
			</td>
		</tr> -->
		<tr>
			<td>${message}</td>
		</tr>
	</table>	
</form:form>
</body>
</html>
