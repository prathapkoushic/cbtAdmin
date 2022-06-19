
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
.error {
	color: red;
	font-weight: bold;
	font-size: 12px;
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
					<b>ADD QUESTION</b>
				</h3>
				<div class="example-box-wrapper">
					<form action="savingQuestionUrl" method="post" id="f3"
						class="form-horizontal bordered-row">
						<%
							String msg = (String) request.getAttribute("msg");
							if (msg != null) {
						%>
						<div class="form-group" id="msg">
							<label class="control-label col-sm-12 text-center font-size-16"><%=msg%></label>

						</div>
						<%
							}
						%>

						<div class="form-group">
							<label class="control-label col-sm-3" for="selectpost">
								Select Exam Name : </label>
							<div class="col-sm-6">
								<select class="form-control" name="examId" required="required">
									<option value="">--Select Exam Name --</option>

									<c:forEach var="exam" items="${values}">

										<option value=${exam.id}>${exam.exam_name}</option>
									</c:forEach>

								</select>
							</div>
						</div>
						<input type="hidden" name="setNo" value="SET-1">
						<!-- <div class="form-group ">
							<label class="control-label col-sm-3" for="selectpost">
								Select Set Number : </label>
							<div class="col-sm-6">
								<select class="form-control" name="setNo" required="required">
									<option value="">--Select Set no --</option>
									<option value="SET-1">SET-1</option>
									<option value="SET-2">SET-2</option>
									<option value="SET-2">SET-3</option>
									<option value="SET-2">SET-4</option>

								</select>
							</div>
						</div> -->
						<div class="panel form-horizontal bordered-row">
							<div class="panel-body">
								<h3 class="title-hero">
									<b>QUESTION (ENGLISH)</b>
								</h3>
								<div class="form-group">
									<label class="control-label col-sm-2" for="fullname">Question
										: </label>
									<div class="col-sm-8">
										<!-- <textarea class="ckeditor1"  required="required" cols="80" id="editor1" name="editor1" rows="10"> -->
										<textarea class="ckeditor1" rows="5" cols="80"
											name="questionName" id="question_english"
											placeholder="Enter Question" required="required"
											minlength="6"></textarea>
										<input type="hidden" class="form-control"
											name="englishImageId" id="question_english_image_id"
											placeholder="" value="1">

									</div>
								</div>
								<div class="bordered-row">
									<div class="form-group ">
										<label class="control-label col-sm-2" for="fullname">Option
											1 : </label>
										<div class="col-sm-1"></div>
										<div class="col-sm-6">
											<textarea class="ckeditor1" required="required" rows="5"
												cols="80" name="options[0].answerOptionEnglish" id="option1"
												placeholder="Enter Option 1"> </textarea>

											<input type="hidden" class="form-control"
												name="options[0].optionOrder" id="optionOrder1"
												placeholder="Enter Option 2" value="1"> <input
												type="hidden" class="form-control"
												name="options[0].englishImageId" id="option1_image_id">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											2 : </label>
										<div class="col-sm-1"></div>
										<div class="col-sm-6">
											<textarea class="ckeditor1" required="required" rows="5"
												cols="80" name="options[1].answerOptionEnglish" id="option2"
												placeholder="Enter Option 2"> 
									</textarea>
											<input type="hidden" class="form-control"
												name="options[1].optionOrder" id="optionOrder2"
												placeholder="Enter Option 2" value="2"> <input
												type="hidden" class="form-control"
												name="options[1].englishImageId" id="option2_image_id">

										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											3 : </label>
										<div class="col-sm-1"></div>
										<div class="col-sm-6">
											<textarea class="ckeditor1" required="required" rows="5"
												cols="80" name="options[2].answerOptionEnglish" id="option3"
												placeholder="Enter Option 3">
									</textarea>
											<input type="hidden" class="form-control"
												name="options[2].optionOrder" id="optionOrder3"
												placeholder="Enter Option 2" value="3"> <input
												type="hidden" class="form-control"
												name="options[2].englishImageId" id="option3_image_id">

										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											4 : </label>
										<div class="col-sm-1"></div>
										<div class="col-sm-6">
											<textarea class="ckeditor1" required="required" rows="5"
												cols="80" name="options[3].answerOptionEnglish" id="option4"
												placeholder="Enter Option 4"> </textarea>
											<input type="hidden" class="form-control"
												name="options[3].optionOrder" id="optionOrder4"
												placeholder="Enter Option 2" value="4"> <input
												type="hidden" class="form-control"
												name="options[3].englishImageId" id="option4_image_id">

										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="panel form-horizontal bordered-row">
							<div class="panel-body">
								<h3 class="title-hero">
									<b>QUESTION (HINDI)</b>
								</h3>
								<div class="form-group">
									<label class="control-label col-sm-2" for="fullname">Question
										: </label>
									<div class="col-sm-8">
										<!-- <textarea class="ckeditor1"  required="required" cols="80" id="editor1" name="editor1" rows="10"> -->
										<textarea class="ckeditor1" required="required" rows="5"
											cols="80" name="questionHindi" id="question_hindi">
											</textarea>
										<input type="hidden" class="form-control" name="hindiImageId"
											id="question_hindi_image_id">

									</div>
								</div>
								<div class="bordered-row">
									<div class="form-group ">
										<label class="control-label col-sm-2" for="fullname">Option
											1 : </label>
										<div class="col-sm-1"></div>
										<div class="col-sm-6">
											<textarea class="ckeditor1" required="required" rows="5"
												cols="80" name="options[0].answerOptionHindi" id="option_hindi_1"
												placeholder="Enter Option 1"> </textarea>
											<input type="hidden" class="form-control"
												name="options[0].hindiImageId" id="option_hindi_1_image_id">

										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											2 : </label>
										<div class="col-sm-1"></div>
										<div class="col-sm-6">
											<textarea class="ckeditor1" required="required" rows="5"
												cols="80" name="options[1].answerOptionHindi" id="option_hindi_2"
												placeholder="Enter Option 2"> 
												
									</textarea>
											<input type="hidden" class="form-control"
												name="options[1].hindiImageId" id="option_hindi_2_image_id">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											3 : </label>
										<div class="col-sm-1"></div>
										<div class="col-sm-6">
											<textarea class="ckeditor1" required="required" rows="5"
												cols="80" name="options[2].answerOptionHindi" id="option_hindi_3"
												placeholder="Enter Option 3">
									</textarea>
											<input type="hidden" class="form-control"
												name="options[2].hindiImageId" id="option_hindi_3_image_id">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="fullname">Option
											4 : </label>
										<div class="col-sm-1"></div>
										<div class="col-sm-6">
											<textarea class="" required="required" rows="5" cols="80"
												name="options[3].answerOptionHindi" id="option_hindi_4"
												placeholder="Enter Option 4"> </textarea>
											<input type="hidden" class="form-control"
												name="options[3].hindiImageId" id="option_hindi_4_image_id">
										</div>
									</div>

								</div>
							</div>
						</div>
						
						<input type="hidden" class="form-control" name="teluguImageId"
											id="question_telugu_image_id">
											
						<input type="hidden" class="form-control"
												name="options[0].teluguImageId" id="option_telugu_1_image_id">		
												
						<input type="hidden" class="form-control"
												name="options[1].teluguImageId" id="option_telugu_2_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[2].teluguImageId" id="option_telugu_3_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[3].teluguImageId" id="option_telugu_4_image_id">		
												
												
												
						<input type="hidden" class="form-control" name="marathiImageId"
											id="question_marathi_image_id">
											
						<input type="hidden" class="form-control"
												name="options[0].marathiImageId" id="option_marathi_1_image_id">		
												
						<input type="hidden" class="form-control"
												name="options[1].marathiImageId" id="option_marathi_2_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[2].marathiImageId" id="option_marathi_3_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[3].marathiImageId" id="option_marathi_4_image_id">	
												
												
						<input type="hidden" class="form-control" name="tamilImageId"
											id="question_tamil_image_id">
											
						<input type="hidden" class="form-control"
												name="options[0].tamilImageId" id="option_tamil_1_image_id">		
												
						<input type="hidden" class="form-control"
												name="options[1].tamilImageId" id="option_tamil_2_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[2].tamilImageId" id="option_tamil_3_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[3].tamilImageId" id="option_tamil_4_image_id">																	
															
                          
                        <input type="hidden" class="form-control" name="kanadaImageId"
											id="question_kanada_image_id">
											
						<input type="hidden" class="form-control"
												name="options[0].kanadaImageId" id="option_kanada_1_image_id">		
												
						<input type="hidden" class="form-control"
												name="options[1].kanadaImageId" id="option_kanada_2_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[2].kanadaImageId" id="option_kanada_3_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[3].kanadaImageId" id="option_kanada_4_image_id">
												
												
						
						<input type="hidden" class="form-control" name="urduImageId"
											id="question_urdu_image_id">
											
						<input type="hidden" class="form-control"
												name="options[0].urduImageId" id="option_urdu_1_image_id">		
												
						<input type="hidden" class="form-control"
												name="options[1].urduImageId" id="option_urdu_2_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[2].urduImageId" id="option_urdu_3_image_id">					
						
						<input type="hidden" class="form-control"
												name="options[3].urduImageId" id="option_urdu_4_image_id">						
												
												
												
                          

						<div class="form-group">
							<div class="col-sm-4"></div>
							<div class="col-sm-4">
								<button id="submit" type="submit"
									class="btn btn-primary btn-hover">Add Question</button>
							</div>
						</div>
					</form>



				</div>

				<div class="clearfix"></div>

			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="assets/widgets/ckeditor/ckeditor.js"></script>


	<script>
		/*  CKEDITOR.replace('option_hindi_4', {
		    extraPlugins: 'mathjax',
		    mathJaxLib: 'assets/widgets/ckeditor/plugins/mathjax/dialogs/mathjax.js',
		    height: 320
		  });

		  if (CKEDITOR.env.ie && CKEDITOR.env.version == 8) {
		    document.getElementById('ie8-warning').className = 'tip alert';
		  }*/
	</script>

	<script type="text/javascript">
	var map = new Map([ [ 1, "question_english" ],
		[ 2, "option1" ], [ 3, "option2" ],
		[ 4, "option3" ], [ 5, "option4" ],
		[ 6, "question_hindi" ],
		[ 7, "option_hindi_1" ],
		[ 8, "option_hindi_2" ],
		[ 9, "option_hindi_3" ],
		[ 10, "option_hindi_4" ] ]);
$(document)
		.ready(function() {
					
							
							$('#submit')
									.click(
											function() {
												debugger;
												var count=0;
												var get_keys = map.keys();
				                          for(var ele of get_keys){
												var ck_content = $(
														'#cke_'+ele+'_contents')
														.children().eq(1);
												var ck_content_child = ck_content
														.eq(0);
												var body = ck_content_child
														.get(0).contentDocument.body;
												//checkImgTagsInEditor(body);
												if(!checkImgTagsInEditor(body,map.get(ele)+'_image_id')){
													return false;
												}
										var ck_value=	body.innerHTML;
										ck_value=ck_value.replaceAll('&nbsp;','').trim();
										console.log(ck_value.trim());
										if(ck_value=="<br>" ||ck_value==''){
												shownErrorMsg(map.get(ele));
												count++;
												}else{
													hideErrorMsg(map.get(ele));
												}
												}
												if(count==0){
													//return true;
												}
												else{
												return false;
												}
				                            
											});
					                            	});

		function scroll() {
			var objDiv = document.getElementById("msg");
			objDiv.scrollTop = objDiv.scrollHeight;

		}
	    function shownErrorMsg(id){
			debugger;
			var element =  document.getElementById(id+'-error');
		
			var ele_msg_text='<div class="col-sm-6" id="'+id+'-error"><p class="error opacity-100">This field is required</p></div>';
		
			if (typeof(element) != 'undefined' && element != null)
			{
				}else{
					$('#'+id).parent().append(ele_msg_text);
				}
		}
		function hideErrorMsg(id){
			
		var element =  document.getElementById(id+'-error');
			if(typeof(element) != "undefined" && element != null){
				element.remove();
			}
			}
		
		function checkImgTagsInEditor(editor_body,image_id){
		var list=	editor_body.getElementsByTagName("img");
		if(list.length<=1){
			if(list.length==1){
				var src=list[0].src;
				var url = new URL(src);
				$('#'+image_id).val(url.searchParams.get('imageId'));
				}
			return true;
		}else{
			alert("only one image is allowed per question/option");
			return false;
		}
		
			
		}
		
	
            var path = "ambu.png";
            var fullPath = null;
            CKEDITOR.on('instanceReady', function(ev) {
                // Prevent drag-and-drop.
                console.log("--1-->" + fullPath); 
                
                ev.editor.on('fileUploadRequest', function(evt) {
                	var image_name="";
                	var id="";
                	 var get_keys = map.keys();
         			for(var ele of get_keys){
         			 id=	map.get(ele);
                	if(evt.editor.name==id){
                		image_name=evt.editor.name+"_image_id";
                		break;
                		}
                	}
                	 var fileLoader = evt.data.fileLoader,
     		        formData = new FormData(),
     		     xhr = fileLoader.xhr,
     		    response = xhr.responseText.split( '|' );

                 xhr.overrideMimeType("application/json");
     		     xhr.open( 'POST', fileLoader.uploadUrl, true );
     		     formData.append( 'upload', fileLoader.file, fileLoader.fileName );
     		  var tools=  window.parent.CKEDITOR.tools;

     		    fileLoader.xhr.send( formData );
     		  var data=  $('#'+image_name).val();
     		    
     		 //  xhr
     		    // Prevented the default behavior.
     		    evt.stop();
                   /*  alert(evt.editor.name);
                    var reader = new FileReader();
                    var file = evt.data.fileLoader.file;
                   
                    console.log("--file-->" + file);
                    // getBase64(file);
                    var filebase = reader.readAsDataURL(file); */
 // path=evt.data.fileLoader.file.name;
               /*      setTimeout(function() {
                        if (path !== "ambu.png") {
                        	debugger;
                            //var fullPath='http://localhost:1111/AssignController/uploadImage?imageValue='+path;
                            var fullPath = 'http://localhost:1111/AssignController/getImage2?filePath=' + path;
                            console.log("--fullPath-->" + fullPath);
                            var fileLoader = evt.data.fileLoader,
                                    formData = new FormData(),
                                    xhr = fileLoader.xhr;
                            debugger;
                            formData.append('upload', fileLoader.file, fileLoader.fileName);
                            // Prevented the default behavior.
                            evt.stop();
                            jQuery.ajax({
                                url: 'saveQuestionImage',
                                data: formData,
                                cache: false,
                                contentType: false,
                                processData: false,
                                method: 'POST',
                                type: 'POST', // For jQuery < 1.9
                                success: function(data) {
                                	debugger;
                                    alert(JSON.stringify(data));
                                    return JSON.stringify(data);
                                }
                            });
                        }
                    }, 1000); */


                }, null, null, 4);
            });

            var get_keys = map.keys();
			for(var ele of get_keys){
			var id=	map.get(ele);
            CKEDITOR.config.extraPlugins = "base64image";
            CKEDITOR.replace(id, {
                extraPlugins: 'uploadimage,image',
                height: 300,
                uploadUrl: '/saveQuestionImage',
                // Configure your file manager integration. This example uses CKFinder 3 for PHP.
              //  filebrowserBrowseUrl: '/apps/ckfinder/3.4.5/ckfinder.html',
                //filebrowserImageBrowseUrl: '/apps/ckfinder/3.4.5/ckfinder.html?type=Images',
                filebrowserUploadUrl: 'base64',
                filebrowserImageUploadUrl: 'saveQuestionImage',
                // The following options are not necessary and are used here for presentation purposes only.
                // They configure the Styles drop-down list and widgets to use classes.

                stylesSet: [{
                        name: 'Narrow image',
                        type: 'widget',
                        widget: 'image',
                        attributes: {
                            'class': 'image-narrow'
                        }
                    },
                    {
                        name: 'Wide image',
                        type: 'widget',
                        widget: 'image',
                        attributes: {
                            'class': 'image-wide'
                        }
                    }
                ],
                // Load the default contents.css file plus customizations for this sample.
                contentsCss: [
                    'http://cdn.ckeditor.com/4.15.1/full-all/contents.css',
                    'https://ckeditor.com/docs/ckeditor4/4.15.1/examples/assets/css/widgetstyles.css'
                ],
                // Configure the Enhanced Image plugin to use classes instead of styles and to disable the
                // resizer (because image size is controlled by widget styles or the image takes maximum
                // 100% of the editor width).
                image2_alignClasses: ['image-align-left', 'image-align-center', 'image-align-right'],
                image2_disableResizer: true
            });

			}


            /* function replace(PATH) {
                console.log("---->" + PATH);
                CKEDITOR.replace('editor1', {
                    extraPlugins: 'uploadimage,image',
                    height: 300,
                    // Upload images to a CKFinder connector (note that the response type is set to JSON).
                    uploadUrl: PATH,
                    // Configure your file manager integration. This example uses CKFinder 3 for PHP.
                    filebrowserBrowseUrl: '/apps/ckfinder/3.4.5/ckfinder.html',
                    filebrowserImageBrowseUrl: '/apps/ckfinder/3.4.5/ckfinder.html?type=Images',
                    filebrowserUploadUrl: '/apps/ckfinder/3.4.5/core/connector/php/connector.php?command=QuickUpload&type=Files',
                    //  filebrowserImageUploadUrl: 'http://localhost:1111/AssignController/uploadImage?imageValue='+path,

                    // The following options are not necessary and are used here for presentation purposes only.
                    // They configure the Styles drop-down list and widgets to use classes.

                    stylesSet: [{
                            name: 'Narrow image',
                            type: 'widget',
                            widget: 'image',
                            attributes: {
                                'class': 'image-narrow'
                            }
                        },
                        {
                            name: 'Wide image',
                            type: 'widget',
                            widget: 'image',
                            attributes: {
                                'class': 'image-wide'
                            }
                        }
                    ],
                    // Load the default contents.css file plus customizations for this sample.
                    contentsCss: [
                        'http://cdn.ckeditor.com/4.15.1/full-all/contents.css',
                        'https://ckeditor.com/docs/ckeditor4/4.15.1/examples/assets/css/widgetstyles.css'
                    ],
                    // Configure the Enhanced Image plugin to use classes instead of styles and to disable the
                    // resizer (because image size is controlled by widget styles or the image takes maximum
                    // 100% of the editor width).
                    image2_alignClasses: ['image-align-left', 'image-align-center', 'image-align-right'],
                    image2_disableResizer: true
                });
            } */


        </script>
</body>
</html>




