
<%@page import="com.ttipl.pojo.DescriptiveQuestion"%>
<%@page import="java.util.Map"%>
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
<script type="text/javascript" src="assets/widgets/ckeditor/ckeditor.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-AMS_HTML"></script>
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
					<form action="editQuestion" method="post" id="form"
						class="form-horizontal bordered-row">
						
						<h4 style="color:red">EXAM NAME : ${examName}</h4>
						<div class="form-group row">
							<label class="control-label col-sm-4" for="selectLanguage">
								Select Language : </label>
							<div class="col-sm-5">
								<select onchange="go()" class="form-control" name="setNo" id="lang">
									<option value="" selected style="display:none"> --Select Language -- </option>
									<option value="english">ENGLISH</option>
									<option value="hindi">HINDI</option>
									<!-- <option value="telugu">TELUGU</option>
									<option value="tamil">TAMIL</option>
									<option value="urdu">URDU</option>
									<option value="kanada">KANNADA</option>
									<option value="marathi">MARATI</option> -->
									

								</select>
							</div>
						</div>
						
						<a href="list2"><button type="button" class="btn btn-primary">BACK TO LIST</button></a>
						

						<table style="visibility:hidden" class="table table-bordered table-striped "
							id="hindi">
							<thead>
								<tr>
								    <td><b>S.NO</b>
									<td><b>Question (HINDI)</b></td>
									<td><b>LEVEL</b></td>
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map1 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map1 != null) 
									{
                                        int i = 0;
										List<DescriptiveQuestion> questions = (List<DescriptiveQuestion>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null) 
										{
											
											for (DescriptiveQuestion question : questions) {
													
								%>
														
								<tr>

								<%    
								String question1 = "";
								String questionHindi = "";
								++i;
								  if(question.getBlob_hindi()!=null)
								  {
									  questionHindi = question.getBlob_hindi();
									  question1 += "<img src=\"data:image/jpeg;base64," + questionHindi
												+ "\" height=\"150px\" width=\"450px\"><br>";
								  }   
								  
								  else
								  {
									  question1 = question.getQuestionHindi();
								  }
								%>		
								    <td width="200px"><%= i%></td>						
									<td width="200px"><%=question1.trim()%></td>
									<td width="200px"><%=question.getLevel()%></td>
								</tr>
								<%
									}
										}
									}
								%>
							</tbody>
						</table>
						
						
						<table style="visibility:hidden" class="table table-bordered table-striped "
							id="english">
							<thead>
								<tr>	
								    <td><b>S.NO</b>								
									<td><b>Question (ENGLISH)</b></td> 
									<td><b>LEVEL</b></td>
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map2 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map2 != null) 
									{
										 int i = 0;
										List<DescriptiveQuestion> questions = (List<DescriptiveQuestion>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null)
										{
											
											for (DescriptiveQuestion question : questions) {
																								
								   %>
								
								 <tr>
                                  
                                  <%    
                                  ++i;
								String question2 = "";
								String questionEnglish = "";
								  if(question.getBlob_english()!=null)
								  {
									  questionEnglish = question.getBlob_english();
									  question2 += "<img src=\"data:image/jpeg;base64," + questionEnglish
												+ "\" height=\"150px\" width=\"450px\"><br>";
								  }   
								  
								  else
								  {
									  question2 = question.getQuestionName();
								  }
								%>
								
									<td width="200px"><%= i%></td>
									<td width="200px"><%=question2.trim()%></td>
									<td width="200px"><%=question.getLevel()%></td>
									
								</tr>
								<%
									}
										}
									}
								%>
							</tbody>
						</table>
						
						<input type="number" name="questionId" id="questionId"
							hidden="true">
					</form>
				</div>
			</div>
		</div>

	</div>
	
	<script>
	 function go()
	 {
	
	  var hindi = document.getElementById("hindi");
	  var english = document.getElementById("english");
	  
	  
	  var lang =  document.getElementById("lang").value;
	  
	  if (lang == "") {
			
				alert('please select the values');
				return false;
			}
	 
	  
	   if(lang=="hindi")
	  {
	    hindi.style.visibility = "visible";
	    hindi.style.position = "inherit";
	    
	   
	    english.style.visibility = "hidden";
	    english.style.position = "absolute";
	    
	    
	  }
	   
	  
	  
	 
	             
	  
			
			
	  
	  
	    if(lang=="english")
	  {
	    english.style.visibility = "visible";
	    english.style.position = "inherit";
	    
	  
	    hindi.style.visibility = "hidden";
	    hindi.style.position = "absolute";
	   
	    
	  }
	  
	
	  
	  
	  
	 }
	</script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable.js">
		
	</script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-bootstrap.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-tabletools.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {

					var table = $("#questions_tbl")
							.DataTable(
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
										"language" : {
											"search" : "Search"
										}
									});
				});

		function clickOnEdit(id) {

			$('#questionId').val(id);
			$('form').attr('action', 'editQuestion');
		}
		function clickOnsubmit() {
			//	var setNO=$('#setno').find(":selected").val();
			var examid = $('#examId').find(":selected").val();
			if (examid != "") {
				$('form').attr('action', 'questionsList');
			} else {
				alert('please select the values');
				return false;
			}

		}
	</script>

</body>
</html>

