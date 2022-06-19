
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

		<div class="col-sm-12">

			<div class="panel">
				<div class="panel-body">
					<h3 class="title-hero">RESULT PROCESSING</h3>
					<div class="example-box-wrapper">
						<form action="resultProcessing" method="post" id="form"
							class="form-horizontal bordered-row">

							<%
								String msg = (String) request.getAttribute("msg");
								if (msg != null) {
							%>
							<div class="form-group" id="msg">
								<label class="control-label col-sm-12 text-center font-size-16
"><%=msg%></label>

							</div>
							<%
								}
							%>


							<div class="form-group">
								<label class="control-label col-sm-2" for="select exam name">Select
									Exam Name :</label>
								<div class="col-sm-3">
									<select id="postId" class="form-control" name="examId" required>
										<option value="">--Select Exam Name--</option>
										<c:forEach var="exam" items="${exams}">
											<option value=${exam.id} >${exam.exam_name}(${exam.exam_code})</option>
										</c:forEach>
									</select>
								</div>
								<label class="control-label col-sm-3" for="select Exam location">Select
									Location (Session):</label>
								<div class="col-sm-3">
									<select id="location" class="form-control"
										name="exam_loc_session_id" required>
										<option value="">--Select Location</option>

									</select>
								</div>
								
							</div>
							<!-- <div class="form-group">
								<label class="control-label col-sm-6" for="select Exam location">Select
									Location (Session):</label>
								<div class="col-sm-6">
									<select id="location" class="form-control"
										name="exam_loc_session_id" required>
										<option value="">--Select Location</option>

									</select>
								</div>
							</div>-->
							<div class="form-group">
								<div class="col-md-11 text-right">
									<button type="submit" class="btn btn-primary">Process
										Result</button>
								</div>
							</div> 
							<!-- 	<div class="col-md-12">
				<div class="preBtn text-center">
					<button type="submit" class="btn btn-primary submit" onclick="gone()" value="Generate Excel">Generate EXCEL</button>
				</div>
			</div> 
			 -->
						</form>
					</div>
				</div>
			</div>
			<!-- <div class="footer">
				<div class="row">
					<div class="col-md-6">
						<div class="footer-info"></div>
					</div>
					<div class="col-md-6 text-right">
						<div class="footer-info">
							<span>&copy; 2020 Timing Technologies India Pvt Ltd. </span>
						</div>
					</div>
				</div>
			</div> -->
		</div>
		<script type="text/javascript">
			$(function() {

				$('#postId')
						.change(
								function() {
									$('#location').html(
											"<option value='%'>"
													+ "all"
													+ "</option>");
									var examId_doc = $(
											'#postId option:selected').val();
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
													success : function(data) {
														var obj = JSON
																.parse(data);
														obj.forEach(itr);
														function itr(item) {
															debugger;
															$('#location')
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
													error : function(jqxhr,
															status, exception) {
														alert(exception, status)
														debugger;

													}
												});
									}
								});
			
			});
		</script>
</body>
</html>