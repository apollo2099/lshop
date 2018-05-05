<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
Map ActivityMap = new HashMap();
ActivityMap.put(new Integer(3), "Placing order promotion");
ActivityMap.put(new Integer(4), "Link-clicking promotion");
ActivityMap.put(new Integer(5), "Registration promotion");
ActivityMap.put(new Integer(6), "Sharing promotion");
ActivityMap.put(new Integer(7), "Lucky Draw");
ActivityMap.put(new Integer(8), "Buy promotion");
request.setAttribute("ActivityMap", ActivityMap);
%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _TVpad Mall</title>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp"%>	
	
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/en/user_center/leftFrame.jsp" %>
			
  			<!--End left_Frame-->
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> My coupons</span></h1>
				<div class="usercenter_box">		
					<div class="indextj commargin1">
						<div class="product_item">
				            <ul>
				              	<li <c:if test="${useStatus==1 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=1">Available</a></li>
				              	<li <c:if test="${useStatus==2 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=2">Used</a></li>
				              	<li <c:if test="${useStatus==3 }">class="choose"</c:if>><a href="/web/couponManage!getCouponList.action?useStatus=3">Expired</a></li>
				            </ul>
				        </div>
				        <c:if test="${useStatus==1 }">
          				<div class="tjcontent" id="f_Pic1">
							<table width="735" border="0" cellpadding="0" cellspacing="1" bgcolor="#e5e5e5">
							  <tr>
							    <td width="173" height="25" align="center" bgcolor="#F7F4EB">Coupon name</td>
							    <td width="78" align="center" bgcolor="#F7F4EB"><p >Value</p></td>
							    <td width="244" align="center" bgcolor="#F7F4EB"><p >Rules&nbsp;of&nbsp;use</p></td>
							    <td width="137" align="center" bgcolor="#F7F4EB">Validity</td>
							    <td width="97" align="center" bgcolor="#F7F4EB">Source </td>
							  </tr>
							  <c:foreach items="${page.list }" var="lvCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">
								    	${lvCoupon.lvCouponType.name }
								    	<c:if test="${lvCoupon.lvCouponType.status==0}">
								      		<a href="#"><img src="${resDomain}/en/res/images/wx.gif" title="${lvCoupon.lvCouponType.disableReasons }"/></a>
								      	</c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF">${lvCoupon.lvCouponType.currency } ${lvCoupon.lvCouponType.amount }</td>
								     <td align="left" bgcolor="#FFFFFF">
								    	<p>1.<c:if test="${lvCoupon.lvCouponType.relationType==1}">Available&nbsp;categories</c:if><c:if test="${lvCoupon.lvCouponType.relationType==2}">Available&nbsp;items</c:if>：${lvCoupon.limitProducts }</p>
								      	<p>2.Order&nbsp;amount&nbsp;should&nbsp;be&nbsp;no&nbsp;less&nbsp;than&nbsp;${lvCoupon.lvCouponType.currency } ${lvCoupon.lvCouponType.limitAmount }</p>
								    	<c:if test="${lvCoupon.lvCouponType.reuse==1}"><p>3.Reusable</p></c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${lvCoupon.lvCouponType.startTime }"  type="both" /> to <fmt:formatDate value="${lvCoupon.lvCouponType.endTime }"  type="both" /></td>
								    <c:if test="${not empty lvCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF"><c:out value="${ActivityMap[lvCoupon.grantActivity.typeKey]}"></c:out>：${lvCoupon.grantActivity.activityTitle}</td>
								    </c:if>
								    <c:if test="${empty lvCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF">Other</td>
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
								    <td width="173" height="25" align="center" bgcolor="#F7F4EB">Coupon name</td>
								    <td width="72" align="center" bgcolor="#F7F4EB">Value</td>
								    <td width="240" align="center" bgcolor="#F7F4EB">Rules&nbsp;of&nbsp;use</td>
								    <td width="72" align="center" bgcolor="#F7F4EB">Order No.</td>
								    <td width="73" align="center" bgcolor="#F7F4EB">Time of use</td>
								    <td width="97" align="center" bgcolor="#F7F4EB">Source </td>
								 </tr>
								 <c:foreach items="${page.list }" var="uCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">${uCoupon.lvCouponType.name } </td>
								    <td align="center" bgcolor="#FFFFFF">${uCoupon.lvCouponType.currency } ${uCoupon.lvCouponType.amount }</td>
								    <td align="left" bgcolor="#FFFFFF">
								    	<p>1.<c:if test="${uCoupon.lvCouponType.relationType==1}">Available&nbsp;categories</c:if><c:if test="${uCoupon.lvCouponType.relationType==2}">Available&nbsp;items</c:if>：${uCoupon.limitProducts }</p>
								      	<p>2.Order&nbsp;amount&nbsp;should&nbsp;be&nbsp;no&nbsp;less&nbsp;than&nbsp;${uCoupon.lvCouponType.currency } ${uCoupon.lvCouponType.limitAmount }</p>
								      	<c:if test="${uCoupon.lvCouponType.reuse==1}"><p>3.Reusable</p></c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF"><a href="/web/userOrder!viewOrderInfo.action?oid=${uCoupon.orderId }">${uCoupon.orderId }</a></td>
								    <td align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${uCoupon.applyTime }"  type="both" /></td>
								    <c:if test="${not empty uCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF"><c:out value="${ActivityMap[uCoupon.grantActivity.typeKey]}"></c:out>：${uCoupon.grantActivity.activityTitle}</td>
								    </c:if>
								    <c:if test="${empty uCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF">Other</td>
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
							    <td width="245" height="25" align="center" bgcolor="#F7F4EB">Coupon name</td>
							    <td width="72" align="center" bgcolor="#F7F4EB">Value</td>
							    <td width="132" align="center" bgcolor="#F7F4EB">Rules&nbsp;of&nbsp;use</td>
							    <td width="156" align="center" bgcolor="#F7F4EB">Validity</td>
							    <td width="60" align="center" bgcolor="#F7F4EB">Status</td>
							    <td width="97" align="center" bgcolor="#F7F4EB">Source </td>
							  </tr>
							  <c:foreach items="${page.list }" var="pCoupon">
								  <tr>
								    <td height="53" align="center" bgcolor="#FFFFFF">${pCoupon.lvCouponType.name }</td>
								    <td align="center" bgcolor="#FFFFFF">${pCoupon.lvCouponType.currency } ${pCoupon.lvCouponType.amount }</td>
								      <td align="left" bgcolor="#FFFFFF">
								    	<p>1.<c:if test="${pCoupon.lvCouponType.relationType==1}">Available&nbsp;categories</c:if><c:if test="${pCoupon.lvCouponType.relationType==2}">Available&nbsp;items</c:if>：${pCoupon.limitProducts }</p>
								      	<p>2.Order&nbsp;amount&nbsp;should&nbsp;be&nbsp;no&nbsp;less&nbsp;than&nbsp;${pCoupon.lvCouponType.currency } ${pCoupon.lvCouponType.limitAmount }</p>
								    	<c:if test="${pCoupon.lvCouponType.reuse==1}"><p>3.Reusable</p></c:if>
								    </td>
								    <td align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${pCoupon.lvCouponType.startTime }"  type="both" /> to <fmt:formatDate value="${pCoupon.lvCouponType.endTime }"  type="both" /></td>
								    <td align="center" bgcolor="#FFFFFF">Expired</td>
								    <c:if test="${not empty pCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF"><c:out value="${ActivityMap[pCoupon.grantActivity.typeKey]}"></c:out>：${pCoupon.grantActivity.activityTitle}</td>
								    </c:if>
								    <c:if test="${empty pCoupon.grantActivity}">
								    <td align="center" bgcolor="#FFFFFF">Other</td>
								    </c:if>
								  </tr>
							  </c:foreach>
							</table>			
						</div>
						</c:if>
						<c:if test="${page.totalPage>1}">
						  <fmt:newPage href="/web/couponManage!getCouponList.action?useStatus=${useStatus }&page.pageNum=@" language="en"></fmt:newPage>
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
		<%@include file="/web/en/common/footer.jsp" %>
	
	</body>
</html> 