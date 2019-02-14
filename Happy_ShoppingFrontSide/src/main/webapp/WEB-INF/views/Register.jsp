<%@ page language="java" contentType="text/html"%>
<%@include file="HeaderFiles.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript">
	function fillShippingAddress(form) {
		
		form.shippingAddr.value=form.customerAddr.value
		if(form.shippingaddressform.checked==false)
			{
			form.shippingAddr.value=""
			}
	    
	}
	</script>

<style>
table, th, td {
boder: 2px solid blue;
}
}
th, td {
  padding: 8px;
  text-align: left;
}
table#t01 
{  

  background-color: #FFE4C4;
}
</style>
<form:form action="registerUser" modelAttribute="UserDetail" method="post">
<table id="t01" align="center" class="table-bordered">
<tr>
<td colspan="2"><center><h2>Sign Up</h2></center></td>
</tr>

<tr>
<td>Name</td>
<td><form:input path="customerName" required="required"/></td>
</tr>

<tr>
<td>Customer Address</td>
<td><form:textarea path="customerAddr" required="required" row="5" id="customerAddr"/></td>
</tr>
<tr>

<td colspan="2">
Check this if Customer address is same as Shipping address
<input type="checkbox" onclick="fillShippingAddress(this.form)" id="shippingaddressform">
</td>
</tr>



<td>Shipping Address</td>
<td><form:textarea path="shippingAddr" required="required" row="5"/></td>
</tr>
<tr>
<td>Mobile Number</td>
<td><form:input path="mobileNumber" required="required"/></td>
</tr>

<tr>
<td>User Name</td>
<td><form:input path="username" required="required"/></td>
</tr>

<tr>
<td>Password</td>
<td><form:input path="password" required="required"/></td>
</tr>
<tr>
<td><!--Enabled  --></td>
<td><form:hidden path="enabled"/></td>
</tr>

<tr>
<td><!-- Role --></td>
<td><form:hidden path="role"/></td>

</tr>
<tr>
<td colspan="2"><center><input type="submit" value="Register User"/></center></td>
</tr>
</table>
</form:form>

</body>
</html>