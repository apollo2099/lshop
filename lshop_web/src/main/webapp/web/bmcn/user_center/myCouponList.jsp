<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%
Map ActivityMap = new HashMap();
ActivityMap.put(new Integer(3), "订单满就送活动");
ActivityMap.put(new Integer(4), "点击链接就送活动");
ActivityMap.put(new Integer(5), "注册就送活动");
ActivityMap.put(new Integer(6), "分享就送活动");
ActivityMap.put(new Integer(7), "抽奖活动");
request.setAttribute("ActivityMap", ActivityMap);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_我的优惠券</title>
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
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 我的优惠券</span></h1> 
				<div class="usercenter_box">		
					<div class="indextj commargin1">
						<div class="product_item2">
				            <ul>
				              	<li <c:if test="${useStatus==1 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=1">未使用</a></li>
				              	<li <c:if test="${useStatus==2 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=2">已使用</a></li>
				              	<li <c:if test="${useStatus==3 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=3">已过期</a></li>
				            </ul>
				        </div>
				        
				       <c:if test="${useStatus==1 }">
						<div class="tjcontent" id="f_Pic1">
							<table width="735" border="0" cellpadding="0" cellspacing="1" bgcolor="#e5e5e5">
							  <tr>
						        <td width="261" height="25" align="center" bgcolor="#e2f2fc">优惠券名称</td>
								<td width="84" align="center" bgcolor="#e2f2fc">面值</td>
								<td width="215" align="center" bgcolor="#e2f2fc">使用规则</td>
								<td width="170" align="center" bgcolor="#e2f2fc">有效期</td>
								<td width="170" align="center" bgcolor="#e2f2fc">来源</td>
							  </tr>
							  <c:foreach items="${page.list }" var="lvCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">
								    	${lvCoupon.lvCouponType.name }
								    	<c:if test="${lvCoupon.lvCouponType.status==0}">
								      		<a href="#"><img src="${resDomain}/bmcn/res/images/wx.gif" title="${lvCoupon.lvCouponType.disableReasons }"/></a>
								      	</c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF">${lvCoupon.lvCouponType.currency } ${lvCoupon.lvCouponType.amount }</td>
								    <td align="left" bgcolor="#FFFFFF">
								    	<p>1. <c:if test="${lvCoupon.lvCouponType.relationType==1}">限品类</c:if><c:if test="${lvCoupon.lvCouponType.relationType==2}">限商品</c:if>：${lvCoupon.limitProducts }</p>
								      	<p>2. 商品总金额滿${lvCoupon.lvCouponType.currency } ${lvCoupon.lvCouponType.limitAmount }</p>
								      	<c:if test="${lvCoupon.lvCouponType.reuse==1}"><p>3. 可重複使用</p></c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${lvCoupon.lvCouponType.startTime }"  type="both" />至 <fmt:formatDate value="${lvCoupon.lvCouponType.endTime }"  type="both" /></td>
								    <c:if test="${not empty lvCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF"><c:out value="${ActivityMap[lvCoupon.grantActivity.typeKey]}"></c:out>：${lvCoupon.grantActivity.activityTitle}</td>
								    </c:if>
								    <c:if test="${empty lvCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF">其它</td>
								    </c:if>
								  </tr>
							  </c:foreach>
							</table>
						</div>
						</c:if>
		
						<c:if test="${useStatus==2 }">
						<div class="tjcontent" id="f_Pic2">
							<table width="735" border="0" cellpadding="0" cellspacing="1" bgcolor="#e5e5e5">
							  <tr>
								<td width="249" height="25" align="center" bgcolor="#e2f2fc">优惠券名称</td>
								<td width="70" align="center" bgcolor="#e2f2fc">面值</td>
								<td width="131" align="center"bgcolor="#e2f2fc">使用规则</td>
								<td width="67" align="center" bgcolor="#e2f2fc">订单号</td>
								<td width="86" align="center" bgcolor="#e2f2fc">使用时间</td>
								<td width="125" align="center" bgcolor="#e2f2fc">来源</td>
							  </tr>
							  <c:foreach items="${page.list }" var="uCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">${uCoupon.lvCouponType.name } </td>
								    <td align="center" bgcolor="#FFFFFF">${uCoupon.lvCouponType.currency } ${uCoupon.lvCouponType.amount }</td>
								    <td align="left" bgcolor="#FFFFFF">
								    	<p>1.<c:if test="${uCoupon.lvCouponType.relationType==1}">限品类</c:if><c:if test="${uCoupon.lvCouponType.relationType==2}">限商品</c:if>：${uCoupon.limitProducts }</p>
								      	<p>2. 商品总金额滿${uCoupon.lvCouponType.currency } ${uCoupon.lvCouponType.limitAmount }</p>
								      	<c:if test="${uCoupon.lvCouponType.reuse==1}"><p>3. 可重複使用</p></c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF"><a href="/web/userOrder!viewOrderInfo.action?oid=${uCoupon.orderId }">${uCoupon.orderId }</a></td>
								    <td align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${uCoupon.applyTime }"  type="both" /></td>
								    <c:if test="${not empty uCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF"><c:out value="${ActivityMap[uCoupon.grantActivity.typeKey]}"></c:out>：${uCoupon.grantActivity.activityTitle}</td>
								    </c:if>
								    <c:if test="${empty uCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF">其它</td>
								    </c:if>
								  </tr>
							  </c:foreach>
							</table>			
						</div>
						</c:if>
		
						<c:if test="${useStatus==3 }">
						<div class="tjcontent" id="f_Pic3">
							<table width="735" border="0" cellpadding="0" cellspacing="1" bgcolor="#e5e5e5">
							  <tr>
								<td width="213" height="25" align="center" bgcolor="#e2f2fc">优惠券名称</td>
								<td width="75" align="center" bgcolor="#e2f2fc">面值</td>
								<td width="128" align="center" bgcolor="#e2f2fc">使用规则</td>
								<td width="109" align="center" bgcolor="#e2f2fc">有效期</td>
								<td width="69" align="center" bgcolor="#e2f2fc">状态</td>
								<td width="134" align="center" bgcolor="#e2f2fc">来源</td>
							  </tr>
							  <c:foreach items="${page.list }" var="pCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">${pCoupon.lvCouponType.name }</td>
								    <td align="center" bgcolor="#FFFFFF">${pCoupon.lvCouponType.currency } ${pCoupon.lvCouponType.amount }</td>
								     <td align="left" bgcolor="#FFFFFF">
								    	<p>1. <c:if test="${pCoupon.lvCouponType.relationType==1}">限品类</c:if><c:if test="${pCoupon.lvCouponType.relationType==2}">限商品</c:if>：${pCoupon.limitProducts }</p>
								      	<p>2. 商品总金额滿${pCoupon.lvCouponType.currency } ${pCoupon.lvCouponType.limitAmount }</p>
								      	<c:if test="${pCoupon.lvCouponType.reuse==1}"><p>3. 可重複使用</p></c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${pCoupon.lvCouponType.startTime }"  type="both" />至 <fmt:formatDate value="${pCoupon.lvCouponType.endTime }"  type="both" /></td>
								    <td align="center" bgcolor="#FFFFFF">已过期</td>
								    <c:if test="${not empty pCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF"><c:out value="${ActivityMap[pCoupon.grantActivity.typeKey]}"></c:out>：${pCoupon.grantActivity.activityTitle}</td>
								    </c:if>
								    <c:if test="${empty pCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF">其它</td>
								    </c:if>
								  </tr>
							  </c:foreach>
							</table>			
						</div>
						</c:if>
		          		<c:if test="${page.totalPage>1}">
						  <fmt:newPage href="/web/couponManage!getCouponList.action?useStatus=${useStatus }&page.pageNum=@"></fmt:newPage>
						  <script type="text/javascript">
							function toPage(){
								var pageNum = $("#pageValue").val();
							   	window.location.href="/web/couponManage!getCouponList.action?useStatus=${useStatus }&page.pageNum="+pageNum;
							
							}
						 </script>
					 	</c:if>
			  		</div>
				</div>
		  	</div>
			<!--End right_Frame-->
			 
		 	<div class="cb"></div>	 
		</div>
		<!--End content-->	

		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
				
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 