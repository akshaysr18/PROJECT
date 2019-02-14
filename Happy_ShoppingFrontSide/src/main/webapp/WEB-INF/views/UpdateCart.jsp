<%@ page language="java" contentType="text/html"%>
<%@include file="HeaderFiles.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<form action ="<c:url value='/Cart'/>" method="post">
<table align="center" class="table">
<tr>
<td colspan="2">
<center><h3>Cart</h3></center>
</td>
<tr>
<td>CartItem Id</td>
<td><input type="text" name="cartItem Id" value=${CartItem.CartItemId} readonly/></td>
</tr>



<td colspan="2">
<center><input type="submit" value="Cart"/></center>
</td> 
</tr>
</table>
</form>