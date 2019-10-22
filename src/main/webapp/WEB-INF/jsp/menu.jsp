<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>Menu</p>
<!-- <p>
<a href="contacts">Contact</a>
</p> -->
 
<ul>
	<li> <a href="voteScreen">Vote</a></li>
  	<c:if test="${sessionScope.userName=='nisarg'}">
  		<li><a href="createUserScreen">Create User</a></li>
  		<li><a href="viewUsers">View Users</a></li>
  		<li><a href="dashboardScreen">Dashboard</a></li>
  		<li><a href="scheduleInputScreen">Generate C/W Schedule</a></li>
  	</c:if>
  	<c:if test="${sessionScope.userName=='vv'}">
  		<li><a href="scheduleInputScreen">Generate C/W Schedule</a></li>
  	</c:if>
  	<li><a href="signOut">Sign Out</a></li>
</ul>