<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<gv:esi uri="/web/en/common/shopcart_header.jsp">
<div id="sc_top">
 	<div id="sc_head">
  		<div id="header">
    		<div id="help">
          		<ul>
		            <li class="yh" id="loginId"><a href="javascript:onshow('tx_b','loginDiv');">Login</a></li>
					<li class="tc" id="registerId"><a href="javascript:toRe('${storeDomain }');">Register</a></li>
					<li id="c_welcome" style="display:none;">Welcome!&nbsp;</li>
		            <li id="nameId" class="menu2" style="display:none;"><a id="nicknameId" href="${storeDomain}/web/userCenterManage!getAccount.action"></a>
		        	   <ul class="list" style="display:none;">
						  <li><a href="${storeDomain}/web/userOrder!getOrderlist.action">My Order</a></li>
		          		  <li><a href="${storeDomain}/web/userCenterManage!getAccount.action">My Account</a></li>
		          		  <li><a href="${storeDomain}/web/userCenterManage!getInfoDetail.action">My Profile</a></li>
						  <li><a href="${storeDomain}/web/userCenterManage!getAddressList.action">Billing Addr.</a></li>
						  <li><a href="${storeDomain}/web/userCenterManage!getCommentList.action">My Reviews</a></li>
						  <li class="logout"><a href="${storeDomain}/web/userCenterManage!logout.action">log out</a></li>
		        	   </ul>
		      		</li>
				    <li class="gouwu"><a href="#" onclick="toCar('${storeDomain}');">
				    <span id="shopCartNum" class="number"></span></a></li>
				    <div class="clear"></div>
         		</ul>
       		</div>
 			<div id="shoucang">
		  		<%-- <p><a href="javascript:addBookmark('TVpad Mall','http://en.itvpad.com/')"><img src="${resDomain}/en/res/images/shoucang.jpg" title="Add TVpad Mall to Favorites" width="16" height="38" /></a></p> --%>
		  		<a href="http://weibo.com/tvpad" class="xinlang_h" target="_blank" title="weibo">weibo</a>
		  		<a href="javascript:void(0);"  id="c_top_weixin_btn" class="weixi_h" title="Wechat">WeChat</a>
				<a href="https://www.facebook.com/mytvpad" class="f_h" target="_blank" title="facebook"></a>
				<span class="chian"><a href="http://www.mtvpad.com/index.html">[中文版]</a></span>
				<div class="clear"></div>
   			</div>
   			<div class="search">
				<img id="c_search_coll" style="cursor: pointer;" src="${resDomain}/en/res/images/src_bt.png">
				<div id="c_inner_search" class="inner_srarch">
				<form action="${storeDomain}/web/store!searchProducts.action" method="post" id="searchForm">
			  		<input name="str" type="text" maxlength="50" class="sc_wb" id="searchContent" value="TVpad4" />
			  		<input name="" type="submit" class="smit_src" value=" ">
			  	</form>
				</div>
			</div>
		</div>
  	</div>
	<div class="clear"></div>
	<div class="sc_logo">
	    <div class="sc_log">
			<div class="logo"><a href="${storeDomain}/index.html"><img src="<cus:store param='storeLogo'/>"/></a></div>
			<div class="clear"></div>
		</div>
	 </div>
	

	<!-- begin login -->
	<div id="loginDiv"   style="display:none;background-color:#000000;width:800px;height:600px;position:absolute;top:0px;left:0px;text-align:center;z-index:9000;filter:alpha(opacity=10);opacity:0.3"></div>
	<div id="tx_b" style="top:124px;position: absolute;display: none;z-index:9999;">
		<div class="pop_up_box" style="background:white;">
			<span class="box_l">User Login<span class="box_r"><a href="javascript:onhide('tx_b','loginDiv');"><img src="${resDomain}/en/res/images/close.gif" border="0" /></a></span></span>
			<div style="color: red; margin: 10px 0 0 90px;"><span id="h_emsg"></span></div>
			<form action="${storeDomain}/web/userCenterManage!login.action" method="post" id="divlogin"
			onsubmit="">
				<input type="hidden" name="loginstyle" value="0"/>
				<input type="hidden" name="jumpurl" id="h_jumpurl" value=""/>
				<ul>
					<li>
						<span class="wd1"></span>
						<span class="wd_input" id="msg"></span>
					</li>
					<li>
						<span class="wd1"><font class="redfont">*</font>Email/Nickname：</span>
						<span class="wd_input"><input name="uname" id="h_uname" class="input02" type="text" value="${uname}"/></span>
					</li>
					<li class="tishiyu"></li>
					<li><span class="wd1"><font class="redfont">*</font>Password：</span><span class="wd_input"><input name="pwd" id="h_pwd" class="input02" type="password" /></span></li>
					<li class="tishiyu"></li>
					<li><span class="wd1">&nbsp;</span><a href="${storeDomain}/web/userCenterManage!toFindPassword.action"  class="ljh">Forget password?</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:toReg('${storeDomain}');"  class="ljh">Register</a></li>
					<li><span class="wd2"><input type="submit" value="Login" class="user_center_bt02" /></li>
		  		</ul>
		  	</form>
		</div>
	</div>
	<!-- end login-->
</div>
<!--End top-->
<!-- 隐藏微信二维码 -->
<div id="c_wx_panel" style="position: fixed;z-index: 10000000001;display:none;">
	<div style="background-clip: padding-box;background-color: #FFFFFF;border: 1px solid rgba(0, 0, 0, 0.3);border-radius: 6px 6px 6px 6px;box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
		left: 50%;margin: -200px 0 0 -200px;overflow: hidden;position: fixed;top: 50%;width: 360px;height: 360px;">
		<div style="border-bottom: 1px solid #EEEEEE;padding: 9px 15px;">
		<a href="javascript:void(0);"  style="text-decoration: none;margin-top: 2px;color: #000000;float: right;font-size: 20px;
		font-weight: bold;cursor: pointer;line-height: 20px;opacity: 0.2;text-shadow: 0 1px 0 #FFFFFF;">×</a>
		<h3 style="line-height: 30px;margin: 0;font-weight: normal;">Follow us on Wechat</h3></div>
		<div style="text-align: center;height: 251px;">
		<img style="margin-top: 25px;" src="http://res.mtvpad.com/en/res/images/attention_03.jpg"/>
		</div>
	</div>
</div>
</gv:esi>