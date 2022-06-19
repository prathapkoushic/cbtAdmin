
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
			<h3 class="title-hero">generate OTP</h3>
			<div class="example-box-wrapper">

				<form class="form-horizontal bordered-row" action="generateOTP"
					method="post" id="otp" data-parsley-validate=""
					id="demo-form">
					<div class="form-group">
						<label class="col-sm-3 control-label">Exam Name</label>
						<div class="col-sm-6">
							<select class="form-control" name="examId" id="sel"
								required="required">
								<option value="" selected>Select Exam Name</option>
								<c:forEach items="${values}" var="m">
									<option value="${m.id}">${m.exam_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Exam Location</label>
						<div class="col-sm-6">
							<select id="cId" name="sessionId" class="form-control" required>

								<option value="">select location</option>
							</select>
						</div>
					</div>

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
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6">
							<!-- <button id="submit" type="submit" class="btn btn-primary button">ADD</button>
 -->
							<button type="submit" class="btn btn-alt btn-hover btn-primary"
								id="submit">generate OTP</button>
						</div>
					</div>





				</form>



			</div>
		</div>
	</div>


	<!--<link rel="stylesheet" type="text/css" href="assets/widgets/datatable/datatable.css">-->
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-bootstrap.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-tabletools.js"></script>
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
																				"<option value="+">"
																						+ "Select Location"
																						+ "</option>");
																$('#cId')
																		.append(
																				"<option value=-1>"
																						+ "All"
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
																							+ ' ('
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

						});
	</script>

</body>
</html>