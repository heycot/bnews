<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>VinaEnter EDU - Admin template</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/admin/css/styles.css" media="screen" />
	<script src="<%=request.getContextPath()%>/template/admin/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/template/admin/jquery/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath()%>/libaries/ckeditor/ckeditor.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/libaries/ckfinder/ckfinder.js" type="text/javascript"></script>
</head>
<body>
	<!-- Header -->
	<div id="header">
		<!-- Header. Status part -->
		<div id="header-status">
			<div class="container_12">
				<div class="grid_4">
					<ul class="user-pro">
					<%
					//	HttpSession session = request.getSession();
					Users admin = (Users)session.getAttribute("admin");
					%>
						<li><a href="<%= request.getContextPath()%>/admin/logout" onclick="return confirm('Bạn có muốn log out?')">Logout</a></li>
						<li>Chào, <a href="profile.jsp"><%= admin.getUsername()%></a></li>
					</ul>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
		<!-- End #header-status -->

		<!-- Header. Main part -->
		<div id="header-main">
			<div class="container_12">
				<div class="grid_12">
					<div id="logo">
						<ul id="nav">
							<li id="current"><a href="<%= request.getContextPath()%>/admin">Quản trị</a></li>
							<li><a href="<%= request.getContextPath()%>/admin">Tài khoản</a></li>
						</ul>
					</div>
					<!-- End. #Logo -->
				</div>
				<!-- End. .grid_12-->
				<div style="clear: both;"></div>
			</div>
			<!-- End. .container_12 -->
		</div>
		<!-- End #header-main -->
		<div style="clear: both;"></div>
		<!-- Sub navigation -->
		<div id="subnav">
			<div class="container_12">
				<div class="grid_12">
					<ul>
						<li><a href="<%=request.getContextPath()%>/admin/news">Tin
								tức</a></li>
						<li><a href="<%=request.getContextPath()%>/admin/cat">Danh
								mục</a></li>
						<li><a href="<%=request.getContextPath()%>/admin/user">Users</a></li>
					</ul>

				</div>
				<!-- End. .grid_12-->
			</div>
			<!-- End. .container_12 -->
			<div style="clear: both;"></div>
		</div>
		<!-- End #subnav -->
	</div>
	<!-- End #header -->

	<div class="container_12">