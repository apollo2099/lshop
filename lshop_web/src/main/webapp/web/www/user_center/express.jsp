<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用戶中心_TVpad商城</title>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>	
	
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/www/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--><a>物流信息</a></font> </span></h1> 
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
							<li class="tips"><img src="${resDomain}/www/res/images/pos_icon.gif" width="19" height="19" /> 以上信息由物流公司提供，若無跟蹤信息或有疑問，清查詢<a href="http://www.cn.dhl.com" target="_blank">DHL快遞</a>官方網站貨其他公示電話。</li>
						</ul>
						</s:if>
						<s:else>
							<li class="tips"><img src="${resDomain}/www/res/images/pos_icon.gif" width="19" height="19" /> 若查看跟蹤信息或有疑問，請查詢官方網站，在此特提供幾個常用網站地址。</li>
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
										<p><font class="redfont fontwz">${moneyMark}${obj[2] }</font></p>
									</li>
									</c:foreach>
								</ul>
								<ul class="sum">商品總金額：${moneyMark}${allAmount} - 優惠券減免：${moneyMark}${allCouponPrice }+ 運費：${moneyMark}${lvOrder.postprice }</a></ul>
								<ul class="sum01">應付金額：<font class="redfont">${moneyMark}${lvOrder.totalPrice }</font></a></ul>
								<ul class="btn">
								  <input type="button" onclick="javascript:history.go(-1);" value="返回" class="user_center_bt" />
								  </a>
								</ul>
				  </div>
				
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		</div>	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 