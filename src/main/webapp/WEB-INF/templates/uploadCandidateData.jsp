<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<style>
/* Loading Spinner */
.spinner {
	margin: 0;
	width: 70px;
	height: 18px;
	margin: -35px 0 0 -9px;
	position: absolute;
	top: 50%;
	left: 50%;
	text-align: center
}

.spinner>div {
	width: 18px;
	height: 18px;
	background-color: #333;
	border-radius: 100%;
	display: inline-block;
	-webkit-animation: bouncedelay 1.4s infinite ease-in-out;
	animation: bouncedelay 1.4s infinite ease-in-out;
	-webkit-animation-fill-mode: both;
	animation-fill-mode: both
}

.spinner .bounce1 {
	-webkit-animation-delay: -.32s;
	animation-delay: -.32s
}

.spinner .bounce2 {
	-webkit-animation-delay: -.16s;
	animation-delay: -.16s
}

@
-webkit-keyframes bouncedelay { 0%,80%,100%{
	-webkit-transform: scale(0.0)
}

40%{
-webkit-transform




















:scale




















(1
.0




















)
}
}
@
keyframes bouncedelay { 0%,80%,100%{
	transform: scale(0.0);
	-webkit-transform: scale(0.0)
}
40%{
transform




















:scale




















(1
.0




















);
-webkit-transform




















:scale




















(1
.0




















)
}
}
</style>


<meta charset="UTF-8">
<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<title>:: RRC ::</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">






</head>
<body>
	<jsp:include page='headerUI.jsp'>
		<jsp:param name="articleId" value="" />
	</jsp:include>
	<div id="page-title">
		<div class="panel">
			<div class="panel-body">
				<h3 class="title-hero">
					<b>Upload Candidate Data</b>
				</h3>
				<div class="example-box-wrapper">
					<form class="form-horizontal bordered-row"
						enctype="multipart/form-data" action="uploadcandidatedata"
						method="POST" data-parsley-validate="">
						<%
							String msg = (String) request.getAttribute("msg");
							if (msg != null) {
						%>
						<div class="form-group" id="msg">
							<label class="control-label col-sm-12 text-center font-size-16"><%=msg%></label>

						</div>
						<%
							}
						%>
						<div class="form-group">
							<label class="col-sm-4 control-label">Select Exam Name</label>
							<div class="col-sm-5">
								<select class="form-control" name="exam_name" id="sel"
									required="required">
									<option value="" selected>Select Exam Name</option>
									<c:forEach var="m" items="${values}">

										<option value=${m.id}>${m.exam_name}(${m.exam_code})</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Select Location
								Name</label>
							<div class="col-sm-5">
								<select id="cId" name="location_name" class="form-control"
									required>
									<option value="">Select Location</option>
								</select>
							</div>

						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label">Upload File </label>
							<div class="col-sm-5">
								<input type="file" class="form-control" id="file" name="file"
									required="required">


							</div>
							<div class="col-md-3 text-right">
								To download Sample Excel sheet <a
									href="excel_formats/CANDIDATE_LOCATION_FORMAT.xlsx">click
									here </a>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="fullname"> Is
								password Protected :</label>
							<div class="col-sm-5 ">
								<label class="radio-inline"> <input type="radio"
									class="" id="radioYes" name="Protected" value="yes"
									required="required">Yes
								</label> <label class="radio-inline"> <input type="radio"
									class=" " id="radioNo" name="Protected" value="no"
									required="required">No
								</label>
							</div>
						</div>
						<div class="form-group" hidden=true id="hiddenField">
							<label class="control-label col-sm-4" for="fullname">
								Enter Password :</label>
							<div class="col-sm-5">
								<input type="password" class="form-control" id="password"
									name="password">

							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-6">
								<button id="submit" type="submit" class="btn btn-primary ">Upload
								</button>
							</div>
						</div>


					</form>
				</div>
			</div>

		</div>
	</div>
	<script>
		$(document)
				.ready(
						function() {
							$('#sel')
									.change(
											function() {

												$('#cId').empty();
												var examName = $(
														'#sel option:selected')
														.val();
												$
														.ajax({
															method : 'POST',
															url : 'findLocationAndSession',
															cache : false,
															data : {
																examId : examName
															},
															ContentType : 'json',

															success : function(
																	datar) {
																$('#cId')
																		.append(
																				"<option>"
																						+ "Select Location"
																						+ "</option>");
																var obj = JSON
																		.parse(datar);
																obj
																		.forEach(itr);

																function itr(
																		item) {
																	$('#cId')
																			.append(
																					"<option value="+item.id+">"
																							+ item.location_name
																							+ '('
																							+ item.start_time
																							+ '-'
																							+ item.end_time
																							+ ')'
																							+ "</option>");
																}

															},
															error : function(
																	jqxhr,
																	status,
																	exception) {
																alert(
																		exception,
																		status);
															}
														});
											});

							$('form').click(function() {
								$('#msg').hide();
							});
							$('#radioYes').click(function() {
								$('#hiddenField').show();
							});
							$('#radioNo').click(function() {
								$('#hiddenField').hide();
							});

						});
	</script>
</body>
</html>