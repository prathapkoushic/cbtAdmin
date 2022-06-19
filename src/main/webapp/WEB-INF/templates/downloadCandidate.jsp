<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

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
<meta charset="ISO-8859-1">

<style>
table th, td {
	boarder: 1px solid black;
}

#ecportCandidate {
	margin-top: 100px;
}
</style>
</head>
<body>
	<div class="container">

		<jsp:include page='header.jsp'>
			<jsp:param name="articleId" value="" />
		</jsp:include>

		<form class="form-horizontal" id="ecportCandidate"
			action="downloadCandidate" method="post">
			<div class="form-group">
				<label class="control-label col-sm-5" for="select exam nmae">Select
					Exam Name :</label>
				<div class="col-sm-3">
					<select id="postId" class="form-control" name="exam_name">
						<option value="">--Select Exam Name--</option>
						<c:forEach var="m" items="${values}">
							<option value="${m.exam_name}">${m.exam_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-5" for="select location">Select
					Location Name :</label>
				<div class="col-sm-3">
					<select id="postId" class="form-control" name="location_name">
						<option value="">--Select Location Name--</option>
						<c:forEach var="m" items="${value}">
							<option value="${m.location_name}">${m.location_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-5" for="select location">Select
					Location Name :</label>
				<div class="col-sm-3">
					<select id="postId" class="form-control" name="session_timings">
						<option value="">--Select Session Timings</option>
						<c:forEach var="m" items="${value}">
							<option value="${m.session_timings}">${m.session_timings}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-md-12">
				<div class="preBtn text-center">
					<button type="submit" class="btn btn-primary submit"
						value="Download">Download</button>
				</div>
			</div>
		</form>
		<div class="footer">
			<div class="row">
				<div class="col-md-6">
					<div class="footer-info"></div>
				</div>
				<div class="col-md-6 text-right">
					<div class="footer-info">
						<span>&copy; 2020 Timing Technologies India Pvt Ltd </span>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>