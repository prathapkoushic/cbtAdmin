
<%@page import="com.ttipl.pojo.ReportBean"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
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

	<div id="page-title">


		<div class="panel">
			<div class="panel-body">
				<h3 class="title-hero">Candidate Results Report</h3>
				<div class="example-box-wrapper">


					<form class="form-horizontal bordered-row" id="form"
						action="downloadExcel" method="post" data-parsley-validate=""
						id="demo-form">
						<div class="form-group">
							<label class="control-label col-sm-3" for="select exam name">Select
								Exam Name :</label>
							<div class="col-sm-5">
								<select id="postId" class="form-control" name="examId" required>
									<option value="">--Select Exam Name--</option>
									<c:forEach var="exam" items="${exams}">
										<option value=${exam.id} >${exam.exam_name}(${exam.exam_code})</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="select Exam location">Select
								Location (Session):</label>
							<div class="col-sm-5">
								<select id="location" class="form-control" name="locId" required>
									<option value="">--Select Location</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="select location">Select
								Candidate:</label>
							<div class="col-sm-5">
								<select id="candidate" class="form-control" name="candidateId"
									required>
									<option value="">--Select Candidate --</option>
								</select>
							</div>
						</div>



						<%
							List<ReportBean> reportBeans = (List<ReportBean>) request.getAttribute("reportBean");
							int sNo = 1;
							if (reportBeans == null) {
						%>
						<div class="form-group">
							<div class="col-md-3"></div>
							<div class="col-md-3">
								<button type="button" class="btn btn-primary" id="preveiw">Preview
								</button>
							</div>

							<!-- <div class="col-md-6"></div>
								<div class="col-md-6 text-right">
									<button type="button" class="btn btn-primary"
										id="excelDetailedReport">Excel Detailed Report</button>
								</div> -->
						</div>
					</form>
					<%
						} else {
					%>

					<div class="form-group">
						<div class="col-md-3"></div>
						<div class="col-md-3">
							<button type="button" class="btn btn-primary" id="pdf">Pdf
								Report&nbsp;&nbsp;</button>
						</div>

						<div class="col-md-2 text-right">
							<button type="button" class="btn btn-primary" id="excelReport">Excel
								Report</button>
						</div>
						<!-- <div class="col-md-6"></div>
								<div class="col-md-6 text-right">
									<button type="button" class="btn btn-primary"
										id="excelDetailedReport">Excel Detailed Report</button>
								</div> -->
					</div>

					</form>
					<table class="table table-bordered table-striped "
						id="questions_tbl">
						<thead>
							<tr>
								<td><b>S.NO</b></td>
								<td><b>H T No</b></td>
								<td><b> NAME</b></td>
								<td><b>TOTAL QUESTIONS </b></td>
								<td><b>ANSWERED</b></td>
								<td><b>CORRECT</b></td>
								<td><b> MARKS</b></td>

							</tr>
						<thead>
						<tbody>


							<%
								for (ReportBean reportBean : reportBeans) {
							%>
							<tr>
								<td><%=sNo++%></td>
								<td><%=reportBean.getHTnumber()%></td>
								<td><%=reportBean.getCandidateName()%></td>
								<td class="text-center"><%=reportBean.getTotalQuestions()%></td>
								<td class="text-center"><%=reportBean.getTotalAnswered()%></td>
								<td class="text-center"><%=reportBean.getTotalCorrect()%></td>
								<td class="text-center"><%=reportBean.getTotalObtained()%></td>
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
	<script type="text/javascript" src="assets/widgets/chosen/chosen.js"></script>
	<script type="text/javascript"
		src="assets/widgets/chosen/chosen-demo.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-bootstrap.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-tabletools.js"></script>

	<script type="text/javascript">
		var locId = "${locId}";
		var examId = "${examId}";
		var candidateId = "${candidateId}";
		//console.log(locId);
		//console.log(examId);
		//console.log(candidateId);
		$(function() {

			if (locId != "" && examId != "" && candidateId != "") {
				$('#postId').val(examId);
				callAjax();

			}

			$('#postId').change(
					function() {
						$('#location').html(
								"<option value=''>" + "--Select Location--"
										+ "</option>");
						var examId_doc = $('#postId option:selected').val();
						if (examId_doc != 0) {
							$.ajax({
								method : 'POST',
								url : 'findLocationAndSession',
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
												"<option value="+item.id+">"
														+ item.location_name
														+ '(' + item.start_time
														+ '-' + item.end_time
														+ ')' + "</option>");
									}
								},
								error : function(jqxhr, status, exception) {
									alert(exception, status)
									debugger;

								}
							});
						}
					});
			$('#location')
					.change(
							function() {
								$('#candidate').html(
										"<option value=''>"
												+ "--Select Candidate--"
												+ "</option>");
								var locId_doc = $('#location option:selected')
										.val();
								if (locId_doc != 0) {
									$
											.ajax({
												method : 'POST',
												url : 'findCandidatesBasedOnLocation',
												cache : false,
												data : {
													locId : locId_doc
												},
												ContentType : 'json',
												success : function(data) {
													$('#candidate')
															.append(
																	"<option value=all>All</option>)");

													var obj = JSON.parse(data);
													obj.forEach(itr);
													function itr(item) {
														$('#candidate')
																.append(
																		"<option value="+item[0]+">"
																		+ item[1]
																		+ " "

																		+ '('
																		+ item[0]

																		+ ')'
																		+ "</option>");
													}
													$('#candidate').chosen();
													$('#candidate').trigger(
															"chosen:updated");
												},
												error : function(jqxhr, status,
														exception) {
													alert(exception, status)
													debugger;

												}
											});
								}
							});

			$('#preveiw').click(function() {
				$('#form').attr('action', 'candidateResultsReportList');
				$('#form').submit();

			});
			$('#excelReport').click(function() {
				$('#form').attr('action', 'downloadExcelReport');
				$('#form').submit();

			});
			$('#pdf').click(function() {
				$('#form').attr('action', 'generateCandidateResultsReport');
				$('#form').submit();

			});

			var table = $("#questions_tbl").dataTable(
					{
						"responsive" : true,
						"autoWidth" : false,
						"aLengthMenu" : [
								[ 3, 5, 8, 10, 15, 20, 25, 50, 100, -1 ],
								[ 3, 5, 8, 10, 15, 20, 25, 50, 100, "ALL" ] ],
						"pageLength" : 8,
						"ordering" : false,
						"language" : {
							"search" : "Search"
						}

					});

			$("#form").validate({
				rules : {
					examId : {
						required : true,
					},
					locId : {
						required : true,
					},
					candidateId : {
						required : true,
					}

				},
				submitHandler : function(from) {
					return true; // for demo
				}
			});

		});

		function callAjax() {
			$('#location').html(
					"<option value=''>" + "--Select Location--" + "</option>");
			var examId_doc = $('#postId option:selected').val();
			if (examId_doc != 0) {
				$.ajax({
					method : 'POST',
					url : 'findLocationAndSession',
					cache : false,
					data : {
						examId : examId_doc
					},
					ContentType : 'json',
					success : function(data) {
						var obj = JSON.parse(data);
						obj.forEach(itr);
						function itr(item) {
							$('#location')
									.append(
											"<option value="+item.id+">"
													+ item.location_name + '('
													+ item.start_time + '-'
													+ item.end_time + ')'
													+ "</option>");

						}
						$('#location').val(locId);
						callAjaxCandidate();

					},
					error : function(jqxhr, status, exception) {
						alert(exception, status)
						debugger;

					}
				});
			}
		}
		function callAjaxCandidate() {
			$('#candidate').html(
					"<option value=''>" + "--Select Candidate--" + "</option>");
			var locId_doc = $('#location option:selected').val();
			if (locId_doc != 0) {
				$.ajax({
					method : 'POST',
					url : 'findCandidatesBasedOnLocation',
					cache : false,
					data : {
						locId : locId_doc
					},
					ContentType : 'json',
					success : function(data) {
						$('#candidate').append(
								"<option value=all>All</option>)");

						var obj = JSON.parse(data);
						obj.forEach(itr);
						function itr(item) {
							debugger;
							$('#candidate').append(
									"<option value="+item[0]+">"
									+ item[1]
									+ " "

									+ '('
									+ item[0]

									+ ')'
									+ "</option>");
						}
						$('#candidate').val(candidateId);
						$('#candidate').chosen();
						//$('#candidate').trigger("chosen:updated");

					},
					error : function(jqxhr, status, exception) {
						alert(exception, status)
						debugger;

					}
				});
			}
		}
	</script>
</body>
</html>