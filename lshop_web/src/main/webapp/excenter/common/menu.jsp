<%@ page language="java"  pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function (){
	   var users=lshop.getCookieToJSON('exuser');
		if(users.userType==1){
		   $("#p6").hide();
		}else{
		   $("#p6").show();
		}
}
);
</script>
<div class="banner02">
 <% String p=request.getParameter("p"); %>
	<ul>
		<ol>人个管理中心</ol>
		<div class="concenter_nv">
			<p><a href="promtManager!promtCodeList.action?uid=${uid}">我的推广码</a></p>
			<p><a href="/excenter/excenter!basic.action" <% if("profile".equals(p)){%> class="choose" <%} %>>个人资料</a></p>
			<p><a href="promtManager!getSettledList.action">结算查询</a></p>
			<!--<p id="p4"><a href="promtManager!getToolDetail.action">推广工具</a></p>	
			<p id="p5"><a href="promtManager!getFundRelate.action">广告基金</a></p>  -->
			<p id="p6"  style="display:none"><a href="diffusionPB.html#flow">推广百科</a></p>
		</div>
	</ul>
</div>