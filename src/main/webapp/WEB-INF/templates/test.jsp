
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
						
						<a href="list"><button type="button" class="btn btn-primary">BACK TO LIST</button></a>
						
						<table style="visibility:hidden" class="table table-bordered table-striped" id="hindi">
							<thead>
								<tr>
									<td><b>S.NO</b>
									<td><b>Question (HINDI)</b></td>
									<td><b>Options (HINDI)</b></td>
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map1 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map1 != null) 
									{
										 int i1 = 0;
										List<Question> questions = (List<Question>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null) 
										{
											String options_Hindi = "";
											
											for (Question question : questions) {
												
												options_Hindi = "";
												List<Options> options = question.getOptions();
												for (int i = 0; i < options.size(); i++) {
													options_Hindi += (1 + i + ") " + options.get(i).getAnswerOptionHindi());
													String imge1 = "";
													if (options.get(i).getBlob_hindi() != null) 
													{
														imge1 = options.get(i).getBlob_hindi();
													
								 %>
								
								<%
									options_Hindi += "<img src=\"data:image/jpeg;base64," + imge1
																+ "\" height=\"150px\" width=\"450px\"><br>";
								%>
								<%
									} else {
														options_Hindi += "<br>";
													}
							                	%>
								
							
								<%
									}
								%>

								<tr>

								<%    
								++i1;
								String question1 = "";
								String questionHindi = "";
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
								
									<td width="200px"><%= i1%></td>	
									<td width="200px"><%=question1%> </td>
				                    <td><%=options_Hindi%></td>
									
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
									<td><b>Options (ENGLISH)</b></td>
									
 
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map2 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map2 != null) {
										int i1 = 0;
										List<Question> questions = (List<Question>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null) {
											String options_english = "";
											
											for (Question question : questions) {
												
												options_english = "";
												List<Options> options = question.getOptions();
												for (int i = 0; i < options.size(); i++) {
													options_english += (1 + i + ") " + options.get(i).getAnswerOptionEnglish());
													String imge2 = "";
                                                     	if (options.get(i).getBlob_english() != null) {
														imge2 = options.get(i).getBlob_english();
													
								%>
								
																<%
									options_english += "<img src=\"data:image/jpeg;base64," + imge2
																+ "\" height=\"150px\" width=\"450px\"><br>";
								%>
								<%
									} else {
														options_english += "<br>";
													}
								%>
							
								<%
									}
								%>

								<tr>
                                  
                                  <%    
                                  ++i1;
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
								
								
									<td width="200px"><%= i1%></td>
									<td width="200px"><%=question2%> </td>
				                    <td><%=options_english%></td>
									
								</tr>
								<%
									}
										}
									}
								%>
							</tbody>
						</table>
						
						
						<table style="visibility:hidden" class="table table-bordered table-striped "
							id="telugu">
							<thead>
								<tr>

									
									<td><b>Question (TELUGU)</b></td>
									<td><b>Options (TELUGU)</b></td>
									
 
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map3 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map3 != null) {

										List<Question> questions = (List<Question>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null) {
											String options_telugu = "";
											
											for (Question question : questions) {
												
												options_telugu = "";
												List<Options> options = question.getOptions();
												for (int i = 0; i < options.size(); i++) {
													options_telugu += (1 + i + ") " + options.get(i).getAnswerOptionTelugu());
                                                    String imge3 = "";
                                                     	if (options.get(i).getBlob_telugu() != null) {
														imge3 = options.get(i).getBlob_telugu();
													
								%>
								
															<%
									options_telugu += "<img src=\"data:image/jpeg;base64," + imge3
																+ "\" height=\"150px\" width=\"450px\"><br>";
								%>
								<%
									} else {
														options_telugu += "<br>";
													}
								%>
							
							
								<%
									}
								%>

								<tr>
                                
								   <%    
								String question3 = "";
								String questionTelugu = "";
								  if(question.getBlob_telugu()!=null)
								  {
									  questionTelugu = question.getBlob_telugu();
									  question3 += "<img src=\"data:image/jpeg;base64," + questionTelugu
												+ "\" height=\"150px\" width=\"450px\"><br>";
								  }   
								  
								  else
								  {
									  question3 = question.getQuestionTelugu();
								  }
								%>
								
								
									
									<td width="200px"><%=question3%> </td>
				                    <td><%=options_telugu%></td>
									
								</tr>
								<%
									}
										}
									}
								%>
							</tbody>
						</table>
						
						
						<table style="visibility:hidden" class="table table-bordered table-striped "
							id="marathi">
							<thead>
								<tr>

									
									
									<td><b>Question (MARATHI)</b></td>
									<td><b>Options (MARATHI)</b></td>
									
 
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map4 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map4 != null) {

										List<Question> questions = (List<Question>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null) {
											String options_marathi = "";
											
											for (Question question : questions) {
												
												options_marathi = "";
												List<Options> options = question.getOptions();
												for (int i = 0; i < options.size(); i++) {
													options_marathi += (1 + i + ") " + options.get(i).getAnswerOptionMarathi());
													String imge4 = "";
                                                     	if (options.get(i).getBlob_marathi() != null) {
														imge4 = options.get(i).getBlob_marathi();

													
								%>
								
															<%
									options_marathi += "<img src=\"data:image/jpeg;base64," + imge4
																+ "\" height=\"150px\" width=\"450px\"><br>";
								%>
								<%
									} else {
														options_marathi += "<br>";
													}
								%>
							
							
								<%
									}
								%>

								<tr>
                                    
                                      <%    
								String question4 = "";
								String questionMarathi = "";
								  if(question.getBlob_marathi()!=null)
								  {
									  questionMarathi = question.getBlob_marathi();
									  question4 += "<img src=\"data:image/jpeg;base64," + questionMarathi
												+ "\" height=\"150px\" width=\"450px\"><br>";
								  }   
								  
								  else
								  {
									  question4 = question.getQuestionMarathi();
								  }
								%>
								
								
									
									<td width="200px"><%=question4%> </td>
				                    <td><%=options_marathi%></td>
									
								</tr>
								<%
									}
										}
									}
								%>
							</tbody>
						</table>
						
						
							<table style="visibility:hidden" class="table table-bordered table-striped "
							id="tamil">
							<thead>
								<tr>

									
									
									<td><b>Question (TAMIL)</b></td>
									<td><b>Options (TAMIL)</b></td>
									
 
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map5 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map5 != null) {

										List<Question> questions = (List<Question>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null) {
											String options_tamil = "";
											
											for (Question question : questions) {
												
												options_tamil = "";
												List<Options> options = question.getOptions();
												for (int i = 0; i < options.size(); i++) {
													options_tamil += (1 + i + ") " + options.get(i).getAnswerOptionTamil());
                                                    String imge5 = "";
                                                     	if (options.get(i).getBlob_tamil() != null) {
														imge5 = options.get(i).getBlob_tamil();
													
								%>
								
															<%
									options_tamil += "<img src=\"data:image/jpeg;base64," + imge5
																+ "\" height=\"150px\" width=\"450px\"><br>";
								%>
								<%
									} else {
														options_tamil += "<br>";
													}
								%>
							
							
								<%
									}
								%>

								<tr>
                                   
                                          <%    
								String question5 = "";
								String questionTamil = "";
								  if(question.getBlob_tamil()!=null)
								  {
									  questionTamil = question.getBlob_tamil();
									  question5 += "<img src=\"data:image/jpeg;base64," + questionTamil
												+ "\" height=\"150px\" width=\"450px\"><br>";
								  }   
								  
								  else
								  {
									  question5 = question.getQuestionTamil();
								  }
								%>
								
								
								
									
									<td width="200px"><%=question5%> </td>
				                    <td><%=options_tamil%></td>
									
								</tr>
								<%
									}
										}
									}
								%>
							</tbody>
						</table>
						
						
						
								<table style="visibility:hidden" class="table table-bordered table-striped "
							id="kanada">
							<thead>
								<tr>
									
									<td><b>Question (KANADA)</b></td>
									<td><b>Options (KANADA)</b></td>
									
 
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map6 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map6 != null) {

										List<Question> questions = (List<Question>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null) {
											String options_kanada = "";
											
											for (Question question : questions) {
												
												options_kanada = "";
												List<Options> options = question.getOptions();
												for (int i = 0; i < options.size(); i++) {
													options_kanada += (1 + i + ") " + options.get(i).getAnswerOptionKanada());
                                                   String imge6 = "";
                                                     	if (options.get(i).getBlob_kanada() != null) {
														imge6 = options.get(i).getBlob_kanada();
													
								%>
								
															<%
									options_kanada += "<img src=\"data:image/jpeg;base64," + imge6
																+ "\" height=\"150px\" width=\"450px\"><br>";
								%>
								<%
									} else {
														options_kanada += "<br>";
													}
								%>
							
							
								<%
									}
								%>

								<tr>

								     <%    
								String question6 = "";
								String questionKanada = "";
								  if(question.getBlob_kanada()!=null)
								  {
									  questionKanada = question.getBlob_kanada();
									  question6 += "<img src=\"data:image/jpeg;base64," + questionKanada
												+ "\" height=\"150px\" width=\"450px\"><br>";
								  }   
								  
								  else
								  {
									  question6 = question.getQuestionKanada();
								  }
								%>
								
							
									<td width="200px"><%=question6%> </td>
				                    <td><%=options_kanada%></td>
									
								</tr>
								<%
									}
										}
									}
								%>
							</tbody>
						</table>
						
						
						<table style="visibility:hidden" class="table table-bordered table-striped "
							id="urdu" dir="rtl">
							<thead>
								<tr>

									
									
									<td><b>Question (URDU)</b></td>
									<td><b>Options (URDU)</b></td>
									
 
								</tr>
							<thead>
							<tbody>
								<%
									Map<Integer, String> map7 = (Map<Integer, String>) request.getAttribute("examMap");
									if (map7 != null) {

										List<Question> questions = (List<Question>) request.getAttribute("questions");
										int sNo = 1;
										if (questions != null) {
											String options_urdu = "";
											
											for (Question question : questions) {
												
												options_urdu = "";
												List<Options> options = question.getOptions();
												for (int i = 0; i < options.size(); i++) {
													options_urdu += (1 + i + ") " + options.get(i).getAnswerOptionUrdu());
                                                   String imge7 = "";
                                                     	if (options.get(i).getBlob_urdu() != null) {
														imge7 = options.get(i).getBlob_urdu();
													
								%>
								
															<%
									options_urdu += "<img src=\"data:image/jpeg;base64," + imge7
																+ "\" height=\"150px\" width=\"450px\"><br>";
								%>
								<%
									} else {
														options_urdu += "<br>";
													}
								%>
							
							
								<%
									}
								%>

								<tr>

								    <%    
								String question7 = "";
								String questionUrdu = "";
								  if(question.getBlob_urdu()!=null)
								  {
									  questionUrdu = question.getBlob_urdu();
									  question7 += "<img src=\"data:image/jpeg;base64," + questionUrdu
												+ "\" height=\"150px\" width=\"450px\"><br>";
								  }   
								  
								  else
								  {
									  question7 = question.getQuestionUrdu();
								  }
								%>
								
									
									<td width="200px"><%=question7%> </td>
				                    <td><%=options_urdu%></td>
									
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
	  var telugu = document.getElementById("telugu");
	  
	  
	  var lang =  document.getElementById("lang").value;
	  
	  if (lang == "") {
			
				alert('please select the values');
				return false;
			}
	 
	  
	   if(lang=="hindi")
	  {
	    hindi.style.visibility = "visible";
	    hindi.style.position = "inherit";
	    
	    marathi.style.visibility = "hidden";
	    marathi.style.position = "absolute";
	    telugu.style.visibility = "hidden";
	    telugu.style.position = "absolute";
	    tamil.style.visibility = "hidden";
	    tamil.style.position = "absolute";
	    kanada.style.visibility = "hidden";
	    kanada.style.position = "absolute";
	    english.style.visibility = "hidden";
	    english.style.position = "absolute";
	    urdu.style.visibility = "hidden";
	    urdu.style.position = "absolute";
	    
	  }
	   
	   if(lang=="telugu")
		  {
		    telugu.style.visibility = "visible";
		    telugu.style.position = "inherit";
		    
		    marathi.style.visibility = "hidden";
		    marathi.style.position = "absolute";
		    hindi.style.visibility = "hidden";
		    hindi.style.position = "absolute";
		    tamil.style.visibility = "hidden";
		    tamil.style.position = "absolute";
		    kanada.style.visibility = "hidden";
		    kanada.style.position = "absolute";
		    english.style.visibility = "hidden";
		    english.style.position = "absolute";
		    urdu.style.visibility = "hidden";
		    urdu.style.position = "absolute";
		    
		  }
	   
	   if(lang=="kanada")
		  {
		    kanada.style.visibility = "visible";
		    kanada.style.position = "inherit";
		    
		    marathi.style.visibility = "hidden";
		    marathi.style.position = "absolute";
		    hindi.style.visibility = "hidden";
		    hindi.style.position = "absolute";
		    tamil.style.visibility = "hidden";
		    tamil.style.position = "absolute";
		    telugu.style.visibility = "hidden";
		    telugu.style.position = "absolute";
		    english.style.visibility = "hidden";
		    english.style.position = "absolute";
		    urdu.style.visibility = "hidden";
		    urdu.style.position = "absolute";
		    
		  }
	   
	   if(lang=="tamil")
		  {
		   tamil.style.visibility = "visible";
		   tamil.style.position = "inherit";
		    
		    marathi.style.visibility = "hidden";
		    marathi.style.position = "absolute";
		    hindi.style.visibility = "hidden";
		    hindi.style.position = "absolute";
		    kanada.style.visibility = "hidden";
		    kanada.style.position = "absolute";
		    telugu.style.visibility = "hidden";
		    telugu.style.position = "absolute";
		    english.style.visibility = "hidden";
		    english.style.position = "absolute";
		    urdu.style.visibility = "hidden";
		    urdu.style.position = "absolute";
		    
		  }
	  
	   if(lang=="urdu")
		  {
		   urdu.style.visibility = "visible";
		   urdu.style.position = "inherit";
		    
		    marathi.style.visibility = "hidden";
		    marathi.style.position = "absolute";
		    hindi.style.visibility = "hidden";
		    hindi.style.position = "absolute";
		    kanada.style.visibility = "hidden";
		    kanada.style.position = "absolute";
		    telugu.style.visibility = "hidden";
		    telugu.style.position = "absolute";
		    english.style.visibility = "hidden";
		    english.style.position = "absolute";
		    tamil.style.visibility = "hidden";
		    tamil.style.position = "absolute";
		    
		  }
	  
	   if(lang=="marathi")
		  {
		   marathi.style.visibility = "visible";
		   marathi.style.position = "inherit";
		    
		    urdu.style.visibility = "hidden";
		    urdu.style.position = "absolute";
		    hindi.style.visibility = "hidden";
		    hindi.style.position = "absolute";
		    kanada.style.visibility = "hidden";
		    kanada.style.position = "absolute";
		    telugu.style.visibility = "hidden";
		    telugu.style.position = "absolute";
		    english.style.visibility = "hidden";
		    english.style.position = "absolute";
		    tamil.style.visibility = "hidden";
		    tamil.style.position = "absolute";
		    
		  }
	 
	  
	 
	             
	  
			
			
	  
	  
	    if(lang=="english")
	  {
	    english.style.visibility = "visible";
	    english.style.position = "inherit";
	    
	    marathi.style.visibility = "hidden";
	    marathi.style.position = "absolute";
	    telugu.style.visibility = "hidden";
	    telugu.style.position = "absolute";
	    tamil.style.visibility = "hidden";
	    tamil.style.position = "absolute";
	    kanada.style.visibility = "hidden";
	    kanada.style.position = "absolute";
	    hindi.style.visibility = "hidden";
	    hindi.style.position = "absolute";
	    urdu.style.visibility = "hidden";
	    urdu.style.position = "absolute";
	    
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

