<%@ page language="java"  pageEncoding="UTF-8"%>
<div class="banner02">
 <% String p=request.getParameter("p"); %>
	<ul>
		<ol>人个管理中心</ol>
		<div class="concenter_nv">
			<p><a href="rankpromt!getPromtCodeList.action">我的推广码</a></p>
			<p><a href="rankpromt!basic.action" <% if("profile".equals(p)){%> class="choose" <%} %>>个人资料</a></p>
			<p><a href="rankpromt!getSettledList.action">结算查询</a></p>
		</div>
	</ul>
</div>