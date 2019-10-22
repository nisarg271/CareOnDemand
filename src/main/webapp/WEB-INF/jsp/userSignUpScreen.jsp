<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
<h2>User Sign Up</h2>
<form:form method="post" action="userSignUpFunction" modelAttribute="userSignUp">
	<table>
	<tr>
		<td><form:label path="userName">User Name: </form:label></td>
		<td><form:input path="userName" /></td> 
	</tr>
	<tr>
		<td><form:label path="password">Password: </form:label></td>
		<td><form:password path="password" /></td> 
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Sign Up"/>
		</td>
	</tr>
</table>	
	
</form:form>
</body>
</html>
