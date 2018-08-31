<%@page import="model.bean.Users"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp"%>
<!-- Form elements -->
<div class="grid_12">

	<div class="module">
		<h2>
			<span>Sửa Người Dùng</span>
		</h2>
		<%
			if (request.getAttribute("error") != null) {
				String error = (String) request.getAttribute("error");
		%>
		<h4 style="color: red"><%= error%></h4>
		<%
		}
		%>
		<div class="module-body">
			<%
				String name = "";
				int id = -1;
				String fullname = "";
				if (request.getAttribute("user") != null) {
					Users user = (Users) request.getAttribute("user");
					name = user.getUsername();
					id = user.getId();
					fullname = user.getFullname();
				}
			%>
			<form action="<%=request.getContextPath()%>/admin/user/edit?uid=<%= id%>" method="post" class="form">
				<p>
					<label>Tên Người Dùng</label> 
					<input type="text" name="username" value="<%=name%>" class="input-medium"  readonly/>
				</p>
				<p>
					<label>Password</label>
					<input type="password" name="password" id="password" value="" class="input-medium" />
				</p>
				<p>
					<label>Xác nhận password</label>
					<input type="password" name="confirmPassword" value="" class="input-medium" />
				</p>
				<p>
					<label>Fullname</label>
					<input type="text" name="fullname" value="<%= fullname%>" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="sửa" />
					<input class="submit-gray" name="reset" type="reset" value="nhập lại" />
				</fieldset>
			</form>
		</div>
		<!-- End .module-body -->

	</div>
	<!-- End .module -->
	<div style="clear: both;"></div>
	<script type="text/javascript">
		$().ready(function(){
			$(".form").validate({
				rules: {
					username: {
						required: true,
						minlength: 5,
						maxlength: 25
					},
					password: {
						required: true,
						minlength: 6,
						maxlength: 10
					},
					confirmPassword:{
						equalTo: "#password",
						minlength: 6,
						maxlength: 10
					},
					fullname: {
						required: true
					}
				},
				messages: {
					username: {
						required: "Vui lòng nhập username",
						minlength: "Ít nhất 5 kí tự",
						maxlength: "Nhiều nhất 25 kí tự"
					},
					password: {
						required: "Vui lòng nhập password",
						minlength: "Ít nhất 6 kí tự",
						maxlength: "Nhiều nhất 10 kí tự"
					},
					confirmPassword:{
						equalTo: "#password",
						minlength: "Ít nhất 6 kí tự",
						maxlength: "Nhiều nhất 10 kí tự"
					},
					fullname: {
						required: "Vui lòng nhập họ tên"
					}
				}
			});
		});
	</script>
</div>
<!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp"%>
