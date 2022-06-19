<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>CBT AdminPanel</title>
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
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">

</head>
<body>


	<div class="container">

		<jsp:include page='header.jsp'>
			<jsp:param name="articleId" value="" />
		</jsp:include>

		<h4>Successfully uploaded</h4>

		<div class="footer">
			<div class="row">
				<div class="col-md-6">
					<div class="footer-info"></div>
				</div>
				<div class="col-md-6 text-right">
					<div class="footer-info">
						<span>&copy;2020 Timing Technologies India Pvt Ltd </span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>