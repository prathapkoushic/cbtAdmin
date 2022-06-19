<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>:: RRC ::</title>
<link rel="icon" href="images/logo.png">
<meta charset="utf-8">

<link rel="stylesheet" href="css/style.css" type="text/css" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap"
	rel="stylesheet">
</head>
<style>
.adminBlock .row label {
	margin-bottom: 40px;
}

.adminBlock .row .mgn-left a {
	margin-bottom: 20px;
	width: 200px;
}

.adminBlock .row .mgn-left {
	text-align: left;
	margin-top: -3px;
}
</style>

<script>
	$(document).ready(function() {
		$("#btn1").click(function() {
			console.log("Hey , i'm working fine");
			$("#sub-main1").load("Createexam.jsp");

		});

		$('#btn2').click(function() {

			$('#sub-main1').load('exam_list.jsp');

		});

	});
</script>

<body>
	<div class="container">
		<jsp:include page='header.jsp'>
			<jsp:param name="articleId" value="" />
		</jsp:include>
		<div class="adminBlock">
			<div class="row">
				<div class="col-md-6">
					<label class="control-label">Click to Create Location
						Session:</label> <label class="control-label">Click to Location
						Session List :</label> <label class="control-label">Edit
						Locations:</label>
				</div>
				<div class="col-md-6">
					<div class="mgn-left">
						<a href="createlocation" class="btn btn-primary">Create
							Location Session </a>
					</div>
					<div class="mgn-left">
						<a href="locationSessionList" class="btn btn-primary">Sessions List </a>
					</div>
					<div class="mgn-left">
						<a href="editLocationSession" class="btn btn-primary">Edit/Delete Session</a>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="row">
				<div class="col-md-3">
					<div class="footer-info">
						<span></span>
					</div>
				</div>
				<div class="col-md-6 text-center">
					<div class="footer-info">
						<span>&copy;2020 Timing Technologies India Pvt Ltd. </span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--Container end-->
</body>
</html>