<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="common/header.jsp"></c:import>
<title>Music Store</title>

</head>
<body style="background-image: url('assets/musiclogin.jpg'); background-size: 100%">
	<!-- Responsive navbar-->
	<c:import url="common/nav.jsp"></c:import>

	<!-- Page content-->
	<div class="container mt-5">
		<table id="userTable" class="table table-striped" style="width: 100%">
			<thead>
				<tr>
					<th>ID</th>
					<th>UserName</th>
					<th>Email</th>
					<th>Role</th>
					
				
						<th>Actions</th>
				
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList }">

					<c:url var="editLink" value="user">
						<c:param name="mode" value="LOAD"></c:param>
						<c:param name="id" value="${user.id }"></c:param>
					</c:url>

					

					<c:url var="deleteLink" value="user">
						<c:param name="mode" value="DELETE"></c:param>
						<c:param name="id" value="${user.id }"></c:param>
					</c:url>
					<tr>
						<td>${user.id }</td>
						<td>${user.username }</td>
						<td>${user.email }</td>
						<td>${user.role }</td>
				

					
							
						
							<td><a href="${editLink }" class="btn btn-primary">Edit</a>
								<a href="${deleteLink }" class="btn btn-danger"
								>Delete</a>
							</td>
						
					</tr>
				</c:forEach>

			</tbody>
			<tfoot>
				<tr>
					<th>ID</th>
					<th>UserName</th>
					<th>Email</th>
					<th>Role</th>
					
				
						<th>Actions</th>
				
				</tr>
			</tfoot>
		</table>

	</div>
	<c:import url="common/footer.jsp"></c:import>

	<script>
		$(document).ready(function() {
			$('#userTable').DataTable();
		});
	</script>

</body>
</html>
