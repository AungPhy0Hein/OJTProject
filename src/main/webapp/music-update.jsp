<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="common/header.jsp"></c:import>
<title>Update Music</title>
</head>
<body style="background-image: url('assets/musiclogin.jpg'); background-size: 100%">
<!-- Responsive navbar-->
	<c:import url="common/nav.jsp"></c:import>
	
	<div class="container">
    <form action="mc" method="post">
        <h2 class="text-center">Music Editing</h2>
        
        <input type="hidden" name="mode" value="UPDATE">
        <input type="hidden" name="id" value="${music.id }">
        <h3> <c:out value="ID : ${music.id}"></c:out> </h3>
        <div class="mb-3">
            <label for="name" class="form-label">*Name</label>
            <input type="text" id="name" name="name" value="${music.name }" placeholder="Enter Song Name" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="artist" class="form-label">*Artist</label>
            <input type="text" id="artist" name="artist" value="${music.artist }" placeholder="Enter Artist Name" class="form-control" required="required" autofocus>
        </div>
        
         <div class="mb-3">
            <label for="time" class="form-label">*Time</label>
            <input type="text" id="time" name="time" value="${music.time }" placeholder="Enter Duration" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="genre" class="form-label">*Genre</label>
            <input type="text" id="genre" name="genre" value="${music.genre }" placeholder="Enter Music Genre" class="form-control" required="required" autofocus>
        </div>
        
         <div class="mb-3">
            <label for="date" class="form-label">*ReleaseDate</label>
            <input type="Date" id="date" name="date" value="${music.releasedate }"  class="form-control" required="required" autofocus>
        </div>    	
    	<div class="mb-3">
            <div class="col-sm-9 col-sm-offset-3">
                <span class="help-block">*Required fields</span>
            </div>
        </div>
        
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="reset" class="btn btn-danger">Revert</button>
    </form> <!-- /form -->
</div> <!-- ./container -->
	
	
	<c:import url="common/footer.jsp"></c:import>
</body>
</html>