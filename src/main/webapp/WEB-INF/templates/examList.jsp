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
				<b>EXAM List</b>
			</h3>
			<div class="example-box-wrapper">

				<form class="form-horizontal" action="examlist" id="form"
					method="post">
					<div class="form-group">
						<label class="col-sm-4 control-label">Select Post Name</label>
						<div class="col-sm-4">
							<select class="form-control" name="postId" id="postChange">
								<option value=null>Select Post Name</option>
								<option value=-1>All</option>
								<c:forEach items="${value}" var="m">
									<option value=${m.postId}>${m.post_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>



					<table class="table table-bordered table-striped" id="datatable">
						<thead>
							<tr>
								<!-- <th>SNo</th> -->
								<th>Exam code</th>
								<th>Exam name</th>
								<th>Exam Date</th>
								<th>Exam Duration</th>
								<th>MFE Question</th>
								<th>NMFE Question</th>
								<th>Post Name</th>
								<th> Descriptive Duration</th>
								<th>Edit</th>
								<!-- <th>Delete</th> -->
							</tr>
						</thead>
						<tbody>
							<%
								List<Object[]> exams = (List<Object[]>) request.getAttribute("values");
								if (exams != null) {
									for (Object[] exam : exams) {
							%>
							<tr>
								<td><%=exam[1]%></td>
								<td><%=exam[2]%></td>
								<td><%=exam[3]%></td>
								<td><%=exam[4]%></td>
								<td><%=exam[5]%></td>
								<td><%=exam[6]%></td>
								<td><%=exam[8]%></td>
								<td><%=exam[7]%></td>
								<td><a href="editurl<%=exam[0]%>"><input type="hidden"
										name="edit" value=<%=exam[0]%>>Edit</a></td>
								<%-- <td><a href="deleteurl<%=exam[0]%>"><input
										type="hidden" name="edit" value=<%=exam[0]%>>Delete</a></td> --%>
							</tr>
							<%
								}
								}
							%>

						</tbody>
					</table>

				</form>
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
						/*	$("#createpost").validate({
						rules : {
							post_name : {
								required : true,
							},
							post_description : {
								required : true,
							},
							post_qualifications : {
								required : true,
							},
							no_of_vacancies : {
								required : true,
								minlength : 1,

								digits : true
							}

						},
						submitHandler : function(from) {
							alert('Post added successfully'); // for demo
							return true; // for demo
						}
						}); */

						var table = $("#datatable").DataTable(
								{
									"responsive" : true,
									"autoWidth" : false,
									"aLengthMenu" : [
											[ 3, 5, 8, 10, 15, 20, 25, 50, 100,
													-1 ],
											[ 3, 5, 8, 10, 15, 20, 25, 50, 100,
													"ALL" ] ],
									"pageLength" : 8,
									"ordering" : false,
									"language" : {
										"search" : "Search"
									}
								});

						$('#postChange').change(function() {
							$('#form').submit();

						});
					});
		</script>
</body>

</html>
