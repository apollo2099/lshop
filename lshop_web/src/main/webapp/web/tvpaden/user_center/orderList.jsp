<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${resDomain}/tvpaden/res/js/ymPrompt/skin/vista/ymPrompt.css" />
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/ymPrompt/ymPrompt.js" ></script>		
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>

<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> My Order</span></h1> 
		<div class="usercenter_box">
		<div class="orderlist">
          <ul class="title">
            <li class="wdx1">Order No.</li>
            <li class="wdx2">Order Date </li>
            <li>Amount</li>
            <li>Payment Method</li>
            <li>Order Status</li>
            <li>Details</li>
            <li>Action</li>
          </ul>
          
          <s:iterator value="page.list" status="entry">
          <ul <s:if test="#entry.count%2==1">class="cn"</s:if><s:else>class="bluebg1"</s:else>>
            <li class="wdx1"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a></li>
            <li class="wdx2"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></li>
            <li> ${currency} ${totalPrice}</li>	
            <li title='<cus:payName showLength="" payValue="${paymethod}"></cus:payName>'><cus:payName showLength="5" payValue="${paymethod}"></cus:payName></li>
            <s:if test="payStatus==0">
            	<li class="red"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">Awaiting payment</a> </li>
            	<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> </li>
            	<li>
            		<s:if test="paymethod==4">
					   <a href="/web/userOrder!toOrderWestern.action?oid=${oid }" >Submit Western Union information</a>
				     </s:if>
				     <s:else>
					   <a href="/web/userOrder!toConfirmPay.action?oid=${oid }">Pay it Now</a>
				     </s:else>
				</li>
            </s:if>	
            <s:elseif test="payStatus==2">
				 <li class="red"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">Paid, unconfirmed</a></li>
				 <li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> </li>
				 <li>--</li>
			</s:elseif>
			<s:elseif test="payStatus==1">
				<s:if test="status==0">
					<li class="red"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">Paid, awaiting shipping</a></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> </li>
					<li><a href="javascript:void(0)"  onclick="showRemind('${oid }');">Remind the seller of shipping</a></li>
				</s:if>
				<s:elseif test="status==1">
					<li class="red"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">Paid, deliveried</a></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> <a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}">Logistics details</a></li>
					<li><a href="javascript:void(0)" onclick="confirmShouhuo('${code }');">Confirm receipt</a></li>
				</s:elseif>
				<s:elseif test="status==2">
					<li class="red"> <a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">Awaiting comment</a></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> </li>
					<li><a href="/web/userOrder!toOrderComment.action?oid=${oid }">Comment</a></li>
				</s:elseif>
				<s:elseif test="status==4">
					<li class="red"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">Finished</a></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> </li>
					<li>--</li>
				</s:elseif>
				<s:elseif test="status==3">
					<li class="red"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">Returned</a></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">View</a> </li>
					<li>--</li>
				</s:elseif>
			</s:elseif>
          </ul>
         </s:iterator>
		</div>
		<s:if test="page.totalCount>page.numPerPage">
			<ul class="page">
			  <u:page href="/web/userOrder!getOrderlist.action?payStatus=${payStatus}&status=${status}&page.pageNum=@" language="en">
			  </u:page>
			   <script type="text/javascript">
				function gotoPage(num){
				   window.location.href="/web/userOrder!getOrderlist.action?payStatus=${payStatus}&status=${status}&page.pageNum="+num;
				
				}

				</script>
			 
			</ul>	
		</s:if>				
  	</div>
	 <!--End right_Frame-->
</div>
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
<script type="text/javascript">
function confirmShouhuo(code){
	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	ymPrompt.confirmInfo('<strong>Confirm receipt</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
		  window.location.href="/web/userOrder!confirmShouhuo.action?code="+code;
	  }
	if(tp=='cancel'){
		ymPrompt.close();
	  }} );
}

function showRemind(oid){
	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	$.ajax({
	   type: "POST",
	   url: "/web/userOrder!remindOrder.action",
	   data: "oid="+oid,
	   success: function(){
	   		ymPrompt.succeedInfo({
				message:'<strong>The reminder is successfully sent, please wait in pacient or contact the online customer service!</strong>',
				title:'Tips', 
				maxBtn:false,
				height:220
			});
	   }
	});

}


</script>	