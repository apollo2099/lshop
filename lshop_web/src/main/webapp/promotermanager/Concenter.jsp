<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@include file="/promotermanager/common/header.jsp" %>
<link rel="stylesheet" type="text/css"  href="css/form.css">
<script type="text/javascript" src="js/request.js"></script>
<script type="text/javascript" src="js/form.js"></script>
<script type="text/javascript">
	function clearData(ob){
		if(ob.value==ob.defaultValue){
			ob.value="";
		}
	}
	function searcData(){
		document.searchForm.submit();
	}
</script>
<%@include file="top.jsp" %>
 
<div class="clear_p"></div>
<!--banner部份-->

<div class="banner02">
	<ul>
		<ol>人个管理中心</ol>
		<div class="concenter_nv">
			<p><a href="rankpromt!getPromtCodeList.action" class="choose">我的推广码</a></p>
			<p><a href="rankpromt!basic.action">个人资料</a></p>
			<p><a href="rankpromt!getSettledList.action">结算查询</a></p>
		</div>
	</ul>
</div>
<!--主要内容-->
<div class="main_conten3">
	<!--我的推广码-->
	<div class="my_Dcod">
	 <ol>
		<div id="content" class="left">
		  <c:if test="${couponNum==0}">
		              拥有<span class="f_orange f_B">${couponCodeNum}</span>个推广码：
	        <a href="#"><input name="提交" style="cursor:pointer;" class="button_06" value="申请推广码"/></a>
	      </c:if>
	      <c:if test="${couponNum>0}">
		              您这次提交的申请还未审核，请等待......
	         <input name="提交" style="cursor:pointer;" class="button_06" value="申请推广码" disabled="disabled"/>
	      </c:if>
	    </div>
<!--	<p class="right"><input type="text" value="搜索推广码" class="input02" /><input name="" type="button" class="button_07" /></p> -->
		<div class="right">
			<form name="searchForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="rankpromt!getPromtCodeList.action" method="post">
				<input type="text" value="搜索推广码" class="input02" id="couponCode" name="couponCode" onfocus="clearData(this);"/>
				<input name="" type="button" class="button_07" onclick="searcData();"/>
			</form>
		</div>
	 
		<div id="alert">
		  <form id="myform" method="post" target="excelUploadFrame">
				<h4>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="close">关闭</span>
				</h4>
				<p>
					<label>申请推广码个数数量</label>
					<input type="text" class="myinp"  name="num" id="num" onmouseover="this.style.border='1px solid #f60'"  onfocus="this.style.border='1px solid #f60'" onblur="this.style.border='1px solid #ccc'" />
				</p>
				<p style="text-align: center;">
					<input type="button" value="提交申请" class="sub" onclick="sendRequest();"/>
				</p>
			</form>
		</div>
		<iframe name="excelUploadFrame" width="0px;" height="0px;"></iframe>
	  	<script type="text/javascript" src="js/form.js"></script>
	 </ol> 
		<ul>
			<li class="my_ht">
				<p class="ht01">序列号</p>
				<p class="ht02">推广码</p>
				<p class="ht03">截止有效期</p>
				<p class="ht04">领取时间</p>
				<p class="ht05">成交订单数</p>
				<p class="ht06">推广台数</p>
				<p class="ht07">佣金累积</p>
				<p class="ht08">操作</p>
			</li>
			<c:foreach items="${objList}" var="obj" varStatus="status">           
			<li>
				<p class="ht01"> ${status.count}</p>
				<p class="ht02">${obj[0]}</p>
				<p class="ht03">${obj[1]}</p>
				<p class="ht04">${obj[2]}</p>
				<p class="ht05">${obj[3]}</p>
				<p class="ht06">
				<c:if test="${not empty obj[4]}">${obj[4]}</c:if><c:if test="${empty obj[4]}">0</c:if></p>
				<p class="ht07">${obj[5]}美元</p>
				<p class="ht08"><a href="rankpromt!getInfo.action?code=${obj[0]}">详细信息</a></p>
			</li>
		  </c:foreach>
	  </ul>
	  <ul class="page" style="float:right;">
				  <u:page href="/promotermanager/rankpromt!getPromtCodeList.action?page.pageNum=@"></u:page>
				   <script type="text/javascript">
					<!--
					function gotoPage(num){
					   window.location.href="/promotermanager/rankpromt!getPromtCodeList.action?page.pageNum="+num;
					
					}
					//-->
					
					
					</script>
				 
				</ul>	
	</div>
	
		<!--我的推广码结算列表-->
  <div class="my_Dcod02">
    <table cellspacing="1" bgcolor="#bcbcbc" >
    <tr>
        <td width="235" bgcolor="#F2F2F2" class="f_B">推广业绩</td>
        <td width="521" style="text-align:left; text-indent:20px;">总推广台数</td>
        <td width="210">${totaNum}台</td>
      </tr>
      <tr>
        <td width="235" rowspan="2" bgcolor="#F2F2F2">未结算</td>
        <td width="521" style="text-align:left; text-indent:20px;" class="f_blue">可申请结算的佣金</td>
        <td width="210">${settlementAmount}美元</td>
      </tr>
      <tr>
        <td style="text-align:left; text-indent:20px;" class="f_orange">未满足15天条件的佣金</td>
        <td>${nonSettlementAmount}美元</td>
      </tr>
      <tr>
        <td bgcolor="#F2F2F2">已结算</td>
        <td style="text-align:left; text-indent:20px;">已支付佣金</td>
        <td>${settlementedAmount}美元</td>
      </tr>
    </table>
	  <div>
	    <form action="rankpromt!payRequest.action">
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
  	说明：如需申请推广码请点击<span class="f_red">“申请推广码”</span>按扭提交申请，申请数量请<span class="f_red">酌情填写</span>，审请通过后系统将自动发送邮件通知。
  </div>
  
</div>
<!--版权-->
<%@include file="/promotermanager/common/footer.jsp" %>