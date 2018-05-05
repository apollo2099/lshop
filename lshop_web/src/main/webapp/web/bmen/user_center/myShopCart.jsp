<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmen/res/css/css.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/cart.js"></script>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
	
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> My Cart</span></h1> 
				<div class="usercenter_box">
				  <div class="gouwuche">
				  <!-- 判断购物车是否为空 -->
				  <c:if test="${not empty objList}">
				   <p class="gouwuche1"><span class="xz"><input name="allBox" value="" type="checkbox" onclick="checkAll(this);" /> 
				    All</span> <span class="xz_text"><input type="button" onclick="deleteAllCheckData('/web/userCenterManage!delCartList.action');" value="Bulk Delete" class="user_center_bt03" /> </span><span class="xz_text1">Please check all the items you want to buy and check out accordingly.</span></p>
				  	<c:foreach items="${objList}" var="ob" varStatus="outStatus">
						<table width="747" height="30" border="0" cellpadding="0" cellspacing="1" bgcolor="#dbdbdb">
			           		<tr>
			           			<td height="30" colspan="6" bgcolor="#ffffff">
					     			<table width="747" border="0" cellspacing="0" cellpadding="0">
			              				<tr>
			               					 <td height="51" align="left">Seller：${ob[0].name }
			               					<input type="button" onclick="deleteShopData('${outStatus.count }','/web/userCenterManage!delCartList.action');" value="Bulk Delete" class="user_center_bt03" /></td>
			              				</tr>
			            			</table>		   
			           			 </td>
			           		</tr>
		          			<tr>
						        <td width="38" height="30" align="center" bgcolor="#ffffff"><input  type="checkbox"  value="" name="box${outStatus.count }" onclick="checkBox('${outStatus.count }',this);"/></td>
					           <td width="260" align="center" bgcolor="#eeeeee"><span class="STYLE1">Item Name</span></td>
					           <td width="60" align="center" bgcolor="#eeeeee"><span class="STYLE1">Price</span></td>
					           <td width="80" align="center" bgcolor="#eeeeee"><span class="STYLE1">Quantity</span></td>
					           <td width="80" align="center" bgcolor="#eeeeee"><span class="STYLE1">Subtotal</span></td>
					           <td width="80" align="center" bgcolor="#eeeeee"><span class="STYLE1">Operation</span></td>
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
				    				 	<span class="jian"><a href="javascript:changeNum('del','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"><img src="${resDomain}/bmen/res/images/jian.gif" border="0" /></a></span>
					 					<span class="shuliang"><input type="text" class="input0" id="_${outStatus.count }num${status.count }" name="num" value="${obj[0].shopNum }" readonly/></span>
					 					<span class="jian"><a href="javascript:changeNum('add','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"><img src="${resDomain}/bmen/res/images/jia.gif" border="0" /></a></span>
					 				</p>
					 			</td>
		        		 		<td width="60" height="82" align="center" bgcolor="#ffffff"><span class="price1"><strong>USD <span id="_${outStatus.count }price${status.count }">${obj[2] }</span></strong></span></td>
		         				<td width="60" height="82" align="center" bgcolor="#ffffff"><a href="#" onclick="delCart('${obj[0].code}','${outStatus.count }','${status.count }','${obj[0].shopPrice }')" class="sc">Delete</a></td>
		         			</tr>
		         			</c:foreach>
		         			</c:if>
		         			<tr>
		        				<td height="30" colspan="6" align="right" bgcolor="#eeeeee" style="font-size:14px; font-weight:bold; padding:0 10px 0 0;">Total：<span class="price1">USD <span id="allAmount${outStatus.count }">${ob[2] }</span></span></td>
		         			</tr>
		        			<tr>
		        				<td height="75" colspan="6" align="right" bgcolor="#ffffff" style="padding:0 10px 0 0;"><input type="button" class="btn04" id="regbtn"  onclick="showInfo('${ob[0].storeFlag }','${outStatus.count }');" value="Check Out" /></td>
		       				</tr>
		      			</table>
		      		</c:foreach>
				  </c:if>
				  <c:if test="${empty objList}">
				  	<div class="search_none"> 
				   		<p class="biaoqing" align="center"><span class="search_text"><img src="${resDomain}/bmen/res/images/biaoqing.gif" /><br />You have not yet added any items to your Shopping Cart, please choose an item first!</span></p>
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
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 