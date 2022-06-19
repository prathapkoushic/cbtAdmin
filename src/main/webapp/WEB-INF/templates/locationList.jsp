<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.time.LocalDateTime"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="en">

<head>
<title>:: RRC ::</title>
<link rel="icon" href="images/logo.png">
<meta charset="utf-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">

<link href="css/font-awesome.min.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap"
	rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
</head>

<body>
	<div class="container">
		<jsp:include page='header.jsp'>
			<jsp:param name="articleId" value="" />
		</jsp:include>
		<form class="form-inline" id="location" action="examListUrl"
			method="post">
			<div class="main-dashboard">
				<div class="text-center" style="padding-top: 30px;">

							<div class="form-group row">
								<div class="row mb-2">

									<div class="col-sm-4  float-sm-center">
										<label for="selectpost">Exam List :<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-sm-8 sm-float-center">

										<select class="form-control" name="exam_name">
											<option value="">--Select Exam Name --</option>
											<c:forEach var="m" items="${values}">

												<option value="${m.exam_name}">${m.exam_name}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<br> <br> <br>
								<div class="form-group row">
									<div class="row mb-2">

										<div class="col-sm-4  float-sm-center">
											<label for="selectpost">Location Name :<span
												style="color: red">*</span>
											</label>
										</div>
										<div class="col-sm-8 sm-float-center">

											<select class="form-control" name="location_name">
												<option value="">--Select Exam Name --</option>
												<c:forEach var="m1" items="${value}">

													<option value="${m1.location_name}">${m1.location_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<br> <br>
									<div class="form-group row">
										<div class="row mb-2">

											<div class="col-sm-4  float-sm-center">
												<label for="selectpost">SessionTimings :<span
													style="color: red">*</span>
												</label>
											</div>
											<div class="col-sm-8 sm-float-center">

												<select class="form-control" name="session_timings">
													<option value="">--Select Session timing --</option>
													<c:forEach var="m2" items="${value}">

														<option value="${m2.session_timings}">${m2.session_timings}</option>
													</c:forEach>
												</select>
											</div>
										</div>


										<div class="form-group row">
											<div class="row mb-2">

												<div class="col-sm-4  float-sm-center">
													<label for="selectpost"> Exam Start Flag :<span
														style="color: red">*</span>
													</label>
												</div>
												<div class="col-sm-8 sm-float-center">
													<input type="radio" name="start_flag" value="true">Yes
													<input type="radio" name="start_flag" value="false">No
												</div>
											</div>
										</div>
										<br>
										<div class="form-group row">
											<div class="row mb-2">

												<div class="col-sm-4  float-sm-center">
													<label for="password">Passowrd :</label>
												</div>
												<div class="col-sm-8 sm-float-center">

													<input type="password" class="form-control ct_formCtrl"
														id="password" placeholder="Enter password" name="password">
													<!-- <span id="lblError2" style="color: red;visibility:hidden">This field is required</span>-->
												</div>
											</div>
										</div>
									</div>
								</div>
</div>

								<div class="col-md-12">
									<div class="preBtn text-center">
										<button id="submit" type="submit"
											class="btn btn-primary mgn-top">Update</button>
									</div>
								</div>
							</div>
							</div>
		</form>
		<h4 align="center" style="color:red">${msg}</h4>
		<!--Main dashboard end-->
		<div class="clearfix"></div>
		<div class="footer">
			<div class="row">
				<div class="col-md-4">
					<div class="footer-info"></div>

				</div>
				<div class="col-md-6 text-center">
					<div class="footer-info">
						<span>&copy; 2020 Timing Technologies India Pvt Ltd.</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--Container end-->
	<!-- <script>
		$(document)
				.ready(
						function() {
							$("#location")
									.validate(
											{
												rules : {
													location_name : {
														required : true,
													}

												},
												submitHandler : function(from) {
													//e.preventDefault();// for demo
													alert('Successfully Created ExamLocation With Sessition:)'); // for demo
													return true; // for demo
												}

											});
						});
	</script>
 -->
</body>
</html>


