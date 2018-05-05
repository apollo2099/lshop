<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="help" uri="/WEB-INF/tld/gv-help.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="store" uri="/WEB-INF/tld/gv-store.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>

<div id="sc_top">
 	<div id="sc_head">
  		<div id="header">
   			<div id="shoucang">
		  		<p><a href="javascript:addBookmark('TVpad Mall','http://en.mtvpad.com/')" class="shoucang1">Add TVpad Mall to Favorites</a></p><p><a href="http://weibo.com/tvpad" target="_blank"><img src="${resDomain}/tvpaden/res/images/xinlang.jpg" border="0" /></a></p><p><a class="fav_facebook" rel="nofollow" href="https://www.facebook.com/mytvpad" target="_blank"><img src="${resDomain}/tvpaden/res/images/f_book.jpg" border="0" /></a></p>
   			</div>
    		<div id="help">
          		<ul>
		        	<li class="yh" style="display:none;" id="nameId"><font id="nicknameId"></font>,</li>
		        	<li class="line">Hello! Welcome to TVpad Mall!</li>
		            <li class="dl" id="loginId"><a href="javascript:onshow('tx_b','loginDiv');">Login</a></li>
					<li class="dl" id="registerId"><a href="javascript:toRe('${storeDomain }');">Register</a></li>
					<li class="tc" style="display:none;" id="logoutId"><a href="${storeDomain}/web/userCenterManage!logout.action" >Log out</a></li><!--登录之后状态-->
		            <li class="dd">| <a href="${storeDomain}/web/userOrder!getOrderlist.action" >My Order</a> |</li>
		            <li class="menu2" onmouseover="this.className='menu1'" onmouseout="this.className='menu2'"><a href="${storeDomain}/web/userCenterManage!getAccount.action"> User Center</a>
		        	   <ul class="list">
		          		  <li><a href="${storeDomain}/web/userCenterManage!getAccount.action">My Account</a></li>
		          		  <li><a href="${storeDomain}/web/userCenterManage!getInfoDetail.action">My Profile</a></li>
		          		  <li><a href="${storeDomain}/web/userCenterManage!toUpdatePassword.action">Password</a></li>
						  <li><a href="${storeDomain}/web/userCenterManage!getAddressList.action">Billing Addr.</a></li>
						  <li><a href="${storeDomain}/web/mall!getShopCartList.action">My Cart</a></li>
						  <li><a href="${storeDomain}/web/userOrder!getOrderlist.action">My Order</a></li>
						  <li><a href="${storeDomain}/web/userCenterManage!getCommentList.action">My Reviews</a></li>
		        	   </ul>
		      		</li>
					<li class="dl">| <a href="${storeDomain}/web/helpCenter!getHelps.action">Help</a> |</li>
				    <li class="gouwu"><p><a href="#" onclick="toCar('${storeDomain}');">My Cart</a></p>
				    <p class="number">(<span id="shopCartNum"></span>)</p></li>	
         		</ul>
       		</div>
		</div>
  	</div>
  
	<div class="sc_logo">
    	<div class="sc_log">
	  		<p class="logo"><a href="${storeDomain}/index.html"><img src="<cus:store param='storeLogo'/>" /></a></p>
	  		<div class="search">
	  			<form action="" method="post" class="sousuo_bg" id="searchForm">
	  				<input name="str" type="text" maxlength="50" class="sc_wb" id="searchContent" value="${searchContent }" onfocus="if(this.value=='Please enter your search here…')this.value=''" onblur="if(this.value=='')this.value='Please enter your search here…'"/><input name="" type="image" onclick="subSearch('product','${storeDomain}');" src="${resDomain}/tvpaden/res/images/soushangpin.gif"/><%--<input name="" type="image" onclick="subSearch('store')" src="${resDomain}/tvpaden/res/images/soudianpu.gif"/>  --%>
	  			</form>
	  			<p class="reci">Hot：<span><st:tagStyle></st:tagStyle></span></p>
	  		</div>
		</div>
  	</div>
	
  	<div class="sc_menu">
    	<div class="sc_menu_A">
    		<div class="sc_menu_A_l">
	 			<cus:navigation storeKey="en" style="1"></cus:navigation>
	 		</div>
	 		<%--<div class="sc_menu_A_r" >
	    		<h1 class="xuanze" onmouseover="showContinent();" id="selectStore">Find a TVpad Retail Store</h1>
	    		<div class="dp_menu" id="menu_country" style="display:none;"></div>
	    		<div class="dp_menu2" id="menu_store" style="display:none;position: absolute;"></div>
	    	</div>  --%>
		</div>
  	</div>
	<!--End top -->

	<!-- begin login -->
	<div id="loginDiv"   style="display:none;background-color:#000000;width:800px;height:600px;position:absolute;top:0px;left:0px;text-align:center;z-index:9000;filter:alpha(opacity=10);opacity:0.3"></div>
	<div id="tx_b" style="top:124px;position: absolute;display: none;z-index:9999;">
		<div class="pop_up_box" style="background:white;">
			<span class="box_l">User Login<span class="box_r"><a href="javascript:onhide('tx_b','loginDiv');"><img src="${resDomain}/tvpaden/res/images/close.gif" border="0" /></a></span></span>
			<div style="color: red; margin: 10px 0 0 90px;"><span id="h_emsg"></span></div>
			<form action="${storeDomain}/web/userCenterManage!login.action" id="divlogin"
			method="post" onsubmit="">
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

<!-- 店铺顶部广告位 -->	  
<ad:ad adkey="AD_lOCATION_TOP"></ad:ad>

<!-- 店铺导航 -->	  
<%--<div class="logo">
	<a href="/index.html"><img src="<cus:store param='storeLogo' shopFlag="tvpaden"/>" width="90px" height="48px"/></a>
    <div class="menu_A"><cus:navigation></cus:navigation></div>
</div>

<div class="banner_line"></div>  --%>
<!--End banner-->
	
