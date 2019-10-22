<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Schedule Output</h2>
	<table>
	<tr>
		<th>Date</th>
		<th>Name</th>
	</tr>
	<c:forEach var="schedule" items="${finalScheduleMap}">
		<%-- <tr bgcolor="${schedule.value.backgroundColor}"> --%>
		<tr>
			<td>${schedule.key}</td>
			<td>${schedule.value.name}</td>
		</tr>
	</c:forEach>
	</table>	
</body>
</html>
