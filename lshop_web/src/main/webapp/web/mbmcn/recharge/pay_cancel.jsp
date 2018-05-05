<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>	
<title>banana商城_充值卡充值</title>

</head>

<body>
<%@include file="/web/mbmcn/user_center/c_top.jsp"%>

<article>
  <div class="subminsucess">
    <div class="sucesstrue">
       
    </div>
    <div class="sucwwtip" style="text-align:center">
       <span style="color:#37a008; font-size:24px">您已取消订单 ${charge.rnum} 的支付交易！</span>
    </div>
  </div>
  
  <div class="goshop">
    <a href="/web/recharge!toPay.action?rnum=${charge.rnum}&paymethod=${charge.rtype}" style="background-color:#0099ff">重新支付</a>
  </div> 
  <div class="goshop">
    <a href="/web/recharge!list.action" style="background-color:#0099ff">返回</a>
<!--     <a href="/web/mbmcn/recharge/online.jsp" style="background-color:#0099ff">返回</a> -->
  </div> 
   
</article>

<article>
  <div style="height:190px">
  
  </div>
</article>

<!-- footer-->
<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '在线充值';
</script>
</body>
</html>