<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>选课系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS Libs -->
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

<script type="text/javascript">

	//判断课程ID是否存在
	function onfocuscourseID() {
		var couserIDcheck = document.getElementById("couserIDcheck");
		couserIDcheck.style.display = "none";
	}

	function onblurcourseID(val) {
		var couserIDcheck = document.getElementById("couserIDcheck");

		if (val == "") {
			couserIDcheck.innerHTML = "• 课程ID未填写";
			couserIDcheck.style.display = "";
			return;
		}
		

		$.ajax({
			type : "GET",
			url : "<%=basePath%>course/courseIDCheck?courseID=" + val,
			data : "",
			success : function(data) {
				couserIDcheck.innerHTML = "• 课程ID已存在,请重新填写";
				//状态为1用户存在 状态为0用户不存在
				if (data.state == "1") {
					couserIDcheck.style.display = "";
				} else {
					couserIDcheck.style.display = "none";
				}
			}
		});
	}

	//判断课程名称
	function onfocuscoursename() {
		var cousernamecheck = document.getElementById("cousernamecheck");
		cousernamecheck.style.display = "none";
	}

	function onblurcoursename(val) {
		var cousernamecheck = document.getElementById("cousernamecheck");
		if (val == "") {
			cousernamecheck.style.display = "";
		} else {
			cousernamecheck.style.display = "none";
		}
	}

	//判断总学时
	function onfocuscoursehours() {
		var cousercoursehours = document.getElementById("cousercoursehours");
		cousercoursehours.style.display = "none";
	}

	function onblurcoursehours(val) {
		var cousercoursehours = document.getElementById("cousercoursehours");
		if (val == "") {
			cousercoursehours.style.display = "";
		} else {
			cousercoursehours.style.display = "none";
		}
	}

	//判断授课学时
	function onfocusteachhours() {
		var onblurteachhours = document.getElementById("teachhourscheck");
		onblurteachhours.style.display = "none";
	}

	function onblurteachhours(val) {
		var teachhourscheck = document.getElementById("teachhourscheck");
		if (val == "") {
			teachhourscheck.style.display = "";
		} else {
			teachhourscheck.style.display = "none";
		}
	}

	//判断实验学时
	function onfocusexperimenthours() {
		var experimenthourscheck = document.getElementById("experimenthourscheck");
		experimenthourscheck.style.display = "none";
	}

	function onblurexperimenthours(val) {
		var experimenthourscheck = document.getElementById("experimenthourscheck");
		if (val == "") {
			experimenthourscheck.style.display = "";
		} else {
			experimenthourscheck.style.display = "none";
		}
	}

	//判断学分
	function onfocuscredit() {
		var cousercredit = document.getElementById("cousercredit");
		cousercredit.style.display = "none";
	}

	function onblurcredit(val) {
		var cousercredit = document.getElementById("cousercredit");
		if (val == "") {
			cousercredit.style.display = "";
		} else {
			cousercredit.style.display = "none";
		}
	}

	//退出
	function loginout() {
		location.href = "${pageContext.request.contextPath}/course/loginOut";
	}

	//提交选课信息判断
	function submitcheck() {
		var courseID = document.getElementById("courseID").value;
		var coursename = document.getElementById("coursename").value;
		var coursehours = document.getElementById("coursehours").value;
		var teachhours = document.getElementById("teachhours").value;
		var experimenthours = document.getElementById("experimenthours").value;
		var credit = document.getElementById("credit").value;

		var couserIDcheck = document.getElementById("couserIDcheck");
		var cousernamecheck = document.getElementById("cousernamecheck");
		var cousercoursehours = document.getElementById("cousercoursehours");
		var teachhourscheck = document.getElementById("teachhourscheck");
		var experimenthourscheck = document.getElementById("experimenthourscheck");
		var cousercredit = document.getElementById("cousercredit");

		if (courseID == "") {

			couserIDcheck.innerHTML = "• 课程ID未填写";
			couserIDcheck.style.display = "";
		}

		if (coursename == "") {

			cousernamecheck.style.display = "";
		}

		if (coursehours == "") {

			cousercoursehours.style.display = "";
		}

		if (credit == "") {
			cousercredit.style.display = "";
		}

		if (teachhours == "") {
			teachhourscheck.style.display = "";
		}

		if (experimenthours == "") {
			experimenthourscheck.style.display = "";
		}

		if (couserIDcheck.style.display == "none" &&
			cousernamecheck.style.display == "none" &&
			cousercoursehours.style.display == "none" &&
			teachhourscheck.style.display == "none" &&
			experimenthourscheck.style.display == "none" &&
			cousercredit.style.display == "none") {
			document.getElementById("formid").submit();
		}

		return;

	}
</script>

<style type="text/css">
td {
	height: 100px;
	width: 500px;
}

tr {
	width: 200px;
}
</style>

</head>

<body class="flat-blue">
	<div class="app-container">
		<div class="row content-container">
			<nav class="navbar navbar-default navbar-fixed-top navbar-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-expand-toggle">
						<i class="fa fa-bars icon"></i>
					</button>
					<ol class="breadcrumb navbar-breadcrumb">
						<li>课程</li>
						<li class="active">添加课程</li>
					</ol>
					<button type="button"
						class="navbar-right-expand-toggle pull-right visible-xs">
						<i class="fa fa-th icon"></i>
					</button>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown profile"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-expanded="false" style="margin-right: 40px">${sessionScope.user.name}<span
							class="caret" style="margin-left: 10px"></span></a>
						<ul class="dropdown-menu animated fadeInDown">
							<li class="profile-img"><img src="img/img.jpg"
								class="profile-img"></li>
							<li>
								<div class="profile-info">
									<h4 class="username">${sessionScope.user.name}</h4>
									<p>人生若只如初见 何事秋风悲画扇</p>
									<div class="btn-group margin-bottom-2x" role="group">
										<button type="button" class="btn btn-default"
											onclick="loginout()">
											<i class="fa fa-sign-out"></i> 退出
										</button>
									</div>
								</div>
							</li>
						</ul></li>
				</ul>
			</div>
			</nav>
			<div class="side-menu sidebar-inverse">
				<nav class="navbar navbar-default" role="navigation">
				<div class="side-menu-container">
					<div class="navbar-header">
						<a class="navbar-brand" href="<%=basePath%>course/index">
							<div class="icon fa fa-paper-plane"></div>
							<div class="title">选课系统</div>
						</a>
						<button type="button"
							class="navbar-expand-toggle pull-right visible-xs">
							<i class="fa fa-times icon"></i>
						</button>
					</div>
					<ul class="nav navbar-nav">
						<li class="panel panel-default dropdown"><a
							data-toggle="collapse" href="#dropdown-table"> <span
								class="icon fa fa-table"></span><span class="title">课程</span>
						</a> <!-- Dropdown level 1 -->
							<div id="dropdown-table" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="<%=basePath%>course/index">全部课程</a></li>
										<c:if test="${sessionScope.user.grade == 1}">
											<li><a href="<%=basePath%>course/myCourse">已选课程</a></li>
											<li><a href="<%=basePath%>course/notMyCourse">未选课程</a></li>
										</c:if>
										<c:if test="${sessionScope.user.grade == 0}">
											<li><a href="<%=basePath%>course/addCoursePage">添加课程</a>
											</li>
										</c:if>
									</ul>
								</div>
							</div></li>
						<c:if test="${sessionScope.user.grade == 1}">
							<li class="panel panel-default dropdown"><a
								data-toggle="collapse" href="#dropdown-form"> <span
									class="icon fa fa-file-text-o"></span><span class="title">选课</span>
							</a> <!-- Dropdown level 1 -->
								<div id="dropdown-form" class="panel-collapse collapse">
									<div class="panel-body">
										<ul class="nav navbar-nav">
											<li><a href="<%=basePath%>course/selectCoursePage">选择课程</a>
											</li>
											<li><a href="<%=basePath%>course/deleteSelectCoursePage">删除选课</a>
											</li>
										</ul>
									</div>
								</div></li>
						</c:if>
						<%--  <c:if test="${sessionScope.user.grade == 0}">
	                            <li class="panel panel-default dropdown">
	                                <a data-toggle="collapse" href="#dropdown-example">
	                                    <span class="icon fa fa-slack"></span><span class="title">操作</span>
	                                </a>
	                                <!-- Dropdown level 1 -->
	                                <div id="dropdown-example" class="panel-collapse collapse">
	                                    <div class="panel-body">
	                                        <ul class="nav navbar-nav">
	                                            <li><a href="">添加学生信息</a>
	                                            </li>
	
	                                            <li><a href="#">编辑学生信息</a>
	                                            </li>
	                                        </ul>
	                                    </div>
	                                </div>
	                            </li>
	                        </c:if> --%>
					</ul>
				</div>
				<!-- /.navbar-collapse --> </nav>
			</div>
			<!-- Main Content -->
			<div class="side-body">
				<div class="page-title" align="center" style="margin-right: 200px">
					<span class="title">添加课程信息</span>
				</div>
				<form action="<%=basePath%>course/insertCourse" id="formid"
					method="post">
					<div style="widows: 100%;font-size: 16px;margin-top: 50px">
						<table align="center">

							<tr>

								<td>课程编号: <input name="courseID" id="courseID" type="text"
									class="form-control" placeholder="Text input"
									style="width: 200px;" onfocus="onfocuscourseID()"
									onblur="onblurcourseID(this.value)"
									onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" />
									<span id="couserIDcheck"
									style="font-size: 13px;color: red;margin-left: 20px;display: none"></span>
								</td>

								<td>课程名称: <input name="coursename" id="coursename"
									type="text" class="form-control" placeholder="Text input"
									style="width: 200px;" onfocus="onfocuscoursename()"
									onblur="onblurcoursename(this.value)"> <span
									id="cousernamecheck"
									style="font-size: 13px;color: red;margin-left: 20px;display: none">•
										课程名称未填写</span>
								</td>

							</tr>

							<tr>
								<td>课程性质:
									<div class="radio3 radio-check radio-inline">
										<input type="radio" id="radio4" name="coursetype" value="1"
											checked=""> <label for="radio4"> 公共课 </label>
									</div>
									<div class="radio3 radio-check radio-success radio-inline">
										<input type="radio" id="radio5" name="coursetype" value="2">
										<label for="radio5"> 必修课 </label>
									</div>
									<div class="radio3 radio-check radio-warning radio-inline">
										<input type="radio" id="radio6" name="coursetype" value="3">
										<label for="radio6"> 选修课 </label>
									</div>
								</td>

								<td>总学时:<input name="coursehours" id="coursehours"
									type="text" class="form-control" placeholder="Text input"
									style="width: 200px;margin-left: 20px;"
									onfocus="onfocuscoursehours()"
									onblur="onblurcoursehours(this.value)"
									onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" />
									<span id="cousercoursehours"
									style="font-size: 13px;color: red;margin-left: 20px;display: none">•
										总学时未填写</span>
								</td>

							</tr>

							<tr>

								<td>授课学时: <input id="teachhours" name="teachhours"
									type="text" class="form-control" placeholder="Text input"
									style="width: 200px;" onfocus="onfocusteachhours()"
									onblur="onblurteachhours(this.value)"
									onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" />
									<span id="teachhourscheck"
									style="font-size: 13px;color: red;margin-left: 20px;display: none">•
										授课学时未填写</span>
								</td>

								<td>实验学时:<input id="experimenthours" name="experimenthours"
									type="text" class="form-control" placeholder="Text input"
									style="width: 200px;" onfocus="onfocusexperimenthours()"
									onblur="onblurexperimenthours(this.value)"
									onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" />
									<span id="experimenthourscheck"
									style="font-size: 13px;color: red;margin-left: 20px;display: none">•
										实验学时未填写</span>
								</td>

							</tr>

							<tr>

								<td>学分: <input name="credit" id="credit" type="text"
									class="form-control" placeholder="Text input"
									style="width: 200px;margin-left: 30px;"
									onfocus="onfocuscredit()" onblur="onblurcredit(this.value)"
									onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" />
									<span id="cousercredit"
									style="font-size: 13px;color: red;margin-left: 20px;display: none">•
										学分未填写</span>
								</td>
								<td>开课学期: <input name="coursestart" type="date"
									class="form-control" placeholder="Text input"
									style="width: 200px;">
								</td>
							</tr>
							<tr style="text-align: center;">
								<td colspan="2">
									<button type="reset" class="btn btn-default">重置</button>
									<button type="button" class="btn btn-default"
										style="margin-right: 200px;margin-left: 50px;"
										onclick="submitcheck()">提交</button>
								</td>
							</tr>

						</table>

					</div>

				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Chart.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-switch.min.js"></script>

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
