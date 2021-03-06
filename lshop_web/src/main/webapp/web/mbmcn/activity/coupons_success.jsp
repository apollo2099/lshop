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
      <div class="massinfomaw1">
      <table width="310" border="0" align="center" class="nousertable">
     <tr>
      <td width="31%" height="12" align="right"></td>
      <td height="12" colspan="2"></td>
    </tr>
  <tr>
    <td height="25" colspan="3" align="center" valign="top" class="activ"><strong>恭喜您领取成功</strong></td>
    </tr>
  <tr>
    <td width="31%" height="25" align="right" valign="top">面&nbsp;&nbsp;&nbsp;&nbsp;值：</td>
    <td height="25" colspan="2" valign="top">${couponView.faceValue}</td>
  </tr>
  <tr>
    <td width="31%" height="25" rowspan="2" align="right" valign="top">使用规则：</td>
    <td height="25" colspan="2" valign="top">1.${couponView.type} ：${couponView.typeProduct}</td>
  </tr>
  <tr>
    <td height="25" colspan="2" valign="top">2.商品总金额滿${couponView.limitAmount}</td>
  </tr>
  <tr>
    <td width="31%" height="25" align="right" valign="top">有效期：</td>
    <td height="25" colspan="2" valign="top">${couponView.validDate}</td>
  </tr>
  <tr>
      <td width="31%" height="12" align="right"></td>
      <td width="49%" height="12" align="right"></td>
      <td width="25%" align="right"></td>
    </tr>
</table>
   </div>
   <div class="userinput"><a  href="${storeDomain}/index.html" class="usinbt">去首页</a></div>
</article>

<!-- 分享 -->
<%@include file="/web/mbmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mbmcn/common/footer.jsp" %>
</body>
</html>

