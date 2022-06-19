
<%@page import="com.ttipl.pojo.ExamBean"%>
<%@page import="com.ttipl.pojo.User"%>
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
				<h3 class="title-hero">
					<b>Upload Questions</b>
				</h3>

				<div class="example-box-wrapper">
					<form class="form-horizontal bordered-row" action="uplaodQuestions"
						method="post" enctype="multipart/form-data"
						data-parsley-validate="">
						<%
						String msg = (String) request.getAttribute("msg");
						if (msg != null) {
						%>
						<div class="form-group" id="msg">
							<label class="control-label col-sm-12 text-center font-size-16"><%=msg%>
							</label>

						</div>
						<%
						}
						%>
						<div class="form-group row">
							<label class="control-label col-sm-4" for="selectpost">
								Select Exam Name : </label>
							<div class="col-sm-5">
								<select id="exam" class="form-control" name="examId" required="required">
									<%
									boolean status = false;
									Object object = (Object) session.getAttribute("role");
									User user = new User();
									if (object instanceof String) {
										status = true;
									} else {
										user = (User) object;
									}
									List<ExamBean> exams = (List<ExamBean>) request.getAttribute("values");
									%>
									<option value="">--Select Exam Name --</option>
									<%
									if (status) {
										for (ExamBean exam : exams) {
									%>
									<option value=<%=exam.getId()%>><%=exam.getExam_name()%>(<%=exam.getExam_code()%>)
									</option>
									<%
									}
									} else {

									if ("JE_SCR".equalsIgnoreCase(user.getUsername())) {
									%>
									<option value="45">Exam1 JE(GDCE 01/2021)</option>
									<%
									} else if ("NURSING".equalsIgnoreCase(user.getUsername())) {
									%>

									<option value="46">Exam1 NURSING(GDCE 02/2021)</option>
									<%
									} else if ("STENO".equalsIgnoreCase(user.getUsername())) {
									%>

									<option value="47">Exam2 STENO(GDCE 01/2021)</option>

									<%
									} else if ("JRTHINDI".equalsIgnoreCase(user.getUsername())) {
									%>
									<option value="48">Exam3 JRTHINDI(GDCE 01/2021)</option>

									<%
									} else if ("TECHG3".equalsIgnoreCase(user.getUsername())) {
									%>
									<option value="49">Exam3 GRADE3(GDCE 02/2021)</option>
									<%
									}
									}
									%>




								</select>
							</div>
						</div>

						<input type="hidden" name="setNo" value="SET-1">

						<!-- <div class="form-group row">
							<label class="control-label col-sm-4" for="selectpost">
								Select Set Number : </label>
							<div class="col-sm-5">
								<select class="form-control" name="setNo">
									<option value="">--Select Set no --</option>
									<option value="SET-1">SET-1</option>
									<option value="SET-2">SET-2</option>
									<option value="SET-3">SET-3</option>
									<option value="SET-4">SET-4</option>

								</select>
							</div>
						</div> -->

						<div class="form-group">
							<label class="control-label col-sm-4" for="fullname">Upload
								File : </label>
							<div class="col-sm-5">
								<input type="file" class="form-control" id="file" name="file"
									accept=".xlsx" required="required">

							</div>
							<div class="col-md-3 text-right">
								To download Sample Excel sheet <a
									href="excel_formats/QUESTIONS_FORMAT.xlsx">click here </a>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="fullname"> Is
								password Protected :</label>
							<div class="col-sm-5 ">
								<label class="radio-inline"> <input type="radio"
									class="" id="radioYes" name="Protected" value="yes">Yes
								</label> <label class="radio-inline"> <input type="radio"
									class=" " id="radioNo" name="Protected" value="no">No
								</label>
							</div>
						</div>
						<div class="form-group" hidden=true id="hiddenField">
							<label class="control-label col-sm-4" for="fullname">
								Enter Password :</label>
							<div class="col-sm-5">
								<input type="password" class="form-control" id="password"
									name="password">

							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4" for="fullname"> </label>
							<div class="col-sm-5">
								<button id="submit" type="submit"
									class="btn btn-primary btn-hover">Upload Questions</button>
							</div>
						</div>





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
		$(document).ready(function() {
			$('#radioYes').click(function() {
				$('#hiddenField').show();
			});
			$('#radioNo').click(function() {
				$('#hiddenField').hide();
			});

		});
		
		 $('#file').change(function(){
				let file = $("#file")[0].files[0]; 
				var fileName = file.name;
				var examName = $('#exam option:selected').html();
			   var examOnly = examName.replace(/[^a-zA-Z ]/g, "");
			  
			  var er =  examOnly.split(" ");
			  var fileName1 = fileName.split(".");
			    if(er[0].trim().toUpperCase().match(fileName1[0].trim().toUpperCase())==null)
			    	{
			    	 alert("YOU HAVE SELECTED DIFFERENT QUESTION PAPER FOR YOUR EXAM PLEASE ONCE CHECK");
			    	 clearFileInput(document.getElementById("file"));
			    	 return false;
			    	}
			});
		 
		 function clearFileInput(ctrl) {
			  try {
			    ctrl.value = null;
			  } catch(ex) { }
			  if (ctrl.value) {
			    ctrl.parentNode.replaceChild(ctrl.cloneNode(true), ctrl);
			  }
			} 
	</script>
</body>
</html>

