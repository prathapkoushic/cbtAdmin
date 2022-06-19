<%@ page language="java"%>
<%@ page errorPage="error.jsp"%>
<%@ page pageEncoding="utf-8"%>
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
<title>Calendar</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Favicons -->

<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/images/icons/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/images/icons/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/images/icons/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/images/icons/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="assets/images/icons/favicon.png">



<link rel="stylesheet" type="text/css"
	href="assets/bootstrap/css/bootstrap.css">


<!-- HELPERS -->

<link rel="stylesheet" type="text/css" href="assets/helpers/animate.css">
<link rel="stylesheet" type="text/css"
	href="assets/helpers/backgrounds.css">
<link rel="stylesheet" type="text/css"
	href="assets/helpers/boilerplate.css">
<link rel="stylesheet" type="text/css"
	href="assets/helpers/border-radius.css">
<link rel="stylesheet" type="text/css" href="assets/helpers/grid.css">
<link rel="stylesheet" type="text/css"
	href="assets/helpers/page-transitions.css">
<link rel="stylesheet" type="text/css" href="assets/helpers/spacing.css">
<link rel="stylesheet" type="text/css"
	href="assets/helpers/typography.css">
<link rel="stylesheet" type="text/css" href="assets/helpers/utils.css">
<link rel="stylesheet" type="text/css" href="assets/helpers/colors.css">

<!-- ELEMENTS -->

<link rel="stylesheet" type="text/css" href="assets/elements/badges.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/buttons.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/content-box.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/dashboard-box.css">
<link rel="stylesheet" type="text/css" href="assets/elements/forms.css">
<link rel="stylesheet" type="text/css" href="assets/elements/images.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/info-box.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/invoice.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/loading-indicators.css">
<link rel="stylesheet" type="text/css" href="assets/elements/menus.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/panel-box.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/response-messages.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/responsive-tables.css">
<link rel="stylesheet" type="text/css" href="assets/elements/ribbon.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/social-box.css">
<link rel="stylesheet" type="text/css" href="assets/elements/tables.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/tile-box.css">
<link rel="stylesheet" type="text/css"
	href="assets/elements/timeline.css">



<!-- ICONS -->

<link rel="stylesheet" type="text/css"
	href="assets/icons/fontawesome/fontawesome.css">
<link rel="stylesheet" type="text/css"
	href="assets/icons/linecons/linecons.css">
<link rel="stylesheet" type="text/css"
	href="assets/icons/spinnericon/spinnericon.css">
<link rel="stylesheet" type="text/css"
	href="assets/icons/elusive/elusive.css">


<!-- WIDGETS -->

<link rel="stylesheet" type="text/css"
	href="assets/widgets/accordion-ui/accordion.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/calendar/calendar.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/carousel/carousel.css">

<link rel="stylesheet" type="text/css"
	href="assets/widgets/charts/justgage/justgage.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/charts/morris/morris.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/charts/piegage/piegage.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/charts/xcharts/xcharts.css">

<link rel="stylesheet" type="text/css"
	href="assets/widgets/chosen/chosen.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/colorpicker/colorpicker.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/datatable/datatable.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/datepicker/datepicker.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/datepicker-ui/datepicker.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/dialog/dialog.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/dropdown/dropdown.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/dropzone/dropzone.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/file-input/fileinput.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/input-switch/inputswitch.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/input-switch/inputswitch-alt.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/ionrangeslider/ionrangeslider.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/jcrop/jcrop.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/jgrowl-notifications/jgrowl.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/loading-bar/loadingbar.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/maps/vector-maps/vectormaps.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/markdown/markdown.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/modal/modal.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/multi-select/multiselect.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/multi-upload/fileupload.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/nestable/nestable.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/noty-notifications/noty.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/popover/popover.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/pretty-photo/prettyphoto.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/progressbar/progressbar.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/range-slider/rangeslider.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/slidebars/slidebars.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/slider-ui/slider.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/summernote-wysiwyg/summernote-wysiwyg.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/tabs-ui/tabs.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/theme-switcher/themeswitcher.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/timepicker/timepicker.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/tocify/tocify.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/tooltip/tooltip.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/touchspin/touchspin.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/uniform/uniform.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/wizard/wizard.css">
<link rel="stylesheet" type="text/css"
	href="assets/widgets/xeditable/xeditable.css">

<!-- SNIPPETS -->

<link rel="stylesheet" type="text/css" href="assets/snippets/chat.css">
<link rel="stylesheet" type="text/css"
	href="assets/snippets/files-box.css">
<link rel="stylesheet" type="text/css"
	href="assets/snippets/login-box.css">
<link rel="stylesheet" type="text/css"
	href="assets/snippets/notification-box.css">
<link rel="stylesheet" type="text/css"
	href="assets/snippets/progress-box.css">
<link rel="stylesheet" type="text/css" href="assets/snippets/todo.css">
<link rel="stylesheet" type="text/css"
	href="assets/snippets/user-profile.css">
<link rel="stylesheet" type="text/css"
	href="assets/snippets/mobile-navigation.css">

<!-- APPLICATIONS -->

<link rel="stylesheet" type="text/css"
	href="assets/applications/mailbox.css">

<!-- Admin theme -->

<link rel="stylesheet" type="text/css"
	href="assets/themes/admin/layout.css">
<link rel="stylesheet" type="text/css"
	href="assets/themes/admin/color-schemes/default.css">

<!-- Components theme -->

<link rel="stylesheet" type="text/css"
	href="assets/themes/components/default.css">
<link rel="stylesheet" type="text/css"
	href="assets/themes/components/border-radius.css">

<!-- Admin responsive -->

<link rel="stylesheet" type="text/css"
	href="assets/helpers/responsive-elements.css">
<link rel="stylesheet" type="text/css"
	href="assets/helpers/admin-responsive.css">


<link rel="stylesheet" type="text/css"
	href="assets/icons/fontawesome/fontawesome.css">
<link rel="stylesheet" type="text/css"
	href="assets/icons/linecons/linecons.css">
<link rel="stylesheet" type="text/css"
	href="assets/icons/spinnericon/spinnericon.css">

<!-- JS Core -->

<script type="text/javascript" src="assets/js-core/jquery-core.js"></script>
<script type="text/javascript" src="assets/js-core/jquery-ui-core.js"></script>
<script type="text/javascript" src="assets/js-core/jquery-ui-widget.js"></script>
<script type="text/javascript" src="assets/js-core/jquery-ui-mouse.js"></script>
<script type="text/javascript"
	src="assets/js-core/jquery-ui-position.js"></script>
<!--<script type="text/javascript" src="assets/js-core/transition.js"></script>-->
<script type="text/javascript" src="assets/js-core/modernizr.js"></script>
<script type="text/javascript" src="assets/js-core/jquery-cookie.js"></script>





<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function() {
			$('#loading').fadeOut(400, "linear");
		}, 300);
	});
</script>

<!--parsley -->
<script type="text/javascript" src="assets/widgets/parsley/parsley.js"></script>

</head>


<body>
	<div id="sb-site">
		<div class="sb-slidebar bg-black sb-left sb-style-overlay">
			<div class="scrollable-content scrollable-slim-sidebar">
				<div class="pad10A">
					<div class="divider-header">Online</div>


				</div>
			</div>
		</div>

		<div class="sb-slidebar bg-black sb-right sb-style-overlay">
			<div class="scrollable-content scrollable-slim-sidebar">
				<div class="pad15A">
					<a href="#" title="" data-toggle="collapse"
						data-target="#sidebar-toggle-1" class="popover-title"> Cloud
						status <span class="caret"></span>
					</a>
					<div id="sidebar-toggle-1" class="collapse in">
						<div class="pad15A container">

							<div class="divider mrg15T mrg15B"></div>
							<div class="text-center">
								<a href="#"
									class="btn center-div btn-info mrg5T btn-sm text-transform-upr updateEasyPieChart">
									<i class="glyph-icon icon-refresh"></i> Update charts
								</a>
							</div>
						</div>
					</div>

					<div class="clear"></div>

					<a href="#" title="" data-toggle="collapse"
						data-target="#sidebar-toggle-6" class="popover-title"> Latest
						transfers <span class="caret"></span>
					</a>
					<div id="sidebar-toggle-6" class="collapse in"></div>

					<div class="clear"></div>

					<a href="#" title="" data-toggle="collapse"
						data-target="#sidebar-toggle-3" class="popover-title"> Tasks
						for today <span class="caret"></span>
					</a>
					<div id="sidebar-toggle-3" class="collapse in"></div>

					<div class="clear"></div>

					<a href="#" title="" data-toggle="collapse"
						data-target="#sidebar-toggle-4" class="popover-title"> Pending
						notifications <span class="bs-label bg-orange tooltip-button"
						title="Label example">New</span> <span class="caret"></span>
					</a>
					<div id="sidebar-toggle-4" class="collapse in"></div>
				</div>
			</div>
		</div>
		<div id="loading">
			<div class="spinner">
				<div class="bounce1"></div>
				<div class="bounce2"></div>
				<div class="bounce3"></div>
			</div>
		</div>

		<div id="page-wrapper">
			<div id="page-header" class="bg-gradient-9">
				<div id="mobile-navigation">
					<button id="nav-toggle" class="collapsed" data-toggle="collapse"
						data-target="#page-sidebar">
						<span></span>
					</button>
					<a href="#" class="" title="ADMIN"> RRC</a>
				</div>
				<div id="header-logo" class="logo-bg">
					<div class="mrg25A">
						<a href="#" class="font-white .text-justify" title=" ADMIN">
							<span> ZRTI </span>
						</a> <a id="close-sidebar" href="#" title="Close sidebar"> <i
							class="glyph-icon icon-angle-left"></i>
						</a>
					</div>
					<!-- <a href="index.html" class="logo-content-big" title="MonarchUI">
						Monarch <i>UI</i> <span>The perfect solution for user
							interfaces</span>
					</a> <a href="index.html" class="logo-content-small" title="MonarchUI">
						Monarch <i>UI</i> <span>The perfect solution for user
							interfaces</span>
					</a> <a id="close-sidebar" href="#" title="Close sidebar"> <i
						class="glyph-icon icon-angle-left"></i>
					</a> -->
				</div>
				<!-- 	<div id="header-nav-left">
					<div class="user-account-btn dropdown">
						<a href="#" title="My Account" class="user-profile clearfix"
							data-toggle="dropdown"> <span>SCR ADMIN</span> <i
							class="glyph-icon icon-angle-down"></i>
						</a>
					</div>
				</div> -->
				<!-- #header-nav-left -->
				<div id="header-nav-left">
					<div class="mrg20A">
						<a href="#" class="font-white .text-justify "
							title="ADMIN"> <span
							style="font-size: 25px"><!-- Southern Railway  --> ZONAL RAILWAY TRAINING INSTITUTE ,MOULALI 
								<!-- हरियाणा कर्मचारी चयन आयोग -->
						</span>
						</a>
					</div>

				</div>
				<div id="header-nav-right">
					<a class="header-btn" id="logout-btn" href="logout" title="Logout">
						<i class="glyph-icon icon-power-off"></i>
					</a>
				</div>
				<!-- #header-nav-right -->
				<!-- side bar  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ -->
			</div>
			<div id="page-sidebar">
				<div class="scroll-sidebar">


					<ul id="sidebar-menu">
						<!-- <li class="header"><span>Overview</span></li> -->
						<!-- 	<li><a href="locationStats" title="Admin Dashboard"> <i
								class="glyph-icon icon-linecons-tv"></i> <span> dashboard</span>
						</a></li> -->
						<!-- 	<li class="divider"></li> -->
                 <li><a href="user" title="Add user"> <i
								class="glyph-icon icon-user"></i> <span>Add User
									 </span>
						</a></li>


						<li><a href="#" title="Add"> <i
								class="glyph-icon    icon-graduation-cap"></i> <span>Post
									Category</span>
						</a>
							<div class="sidebar-submenu">

								<ul>
									<li><a href="addPostCategory" title="Add"><span>
												Add </span> </a></li>
									<li><a href="postCategoryList" title="List"> <span>
												List/Edit </span>
									</a></li>
								</ul>
							</div></li>
						<li><a href="#" title="Add"> <i
								class="glyph-icon    icon-graduation-cap"></i> <span>Post</span>
						</a>
							<div class="sidebar-submenu">

								<ul>
									<li><a href="posturl" title="Add"><span> Add </span> </a></li>
									<li><a href="listOfPosts" title="Add"> <span>
												List/Edit/Delete </span>
									</a></li>
								</ul>
							</div></li>

						<li><a href="#" title="Add"> <i
								class="glyph-icon icon-linecons-note"></i> <span>Exam</span>
						</a>
							<div class="sidebar-submenu">

								<ul>
									<li><a href="createexamurl" title="Add"><span>
												Add </span> </a></li>
									<li><a href="examlist" title="Add"> <span>
												List/Edit </span>
									</a></li>
								</ul>
							</div></li>
						<li><a href="#" title="Add"> <i
								class="glyph-icon icon-elusive-location"></i> <span>Exam
									Loc Session</span>
						</a>
							<div class="sidebar-submenu">

								<ul>
									<li><a href="createlocation" title="Add"><span>
												Add </span> </a></li>


									<li><a href="locationSessionList" title="List/Edit"> <span>
												List/Edit</span>
									</a></li>
									<li><a href="uploadExamLocation" title="upload"><span>
												Upload </span> </a></li>

								</ul>
							</div></li>

						<li><a href="#" title="Add"> <i
								class="glyph-icon icon-book"></i> <span>Questions</span>
						</a>
							<div class="sidebar-submenu">

								<ul>
								
				<li><a href="redirectToUploadQuestions" title="List/Edit"><span>Upload</span></a></li>
		        
		        <li><a href="#" title="Add"><span>Descriptive Questions</span></a>
				<div class="sidebar-submenu">
				<ul><li><a href="descriptive" title="uploadDescriptive"><span>Upload Descriptive Questions
										</span></a></li>
										<li><a href="examQuestions" title="examQuestions"><span>Set Final Number of Questions 
										</span></a></li></ul>
				</div>
				</li>
				
				<li><a href="#" title="Add"><span>LIST</span></a>
				<div class="sidebar-submenu">
				<ul><li><a href="list" title="List/Edit"><span>Objective Questions List
										</span></a></li>
										<li><a href="list2" title="List/Edit"><span>Descriptive Questions List
										</span></a></li></ul>
				</div>
				</li>
										<li><a href="#" title="delete"><span>DELETE</span></a>
				<div class="sidebar-submenu">
				<ul><li><a href="delQ" title="delete"><span>Delete Objective Questions List
										</span></a></li>
										<li><a href="delDesQ" title="delDes"><span>Delete Descriptive Questions List
										</span></a></li></ul>
				</div>
				</li>
										<li><a href="createQuestion" title="Add"><span>
												Add </span></a></li>

								</ul>

							</div> <!-- .sidebar-submenu --></li>
						<li><a href="#" title="Reports"> <i
								class="glyph-icon icon-user"></i> <span> Candidate</span>
						</a>
							<div class="sidebar-submenu">

								<ul>

									<li><a href="createcandidate">Add </a></li>

									<li><a href="CandidateList"> List/Edit/Delete </a></li>
									<!-- <li><a href="CandidateListBasedOnCategory"> List Based On Category </a></li> -->
									<li><a href="uploadurl">Upload By Loc</a></li>
									<li><a href="candidatesByExam">Upload By Exam</a></li>


								</ul>

							</div></li>
						<li><a href="session_user_list" title=""> <i
								class="glyph-icon icon-linecons-diamond"></i> <span>
									Sessions </span>
						</a></li>
						<li><a href="#" title="OTP"> <i
								class="glyph-icon icon-phone"></i> <span> OTP</span>
						</a>
							<div class="sidebar-submenu">

								<ul>
									<li><a href="redirectGenerateOTP"> Generate</a></li>
									<li><a href="sendOtps"> Send</a></li>
									<li><a href="OTPlist"> List</a></li>

								</ul>

							</div></li>

						<li><a href="stats" title="stats"> <i
								class="glyph-icon icon-tachometer"> </i><span>Dashboard</span>
						</a></li>
						<li><a href="redirectResultProcessing" title="Add"> <i
								class="glyph-icon icon-linecons-diamond"></i> <span>
									Process Result </span>
						</a></li>
						<li><a href="answerKey" title="Add"> <i
								class="glyph-icon icon-linecons-key"></i> <span>Answer
									Key </span>
						</a></li>


						<!-- 
						<li><a href="assignSetNo" title="Add"> <i
								class="glyph-icon icon-linecons-diamond"></i> <span>Assign
									Set Number </span>
						</a></li> -->
						<li><a href="#" title="Reports"> <i
								class="glyph-icon icon-file"></i> <span> Reports</span>
						</a>
							<div class="sidebar-submenu">

								<ul>

									<li><a href="redirctQuestionsAndAnswer">Candidate
											Q&amp;A Report</a></li>

									<li><a href="redirctCandidateResultsReport">Candidate
											Results Report </a></li>
									<li><a href="rediectCandidateResult"> Candidate Result</a></li>

									<li><a href="redirctStatisticReports"> Q&amp;A
											Statistics Report </a></li>
								</ul>

							</div></li>




					</ul>
				</div>
			</div>


			<div id="page-content-wrapper">
				<div id="page-content">

					<div class="container">
						<div id="theme-options" class="admin-options">
							<a href="javascript:void(0);"
								class="btn btn-primary theme-switcher tooltip-button"
								data-placement="left" title="Color schemes and layout options">
								<i class="glyph-icon icon-linecons-cog icon-spin"></i>
							</a>
							<div id="theme-switcher-wrapper">
								<div class="scroll-switcher">
									<h5 class="header">Layout options</h5>
									<ul class="reset-ul">
										<li><label for="boxed-layout">Boxed layout</label> <input
											type="checkbox" data-toggletarget="boxed-layout"
											id="boxed-layout" class="input-switch-alt"></li>
										<li><label for="fixed-header">Fixed header</label> <input
											type="checkbox" data-toggletarget="fixed-header"
											id="fixed-header" class="input-switch-alt"></li>
										<li><label for="fixed-sidebar">Fixed sidebar</label> <input
											type="checkbox" data-toggletarget="fixed-sidebar"
											id="fixed-sidebar" class="input-switch-alt"></li>
										<li><label for="closed-sidebar">Closed sidebar</label> <input
											type="checkbox" data-toggletarget="closed-sidebar"
											id="closed-sidebar" class="input-switch-alt"></li>
									</ul>
									<div class="boxed-bg-wrapper hide">
										<h5 class="header">
											Boxed Page Background <a class="set-background-style"
												data-header-bg="" title="Remove all styles"
												href="javascript:void(0);">Clear</a>
										</h5>
										<div class="theme-color-wrapper clearfix">
											<h5>Patterns</h5>
											<a class="tooltip-button set-background-style pattern-bg-3"
												data-header-bg="pattern-bg-3" title="Pattern 3"
												href="javascript:void(0);">Pattern 3</a> <a
												class="tooltip-button set-background-style pattern-bg-4"
												data-header-bg="pattern-bg-4" title="Pattern 4"
												href="javascript:void(0);">Pattern 4</a> <a
												class="tooltip-button set-background-style pattern-bg-5"
												data-header-bg="pattern-bg-5" title="Pattern 5"
												href="javascript:void(0);">Pattern 5</a> <a
												class="tooltip-button set-background-style pattern-bg-6"
												data-header-bg="pattern-bg-6" title="Pattern 6"
												href="javascript:void(0);">Pattern 6</a> <a
												class="tooltip-button set-background-style pattern-bg-7"
												data-header-bg="pattern-bg-7" title="Pattern 7"
												href="javascript:void(0);">Pattern 7</a> <a
												class="tooltip-button set-background-style pattern-bg-8"
												data-header-bg="pattern-bg-8" title="Pattern 8"
												href="javascript:void(0);">Pattern 8</a> <a
												class="tooltip-button set-background-style pattern-bg-9"
												data-header-bg="pattern-bg-9" title="Pattern 9"
												href="javascript:void(0);">Pattern 9</a> <a
												class="tooltip-button set-background-style pattern-bg-10"
												data-header-bg="pattern-bg-10" title="Pattern 10"
												href="javascript:void(0);">Pattern 10</a>

											<div class="clear"></div>

											<h5 class="mrg15T">Solids &amp;Images</h5>
											<a class="tooltip-button set-background-style bg-black"
												data-header-bg="bg-black" title="Black"
												href="javascript:void(0);">Black</a> <a
												class="tooltip-button set-background-style bg-gray mrg10R"
												data-header-bg="bg-gray" title="Gray"
												href="javascript:void(0);">Gray</a> <a
												class="tooltip-button set-background-style full-bg-1"
												data-header-bg="full-bg-1 fixed-bg" title="Image 1"
												href="javascript:void(0);">Image 1</a> <a
												class="tooltip-button set-background-style full-bg-2"
												data-header-bg="full-bg-2 fixed-bg" title="Image 2"
												href="javascript:void(0);">Image 2</a> <a
												class="tooltip-button set-background-style full-bg-3"
												data-header-bg="full-bg-3 fixed-bg" title="Image 3"
												href="javascript:void(0);">Image 3</a> <a
												class="tooltip-button set-background-style full-bg-4"
												data-header-bg="full-bg-4 fixed-bg" title="Image 4"
												href="javascript:void(0);">Image 4</a> <a
												class="tooltip-button set-background-style full-bg-5"
												data-header-bg="full-bg-5 fixed-bg" title="Image 5"
												href="javascript:void(0);">Image 5</a> <a
												class="tooltip-button set-background-style full-bg-6"
												data-header-bg="full-bg-6 fixed-bg" title="Image 6"
												href="javascript:void(0);">Image 6</a>

										</div>
									</div>
									<h5 class="header">
										Header Style <a class="set-adminheader-style"
											data-header-bg="bg-gradient-9" title="Remove all styles"
											href="javascript:void(0);">Clear</a>
									</h5>
									<div class="theme-color-wrapper clearfix">
										<h5>Solids</h5>
										<a class="tooltip-button set-adminheader-style bg-primary"
											data-header-bg="bg-primary font-inverse" title="Primary"
											href="javascript:void(0);">Primary</a> <a
											class="tooltip-button set-adminheader-style bg-green"
											data-header-bg="bg-green font-inverse" title="Green"
											href="javascript:void(0);">Green</a> <a
											class="tooltip-button set-adminheader-style bg-red"
											data-header-bg="bg-red font-inverse" title="Red"
											href="javascript:void(0);">Red</a> <a
											class="tooltip-button set-adminheader-style bg-blue"
											data-header-bg="bg-blue font-inverse" title="Blue"
											href="javascript:void(0);">Blue</a> <a
											class="tooltip-button set-adminheader-style bg-warning"
											data-header-bg="bg-warning font-inverse" title="Warning"
											href="javascript:void(0);">Warning</a> <a
											class="tooltip-button set-adminheader-style bg-purple"
											data-header-bg="bg-purple font-inverse" title="Purple"
											href="javascript:void(0);">Purple</a> <a
											class="tooltip-button set-adminheader-style bg-black"
											data-header-bg="bg-black font-inverse" title="Black"
											href="javascript:void(0);">Black</a>

										<div class="clear"></div>

										<h5 class="mrg15T">Gradients</h5>
										<a class="tooltip-button set-adminheader-style bg-gradient-1"
											data-header-bg="bg-gradient-1 font-inverse"
											title="Gradient 1" href="javascript:void(0);">Gradient 1</a>
										<a class="tooltip-button set-adminheader-style bg-gradient-2"
											data-header-bg="bg-gradient-2 font-inverse"
											title="Gradient 2" href="javascript:void(0);">Gradient 2</a>
										<a class="tooltip-button set-adminheader-style bg-gradient-3"
											data-header-bg="bg-gradient-3 font-inverse"
											title="Gradient 3" href="javascript:void(0);">Gradient 3</a>
										<a class="tooltip-button set-adminheader-style bg-gradient-4"
											data-header-bg="bg-gradient-4 font-inverse"
											title="Gradient 4" href="javascript:void(0);">Gradient 4</a>
										<a class="tooltip-button set-adminheader-style bg-gradient-5"
											data-header-bg="bg-gradient-5 font-inverse"
											title="Gradient 5" href="javascript:void(0);">Gradient 5</a>
										<a class="tooltip-button set-adminheader-style bg-gradient-6"
											data-header-bg="bg-gradient-6 font-inverse"
											title="Gradient 6" href="javascript:void(0);">Gradient 6</a>
										<a class="tooltip-button set-adminheader-style bg-gradient-7"
											data-header-bg="bg-gradient-7 font-inverse"
											title="Gradient 7" href="javascript:void(0);">Gradient 7</a>
										<a class="tooltip-button set-adminheader-style bg-gradient-8"
											data-header-bg="bg-gradient-8 font-inverse"
											title="Gradient 8" href="javascript:void(0);">Gradient 8</a>
									</div>
									<h5 class="header">
										Sidebar Style <a class="set-sidebar-style" data-header-bg=""
											title="Remove all styles" href="javascript:void(0);">Clear</a>
									</h5>
									<div class="theme-color-wrapper clearfix">
										<h5>Solids</h5>
										<a class="tooltip-button set-sidebar-style bg-primary"
											data-header-bg="bg-primary font-inverse" title="Primary"
											href="javascript:void(0);">Primary</a> <a
											class="tooltip-button set-sidebar-style bg-green"
											data-header-bg="bg-green font-inverse" title="Green"
											href="javascript:void(0);">Green</a> <a
											class="tooltip-button set-sidebar-style bg-red"
											data-header-bg="bg-red font-inverse" title="Red"
											href="javascript:void(0);">Red</a> <a
											class="tooltip-button set-sidebar-style bg-blue"
											data-header-bg="bg-blue font-inverse" title="Blue"
											href="javascript:void(0);">Blue</a> <a
											class="tooltip-button set-sidebar-style bg-warning"
											data-header-bg="bg-warning font-inverse" title="Warning"
											href="javascript:void(0);">Warning</a> <a
											class="tooltip-button set-sidebar-style bg-purple"
											data-header-bg="bg-purple font-inverse" title="Purple"
											href="javascript:void(0);">Purple</a> <a
											class="tooltip-button set-sidebar-style bg-black"
											data-header-bg="bg-black font-inverse" title="Black"
											href="javascript:void(0);">Black</a>

										<div class="clear"></div>

										<h5 class="mrg15T">Gradients</h5>
										<a class="tooltip-button set-sidebar-style bg-gradient-1"
											data-header-bg="bg-gradient-1 font-inverse"
											title="Gradient 1" href="javascript:void(0);">Gradient 1</a>
										<a class="tooltip-button set-sidebar-style bg-gradient-2"
											data-header-bg="bg-gradient-2 font-inverse"
											title="Gradient 2" href="javascript:void(0);">Gradient 2</a>
										<a class="tooltip-button set-sidebar-style bg-gradient-3"
											data-header-bg="bg-gradient-3 font-inverse"
											title="Gradient 3" href="javascript:void(0);">Gradient 3</a>
										<a class="tooltip-button set-sidebar-style bg-gradient-4"
											data-header-bg="bg-gradient-4 font-inverse"
											title="Gradient 4" href="javascript:void(0);">Gradient 4</a>
										<a class="tooltip-button set-sidebar-style bg-gradient-5"
											data-header-bg="bg-gradient-5 font-inverse"
											title="Gradient 5" href="javascript:void(0);">Gradient 5</a>
										<a class="tooltip-button set-sidebar-style bg-gradient-6"
											data-header-bg="bg-gradient-6 font-inverse"
											title="Gradient 6" href="javascript:void(0);">Gradient 6</a>
										<a class="tooltip-button set-sidebar-style bg-gradient-7"
											data-header-bg="bg-gradient-7 font-inverse"
											title="Gradient 7" href="javascript:void(0);">Gradient 7</a>
										<a class="tooltip-button set-sidebar-style bg-gradient-8"
											data-header-bg="bg-gradient-8 font-inverse"
											title="Gradient 8" href="javascript:void(0);">Gradient 8</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- WIDGETS -->

					<script type="text/javascript"
						src="assets/bootstrap/js/bootstrap.js"></script>

					<!-- Bootstrap Dropdown -->

					<!-- <script type="text/javascript" src="assets/widgets/dropdown/dropdown.js"></script> -->

					<!-- Bootstrap Tooltip -->

					<!-- <script type="text/javascript" src="assets/widgets/tooltip/tooltip.js"></script> -->

					<!-- Bootstrap Popover -->

					<!-- <script type="text/javascript" src="assets/widgets/popover/popover.js"></script> -->

					<!-- Bootstrap Progress Bar -->

					<script type="text/javascript"
						src="assets/widgets/progressbar/progressbar.js"></script>

					<!-- Bootstrap Buttons -->

					<script type="text/javascript"
						src="assets/widgets/button/button.js"></script>

					<!-- Bootstrap Collapse -->

					<!-- <script type="text/javascript" src="assets/widgets/collapse/collapse.js"></script> -->

					<!-- Superclick -->

					<script type="text/javascript"
						src="assets/widgets/superclick/superclick.js"></script>

					<!-- Input switch alternate -->

					<script type="text/javascript"
						src="assets/widgets/input-switch/inputswitch-alt.js"></script>

					<!-- Slim scroll -->

					<script type="text/javascript"
						src="assets/widgets/slimscroll/slimscroll.js"></script>

					<!-- Slidebars -->

					<script type="text/javascript"
						src="assets/widgets/slidebars/slidebars.js"></script>
					<script type="text/javascript"
						src="assets/widgets/slidebars/slidebars-demo.js"></script>

					<!-- PieGage -->

					<script type="text/javascript"
						src="assets/widgets/charts/piegage/piegage.js"></script>
					<script type="text/javascript"
						src="assets/widgets/charts/piegage/piegage-demo.js"></script>

					<!-- Screenfull -->

					<script type="text/javascript"
						src="assets/widgets/screenfull/screenfull.js"></script>

					<!-- Content box -->

					<script type="text/javascript"
						src="assets/widgets/content-box/contentbox.js"></script>

					<!-- Overlay -->

					<script type="text/javascript"
						src="assets/widgets/overlay/overlay.js"></script>

					<!-- Widgets init for demo -->

					<script type="text/javascript" src="assets/js-init/widgets-init.js"></script>

					<!-- Theme layout -->

					<script type="text/javascript" src="assets/themes/admin/layout.js"></script>

					<!-- Theme switcher -->

					<script type="text/javascript"
						src="assets/widgets/theme-switcher/themeswitcher.js"></script>


					<script src="js/jquery.validate.min.js"></script>