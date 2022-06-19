<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>UploadFile</title>
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

<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>

</head>

<style>
.btnsubmit {
	margin-bottom: 50px;
}
button:hover {
	background: #104a36 !important;
}
</style>

<body>
	<div class="container">
		<jsp:include page='header.jsp'>
			<jsp:param name="articleId" value="" />
		</jsp:include>

		<div class="createPostBlock">
			<div class="row"></div>
			<form class="form-horizontal" id="createxam"
				action="saveanswerPaperUploadUrl" method="post"
				enctype="multipart/form-data">

				<div class="form-group row">
					<label class="control-label col-sm-5" for="selectpost">
						Select Exam Name :<span style="color: red">*</span>
					</label>
					<div class="col-sm-3">
						<select class="form-control" name="examName">
							<option value="">--Select Exam Name --</option>
							<c:forEach var="m" items="${values}">

								<option value="${m.exam_name}">${m.exam_name}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group row">
					<label class="control-label col-sm-5" for="selectpost">
						Select Set Number :<span style="color: red">*</span>
					</label>
					<div class="col-sm-3">
						<select class="form-control" name="setNo">
							<option value="">--Select Set no --</option>
							<option value="SET-1">SET-1</option>
							<option value="SET-2">SET-2</option>

						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-5" for="fullname">Upload
						File :<span style="color: red">*</span>
					</label>
					<div class="col-sm-3">
						<input type="file" class="form-control" id="file" name="file">


					</div>
				</div>




				<div class="col-md-12">
					<div class="preBtn text-center">
						<button id="submit" type="submit" class="btn btn-primary mgn-top">Add
							Answers</button>
					</div>
				</div>
			</form>



		</div>

		<div class="clearfix"></div>
		<div class="footer">
			<div class="row">
				<div class="col-md-6">
					<div class="footer-info"></div>

				</div>
				<div class="col-md-6 text-right">
					<div class="footer-info">
						<span>&copy; 2020 Timing Technologies India Pvt Ltd.</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--Container end-->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#createxam").validate({
				rules : {
					examName : {
						required : true,
					},
					setNo : {
						required : true,
					},
					file : {
						required : true,
					}
				},
				submitHandler : function(from) {
					alert('Successfully added Answers'); // for demo
					return true; // for demo
				}
			});
		});
	</script>
</body>
</html>