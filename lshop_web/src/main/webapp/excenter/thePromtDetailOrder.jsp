<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/excenter/common/header.jsp" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
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
			<p><a href="promtManager!promtCodeList.action" class="choose">我的推广码</a></p>
			<p><a href="/excenter/excenter!basic.action">个人资料</a></p>
			<p><a href="promtManager!getSettledList.action">结算查询</a></p>
			<!--<p id="p4"><a href="promtManager!getToolDetail.action">推广工具</a></p>	
			<p id="p5"><a href="promtManager!getFundRelate.action">广告基金</a></p>  -->
			<p id="p6"  style="display:none"><a href="diffusionPB.html">推广百科</a></p>
		</div>
	</ul>
</div>
<!--主要内容-->
<div class="main_conten3">
	<!--我的推广码-->
	<div class="my_Dcod">
		<ol class="f_B">推广码<span class="f_orange">${couponCode }</span> 的推广订单详细情况</ol>
		<ul>
			<li class="my_ht">
				<p class="ht01">买家姓名</p>
				<p class="ht02">推广码</p>
				<p class="ht03">成功使用时间</p>
				<p class="ht04">购买台数</p>
				<p class="ht05">买家所在地区</p>
				<p class="ht06">是否结算</p>	
				<p class="ht07">是否退货</p>			
			</li>
		    <c:foreach items="${objList}" var="obj">
			<li>
				<p class="ht01">${obj[0]}</p>
				<p class="ht02">${obj[1]}</p>
				<p class="ht03">${obj[2] }</p>
				<p class="ht04">${obj[3]}</p>
				<p class="ht05">${obj[4]}</p>
				<p class="ht06">
					<c:if test="${obj[5]!=1}">未结算</c:if>
					<c:if test="${obj[5]==1}">已结算</c:if>
			    </p>
			    <p class="ht07">
				     <c:if test="${obj[6]==3}">已退货</c:if>
				     <c:if test="${obj[6]!=3}">否</c:if>
				</p>
			</li>
			</c:foreach>
			
		</ul>
		<ul class="page" style="float:right;">
				  <u:page href="promtManager!promtOrderList.action?page.pageNum=@"></u:page>
				   <script type="text/javascript">
					<!--
					function gotoPage(num){
					   window.location.href="promtManager!promtOrderList.action?page.pageNum="+num;
					
					}
					//-->
					
					
					</script>
				 
				</ul>	
	</div>	
  <!--说明文字-->
  <div class="note">
  	说明：<br />
     是否结算状态：
     <span class="f_red">未结算</span>（未获得佣金的订单）
     <span class="f_red">已结算</span>（已完成支付佣金的订单的状态）
  </div> 
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
<div style="text-align:center;"><br/>
   <script language="javascript" type="text/javascript" src="http://js.users.51.la/7189009.js"></script>
   <noscript><a href="http://www.51.la/?7189009" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/7189009.asp" style="border:none" /></a></noscript>	
</div>