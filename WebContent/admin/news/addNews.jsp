<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Thêm tin tức</span></h2>
		<%
			if(request.getAttribute("error") != null){
		%>
			<h5 style="color: red"><%= (String) request.getAttribute("error")%></h5>
		<%	}
		%>
		 <div class="module-body">
			<form action="<%= request.getContextPath()%>/admin/news/add" enctype="multipart/form-data" class="form" method="post">
				<p>
					<label>Tên tin</label>
					<input type="text" name="tentin" value="" class="input-medium" />
				</p>
				<p>
					<label>Danh mục tin</label>
					<select  name="danhmuc" class="input-short">
					<%
					if(request.getAttribute("listcat") != null){
					ArrayList<Category> listcat = (ArrayList<Category>)request.getAttribute("listcat");
					if(listcat.size() > 0){
						for(Category cat:listcat){
							%>
						<option value="<%=cat.getId()%>"><%= cat.getName()%></option>
					<%	}
					}}
					%>
					</select>
				</p>
				<p>
					<label>Hình ảnh</label>
					<input type="file"  name="hinhanh" value="" />
				</p>
				<p>
					<label>Mô tả</label>
					<textarea name="mota" value="" rows="7" cols="90" class="input-medium" id="motaeditor"  class="ckeditor"></textarea>
				</p>
				<p>
					<label>Chi tiết</label>
					<textarea  name="chitiet" value="" rows="7" cols="90" class="input-long" id="chitieteditor"  class="ckeditor"></textarea>
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Sửa" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập Lại" />
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
	<script type="text/javascript">
	//CKEDITOR.replace('motaeditor');
	//CKEDITOR.replace('chitieteditor');
		$().ready(function(){
			$(".form").validate({
				ignore: [],
             	debug: false, 
				rules: {
					tentin: {
						required: true,
						minlength: 6,
						maxlength: 100
					},
					danhmuc: {
						required: true
					},
					mota:{
						required: true,
						maxlength: 250,
					},
					chitiet: {
						required: true,
						maxlength: 1500
					}
				},
				messages: {
					tentin: {
						required: "Vui lòng nhập tên tin",
						minlength: "Ít nhất 6 kí tự",
						maxlength: "Nhiều nhất 100 kí tự"
					},
					danhmuc: {
						required: "Vui lòng chọn danh mục"
					},
					mota:{
						required: "Vui lòng nhập mô tả",
						maxlength: "Nhiều nhất 250 kí tự",
					},
					chitiet: {
						required: "Vui lòng nhập chi tiết tin",
						maxlength: "Nhiều nhất 1500 kí tự"
					}
				}
			});
		});
	</script>
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 