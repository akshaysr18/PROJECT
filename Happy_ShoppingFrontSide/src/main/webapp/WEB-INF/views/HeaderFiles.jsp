<%@ page language="java" contentType="text/html"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<nav class="navbar navbar-expand-md navbar-dark bg-success text-white">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Happy Shopping</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
</div>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
        <c:if test="${!sessionScope.loggedIn}">
            <ul class="navbar-nav ">
                <li class="nav-item">
                   <a class="nav-link" href="<c:url value="/Home"/>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/login"/>">login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/About Us"/>">AboutUs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/Contact Us"/>">ContactUs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/Register"/>">SignUp</a>
                </li>
                </ul>
                </c:if>
                <c:if test="${sessionScope.loggedIn}">
					<c:if test="${sessionScope.role=='Admin'}">
                <ul class="navbar-nav ">
               <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/Category"/>">Category</a>
                  </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/Supplier"/>">Supplier</a>
             
                 </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/Product"/>">Product</a>
                </li>
                </ul>
					</c:if>
					</c:if>
                
                <c:if test="${sessionScope.role=='User'}">
                <ul class="navbar-nav ">
                 <li class="nav-item">
                   <a class="nav-link" href="<c:url value="/UserHome"/>">Home</a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="<c:url value="/ProductDisplay"/>">ProductCatalog</a>
                </li>
                <li class="nav-item">
                   <a class="nav-link" href="<c:url value="/ShowCart"/>">Cart</a>
                </li>
                		</ul>
					</c:if>
	
<c:if test="${sessionScope.loggedIn}">
		<div class="nav navbar-nav navbar-right">
			<font color="white" face="calibri" size="2">Welcome : ${sessionScope.username}</font>
			<a href="<c:url value="/perform_logout"/>" class="btn btn-primary">Logout</a>
		</div>
		</c:if>
            
        </div>
    </div>
</nav>



