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
			<!--<p id="p4"><a href="diffusionPB.html">推广百科</a></p>
			<p id="p5"><a href="promtManager!getToolDetail.action">推广工具</a></p>	  -->
			<p id="p6"  style="display:none"><a href="promtManager!getFundRelate.action">广告基金</a></p>
		</div>
	</ul>
</div>
<!--主要内容-->
<div class="main_conten3">
	<!--我的推广码-->
	<div class="check">
	   <c:if test="${userType==0}">
		<ul>
		  <c:if test="${presentRate=='1'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP1</span>(已结算台数1-10台)：每台返 15美金 </li>
			<li>下一级<span class="f_orange f_B"> VIP2 </span>(已结算台数11-50台)  每台返20美金 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad，还差<span class="f_blue f_B"> ${rateNum} </span>台即可升级 </li>	
		  </c:if>	
		  <c:if test="${presentRate=='2'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP2</span>(已结算台数11-50台)：每台返 20美金 </li>
			<li>下一级<span class="f_orange f_B"> VIP3 </span>(已结算台数51-200台)  每台返25美金 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad，还差<span class="f_blue f_B"> ${rateNum} </span>台即可升级 </li>	
		  </c:if>	
		  <c:if test="${presentRate=='3'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP3</span>(已结算台数51-200台)：每台返 25美金 </li>
			<li>下一级<span class="f_orange f_B"> VIP4 </span>(已结算台数201-1000台)  每台返30美金 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad，还差<span class="f_blue f_B"> ${rateNum} </span>台即可升级 </li>	
		  </c:if>	
		  <c:if test="${presentRate=='4'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP4</span>(已结算台数201-1000台)：每台返 30美金 </li>
			<li>下一级<span class="f_orange f_B"> VIP5 </span>(已结算台数1001-台)  每台返35美金 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad，还差<span class="f_blue f_B"> ${rateNum} </span>台即可升级 </li>	
		  </c:if>	
		  <c:if test="${presentRate=='5'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP5</span>(已结算台数1001-台)：每台返 35美金 </li>
			<li><span class="f_orange f_B"> </span>您已达到最高级别 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad </li>	
		  </c:if>	
		</ul>
		<ul>
			<li class="f_B">结算规则说明： </li>
			<li>假如结算时您是VIP1，还有 2 台即可升级，目前有 8 台佣金待结算。 </li>
			<li>那么您这次申请结算的实际金额为：</li>
			<li>2 * 15 + 6 * 20 = 150  </li>
		</ul>
		</c:if>
		<c:if test="${userType==1}">
		  <ul>
		   <li>特殊合作伙伴：每台返<span class="f_orange">${specialAmount}</span>美金</li>
		   <li>推广码有效日期：${createTime}至${validitydate}</li>
		  </ul>
		</c:if>
		<form action="promtManager!payRequest.action">
		  <p>
		     <input name="" type="submit" value="确认,提交结算申请"  class="button_01" />
		  </p>
		</form>
	</div>
	
  <!--说明文字-->
  <div class="note">
  	注：实际可结算金额，以结算支付时系统计算的可结算金额为准
  </div> 
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
	
