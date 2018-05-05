<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
Map ActivityMap = new HashMap();
ActivityMap.put(new Integer(3), "訂單滿就送活動");
ActivityMap.put(new Integer(4), "點擊鏈接就送活動");
ActivityMap.put(new Integer(5), "注冊就送活動");
ActivityMap.put(new Integer(6), "分享就送活動");
ActivityMap.put(new Integer(7), "抽獎活動");
ActivityMap.put(new Integer(8), "購買指定商品就送活動");
request.setAttribute("ActivityMap", ActivityMap);
%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
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
			
  			<!--End left_Frame-->
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--><a>我的優惠券</a></font> </span></h1>
				<div class="usercenter_box">		
					<div class="indextj commargin1">
						<div class="product_item">
				            <ul>
				              	<li <c:if test="${useStatus==1 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=1">未使用</a></li>
				              	<li <c:if test="${useStatus==2 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=2">已使用</a></li>
				              	<li <c:if test="${useStatus==3 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=3">已過期</a></li>
				            </ul>
				        </div>
				        <c:if test="${useStatus==1 }">
          				<div class="tjcontent" id="f_Pic1">
							<table width="735" border="0" cellpadding="0" cellspacing="1" bgcolor="#e5e5e5">
							  <tr>
							    <td width="233" height="25" align="center" bgcolor="#F7F4EB">優惠券名稱</td>
								<td width="83" align="center" bgcolor="#F7F4EB">面值</td>
								<td width="159" align="center" bgcolor="#F7F4EB">使用規則</td>
								<td width="118" align="center" bgcolor="#F7F4EB">有效期</td>
								<td width="136" align="center" bgcolor="#F7F4EB">來源</td>
							  </tr>
							  <c:foreach items="${page.list }" var="lvCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">
								    	${lvCoupon.lvCouponType.name }
								    	<c:if test="${lvCoupon.lvCouponType.status==0}">
								      		<a href="#"><img src="${resDomain}/www/res/images/wx.gif" title="${lvCoupon.lvCouponType.disableReasons }"/></a>
								      	</c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF">${lvCoupon.lvCouponType.currency } ${lvCoupon.lvCouponType.amount }</td>
								    <td align="left" bgcolor="#FFFFFF">
								    	<p>1. <c:if test="${lvCoupon.lvCouponType.relationType==1}">限品類</c:if><c:if test="${lvCoupon.lvCouponType.relationType==2}">限商品</c:if>：${lvCoupon.limitProducts }</p>
								      	<p>2. 商品總金額滿${lvCoupon.lvCouponType.currency } ${lvCoupon.lvCouponType.limitAmount }</p>
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
									<td width="265" height="25" align="center" bgcolor="#F7F4EB">優惠券名稱</td>
									<td width="72" align="center" bgcolor="#F7F4EB">面值</td>
									<td width="132" align="center" bgcolor="#F7F4EB">使用規則</td>
									<td width="72" align="center" bgcolor="#F7F4EB">訂單號</td>
									<td width="73" align="center" bgcolor="#F7F4EB">使用時間</td>
									<td width="166" align="center" bgcolor="#F7F4EB">來源</td>
								 </tr>
								 <c:foreach items="${page.list }" var="uCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">${uCoupon.lvCouponType.name } </td>
								    <td align="center" bgcolor="#FFFFFF">${uCoupon.lvCouponType.currency } ${uCoupon.lvCouponType.amount }</td>
								    <td align="left" bgcolor="#FFFFFF">
								    	<p>1. <c:if test="${uCoupon.lvCouponType.relationType==1}">限品類</c:if><c:if test="${uCoupon.lvCouponType.relationType==2}">限商品</c:if>：${uCoupon.limitProducts }</p>
								      	<p>2. 商品總金額滿${uCoupon.lvCouponType.currency } ${uCoupon.lvCouponType.limitAmount }</p>
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
								<td width="245" height="25" align="center" bgcolor="#F7F4EB">優惠券名稱</td>
								<td width="72" align="center" bgcolor="#F7F4EB">面值</td>
								<td width="132" align="center" bgcolor="#F7F4EB">使用規則</td>
								<td width="156" align="center" bgcolor="#F7F4EB">有效期</td>
								<td width="60" align="center" bgcolor="#F7F4EB">狀態</td>
								<td width="166" align="center" bgcolor="#F7F4EB">來源</td>
							  </tr>
							  <c:foreach items="${page.list }" var="pCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">${pCoupon.lvCouponType.name }</td>
								    <td align="center" bgcolor="#FFFFFF">${pCoupon.lvCouponType.currency } ${pCoupon.lvCouponType.amount }</td>
								     <td align="left" bgcolor="#FFFFFF">
								    	<p>1. <c:if test="${pCoupon.lvCouponType.relationType==1}">限品類</c:if><c:if test="${pCoupon.lvCouponType.relationType==2}">限商品</c:if>：${pCoupon.limitProducts }</p>
								      	<p>2. 商品總金額滿${pCoupon.lvCouponType.currency } ${pCoupon.lvCouponType.limitAmount }</p>
								      	<c:if test="${pCoupon.lvCouponType.reuse==1}"><p>3. 可重複使用</p></c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${pCoupon.lvCouponType.startTime }"  type="both" />至 <fmt:formatDate value="${pCoupon.lvCouponType.endTime }"  type="both" /></td>
								    <td align="center" bgcolor="#FFFFFF">已過期</td>
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
          				<!-- End 規格參數-->
	  				</div>
				</div>
  			</div>
  			<!--End right_Frame-->
		</div>
		<!--End content-->	
	
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 