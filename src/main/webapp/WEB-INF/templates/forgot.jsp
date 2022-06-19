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

40%{
-webkit-transform




















:scale




















(1
.0




















)
}
}
@
keyframes bouncedelay { 0%,80%,100%{
	transform: scale(0.0);
	-webkit-transform: scale(0.0)
}
40%{
transform




















:scale




















(1
.0




















);
-webkit-transform




















:scale




















(1
.0




















)
}
}
</style>
<meta charset="UTF-8">
<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<title>Login</title>
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


<style type="text/css">
html, body {
	height: 100%;
}
</style>


<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function() {
			$('#loading').fadeOut(400, "linear");
		}, 300);
	});
</script>



</head>
<body onclick=" clinkOnBody()">
	<div id="loading">
		<div class="spinner">
			<div class="bounce1"></div>
			<div class="bounce2"></div>
			<div class="bounce3"></div>
		</div>
	</div>


	<div class="center-vertical bg-black">
		<div class="center-content row">
			<form action="upadatePassword" id="login-validation"
				class="center-margin col-xs-11 col-sm-5" method="post"
				autocomplete="off" data-parsley-validate="" id="demo-form">
				<h3 class="text-center pad25B font-gray font-size-23">
					RRC ADMIN PANEL <span class="opacity-80"></span>
				</h3>
		
				
				<div id="login-forgot" class="content-box modal-content">
					<div class="content-box-wrapper pad20A">

						<div class="form-group">
							<label for="exampleInputEmail1">UserName:</label>
							<div class="input-group input-group-lg">
								<span
									class="input-group-addon addon-inside bg-white font-primary">
									<i class="glyph-icon icon-user"></i>
								</span> <input type="text" class="form-control"
									id="username" placeholder="Enter username" required>
									<span class="parsley-required" id="errorMsg"></span>
							</div>
						</div>
						
					</div>
					<div class="button-pane text-center">
						<button type="button" class="btn btn-md btn-primary" id="usernameBtn">
							Next</button>
						<a href="index" class="btn btn-md btn-link "
							
							title="Cancel">Cancel</a>
					</div>
				</div>
           <div id="login-otp" class="content-box modal-content hide">
					<div class="content-box-wrapper pad20A">

						<div class="form-group">
							<label for="exampleInputEmail1">Enter Otp:</label>
							<div class="input-group input-group-lg">
								<span
									class="input-group-addon addon-inside bg-white font-primary">
									<i class="glyph-icon icon-user"></i>
								</span> <input type="text" class="form-control"
									id="otp" placeholder="Enter OTP" required>
							</div>
							<span class="parsley-required" id="errorMsgOTP"></span>
						</div>
					</div>
					<div class="button-pane text-center">
						<button type="button" class="btn btn-md btn-primary" id="otpBtn">
							Next</button>
						<a href="index" class="btn btn-md btn-link "
							
							title="Cancel">Cancel</a>
					</div>
				</div> 
				<div id="upadatePassword" class="content-box modal-content hide">
					<div class="content-box-wrapper pad20A">
	                 <label id="success" class="font-size-16 text-center	 font-green
"></label>
						
						<div class="form-group">
							<label for="exampleInputEmail1">Enter New Password:</label>
							<div class="input-group input-group-lg">
								<span
									class="input-group-addon addon-inside bg-white font-primary">
									<i class="glyph-icon icon-unlock-alt"></i>
								</span> <input type="password" class="form-control"
									id="ps1" placeholder="Enter New Password" required>
							</div>
						</div>
						<div class="form-group">
							<label >Conform Password:</label>
							<div class="input-group input-group-lg">
								<span
									class="input-group-addon addon-inside bg-white font-primary">
									<i class="glyph-icon icon-unlock-alt"></i>
								</span> <input type="password" class="form-control"
									data-parsley-equalto="#ps1" placeholder="Conform Password" required id="ps2">
							</div>
							<span class="parsley-required" id="errorMsgps"></span>
						</div>
					</div>
					<div class="button-pane text-center">
						<button type="button" class="btn btn-md btn-primary" id="submit">
							Update</button>
						<a href="index" class="btn btn-md btn-link "
							
							title="Login" id="login">login</a>
					</div>
					</div>
			
              <input type="hidden" id="userId">
			</form>

		</div>
	


	<!-- WIDGETS -->

	<script type="text/javascript" src="assets/bootstrap/js/bootstrap.js"></script>

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

	<!-- <script type="text/javascript" src="assets/widgets/button/button.js"></script> -->

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

	<script type="text/javascript" src="assets/widgets/overlay/overlay.js"></script>

	<!-- Widgets init for demo -->

	<script type="text/javascript" src="assets/js-init/widgets-init.js"></script>

	<!-- Theme layout -->

	<script type="text/javascript" src="assets/themes/admin/layout.js"></script>

	<!-- Theme switcher -->

	<script type="text/javascript"
		src="assets/widgets/theme-switcher/themeswitcher.js"></script>

	<!-- parsley validation -->
	<script type="text/javascript" src="assets/widgets/parsley/parsley.js"></script>
	<script>
		function clinkOnBody() {
			$('#msg').hide();
		}$(document).ready(function(){
			$('#usernameBtn').click(function(){
				$('#errorMsg').html('');
			var username=	$('#username').val();
			if(username!=""){
				$.ajax({
					method : 'POST',
					url : 'finduser',
					cache : false,
					data : {
						'username' : username
					},
					ContentType : 'json',
					success : function(data) {
						
				if(data.status==200){
					$('#login-forgot').hide();
					$('#login-otp').removeClass('hide');
					$('#userId').val(data.userId);
				}else{
					$('#errorMsg').html('Please enter valid username');
				}
					},
					error : function(jqxhr, status, exception) {
						alert(exception, status)

					}

				});}else{
					$('#errorMsg').html('This value is required.');
				}
			});
			$('#otpBtn').click(function(){
				debugger;
			var otp=	$('#otp').val();
		var userId=	$('userId').val();
			if(otp!=""){
				$.ajax({
					method : 'POST',
					url : 'checkotp',
					cache : false,
					data : {
						'otp' : otp
					},
					ContentType : 'json',
					success : function(data) {
				if(data.status==200){
					$('#upadatePassword').removeClass('hide');
					$('#login-otp').hide();
				}else{
					$('#errorMsgOTP').html('Invalid OTP');
				}
					},
					error : function(jqxhr, status, exception) {
						alert(exception, status)
					}

				});}else{
					$('#errorMsgOTP').html('This value is required.');
				}
			});
			$('#submit').click(function(){
				debugger;
				var userId=$('#userId').val();
			var ps1=	$('#ps1').val();
			var ps2=	$('#ps2').val();
			if(ps1!="" && ps2!=""){
			if(ps1==ps2){
		
				$.ajax({
					method : 'POST',
					url : 'updatePassword',
					cache : false,
					data : {
						'password' : ps1,
						'userId':userId
					},
					ContentType : 'json',
					success : function(data) {
				if(data.status==200){
					$('#success').html('Password updated successfully');
					$('#ps1').val('');
					$('#ps2').val('');
					window.location.href="index";
					//$('login').trigger("click");
					return false;
				}else{
					$('#errorMsgps').html('INTERNAL SERVER ERROR.');
					return false;
				}
					},
					error : function(jqxhr, status, exception) {
						alert(exception, status)
					}

				});}else{
					$('#errorMsgps').html('This value should be the same.');
					return false;
				}
			
			
			}else{
					$('#errorMsgps').html('This value is required.');
					return false;
				}
			});
		});
	</script>

</body>





</html>