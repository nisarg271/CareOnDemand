<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script type="text/javascript">
	function deleteUser(userName){
		var result = confirm("Are you sure you want to delete the user?");
		if (result) {
			userListForm.action = "deleteUser"; 
			userListForm.deleteUserName.value = userName;
			userListForm.submit();
		}
	}
	
	function activateUser(userName){
		var result = confirm("Are you sure you want to activate the user?");
		if (result) {
			userListForm.action = "activateUser";
			userListForm.activeUserName.value = userName;
			userListForm.submit();
		}
	}
</script>
</head>
<body>
<h2>User List:</h2>
<form:form method="post" modelAttribute="user" name="userListForm">
	<input type="hidden" id="deleteUserName" name= "deleteUserName" value="">
	<input type="hidden" id="activeUserName" name= "activeUserName" value="">
	<table>
		<c:forEach var="user" items="${userList}">
		<tr>
			<td colspan="2">${user.userName}</td>
			<td>${user.status}</td>
			<td>
				<a href="javascript:deleteUser('${user.userName}')">delete</a>	
			</td>
			<td>
				<c:if test="${user.status=='pending'}">
					<a href="javascript:activateUser('${user.userName}')">activate</a>
				</c:if>	
			</td>
		</tr>
		</c:forEach>
	</table>
	
</form:form>
</body>
</html>
