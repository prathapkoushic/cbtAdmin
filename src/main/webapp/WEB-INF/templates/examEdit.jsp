<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>PostEdit</title>
<link rel="icon" href="../images/logo.png">
<meta charset="utf-8">
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap"
	rel="stylesheet">
</head>

<body>
	<div class="container">
		<jsp:include page='header.jsp'>
			<jsp:param name="articleId" value="" />
		</jsp:include>

		<form class="form-horizontal">
			<div id="ssc">
				<div class="main-dashboard">
					<div class="sscBlock">
						<div class="row">
							<div class="col-md-12">
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
											<tr>

												<th>Exam code</th>
												<th>Exam name</th>
												<th>Exam Date</th>
												<th>Exam Duration</th>
												<th>MFE Question</th>
												<th>NMFE Question</th>
												<th>Post Name</th>
												<th>Exam Description</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${values}" var="value">
												<tr>

													<td>${value.exam_code}</td>
													<td>${value.exam_name}</td>
													<td>${value.exam_date}</td>
													<td>${value.duration}</td>
													<td>${value.marks_per_question}</td>
													<td>${value.negativemarks_per_question}</td>
													<td>${value.postId}</td>
													<td>${value.exam_description}</td>
													<td><a href="editurl/${value.id}"><input
															type="hidden" id="edit" name="edit" value="${value.id}">Edit</a></td>
													<td><a href="deleteurl/${value.id}"><input
															type="hidden" id="edit" name="edit" value="${value.id}">Delete</a></td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="clearfix"></div>

		<!--Wpp Block End-->

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
</body>
</html>

