<%@page import="com.ttipl.pojo.Post"%>
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

<script>
	function go() {
		var a = document.getElementById("bck");
		a.submit();
	}
	function so() {
		var d = new Date();
		var n = d.toTimeString();
		var as = n.substring(0, 8);
		document.getElementById("time").value = as;
		var df = document.getElementById("addresser").value;
		document.getElementById("address").value = df;

	}
</script>
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
			<h3 class="title-hero">UPDATE Exam Location &amp; Session</h3>
			<div class="example-box-wrapper">
				<form class="form-horizontal bordered-row" id="locationsession"
					action="updateLocation${updateValues.id}" method="post"
					data-parsley-validate="">
					<%
						String msg = (String) request.getAttribute("msg");
						if (msg != null) {
					%>
					<div class="form-group" id="msg">
						<label class="control-label col-sm-12 text-center font-green"><%=msg%></label>

					</div>
					<%
						}
					%>
					<div class="form-group">
						<label class="control-label col-sm-5" for="examId">Exam
							Name </label>
						<div class="col-sm-4">
							<select class="form-control" id="exam_code" name="examId"
								required>
								<option value="" selected>Select Exam Name</option>
								<c:forEach items="${values}" var="m">
									<option value="${m.id}">${m.exam_name}</option>
								</c:forEach>
							</select>


						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="location_name">Location
							Name </label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="location_name"
								id="loc_name" placeholder="Enter Location Name"
								value="${updateValues.location_name}">
							<!--  <span id="lblError1" style="color: red;visibility:hidden">this field is required</span>-->
						</div>
					</div>
					<!-- <!-- <div class="form-group">
						<label class="control-label col-sm-5" for="date_created"> Date
						
							of Creation : </label>
						<div class="col-sm-4"> -->
					--> <input type="hidden" class="form-control" id="exam_date"
						name="date" placeholder="Enter session creation date"
						value="${updateValues.date_created}">

					<!-- 		</div>
					</div> -->
					<div class="form-group">
						<label class="control-label col-sm-5" for="address"> Ip
							Address </label>
						<div class="col-sm-4">

							<input class="form-control" name="ipaddress" id="ipaddress"
								value="${updateValues.ipaddress }">

						</div>
					</div>
					<%-- <div class="form-group">
					<label class="control-label col-sm-5" for="address">Ip
						Address : 
					</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="ipaddress"
							id="ipaddress" value="${updateValues.ipaddress }">
						<!-- <textarea class="form-control" name="ipaddress" id="ipaddress"></textarea> -->

					</div>
				</div> --%>
					<div class="form-group">
						<label class="control-label col-sm-5" for="mobileNumber">Mobile
							Number </label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-mask" name="mobileNumber"
								id="mobile Number" placeholder="Enter mobile number"
								value="${updateValues.mobileNumber}" data-inputmask="'mask':'9999999999'">

						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="contactName">Contact
							Name </label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="contactName"
								id="contactName" placeholder="Contact Name"
								value="${updateValues.contactName}">

						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="address">Address
						</label>
						<div class="col-sm-4">

							<textarea class="form-control" name="address" id="address">${updateValues.address }</textarea>

						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-5 control-label">User name </label>
						<div class="col-sm-4">
							<input type="text" required="required"
								class="form-control ct_formCtrl" id="Username"
								placeholder="Enter  username" name="user_name"
								value="${updateValues.user_name}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-5 control-label">Password </label>
						<div class="col-sm-4">
							<input type="text" required="required"
								class="form-control ct_formCtrl" id="password"
								placeholder="Enter  password" name="password"
								value="${updateValues.password}">
						</div>
					</div>


					<div class="form-group">
						<label class="control-label col-sm-5" for="contactName">Start
							time </label>
						<div class="col-sm-4">
							<input type="text" name="start_time" id="start_time"
								placeholder="Start time" value="${updateValues.start_time}"
								class="input-mask  form-control ct_formCtrl"
								data-inputmask="'mask':'99:99:99'">

						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="contactName">End
							time </label>
						<div class="col-sm-4">
							<input type="text" name="end_time" placeholder="End time"
								value="${updateValues.end_time}"
								class="input-mask  form-control ct_formCtrl" id="end_time"
								data-inputmask="'mask':'99:99:99'">

						</div>
					</div>
					<input type="number" hidden="true" id="exam_id_old"
						value=${updateValues.examId }> <input id="time"
						type="hidden" name="time" value=""> <input id="addresser"
						type="hidden" name="addr" value="${updateValues.address}">

					<div class="form-group">
						<label class="control-label col-sm-5" for="contactName"> </label>
						<div class="col-sm-4">
							<button id="submit" type="submit" class="btn btn-primary mgn-top">Update
								Session</button>

						</div>
					</div>

				</form>
			</div>
		</div>
			<script type="text/javascript"
		src="assets/widgets/input-mask/inputmask.js"></script>
		<script>
			$(document)
					.ready(
							function() {
								var exan_id_old = $('#exam_id_old').val();
								$('#exam_code').val(exan_id_old);
								$(function() {
									"use strict";
									$(".input-mask").inputmask();
								});

								$("#submit")
										.click(
												function() {
													debugger;
													
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
															"#start_time")
															.val();
													
													var end_time = $(
															"#end_time").val();
													if (start_time != ""
															&& end_time != "") {

														if ((start_time
																.search("_") == -1)
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
													}else{
														alert("Please Enter Valid Time");
														return false;
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


