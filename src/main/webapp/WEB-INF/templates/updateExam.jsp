<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
				<b>UPDATE EXAM</b>
			</h3>
			<div class="example-box-wrapper">

				<form class="form-horizontal bordered-row" action="examupdateurl"
					method="post" data-parsley-validate="" id="demo-form">

					<div class="form-group row">
						<label class="control-label col-sm-4" for="selectpost">Select
							post Name : </label>
						<div class="col-sm-4">
							<select class="form-control" name="postId" required="required"
								id="postId">
								<option value="">--select post --
									<c:forEach var="updateValues" items="${value}">

										<option value=${updateValues.postId}>${updateValues.post_name}</option>
									</c:forEach>
							</select>
							<!-- <input type="text" class="form-control" name="post" id="post" >  -->
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="examcode">Exam
							Code :</label>
						<div class="col-sm-4">

							<input type="number" name="id" value=${updateValues.id }
								hidden=true> <input type="text" class="form-control"
								name="exam_code" value="${updateValues.exam_code}"
								required="required">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name">Exam Name
							:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="exam_name"
								value="${updateValues.exam_name}" required="required">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="examdate"> Exam
							Date :</label>
						<div class="col-sm-4">
							<input type="date" class="form-control" name="exam_date_dupli"
								value="${updateValues.exam_date}" required="required"  id="exam_date"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="description">No
							of Questions </label>
						<div class="col-sm-4">
							<input type="number" class="form-control" name="noOfQuestions"
								id="" placeholder="" required="required" min="1"
								value=${updateValues.noOfQuestions}>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-4" for="description">No
							of Descriptive Questions </label>
						<div class="col-sm-4">
							<input type="number" class="form-control" name="no_of_descriptiveQuestions"
								id="" placeholder="" required="required" min="1"
								value=${updateValues.no_of_descriptiveQuestions}>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="duration">Exam
							Duration :</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-mask"
								name="duration" value="${updateValues.duration}"
								required="required" id="duration_dupli" placeholder="HH:MM:SS"
								data-inputmask="'mask':'99:99:99'" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-4" for="description">Descriptive Exam
							Duration </label>
						<div class="col-sm-4" id="examduration_dupli_div">
							<input type="text" class="input-mask form-control" value="${updateValues.descriptiveDuration}"
								name="descriptiveDuration" id="duration_dupli" placeholder="HH:MM:SS"
								required="required" data-inputmask="'mask':'99:99:99'">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="question">MFE
							Question :</label>
						<div class="col-sm-4">
							<input type="number" class="form-control"
								name="marks_per_question"
								value="${updateValues.marks_per_question}" required="required"
								min="1" max="100"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="negativemarks">NMFE
							Question :</label>
						<div class="col-sm-4" id="divNegMarks">
							<input type="text" class="form-control"
								name="negativemarks_per_question"
								value="${updateValues.negativemarks_per_question}"
								required="required" id="negativemarks_per_question"
								placeholder="Ex:0.33" oninput="return validateNegMarks()" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="no.ofvancancies">Exam
							Description :</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="exam_description"
								value="${updateValues.exam_description}" required="required" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"></label>
						<div class="col-sm-4">
							<button type="submit" class="btn btn-primary"  id="submit" onclick="return validateNegMarks()">UPDATE</button>
						</div>
					</div>
					<div hidden=true>
						<input type="number" class="form-control" name="pos" id="pos_id"
							value="${updateValues.postId}" required="required" hidden=true>
					</div>

				</form>
			</div>
		</div>
	</div>
	<%
						SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
					%>
	<script type="text/javascript"
		src="assets/widgets/input-mask/inputmask.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			var post_id = $('#pos_id').val();
			$("#postId").val(post_id);
			$(function() {
				"use strict";
				$(".input-mask").inputmask();
			});

			$("#submit").click(function() {
				var start_time = $("#duration_dupli").val();

				if (start_time != "") {
					if ((start_time.search("_") == -1)) {

						if (checkTime(start_time)) {
							$('#duration_dupli').removeClass('parsley-error');
							$('#duration_dupli').addClass('parsley-success');
							
						} else {
							alert("Please Enter Exam Duration");
							//errorMsg();
							return false;
						}

					} else {
						//errorMsg();
						alert("Please Enter Exam Duration");
						return false;
					}

				} else {
					//errorMsg();
					alert("Please Enter Exam Duration");
					return false;
				}
				
				return validateDate();

			});
		});

		function validateNegMarks() {
			
			var val = $('#negativemarks_per_question').val();
			var staus = false;
			$('#parsley-id-0009').remove();
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
			}
			if (!staus) {
				$('#negativemarks_per_question').removeClass('parsley-success');
				$('#negativemarks_per_question').addClass('parsley-error');
				$('#divNegMarks')
						.append(
								'<ul class="parsley-errors-list filled" id="parsley-id-0009"> <li class="parsley-required">This value is Incorerct.</li></ul>');
				return false;
			}

		}
		function checkTime(time) {

			var times = time.split(":");
			if (Number(times[0]) <= 24 && Number(times[1]) <= 60
					&& Number(times[2]) <= 60) {
				return true;
			} else {
				return false;
			}

		}
		function errorMsg() 
		{
			$('#duration_dupli').removeClass('parsley-success');
			$('#duration_dupli').addClass('parsley-error');
			$('#examduration_dupli_div')
					.append('<ul class="parsley-errors-list filled" id="parsley-id-0009"> <li class="parsley-required">This value is Incorerct.</li></ul>');
		}
		
		
		function validateDate(){
			var date="<%=sdf.format(new Date())%>";
			var selDate = $('#exam_date').val();

			if (selDate != "") {
				var d1 = new Date(date);
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