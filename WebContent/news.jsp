<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="template/public/inc/header.jsp"%>
<div class="leftpanel">
	<%@include file="template/public/inc/left_bar.jsp"%>
</div>
<div class="rightpanel">
	<div class="rightbody">
		<h1 class="title">Tin Tức</h1>
		<div class="items-new">
			<ul>
				<%
					int pages =  1;
					int total = 0;
				 	if (request.getAttribute("pages") != null) {
				        pages = Integer.parseInt((String)request.getAttribute("pages"));
				    }
				 	
					if (request.getAttribute("listNews") != null && request.getAttribute("total") != null) {
						ArrayList<News> listNews = (ArrayList<News>) request.getAttribute("listNews");
						total = Integer.parseInt((String)request.getAttribute("total"));
						for (int i = 0; i < listNews.size(); i++) {
							String urlClugdetail = request.getContextPath() + "/chi-tiet-tin/" + StringLibrary.makeSlug(listNews.get(i).getName()) 
							+ "-" + listNews.get(i).getId() +".html";
				%>
				<li>
					<h2>
						<a href="<%= urlClugdetail%>" title="">
							<%= listNews.get(i).getName()%>
						</a>
					</h2>
					<div class="item">
						<a href="<%= urlClugdetail%>" title="">
							<img src="<%=request.getContextPath() %>/files/<%= listNews.get(i).getPicture()%>"
							alt="" />
						</a>
						<p><%= listNews.get(i).getPreview()%></p>
						<div class="clr"></div>
					</div>
				</li>
				<%
					}
					}
				%>
			</ul>
			<ul>
				<%
					if (request.getAttribute("listNewsSearch") != null && request.getAttribute("total") != null) {
						ArrayList<News> list = (ArrayList<News>) request.getAttribute("listNewsSearch");
						total = Integer.parseInt((String)request.getAttribute("total"));
						for (int i = 0; i < list.size(); i++) {
							String urlClugdetail1 = request.getContextPath() + "/chi-tiet-tin/" + StringLibrary.makeSlug(list.get(i).getName()) 
							+ "-" + list.get(i).getId() +".html";
				%>
				<li>
					<h2>
						<a href="<%= urlClugdetail1%>" title="">
							<%= list.get(i).getName()%>
						</a>
					</h2>
					<div class="item">
						<a href="<%= urlClugdetail1%>" title="">
							<img src="<%=request.getContextPath() %>/files/<%= list.get(i).getPicture()%>"
							alt="" />
						</a>
						<p><%= list.get(i).getPreview()%></p>
						<div class="clr"></div>
					</div>
				</li>
				<%
					}
					}
				%>
			</ul>

			<div class="paginator">
				<%
				if(total > 5){
					int back = 1;
					if(pages == 0 || pages == 1){
						back = 1;
					}else{back = pages -1;}
					String urlClugback = request.getContextPath() + "/tin-tuc/pages-" + back +".html";
				%>
				<a style="text-decoration: none" href="<%= urlClugback%>" class="button_pages_back"> << </a>
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
            	
					String urlClug = request.getContextPath() + "/tin-tuc/pages-" + i +".html";
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

					String urlClugnext = request.getContextPath() + "/tin-tuc/pages-" + next +".html";
   			 %>
			<a style="text-decoration: none" href="<%= urlClugnext%>" class="button_pages_next">>></a>
			<%} %>
			</div>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="template/public/inc/footer.jsp"%>

