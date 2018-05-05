<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad4 最新活动</title>
		<link href="${resDomain}/www/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/www/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
		
		<div class="coupons_lk">
            <h1>${couponView.activityTitle}</h1>
            <h2>恭喜您領取成功</h2>
            <ul class="coupons_info">
                <li class="clearfix">
                    <label>优惠码：</label>
                    <p>${couponView.couponCode}</p>
                </li>
                <li class="clearfix">
                    <label>面值：</label>
                    <p>${couponView.faceValue}</p>
                </li>
                <li class="clearfix">
                    <label>使用規則：</label>
                    <ul class="coupons_re">
                        <li>${couponView.type} ：${couponView.typeProduct}</li>
                        <li>商品總金額滿${couponView.limitAmount}</li>
                        <li id="reuseli">${couponView.reuseName}</li>
                    </ul>
                </li>
                <li class="clearfix">
                    <label>有效期：</label>
                    <p>${couponView.validDate}</p>
                </li>
            </ul>
            <p class="coupons_btn">
                <a href="http://cn.mtvpad.com/products/6f2390c6bd794e28a34fc5dc512e4a2e.html" target="_blank">
                <input name="" type="button" class="btn04" value="去购买" />
                </a>
            </p>
        </div>
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>	  
	</body>
</html>
<script type="text/javascript">
function setTr() {
	if('${couponView.reuse}'==0){
		$("#reuseli").attr("style", "display:none");
	}
}

setTimeout("setTr()", 50);
</script>