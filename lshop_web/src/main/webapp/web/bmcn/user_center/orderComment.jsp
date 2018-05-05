<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/comment.js"></script>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 发表评论</span></h1> 
				<div class="buy_order">
					<ul class="title">商品信息</ul>
					<ul>
						<li class="buy_order01">
							<p>&nbsp;</p>
							<p class="title">商品名称</p>
							<p>价格</p>
							<p>购买数量</p>
							<p>小计金额</p>
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
					<ul class="sum">商品总金额：${lvOrder.currency} ${allAmount} + 运费：${lvOrder.currency} ${lvOrder.postprice }</a></ul>
					<ul class="sum01">应付金额：<font class="redfont">${lvOrder.currency} ${lvOrder.totalPrice }</font></a></ul>
					<ul class="wz">
					  只针对本商品，不要针对交易、配送等服务过程，有关服务过程的问题请联系客服，谢谢参与！</a>
					</ul>
				</div>
				<div class="comment">
					<form  name="myform" action="/web/userOrder!saveComment.action" method="post">
						<input type="hidden" name="lvOrderComment.score" id="scoreid"  value="0"/>
						<input type="hidden" name="lvOrderComment.orderId"   value="${lvOrder.oid}"/>
						<input type="hidden" name="lvOrderComment.contryId" value="${lvOrderAdress.contryId}"/>
						<input type="hidden" name="lvOrderComment.grade" id="gradeId"  value="0"/>	
						<input type="hidden" name="shopFlag"   value="${lvOrder.storeId}"/>	
						<ul>商品評分</ul>
						<ul class="pf">
							<li>
								<img src="${resDomain}/bmcn/res/images/wjx.gif" border="0" onclick="dograde(1,'${resDomain}/bmcn')" onmousemove="moveStar(1,'${resDomain}/bmcn')"  onmouseout="outStar('${resDomain}/bmcn')"    id="image_1"/>
								<img src="${resDomain}/bmcn/res/images/wjx.gif" onmousemove="moveStar(2,'${resDomain}/bmcn')"  onmouseout="outStar('${resDomain}/bmcn')"  border="0" onclick="dograde(2,'${resDomain}/bmcn')" id="image_2"/>
								<img src="${resDomain}/bmcn/res/images/wjx.gif" onmousemove="moveStar(3,'${resDomain}/bmcn')"  onmouseout="outStar('${resDomain}/bmcn')"  onclick="dograde(3,'${resDomain}/bmcn')"  id="image_3" border="0"/>
								<img src="${resDomain}/bmcn/res/images/wjx.gif" onclick="dograde(4,'${resDomain}/bmcn')" onmousemove="moveStar(4,'${resDomain}/bmcn')"  onmouseout="outStar('${resDomain}/bmcn')" id="image_4"  width="30" height="29" border="0"/>
								<img src="${resDomain}/bmcn/res/images/wjx_h.gif" onclick="dograde(5,'${resDomain}/bmcn')" onmousemove="moveStar(5,'${resDomain}/bmcn')"  onmouseout="outStar('${resDomain}/bmcn')" id="image_5"  width="30" height="29" border="0" />
							</li>
							<li>请点击星星进行评分</li>
							<li>
								<input name="grade" type="checkbox" disabled="disabled"  onclick="checkBoxValidate(0)" value="3" /> 好 评
								<input name="grade" type="checkbox"  disabled="disabled" onclick="checkBoxValidate(1)"  value="2" /> 中 评
								<input name="grade" type="checkbox"  disabled="disabled" onclick="checkBoxValidate(2)" value="1" /> 差 评
							</li>
						</ul>
						<ul>评论正文</ul>
						<ul class="pf">
							<li><textarea name="lvOrderComment.content" id="contentid" class="input1" onkeyup="changNumKeyValue()" onkeypress="changNumKeyValue()"    onfocus="if(this.value=='0/500')this.value=''">0/500</textarea><span id="contentNumId">0</span>/500</li>
							<li><input type="button" onclick="doAddComment();" value="确认提交" class="user_center_bt" /></li>
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
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 