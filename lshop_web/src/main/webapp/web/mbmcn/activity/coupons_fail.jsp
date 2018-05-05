<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana 商城</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp" %>
</head>

<body>
<header>
   <div class="top">
      <div class="title">
        <h1>banana 商城</h1>
        <span><a href="#">English</a></span>
      </div>
      <div class="shopping">
         <div class="shoplebg">
            <div class="shopicon"><a href="javascript:toCar('${storeDomain}');"></a><span id="shopCartNum" style="display:none;"></span></div>
         </div>
      </div>
   </div>
</header>
<article>
  
      <h1 class="activ">${couponView.activityTitle}</h1>
      <div class="activetip">
         您已领取过了，下次再来吧
      </div>
   <div class="userinput"><a  href="${storeDomain}/index.html" class="usinbt">去首页</a></div>
</article>

<!-- 分享 -->
<%@include file="/web/mbmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mbmcn/common/footer.jsp" %>
</body>
</html>

