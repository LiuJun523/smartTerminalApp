<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%> 
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Doctor Web Application</title>
	<link rel="stylesheet" href="<%=basePath%>/bootstrap/css/bootstrap.min.css">
  	<link rel="stylesheet" href="<%=basePath%>/css/login.css">
	
	<script src="<%=basePath%>/js/jquery.min.js"></script>
	<script src="<%=basePath%>/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/js/login.js"></script>
</head>
	
<body>
	<!-- Navigation -->
	<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-primary fixed-top">
		<div class="container">
			<a class="navbar-brand" href="main.jsp">Doctor Web</a>
			<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" 
				data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" 
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
		              <a class="nav-link" href="main.jsp">Home</a>
		            </li>
					<li class="nav-item">
		              <a class="nav-link" href="personal.jsp">Personal Info.</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="add.jsp">Add Patients</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="patients.jsp">My Patients</a>
		            </li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<!-- Page Heading/Breadcrumbs -->
      	<h1 class="mt-4 mb-3">Personal Information</h1>
      
		<ol class="breadcrumb">
        	<li class="breadcrumb-item">
          		<a href="main.jsp">Home</a>
        	</li>
        	<li class="breadcrumb-item active">Personal Info.</li>
      	</ol>
      	
      	<!-- Info. Form -->
		<div class="content-wrapper">
			<div class="row">
        		<div class="col-lg-8 mb-4">
        			<form name="info" id="infoForm" novalidate>
        				<div class="control-group form-group">
			              <div class="controls">
			                <label>User ID</label>
			                <input type="text" class="form-control" id="userid" disabled>
			              </div>
			            </div>
			            <div class="control-group form-group">
			              <div class="controls">
			                <label>Name</label>
			                <input type="text" class="form-control" id="name" disabled>
			              </div>
			            </div>
			            <div class="control-group form-group">
			              <div class="controls">
			                <label>Email Address</label>
			                <input type="email" class="form-control" id="email" required data-validation-required-message="Please enter your Email address.">
			              </div>
			            </div>
			            <div class="control-group form-group">
			              <div class="controls">
			                <label>Phone Number</label>
			                <input type="tel" class="form-control" id="phone" required data-validation-required-message="Please enter your phone number.">
			              </div>
			            </div>
			            <div class="control-group form-group">
			              <div class="controls">
			                <label>Department</label>
			                <input type="text" class="form-control" id="department">
			              </div>
			            </div>
			            <div class="control-group form-group">
			              <div class="controls">
			                <label>Password</label>
			                <input type="password" class="form-control" id="password" required data-eye data-validation-required-message="Please enter your password.">
			              </div>
			            </div>

			            <button type="submit" class="btn btn-primary" id="submitButton">Submit</button>
			            <button class="btn btn-light">Cancel</button>
			    	</form>
        		</div>
        	</div>
		</div>
	</div>

    <!-- Footer -->
    <footer class="py-5 bg-light">
      <div class="container">
        <p class="m-0 text-center text-dark">Copyright &copy; 2018 &mdash; Team One</p>
      </div>
      <!-- /.container -->
    </footer>
</body>
</html>