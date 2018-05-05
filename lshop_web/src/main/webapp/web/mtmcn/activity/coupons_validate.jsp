<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp" %>
</head>

<body>
<header>
   <div class="top">
      <div class="title">
        <h1>TVpad商城</h1>
        <%-- <span>English</span>--%>
      </div>
      <div class="shopping">
         <div class="shoplebg">
            <div class="shopicon"><a href="javascript:toCar('${storeDomain}');"></a><span id="shopCartNum" style="display:none;"></span></div>
         </div>
      </div>
   </div>
</header>

<article>
  
      <h1 class="activ">领取优惠券出错了</h1>
      <div class="activetip">返回消息：${resultMsg.msg}</div>
   <div class="userinput"><a  href="${storeDomain}/index.html" class="usinbt">去首页</a></div>
</article>

<!-- 分享 -->
<%@include file="/web/mtmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mtmcn/common/footer.jsp" %>
</body>
</html>