<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>选课管理系统</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/animate.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-switch.min.css">
<link rel="stylesheet" type="text/css" href="css/checkbox3.min.css">
<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/select2.min.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/flat-blue.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/Chart.min.js"></script>
<script type="text/javascript" src="js/bootstrap-switch.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		$("#login").click(function() {
			var username = $("#username").val();
			var password = $("#password").val();

			$.ajax({
				type : "GET",
				url : "<%=basePath%>course/ajaxLogin?username=" + username + "&password=" + password,
				data : "",
				success : function(data) {
					if (data.state == 0) {
						location.href = "<%=basePath%>course/main";
					}
					if (data.state == 1) {
						$("#password").val("")
						$("#loginModal").modal("show");
					}
				}
			});

		});
	});
</script>
</head>

<body class="flat-blue login-page">
	<div class="container">
		<div class="login-box">
			<div>
				<div class="login-form row">
					<div class="col-sm-12 text-center login-header">
						<i class="login-logo fa fa-paper-plane fa-5x"></i>
						<h4 class="login-title">选课管理系统</h4>
					</div>
					<div class="col-sm-12">
						<div class="login-body">
							<div class="control">
								<span style="color: #FFF;">用户名:</span><input type="text"
									id="username" style="width: 80%;margin-left: 10px"
									class="form-control" name="username" />
							</div>
							<div class="control">
								<span style="color: #FFF;">密码:</span><input type="password"
									id="password" style="width: 80%;margin-left: 23px"
									class="form-control" name="password" />
							</div>

							<div class="login-button text-center">
								<input type="button" id="login" class="btn btn-primary"
									value="登陆">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Javascript Libs -->

	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="text-align: center;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">用户名或密码错误！请重新填写</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script type="text/javascript" src="js/jquery.matchHeight-min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="js/select2.full.min.js"></script>
	<script type="text/javascript" src="js/ace/ace.js"></script>
	<script type="text/javascript" src="js/ace/mode-html.js"></script>
	<script type="text/javascript" src="js/ace/theme-github.js"></script>
	<!-- Javascript -->
	<script type="text/javascript" src="js/app.js"></script>
</body>
</html>
