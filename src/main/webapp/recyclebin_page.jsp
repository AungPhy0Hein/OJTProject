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
		<table id="musicTable" class="table table-striped" style="width: 100%">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Artist</th>
					<th>Time</th>
					<th>Genre</th>
					<th>Release Date</th>

					<th>Actions</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="music" items="${binList }">
					<c:url var="recoverLink" value="mc">
						<c:param name="mode" value="RECOVER"></c:param>
						<c:param name="id" value="${music.id }"></c:param>
					</c:url>
					<c:url var="deleteLink" value="mc">
						<c:param name="mode" value="BIN"></c:param>
						<c:param name="id" value="${music.id }"></c:param>
					</c:url>
					<tr>
						<td>${music.id }</td>
						<td>${music.name }</td>
						<td>${music.artist }</td>
						<td>${music.time }</td>
						<td>${music.genre }</td>
						<td>${music.releasedate }</td>


						<td><a href="${recoverLink }" class="btn btn-primary">Recover</a>
							<a href="${deleteLink }" class="btn btn-danger"
							onclick="return confirm('Are you sure to delete?');">Delete</a></td>

					</tr>

				</c:forEach>

			</tbody>
			<tfoot>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Artist</th>
					<th>Time</th>
					<th>Genre</th>
					<th>Release Date</th>

					<th>Actions</th>



				</tr>
			</tfoot>
		</table>

	</div>
	<c:import url="common/footer.jsp"></c:import>

	<script>
		$(document).ready(function() {
			$('#musicTable').DataTable();
		});
	</script>

</body>
</html>