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

<meta charset="UTF-8">
<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<title>Push Questions</title>
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
			<h3 class="title-hero">ASSIGN SET NO:</h3>
			<div class="example-box-wrapper">

				<form class="form-horizontal bordered-row" action="assigningSetNO"
					method="post" id="createpost" data-parsley-validate=""
					id="demo-form">
					<p style="text-align: center; color: green">${data }</p>
					<div class="form-group">
						<label class="col-sm-3 control-label">Select Exam Name</label>
						<div class="col-sm-6">
							<select class="form-control" name="examId" id="postId"
								required="required">
								<option value="">--Select Exam Name --</option>
								<c:forEach var="m" items="${examNames}">
									<option value="${m.id}">${m.exam_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Select
							Location Name </label>
						<div class="col-sm-6">
							<select class="form-control" name="LocationSessionId" id="cId"
								required="required">
								<option value="">--Select Location Name --</option>
							</select>

						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Select
							Set Number </label>
						<div class="col-sm-6">
							<select class="form-control" name="setNo" id="cId"
								required="required">
								<option value="">--Select Set no --</option>

								<option value="Set-1">Set-1</option>
								<option value="Set-2">Set-2</option>
								<option value="Set-3">Set-3</option>
								<option value="Set-4">Set-4</option>
								<option value="Set-5">Set-5</option>
							</select>

							<!-- <span id="lblError1" style="color: red;visibility:hidden">This field is required</span>-->
						</div>
					</div>

					<%-- 	<%
						String msg = (String) request.getAttribute("msg");
					String col = "";
					if (msg != null) {

						if (msg == "Success") {
							col = "green";
						} else {
							col = "red";
						}
					%>
					<div class="form-group" id="msg">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6 ">
							<p style="text-align:center;color:<%=col%>">
								<b><%=msg%></b>
							</p>
						</div>
 --%>


					<%-- <%
						}
					%> --%>



					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6">
							<!-- <button id="submit" type="submit" class="btn btn-primary button">ADD</button>
 -->
							<button type="submit" class="btn btn-alt btn-hover btn-primary"
								id="submit">Assign</button>
						</div>
					</div>





				</form>



			</div>
		</div>


		<!--<link rel="stylesheet" type="text/css" href="assets/widgets/datatable/datatable.css">-->
		<script type="text/javascript"
			src="assets/widgets/datatable/datatable.js"></script>
		<script type="text/javascript"
			src="assets/widgets/datatable/datatable-bootstrap.js"></script>
		<script type="text/javascript"
			src="assets/widgets/datatable/datatable-tabletools.js"></script>
		<script type="text/javascript">
			$(document)
					.ready(
							function() {

								$('#postId')
										.change(
												function() {
													$('#cId')
															.html(
																	"<option value=''>"
																			+ "--Select Location--"
																			+ "</option>");
													var examId_doc = $(
															'#postId option:selected')
															.val();
													if (examId_doc != 0) {
														$
																.ajax({
																	method : 'POST',
																	url : 'findLocationAndSession',
																	cache : false,
																	data : {
																		examId : examId_doc
																	},
																	ContentType : 'json',
																	success : function(
																			data) {
																		var obj = JSON
																				.parse(data);
																		obj
																				.forEach(itr);
																		function itr(
																				item) {
																			debugger;
																			$(
																					'#cId')
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
																				status)
																		debugger;

																	}
																});
													}
												});
							});
		</script>
</body>
</html>