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
<form action="Addcategory" method="post">
<table align="center" class="table">
<tr>
<td colspan="2"><center><h2>Category</h2></center></td>
</tr>

<tr>
<td>Category Name</td>
<td><input type="text" name="catName" required/></td>
</tr>

<tr>
<td>Category Desc</td>
<td><input type="text" name="catDesc" required/></td>
</tr>

<tr>
<td colspan="2"><center><input type="submit" value="Addcategory"/></center></td>
</tr>
</table>
</form>

<table class="table-bordered">
<tr>
<td>category Id</td>
<td>category Name</td>
<td>category Desc</td>
<td>operations</td>
</tr>

<c:forEach items="${listcategories}" var="category">
<tr>
<td>${category.categoryId}</td>
<td>${category.categoryName}</td>
<td>${category.categoryDesc}</td>
<td>
<a href="<c:url value='/editCategory/${category.categoryId}'/>" class="btn btn-success">Edit</a>
<a href="<c:url value='/deletecategory/${category.categoryId}'/>" class="btn btn-danger">Delete</a>
</td>
</tr>
</c:forEach>
</table>


</body>
</html>