<%@page import="com.ttipl.pojo.PostCategory"%>
<%@page import="com.ttipl.pojo.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


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
				<b>UPDATE POST</b>
			</h3>
			<div class="example-box-wrapper">

				<form class="form-horizontal bordered-row" action="updatePost"
					method="post" id="createpost" data-parsley-validate=""
					id="demo-form">
					<%int postCId=0;
						Post post = (Post) request.getAttribute("post");
						if (post != null) {
							postCId=post.getPostCategoryId();
					%>
					<input type="number" name="postId" value=<%=post.getPostId()%>
						hidden=true>


					<div class="form-group">
						<label class="col-sm-3 control-label"> Select Post
							Category</label>
						<div class="col-sm-6">
							<select class="form-control" id="postCategoryId"
								name="postCategoryId" required="required"><option
									value="">select post category</option>
								<%
									List<PostCategory> postCs = (List<PostCategory>) request.getAttribute("postCategory");
										if (postCs != null) {
											for (PostCategory postC : postCs) {
								%>

								<option value=<%=postC.getPostCategoryId()%>><%=postC.getPostCategoryName()%></option>
								<%
									}
										}
								%>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">PostName</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="post_name"
								placeholder="Enter post name" name="post_name"
								value="<%=post.getPost_name()%>" required="required">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Post Qualification</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="post_qualifications"
								placeholder="Enter qualification" name="post_qualifications"
								value="<%=post.getPost_qualifications()%>" required="required">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Vacancies</label>
						<div class="col-sm-6">
							<input type="number" class="form-control" id="no_of_vacancies"
								placeholder="Enter vacancies" name="no_of_vacancies" required
								value=<%=post.getNo_of_vacancies()%> min="1" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="description">Description:
						</label>
						<div class="col-sm-6">
							<textarea class="form-control" id="post_description"
								placeholder="Enter description" name="post_description" rows="2"><%=post.getPost_description()%></textarea>
						</div>
					</div>





					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6">
							<!-- <button id="submit" type="submit" class="btn btn-primary button">ADD</button>
 -->
							<button type="submit" class="btn btn-alt btn-hover btn-primary"
								id="submit">Update</button>
						</div>
					</div>


					<%
						}
					%>


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
	<script type="text/javascript">
		$(document).ready(function() {
			var postCid=<%=postCId%>;
			$("#postCategoryId").val(postCid);
		});
	</script>
</body>
</html>