<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>

<!--top -->
	<div class="top" id="sc_top">
		<div class="top_line"></div>
		<div class="top_submenu">
			<div class="top_submenu1">
				<p class="collection">
					<img src="${resDomain}/bmcn/res/images/collect.gif" /> 
					<a href="javascript:addBookmark('banana商城','http://www.bananatv.com/')">收藏banana商城</a>
				</p>
				<p  class="p1">
					<span style="display:none;" id="nameId"><font id="nicknameId"></font>，</span>您好！欢迎光临banana商城
				</p>
				<p class="submenu">
					<span id="loginId"><a href="javascript:onshow('tx_b','loginDiv');">登录</a> |</span>
					<span id="registerId"><a href="javascript:toRe('${storeDomain }');">注册</a> |</span>
					<span id="logoutId" style="display:none;"><a href="javascript:userlogout();" >退出</a> |</span>
					<a href="${storeDomain}/web/userOrder!getOrderlist.action">我的订单</a> |
					<%--<a href="${storeDomain}/web/recharge!list.action">V币充值</a> |--%>
					<a href="${storeDomain}/web/helpCenter!getHelps.action">帮助中心</a> |
					<a href="http://tg.bananatv.com/" target="_blank">推广联盟</a> |
					<img src="${resDomain}/bmcn/res/images/buy.gif" /> <a href="javascript:toCar('${storeDomain}');">购物车</a><font class="white">（<span id="shopCartNum"></span>）</font>
					|
					<a href="http://en.bananatv.com/index.html" class="white">[English]</a>
				</p>
			</div>
		</div>
	</div>
	<!--end top-->

	<div class="menu">
		<div class="menu_a">
			<p>
				<a href="/index.html"><img src="<cus:store param='storeLogo'/>" /></a>
			</p>
			<ul>
				<cus:navigation style="3"></cus:navigation>
			</ul>
		</div>
	</div>
	<!--end menu-->

	<!--login -->
	<div id="loginDiv"   style="display:none;background-color:#000000;width:800px;height:600px;position:absolute;top:0px;left:0px;text-align:center;z-index:9000;filter:alpha(opacity=10);opacity:0.3"></div>
	<div id="tx_b" style="top:124px;position: absolute;display: none;z-index:9999;">
		<div class="pop_up_box" style="background:white;">
			<span class="box_l">用户登录<span class="box_r"><a href="javascript:onhide('tx_b','loginDiv');"><img src="${resDomain}/bmcn/res/images/close.gif" border="0" /></a></span></span>
			<form id="divlogin"  action="${storeDomain}/web/userCenterManage!login.action" method="post" onsubmit="">
				<input type="hidden" name="loginstyle" value="0"/>
				<input type="hidden" name="jumpurl" id="jumpurl" value=""/>
				<ul>
					<li id="erro_msg" style="display: none;"><span id="msg" class="redfont" style="margin-left: 90px;"></span></li>
					<li><span class="wd1">邮箱或昵称：</span><span class="wd_input"><input name="uname" id="uname" class="input02" type="text"  value="${uname}" maxlength="60"/></span></li>
					<li><span class="wd1">密 码：</span><span class="wd_input"><input name="pwd"  id="pwd" class="input02"  type="password" /></span></li>
					<li class="btn"><font class="redfont"><a href="${storeDomain}/web/userCenterManage!toFindPassword.action">忘记密码？</a></font> <font class="redfont"><a href="javascript:toReg('${storeDomain}');">注册</a></font></li>
					<li class="btn"><input type="submit" value="登录" class="user_center_bt02"/></li>
		  		</ul>
	  		</form>
	  			<div class="he_dl">
         <h3>合作网站账号登陆</h3>
        <a href="/web/threeauth!qq.action"><img src="${resDomain}/bmcn/res/images/QQ.png" alt="QQ登陆"/>QQ</a>
        <a href="/web/threeauth!weibo.action"><img src="${resDomain}/bmcn/res/images/xl.png" alt="微博登陆"/>微博</a>
 		<!-- 
         <a href="#"><img src="${resDomain}/bmcn/res/images/wx.png" alt="微信登陆"/></a>
         <a href="#"><img src="${resDomain}/bmcn/res/images/zfb.png" alt="支付宝"/></a>
         -->
      </div>  
		</div>
	</div>


