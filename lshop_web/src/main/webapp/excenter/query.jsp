<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/excenter/common/header.jsp" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!--顶部信息-->
<%@include file="/excenter/common/top.jsp" %>
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
<div class="clear_p"></div>

<!--banner部份-->
<div class="banner02">
	<ul>
		<ol>人个管理中心</ol>
		<div class="concenter_nv">
			<p><a href="promtManager!promtCodeList.action">我的推广码</a></p>
			<p><a href="/excenter/excenter!basic.action">个人资料</a></p>
			<p><a href="promtManager!getSettledList.action" class="choose">结算查询</a></p>
			<!--<p id="p4"><a href="promtManager!getToolDetail.action">推广工具</a></p>	
			<p id="p5"><a href="promtManager!getFundRelate.action">广告基金</a></p>  -->
			<p id="p6"  style="display:none"><a href="diffusionPB.html">推广百科</a></p>
		</div>
	</ul>
</div>
<!--主要内容-->
<div class="main_conten3">
	<!--结算历史记录-->
	<div class="my_Dcod query">
		<ol class="f_B">结算历史记录</ol>
		<ul>
			<li class="my_ht">
				<p class="ht01">序列号</p>
			<!--<p class="ht02">申请时间</p>   -->	
				<p class="ht03">结算处理时间</p>
				<p class="ht04">结算台数</p>
				<p class="ht05">结算金额</p>
				<p class="ht06">结算状态</p>
				<p class="ht07">详细</p>
			</li>
			<c:foreach items="${objList}" var="log" varStatus="status">            
			<li>
				<p class="ht01">
				  ${status.count }
				</p>
			<!--<p class="ht02"></p> -->
				<p class="ht03">${log[0]}</p>
				<p class="ht04">${log[1]}</p>
				<p class="ht05">${log[2]}美元</p>
				<p class="ht06">已结算</p>
				<p class="ht07"><a href="promtManager!getDetailList.action?balanceId=${log[4]}&couponCode=${couponCode }">明细</a></p>
			</li>
			</c:foreach>
		</ul>
		<ul class="page" style="float:right;">
				  <u:page href="promtManager!getSettledList.action?page.pageNum=@"></u:page>
				   <script type="text/javascript">
					<!--
					function gotoPage(num){
					   window.location.href="promtManager!getSettledList.action?page.pageNum="+num;
					
					}
					//-->
					
					
					</script>
				 
				</ul>	
	</div>
  <!--说明文字-->
  <div class="note">
  	说明：<br />
     1、结算状态：未结算（平台还未支付）  已结算（平台已支付）<br />     2、查看明细，显示当前结算申请的订单明细
  </div> 
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
<div style="text-align:center;"><br/>
   <script language="javascript" type="text/javascript" src="http://js.users.51.la/7189009.js"></script>
   <noscript><a href="http://www.51.la/?7189009" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/7189009.asp" style="border:none" /></a></noscript>	
</div>