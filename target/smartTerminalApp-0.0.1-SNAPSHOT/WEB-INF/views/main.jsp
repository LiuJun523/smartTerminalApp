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
		<!-- Breadcrumbs -->
		<ol class="breadcrumb">
        	<li class="breadcrumb-item">
          		<a href="main.jsp">Home</a>
        	</li>
      	</ol>
      	
      	<!-- Details -->
		<div class="content-wrapper">
			<div class="row">
				<!-- Patient One -->
				<div class="col-lg-4 mb-4">
					<div class="card h-100">
						<h4 class="card-header">Patient One</h4>
						<div class="card-body">
							<p class="card-text">Detail information about Patient One.</p>
						</div>
	            		<div class="card-footer">
	              			<a class="btn btn-primary" href="">Read More</a>
	            		</div>
	          		</div>
	          	</div>
	          	
	          	<!-- Patient Two -->
				<div class="col-lg-4 mb-4">
					<div class="card h-100">
						<h4 class="card-header">Patient Two</h4>
						<div class="card-body">
							<p class="card-text">Detail information about Patient Two.</p>
						</div>
	            		<div class="card-footer">
	              			<a class="btn btn-primary" href="">Read More</a>
	            		</div>
	          		</div>
	          	</div>
	    	</div>	
		</div>
	</div>
</body>
</html>