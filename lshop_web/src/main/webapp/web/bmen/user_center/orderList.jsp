<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${resDomain}/bmen/res/js/ymPrompt/skin/vista/ymPrompt.css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/ymPrompt/ymPrompt.js" ></script>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/userCenter.js"></script>		
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif"/><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> My Order</h1> 
				<div class="usercenter_box">
					<div class="orderlist">
						<!-- 搜索 -->
						<form action="/web/userOrder!getOrderlist.action" method="post">
						<ul class="cx">
			  				<li class="wdx7">Order No.：<input name="orderId"  type="text" class="input3" value="${orderId }"/> </li>
			   				<li class="wdx7">Seller：<input name="storeName"  type="text" class="input04" value="${storeName }"/> </li>
			   				<li class="wdx7">Time of Order：<input name="startTime"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'en'})" type="text" class="input05"  value="${startTime }"/> to <input name="endTime"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'en'})" type="text" class="input05" value="${endTime }"/></li>
			   				<li class="wdx7">Status：
			    				<select class="input04" name="oidStatus">
			    					<option value="0">--choose--</option>
			      					<option value="1" <c:if test="${oidStatus==1 }">selected</c:if>>Await payment</option>
			      					<option value="2" <c:if test="${oidStatus==2 }">selected</c:if>>Paid, Unconfirmed</option>
			      					<option value="3" <c:if test="${oidStatus==3 }">selected</c:if>>Paid, Await delivery</option>
			      					<option value="4" <c:if test="${oidStatus==4 }">selected</c:if>>Paid, Delivered</option>
			      					<option value="5" <c:if test="${oidStatus==5 }">selected</c:if>>Await reviews</option>
			      					<option value="6" <c:if test="${oidStatus==6 }">selected</c:if>>Completed</option>
			      					<option value="7" <c:if test="${oidStatus==7 }">selected</c:if>>Returned</option>
			    				</select>
			   				</li>
				   			<li class="wdx8"><input type="submit" value="Search" class="user_center_bt02" /></li>
				  		</ul>
				  		</form>
			          <ul class="title">
			            <li class="wdx1">Order No.</li>
			            <li>Seller</li>
			            <li class="wdx2">Time of Order</li>
			            <li>Amount</li>
			            <li>Payment</li>
			            <li class="red">Status</li>
			            <li class="blue">Operation</li>
			          </ul>
			          <s:iterator value="page.list" status="entry">
			          <ul <s:if test="#entry.count%2==1"></s:if><s:else>class="bluebg"</s:else>>
			            <li class="wdx1"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a></li>
			            <li> <cus:store param="storeName" shopFlag="${storeId}"/></li>
			            <li class="wdx2"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></li>
			            <li> ${currency} ${totalPrice}</li>	
			            <li>
			            	<s:if test="paymethod==3||paymethod==18">
			            		Alipay
			            	</s:if>
			            	<s:if test="paymethod==4">
			            		Western Union
			            	</s:if>
			            	<s:if test="paymethod==11||paymethod==15">
			            		Credit or Debit Card by VISA
			            	</s:if>
			            	<s:if test="paymethod==12||paymethod==16">
			            		Credit or Debit Card by Master 
			            	</s:if>
			            	<s:if test="paymethod==13||paymethod==17">
			            		Credit or Debit Card by JCB
			            	</s:if>
			            	<s:if test="paymethod==14">
			            		VISA/Master credit or debit card 
			            	</s:if>      
			            </li>
			            <s:if test="payStatus==0">
			            	<li class="red"><font class="redfont">Await payment</font></li>
			            	<li  class="blue">
			            		<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> <br />
			            		<s:if test="paymethod==4">
								   <a href="/web/userOrder!toOrderWestern.action?oid=${oid }" >Submit Western Union Info</a><br />
							     </s:if>
							     <s:else>
								   <a href="/web/userOrder!toConfirmPay.action?oid=${oid }&shopFlag=${storeId }">Pay now</a>
							     </s:else>
							</li>
			            </s:if>	
			            <s:elseif test="payStatus==2">
							 <li class="red"><font class="redfont">Paid, Unconfirmed</font></li>
							 <li class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> </li>
						</s:elseif>
						<s:elseif test="payStatus==1">
							<s:if test="status==0">
								<li class="red"><font class="redfont">Paid, Await delivery</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> <br/><a href="javascript:void(0)"  onclick="showRemind('${oid }','${storeId}');">Remind delivery</a></li>
							</s:if>
							<s:elseif test="status==1">
								<li class="red"><font class="redfont">Paid, Delivered</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> <br/><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}&shopFlag=${storeId }">Logistics details</a><br/><a href="javascript:void(0)" onclick="confirmShouhuo('${code }');">Confirm receipt</a></li>
							</s:elseif>
							<s:elseif test="status==2">
								<li class="red"><font class="redfont">Await reviews</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a><br/><a href="/web/userOrder!toOrderComment.action?oid=${oid }&shopFlag=${storeId }">Review</a></li>
							</s:elseif>
							<s:elseif test="status==4">
								<li class="red"><font class="redfont">Completed</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a></li>
							</s:elseif>
							<s:elseif test="status==3">
								<li class="red"><font class="redfont">Returned</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a></li>
							</s:elseif>
						</s:elseif>
		          	</ul>
		         </s:iterator>
		         <div class="cb"></div>
				</div>
				<c:if test="${page.totalPage>1}">
				  <u:newPage href="/web/userOrder!getOrderlist.action?payStatus=${payStatus}&status=${status}&orderId=${orderId }&storeName=${storeName }&startTime=${startTime }&endTime=${endTime }&oidStatus=${oidStatus }&page.pageNum=@" language="en"></u:newPage>
				  <script type="text/javascript">
					function toPage(){
						var pageNum = $("#pageValue").val();
					   	window.location.href="/web/userOrder!getOrderlist.action?payStatus=${payStatus}&status=${status}&orderId=${orderId }&storeName=${storeName }&startTime=${startTime }&endTime=${endTime }&oidStatus=${oidStatus }&page.pageNum="+pageNum;
					
					}
				 </script>
			 </c:if>
			</div>	
		  	</div>
			 <!--End right_Frame-->
			 <div class="cb"></div>
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		
		<!-- footer-->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 
	