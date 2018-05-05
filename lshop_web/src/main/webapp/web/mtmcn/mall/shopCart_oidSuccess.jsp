<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_订单提交成功</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/Configure-mall.js"></script>
<script type="text/javascript">
	var _maq = _maq || [];
	_maq.push(['_setAccount', Mall_Conf.tgAccount]);
	_maq.push(['_setOrder', '${param.oid}']);
	(function() {
	    var ma = document.createElement('script'); ma.type = 'text/javascript'; ma.async = true;
	    ma.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + Mall_Conf.tgDomain;
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ma, s);
	})();
</script>
</head>

<body>
<header>
   <div class="top">
      <div class="title1">
        <h1>订单提交成功</h1>
      </div>
   </div>
</header>



<article>
  <div class="subminsucess">
    <div class="sucesstrue">
       
    </div>
    <div class="sucwwtip">
      您的订单已提交成功，请及时支付！
      <br />
      订单编号：<br /><span class="ord"><a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a></span>
    </div>
  </div>
  
  <div class="goshop">
    <a href="javascript:window.open('/web/shopCart!toPayOrder.action?oid=${param.oid }');" style="background-color:#0099ff">前往支付</a>
  </div> 
   
</article>

<article>
  <div style="height:190px">
  
  </div>
</article>

<!-- 分享 -->
<%@include file="/web/mtmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mtmcn/common/footer.jsp" %>

</body>
</html>
