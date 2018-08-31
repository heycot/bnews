<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="template/public/inc/header.jsp"%>
<div class="leftpanel">
	<%@include file="template/public/inc/left_bar.jsp"%>
</div>
<div class="rightpanel">
	<div class="rightbody">
		<%
			if (request.getAttribute("news") != null) {
				News news = (News) request.getAttribute("news");
		%>

		<h1 class="title"><%=news.getName()%></h1>
		<div class="items-new">
		<div style="width: 480; height: 260">
			<img width=480 height=260 src="<%=request.getContextPath()%>/files/<%=news.getPicture()%>" alt="Trung Quốc điều thêm 17 tàu đến khu vực giàn khoan">
		</div>
		<div class="new-detail">
				<p><%=news.getDetail()%></p>
			</div>
		</div>
		<%
			}
		%>
		
		<script type="text/javascript" src="https://apis.google.com/js/plusone.js" ></script>
		<g:plusone size="medium" ></g:plusone>
		<div class="fb-share-button" data-href="http://localhost:8080/bnews-tulam/chi-tiet-tin?nid=63" data-layout="button_count" data-size="small" data-mobile-iframe="true">
			<a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A8080%2Fbnews-tulam%2Fchi-tiet-tin%3Fnid%3D63&amp;src=sdkpreparse">
			Chia sẻ
			</a>
		</div>
		<h2 class="title" style="margin-top: 30px; color: #BBB">Tin tức
			liên quan</h2>
		<div class="items-new">
			<ul>
				<%
					if (request.getAttribute("listNews") != null) {
						ArrayList<News> list = (ArrayList<News>) request.getAttribute("listNews");
						for (int i = 0; i < list.size(); i++) {
							String urlClugdetail = request.getContextPath() + "/chi-tiet-tin/" + StringLibrary.makeSlug(list.get(i).getName()) 
							+ "-" + list.get(i).getId() +".html";
				%>
				<li>
					<h2>
						<a
							href="<%= urlClugdetail%>"
							title="<%=list.get(i).getName()%>"><%=list.get(i).getName()%></a>
					</h2>
					<div class="item">
						<a
							href="<%= urlClugdetail%>"
							title="<%=list.get(i).getName()%>"><img
							src="<%=request.getContextPath()%>/files/<%=list.get(i).getPicture()%>"
							alt=""></a>
						<p><%=list.get(i).getPreview()%></p>
						<div class="clr"></div>
					</div>
				</li>
				<%
					}
					}
				%>
			</ul>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="template/public/inc/footer.jsp"%>
