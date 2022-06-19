<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="en">
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
	<div class="panel">
		<div class="panel-body">
			<h3 class="title-hero">
				<b>Add Exam Location &amp; Session</b>
			</h3>

			<div class="example-box-wrapper">
				<form class="form-horizontal bordered-row" id="locationsession"
					name="locationsession" action="createLocationSession" method="post"
					data-parsley-validate="" id="demo-form">

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
						<label class="col-sm-3 control-label">Exam Name </label>
						<div class="col-sm-6">
							<select  class="form-control" name=examId required="required">
								<option value="">--Select Exam Name --</option>
								<c:forEach var="m" items="${values}">

									<option value=${m.id }>${m.exam_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">Location Name </label>
						<div class="col-sm-6">
							<input type="text" required="required"
								class="form-control ct_formCtrl" id="location_name"
								placeholder="Enter location name" name="location_name">
						</div>
					</div>



					<div class="form-group">
						<label class="col-sm-3 control-label">Created Date </label>
						<div class="col-sm-6">
							<input type="date" class="form-control ct_formCtrl" id="examdate"
								placeholder="Enter Examdate" name="examdate" required="required">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">IP Address </label>
						<div class="col-sm-6">
							<input type="text" required="required"
								class="form-control ct_formCtrl" id="ipaddress"
								placeholder="Enter ip address" name="ipaddress"
								data-parsley-maxlength="15">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">Contact Name </label>
						<div class="col-sm-6">
							<input type="text" required="required"
								class="form-control ct_formCtrl" id="contactName"
								placeholder="Enter contact Name" name="contactName">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Mobile No </label>
						<div class="col-sm-6">
							<input type="text" required="required"
								class=" form-control input-mask ct_formCtrl" id="mobileNumber"
								placeholder="Enter mobile Number" name="mobileNumber"
								data-inputmask="'mask':'9999999999'">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Address </label>
						<div class="col-sm-6">
							<textarea class="form-control ct_formCtrl" id="address"
								placeholder="Enter address" name="address" required="required"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">User name </label>
						<div class="col-sm-6">
							<input type="text" required="required"
								class="form-control ct_formCtrl" id="Username"
								placeholder="Enter  username" name="user_name">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">Password </label>
						<div class="col-sm-6">
							<input type="text" required="required"
								class="form-control ct_formCtrl" id="password"
								placeholder="Enter  password" name="password">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">Start
							Time(EX:14:00:00) </label>
						<div class="col-sm-3">
							<input type="text" required="required"
								class="input-mask form-control" id="start_time"
								name="start_time" placeholder="Enter Session"
								data-inputmask="'mask':'99:99:99'">
						</div>
						<label class="col-sm-2 control-label">End Time(EX:14:30:00
							) </label>
						<div class="col-sm-3">
							<input type="text" required="required"
								class="input-mask  form-control ct_formCtrl" id="end_time"
								placeholder="Enter Session" name="end_time"
								data-inputmask="'mask':'99:99:99'">
						</div>
					</div>
					<div>
						<input type="hidden" name="date_created_dupli"
							value="<%=LocalDateTime.now()%>">
					</div>


					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6">
							<button id="submit" type="submit" class="btn btn-primary">Add
								Location</button>
						</div>
					</div>

					<%-- 
		<div class="main-dashboard">
			<div class="text-center" style="padding-top: 30px;">
				<div class="form-group row">
					<div class="row mb-2">

						<div class="col-sm-5  float-sm-center">
							<label for="locationname">E :<span style="color: red">*</span></label>
						</div>
						<div class="col-sm-7 sm-float-center">
							<select class="form-control" name=examName>
								<option value="">--Select Exam Name --
									<c:forEach var="m" items="${values}">

										<option value="${m.id}">${m.exam_name}</option>
									</c:forEach>
							</select>

							<!-- <input  type="text"  required="required" class="form-control ct_formCtrl"
									id="location_name" placeholder="Enter location name"
									name="location_name"> -->
							<!--   <span id="lblError1" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>
				</div>
				<br> <br>
				<div class="form-group row">
					<div class="row mb-2">

						<div class="col-sm-5  float-sm-center">
							<label for="locationname">Location Name :<span
								style="color: red">*</span></label>
						</div>
						<div class="col-sm-7 sm-float-center">
							<input  type="text"  required="required" class="form-control ct_formCtrl"
								id="location_name" placeholder="Enter location name"
								name="location_name">
							<!--   <span id="lblError1" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>
				</div>
				<br> <br>
				<div class="form-group row">
					<div class="row mb-2">

						<div class="col-sm-5  float-sm-center">
							<label for="address">Address :</label>
						</div>
						<div class="col-sm-7 sm-float-center">
							<textarea class="form-control ct_formCtrl" id="address"
								placeholder="Enter address" name="address"></textarea>
							<!-- <span id="lblError2" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>
				</div>
				<br> <br>


				<div class="form-group row">
					<div class="row mb-2">

						<div class="col-sm-5  float-sm-center">
							<label for="examdate">ExamDate :</label>
						</div>
						<div class="col-sm-7 sm-float-center">
							<input type="date" class="form-control ct_formCtrl" id="examdate"
								placeholder="Enter Examdate" name="examdate">
							<!-- <span id="lblError2" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>
				</div>
				<br> <br>
				<div class="form-group row">
					<div class="row mb-2">

						<div class="col-sm-5  float-sm-center">
							<label for="ipaddress">IpAddress :</label>
						</div>
						<div class="col-sm-7 sm-float-center">

							<input  type="text"  required="required" class="form-control ct_formCtrl"
								id="ipaddress" placeholder="Enter ip address" name="ipaddress">
							<!-- <span id="lblError2" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>
				</div>
				<br> <br>




				<div class="form-group row">
						<div class="row mb-2">

							<div class="col-sm-4  float-sm-center">
								<label for="selectpost">Post Name:<span
									style="color: red">*</span>
								</label>
							</div>

							<div class="col-sm-7 sm-float-center">
								<select class="form-control" name=id>
									<option value="">--Select Post --
										<c:forEach var="m" items="${values}">

											<option value="${m.id}">${m.post_name}</option>
										</c:forEach>
								</select>
							</div>
						</div>
					</div>

			</div>
			<br />

			<div class="ct_Width">
				<div>
					<div class="row text-center">
						<div class="col-md-6">
							<h5>Start Time(EX:8:00:00 AM)</h5>
						</div>
						<div class="col-md-6">
							<h5>End Time(EX:8:00:00 AM)</h5>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="Session">Session1 :</label> <input  type="text"  required="required"
						class="form-control ct_formCtrl" id="start_time" name="start_time"
						placeholder="Enter Session"> <input  type="text"  required="required"
						class="form-control ct_formCtrl" id="end_time"
						placeholder="Enter Session" name="end_time">
					<!--   <span id="lblError3" style="color: red;visibility:hidden">This field is required</span>-->
				</div>
				<!-- <div class="form-group">
						<label for="Session">Session2 :</label> <input  type="text"  required="required"
							class="form-control ct_formCtrl" id="start_time" name="start_time"
							placeholder="Enter Session"> <input  type="text"  required="required"
							class="form-control ct_formCtrl" id="end_time"
							placeholder="Enter Session" name="end_time">
						  <span id="lblError3" style="color: red;visibility:hidden">This field is required</span>
					</div> -->
				<br /></br>
			</div>
			<div>
				<input type="hidden" name="date_created"
					value="<%=LocalDateTime.now()%>">
			</div>




			<div class="preBtn text-center">
				<button id="submit" type="submit" class="btn btn-primary">Create
					Location</button>
			</div>

		</div> --%>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="assets/widgets/input-mask/inputmask.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('form').click(function() {
								$('#msg').hide();
							});

							$(function() {
								"use strict";
								$(".input-mask").inputmask();
							});

							$("#submit")
									.click(
											function() {
												var ipAddress = $('#ipaddress')
														.val();
												if (ipAddress != "") {
													var status = ValidateIPaddress(ipAddress);
													if (status) {

													} else {
														return false;
													}
												}

												var start_time = $(
														"#start_time").val();
												var end_time = $("#end_time")
														.val();
												if (start_time != ""
														&& end_time != "") {

													if ((start_time.search("_") == -1)
															&& (end_time
																	.search("_") == -1)) {
														if (start_time <= end_time) {
															if (checkTime(start_time)
																	&& checkTime(end_time)) {
																return true;
															} else {
																alert("Please Enter Valid Time");
																return false;
															}
														} else {
															alert("Please Enter Valid Time");
															return false;
														}

													} else {
														alert("Please Enter Valid Time");
														return false;
													}
												}

											});
						});

		function checkTime(time) {

			var times = time.split(":");
			if (Number(times[0]) <= 24 && Number(times[1]) <= 60
					&& Number(times[2]) <= 60) {
				return true;
			} else {
				return false;
			}

		}
		function ValidateIPaddress(ipaddress) {
			if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
					.test(ipaddress)) {
				return (true);
			}
			alert("You have entered an invalid IP address!")
			return (false)
		}
	</script>

</body>
</html>


