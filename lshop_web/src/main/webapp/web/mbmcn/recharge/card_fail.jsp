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
    <div class="sucesstrue1">
       
    </div>
    <div class="sucwwtip" style="text-align:center; line-height:50px">
     <span style="color:#e60012; font-size:24px">充值失败</span>
      <br />
      	订单号：<span class="ord">${orderno}</span>
      	 <br />
      	账号：<span class="ord">${recharge.getAccounts}</span>
      	 <br />
      	客服热线：<span class="ord">${serviceTel}</span>
    </div>
  </div>
  
  <div class="goshop">
    <a href="/web/recharge!list.action" style="background-color:#d8d8d8; color:#8b8c90">返 回</a>
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