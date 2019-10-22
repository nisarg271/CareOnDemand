<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
<h2>Vote</h2>
<form:form method="post" action="voteFunction" modelAttribute="vote">

	<table>
	<tr>
		<td>POINTS :</td>
	</tr>
	<tr>
		<td><form:radiobutton path="points" value="1" label="1" /></td>
	</tr>
	<tr>
		<td><form:radiobutton path="points" value="2" label="2" /></td>
	</tr>
	<tr>
		<td><form:radiobutton path="points" value="3" label="3"/></td>
	</tr>
	<tr>
		<td><form:radiobutton path="points" value="5" label="5"/></td>
	</tr>
	<tr>
		<td><form:radiobutton path="points" value="8" label="8"/></td>
	</tr>
	<tr>
		<td><form:radiobutton path="points" value="13" label="13"/></td>
	</tr>
	<tr>
		<td><form:radiobutton path="points" value="21" label="21"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Submit Vote"/>
		</td>
	</tr>
</table>	
	
</form:form>
</body>
</html>
