<%@ page language="java" contentType="text/html"%>
<%@include file="HeaderFiles.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  

<div class="row">

  <c:forEach items = "${productList}" var="product" >



    <div class = "col-sm-4 col-md-3">

      <a href = "<c:url value="/TotalProductDisplay/${product.productId}" />" class = "thumbnail">

         <img src="<c:url value="/resources/images/${product.productId}.jpg" />" style=" max-width: 350px; min-width:350px;       max-height: 450px;   min-height:450px;"alt = "Generic placeholder thumbnail"/>

      </a>

      

      <p align="center"> ${product.productName} </p>

      <p align="center"> Price : INR ${product.price}/- </p>

      <p align="center"> Stock : ${product.stock} </p>

      

    </div>     

  </c:forEach>

</div>  

</body>

</html>