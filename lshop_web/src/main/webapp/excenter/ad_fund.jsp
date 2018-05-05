<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
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
		<p><a href="promtManager!promtCodeList.action">我的推广码</a></p>
			<p><a href="/excenter/excenter!basic.action">个人资料</a></p>
			<p><a href="promtManager!getSettledList.action">结算查询</a></p>
			<!--<p id="p4"><a href="promtManager!getToolDetail.action">推广工具</a></p>	
			<p id="p5"><a href="promtManager!getFundRelate.action" class="choose">广告基金</a></p>  -->
			<p id="p6"  style="display:none"><a href="diffusionPB.html">推广百科</a></p>
		</div>
	</ul>
</div>
<!--主要内容-->
<div class="main_conten3">
	<!--广告基金-->
	<div class="ad_fund">
		<ul>
			<li>
				<p>我的推广码 <span class="f_orange">${couponCode}</span> (成功支付的订单才算有效推广订单，才可获得广告基金)</p>
				<p>推广台数： <span class="f_orange">${num}</span> 台</p>
				<p>可用广告基金： <span class="f_blue">${enablefund}</span> 美元</p>
			</li>
			<c:if test="${size>0}">
			<li>				
				<div class="ad_record">
					<p class="f_B">广告报销历史记录：</p>
					<table width="800" cellpadding="5" cellspacing="0">
						<tr bgcolor="#f2f2f2">
							<td>编号</td>
							<td>报销时间</td>
							<td>报销金额</td>
							<td>报销说明</td>
						</tr>
						<c:foreach items="${objList}" var="expensefund" varStatus="status">
						<tr>
						  <td>${status.count}</td>
						  <td>${expensefund[0]}</td>
						  <td>${expensefund[1]}</td>
						  <td>${expensefund[2]}</td>
					  </tr>
					  </c:foreach>
				  </table>
				  <ul class="page" style="float:right;">
				  <u:page href="/excenter/DiffusionCode/promtManager!getFundRelate.action?page.pageNum=@"></u:page>
				   <script type="text/javascript">
					<!--
					function gotoPage(num){
					   window.location.href="/excenter/DiffusionCode/promtManager!getFundRelate.action?page.pageNum="+num;
					
					}
					//-->
					
					
					</script>
				 
				</ul>	
				</div>
			</li>
			</c:if>
			<li>
				<p class="f_B">广告基金说明：</p>
				<p>设置广告基金的目的是鼓励推广者尝试进行广告的投放，帮助推广者获得更多收益，推广者每成功推广一台TVpad奖励<span class="f_orange f_B">10美金</span>的广告基金，不断累积，当推广者等级达到<span class="f_orange f_B">50台</span>及以上就可以申请广告基金；广告基金无法取现，仅用于广告费用的报销；可报销广告费用的<span class="f_orange f_B">50%</span>,但不超过积累的广告基金总额。</p>
			</li>
			<li>
				<p class="f_B">申请流程：</p>
				<p>1、<span class="f_red">推广者</span>发送邮件至<a href="mailto:market@tvpad.hk" style="color: blue">market@tvpad.hk</a>提交广告申请，邮件内容含以下内容：</p>
				<p style="padding-left:20px;">
				广告文案及内容<br />
				广告投放渠道<br />
				广告费用<br />
				广告日期区间<br />
				预估效果<br />
				</p>	
				<p>2、<span class="f_blue">TVpad 官方</span>进行审批，通过邮件进行回复。</p>
				<p>3、如申请通过，<span class="f_red">推广者</span>进行广告投放，影印合同附件，通过相机/复印等手段展示广告效果，提交给TVpad官方。</p>
				<p>4、<span class="f_blue">TVpad 官方</span>报销<span class="f_red">推广者</span>广告费用的50%,但不超过累积的广告基金金额。</p>
			</li>			
		</ul>
	</div>
  	<div class="clear_p"></div>
	<!--说明文字-->
  <div class="note">
  	说明：<br />
    当您推广等级达到VIP3及以上就可以申请使用广告基金进行广告投放，采用各种有效宣传方式为您带来更大的收益。报销广告费用后可申请的广告基金将扣除已报销的费用，已支取的广告基金增加，点击已支取的广告基金数据您可以查看过往的报销记录。如有任何疑问请邮件至market@tvpad.hk
  </div>
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
<div style="text-align:center;"><br/>
   <script language="javascript" type="text/javascript" src="http://js.users.51.la/7189009.js"></script>
   <noscript><a href="http://www.51.la/?7189009" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/7189009.asp" style="border:none" /></a></noscript>	
</div>	