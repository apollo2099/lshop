<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${resDomain}/tvpadcn/res/js/ymPrompt/skin/vista/ymPrompt.css" />
<script type="text/javascript" src="${resDomain}/tvpadcn/res/js/ymPrompt/ymPrompt.js" ></script>		
</head>
<body>
<!-- top -->
<%@include file="/web/tvpadcn/common/top.jsp" %>

<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpadcn/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 我的訂單</span></h1> 
		<div class="usercenter_box">
		<div class="orderlist">
          <ul class="title">
            <li class="wdx1">訂單號</li>
            <li class="wdx2">下單時間</li>
            <li class="red">訂單金額</li>
            <li>付款方式</li>
            <li class="red">訂單狀態</li>
            <li>詳情</li>
            <li class="red">操作</li>
          </ul>
          <s:iterator value="page.list" status="entry">
          <ul <s:if test="#entry.count%2==1"></s:if><s:else>class="bluebg"</s:else>>
            <li class="wdx1"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a></li>
            <li class="wdx2"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></li>
            <li class="red"> ${currency} ${totalPrice}</li>	
            <li title='<cus:payName showLength="" payValue="${paymethod}"></cus:payName>'><cus:payName showLength="5" payValue="${paymethod}"></cus:payName></li>
            <s:if test="payStatus==0">
            	<li class="red"><font class="redfont">待付款</font></li>
            	<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">查看</a> </li>
            	<li  class="blue">
            		<s:if test="paymethod==4">
					   <a href="/web/userOrder!toOrderWestern.action?oid=${oid }" >提交西联信息</a>
				     </s:if>
				     <s:else>
					   <a href="/web/userOrder!toConfirmPay.action?oid=${oid }">现在支付</a>
				     </s:else>
				</li>
            </s:if>	
            <s:elseif test="payStatus==2">
				 <li class="red"><font class="redfont">已付款,未确认</font></li>
				 <li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">查看</a> </li>
				 <li>--</li>
			</s:elseif>
			<s:elseif test="payStatus==1">
				<s:if test="status==0">
					<li class="red"><font class="redfont">已付款,待发货</font></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">查看</a> </li>
					<li  class="blue"><a href="javascript:void(0)"  onclick="showRemind('${oid }');">提醒卖家发货</a></li>
				</s:if>
				<s:elseif test="status==1">
					<li class="red"><font class="redfont">已付款,已发货</font></li>
					<li class="blue"><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">查看</a> <a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}">物流详情</a></li>
					<li  class="blue"><a href="javascript:void(0)" onclick="confirmShouhuo('${code }');">确认收到货</a></li>
				</s:elseif>
				<s:elseif test="status==2">
					<li class="red"><font class="redfont">待评价</font></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">查看</a> </li>
					<li  class="blue"><a href="/web/userOrder!toOrderComment.action?oid=${oid }">评价</a></li>
				</s:elseif>
				<s:elseif test="status==4">
					<li class="red"><font class="redfont">已完成</font></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">查看</a> </li>
					<li  class="blue">--</li>
				</s:elseif>
				<s:elseif test="status==3">
					<li class="red"><font class="redfont">已退货</font></li>
					<li><a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">查看</a> </li>
					<li  class="blue">--</li>
				</s:elseif>
			</s:elseif>
          </ul>
         </s:iterator>
		</div>
		<s:if test="page.totalCount>page.numPerPage">
			<ul class="page">
			  <u:page href="/web/userOrder!getOrderlist.action?payStatus=${payStatus}&status=${status}&page.pageNum=@" language="cn">
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
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 
<script type="text/javascript">
function confirmShouhuo(code){
	ymPrompt.confirmInfo('<strong>确认收到货</strong>',null,null,'温馨提示',function handler(tp){if(tp=='ok'){
		  window.location.href="/web/userOrder!confirmShouhuo.action?code="+code;
	  }
	if(tp=='cancel'){
		ymPrompt.close();
	  }} );
}

function showRemind(oid){
	$.ajax({
	   type: "POST",
	   url: "/web/userOrder!remindOrder.action",
	   data: "oid="+oid,
	   success: function(){
	   		ymPrompt.succeedInfo({
				message:'<strong>提醒发送成功，请耐心等待<br/><br/>或联系在线客服!</strong>',
				title:'温馨提示', 
				maxBtn:false
			});
	   }
	});

}


</script>	