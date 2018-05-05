<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="help" uri="/WEB-INF/tld/gv-help.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="store" uri="/WEB-INF/tld/gv-store.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>

<!--top -->
	<div class="top" id="sc_top">
		<div class="top_line"></div>
		<div class="top_submenu">
			<div class="top_submenu1">
				<p class="collection">
					<img src="${resDomain}/bsen/res/images/collect.gif" /> 
					<a href="javascript:addBookmark('banana Mall','http://en.bananatv.com/')">Favorites</a>
				</p>
				<p>
					<span style="display:none;" id="nameId"><font id="nicknameId"></font>，</span>Hi, Welcome to banana Store.
				</p>
				<p class="submenu">
					<span id="loginId"><a href="javascript:onshow('tx_b','loginDiv');">Login</a> |</span>
					<span id="registerId"><a href="javascript:toRe('${storeDomain }');">Register</a> |</span>
					<span id="logoutId" style="display:none;"><a href="${storeDomain}/web/userCenterManage!logout.action" >Log out</a> |</span>
					<a href="${storeDomain}/web/userOrder!getOrderlist.action">My order</a> |
					<a href="${storeDomain}/web/helpCenter!getHelps.action">Help</a> |
					<img src="${resDomain}/bsen/res/images/buy.gif" /> <a href="javascript:toCar('${storeDomain}');">My cart</a><font class="white">（<span id="shopCartNum"></span>）</font>
					|
					<a href="http://www.bananatv.com/index.html" class="white">[Chinese]</a>
				</p>
			</div>
		</div>
	</div>
	<!--end top-->

	<div class="menu">
		<div class="menu_a">
			<p>
				<a href="${storeDomain}/index.html"><img src="<cus:store param='storeLogo'/>" /></a>
			</p>
			<ul>
				<cus:navigation storeKey="bmen" style="3"></cus:navigation>
			</ul>
		</div>
	</div>
	<!--end menu-->

	<!--login -->
	<div id="loginDiv"   style="display:none;background-color:#000000;width:800px;height:600px;position:absolute;top:0px;left:0px;text-align:center;z-index:9000;filter:alpha(opacity=10);opacity:0.3"></div>
	<div id="tx_b" style="top:124px;position: absolute;display: none;z-index:9999;">
		<div class="pop_up_box" style="background:white;">
			<span class="box_l">User Login<span class="box_r"><a href="javascript:onhide('tx_b','loginDiv');"><img src="${resDomain}/bsen/res/images/close.gif" border="0" /></a></span></span>
			<form id="divlogin"  action="${storeDomain}/web/userCenterManage!login.action" method="post" onsubmit="">
				<input type="hidden" name="loginstyle" value="0"/>
				<input type="hidden" name="jumpurl" id="jumpurl" value=""/>
				<ul>
					<li id="erro_msg" style="display: none;"><span id="msg" class="redfont" style="margin-left: 130px;"></span></li>
					<li><span class="wd1">Email/NickName:</span><span class="wd_input"><input name="uname" id="uname" class="input02" type="text"  value="${uname}"  maxlength="60"/></span></li>
					<li><span class="wd1">Password:</span><span class="wd_input"><input name="pwd"  id="pwd" class="input02"  type="password" /></span></li>
					<li class="btn"><font class="redfont"><a href="${storeDomain}/web/userCenterManage!toFindPassword.action">Forget password?</a></font> <font class="redfont"><a href="javascript:toReg('${storeDomain}');">Register</a></font></li>
					<li class="btn"><input type="submit" value="Login" class="user_center_bt02"/></li>
		  		</ul>
	  		</form>
		</div>
	</div>

<!-- 店铺顶部广告位 -->	  
<ad:ad adkey="AD_lOCATION_TOP"></ad:ad>
