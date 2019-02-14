<%@ page language="java" contentType="text/html"%>
<%@include file="HeaderFiles.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 15px;
}
</style>
<form action="AddSupplier" method="post">
<table align="center" class="table">
<tr>
<td colspan="2"><center><h2>Supplier</h2></center></td>
</tr>

<tr>
<td>Supplier Name</td>
<td><input type="text" name="SupName" required/></td>
</tr>

<tr>
<td>SupplierAddr</td>
<td><input type="text" name="SupAddr" required/></td>
</tr>

<tr>
<td colspan="2"><center><input type="submit" value="AddSupplier"/></center></td>
</tr>
</table>
</form>

<table class="table-bordered">
<tr>
<td>Supplier Id</td>
<td>Supplier Name</td>
<td>Supplier Addr</td>
<td>operations</td>
</tr>

<c:forEach items="${listSuppliers}" var="supplier">
<tr>
<td>${supplier.supplierId}</td>
<td>${supplier.supplierName}</td>
<td>${supplier.supplierAddr}</td>
<td>
<a href="<c:url value='/editSupplier/${supplier.supplierId}'/>" class="btn btn-success">Edit</a>
<a href="<c:url value='/deleteSupplier/${supplier.supplierId}'/>" class="btn btn-danger">Delete</a>
</td>
</tr>
</c:forEach>
</table>


</body>
</html>