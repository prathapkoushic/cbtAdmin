<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CBT Admin Panel</title>

<link rel="icon" href=images/logo.png">
<link rel="icon" href="images/logo.png">
<meta charset="utf-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<link rel="stylesheet"
	href="js/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="js/datatables-responsive/css/responsive.bootstrap4.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/datatables/jquery.dataTables.min.js"></script>
<script src="js/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="js/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="js/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<style type="text/css">
.buttonAslink {
	background: none !important;
	border: none;
	padding: 0 !important;
	/*optional*/
	font-family: arial, sans-serif;
	/*input has OS specific font-family*/
	color: #069;
	text-decoration: underline;
	cursor: pointer;
}
</style>
</head>

<body>

	<nav class="navbar navbar-default">
		<div class="topNav">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img src="../images/logo.png">
					<span>
						<h3>Online Exam Admin Panel</h3>
				</span> </a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/homeurl"><i class="fa fa-home fa-fw"></i>HOME</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/logouturl"> <i class="fa fa-logout fa-fw"></i>Logout</a></li>
			</ul>

		</div>
	</nav>

</body>
</html>