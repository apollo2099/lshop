<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad Coupon</title>
		<link href="${resDomain}/en/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/en/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp"%>
		
				<div class="coupons_lk">
            <h1 style="font-size: 24px;font-weight: bold;">${couponView.activityTitle}</h1>
            <h2 style="font-size: 16px;font-weight: bold;">Congratulations! You got a coupon!</h2>
            <ul class="coupons_info">
                <li class="clearfix">
                    <label>Promo Code：</label>
                    <p>${couponView.couponCode}</p>
                </li>
                <li class="clearfix">
                    <label>Value：</label>
                    <p>${couponView.faceValue}</p>
                </li>
                <li class="clearfix">
                    <label>Rules of use：</label>
                    <ul class="coupons_re">
                        <li>${couponView.type} ：${couponView.typeProduct}</li>
                        <li>Order amount should be no less than ${couponView.limitAmount}</li>
                        <li id="reuseli">${couponView.reuseName}</li>
                    </ul>
                </li>
                <li class="clearfix">
                    <label>Validity：</label>
                    <p>${couponView.validDate}</p>
                </li>
            </ul>
            <p class="coupons_btn">
            <a href="http://tvpaden.mtvpad.com/products/0e55accec6f44cd484dc6ca30ba46b3a.html" target="_blank">
                <input name="" type="button" class="btn04" value="Go Buy" />
            </a>
            </p>
            
        </div>
		<%--
		
			<!-- 成功 -->
			<div class="coupons_f">
				<ul>
			     <li class="wd1">${couponView.activityTitle}</li>
			      <li class="wd3">Congratulations! You got a coupon!</li>
			      <div class="wd4">
			      	<ul>
			      	    <li><span class="w">Promo Code：</span><font >${couponView.couponCode}</font></li>
			        	<li><span class="w">Value：</span><font class="bluewz fontwz">${couponView.faceValue}</font></li>
			            <li><span class="w">Rules of use：</span>1.${couponView.type} ：${couponView.typeProduct}</li> 
			            <li><span class="w">&nbsp;</span>2.Order amount should be no less than ${couponView.limitAmount}</li> 
			            <li ><span class="w">Validity：</span><font class="bluewz fontwz">${couponView.validDate}</font></li>         
			        </ul>
			      </div>
			      
			         <li class="wd2"><input name="" type="button" class="btn04"value="Home" onclick="window.location.href='${storeDomain}/index.html'" /></li>
			    </ul>
			</div>
			 --%>
			<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/en/common/footer.jsp" %>
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