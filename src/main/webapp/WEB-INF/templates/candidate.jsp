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

40
%
{
-webkit-transform




































:




































scale




































(
1
.0




































)
}
}
@
keyframes bouncedelay { 0%,80%,100%{
	transform: scale(0.0);
	-webkit-transform: scale(0.0)
}
40
%
{
transform




































:




































scale




































(
1
.0




































)
;
-webkit-transform




































:




































scale




































(
1
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
					<b>Add Candidate</b>
				</h3>
				<div class="example-box-wrapper">
					<form class="form-horizontal bordered-row" action="saveCandidate"
						method="POST" data-parsley-validate="" id="demo-form">
						<%
							String msg = (String) request.getAttribute("msg");
							if (msg != null) {
						%>
						<div class="form-group" id="msg">
							<label
								class="control-label col-sm-12 text-center font-size-16 font-green"><%=msg%></label>

						</div>
						<%
							}
						%>
						<div class="form-group">
							<label class="col-sm-3 control-label">Select Exam Name</label>
							<div class="col-sm-6">
								<select class="form-control" name="exam_id" id="sel"
									required="required">
									<option value="" selected>Select Exam Name</option>
									<c:forEach items="${values}" var="m">
										<option value="${m.id}">${m.exam_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Select Location
								Name</label>
							<div class="col-sm-6">
								<select id="cId" name="exam_loc_session_id" class="form-control"
									required>
									<option value="">select location</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Select Post Name</label>
							<div class="col-sm-6">
								<select class="form-control" name="post_id" required="required">
									<option value="" selected>Select Post Name</option>
									<c:forEach items="${valuee}" var="m">
										<option value="${m.postId}">${m.post_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Enter Candidate Id</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="candidate_id"
									required="required" placeholder="Enter candidate Id">

							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"> Password</label>
							<div class="col-sm-6">
								<input type="text" value="" name="password"
									class="form-control popover-button-default"
									placeholder="Enter password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"> First Name</label>
							<div class="col-sm-6">
								<input type="text" name="candidate_first_name"
									class="form-control popover-button-default"
									placeholder="Enter First Name" required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label"> Last Name</label>
							<div class="col-sm-6">
								<input type="text" name="candidate_last_name"
									class="form-control popover-button-default"
									placeholder="Enter Last Name" required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label"> Gender </label>
							<div class="col-sm-6">
								<input type="radio" name="gender" placeholder="Enter Gender"
									required="required" value="male"> Male &nbsp;&nbsp;<input
									type="radio" name="gender" placeholder="Enter Gender"
									required="required" value="female"> Female &nbsp;&nbsp;<input
									type="radio" name="gender" required="required" value="others">
								Others

								<!-- <input
									type="text" name="gender"
									class="form-control popover-button-default"
									placeholder="Enter Gender" required="required"> -->
							</div>
							<!-- <div class="col-sm-6">
								<input type="radio" name="gender" placeholder="Enter Gender"
									required="required">Female

								<input
									type="text" name="gender"
									class="form-control popover-button-default"
									placeholder="Enter Gender" required="required">
							</div> -->
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label"> Father Name</label>
							<div class="col-sm-6">
								<input type="text" name="father_name" class="form-control "
									placeholder="Enter Father Name" required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Date of Birth</label>
							<div class="col-sm-6">
								<input class="form-control" type="date" name="date"
									required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Select Community</label>
							<div class="col-sm-6">
								<select class="form-control" name="caste" required="required">
									<option value="">select Community</option>
									<option value="OC">OC</option>
									<option value="BC">BC</option>
									<option value="SC">SC</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"> Email Id</label>
							<div class="col-sm-6">
								<input type="text" name="email_id" class="form-control "
									placeholder="Enter Email" required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Contact No</label>
							<div class="col-sm-6">
								<input type="text" name="contact_no"
									class="form-control input-mask "
									placeholder="Enter Phone Number" required="required"
									data-inputmask="'mask':'9999999999'">
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label"></label>
							<div class="col-sm-6">
								<button id="submit" type="submit"
									class="btn btn-primary mgn-top">Add Candidate</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="assets/widgets/input-mask/inputmask.js"></script>
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
							$(function() {
								"use strict";
								$(".input-mask").inputmask();
							});

						});
	</script>
</body>
</html>