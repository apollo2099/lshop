<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用戶中心_TVpad商城</title>
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${resDomain}/www/res/js/ymPrompt/skin/vista/ymPrompt.css" />
		<script type="text/javascript" src="${resDomain}/www/res/js/ymPrompt/ymPrompt.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/userCenter.js"></script>		
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/www/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--><a>我的訂單</a></font> </span></h1> 
				<div class="usercenter_box">
					<div class="orderlist">
						<!-- 搜索 -->
						<form action="/web/userOrder!getOrderlist.action" method="post">
						<ul>
				  			<li class="wdx7">訂單號：</li>
			  				<li class="wdx6"><input name="orderId"  type="text" class="input2" value="${orderId }"/> </li>
			   				<li class="wdx7">商家名稱：</li>
			  				<li class="wdx8"><input name="storeName"  type="text" class="input4" value="${storeName }"/> </li>
			   				<li class="wdx7">下單時間：</li>
			  				<li class="wdx9"><input name="startTime"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-tw'})" type="text" class="input5"  value="${startTime }"/> 至 <input name="endTime"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-tw'})" type="text" class="input5" value="${endTime }"/></li>
				  		</ul>
				        <ul class="cx">
				            <li class="wdx7">優惠碼：</li>
				            <li class="wdx6">
				            	<input name="couponCode"  type="text" class="input2" value="${couponCode }"/>
				            </li>
				   			<li class="wdx7">訂單狀態：</li>
				  			<li class="wdx8">
				    			<select class="input4" name="oidStatus">
				    				<option value="0">--請選擇--</option>
				      				<option value="1" <c:if test="${oidStatus==1 }">selected</c:if>>待付款</option>
				      				<option value="2" <c:if test="${oidStatus==2 }">selected</c:if>>已付款,未確認</option>
				      				<option value="3" <c:if test="${oidStatus==3 }">selected</c:if>>已付款,待發貨</option>
				      				<option value="4" <c:if test="${oidStatus==4 }">selected</c:if>>已付款,已發貨</option>
				      				<option value="5" <c:if test="${oidStatus==5 }">selected</c:if>>待評價</option>
				      				<option value="6" <c:if test="${oidStatus==6 }">selected</c:if>>已完成</option>
				      				<option value="7" <c:if test="${oidStatus==7 }">selected</c:if>>已退貨</option>
				    			</select>
				   			</li>
				            <li class="wdx-7"><input type="image" src="${resDomain}/www/res/images/chaxun.jpg" border="0" /></li>
						</ul>
				  		</form>
		          		<ul class="title">
		           			<li class="wdx1">訂單號</li>
		           			<li>商家名稱</li>
		            		<li class="wdx2">下單時間</li>
		            		<li>訂單金額</li>
		            		<li>支付方式</li>
		            		<li class="red">訂單狀態</li>
		            		<li class="red">操作</li>
		          		</ul>
			          <s:iterator value="page.list" status="entry">
			          <ul <s:if test="#entry.count%2==1"></s:if><s:else>class="bluebg"</s:else>>
			            <li class="wdx1"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a></li>
			            <li> <cus:store param="storeName" shopFlag="${storeId}"/></li>
			            <li class="wdx2"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></li>
			            <li> ${currency} ${totalPrice}</li>	
			            <li>
			            	<s:if test="paymethod==1">
			            		PAYPAL支付
			            	</s:if>
			            	<s:if test="paymethod==2">
			            		国际信用卡
			            	</s:if>
			            	<s:if test="paymethod==3||paymethod==18">
			            		支付寶
			            	</s:if>
			            	<s:if test="paymethod==4">
			            		西聯匯款
			            	</s:if>
			            	<s:if test="paymethod==6">
			            		快錢支付
			            	</s:if>
			            	<s:if test="paymethod==8">
			            		雙乾支付
			            	</s:if>
			            	<s:if test="paymethod==11||paymethod==15">
			            		Visa國際信用卡或借記卡
			            	</s:if>
			            	<s:if test="paymethod==12||paymethod==16">
			            		Master國際信用卡或借記卡
			            	</s:if>
			            	<s:if test="paymethod==13||paymethod==17">
			            		JCB國際信用卡或借記卡
			            	</s:if>
			            	<s:if test="paymethod==14||paymethod==20">
			            		VISA/MASTER
			            	</s:if>
			            </li>
			            <s:if test="payStatus==0">
			            	<li class="red"><font class="redfont">待付款</font></li>
			            	<li  class="blue">
			            		<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">詳情</a> 
			            		<s:if test="paymethod==4">
								   <a href="/web/userOrder!toOrderWestern.action?oid=${oid }" >提交西聯信息</a>
							     </s:if>
							     <s:else>
								   <a href="/web/userOrder!toConfirmPay.action?oid=${oid }&shopFlag=${storeId }">現在支付</a>
							     </s:else>
							</li>
			            </s:if>	
			            <s:elseif test="payStatus==2">
							 <li class="red"><font class="redfont">已付款,未確認</font></li>
							 <li class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">詳情</a> </li>
						</s:elseif>
						<s:elseif test="payStatus==1">
							<s:if test="status==0">
								<li class="red"><font class="redfont">已付款,待發貨</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">詳情</a> <a href="javascript:void(0)"  onclick="showRemind('${oid }','${storeId}');">提醒賣家發貨</a></li>
							</s:if>
							<s:elseif test="status==1">
								<li class="red"><font class="redfont">已付款,已發貨</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">詳情</a> <a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}&shopFlag=${storeId }">物流詳情</a><br/><a href="javascript:void(0)" onclick="confirmShouhuo('${code }');">確認收到貨</a></li>
							</s:elseif>
							<s:elseif test="status==2">
								<li class="red"><font class="redfont">待評價</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">詳情</a> <a href="/web/userOrder!toOrderComment.action?oid=${oid }&shopFlag=${storeId }">評價</a></li>
							</s:elseif>
							<s:elseif test="status==4">
								<li class="red"><font class="redfont">已完成</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">詳情</a></li>
							</s:elseif>
							<s:elseif test="status==3">
								<li class="red"><font class="redfont">已退貨</font></li>
								<li  class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">詳情</a></li>
							</s:elseif>
						</s:elseif>
		          	</ul>
		         </s:iterator>
				</div>
				<c:if test="${page.totalPage>1}">
				  <u:newPage href="/web/userOrder!getOrderlist.action?payStatus=${payStatus}&status=${status}&orderId=${orderId }&storeName=${storeName }&startTime=${startTime }&endTime=${endTime }&oidStatus=${oidStatus }&couponCode=${couponCode }&page.pageNum=@"></u:newPage>
				  <script type="text/javascript">
					function toPage(){
						var pageNum = $("#pageValue").val();
					   	window.location.href="/web/userOrder!getOrderlist.action?payStatus=${payStatus}&status=${status}&orderId=${orderId }&storeName=${storeName }&startTime=${startTime }&endTime=${endTime }&oidStatus=${oidStatus }&couponCode=${couponCode }&page.pageNum="+pageNum;
					
					}
				 </script>
			 </c:if>
				
		  	</div>
		</div>
		<!--End right_Frame-->
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		
		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 
	