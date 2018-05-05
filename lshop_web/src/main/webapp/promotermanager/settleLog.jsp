<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/promotermanager/common/header.jsp" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<link rel="stylesheet" type="text/css"  href="css/form.css">
<script type="text/javascript" src="js/request.js"></script>
<script type="text/javascript" src="js/form.js"></script>
<!--顶部信息-->
<%@include file="top.jsp" %>
<div class="clear_p"></div>
<!--banner部份-->
<div class="banner02">
	<ul>
		<ol>人个管理中心</ol>
		<div class="concenter_nv">
			<p><a href="rankpromt!getPromtCodeList.action">我的推广码</a></p>
			<p><a href="rankpromt!basic.action">个人资料</a></p>
			<p><a href="rankpromt!getSettledList.action" class="choose">结算查询</a></p>
		</div>
	</ul>
</div>
<!--主要内容-->
<div class="main_conten3">
	<!--结算历史记录-->
	<div class="my_Dcod">
		<ol class="f_B">结算历史记录</ol>
		<ul>
			<li class="my_ht">
				<p class="ht01">序列号</p>
				<p class="ht03">结算处理时间</p>
				<p class="ht02">结算台数</p>
				<p class="ht04">结算金额</p>
				<p class="ht05">结算状态</p>
			</li>
			<c:foreach items="${objList}" var="log" varStatus="status">            
			<li>
				<p class="ht01">
				  ${status.count }
				</p>
				<p class="ht03">${log[0]}</p>
				<p class="ht02">${log[1]}</p>
				<p class="ht04">${log[2]}美元</p>
				<p class="ht05">已结算</p>
			</li>
			</c:foreach>
		</ul>
		<ul class="page" style="float:right;">
				  <u:page href="/promotermanager/rankpromt!getSettledList.action?page.pageNum=@"></u:page>
				   <script type="text/javascript">
					<!--
					function gotoPage(num){
					   window.location.href="/promotermanager/rankpromt!getSettledList.action?page.pageNum="+num;
					
					}
					//-->
					
					
					</script>
				 
				</ul>	
	</div>
  <!--说明文字-->
  <div class="note">
  	说明：<br />
     1、结算状态：未结算（平台还未支付）  已结算（平台已支付）
  </div> 
</div>
<!--版权-->
<%@include file="/promotermanager/common/footer.jsp" %>
