<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>	
	
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 物流信息</span></h1> 
					<div class="express">
						<ul class="express_t">
							<li class="title">发货方式</li>
							<li>${lvOrder.expressName }</li>
							<li class="title">物流公司</li>
							<li>${lvOrder.expressCompany }</li>
							<li class="title">运单号码</li> 
							<li>${lvOrder.expressNum }</li>
						</ul>
						<s:if test="#request.lvOrder.expressName=='DHL'">
						<ul>
							<li><font class="fontwz">物流跟踪</font></li>
							<s:iterator value="dto.events">
								<li><s:date name="date" format="E MM'月'dd,yyyy"/>&nbsp;&nbsp;<s:date name="time" format="HH:mm"/>&nbsp;&nbsp;${serviceEvent }&nbsp;&nbsp;${serviceArea }</li>
							</s:iterator>
							<li class="tips"><img src="${resDomain}/bmcn/res/images/pos_icon.gif" width="19" height="19" /> 以上信息由物流公司提供，若无跟踪信息或有疑问，请查询<a href="http://www.cn.dhl.com" target="_blank">DHL快运</a>官方网站其他公示电话。</li>
						</ul>
						</s:if>
						<s:else>
							<li class="tips"><img src="${resDomain}/bmcn/res/images/pos_icon.gif" width="19" height="19" /> 若查看跟踪信息或有疑问，请查询官方网站，在此特提供几个常用网站地址。</li>
							<li>TVpad 默认使用<font class="redfont">UPS\DHL\邮政小包</font>进行发货，全球除偏远地区需加收运费外，一律免运费。</li>
							<li>偏远地区查询位置：<a href="http://remoteareas.hhl.com/jsp/first.jsp" target="_blank">http://remoteareas.dhl.com/jsp/first.jsp</a>(录入国家及邮编即可查询)</li>
							<li>UPS运单查询地址：<a href="http://www.ups.com/cn" target="_blank">http://www.ups.com/cn</a></li>
							<li>DHL运单查询地址：<a href="http://www.cn.dhl.com" target="_blank">http://www.cn.dhl.com</a></li>
							<li>邮政运单查询地址：<a href="http://www.shenzhenpost.com.cn" target="_blank">http://www.shenzhenpost.com.cn</a></li>
						</s:else>
					
					</div>
					<div class="buy_order">
								<ul>
									<li class="buy_order01">
										<p>&nbsp;</p>
										<p class="title">商品名称</p>
										<p>价格</p>
										<p>购买数量</p>
										<p>小计金额</p>
									</li>
									<c:foreach items="${objList}" var="obj">
									<li>
										<p><img src="${obj[0].pimage }" width="70px" height="60px"/></p>
										<p class="title" title="${obj[0].productName }">${obj[0].productName }</p>
										<p><font class="redfont fontwz">${lvOrder.currency} ${obj[1].oprice }</font></p>
										<p>${obj[1].pnum }</p>
										<p><font class="redfont fontwz">${lvOrder.currency} ${obj[2] }</font></p>
									</li>
									</c:foreach>
								</ul>
								<ul class="sum">商品总金额：${lvOrder.currency} ${allAmount} + 运费：${lvOrder.currency} ${lvOrder.postprice }</a></ul>
								<ul class="sum01">应付金额：<font class="redfont">${lvOrder.currency} ${lvOrder.totalPrice }</font></a></ul>
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
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 