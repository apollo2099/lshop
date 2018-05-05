<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>Banana 商城</title>
<link href="${resDomain}/mbmcn/res/css/style.css" rel="stylesheet" TYPE="text/css">
<link href="${resDomain}/mbmcn/res/css/merchants_zt.css" rel="stylesheet" TYPE="text/css">
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp" %>
</head>

<body>
<header>
   <div class="top">
      <div class="title">
        <h1>Banana 商城</h1>
        <span>English</span>
      </div>
      <div class="shopping">
         <div class="shoplebg">
            <div class="shopicon"><a href="javascript:toCar('${storeDomain}');"></a><span id="shopCartNum" style="display:none;"></span></div>
         </div>
      </div>
   </div>
</header>

<article>
   <div class="zt_banner">
    
   </div>
</article>

<article>
   <div class="cl_jingru">
      <div class="ti_jinru">
         <span>点击进入</span>
      </div>  
      <div class="cl_img">
         <a href="btvzs.html"><img src="${resDomain}/mbmcn/res/images/merchants/shijie.jpg"  width="100%"/></a>
      </div>
      <div class="cl_deti">
        <strong>banana TV全球经销商招募</strong>
        <span>财富一触即发！</span>
      </div>
   </div>
   
   <div class="cl_jingru">
      <div class="ti_jinru">
         <span>点击进入</span>
      </div>  
      <div class="cl_img">
         <a href="#"><img src="${resDomain}/mbmcn/res/images/merchants/jianshe.jpg" width="100%"/></a>
      </div>
      <div class="cl_deti">
        <strong>没有互联网？一样收看家乡实时高清电视！</strong>
        <span>面向全球诚招智能局域网电视代理商、运营商</span>
      </div>
   </div>
</article>


<!-- 分享 -->
<%@include file="/web/mbmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mbmcn/common/footer.jsp" %>
</body>
</html>
