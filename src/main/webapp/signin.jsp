<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="common/header.jsp"></c:import>
  
	<title>Sign in Page</title>
</head>
<body style="background-image: url('assets/mclogin.jpg'); background-size: 100%">
	
	<div class="container">
    <form action="login" method="post"> 
        <h2 class="mb-3 text-center" style="color: white">Please Sign in To use Music Store</h2>
        
        <input type="hidden" name="mode" value="LOGIN">
        
        <c:if test="${loginFail }">
        <div class="mb-3">
        <span class="alert alert-danger"> Username or Password is incorrect</span>
        </div>
        </c:if>
        
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" id="email" name="email" placeholder="Enter Your Email" class="form-control" required="required" autofocus>
        </div>
        
         <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter Your Password" class="form-control" required="required" autofocus>
        </div>
        
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form> <!-- /form -->
     <p style="color: white"> Don't have an account? <a href="user">Please Sign up</a> </p>
</div> <!-- ./container -->
	
	
<c:import url="common/footer.jsp"></c:import>
</body>
</html>