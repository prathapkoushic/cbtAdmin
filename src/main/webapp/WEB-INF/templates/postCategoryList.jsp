<%@page import="com.ttipl.pojo.PostCategory"%>
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
			<h3 class="title-hero">POST CATEGORY LIST</h3>
			<div class="example-box-wrapper">




				<form action="editPostCategory" method="post">

					<%
						String msg = (String) request.getAttribute("msg");
						String col = "";
						if (msg != null) {

							if (msg == "-1") {
								msg = "error while adding post";
								col = " font-red";
							} else {
								col = "font-green";
							}
					%>
					<div class="form-group" id="msg">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6 ">
							<p class="text-center font-size-16 <%=col%>">
								<b><%=msg%></b>
							</p>
						</div>

					</div>

					<%
						}
					%>

					<div class="col-sm-12">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<td>S.no</td>
									<td>Post Category Name</td>
									<td>Edit</td>
							</thead>
							<%
								List<PostCategory> postCategorys = (List<PostCategory>) request.getAttribute("postCategory");
								if (postCategorys != null) {

									int i = 1;
							%>
							<tbody>
								<%
									for (PostCategory postCategory : postCategorys) {
								%>
								<tr>

									<td><%=i++%></td>
									<td><%=postCategory.getPostCategoryName()%></td>
									<td id="td_remove_"><button
											onclick="clickForEdit(<%=postCategory.getPostCategoryId()%>)"
											class="buttonAslink">Edit</button></td>
								</tr>
								<%
									}
								%>
							</tbody>
							<%
								}
							%>


						</table>
					</div>
					<input type="number" name="postCategoryId" id="postId" hidden=true>
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
		 $(document).ready(function() {
			

			var table = $("#datatable").DataTable({
			 "responsive" : true,
			"autoWidth" : false,
			"aLengthMenu" : [
					[ 3, 5,8, 10, 15, 20, 25, 50,
							100, -1 ],
					[ 3, 5, 8,10, 15, 20, 25, 50,
							100, "ALL" ] ],
			"pageLength" : 8,
			"ordering" : false,
			 "language": {
				    "search": "Search"
				  }
			});
		});
		
		function clickForEdit(id){
			
			$('#postId').val(id);
			$('form').attr('action','editPostCategory');
		}
	
			
		
	</script>
</body>
</html>