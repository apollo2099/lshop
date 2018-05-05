<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- top -->
<%@include file="/web/tvpadcn/common/top.jsp" %>
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpadcn/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 物流信息</span></h1> 
			<div class="express">
				<ul class="express_t">
					<li class="title">發貨方式</li>
					<li>${lvOrder.expressName }</li>
					<li class="title">物流公司</li>
					<li>${lvOrder.expressCompany }</li>
					<li class="title">運單號碼</li> 
					<li>${lvOrder.expressNum }</li>
				</ul>
				<s:if test="#request.lvOrder.expressName=='DHL'">
				<ul>
					<li><font class="fontwz">物流跟蹤</font></li>
					<s:iterator value="dto.events">
						<li><s:date name="date" format="E MM'月'dd,yyyy"/>&nbsp;&nbsp;<s:date name="time" format="HH:mm"/>&nbsp;&nbsp;${serviceEvent }&nbsp;&nbsp;${serviceArea }</li>
					</s:iterator>
					<li class="tips"><img src="${resDomain}/tvpadcn/res/images/pos_icon.gif" width="19" height="19" /> 以上信息由物流公司提供，若無跟蹤信息或有疑問，清查詢<a href="http://www.cn.dhl.com" target="_blank">DHL快遞</a>官方網站貨其他公示電話。</li>
				</ul>
				</s:if>
				<s:else>
					<li class="tips"><img src="${resDomain}/tvpadcn/res/images/pos_icon.gif" width="19" height="19" /> 若查看跟蹤信息或有疑問，請查詢官方網站，在此特提供幾個常用網站地址。</li>
					<li>TVpad 默認使用<font class="redfont">UPS\DHL\郵政小包</font>進行發貨，全球除偏遠地區需加收運費外，一律免運費。</li>
					<li>偏遠地區查詢位址：<a href="http://remoteareas.hhl.com/jsp/first.jsp" target="_blank">http://remoteareas.dhl.com/jsp/first.jsp</a>(錄入國家及郵編即可查詢)</li>
					<li>UPS運單查詢地址：<a href="http://www.ups.com/cn" target="_blank">http://www.ups.com/cn</a></li>
					<li>DHL運單查詢地址：<a href="http://www.cn.dhl.com" target="_blank">http://www.cn.dhl.com</a></li>
					<li>郵政運單查詢地址：<a href="http://www.shenzhenpost.com.cn" target="_blank">http://www.shenzhenpost.com.cn</a></li>
				</s:else>
			
			</div>
			<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">商品名稱</p>
								<p>價格</p>
								<p>購買數量</p>
								<p>小計金額</p>
							</li>
							<c:foreach items="${objList}" var="obj">
							<li>
								<p><img src="${obj[0].pimage }" width="70px" height="60px"/></p>
								<p class="title">${obj[0].productName }</p>
								<p><font class="redfont fontwz">${moneyMark}${obj[1].oprice }</font></p>
								<p>${obj[1].pnum }</p>
								<p><font class="redfont fontwz">${moneyMark}<u:formatnumber value="${obj[1].pnum*obj[1].oprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></p>
							</li>
							</c:foreach>
						</ul>
						<ul class="sum">商品总金额：${moneyMark}<u:formatnumber value="${allAmount}"  type="number" groupingUsed="false" maxFractionDigits="2"/> - 优惠券减免：${moneyMark}<u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/>+ 运费${moneyMark}<u:formatnumber value="${lvOrder.postprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></a></ul>
						<ul class="sum01">應付金额：<font class="redfont">${moneyMark}<u:formatnumber value="${lvOrder.totalPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></a></ul>
						<ul class="btn">
						  <a href="javascript:history.go(-1);"><img src="${resDomain}/tvpadcn/res/images/user_center_btn04.gif" width="101" height="34" />
						  </a>
						</ul>
		  </div>
		
	 <!--End right_Frame-->
</div>
<!--End content-->	
</div>		
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 