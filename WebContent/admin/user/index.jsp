<%@page import="library.StringLibrary"%>
<%@page import="model.bean.Users"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%= request.getContextPath()%>/admin/user/add" class="button">
			<span>Thêm Người Dùng <img src="<%= request.getContextPath()%>/template/admin/images/plus-small.gif" alt="Thêm user"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh Sách Người Dùng</span></h2>
		<%
			Users admin1 = (Users) session.getAttribute("admin");
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
					
					if(request.getAttribute("listUser") != null){
						ArrayList<Users> listUser = (ArrayList<Users>)request.getAttribute("listUser");
				%>
				<thead>
					<tr>
						<th style="width:10%; text-align: center;">ID</th>
						<th style="width:30%">Tên Tài Khoản</th>
						<th style="width:40"> Họ Tên</th>
						<th style="width:20%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<%
					for(Users user:listUser){
					String urlClugUserEdit = request.getContextPath() + "/admin/user/edit/" + StringLibrary.makeSlug(user.getUsername()) 
					+ "-" + user.getId() +".html";
					String urlClugUserDelete = request.getContextPath() + "/admin/user/delete/" + StringLibrary.makeSlug(user.getUsername()) 
					+ "-" + user.getId() +".html";
						%>
				<tbody>
					<tr>
						<td class="align-center"><%= user.getId()%></td>
						<td><a href="<%= urlClugUserEdit%>"><%= user.getUsername()%></a></td>
						<td align="center"><%= user.getFullname() %></td>
						<%
							if(admin.getId_roles() == 1){
						%>
						<td align="center">
							<a href="<%= urlClugUserEdit%>">Sửa 
								<img src="<%= request.getContextPath()%>/template/admin/images/pencil.gif" alt="edit" />
							</a>
							<%
							if(user.getId() != admin.getId()){
							%>
							<a href="<%=urlClugUserDelete%>" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa
								<img src="<%= request.getContextPath()%>/template/admin/images/bin.gif" width="16" height="16" alt="delete" />
							</a>
							<%} %>
						</td>
						<%} else{%>
						<td align="center">
							<%
								if(user.getId() == admin.getId()){
							%>
							<a href="<%= urlClugUserEdit%>">Sửa 
								<img src="<%= request.getContextPath()%>/template/admin/images/pencil.gif" alt="edit" />
							</a>
							<%} %>
						</td>
						<%} %>
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