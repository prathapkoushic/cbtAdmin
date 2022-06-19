<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ttipl.pojo.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
				<b>ADD Exam</b>
			</h3>
			<div class="example-box-wrapper">

				<form class="form-horizontal bordered-row" action="examformurl"
					method="post" id="demo-form" data-parsley-validate="">
					<%
						String msg = (String) request.getAttribute("msg");
						String col = "";
						if (msg != null) {

							if (msg == "-1") {
								msg = "error while adding exam";
								col = " font-red";
							} else {
								col = "font-green";
							}
					%>
					<div class="form-group" id="msg">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6 ">
							<p class="text-center <%=col%>">
								<b><%=msg%></b>
							</p>
						</div>

					</div>

					<%
						}
					%>

					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Select
							post Name </label>
						<div class="col-sm-6">
							<select class="form-control" name="postId" required="required">
								<option value="">--Select post --
									<c:forEach var="m" items="${value}">

										<option value="${m.postId}">${m.post_name}</option>
									</c:forEach>
							</select>

							<!-- <span id="lblError1" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Exam Code</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="exam_code"
								placeholder="Enter exam code" name="exam_code"
								required="required">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"> Exam Name</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="exam_name"
								id="exam_name" placeholder="Enter exam name" required="required">
						</div>
					</div>
					<%
						SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
					%>

					<div class="form-group">
						<label class="col-sm-3 control-label">Exam Date</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" id="exam_date"
								name="exam_date_dupli" placeholder="select exam date"
								data-date-format="MM/DD/YYYY" data-date-minDate="10/21/2014"
								data-parsley-mindate="10/21/2014">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">No
							of Objective Questions </label>
						<div class="col-sm-6">
							<input type="number" class="form-control" name="noOfQuestions"
								id="" placeholder="Enter no of questions" required="required"
								min="1">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">No
							of Descriptive Questions </label>
						<div class="col-sm-6">
							<input type="number" class="form-control" name="no_of_descriptiveQuestions"
								id="" placeholder="Enter no of Descriptive questions" required="required"
								min="1">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Objective Exam
							Duration </label>
						<div class="col-sm-6" id="examduration_dupli_div">
							<input type="text" class="input-mask form-control"
								name="duration" id="duration_dupli" placeholder="HH:MM:SS"
								required="required" data-inputmask="'mask':'99:99:99'">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Descriptive Exam
							Duration </label>
						<div class="col-sm-6" id="examduration_dupli_div">
							<input type="text" class="input-mask form-control"
								name="descriptiveDuration" id="duration_dupli" placeholder="HH:MM:SS"
								required="required" data-inputmask="'mask':'99:99:99'">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Marks
							for each objective Question </label>
						<div class="col-sm-6">
							<input type="number" class="form-control"
								name="marks_per_question" id="marks_per_question"
								placeholder="Enter marks" required="required" min="1" max="100">

							<!-- <span id="lblError1" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Negative
							Marks for each objective Question </label>
						<div class="col-sm-6" id="divNegMarks">
							<input type="text" class="form-control"
								name="negativemarks_per_question"
								id="negativemarks_per_question" placeholder="Ex:0.33"
								oninput=" return validateNegMarks()" required="required">

							<!-- <span id="lblError1" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Description
						</label>
						<div class="col-sm-6">
							<textarea class="form-control" name="exam_description"
								id="exam_description" rows="" cols="2" required="required"></textarea>

							<!-- <span id="lblError1" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>



					<%-- <%
						}
					%> --%>



					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6">
							<!-- <button id="submit" type="submit" class="btn btn-primary button">ADD</button>
 -->
							<button type="submit" class="btn btn-alt btn-hover btn-primary"
								id="submit" onclick="return validateNegMarks()">ADD</button>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="assets/widgets/input-mask/inputmask.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			/* $('#').on(
					"input",
					function() {
						debugger;
						var val = $('#negativemarks_per_question')
								.val();
						if (val != "") {
							var valid = val * 100;
							if (Number.isInteger(valid)) {
								//var st = $('#float_val').val(val);
								$('#negativemarks_per_question')
										.removeClass('parsley-error');
								$('#negativemarks_per_question')
										.addClass('parsley-success');
							} else {
								$('#negativemarks_per_question')
										.addClass('parsley-error');
								return false;
							}
						}
					}); */
					
			$(function() {
				"use strict";
				$(".input-mask").inputmask();
			});

			
		$("#submit").click(function() {
				var start_time = $("#duration_dupli").val();
				if (start_time != "") {
					if ((start_time.search("_") == -1)) {

						if (checkTime(start_time)) {
							$('#duration_dupli').removeClass(
							'parsley-error');
					$('#duration_dupli')
							.addClass('parsley-success');
						} else {
							 alert("Please Enter Valid Exam Duration");
							//errorMsg();
							return false;
						}

					}
					 else {
						 //errorMsg();
						 alert("Please Enter Valid Exam Duration");
							return false;
						}

				}  else {
					//errorMsg();
					alert("Please Enter Exam Duration");
					return false;
				}
				return validateDate();
 
			});
		});

		function validateNegMarks() {
			debugger;
			var val = $('#negativemarks_per_question').val();
			var staus = false;
			$('#parsley-id-6415').remove();
			if (val != "") {
				var valid = val * 100;
				if (Number.isInteger(valid)) {
					staus = true;
					//var st = $('#float_val').val(val);
					$('#negativemarks_per_question').removeClass(
							'parsley-error');
					$('#negativemarks_per_question')
							.addClass('parsley-success');
				}
			
			if (!staus) {
				$('#negativemarks_per_question').removeClass('parsley-success');
				$('#negativemarks_per_question').addClass('parsley-error');
				$('#divNegMarks')
						.append(
								'<ul class="parsley-errors-list filled" id="parsley-id-6415"> <li class="parsley-required">This value is Incorerct.</li></ul>');
				return false;
			}
			}
		}
		function checkTime(time) 
		{

			var times = time.split(":");
			if (Number(times[0]) <= 24 && Number(times[1]) <= 60
					&& Number(times[2]) <= 60) 
			{
				return true;
			} 
			else 
			{
				return false;
			}

		}
		function errorMsg(){
			$('#duration_dupli').removeClass('parsley-success');
			$('#duration_dupli').addClass('parsley-error');
			$('#examduration_dupli_div')
					.append(
							'<ul class="parsley-errors-list filled" id="parsley-id-6415"> <li class="parsley-required">This value is Incorerct.</li></ul>');
		}
		
		function validateDate(){
			var date="<%=sdf.format(new Date())%>";
			var selDate = $('#exam_date').val();

			if (selDate != "") {
				var d1 = new Date(date);
				//var d1 = Tue Dec 27 2022 05:30:00 GMT+0530 (India Standard Time);
				var d2 = new Date(selDate);
				if (d2 < d1) {
					alert("please enter valid Exam Date.");
					return false;
				}
				return true;
			}else{
				alert("please select Exam Date.");	
				return false;
			}
		}
	</script>
</body>
</html>