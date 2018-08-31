<%@page import="library.StringLibrary"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%= request.getContextPath()%>/admin/cat/add" class="button">
			<span>Thêm Danh Mục <img src="<%= request.getContextPath()%>/template/admin/images/plus-small.gif" alt="Thêm danh mục"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh Sách Danh Mục</span></h2>
		<%
			if(request.getParameter("kq") != null){
				int kq = Integer.parseInt(request.getParameter("kq"));
				switch(kq){
				case 1: out.print("<span style='color: red'>Thêm thành công!"); break;
				case 2: out.print("<span style='color: red'>Sửa thành công!"); break;
				case 3: out.print("<span style='color: red'>Xóa thành công!"); break;
				default: out.print("<span style='color: red'>Xảy ra lỗi"); break;
				}
			}
		%>
		
		<div class="module-table-body">
			<form action="">
			<table id="myTable" class="tablesorter">
				<%
					if(request.getAttribute("listCat") != null){
						ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
				%>
				<thead>
					<tr>
						<th style="width:10%; text-align: center;">ID</th>
						<th style="width:60%">Tên Danh Mục</th>
						<th style="width:20%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<%
					for(Category cat:listCat){
						String urlClugCatEdit = request.getContextPath() + "/admin/user/edit/" + StringLibrary.makeSlug(cat.getName()) 
						+ "-" + cat.getId() +".html";
						String urlClugCatDelete = request.getContextPath() + "/admin/user/delete/" + StringLibrary.makeSlug(cat.getName()) 
						+ "-" + cat.getId() +".html";
						%>
				<tbody>
					<tr>
						<td class="align-center"><%= cat.getId() %></td>
						<td><a href="<%= urlClugCatEdit%>"><%= cat.getName()%></a></td>
						<td align="center">
							<a href="<%= urlClugCatEdit%>">Sửa 
								<img src="<%= request.getContextPath()%>/template/admin/images/pencil.gif" alt="edit" />
							</a>
							<a href="<%= urlClugCatDelete%>" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa
								<img src="<%= request.getContextPath()%>/template/admin/images/bin.gif" width="16" height="16" alt="delete" />
							</a>
						</td>
					</tr>
				</tbody>
				<%	}
					}
				%>
			</table>
			</form>
		 </div> <!-- End .module-table-body -->
	</div> <!-- End .module -->
	
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 