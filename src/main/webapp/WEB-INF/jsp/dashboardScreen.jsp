<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script type="text/javascript">

	function resetValues(){
		dashboardForm.resetFlag.value = "Y";
		dashboardForm.submit();
	}
</script>
</head>
<body>
<h2>Results</h2>
<form:form name="dashboardForm" method="post" action="dashboardScreen">
<input type="hidden" id="resetFlag" name= "resetFlag" value="N">
	<p>VOTERS ==> ${voters}</p>
	<table border="1">
		<tr>
			<td>Points</td>
			<td>Votes</td>
		</tr>
		<c:forEach var="dataItem" items="${data}">
		<tr>
			<td>${dataItem.key} :</td>
			<td>${dataItem.value}</td>
		</tr>
		</c:forEach>
		<tr>
			<td>
				<input type="submit" value="Refresh"/>
			</td>
			<td>
				<input type="button" onclick="resetValues()" value="Reset"/>
			</td>
		</tr>
	</table>	
</form:form>
</body>
</html>
