<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>	
<title>banana商城_充值卡充值</title>
<script type="text/javascript">
	if (self.frameElement && self.frameElement.tagName == "IFRAME") {
		window.parent.location.href=location.href;
	}
</script>
</head>

<body>
<%@include file="/web/mbmcn/user_center/c_top.jsp"%>

<article>
  <div class="subminsucess">
    <div class="sucesstrue">
       
    </div>
    <div class="sucwwtip" style="text-align:center">
       <span style="color:#37a008; font-size:24px"> 已经成功充值${charge.vbNum}V币</span><br/>
       订单号:<span class="ord">${charge.rnum}</span><br/>
       账号:<span class="ord">${charge.accounts}</span><br/>

       充值金额： ${charge.vbNum} V币
    </div>
  </div>
  
  <div class="goshop">
    <a href="/web/recharge!list.action" style="background-color:#0099ff">返回</a>
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