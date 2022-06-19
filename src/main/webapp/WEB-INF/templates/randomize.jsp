
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
					<form class="form-horizontal bordered-row" action="randomize"
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
								<select id="exam" class="form-control" name="exam_id" required="required">
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
									<option value=<%=exam.getId()%>><%=exam.getExam_name()%> (<%=exam.getExam_code()%>)
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

						

				        <div class="form-group">
							<label class="control-label col-sm-4" for="easy">EASY : </label>
							<div class="col-sm-5">
								<input class="form-control" type="text" name="easy" required="required">
  							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4" for="medium">MEDIUM : </label>
							<div class="col-sm-5">
								<input class="form-control" type="text" name="medium" required="required">
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-4" for="difficult"> Difficult :</label>
							<div class="col-sm-5 ">
								<input class="form-control" type="text" name="difficult" required="required">
							</div>
						</div>
						
                      <div class="form-group">
						
						<div class="col-sm-12">
					
							<button type="submit" class="btn btn-alt btn-hover btn-primary"
								id="submit" >SUBMIT</button>
						</div>
					</div>


					</form>
				</div>
			</div>
		</div>
		
	</div>
	<script type="text/javascript">
		$(document).ready(function() 
				{
			$('#radioYes').click(function() {
				$('#hiddenField').show();
			});
			$('#radioNo').click(function() {
				$('#hiddenField').hide();
			});

		});
		
		 $('#file').change(function()
				 {
				let file = $("#file")[0].files[0]; 
				var fileName = file.name;
				var examName = $('#exam option:selected').html();
				var level = $('#level option:selected').html();
			    var examOnly = examName.replace(/[^a-zA-Z ]/g, "");
			    var er =  examOnly.split(" ");
			    var levelOnly = level.replace(/[^a-zA-Z ]/g, "");
			    var lv =  levelOnly.split(" ");
			    var fileName1 = fileName.split(".");
			    var merge = er[0]+"_"+lv;
			
			    if(!(merge.trim().toUpperCase()===fileName1[0].trim().toUpperCase()))
			    	{
			    	 alert("YOU HAVE SELECTED DIFFERENT QUESTION PAPER FOR YOUR EXAM PLEASE ONCE CHECK EXAM NAME AND LEVEL");
			    	 clearFileInput(document.getElementById("file"));
			    	 return false;
			    	}
			});
		 
		 function clearFileInput(ctrl) 
		 {
			  try 
			  {
			    ctrl.value = null;
			  } 
			  catch(ex) {}
			  if (ctrl.value) 
			  {
			    ctrl.parentNode.replaceChild(ctrl.cloneNode(true), ctrl);
			  }
		} 
	</script>
</body>
</html>

