<%@page import="java.text.ParseException"%>
<%@page import="java.time.LocalDateTime"%>
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
				<b>OTP List</b>
			</h3>
			<div class="example-box-wrapper">

				<form class="form-horizontal" action="OTPlist" method="post">
					<div class="form-group">
						<label class="control-label col-sm-4" for="selectpost">
							Select Exam Name : </label>
						<div class="col-sm-5">
							<select class="form-control" name="examId" required="required"
								id="sel">
								<option value="%">--all exams--</option>

								<c:forEach var="exam" items="${values}">

									<option value=${exam.id}>${exam.exam_name}</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">


							<table class="table table-bordered table-striped" id="datatable">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Contact Name</th>
										<th>Location Name</th>
										<th>Session timings</th>
										<th>OTP</th>
										<th>Generation Time</th>

									</tr>
								</thead>
								<%
									int i = 1;

									List<Object[]> objs = (List<Object[]>) request.getAttribute("sessions");
								%>
								<tbody>
									<%
										if (objs != null) {
											for (Object[] obj : objs) {
									%>
									<tr>
										<td><%=i++%></td>
										<td><%=obj[3]%></td>
										<td><%=obj[0]%></td>
										<td><%=obj[1]%>-<%=obj[2]%></td>
										<td><%=obj[4]%></td>
										<td><%=obj[5]%></td>




									</tr>
									<%
										}
										}
									%>

								</tbody>
							</table>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-bootstrap.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-tabletools.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var examId = "${examId}";
					$('#sel').val(examId);
					var table = $("#datatable")
							.DataTable(
									{
										"responsive" : true,
										"autoWidth" : false,
										"aLengthMenu" : [
												[ 3, 5, 8, 10, 15, 20, 25, 50,
														100, -1 ],
												[ 3, 5, 8, 10, 15, 20, 25, 50,
														100, "ALL" ] ],
										"pageLength" : 8,
										"ordering" : false,
										"language" : {
											"search" : "Search"
										}

									});

					$('#sel').change(function() {
						$('form').submit();
					});

				});
	</script>
</body>
</html>
