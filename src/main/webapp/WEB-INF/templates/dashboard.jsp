<%@page import="java.util.List"%>
<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: RRC ::</title>
</head>
<body>
	<jsp:include page="headerUI.jsp"></jsp:include>
	<div id="page-title">

		<div class="col-3"></div>
		<div class="col-6">
			<div class="panel">
				<div class="panel-body">
					<h3 class="title-hero">
						<b>DASHBOARD</b>
					</h3>

					<form action="stats" method="post" id="form"
						class="form-horizontal bordered-row" data-parsley-validate=""
						id="demo-form">

						<div class="form-group">
							<label class="control-label col-sm-2" for="select exam name">Select
								Exam Name :</label>
							<div class="col-sm-3">
								<select id="postId" class="form-control" name="examId" required>
									<option value="">--select exam --</option>
									<option value="%">All</option>
									<c:forEach var="exam" items="${exams}">
										<option value=${exam.id }>${exam.exam_name}(${exam.exam_code})</option>
									</c:forEach>
								</select>
							</div>
							<label class="control-label col-sm-2" for="select Exam location">Select
								Location (Session):</label>
							<div class="col-sm-3">
								<select id="location" class="form-control" name="locId" required>
									<option value="">--select exam loc session --</option>
									<option value="all">All</option>

								</select>
							</div>
							<div class="col-sm-2">
								<button type="submit" class="btn btn-primary" id="pdf">Get
								</button>
							</div>
						</div>
						<!-- <div class="form-group">
							<label class="control-label col-sm-3" for="select Exam location">Select
								Location (Session):</label>
							<div class="col-sm-3">
								<select id="location" class="form-control" name="locId" required>
									<option value="">--select exam loc session --</option>
									<option value="all">All</option>

								</select>
							</div>
						</div> -->
						<!-- <div class="form-group">
							<label class="control-label col-sm-3" for="select Exam location"></label>
							<div class="col-sm-5">
								<button type="submit" class="btn btn-primary" id="pdf">Get
								</button>
							</div>
						</div> -->

						<%
							int i = 1;

							List<Object[]> objs = (List<Object[]>) request.getAttribute("sessions");

							if (objs != null) {
						%>
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<a href="CandidateList" title="Example tile shortcut"
										class="tile-box tile-box-alt btn-success">
										<div class="tile-header">CANDIDATES CALLED</div>
										<div class="tile-content-wrapper">
											<div class="pad25A">
												<span class="font-size-18">${total_candidates}</span>
											</div>
										</div>
									</a>
								</div>

								<div class="col-md-4">
									<a href="#" title="Example tile shortcut"
										class="tile-box tile-box-alt btn-warning">
										<div class="tile-header">CANDIDATES APPEARED</div>
										<div class="tile-content-wrapper">
											<div class="pad25A">
												<span class="font-size-18">${total_can_exam_starts}</span>
											</div>
										</div>
									</a>
								</div>
								<div class="col-md-4">
									<a href="#" title="Example tile shortcut"
										class="tile-box tile-box-alt btn-info">
										<div class="tile-header">ATTENDENCE PERCENTAGE</div>
										<div class="tile-content-wrapper">
											<div class="pad25A">
												<span class="font-size-18">${total_Percentage}</span>
											</div>
											<%-- <div class="chart-alt-10 easyPieChart"
									data-percent="${total_Percentage}"
									style="width: 100px; height: 100px; line-height: 100px;">
									<span>${total_Percentage}+1</span>%
									<canvas width="100" height="100"></canvas>
								</div> --%>
										</div>
									</a>
								</div>
								<%-- <div class="col-md-2">
						<a href="#" title="Example tile shortcut"
							class="tile-box tile-box-alt btn-danger">
							<div class="tile-header">Monthly Earnings</div>
							<div class="tile-content-wrapper">
								<div class="chart-alt-10 easyPieChart" data-percent="55"
									style="width: 100px; height: 100px; line-height: 100px;">
									<span>54</span>%
									<canvas width="100" height="100"></canvas>
								</div>
							</div>
						</a>
					</div> --%>


							</div>
						</div>
						<div class="form-group">
							<table class="table table-bordered table-striped" id="datatable">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Location Name</th>
										<th>Session timings</th>
										<th>Address</th>
										<th>Candidates Called</th>
										<th>Candidates Appeared</th>

									</tr>
								</thead>

								<tbody>
									<%
										for (Object[] obj : objs) {
									%>
									<tr>
										<td><%=i++%></td>
										<td><%=obj[2]%></td>

										<td><%=obj[0]%>-<%=obj[1]%></td>
										<td><%=obj[3]%></td>
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

					</form>

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

		var locId = "${locId}";
		var examId = "${examId}";
	//	console.log(locId);
		//console.log(examId);

		$(function() {
			if (locId != "" && examId != "") {
				$('#postId').val(examId);
				    callAjax();
				
			}

			$('#postId').change(
					function() {
						$('#location').html(
								"<option value='all'>" + "All" + "</option>");
						var examId_doc = $('#postId option:selected').val();
						if (examId_doc != "") {
							$.ajax({
								method : 'POST',
								url : 'findSessions',
								cache : false,
								data : {
									examId : examId_doc
								},
								ContentType : 'json',
								success : function(data) {
									var obj = JSON.parse(data);
									obj.forEach(itr);
									function itr(item) {
										$('#location').append(
												"<option value="+ item.start_time + '-'
												+ item.end_time+">"

												+ item.start_time + '-'
														+ item.end_time
														+ "</option>");
									}
								},
								error : function(jqxhr, status, exception) {
									alert(exception + status + "Error");
									debugger;

								}
							});
						}
					});
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
		});

		function callAjax() {
			$('#location').html("<option value='all'>" + "All" + "</option>");
			var examId_doc = $('#postId option:selected').val();
			if (examId_doc != "") {
				$.ajax({
					method : 'POST',
					url : 'findSessions',
					cache : false,
					data : {
						examId : examId_doc
					},
					ContentType : 'json',
					success : function(data) {
						var obj = JSON.parse(data);
						obj.forEach(itr);
						function itr(item) {
							$('#location').append(
									"<option value="+ item.start_time + '-'
											+ item.end_time+">"

									+ item.start_time + '-' + item.end_time
											+ "</option>");
						}
						$('#location').val(locId);
					},
					error : function(jqxhr, status, exception) {
						alert(exception + status + "Error");
						debugger;

					}
				});
			}
		}
	</script>
</body>
</html>