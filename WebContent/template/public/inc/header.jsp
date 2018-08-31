<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VinaEnter EDU-public</title>
<meta name="description" content="Thiet ke website, dao tap lap trinh">
<meta name="keywords" content="hiet ke website, dao tap lap trinh">
<link href="<%= request.getContextPath()%>/template/public/css/style.css"
	rel="stylesheet" type="text/css" >
</head>
<body>
	<div class="main">
		<div class="page">
			<div class="header">
				<div class="header-img">
					<img
						src="<%=request.getContextPath()%>/template/public/images/header.jpg"
						alt="" width="800">
				</div>
				<div class="clc"></div>
				<div class="top-header">
					<div class="topmenu">
						<ul>
							<li><a href="<%=request.getContextPath()%>/trang-chu">Trang Chủ</a></li>
							<li><a href="<%=request.getContextPath()%>/tin-tuc">Tin Tức</a></li>
						</ul>
					</div>
					<div class="search">
						<form action="<%=request.getContextPath()%>/search" method="post">
							<input class="inputsearch" type="text" name="search" value=""
								placeholder="search something here">
						</form>
					</div>
					<div class="clc"></div>
				</div>
			</div>
			<div class="content">