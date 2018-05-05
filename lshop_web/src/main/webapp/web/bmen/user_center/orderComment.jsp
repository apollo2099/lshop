<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/comment.js"></script>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
			<div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Leave Reviews</span></h1> 
				<div class="buy_order">
					<ul class="title">Item Details</ul>
					<ul>
						<li class="buy_order01">
							<p>&nbsp;</p>
							<p class="title">Item Name</p>
							<p>Price</p>
							<p>Quantity</p>
							<p>Subtotal</p>
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
					<ul class="sum">Total：${lvOrder.currency} ${allAmount} + Freight:${lvOrder.currency} ${lvOrder.postprice }</a></ul>
					<ul class="sum01">Amount Payable：<font class="redfont">${lvOrder.currency} ${lvOrder.totalPrice }</font></a></ul>
					<ul class="wz">
					  Please leave reviews on the item only.For question during transaction and delivery service, please contact our customer service! Thank you for your time!</a>
					</ul>
				</div>
				<div class="comment">
					<form  name="myform" action="/web/userOrder!saveComment.action" method="post">
						<input type="hidden" name="lvOrderComment.score" id="scoreid"  value="0"/>
						<input type="hidden" name="lvOrderComment.orderId"   value="${lvOrder.oid}"/>
						<input type="hidden" name="lvOrderComment.contryId" value="${lvOrderAdress.contryId}"/>
						<input type="hidden" name="lvOrderComment.grade" id="gradeId"  value="0"/>	
						<input type="hidden" name="shopFlag"   value="${lvOrder.storeId}"/>	
						<ul>Score</ul>
						<ul class="pf">
							<li>
								<img src="${resDomain}/bmen/res/images/wjx.gif" border="0" onclick="dograde(1,'${resDomain}/bmen')" onmousemove="moveStar(1,'${resDomain}/bmen')"  onmouseout="outStar('${resDomain}/bmen')"    id="image_1"/>
								<img src="${resDomain}/bmen/res/images/wjx.gif" onmousemove="moveStar(2,'${resDomain}/bmen')"  onmouseout="outStar('${resDomain}/bmen')"  border="0" onclick="dograde(2,'${resDomain}/bmen')" id="image_2"/>
								<img src="${resDomain}/bmen/res/images/wjx.gif" onmousemove="moveStar(3,'${resDomain}/bmen')"  onmouseout="outStar('${resDomain}/bmen')"  onclick="dograde(3,'${resDomain}/bmen')"  id="image_3" border="0"/>
								<img src="${resDomain}/bmen/res/images/wjx.gif" onclick="dograde(4,'${resDomain}/bmen')" onmousemove="moveStar(4,'${resDomain}/bmen')"  onmouseout="outStar('${resDomain}/bmen')" id="image_4"  width="30" height="29" border="0"/>
								<img src="${resDomain}/bmen/res/images/wjx_h.gif" onclick="dograde(5,'${resDomain}/bmen')" onmousemove="moveStar(5,'${resDomain}/bmen')"  onmouseout="outStar('${resDomain}/bmen')" id="image_5"  width="30" height="29" border="0" />
							</li>
							<li>Please click on the Star to score the item.</li>
							<li>
								<input name="grade" type="checkbox" disabled="disabled"  onclick="checkBoxValidate(0)" value="3" /> Good
								<input name="grade" type="checkbox"  disabled="disabled" onclick="checkBoxValidate(1)"  value="2" /> Medium
								<input name="grade" type="checkbox"  disabled="disabled" onclick="checkBoxValidate(2)" value="1" /> Bad
							</li>
						</ul>
						<ul>Content</ul>
						<ul class="pf">
							<li><textarea name="lvOrderComment.content" id="contentid" class="input1" onkeyup="changNumKeyValue()" onkeypress="changNumKeyValue()"    onfocus="if(this.value=='0/500')this.value=''">0/500</textarea><span id="contentNumId">0</span>/500</li>
							<li><input type="button" onclick="doAddComment();" value="Submit" class="user_center_bt" /></li>
						</ul>
					</form>	
				</div>
			</div>
		 <!--End right_Frame-->
		</div>
		<!--End content-->
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>		
		<!-- footer-->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 