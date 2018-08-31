<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Không tìm thấy trng</title>
<style type="text/css">
	body{
		margin: 0 auto;
	}
	a{
		text-decoration: none;
		color: blue;
	}
</style>
</head>
<body>
	<h1>Lỗi 404</h1>
	<h2>Không thể tìm thấy trang</h2>
	<br>
	<h3> <a href="<%= request.getContextPath()%>/trang-chu" >Quay về trang chủ</a></h3>
</body>
</html>