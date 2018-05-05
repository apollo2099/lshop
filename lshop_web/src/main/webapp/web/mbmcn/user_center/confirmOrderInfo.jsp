<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/mbmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/mbmcn/common/top.jsp"%>
	</head>
	<body>
			
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/mbmcn/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/mbmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a>--><a href="/web/userCenterManage!getAccount.action">用户中心</a>--></font> 现在支付</span></h1> 
			   <div class="usercenter_box">
			     <div class="order"> <span class="order_t"><font class="fontwz">订单确认,清您核对订单信息</font></span>
		             <ul>
		               <li class="wd1">联系人：</li>
		               <li class="wd2">${lvOrderAdress.relName }</li>
		             </ul>
			       <ul>
		               <li class="wd1">联系电话：</li>
			         <li class="wd2">${lvOrderAdress.mobile }</li>
		           </ul>
		            <ul>
		               <li class="wd1">E-mail：</li>
			         <li class="wd2">${lvOrder.userEmail }</li>
		           </ul>
			       <ul>
		               <li class="wd1">付款方式：</li>
			         <li class="wd2 orangefont">
			         	<s:if test="#request.lvOrder.paymethod==3">
		            		支付宝
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==4">
		            		西联汇款
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==11 or #request.lvOrder.paymethod==15">
		            		Visa国际信用卡或借记卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==12 or #request.lvOrder.paymethod==16">
		            		Master国际信用卡或借记卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==13 or #request.lvOrder.paymethod==17">
		            		JCB国际信用卡或借记卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==14">
		            		VISA/MASTER
		            	</s:if>
			         </li>
		           </ul>
			       <ul>
		               <li class="wd1">邮政编码：</li>
			         <li class="wd2">${lvOrderAdress.postCode }</li>
		           </ul>
		           <ul>
		           	<li class="wd1"></li>
		           	<li class="wd2"></li>
		           </ul>
			       <ul class="long">
		               <li class="wd1">收货地址：</li>
			         <li class="wd3" style="word-break:break-all">
		         		<c:if test="${lvOrderAdress.contryId==100023}">
							${lvOrderAdress.contryName }&nbsp;${lvOrderAdress.provinceName}&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.adress }
						</c:if>
						<c:if test="${lvOrderAdress.contryId!=100023}">
							${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName}&nbsp;${lvOrderAdress.contryName }
						</c:if>
			         </li>
		           </ul>
			       <ul class="long">
		               <li class="wd1">其他备注说明：</li>
			         <li class="wd3" style="word-break:break-all">${lvOrder.orderRemark }</li>
		           </ul>
			       <ul class="long">
		               <li class="wd1">支付状态：</li>
			         <li class="long">
				         <s:if test="#request.lvOrder.payStatus==0">
			          		<font class="redfont">待付款</font> <img src="${resDomain}/mbmcn/res/images/personel_center_icon03.gif" width="16" height="16" /> <a href="/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }&shopFlag=${lvOrder.storeId }">现在支付</a>
			          	</s:if>
			          	<s:elseif test="#request.lvOrder.payStatus==2">
			          		<font class="redfont">已付款，未确认</font> 
			          	</s:elseif>
			          	<s:elseif test="#request.lvOrder.payStatus==1">
			          		<s:if test="#request.lvOrder.status==0">
			          			<font class="redfont">已付款，待发货</font> 
			          		</s:if>
			          		<s:elseif test="#request.lvOrder.status==1">
			          			<font class="redfont">已付款,已发货</font> 
			          		</s:elseif>
			          		<s:elseif test="#request.lvOrder.status==2">
			          			<font class="redfont">待评价</font> 
			          		</s:elseif>
			          		<s:elseif test="#request.lvOrder.status==4">
			          			<font class="redfont">已完成</font> 
			          		</s:elseif>
			          		<s:elseif test="#request.lvOrder.status==3">
			          			<font class="redfont">已退货</font> 
			          		</s:elseif>
			          	</s:elseif>
			         </li>
		           </ul>
			       <table width="710" height="188" border="0" cellpadding="0" cellspacing="1" bgcolor="#E8E5E5">
		               <tr>
		                 <td height="33" colspan="6" bgcolor="#F9F8F8"><font class="redfont fontwz redfont">订单号码：${lvOrder.oid}</font></td>
		               </tr>
		               <tr style="text-align:center;">
		                 <td height="28" bgcolor="#F9F8F8" style="text-align:center;"></td>
		                 <td bgcolor="#F9F8F8" style="text-align:center;"> 商品名称 </td>
		                 <td bgcolor="#F9F8F8" style="text-align:center;"> 价格 </td>
		                 <td bgcolor="#F9F8F8" style="text-align:center;">购买数量 </td>
		                 <td bgcolor="#F9F8F8" style="text-align:center;">小计金额 </td>
		               </tr>
		               <c:foreach items="${objList}" var="obj">
		               <tr style="text-align:center;">
		                 <td height="80" bgcolor="#F9F8F8" style="text-align:center;" ><img src="${obj[0].pimage }" width="70px" height="60px"/></td>
		                 <td bgcolor="#F9F8F8" style="text-align:center;"><div class="hid3" title="${obj[0].productName }">${obj[0].productName }</div></td>
		                 <td bgcolor="#F9F8F8" style="text-align:center;"><font class="redfont fontwz">${lvOrder.currency} ${obj[1].oprice }</font></td>
		                 <td bgcolor="#F9F8F8" style="text-align:center;"><font>${obj[1].pnum }</font></td>
		                 <td bgcolor="#F9F8F8" style="text-align:center;"><font class="redfont fontwz">${lvOrder.currency} ${obj[2] }</font></td>
		               </tr>
		               </c:foreach>
		               <tr style="text-align:right;">
		               	 <td height="42" colspan="5" bgcolor="#F9F8F8" style="padding-right:30px;"><div align="right">商品总金额：${lvOrder.currency} ${allAmount} + 运费:${lvOrder.currency} ${lvOrder.postprice }</div></td>
		               </tr>
		               <tr style="text-align:right;">
		               	 <td height="42" colspan="5" bgcolor="#F9F8F8" style="padding-right:30px;"><div align="right">应付金额：<font class="fontwz redfont">${lvOrder.currency} ${lvOrder.totalPrice }</font></div></td>
		               </tr>        
		           </table>

		           	<span class="close_btn">
			       		<input type="button" onclick="javascript:location.href='/web/userOrder!toPay.action?oid=${lvOrder.oid}';" value="确认提交" class="user_center_bt" style="CURSOR: pointer; "/>
			       		<input type="button" onclick="javascript:history.go(-1);" value="返回" class="user_center_bt" style="CURSOR: pointer; "/>	
		           </span> 
		           </div>
			   </div>
		  </div>
			 <!--End right_Frame-->
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/mbmcn/common/footer.jsp" %>
	
	</body>
</html> 