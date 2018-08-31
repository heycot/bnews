<%@page import="library.StringLibrary"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%= request.getContextPath()%>/admin/news/add" class="button">
			<span>Thêm tin <img src="<%= request.getContextPath()%>/template/admin/images/plus-small.gif" alt="Thêm tin"></span>
		  </a>
	  </div>
	  
	<div class="search">
		<form action="<%=request.getContextPath()%>/admin/search" method="post">
			<input class="inputsearch" type="text" name="search" value=""
				placeholder="search something here">
		</form>
	</div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách tin</span></h2>
		<%
			if(request.getParameter("kq" ) != null){
				int kq = Integer.parseInt(request.getParameter("kq"));
				switch(kq){
				case 1: out.print("<strong style='color: red'>Thêm thành công</strong>"); break;
				case 2: out.print("<strong style='color: red'>Sửa thành công</strong>"); break;
				case 3: out.print("<strong style='color: red'>Xóa thành công</strong>"); break;
				default: out.print("<strong style='color: red'>Lỗi. không xóa được</strong>");
				}
			}
		%>
		<div class="module-table-body">
			<form action="">
			<table id="myTable" class="tablesorter">
				<%
				int total = 0, pages = 1;
				if(request.getAttribute("pages") != null){
					pages = Integer.parseInt((String)request.getAttribute("pages"));
				}
				if(request.getAttribute("total") != null){
					total = Integer.parseInt((String)request.getAttribute("total"));
				}
				if(request.getAttribute("listNews") != null){
					ArrayList<News> listNews = (ArrayList<News>)request.getAttribute("listNews");
				%>
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th>Tên Tin Tức</th>
						<th style="width:20%">Danh mục</th>
						<th style="width:16%; text-align: center;">Hình ảnh</th>
						<th style="width:11%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<%
					for(News news:listNews){
					String urlClugNewsEdit = request.getContextPath() + "/admin/news/edit/" + StringLibrary.makeSlug(news.getName()) 
					+ "-" + news.getId() +".html";
					String urlClugNewsDelete = request.getContextPath() + "/admin/news/delete/" + StringLibrary.makeSlug(news.getName()) 
					+ "-" + news.getId() +".html";
						%>
				<tbody>
					<tr>
						<td class="align-center"><%= news.getId() %></td>
						<td><a href="<%= urlClugNewsEdit%>"><%= news.getName()%></a></td>
						<td><%= news.getCname()%></td>
						<td align="center"><img src="<%= request.getContextPath()%>/files/<%= news.getPicture()%>" class="hoa" /></td>
						<td align="center">
							<a href="<%= urlClugNewsEdit%>">Sửa <img src="<%= request.getContextPath()%>/template/admin/images/pencil.gif" alt="edit" /></a>
							<a href="<%= urlClugNewsDelete%>">Xóa <img src="<%= request.getContextPath()%>/template/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
						</td>
					</tr>
				</tbody>
				<%	}
					}
				if(request.getAttribute("listNewsSearch") != null){
					ArrayList<News> list = (ArrayList<News>)request.getAttribute("listNewsSearch");
					%>
					<thead>
						<tr>
							<th style="width:4%; text-align: center;">ID</th>
							<th>Tên Tin Tức</th>
							<th style="width:20%">Danh mục</th>
							<th style="width:16%; text-align: center;">Hình ảnh</th>
							<th style="width:11%; text-align: center;">Chức năng</th>
						</tr>
					</thead>
					<%
						for(News news:list){
							String urlClugNewsEdit = request.getContextPath() + "/admin/user/edit/" + StringLibrary.makeSlug(news.getName()) 
							+ "-" + news.getId() +".html";
							String urlClugNewsDelete = request.getContextPath() + "/admin/user/delete/" + StringLibrary.makeSlug(news.getName()) 
							+ "-" + news.getId() +".html";
							%>
					<tbody>
						<tr>
							<td class="align-center"><%= news.getId() %></td>
							<td><a href="<%= urlClugNewsEdit%>"><%= news.getName()%></a></td>
							<td><%= news.getCname()%></td>
							<td align="center"><img src="<%= request.getContextPath()%>/files/<%= news.getPicture()%>" class="hoa" /></td>
							<td align="center">
								<a href="<%= urlClugNewsEdit%>">Sửa <img src="<%= request.getContextPath()%>/template/admin/images/pencil.gif" alt="edit" /></a>
								<a href="<%= urlClugNewsDelete%>">Xóa <img src="<%= request.getContextPath()%>/template/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
							</td>
						</tr>
					</tbody>
					<%	}}%>
			</table>
			</form>
		 </div> <!-- End .module-table-body -->
	</div> <!-- End .module -->
		 <div class="pagination">           
			<%
			if(total > 5){
				int back = 1;
				if(pages >= 2){
					back = pages -1;
				}
				String urlClugback = request.getContextPath() + "/admin/news/pages-" + back +".html";
				
			%>
			<a style="text-decoration: none" href="<%= urlClugback%>" class="button_pages_back"><<</a>
			<%
			int endpages = (int)Math.ceil((float)total/5);
			int start, end;
			if( pages == 1){
				start = 1;
				end = 3;
			}else if(pages == endpages){
				start = pages - 2;
				end = pages;
			}else{
				start = pages - 1;
				end = pages + 1;
			}
			
        	//Lap so pages
        	for (int i = start; i <= end; i++) {

				String urlClug = request.getContextPath() + "/admin/news/pages-" + i +".html";
       			 if (pages == i) {%> 
				<span><a style="text-decoration: none; color: red" href="<%= urlClug%>"><%=i%></a></span>
        		<%} else {%>
				<a style="text-decoration: none" href="<%= urlClug%>"><%=i%></a>

				<%}}%>
					 <%
             	int next = pages;
 				if(pages < endpages){
 					next = pages + 1;
 				}
				String urlClugnext = request.getContextPath() + "/admin/news/pages-" + next +".html";
			 %>
		<a style="text-decoration: none" href="<%= urlClugnext%>" class="button_pages_next">>></a>
		<%} %>
			<div style="clear: both;"></div> 
		 </div>
	
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 