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
					<b>Candidate List</b>
				</h3>
				<div class="example-box-wrapper">
					<form class="form-horizontal bordered-row" action="#" method="POST"
						data-parsley-validate="" id="demo-form">

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



					</form>

					<%-- <form action="" method="post" id="form"
						class="form-horizontal bordered-row">
						<div class="form-group">
							<label class="col-sm-3 control-label">Select Exam Name</label>
							<div class="col-sm-6">
								<select class="form-control" name="examId" id="sel">
									<option value="">Select Exam Name</option>
									<c:forEach items="${exams}" var="m">
										<option value="${m.id}">${m.exam_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Select Location
								Name</label>
							<div class="col-sm-6">
								<select id="cId" name="sessionId" class="form-control" required>
									<option value="">Select Location</option>


								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-sm-4" for="selectpost"> </label>

							<div class="col-sm-5">
								<button onclick="return clickOnsubmit()"
									class="btn btn-primary btn-hover">Get Candidates</button>
							</div>
						</div>
					</form> --%>
					<table id="datatable" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>S.no</th>
								<th>Roll No</th>
								<th>First Name</th>
								<th>Exam Name</th>
								<th>Location Name</th>
								<th>Post Name</th>

							</tr>
						</thead>




						<tbody>
							<%
								List<Object[]> data = (List<Object[]>) request.getAttribute("value");
							if (data != null) {
								int index = 1;
								for (Object[] entity : data) {
							%>

							<tr>
								<td><%=index++%></td>
								<td><%=entity[0]%></td>
								<td><%=entity[1]%></td>
								<td><%=entity[2]%></td>
								<td><%=entity[3]%></td>
								<td><%=entity[4]%></td>

								<%
									
								%>

							</tr>
							<%
								}
							}
							%>


						</tbody>
					</table>
				</div>
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
		$(document)
				.ready(
						function() {
							$('body').click(function() {
								$('#msg').hide();
							});
							//setTimeout(myFunction, 300);

							var table = $("#datatable").DataTable(
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
										 "language": {
											    "search": "Search"
											  }

									});

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

						});

		function clickOnsubmit() {
			var examId = $('#sel').find(":selected").val();
			var cid = $('#cId').find(":selected").val();
			if (examId != "" && cid != "") {
				$('form').attr('action', 'CandidateList');
			} else {
				alert('please select the values');
				return false;
			}

		}
	</script>
</body>
</html>