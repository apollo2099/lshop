<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<gv:esi uri="/web/www/common/shopcart_header.jsp">
<!--top -->
<div id="sc_top">
 	<div id="sc_head">
  		<div id="header">
			<div id="shoucang">
		    <a href="javascript:addBookmark('TVpad官網','http://www.mtvpad.com/')" class="wjx_h" title="收藏">收藏</a>
		    <a href="http://weibo.com/tvpad" target="_blank" class="xinlang_h" title="微博"></a>
		    <a id="c_top_weixin_btn" href="javascript:void(0);" class="weixi_h"  title="微信"></a>
		    <a href="https://www.facebook.com/mytvpad" target="_blank"  class="f_h" title="facebook"></a>
		    <div class="clear"></div>
   			</div>
    		<div id="help">
				<ul>
	          		<li class="yh" style="display:none;" id="nameId">歡迎，<font id="nicknameId"></font></li>
	            	<li class="dl" id="loginId"><a href="javascript:onshow('tx_b','loginDiv');">登錄</a></li>
					<li class="dl" id="registerId"><a href="javascript:toRe('${storeDomain }');">註冊</a></li>
					<li class="tc" style="display:none;" id="logoutId"><a href="${storeDomain}/web/userCenterManage!logout.action" >退出</a></li><!--登录之后状态-->
	            	<li class="menu2" id="c_uc_menu" style="display:none;"><a href="${storeDomain}/web/userCenterManage!getAccount.action">個人中心 </a>
		        	   	<ul id="bb" class="list" style="display:none;">
						  	<li class="cc"><a href="${storeDomain}/web/userOrder!getOrderlist.action">我的訂單</a></li>
		          		  	<li class="cc"><a href="${storeDomain}/web/userCenterManage!getAccount.action">我的帳戶</a></li>
		          		  	<li class="cc"><a href="${storeDomain}/web/userCenterManage!getInfoDetail.action">我的資料</a></li>
		          		  	<li class="cc"><a href="${storeDomain}/web/userCenterManage!toUpdatePassword.action">密碼管理</a></li>
						  	<li class="cc"><a href="${storeDomain}/web/userCenterManage!getAddressList.action">收貨地址</a></li>
						  	<li class="cc"><a href="${storeDomain}/web/userCenterManage!getCommentList.action">我的評論</a></li>
		        	   	</ul>
	      			</li>
			    	<li class="gouwu"><a href="#" onclick="toCar('${storeDomain}');">
			    	<span  id="shopCartNum" class="number"></span></a></li>	
					<li class="dd"><a href="http://tg.mtvpad.com/" target="_blank">我要推廣TVpad</a></li>
					<li class="dd"><a href="http://www.mtvpad.com/application.html" target="_blank">應用入駐 </a> </li>
			    	<li class="qiehuan"><a href="http://en.mtvpad.com/index.html">[English]</a></li>	
         		</ul>
			</div>
		</div>
  	</div>
  
	<div class="sc_logo">
    	<div class="sc_log">
	  		<div class="logo"><a href="${storeDomain}/index.html"><img src="<cus:store param='storeLogo'/>" /></a></div>
		</div>
  	</div>
	

	<!--login -->
	<div id="loginDiv"   style="display:none;background-color:#000000;width:800px;height:600px;position:absolute;top:0px;left:0px;text-align:center;z-index:9000;filter:alpha(opacity=10);opacity:0.3"></div>
	<div id="tx_b" style="top:124px;position: absolute;display: none;z-index:9999;">
		<div class="pop_up_box" style="background:white;">
			<span class="box_l">用戶登錄<span class="box_r"><a href="javascript:onhide('tx_b','loginDiv');"><img src="${resDomain}/www/res/images/close.gif" border="0" /></a></span></span>
			<div style="color: red; margin: 10px 0 0 90px;"><span id="h_emsg"></span></div>
			<form action="${storeDomain}/web/userCenterManage!login.action" id="divlogin"
			method="post" onsubmit="">
				<input type="hidden" name="loginstyle" value="0"/>
				<input type="hidden" name="jumpurl" id="h_jumpurl" value=""/>
				<ul>
					<li id="erro_msg" style="display: none;height: 20px"><span id="msg" class="redfont" style="margin-left: 90px;"></span></li>
					<li><span class="wd1"><font class="redfont">*</font>Email或昵稱：</span><input name="uname" id="h_uname" class="input02" type="text"  value="${uname}"/></li>
					<li><span class="wd1"><font class="redfont">*</font>密 碼：</span><input name="pwd"  id="h_pwd" class="input02"  type="password" /></li>
					<li><span class="wd1">&nbsp;</span><a href="${storeDomain}/web/userCenterManage!toFindPassword.action">忘記密碼？</a>&nbsp;&nbsp;<a href="javascript:toReg('${storeDomain}');">註冊</a></li>
					<li><span class="wd2"><input type="submit" value="登錄" class="user_center_bt02" /></span></li>
		  		</ul>
	  		</form>
	  	 <div class="he_dl">
         <h3>合作网站账号登陆</h3>
         <a href="/web/threeauth!qq.action"><img src="${resDomain}/www/res/images/QQ.png" alt="QQ登陆"/></a>
         <a href="/web/threeauth!facebook.action"><img src="${resDomain}/www/res/images/login_facebook.jpg" alt="Facebook登陆"/></a>
          </div>
		</div>
	</div>
	
</div>
<!-- 隐藏微信二维码 -->
<div id="c_wx_panel" style="position: fixed;z-index: 10000000001;display:none;">
	<div style="background-clip: padding-box;background-color: #FFFFFF;border: 1px solid rgba(0, 0, 0, 0.3);border-radius: 6px 6px 6px 6px;box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
		left: 50%;margin: -200px 0 0 -200px;overflow: hidden;position: fixed;top: 50%;width: 360px;height: 360px;">
		<div style="border-bottom: 1px solid #EEEEEE;padding: 9px 15px;">
		<a href="javascript:void(0);"  style="text-decoration: none;margin-top: 2px;color: #000000;float: right;font-size: 20px;
		font-weight: bold;cursor: pointer;line-height: 20px;opacity: 0.2;text-shadow: 0 1px 0 #FFFFFF;">×</a>
		<h3 style="line-height: 30px;margin: 0;font-weight: normal;">TVpad官方微信</h3></div>
		<div style="text-align: center;height: 251px;">
		<img style="margin-top: 25px;" src="http://res.mtvpad.com/www/res/images/attention_03.jpg"/>
		</div>
	</div>
</div>
</gv:esi>