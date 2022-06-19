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
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap"
	rel="stylesheet">
</head>


<body>
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="topNav">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><img src="images/logo.png">
						<span>
							<h3>Online Admin Panel</h3>
					</span> </a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<!-- <li><a href="#">HOME</a></li>-->
					<li><a href="logouturl">Logout</a></li>
				</ul>
			</div>
		</nav>
		<div class="row">
			<div class="col-md-12">
				<div class="adminBlock">
					<div class="btn-group-vertical">
						<a href="posturl" class="btn btn-primary">Post</a> <a
							href="pindexurl" class="btn btn-primary">Add Exam</a> <a
							href="createlocation" class="btn btn-primary">Exam Location
							With Session</a> <a href="cindexurl" class="btn btn-primary">Candidate</a>
						<a href="addQuestionUrl" class="btn btn-primary">Question</a> <a
							href="assignSetNumber" class="btn btn-primary">Assign Set
							Number</a> <a href="answerPaperUpload" class="btn btn-primary">Answer
							Paper Upload</a> <a href="pushQuestions" class="btn btn-primary">Push
							Questions</a>
						<!-- <a href="uplaodQuestionPaper" class="btn btn-primary">Upload Question Paper</a> -->


					</div>
				</div>
			</div>
		</div>
		<!-- <div class="footer">
			<div class="row">
				<div class="col-md-3">
					<div class="footer-info"></div>
				</div>
				<div class="col-md-6 text-center">
					<div class="footer-info">
						<span>&copy; 2020 Timing Technologies India Pvt Ltd.</span>
					</div>
				</div>
			</div>
		</div>
 -->	</div>
	<!--Container end-->
</body>
</html>
