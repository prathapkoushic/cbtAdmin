
<%@page import="com.ttil.encryption.EncryptionDecryption"%>
<%@page import="java.util.Map"%>
<%@page import="java.math.*"%>
<%@page import="com.ttipl.pojo.Options"%>
<%@page import="com.ttipl.pojo.Question"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="js/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="js/datatables-responsive/css/responsive.bootstrap4.min.css">
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
				<h3 class="title-hero">Questions List</h3>
				<div class="example-box-wrapper">
					<table id="questions_tbl" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>S.no</th>
								<th>Question Name</th>
								<th>Number of Candidates Who Attempted The Questions</th>
								<th>Number of Candidates Who Answered Correctly</th>
								<th>Percentage Of Candidates Who Answered Correctly</th>

							</tr>
						</thead>




						<tbody>
							<%
								List<Object[]> data = (List<Object[]>) request.getAttribute("list");
								if (data != null) {
									int index = 1;
									for (Object[] entity : data) {
							%>
							<%
								try {
											entity[0] = EncryptionDecryption.decrypt((String) entity[0]);
										} catch (Exception e) {
											e.printStackTrace();
										}
							%>
							<%
								Integer en = ((BigInteger) entity[2]).intValue();
										Integer er = ((BigInteger) entity[1]).intValue();
										Integer cv = 100 / er;
										Integer lt = cv * en;
							%>
							<tr>
								<td><%=index++%></td>
								<td><%=entity[0]%></td>
								<td><%=entity[1]%></td>
								<td><%=en%></td>
								<td><%=lt%> %</td>


								<%
									}
									}
								%>
							</tr>
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
		$(function() {
			var table = $("#questions_tbl").dataTable({

			});
		});
	</script>


</body>
</html>
