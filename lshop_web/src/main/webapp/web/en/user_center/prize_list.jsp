<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _Tvpad Mall</title>
		<link rel="stylesheet" type="text/css" href="${resDomain}/en/res/js/ymPrompt/skin/vista/ymPrompt.css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>	
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/en/res/js/ymPrompt/ymPrompt.js" ></script>
		<script type="text/javascript" src="${resDomain}/en/res/js/userCenter.js"></script>
		<script type="text/javascript" src="${resDomain}/en/res/js/address.js"></script>
		<style type="text/css">
		#addressForm input[type="text"]{color:#666;}
		</style>
		
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/en/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> My prizes</span></h1> 
		<div class="order_record">
		    
			<table id="c_prize_list" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e3e2e2">
              <tr>
               <td width="29%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Prize</td>
               <td width="25%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Winning Time</td>
               <td width="28%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Redemption Deadline</td>
               <td width="18%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Operate</td>
              </tr>
              <c:if test="${not empty pagination}">
              <c:foreach items="${pagination.list }" var="prize">
              <tr>
               <td width="35%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${prize.prizeName}</td>
               <td width="25%" height="30" align="center" valign="middle" bgcolor="#FFFFFF"><fmt:formatDate value="${prize.winDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
               <td width="15%" height="30" align="center" valign="middle" bgcolor="#FFFFFF"><fmt:formatDate value="${prize.endTicketDate}" pattern="yyyy-MM-dd"/></td>
               <td width="15%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">
               <c:if test="${prize.prizeType == 1}">
               <c:choose>
               <c:when test="${prize.couponStatus == 1}">
               <a href="/web/prize!getCoupon.action?couponCode=${prize.prizeCode}" class="c-prize-btn" >Details</a>
               </c:when>
               <c:when test="${prize.couponStatus == 2}">Used</c:when>
               <c:when test="${prize.couponStatus == 3}">Invalid</c:when>
               <c:when test="${prize.couponStatus == 4}">Expired</c:when>
               </c:choose>
               </c:if>
               <c:if test="${prize.prizeType == 2}">
	               <c:if test="${prize.status == 0}">
		               <c:if test="${nowDate < prize.endTicketDate}">
		               <a href="/web/prize!toAddDeliver.action?prizeCode=${prize.code}" class="c-prize-btn">Redeem now</a>
		               </c:if>
		               <c:if test="${nowDate >= prize.endTicketDate}">Expired</c:if>
	               </c:if>
	               <c:if test="${prize.status == 1 || prize.status == 2}">
	               <a href="/web/prize!getDeliver.action?prizeCode=${prize.code}" class="c-prize-btn">Shipment Status</a>
	               </c:if>
               </c:if>
               </td>
              </tr>
              </c:foreach>
              <c:if test="${empty pagination.list}">
              <tr><td height="30" align="center" bgcolor="#FFFFFF" colspan="4">No Record!</td></tr>
              </c:if>
              </c:if>
			</table>
			<c:if test="${not empty pagination && pagination.totalPage>1}">
			  <fmt:newPage href="/web/prize!prizeList.action?pageNo=@" language="en"></fmt:newPage>
			  <script type="text/javascript">
				function toPage(){
					var pageNum = $("#pageValue").val();
				   	window.location.href="/web/prize!prizeList.action?pageNo="+pageNum;
				}
			 </script>
		 	</c:if>
		</div>
		  
		  <div class="cb"></div>	
			</div>
			 <!--End right_Frame-->
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
				
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
		<!-- 引出框 -->
	<div id="mask" style="width: 100%;height: 100%;position: absolute;background-color: #000;opacity: 0.5;top: 0;left: 0;z-index: 170;filter:alpha(opacity=10);display: none;"></div>
	<div id="mask_content" style="position: absolute;z-index: 200;background:white;display: none;top:50%;left:50%;"></div>
	<script type="text/javascript">
	//打开弹框
	$('#c_prize_list').delegate('.c-prize-btn', 'click', function(e){
		e.preventDefault();
		e.stopPropagation();
		//dialog
		//防止window scroll
		document.documentElement.style.overflow="hidden";
		var scrollTop = $(document).scrollTop();
		$('#mask').css({top: scrollTop+"px"}).show();
		var url = $(this).attr('href');
		$.post(url, function(data){
			var $ct = $('#mask_content');
			$ct.empty().append(data);
			var h = $ct.height()/2 - scrollTop;
			$ct.css({"margin": (-h)+"px 0 0 -"+($ct.width()/2)+"px"}).show();
		}, 'html');
	});
	//弹框事件
	$('#mask_content').delegate('.c-close-mask', 'click', function(e){
		$('#mask_content').trigger('closeMask');
	}).bind('closeMask', function(e){
		$('#mask').hide();
		$('#mask_content').hide();
		//恢复window scroll
		document.documentElement.style.overflow="";
	}).bind('heightChange', function(e){
		var $ct = $('#mask_content');
		var scrollTop = $(document).scrollTop();
		$ct.css({"margin": "-"+($ct.height()/2 - scrollTop)+"px 0 0 -"+($ct.width()/2)+"px"});
	});
	$('#mask').click(function(e){
		$('#mask_content').trigger('closeMask');
	});
	</script>
	</body>
</html>