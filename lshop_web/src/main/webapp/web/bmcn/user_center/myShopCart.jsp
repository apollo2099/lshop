<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmcn/res/css/css.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/cart.js"></script>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
	
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 我的购物车 </span></h1> 
				<div class="usercenter_box">
				  <div class="gouwuche">
				  <!-- 判断购物车是否为空 -->
				  <c:if test="${not empty objList}">
				   <p class="gouwuche1"><span class="xz"><input name="allBox" value="" type="checkbox" onclick="checkAll(this);" /> 
				   全选</span> <span class="xz_text"><a href="#" onclick="deleteAllCheckData('/web/userCenterManage!delCartList.action');"><img src="${resDomain}/bmcn/res/images/piliangshanchu.jpg" border="0" /></a></span><span class="xz_text1">请勾选您确认购买的商品，分商家进行结算支付</span></p>
				  	<c:foreach items="${objList}" var="ob" varStatus="outStatus">
						<table width="747" height="30" border="0" cellpadding="0" cellspacing="1" bgcolor="#dbdbdb">
			           		<tr>
			           			<td height="30" colspan="6" bgcolor="#ffffff">
					     			<table width="747" border="0" cellspacing="0" cellpadding="0">
			              				<tr>
			               					<td width="130" height="51" align="center">商家：${ob[0].name }</td>
			               					<td><a href="#" onclick="deleteShopData('${outStatus.count }','/web/userCenterManage!delCartList.action');"><img src="${resDomain}/bmcn/res/images/piliangshanchu.jpg" border="0" /></a></td>
			              				</tr>
			            			</table>		   
			           			 </td>
			           		</tr>
		          			<tr>
						        <td width="38" height="30" align="center" bgcolor="#ffffff"><input  type="checkbox"  value="" name="box${outStatus.count }" onclick="checkBox('${outStatus.count }',this);"/></td>
						        <td width="280" align="center" bgcolor="#eeeeee"><span class="STYLE1">商品信息</span></td>
						        <td width="60" align="center" bgcolor="#eeeeee"><span class="STYLE1">价格</span></td>
						        <td width="80" align="center" bgcolor="#eeeeee"><span class="STYLE1">购买数量</span></td>
						        <td width="80" align="center" bgcolor="#eeeeee"><span class="STYLE1">小计金额</span></td>
						        <td width="60" align="center" bgcolor="#eeeeee"><span class="STYLE1">操作</span></td>
			          		</tr>
			          		<c:if test="${mark==1}">
			          		<c:foreach items="${ob[1]}" var="obj" varStatus="status"> 
		         			<tr id="_${outStatus.count }cart${status.count }">
		         				<td width="38" height="82" align="center" bgcolor="#ffffff"><input name="inBox${outStatus.count }" value="${obj[0].code}" type="checkbox" onclick="checkInBox('${outStatus.count }',this);"/></td>
		         				<td height="82" align="center" valign="middle" bgcolor="#ffffff">
				  					<table width="100%" height="82" border="0" cellpadding="0" cellspacing="0">
		           						<tr>
		            						<td width="120" align="center" valign="middle"><img src="${obj[1].pimage }" width="70px" height="60px" /></td>
		            						<td width="380" align="left"><div class="hid2" title="${obj[1].productName }">${obj[1].productName }</div></td>
		          						</tr>
		         					</table>		
								</td>
		         				<td width="60" height="82" align="center" bgcolor="#ffffff"><span class="price1"><strong>USD <span id="_${outStatus.count }oPrice${status.count }">${obj[0].shopPrice }</span></strong></span></td>
		         				<td width="90" height="82" align="center" bgcolor="#ffffff">
				    				 <p class="gmsl">
				    				 	<span class="jian"><a href="javascript:changeNum('del','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"><img src="${resDomain}/bmcn/res/images/jian.gif" border="0" /></a></span>
					 					<span class="shuliang"><input type="text" class="input0" id="_${outStatus.count }num${status.count }" name="num" value="${obj[0].shopNum }" readonly/></span>
					 					<span class="jian"><a href="javascript:changeNum('add','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"><img src="${resDomain}/bmcn/res/images/jia.gif" border="0" /></a></span>
					 				</p>
					 			</td>
		        		 		<td width="60" height="82" align="center" bgcolor="#ffffff"><span class="price1"><strong>USD <span id="_${outStatus.count }price${status.count }">${obj[2] }</span></strong></span></td>
		         				<td width="60" height="82" align="center" bgcolor="#ffffff"><a href="#" onclick="delCart('${obj[0].code}','${outStatus.count }','${status.count }','${obj[0].shopPrice }')" class="sc">删除</a></td>
		         			</tr>
		         			</c:foreach>
		         			</c:if>
		         			<tr>
		        				<td height="30" colspan="6" align="right" bgcolor="#eeeeee" style="font-size:14px; font-weight:bold; padding:0 10px 0 0;">商品总金额：<span class="price1">USD <span id="allAmount${outStatus.count }">${ob[2] }</span></span></td>
		         			</tr>
		        			<tr>
		        				<td height="75" colspan="6" align="right" bgcolor="#ffffff" style="padding:0 10px 0 0;"><input type="button" class="btn04" id="regbtn"  onclick="showInfo('${ob[0].storeFlag }','${outStatus.count }');" value="去结算" /></td>
		       				</tr>
		      			</table>
		      		</c:foreach>
				  </c:if>
				  <c:if test="${empty objList}">
				  	<div class="search_none"> 
				   		<p class="biaoqing" align="center"><span class="search_text"><img src="${resDomain}/bmcn/res/images/biaoqing.gif" /><br/>请先添加商品至购物车！</span></p>
				  	</div>
				  </c:if>
		    </div>	
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