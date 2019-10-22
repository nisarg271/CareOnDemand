<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<script type="text/javascript">
function signUpFunction(){
	loginForm.action = "userSignUpScreen";
	loginForm.submit();
}

</script>
<body>
<h2>Enter Credentials:</h2>
<form:form method="post" action="loginFunction" modelAttribute="loginUser" name="loginForm">

	${message}
	<table>
	<tr>
		<td colspan="2" style="color: red;"><form:errors path="userName" /></td>
	</tr>
	<tr>
		<td><form:label path="userName" >User Name: </form:label></td>
		<td><form:input path="userName" /></td> 
	</tr>
	<tr>
		<td><form:label path="password">Password: </form:label></td>
		<td><form:password path="password" /></td>
	</tr>
	<tr>
		<td align="left">
			<input type="submit" value="Login"/>
		</td>
		<td align="right">
			Not a member ? <input type="button" id="signUpButton" name="signUpButton" value="Sign Up" onclick="signUpFunction();">
		<td>
	</tr>
</table>	
	
</form:form>
</body>
</html>
