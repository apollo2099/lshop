<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<head>
<%@include file="/web/mtmcn/user_center/c_meta.jsp"%>	
<title>TVpad商城_充值卡充值</title>

<script type="text/javascript">
$(function(){
	var cardJson=lshop.getCookieToJSON("cardSuccess");

	$("#vbId").text(cardJson['amount']);
	$("#ordernumid").text(cardJson['orderno']);
	$("#accId").text(cardJson['otherAccount']);//帳號
});
</script>
</head>
<body>
<%@include file="/web/mtmcn/user_center/c_top.jsp"%>

<article>
  <div class="subminsucess">
    <div class="sucesstrue">
       
    </div>
    <div class="sucwwtip" style="text-align:center; line-height:50px">
       <span style="color:#37a008; font-size:24px"> 充值成功！</span><br/>
       订单号:<span class="ord" id="ordernumid"></span><br/>
         账号:<span class="ord" id="accId"></span><br/>
       充值金额:<span class="ord" id="vbId"></span> V币
    </div>
  </div>
  
  <div class="goshop">
    <a href="/web/mtmcn/recharge/card.jsp" style="background-color:#0099ff">返回</a>
  </div> 
   
</article>

<article>
  <div style="height:190px">
  
  </div>
</article>


<%@include file="/web/mtmcn/user_center/c_bottom.jsp"%>
<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '充值卡充值';
</script>
</body>
</html>
