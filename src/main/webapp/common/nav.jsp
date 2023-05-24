<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="mc"><img id="logo"
			src="assets/music pic.jpg" alt="logo" /> MUSIC STORE</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="mc">Home</a></li>

				<c:if test="${user.role == 'admin' }">
					<li class="nav-item"><a class="nav-link" href="mc?mode=PAGE">Add
							Music</a></li>
						
				</c:if>

				<c:if test="${user.role == 'user' }">
					<li class="nav-item"><a class="nav-link" href="mc?mode=DPAGE">Downloaded
							Music</a></li>
				</c:if>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">${user.username }</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="navbarDropdown">

						<c:if test="${user.role == 'admin' }">
							<li><a class="dropdown-item" href="mc?mode=BPAGE">Recently Delete</a></li>
									<li><a class="dropdown-item" href="user">Sign Up</a></li>
									<li><a class="dropdown-item" href="user?mode=LIST">UserList</a></li>
						</c:if>
						<li><a class="dropdown-item" href="logout">Logout</a></li>

					</ul></li>
			</ul>
		</div>
	</div>
</nav>