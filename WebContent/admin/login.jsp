<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hello Admin!</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/template/admin/css/loginStyle.css">
	<script src="<%=request.getContextPath()%>/template/admin/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/template/admin/jquery/jquery.validate.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<h2>Chào Admin</h2>
		<%
			if (request.getAttribute("error") != null) {
				String error = (String) request.getAttribute("error");
		%>
			<h4 style='color:red'><%= error %></h4>
		<%
			}
		%>
		<form action="<%=request.getContextPath()%>/login" method="post"
			class="form">
			<p>Username:</p>
			<input class="input" type="text" name="username" value="">
			<p>Password:</p>
			<input class="input" type="password" name="password" value=""> <br>
			<input id="submit" type="submit" name="submit" value="Đăng Nhập">
			<input id="submit" type="reset" name="reset" value="Nhập Lại">
		</form>
	</div>
	<div class="myslides">
		<img alt=""
			src="<%=request.getContextPath()%>/template/admin/images/slide1.jpg"
			style="width: 100%">
	</div>
	<div class="myslides">
		<img alt=""
			src="<%=request.getContextPath()%>/template/admin/images/slide2.jpg"
			style="width: 100%">
	</div>
	<div class="myslides">
		<img alt=""
			src="<%=request.getContextPath()%>/template/admin/images/slide3.jpg"
			style="width: 100%">
	</div>
	<script type="text/javascript">
		var slideIndex = 0;
		showSlides();
		function showSlides() {
			var slides = document.getElementsByClassName("myslides");

			//cho cả 3 ảnh k hiển thị
			for (var i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			//trỏ đến pt slide tiếp theo
			slideIndex++;
			//nêu hết rồi thì quay lại 1
			if (slideIndex > slides.length) {
				slideIndex = 1;
			}
			//cho slide tại vị trí đang set hiển thị
			slides[slideIndex - 1].style.display = "block";
			setTimeout(showSlides, 10000);
		}

		$().ready(function() {
			$("form").validate({
				rules : {
					username : {
						required : true,
						minlength : 5,
						maxlength : 25
					},
					password : {
						required : true,
						minlength : 6
					}
				},
				messages : {
					username : {
						required : "Vui lòng nhập tên đăng nhập",
						minlength : "Ít nhất 6 kí tự",
						maxlength : "Nhiều nhất 25 kí tự"
					},
					password : {
						required : "Vui lòng nhập mật khẩu",
						minlength : "Ít nhất 6 kí tự"
					}
				}
			});
		});
	</script>
</body>
</html>