<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="common/header.jsp"></c:import>
<title>Update Music</title>
</head>
<body
	style="background-image: url('assets/musiclogin.jpg'); background-size: 100%">
	<!-- Responsive navbar-->
	<c:import url="common/nav.jsp"></c:import>

	<div class="container">
		<form action="mc" method="post">
			<h2 class="text-center">User Editing</h2>

			<input type="hidden" name="mode" value="EDIT"> <input
				type="hidden" name="id" value="${user.id }">
			<h3>
				<c:out value="ID : ${user.id}"></c:out>
			</h3>
			<div class="mb-3">
				<c:out value="UserName : ${user.username}"></c:out>
			</div>

			<div class="mb-3">
				<c:out value="Email : ${user.email}"></c:out>
			</div>
			<div class="mb-3 form-check">
      		<input type="checkbox" class="form-check-input" id="role" name="role" value="true">
      		<label class="form-check-label" for="role">Admin</label>
    	</div>
			<div class="mb-3">
				<div class="col-sm-9 col-sm-offset-3">
					<span class="help-block">*Required fields</span>
				</div>
			</div>

			<button type="submit" class="btn btn-primary">Update</button>
			<button type="reset" class="btn btn-danger">Revert</button>
		</form>
		<!-- /form -->
	</div>
	<!-- ./container -->


	<c:import url="common/footer.jsp"></c:import>
</body>
</html>