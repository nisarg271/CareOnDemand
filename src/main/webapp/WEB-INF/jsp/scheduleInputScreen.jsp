<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
var globalRowCounter = 1;
$(document).ready(function(){
    $("#addCF").click(function(){
    	//alert($(this).data('counter'));
    	//alert(globalRowCounter);
    	globalRowCounter++;
        $("#memberTable").append("<tr><td><input type='text' id='name_"+globalRowCounter+"' name='name_"+globalRowCounter+"' /></td><td><select id='slots_"+globalRowCounter+"' name='slots_"+globalRowCounter+"'><option value='1'>1</option><option value='2'>2</option></select><a href='javascript:void(0);' class='remCF'>Remove</a></td></tr>");
    });
    $("#memberTable").on('click','.remCF',function(){
        $(this).parent().parent().remove();
        globalRowCounter--;
    });
});
</script>
<body>
	<h2>Schedule Input</h2>
	<form:form method="post" action="generateSchedule">
		<table id="memberTable">
			<tr>
				<th>Name</th>
				<th>No. of slots / cycle</th>
			</tr>
			<%-- <c:forEach begin="1" end="5" var="index"> --%>
			<tr>
				<td>
					<input type="text" id="name_0" name="name_0"/>
				</td>
				<td>
					<select id="slots_0" name="slots_0">
						<option value="1">1</option>
						<option value="2">2</option>
					</select>
					<a href="javascript:void(0);" id="addCF">Add</a>
				</td>
			</tr>
		</table>
		<table id="buttonTable">
			<tr>
				<td colspan="2">
					<input type="submit" value="Generate Schedule"/>
				</td>
			</tr>
		</table>	
	</form:form>
</body>
</html>