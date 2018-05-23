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
      	<h1 class="mt-4 mb-3">My Patients</h1>
      
		<ol class="breadcrumb">
        	<li class="breadcrumb-item">
          		<a href="main.jsp">Home</a>
        	</li>
        	<li class="breadcrumb-item active">My Patients</li>
      	</ol>
      	
      	<div class="card mb-3">
	      	<div class="input-group">
	      		<input type="text" class="form-control" placeholder="Search for...">
	           	<span class="input-group-btn">
	           		<button class="btn btn-secondary" type="button">Go!</button>
	           	</span>
	        </div>
      	</div>
      	
      	<!-- Info. Table -->
      	<div class="main-panel">
	        <div class="row">
	        	<!-- Patient One -->
	        	<div class="col-lg-4 mb-4">
	        		<div class="card card-outline-primary h-100">
	        			<h3 class="card-header bg-primary text-white"></h3>
		            	<div class="card-body">
		              		<div class="display-4">Patient One</div>
		              		<div class="font-italic">male</div>
		            	</div>
		            	<ul class="list-group list-group-flush">
			              	<li class="list-group-item">40</li>
			              	<li class="list-group-item">110</li>
			              	<li class="list-group-item">
			              		<button type="button" class="btn btn-primary btn-fw">Follow</button>
			              	</li>
		            	</ul>
		          	</div>
		       </div>
		       
		       <!-- Patient Two -->
	        	<div class="col-lg-4 mb-4">
	        		<div class="card card-outline-primary h-100">
	        			<h3 class="card-header bg-primary text-white"></h3>
		            	<div class="card-body">
		              		<div class="display-4">Patient Two</div>
		              		<div class="font-italic">female</div>
		            	</div>
		            	<ul class="list-group list-group-flush">
			              	<li class="list-group-item">35</li>
			              	<li class="list-group-item">999</li>
			              	<li class="list-group-item">
			              		<button type="button" class="btn btn-secondary btn-fw">Unfollow</button>
			              	</li>
		            	</ul>
		          	</div>
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