<%@page language="java" contentType="text/html"%>
<%@include file="HeaderFiles.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="perform_login" method="post">
<br>
<p style="font-size:40px;">${ErrorMessage }</p>

</br>
<table border="1" align="center">
	<tr bgcolor="bg-dark ">
		<td colspan="2"> <center> Login</center></td>
	</tr>
	<tr bgcolor="bg-dark">
		<td>UserName</td>
		<td><input type="text" name="username" required/></td>
	</tr>
	<tr bgcolor="bg-success text-white">
		<td>Password</td>
		<td><input type="password" name="password" required/></td>
	</tr>
	<tr bgcolor="bg-dark "> 
		<td colspan="2">
			<center><input type="submit" value="login"/></center>
		</td>
		
	</tr>
	
</table>

</form>
</body>
</html>