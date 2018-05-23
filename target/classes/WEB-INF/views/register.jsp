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

<body class="login-page">
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						<img src="<%=basePath%>/img/logo.jpg">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">Register</h4>
							<form method="POST">
								<div class="form-group-lg" id="useridDiv">
									<div class="form-group">
										<label for="userid">User ID</label>
										<input id="userid" type="text" class="form-control" name="userid" required autofocus>
									</div>
									<div class="hidden text-center text-danger" id="useridMsg">
										<span class="glyphicon glyphicon-exclamation-sign"></span>
									</div>
								</div>
								
								<div class="form-group">
									<label for="name">Name</label>
									<input id="name" type="text" class="form-control" name="name" required>
								</div>

								<div class="form-group">
									<label for="email">Email Address</label>
									<input id="email" type="email" class="form-control" name="email" required>
								</div>

								<div class="form-group">
									<label for="password">Password</label>
									<input id="password" type="password" class="form-control" name="password" required data-eye>
								</div>

								<div class="form-group no-margin">
									<button id="btn_register" type="button" class="btn btn-primary btn-block">Register</button>
								</div>
								<div class="margin-top20 text-center">
									Already have an account? <a href="index.jsp">Login</a>
								</div>
							</form>
						</div>
					</div>
					<div class="footer">
						Copyright &copy; 2018 &mdash; Team One
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>