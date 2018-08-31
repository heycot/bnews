<%@page import="library.StringLibrary"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h2>Danh mục tin</h2>
<ul>
	<%
		CategoryDao cd = new CategoryDao();
		ArrayList<Category> listCat = cd.getCats(); 
		if (listCat != null) {
			for (Category cat : listCat) {

			String urlSlug = request.getContextPath() + "/danh-muc-tin/" + StringLibrary.makeSlug(cat.getName()) + "-" + cat.getId() + ".html";
	%>
	<li class="danhmuc"><a href="<%=urlSlug%>"><%= cat.getName() %></a></li>

	<%
		}
		}
	%>
</ul>
