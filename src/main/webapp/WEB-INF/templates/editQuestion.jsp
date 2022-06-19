
<%@page import="com.ttipl.pojo.Question"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html"%>
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
<script type="text/javascript"
		src="assets/widgets/ckeditor/ckeditor.js"></script>
	
	<script src="assets/widgets/ckeditor/plugins/mathjax/math.js"></script>
    <!-- MathJax configuration -->
    <script type="text/x-mathjax-config">
    MathJax.Hub.Config({
        tex2jax: {
            inlineMath: [ ['$','$'], ["\\(","\\)"] ],
            displayMath: [ ['$$','$$'], ["\\[","\\]"] ],
            processEscapes: true,
            processEnvironments: true
        },
        // Center justify equations in code and markdown cells. Elsewhere
        // we use CSS to left justify single line equations in code cells.
        displayAlign: 'center',
        "HTML-CSS": {
            styles: {'.MathJax_Display': {"margin": 0}},
            linebreaks: { automatic: true }
        }
    });
    </script>

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
				<h3 class="title-hero">EDIT QUESTION</h3>
				<div class="example-box-wrapper">
					<form action="updateQuestion" method="post" id="form"
						onclick="clinkOnBody()" class="form-horizontal bordered-row">
						<%
							Question question = (Question) request.getAttribute("question");
						%>

						<div class="form-group">
							<label class="control-label col-sm-3" for="selectpost">
								Select Exam Name : </label>
							<div class="col-sm-6">
								<select class="form-control" name="examId" required="required"
									id="examId" disabled="disabled">
									<option value="">--Select Exam Name --</option>

									<c:forEach var="exam" items="${values}">

										<option value=${exam.id} >${exam.exam_name}</option>
									</c:forEach>

								</select>
							</div>
						</div>

						<!-- <div class="form-group row">
							<label class="control-label col-sm-3" for="selectpost">
								Select Set Number : </label>
							<div class="col-sm-6">
								<select class="form-control" name="#" required="required" id="setNo" disabled="disabled">
									<option value="">--Select Set no --</option>
									<option value="SET-1">SET-1</option>
									<option value="SET-2">SET-2</option>
									<option value="SET-3">SET-3</option>
									<option value="SET-4">SET-4</option>
								</select>
							</div>
						</div> -->
						<div class="panel form-horizontal bordered-row">
							<div class="panel-body">
								<h3 class="title-hero">QUESTION (ENGLISH)</h3>
								<div class="form-group">
									<label class="control-label col-sm-2" for="fullname">Question
										: </label>
									<div class="col-sm-8">
										<!-- <textarea class="ckeditor" cols="80" id="editor1" name="editor1" rows="10"> -->
										<textarea class="ckeditor" rows="5" cols="80"
											name="questionName" id="full_name"
											placeholder="Enter Question"><%=question.getQuestionName()%></textarea>
										<input type="number" value=<%=question.getQuestionId()%>
											name="questionId" hidden=true>

									</div>
								</div>
								<div class="bordered-row">
									<div class="form-group ">
										<label class="control-label col-sm-2" for="fullname">Option
											1 : </label>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<textarea class="ckeditor" rows="5" cols="80"
												name="options[0].answerOptionEnglish" id="option1"><%=question.getOptions().get(0).getAnswerOptionEnglish()%> </textarea>

											<input type="hidden" class="form-control"
												name="options[0].optionOrder" value="1">


										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											2 : </label>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<textarea class="ckeditor" rows="5" cols="80"
												name="options[1].answerOptionEnglish"> <%=question.getOptions().get(1).getAnswerOptionEnglish()%>
									</textarea>
											<input type="hidden" class="form-control"
												name="options[1].optionOrder" value="2">

										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											3 : </label>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<textarea class="ckeditor" rows="5" cols="80"
												name="options[2].answerOptionEnglish" id="option3"><%=question.getOptions().get(2).getAnswerOptionEnglish()%>
									</textarea>
											<input type="hidden" class="form-control"
												name="options[2].optionOrder" value="3">

										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											4 : </label>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<textarea class="ckeditor" rows="5" cols="80"
												name="options[3].answerOptionEnglish" id="option4"> <%=question.getOptions().get(3).getAnswerOptionEnglish()%></textarea>
											<input type="hidden" class="form-control"
												name="options[3].optionOrder" value="4">

										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="panel form-horizontal bordered-row">
							<div class="panel-body">
								<h3 class="title-hero">QUESTION (HINDI)</h3>
								<div class="form-group">
									<label class="control-label col-sm-2" for="fullname">Question
										: </label>
									<div class="col-sm-8">
										<!-- <textarea class="ckeditor" cols="80" id="editor1" name="editor1" rows="10"> -->
										<textarea class="ckeditor" rows="5" cols="80"
											name="questionHindi"><%=question.getQuestionHindi()%>
											</textarea>

									</div>
								</div>
								<div class="bordered-row">
									<div class="form-group ">
										<label class="control-label col-sm-2" for="fullname">Option
											1 : </label>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<textarea class="ckeditor" rows="5" cols="80"
												name="options[0].answerOptionHindi"><%=question.getOptions().get(0).getAnswerOptionHindi()%> </textarea>
											<div hidden=true>
												<input type="number" class="form-control"
													name="options[0].optionId"
													value=<%=question.getOptions().get(0).getOptionId()%>
													hidden="true">
											</div>

										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											2 : </label>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<textarea class="ckeditor" rows="5" cols="80"
												name="options[1].answerOptionHindi"> <%=question.getOptions().get(1).getAnswerOptionHindi()%>
									</textarea>
											<div hidden=true>
												<input type="number" class="form-control"
													name="options[1].optionId"
													value=<%=question.getOptions().get(1).getOptionId()%>
													hidden="true">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											3 : </label>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<textarea class="ckeditor" rows="5" cols="80"
												name="options[2].answerOptionHindi"><%=question.getOptions().get(2).getAnswerOptionHindi()%>
									</textarea>
											<div hidden=true>
												<input type="number" class="form-control"
													name="options[2].optionId"
													value=<%=question.getOptions().get(2).getOptionId()%>
													hidden="true">
											</div>

										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											4 : </label>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<textarea class="ckeditor" rows="5" cols="80"
												name="options[3].answerOptionHindi"> <%=question.getOptions().get(3).getAnswerOptionHindi()%></textarea>
											<div hidden=true>
												<input type="number" class="form-control"
													name="options[3].optionId"
													value=<%=question.getOptions().get(3).getOptionId()%>
													hidden="true"> <input type="text"
													class="form-control" value=<%=question.getSetNo()%>
													hidden="true" id="setNO" name="setNo"> <input type="number"
													class="form-control" value=<%=question.getExamId()%>
													hidden="true" id="ExamId" name="examId">
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-4"></div>
							<div class="col-sm-4">
								<button id="submit" type="submit"
									class="btn btn-primary btn-hover">Update Question</button>
							</div>
						</div>
					</form>



				</div>


			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="../../assets/widgets/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
		var examId=	$('#ExamId').val();
		//alert(examId);
		//var setNo=	$('#setNO').val();
		//alert(setNo);
		$('#examId').val(examId);
	//	$('#setNo').val(setNo);

		});
	</script>
</body>
</html>




