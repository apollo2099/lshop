<%@ page language="java" pageEncoding="utf-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta content="telephone=no" name="format-detection" />
<title>TVpad商城_填写订单</title>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<link href="${resDomain}/mtmcn/res/css/style.css" rel="stylesheet" type="text/css">
<%long timeFlag = new Date().getTime(); %>
<link href="${resDomain}/mtmcn/res/css/development.css?time=<%=timeFlag %>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/FomrValidate.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/header.js"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/num_cal.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/searchSelect.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/dev.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/address.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/hammer.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/jquery.cookie.js"></script>
<style type="text/css">
table td{word-break: break-all;}
#pay_method_area ul li img { display:none;}
.inpu{color:#52555B;}
</style>
</head>

<body>
<input type="hidden" value="${lvStore.storeFlag}" id="shopFlag"/>
<header class="c_order">
   <div class="top">
      <div class="title1">
        <h1>填写订单</h1>
      </div>
   </div>
</header>

<article class="c_order">
  <div class="prodetal">
     <h2>收货人信息</h2>
	 <c:if test="${not empty errorMsg}">
		<div class="tvpad_tip">${errorMsg}</div>
	 </c:if>     
     <c:if test="${not empty dAddress}">
      <div class="protable">
      <a href="javascript:void(0)" id="add_ls_btn">
		   <div id="selected_add_btn" class="addbox">
		      <table width="94%" border="0"  cellpadding="0" cellspacing="0">
		    <tr>
		      <td width="30%" height="20" valign="top"></td>
		      <td width="70%" height="20" valign="top"></td>
		   </tr>
		  <tr>
		    <td height="25" align="left" valign="top"> 收&nbsp;货&nbsp;人：</td>
		    <td height="25" valign="top">${dAddress.relName }</td>
		  </tr>
		  <c:if test="${not empty dAddress.mobile}">
		  <tr>
		    <td height="25" align="left" valign="top">手&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
		    <td height="25" valign="top">${dAddress.mobile }</td>
		  </tr>
		  </c:if>
		  <c:if test="${not empty dAddress.tel}">
		  <tr>
		    <td height="25" align="left" valign="top">固定电话：</td>
		    <td height="25" valign="top">${dAddress.tel }</td>
		  </tr>
		  </c:if>
		   <tr>
		    <td height="25" align="left" valign="top">收货地址：</td>
		    <td height="25" valign="top">${dAddress.adress }${dAddress.cityName }${dAddress.provinceName }${dAddress.contryName }</td>
		  </tr>
		   <tr>
		    <td height="25" align="left" valign="top">邮&nbsp;&nbsp;&nbsp;&nbsp;编：</td>
		    <td height="25" valign="top" >${dAddress.postCode }</td>
		  </tr>
		  <tr>
		    <td width="18%" height="20" valign="top"></td>
		    <td width="82%" height="20" valign="top"></td>
		</tr>
		</table>
       </div>
       </a>
     </div> 
     </c:if>
     <c:if test="${empty dAddress}">
     <div class="odertip">
	     收货人信息为空，请添加！
	     <a href="javascript:void(0)" id="c_empty_add"></a>
	  </div>
     </c:if>
   </div>
</article>

<article class="c_order">
  <div class="prodetal">
     <h2>支付方式</h2>
     <c:if test="${not empty paymentList}">
     <div id="c_paymethod" class="protable"> 
       <span class="playsty" onClick="showhide(this)">
       	  请选择支付方式
       </span>
        <div id="pay_method_area" class="country" style="width:94%; margin:0 auto">
        	<input type="hidden" id="payCode" name="payCode" value="<c:if test="${not empty dPayment}">${dPayment.payValue}</c:if>"/> 
            <ul>
            	<c:foreach items="${paymentList}" var="payment" varStatus="status">
		       	  	<li value="${payment.payValue }">${payment.payName }</li>
		       	 </c:foreach>
            </ul>
          </div>
     </div> 
     </c:if>
   </div>
</article>

<article class="c_order">
  <form action="/web/shopCart!saveOrder.action" method="post" id="cartInfoForm">
  		<input type="hidden" name="shopFlag" value="${lvStore.storeFlag }"/>
		<input type="hidden" id="addressCode" name="addressCode" value="${dAddress.code }"/>
		<input type="hidden" id="paymethod" name="lvOrder.paymethod" value=""/>
		<input type="hidden" name="totalPrice" value="${totalAmount }"/>
		<input type="hidden" id="couponCode" name="couponCode" value="" />
		<input type="hidden" id="userCarriage" name="carriage" value="" />
		<input type="hidden" id="userProdAmount" name="prodAmount" value="" />
  <div class="prodetal">
     <h2>订单备注</h2>
     <div class="protable"> 
       <p>
       <textarea id="c_order_mark" name="orderRemark" placeholder="给卖家留言"></textarea>
       </p>
     </div>
    <div  class="reshop"> 
     <a href="javascript:toCar('${storeDomain}');">&lt;&lt;返回购物车修改</a>
   </div>
   </form>
</article>

<article class="c_order">
  <div class="prodlist">
    <a href="javascript:void(0);" id="view_pl_btn">查看商品清单</a>
  </div>
  <div class="prodlist">
    <a href="javascript:void(0);" id="view_coupon_btn"><span>优惠卷</span>
    <c:if test="${not empty couponList}"><span class="youhui">${fn:length(couponList)}张可用</span></c:if>
    </a>
  </div>
  <div id="c_use_coupon_tip" class="pro_tip" style="display:none;">
    已用券抵用<span class="usd" style="margin-left:20px">${lvStore.currency} <span class="c_use_coupon_amount">100.0</span></span>
  </div>
</article>

<article class="c_order">
  <div class="prodetal">
     <div class="prodetal_tomomey" id="totalPayArea">
        商品总金额：${lvStore.currency} <span id="prodAmount">${allAmount }</span> + 运费：${lvStore.currency} <span id="allCarriage">${carriage}</span>
   <span id="couponAmountArea" style="display:none;"> - 优惠券：${lvStore.currency} <span id="couponAmount" class="c_use_coupon_amount">0.0</span></span>
        <br/>
        应付总额：<strong class="usd">${lvStore.currency} <span id="amount">${totalAmount }</span></strong>
     </div>
     
     <input type="button" onclick="subCartInfo();" class="logins" value="提交订单"/>
     
  </div>
</article>

<!-- 收货地址列表 -->
<header class="c_addr_ls" style="display:none;">
	<div class="top">
		<div class="shopping">
			<div class="shoplebg1">
				<div class="shopicon1">
					<a href="/"></a>
				</div>
			</div>
		</div>
		<div class="title">
			<h1>收货人信息</h1>
		</div>
	</div>
</header>

<article class="c_addr_ls" style="display:none;">
	<div class="fanhui" style="height: 72px">
		<div class="fanhui_inner">
			<a href="javascript:void(0)" id="ls_order_back">返回</a>
			<a href="javascript:void(0)" id="addnew">新增</a>
			<div class="clear"></div>
		</div>
	</div>
	<c:if test="${empty addressList}">
	<div class="tipaddress">
		<p>您还没有添加收货地址，请点击“新增”按钮进行添加！</p>
	</div>
	</c:if>
</article>

<article id="addr_ls_area" class="c_addr_ls" style="display:none;">
	<c:if test="${not empty addressList}">
		<c:foreach items="${addressList}" var="address" varStatus="status">
			<div class="massinfomaw">
				<div class="conmaseg" addresscode="${address.code}" contryid="${address.contryId}" storeflag="${lvStore.storeFlag}" allamount="${allAmount }">

					<table width="94%" border="0" align="center">
						<tr>
							<td height="12" align="right"></td>
							<td height="12"></td>
						</tr>
						<tr>
							<td width="30%" height="25" align="left" valign="top" style="min-width:80px;">收&nbsp;货&nbsp;人：</td>
							<td width="70%" height="25" valign="top">${address.relName}</td>
						</tr>
						<c:if test="${not empty address.mobile}">
						<tr>
							<td height="25" align="left" valign="top">手&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
							<td height="25" valign="top">${address.mobile }</td>
						</tr>
						</c:if>
						<c:if test="${not empty address.tel}">
						<tr>
							<td height="25" align="left" valign="top">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
							<td height="25" valign="top">${address.tel }</td>
						</tr>
						</c:if>
						<tr>
							<td height="25" align="left" valign="top">收货地址：</td>
							<td height="25" valign="top">${address.adress
								}&nbsp;${address.cityName }&nbsp;${address.provinceName
								}&nbsp;${address.contryName }</td>
						</tr>
						<tr>
							<td height="25" align="left" valign="top">邮&nbsp;&nbsp;&nbsp;&nbsp;编：</td>
							<td height="25" valign="top">${address.postCode }</td>
						</tr>
						<tr>
							<td height="12" align="right"></td>
							<td height="12"></td>
						</tr>
					</table>
				</div>
				<div class="maseedit">
					<a href="javascript:void(0)" addresscode="${address.code}" class="addr_edit_btn"></a>
					<div class="tipedit">
						<i></i> <em></em>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</c:foreach>
	</c:if>
</article>
<!-- 新增收货地址 -->
<header class="add_addr" style="display:none;">
   <div class="top">
      <div class="title1">
        <h1 id="c_add_edit_addr_title">新增收货人信息</h1>
      </div>
   </div>
</header>
<article class="add_addr" style="display:none;">
	<div class="fanhui">
		<div class="fanhui_inner">
			<a href="javascript:void(0)" id="add_list_back">返回</a>
		</div>
	</div>
</article>

<article class="add_addr" style="display:none;">
	<div class="fanhui_inner">
		<div class="fanhu_tip">提示：非中国地区请输入英文收货信息</div>
	</div>

</article>

<article class="add_addr" style="display:none;">
	<form action="/web/shopCart!addAddress.action" method="post" id="addressForm" name="addressForm" onsubmit="return beforeAddressSubmit();">
		<input type="hidden" name="shopFlag" value="${lvStore.storeFlag}"/>
		<input type="hidden" name="lvAccountAddress.code" value="" id="editAddrCode"/>
		<table width="94%" border="0" align="center">
			<tr>
				<td height="40" colspan="2" class="fon18">收&nbsp;货&nbsp;人:</td>
			</tr>
			<tr>
				<td height="60" colspan="2">
					<div class="inputd">
						<input type="text" class="inpu" id="relName" value=""
							name="lvAccountAddress.relName" placeholder="请输入收货人姓名" />
						<div class="tip">
							<em></em> <span>请输入收货人姓名</span> <i></i> <b></b>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40" colspan="2" class="fon18">手 机：</td>
			</tr>
			<tr>
				<td height="60" colspan="2">
					<div class="inputd">
						<input type="text" class="inpu" id="mobile" value=""
							name="lvAccountAddress.mobile" placeholder="请输入手机号码" />
						<div class="tip">
							<em></em> <span>请输入正确的手机号码</span> <i></i> <b></b>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40" colspan="2" class="fon18">固定电话：</td>
			</tr>
			<tr>
				<td height="60" colspan="2">
					<div class="inputd">
						<input type="text" class="inpu" id="tel" value=""
							name="lvAccountAddress.tel" placeholder="请输入电话号码" />
						<div class="tip">
							<em></em> <span>请输入正确的固定电话</span> <i></i> <b></b>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td width="52%" height="40" class="fon18">国家：</td>
				<td width="48%" height="40" class="fon18">洲/省：</td>
			</tr>
			<tr>
				<td height="60" valign="top">
					<div class="boxsele" style="position: inherit;background-color:transparent;">
						<div id="countrySelect" class="searchSelect"  style="float:left;">
							<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
						</div>								
						<input type="hidden" name="lvAccountAddress.contryName"
							id="contryName" value="" />
						<input type="hidden" name="lvAccountAddress.contryId"
							id="contryId" value="" />	
						<div style="position:relative;">
							<div id="contry_tip" class="tip">
							<em></em>
							<span>请选择国家</span>
							<i></i>
							<b></b>
						</div>					
						</div>					

					</div>
				</td>
				<td height="60" valign="top">
					<div class="boxsele" style="width:100%;position: inherit;background-color:transparent;">
					<div id="provinceSelect" class="searchSelect"  style="float:left;">
						<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
					</div>
					<input type="hidden" name="lvAccountAddress.provinceId" id="provinceId"/>
					<input type="hidden" name="lvAccountAddress.provinceName" id="provinceName"/>	
					<div style="position:relative;">
						<div id="province_tip" class="tip">
							<em></em> <span>请选择洲/省</span> <i></i> <b></b>
						</div>					
					</div>					
					</div>
				</td>
			</tr>
			<tr>
				<td height="40" colspan="2" class="fon18">县/市：</td>
			</tr>
			<tr>
				<td height="60" colspan="2">
					<div class="inputd"style="position: inherit;">
						<div id="citySelect" class="searchSelect"  style="float:left;">
							<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
						</div>	
						<input type="hidden" name="lvAccountAddress.cityId" id="cityId"/>
						<input type="hidden" name="lvAccountAddress.cityName" id="cityName"/>
						<div style="position:relative;">
							<div id="city_tip" class="tip">
								<em></em> <span>请选择市/县</span> <i></i> <b></b>
							</div>						
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40" colspan="2" class="fon18">街道地址：</td>
			</tr>
			<tr>
				<td height="60" colspan="2">
					<div class="inputd">
						<input type="text" class="inpu" value=""
							name="lvAccountAddress.adress" id="adress" placeholder="请输入详细街道地址" />
						<div id="adress_tip" class="tip">
							<em></em> <span>请输入正确街道地址</span> <i></i> <b></b>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40" colspan="2" class="fon18">邮编：</td>
			</tr>
			<tr>
				<td height="60" colspan="2">
					<div class="inputd">
						<input type="text" class="inpu" id="postcodeId" value=""
							name="lvAccountAddress.postCode" placeholder="请输入邮编" />
						<div class="tip">
							<em></em> <span>请输入正确的密码</span> <i></i> <b></b>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40" colspan="2" class="md"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="保存" class="logins" onclick="doSubmitAddAddress();"/>
				</td>
			</tr>
		</table>
	</form>
</article>

<!-- 商品清单 -->
<header class="c_sc_product" style="display:none;">
	<div class="top">
		<div class="title1">
			<h1>商品清单</h1>

		</div>
	</div>
</header>

<article class="c_sc_product" style="display:none;">
	<div class="fanhui">
		<div class="fanhui_inner">
			<a href="javascript:void(0);" id="sl_order_back">返回</a>
		</div>
	</div>
</article>

<article class="c_sc_product" style="display:none;">
	<div class="mycart">
		<div class="cartlist">
			<c:foreach items="${objList}" var="obj" varStatus="status">
			<input type="hidden" value="${obj[0].productCode}" class="product"></input>
				<ul>
					<li>
						<div class="imgchek">
							<table width="100%" border="0">
								<tr>
									<td><a
										href="http://${lvStore.domainName }/products/${obj[1].code}.html">
											<img src="${obj[1].pimage }"
											width="90%" />
									</a></td>
								</tr>
							</table>
						</div>
						<div class="totalconts">
							<a
								href="http://${lvStore.domainName }/products/${obj[1].code}.html">
								<div class="imgname">
									<p>${obj[1].productName }</p>
								</div>
							</a>
							<div>
								<table width="100%" border="0">
									<tr>
										<td valign="middle" class="imgprcont">数量：X ${obj[0].shopNum }</td>
										<td valign="middle" class="imgjiag">USD ${obj[0].shopPrice }</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="clear"></div>
					</li>
				</ul>
			</c:foreach>
		</div>
		<!-- 优惠券，赠品，抽奖机会赠送信息 -->
		<div class="addcoupons" id="order_activity_tip" style="display:none;"></div>
        
	</div>
</article>
<!-- 使用优惠券 -->
<header class="c_coupon" style="display:none;">
   <div class="top">
      <div class="title1">
        <h1>使用优惠券</h1>
      </div>
   </div>
</header>
<article class="c_coupon" style="display:none;">
   <div class="fanhui" style="height:72px">
      <div class="fanhui_inner">
         <a href="javascript:void(0);" id="c_coupon_back_btn">返回</a>
         <div class="clear"></div>
      </div> 
   </div>
</article>
<article class="c_coupon" style="display:none;">
	<div style="margin-top:20px; background-color:#fff" class="mycomment">
	<div id="c_coupon_switch" class="commentbt" style="margin-bottom:20px">
	<c:if test="${not empty couponList}">
	  <span id="curspan" class="c_switch">可用优惠券（<i>${fn:length(couponList)}</i>）</span>
	</c:if>
	<c:if test="${empty couponList}">
		<span id="curspan" class="c_switch">可用优惠券（<i>0</i>）</span>
	</c:if>
	  <span class="c_switch">输入优惠券码</span>
	</div>
	<div class="myconmmentsubdivbox c_coupon_area" style="display:block; padding-bottom:0">
		<div id="c_view_coupon_rule" class="alfind">查看优惠券使用规则</div>
		<div id="c_coupon_ls">
		<c:if test="${not empty couponList}">
		<c:foreach items="${couponList}" var="coupon" varStatus="vs">
			<div class="massinfomaw" style="min-height: 100px;">
			<div class="conmaseg" style="min-height: 100px;">
				<table width="94%" border="0" align="center">
				<tr>
				    <td width="30%" height="12" align="right"></td>
				    <td width="75%" height="12"></td>
				</tr>
				<tr>
				  <td height="25" colspan="2" align="left" valign="top">${coupon.name}</td>
				  </tr>
				<tr>
				  <td width="30%" height="25" align="left" valign="top">面&nbsp;&nbsp;&nbsp;&nbsp;值：</td>
				  <td height="25" valign="top">${coupon.currency} ${coupon.amount}</td>
				</tr>
				<tr>
				  <td width="30%" height="25" align="left" valign="top">到期时间：</td>
				  <td height="25" valign="top"><fmt:formatDate value="${coupon.endTime}" pattern="yyyy年M月d日"/></td>
				</tr>
				<tr>
			      <td width="30%" height="12" align="right"></td>
			      <td height="12"></td>
			    </tr>
				</table>
		        <div class="tipedit1">
		            <i></i>
		            <em></em>
		         </div>
		    </div>
		      <div class="massrediou">
		         <span class="chiredio" code="${coupon.code}" amount="${coupon.amount}"></span>
		      </div>
		      <div class="clear"></div>
		   </div>
		</c:foreach>
		</c:if>
		<c:if test="${empty couponList}">
			<div style="width: 94%;margin: 0 auto;text-align: center;">帐户中无此订单可使用的优惠券</div>
		</c:if>
	   </div>
	   <div class="userinput"><input id="c_couon_btn" type="button" value="确 定" class="logins"></div>
    </div>
	<div class="replayconmmentsubdivbox c_coupon_area" style="display: none; ">
	<div id="c_coupon_code_input_area">    
	   <div class="userinput">
	      <input id="couponCodeVal" type="text" class="inpu" maxlength="12" placeholder="请输入优惠券码"/>
	      <div id="couponCodeTip" class="errortip"></div>
	   </div>
	   <div class="userinput"><input id="couponCodeBtn" type="button" value="确 定" class="logins"></div>
	</div>
	<div id="c_coupon_code_cacel_area" style="display:none;">
		<div class="listip">已输入优惠券码</div>
		<div class="listcoupons">
		<table width="310" border="0" align="center" class="nousertable">
		<tr>
		  <td width="30%" height="12" align="right"></td>
		  <td width="75%" height="12"></td>
		</tr>
		<tr>
		  <td height="25" colspan="2" align="left" valign="top" class="c_coupon_code_name">滿100减500券</td>
		  </tr>
		<tr>
		  <td width="30%" height="25" align="left" valign="top">面&nbsp;&nbsp;&nbsp;&nbsp;值：</td>
		  <td height="25" valign="top" class="c_coupon_code_amount">10 USD</td>
		</tr>
		
		<tr>
		  <td width="30%" height="25" align="left" valign="top">到期时间：</td>
		  <td height="25" valign="top" class="c_coupon_code_endtime">2014年6月6日</td>
		</tr>
		<tr>
		  <td width="30%" height="12" align="right"></td>
		  <td height="12"></td>
		</tr>
		</table>
		</div>
		<div class="userinput"><a id="c_coupon_code_cancel" href="javascript:void(0)" class="retu">取消使用</a></div>
	</div>
	</div>
	</div>
</article>
<!-- 分享 -->
<%@include file="/web/mtmcn/common/share.jsp" %>
<!-- footer -->
<%@include file="/web/mtmcn/common/footer.jsp" %>
<!-- 引出框 -->
<div id="c_coupon_rule_area" class="mark_tip">
	<div class="mark_tip_titile"><h2>使用规则</h2></div>
	<div class="tipdetail" style="text-align:left">
		<p>1.每次只可使用一张优惠券</p>
		<p>2.提交订单，优惠券即被用掉，取消订单不退回</p>
	</div>
	<div class="bt_marktip">
	  <a href="javascript:void(0)" class="c_cancle_btn" style="margin:0 auto; float:none; background-color:#0099FF; color:#fff">确定</a>
	</div>
</div>
<script>
//main
$(function(e){
	var paymethod = $('#payCode').val();
	if(paymethod) {
		$('#pay_method_area').find('li[value="'+paymethod+'"]').trigger('click');
	}
});
//页面显示
$('#add_ls_btn').click(function(e){
	$('.c_order').hide();
	$('.c_addr_ls').show();
});
$('#c_empty_add').click(function(e){
	$('.c_order').hide();
	$('.add_addr').show();
});
$('#addnew').click(function(e){
	if($('#addr_ls_area .massinfomaw').length>=5){
		//限止收货地址个数
		alert('常用地址大于5项,请先删除再增加!');
		return false;
	} else {
		$('.c_addr_ls').hide();
		$('.add_addr').show();
		$('.fastchannel').hide();
		//reset
		$('#c_add_edit_addr_title').text('新增收货人信息');
		$('#addressForm')[0].reset();
		$('#addressForm').attr('action', '/web/shopCart!addAddress.action');
	}
});
$('#ls_order_back').click(function(e){
	$('.c_addr_ls').hide();
	$('.c_order').show();
});
$('#add_list_back').click(function(e){
	$('.add_addr').hide();
	$('.c_addr_ls').show();
	$('.fastchannel').show();
});
$('#view_pl_btn').click(function(e){
	$('.c_order').hide();
	$('.c_sc_product').show();
	
	
	
	var prodAmount = parseFloat($('#prodAmount').text());
	var carriage = parseFloat($('#allCarriage').text());
	var couponAmount = parseFloat($('#couponAmount').text());
	var amount = NumCal.add(prodAmount, carriage);
	amount = NumCal.sub(amount, couponAmount);
	amount = NumCal.precise(amount);
	$('#amount').text(amount);
	
	//商品信息
	var productList=$(".cartlist").find(".product");
	var productStr="";
	for(var i=0;i<productList.length;i++){
		if(i==productList.length-1){
			productStr+=productList[i].value;
		}else{
			productStr+=productList[i].value+",";
		}
	}
	//查看订单活动
	var shopFlag = $('#shopFlag').val();
	$.post('web/shopCart!getOrderActivityTip.action', {orderPay: parseFloat(amount), shopFlag: shopFlag,productCodeList:productStr}, function(data){
		var couponTxt = $.trim(data);
		if(couponTxt) {
			$('#order_activity_tip').empty().append(couponTxt).show();
		} else {
			$('#order_activity_tip').hide();
		}
	});
	
});
$('#sl_order_back').click(function(e){
	$('.c_sc_product').hide();
	$('.c_order').show();
});
$('#view_coupon_btn').click(function(e){
	$('.c_order').hide();
	$('.c_coupon').show();
	window.scroll(0,0);
});
$('#c_coupon_back_btn').click(function(e){
	$('.c_order').show();
	$('.c_coupon').hide();
	window.scroll(0,0);
});
//公用函数
function showhide(obj){
    $(obj).next(".country").slideToggle();
}
//选择支付方式
$('#pay_method_area ul').on('click', 'li', function(e){
	$(this).parents(".country").prev().text($(this).text());
	$('#pay_method_area').hide();
	var val = $(this).attr("value");
	if(val != $("#payCode").val() ){
		//后台设置默认支付方式
		$.post('/web/shopCart!setDefaultPayment.action', {payValue: val, shopFlag: $('#shopFlag').val()});
	}
	$("#payCode").val(val);
	$("input[name='lvOrder.paymethod']").val($(this).attr("value"));
	$('#c_paymethod').attr('style', '');
});
//提交订单信息
function subCartInfo(){
	var a = $('#addressCode').val(), p = $('#paymethod').val();
	if(!a){
		alert('收货人信息不能为空');
		return false;
	}
	if(!p){
		alert('请选择支付方式');
		$('#c_paymethod').attr('style','border:1px solid #ffc600;color:red;');
		return false;
	}
	$("#cartInfoForm").submit();
}
//选择收货地址
$('#addr_ls_area').on('click', '.conmaseg', function(e){
	var addrcode = $(this).attr('addresscode');
	var tbl = $(this).html();
	$('#addressCode').val(addrcode);
	var $s = $('#selected_add_btn');
	$s.empty();
	$s.append(tbl);
	$s.find('td').attr('align', 'left');
	$('#ls_order_back').trigger('click');
	//设置默认收货人信息
	$.post('/web/shopCart!setDefaultAddress.action', {myAddress: addrcode}, function(data){});
	//修改运费
	var contryId = $(this).attr('contryid');
	var shopFlag = $(this).attr('storeflag');
	var allAmount = $(this).attr('allamount');
	$.ajax({
	   type: "POST",
	   url: "/web/shopCart!getCarriage.action",
	   data: "deliveryId="+contryId+"&shopFlag="+shopFlag,
	   success: function(data){
	   	 	$("#allCarriage").html(data);
	   	 	$("#amount").html(toFloat2(toFloat(toFloat(allAmount)+toFloat($("#allCarriage").html()))));
	   	 }
	});
	scroll(0,0);
});
//对话框
$('.c_cancle_btn').click(function(e){
	uncenter($('.mark_tip'));
});
//编辑收货地址
$('#addr_ls_area').on('click', '.addr_edit_btn', function(e){
	var eaddr = $(this).parents('.massinfomaw').find('.conmaseg').attr('addresscode');
	$.post('/web/shopCart!getAddressJson.action', {myAddress: eaddr}, function(data){
		$('.add_addr').show();
		$('.c_addr_ls').hide();
		$('#addressForm')[0].reset();
		$('#relName').val(data.relName);
		$('#mobile').val(data.mobile);
		$('#tel').val(data.tel);
		$('#postcodeId').val(data.postCode);

		if (data.contryId > 0) {
			$('#contryId').val(data.contryId);
			countrySelect.setSelectedId(data.contryId);
		} else {
			$('#contryId').val('');
		}
		$('#contryName').val(data.contryName);
		var provinceId = data.provinceId;
		var pattern = /\d/ig;
		if (!pattern.test(provinceId)) {
			provinceId = -1;
		}
		var cityId = data.cityId;
		if (!pattern.test(cityId)) {
			cityId = -1;
		}		
		if (provinceId > 0) {
			$('#provinceId').val(provinceId);
			$.getScript(resDomainArea+data.contryId+'.js',function(){
				provinceSelect.setData(provinceData);
				provinceSelect.setSelectedId(provinceId);
				if (cityId > 0) {
					$('#cityId').val(cityId);
					$.getScript(resDomainArea+data.contryId+'/'+provinceId+'.js',function(){
						citySelect.setData(cityData);
						citySelect.setSelectedId(cityId);
					});					
				} else {
					$('#cityId').val('');
					citySelect.setData([]);					
					citySelect.setText(data.cityName);
				}
				$('#cityName').val(data.cityName);				
			});			
		} else {
			$('#provinceId').val('');
			provinceSelect.setData([]);
			provinceSelect.setText(data.provinceName);
			$('#cityId').val('');
			$('#cityName').val(data.cityName);
			citySelect.setData([]);
			citySelect.setText(data.cityName);			
		}
		$('#provinceName').val(data.provinceName);

		//街道
		$('#adress').val(data.adress);
		$('#editAddrCode').val(data.code);
		//show edit address
		$('#c_add_edit_addr_title').text('编辑收货人信息');
		$('#addressForm').attr('action', '/web/shopCart!editAddress.action');
	});
});
</script>
<script type="text/javascript">
//表单
function checkPhone(){
	if($('#tel').val() == "" && $('#mobile').val() == ""){
		errorTip('#tel', '电话和手机必须填写其中一个');
		errorTip('#mobile', '电话和手机必须填写其中一个');
	}
}
$('#relName').bind('valiField', {name : '收货人',required:true,min:2,max:32,
	reg:'(?!\.*[\\u4E00-\\u9FA5])^\.*$', txt:'格式不正确'}, valiField);
$('#tel').bind('valiField', {name : '电话号码', max : 16, reg : '^[0-9\\-]{3,16}$',
	txt: '格式不正确', callback: 'checkPhone'}, valiField);
$('#mobile').bind('valiField', {name : '手机号码', max : 16, reg : '^[0-9]{3,16}$',
	txt: '格式不正确', callback: 'checkPhone'}, valiField);
$('#postcodeId').bind('valiField', {name : '邮编', required:true, max : 16, reg : '(?!\.*[\\u4E00-\\u9FA5])^\.*$',
	txt: '不能含有特殊字符'}, valiField);
$('#provinceName').bind('valiField', {name : '洲/省',required:true, max: 32}, valiField);
$('#cityName').bind('valiField', {name : '市/县',required:true, max: 32}, valiField);
$('#adress').bind('valiField', {name : '街道地址',required:true, max: 128}, valiField);
$('#contryId').bind('valiField', function(e){
	//国家不能为空
	if($('#contryId').val()){
		$('#contryId').removeClass('error');
		$('#contry_tip').hide();
	} else {
		$('#contryId').addClass('error');
		$('#contry_tip').show();
	}
});
$('#c_province').bind('valiField', function(e){
	//省份不能为空
	if($('#provinceName').val()){
		$('#c_province').removeClass('error');
		$('#province_tip').hide();
	} else {
		$('#c_province').addClass('error');
		$('#province_tip').show();
	}
});
//优惠券
$('#c_coupon_switch').on('click', '.c_switch', function(e){
	$('#curspan').attr('id', '');
	$(this).attr('id', 'curspan');
	var ind = $(this).index();
	$('.c_coupon_area').hide();
	$('.c_coupon_area:eq('+ind+')').show();
});
$('#c_view_coupon_rule').click(function(e){
	center($('#c_coupon_rule_area'));
});
$('#c_coupon_ls').on('click', '.chiredio', function(e){
	if($(this).attr('id') === 'mdu'){
		$('#mdu').attr('id','');
	} else {
		$('#mdu').attr('id','');
		$(this).attr('id', 'mdu');
	}
});
$('#c_couon_btn').click(function(e){
	var $m = $('#mdu');
	var couponNo = '';
	var couponAmount = 0;
	if($m.length > 0){
		couponNo = $('#mdu').attr('code');
		couponAmount = $('#mdu').attr('amount');
	}
	$('#view_coupon_btn').trigger('useCoupon', [couponNo, couponAmount]);
	//显示订单
	$('#c_coupon_back_btn').trigger('click');
});
$('#couponCodeBtn').click(function(e){
	var code = $('#couponCodeVal').val();
	//validate
	if(!/^\d{12}$/.test(code)){
		$('#couponCodeTip').text('优惠券码格式不正确!');
		return false;
	} else {
		$('#couponCodeTip').text('');
	}
	//check
	$.post('/web/shopCart!checkCouponCode.action', {myCoupon: code, shopFlag: $('#shopFlag').val()}, function(data){
		if(data.success) {
			var coupon = data.reObj;
			var couponAmount = NumCal.precise(coupon.amount);
			$('#couponCodeTip').text('');
			$('#c_coupon_code_input_area').hide();
			var $c = $('#c_coupon_code_cacel_area');
			$c.find('.c_coupon_code_name').text(coupon.name);
			$c.find('.c_coupon_code_amount').text(coupon.currency+" "+couponAmount);
			$c.find('.c_coupon_code_endtime').text(coupon.endTime);
			$c.show();
			//使用优惠券
			$('#view_coupon_btn').trigger('useCoupon', [coupon.code, couponAmount]);
			//显示订单
			$('#c_coupon_back_btn').trigger('click');
		} else {
			$('#couponCodeTip').text('优惠券码无效!');
		}
	});
});
$('#c_coupon_code_cancel').click(function(e){
	$('#view_coupon_btn').trigger('useCoupon', ['', '0.0']);
	$('#c_coupon_code_cacel_area').hide();
	$('#c_coupon_code_input_area').show();
	$('#couponCodeVal').focus();
});
//使用优惠券
$('#view_coupon_btn').bind('useCoupon', function(e, couponNo, couponAmount){
	$('#couponCode').val(couponNo);
	$('.c_use_coupon_amount').text(couponAmount);
	var amount = parseFloat(couponAmount);
	if(0 == amount) {
		$('#c_use_coupon_tip').hide();
		$('#couponAmountArea').hide();
	} else {
		$('#c_use_coupon_tip').show();
		$('#couponAmountArea').show();
	}
	$('#totalPayArea').trigger('changeOrderPay');
});
//购物车计算
$('#totalPayArea').bind('changeOrderPay', function(e){
	var prodAmount = parseFloat($('#prodAmount').text());
	var carriage = parseFloat($('#allCarriage').text());
	var couponAmount = parseFloat($('#couponAmount').text());
	var amount = NumCal.add(prodAmount, carriage);
	amount = NumCal.sub(amount, couponAmount);
	amount = NumCal.precise(amount);
	$('#amount').text(amount);
});
//提交前执行的函数
function beforeFormSubmit(){
	$('#contryId:visible').trigger('valiField');
	$('#c_province:visible').trigger('valiField');
	$('#c_city:visible').trigger('valiField');
	var $mark = $('#c_order_mark');
	if($mark.is(':visible')){
		if($mark.val() && $mark.val().length>160){
			$mark.addClass('error');
			alert("备注不得超过160位字符！");
		} else {
			$mark.removeClass('error');
			//订单提交
			//传回运费和商品中价格,校验是否发生变化
			$('#userProdAmount').val($('#prodAmount').text());
			$('#userCarriage').val($('#allCarriage').text());
		}
	}
}
var resDomainArea = '${resDomain}/www/res/area_en/';
initArea();
</script>

<!-- 引入google统计代码 -->
<!-- Google Code for &#36141;&#29289;&#36710; Conversion Page -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 978626603;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "SiXVCNu-3VcQq9DS0gM";
var google_remarketing_only = false;
/* ]]> */
</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="//www.googleadservices.com/pagead/conversion/978626603/?label=SiXVCNu-3VcQq9DS0gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>

</body>
</html>
