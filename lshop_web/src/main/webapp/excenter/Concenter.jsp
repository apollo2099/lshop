<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@include file="/excenter/common/header.jsp" %>
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
	</ul></div>
<!--主要内容-->
<div class="main_conten3">
	<!--我的推广码-->
	<div class="my_Dcod">
		<ol>我的推广码<span class="f_orange">${couponCode}</span>（成功支付的订单才算有效推广订单，15天后推广者才可申请佣金）</ol>
		<ul>
			<li class="my_ht">
				<p class="ht01">序列号</p>
				<p class="ht02">推广码</p>
				<p class="ht03">截止有效期</p>
				<p class="ht04">领取时间</p>
				<p class="ht05">使用次数</p>
				<p class="ht06">佣金累计收益</p>
				<p class="ht07">详细</p>
			</li>
			<li>
			   <c:if test="${mark==1}">
				<p class="ht01">01</p>
				<p class="ht02">${obj[0]}</p>
				<p class="ht03"><s:date name="#request.obj[1]" format="yyyy-MM-dd HH:mm:ss" /></p>
				<p class="ht04"><s:date name="#request.obj[2]" format="yyyy-MM-dd HH:mm:ss" /></p>
				<p class="ht05">${obj[3]}</p>
				<p class="ht06">${obj[4]}美元</p>
				<p class="ht07"><a href="promtManager!promtOrderList.action">订单详细</a></p>
				</c:if>
			</li>
		</ul>
	</div>
	<!--我的推广码结算列表-->
  <div class="my_Dcod02">
    <table cellspacing="1" bgcolor="#bcbcbc" >
      <tr>
        <td width="235" rowspan="2" bgcolor="#F2F2F2">未结算</td>
        <td width="521" style="text-align:left; text-indent:20px;" class="f_blue">可申请结算的佣金</td>
        <td width="210"><a href="promtManager!getSettleOrderList.action" class="link02">${settlementAmount}美元</a></td>
      </tr>
      <tr>
        <td style="text-align:left; text-indent:20px;" class="f_orange">未满足15天条件的佣金</td>
        <td><a href="promtManager!getNsettleOrderList.action" class="link03">${nonSettlementAmount}美元</a></td>
      </tr>
      <tr>
        <td bgcolor="#F2F2F2">已结算</td>
        <td style="text-align:left; text-indent:20px;">已支付佣金</td>
        <td>${settlementedAmount}美元</td>
      </tr>
    </table>
	  <div>
	    <form action="promtManager!befPayRequest.action">
	     <p>
	       <c:if test="${settlementAmount>0 and settlementStatus==0}">
	          <input name="" type="submit"  value="提交结算申请" class="button_01" />
	       </c:if>
	        <c:if test="${settlementAmount<=0 or settlementStatus!=0}">
	          <input name="" type="submit"  value="提交结算申请" class="button_01" disabled="disabled"/>
	       </c:if>
	      </p>
	    </form>
	  </div>
  </div>
  <!--说明文字-->
  <div class="note">
  	说明：当您提交申请后，将在最近的一个周三对截止当天所有可申请结算佣金进行审核处理。 申请受理后，可申请的佣金归0，该笔费用将计入已支付佣金， 请在5个工作日后查账。
  </div> 
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
<div style="text-align:center;">
<br/>
   <script language="javascript" type="text/javascript" src="http://js.users.51.la/7189009.js"></script>
   <noscript><a href="http://www.51.la/?7189009" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/7189009.asp" style="border:none" /></a></noscript>	
</div>