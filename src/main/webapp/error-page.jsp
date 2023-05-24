<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="common/header.jsp"></c:import>
<title>Error Page</title>
</head>
<body>
<!-- Responsive navbar-->
<c:import url="common/nav.jsp"></c:import>

<h1 class="alert alert-danger">${errorTitle }</h1>
<p class="alert alert-danger">${errorMessage }</p>

 <c:import url="common/footer.jsp"></c:import>

</body>
</html>