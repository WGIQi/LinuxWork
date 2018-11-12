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

	$(document).ready(function() {
		$("#submitbutton").click(function() {
			var spanvalue = $("#sumcredit").text();
			if (spanvalue < 60) {
				$("#creditModal").modal("show");
				return;
			}
			$("#submitModal").modal("show");
		});
	});

	function deletes(val) {
		$.ajax({
			type : "GET",
			url : "<%=basePath%>course/deleteCourseInfoById?courseID=" + val,
			data : "",
			success : function(success) {
				location.href = "<%=basePath%>course/deleteSelectCoursePage";
			}
		});
	}
	;


	function checkboxAll() {
		var id = document.getElementsByName('course');
		var url = "${pageContext.request.contextPath}/course/addCourseInfo?courseID=";
		for (var i = 0; i < id.length; i++) {
			if (id[i].checked)
				url = url + id[i].value + "-";
		}
		location.href = url;
	}

	function selectAll() {
		var checklist = document.getElementsByName("course");
		if (document.getElementById("setinterval").checked) {
			for (var i = 0; i < checklist.length; i++) {
				checklist[i].checked = 1;
			}
		} else {
			for (var j = 0; j < checklist.length; j++) {
				checklist[j].checked = 0;
			}
		}
		var span = document.getElementById("sumcredit");
		span.innerHTML = 0;

	}

	//退出
	function loginout() {
		location.href = "${pageContext.request.contextPath}/course/loginOut";
	}

	function checkcourse(val) {
		var statue = document.getElementById(val);
		var span = document.getElementById("sumcredit");
		var spanvalue = span.innerHTML;
		var id = val.split("-")[3];
		$.ajax({
			type : "POST",
			url : "<%=basePath%>course/findCreditById?courseID=" + id,
			data : "",
			success : function(data) {
				if (statue.checked) {
					span.innerHTML = parseInt(spanvalue) + parseInt(data.credit)
				} else {
					span.innerHTML = parseInt(spanvalue) - parseInt(data.credit)
				}
			}
		});
	}
	;
</script>

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
						<li>${mainmenu}</li>
						<li class="active">${menu}</li>
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
						<%--                             <c:if test="${sessionScope.user.grade == 0}">
	                            <li class="panel panel-default dropdown">
	                                <a data-toggle="collapse" href="#dropdown-example">
	                                    <span class="icon fa fa-slack"></span><span class="title">操作</span>
	                                </a>
	                                <!-- Dropdown level 1 -->
	                                <div id="dropdown-example" class="panel-collapse collapse">
	                                    <div class="panel-body">
	                                        <ul class="nav navbar-nav">
	                                            <li><a href="#">添加学生信息</a>
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
			<div class="container-fluid">
				<div class="side-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="card">
								<div class="card-header" style="width: 100%">

									<div class="card-title">
										<div class="title">课程信息</div>
										<div class="title"
											style="float: right;margin-top: -27px;margin-right: 50px;font-size: 16px;">
											所选课程学分总数 &nbsp;: &nbsp;&nbsp;&nbsp;<span id="sumcredit"
												style="color: red">${creditsum}</span>
										</div>
									</div>
								</div>

								<div class="card-body">
									<table class="datatable table table-striped" cellspacing="0"
										width="100%">
										<thead style="text-align: center;">
											<tr>
												<th>课程编号</th>
												<th>课程名称</th>
												<th>课程性质</th>
												<th>总学时</th>
												<th>授课学时</th>
												<th>实验学时</th>
												<th>学分</th>
												<th>开课学期</th>
												<c:choose>
													<c:when test="${ count == 1 }">
														<th>选择</th>
													</c:when>
													<c:otherwise>
														<th>操作</th>
													</c:otherwise>
												</c:choose>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${courses}" var="course" varStatus="status">
												<tr style="text-align: center;">
													<td>${course.courseID}</td>
													<td>${course.coursename}</td>
													<td>${course.coursetype }</td>
													<td>${course.coursehours}</td>
													<td>${course.teachhours}</td>
													<td>${course.experimenthours}</td>
													<td>${course.credit}</td>
													<td>${course.coursestart}</td>

													<c:choose>
														<c:when test="${ count == 1 }">
															<td>
																<div id="checkboxs"
																	class="checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light">
																	<input name="course" type="checkbox"
																		value="${course.courseID}"
																		id="checkbox-fa-light-${course.courseID}"
																		onclick="checkcourse('checkbox-fa-light-${course.courseID}')" />
																	<label for="checkbox-fa-light-${course.courseID}">
																	</label>
																</div>
															</td>
														</c:when>
														<c:otherwise>
															<td>
																<button class="btn btn-primary btn-lg"
																	value="${course.courseID}" data-toggle="modal"
																	data-target="#myModal${course.courseID}"
																	style="font-size: 14px;padding: 3px 5px;margin-bottom: 0px">
																	删除</button>
																<div class="modal fade" id="myModal${course.courseID}"
																	tabindex="-1" role="dialog"
																	aria-labelledby="myModalLabel" aria-hidden="true">
																	<div class="modal-dialog">
																		<div class="modal-content" style="text-align: center;">
																			<div class="modal-header">
																				<button type="button" class="close"
																					data-dismiss="modal" aria-hidden="true">×
																				</button>
																				<h4 class="modal-title" id="myModalLabel">提示</h4>
																			</div>
																			<div class="modal-body">确定删除该课程?</div>
																			<div class="modal-footer">
																				<button type="button" class="btn btn-default"
																					data-dismiss="modal">关闭</button>
																				<button type="button" class="btn btn-primary"
																					data-dismiss="modal"
																					onclick="deletes(${course.courseID})">确定</button>
																			</div>
																		</div>
																		<!-- /.modal-content -->
																	</div>
																	<!-- /.modal-dialog -->
																</div>
																<!-- /.modal -->
															</td>
														</c:otherwise>
													</c:choose>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div style="float: right;margin-right: 50px;margin-top: -20px"">
									<c:if test="${ count == 1 }">
										<button type="submit" class="btn btn-default" id="setinterval"
											data-toggle="modal" data-target="#selectAllModal">取消</button>
									</c:if>
									<div class="modal fade" id="selectAllModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" style="text-align: center;">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">提示</h4>
												</div>
												<div class="modal-body">是否取消所有已选课程?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">关闭</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal" onclick="selectAll()">确定</button>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- /.modal -->
									<c:if test="${ count == 1 }">
										<button type="submit" id="submitbutton"
											class="btn btn-default" style="margin-left: 50px">提交</button>
									</c:if>
									<div class="modal fade" id="submitModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" style="text-align: center;">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">提示</h4>
												</div>
												<div class="modal-body">确定选择课程?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">关闭</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal" onclick="checkboxAll()">确定</button>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- /.modal -->
									<div class="modal fade" id="creditModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" style="text-align: center;">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">提示</h4>
												</div>
												<div class="modal-body">所选择课程学分小于60分，请重新选择</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">确定</button>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- /.modal -->
								</div>
							</div>
						</div>
					</div>
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
