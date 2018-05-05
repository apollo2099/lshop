<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${resDomain}/bmcn/res/js/ymPrompt/skin/vista/ymPrompt.css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/area_cn.js" async="async"></script>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/address.js"></script>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div class="content_main">
		<!-- left_frame -->
		<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>

		<div class="right_frame">
			<h1>
				<font class="bfont"><img
					src="${resDomain}/bmcn/res/images/icon02.gif" /><a
					href="/index.html">首页</a> --><a
					href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 我的订单
			</h1>
			
			<div class="order_record">
			<table id="c_prize_list" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e3e2e2">
              <tr>
               <td width="35%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">奖品</td>
               <td width="25%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">中奖时间</td>
               <td width="15%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">兑奖截止日期</td>
               <td width="15%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">操作</td>
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
               <a href="/web/prize!getCoupon.action?couponCode=${prize.prizeCode}" class="c-prize-btn" >查看详情</a>
               </c:when>
               <c:when test="${prize.couponStatus == 2}">已使用</c:when>
               <c:when test="${prize.couponStatus == 3}">已禁用</c:when>
               <c:when test="${prize.couponStatus == 4}">已过期</c:when>
               </c:choose>
               </c:if>
               <c:if test="${prize.prizeType == 2}">
               <c:if test="${prize.status == 0}">
               <c:if test="${nowDate < prize.endTicketDate}">
               <a href="/web/prize!toAddDeliver.action?prizeCode=${prize.code}" class="c-prize-btn">立即兑奖</a>
               </c:if>
               <c:if test="${nowDate >= prize.endTicketDate}">已过期</c:if>
               </c:if>
               <c:if test="${prize.status == 1 || prize.status == 2}">
               <a href="/web/prize!getDeliver.action?prizeCode=${prize.code}" class="c-prize-btn">查看收货详情</a>
               </c:if>
               </c:if>
               </td>
              </tr>
              </c:foreach>
              <c:if test="${empty pagination.list}">
              <tr><td height="30" align="center" bgcolor="#FFFFFF" colspan="4">没有记录!</td></tr>
              </c:if>
              </c:if>
              </table>
				<c:if test="${not empty pagination && pagination.totalPage>1}">
				<fmt:newPage href="/web/prize!prizeList.action?pageNo=@"></fmt:newPage>
				<script type="text/javascript">
				function toPage(){
					var pageNum = $("#pageValue").val();
				   	window.location.href="/web/prize!prizeList.action?pageNo="+pageNum;
				}
				</script>
				</c:if>
		</div><!-- end of order_record -->
		</div><!-- end of right_frame -->
		<!--End right_Frame-->
			 <div class="cb"></div>
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	<!-- 引出框 -->
	<div id="mask" style="width: 100%;height: 100%;position: absolute;background-color: #000;opacity: 0.5;top: 0;left: 0;z-index:70;filter:alpha(opacity=10);display: none;"></div>
	<div id="mask_content" style="position: absolute;z-index: 100;background:white;display: none;top:50%;left:50%;"></div>
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