<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Thêm Danh Mục Tin</span></h2>
		<%
			if(request.getAttribute("error") != null){
				String error = (String)request.getAttribute("error");
		%>
			<h5 style="color: red"><%= error%></h5>		
		<%	}
		%>
		 <div class="module-body">
			<form action="<%= request.getContextPath()%>/admin/cat/add" class="form" method="post">
				<p>
					<label>Tên Danh Mục Tin</label>
					<input type="text" name="tendanhmuc" value="" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập Lại" />
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
	<script type="text/javascript">
		$().ready(function(){
			$(".form").validate(function(){
				rules: {
					tendanhmuc: {
						required: true
					},
				},
				messages: {
					tendanhmuc: {
						required: "Vui lòng nhập tên danh mục"
					}
				}
			});
		});
	</script>
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 
