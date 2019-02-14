<%@ page language="java" contentType="text/html"%>
<%@include file="HeaderFiles.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<form action ="<c:url value='/UpdateSupplier'/>" method="post">
<table align="center" class="table">
<tr>
<td colspan="2">
<center><h3>Supplier</h3></center>
</td>
<tr>
<td>Supplier Id</td>
<td><input type="text" name="SupId" value=${Supplier.supplierId} readonly/></td>
</tr>


<tr>
<td>Supplier Name</td>
<td><input type="text" name="SupName" value=${Supplier.supplierName}></td>
</tr>
<tr>
<td>Supplier Addr </td>
<td><input type="text" name="SupAddr" value=${Supplier.supplierAddr}></td>
</tr>
<tr>
<td colspan="2">
<center><input type="submit" value="Update Supplier"/></center>
</td> 
</tr>
</table>
</form>