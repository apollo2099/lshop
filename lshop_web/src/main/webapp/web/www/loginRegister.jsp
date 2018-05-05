<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TVpad商城</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/page/register.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/page/noLoginInfo.js"></script>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp" %>
<!--登录注册合并-->

		<div class="login_gb_warp">       
        	<div class="login_gb_warp_c">
            <!--第三方登錄-->            
          	<div class="third_login"> 
            	<ul>
                	<li class="title">使用合作網站登錄</li>
                	<li class="fb"><a href="/web/threeauth!facebook.action">用 Fcaebook帳號登錄</a></li>
                    <li class="qq"><a href="/web/threeauth!qq.action">用 QQ帳號登錄</a></li>
                    <%--
                    <li class="wx"><a href="#">用 微信帳號登錄</a></li>
                     --%>
                </ul>
            </div> 
            <!--第三方登錄END-->
            
                
            <div class="login_gb_warp_c_yq commargin1">
        			<div class="title">
					<ul>
						<li id="index1"><a href="javascript:MainItem(1);">用戶登錄</a></li>
						<li class="choose"  id="index2"><a href="javascript:MainItem(2);">用戶註冊</a></li>
		  			</ul>
		            </div>
		        <!-- 注册tab -->		
		        <form action="#" id="regedit" method="post" onsubmit="">
		        <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>		
				<div class="tjcontent" id="f_Pic2">
						<div class="register_gb">
                        	<ul>
                        	    <li class="tips"><font class="redfont" id="msgg"></font></li>                        	
                            	<li><input name="lvAccount.email" id="email" value="${lvUser.email}" dvalue="請輸入您的Email地址!" onblur="checkEmail();" type="text"  class="login_input01"/></li>
                                <li id="remind_email" class="tips" style="display:none">該地址將成為登錄賬戶及系統郵件接收地址，請確認可以使用！</li>
                                <li><input name="lvAccount.pwd" id="pwd" onblur="checkPassword();" type="password"  placeholder="請輸入密碼" class="login_input02" /></li>
                                <li id="remind_pwd" class="tips" style="display:none">6-16位字符，可使用字母、數字或符號的組合</li>
                                <li><input name="truePwd" onblur="checkTruePassword();" id="truePwd"  type="password" placeholder="請輸入密碼" class="login_input02"/></li>
                                <li id="remind_truePwd" class="tips" style="display:none">6-16位字符，可使用字母、數字或符號的組合</li>
                                <li> <input name="code" id="vcode" onblur="checkVcode();" type="text"  dvalue="驗證碼" class="login_input03" /> 
                                <img src="/web/imager.jsp" width="47" height="21" id="rcId" onclick="javascript:this.src=this.src+'?'+new Date()"/> 看不清楚？<a href="javascript:void(0);" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">換一張</a> </li>
                                <li  style="display:none" id="remind_vcode" class="error_tips"></li>
                                <li class="btn"><input name="" type="submit" class="login_gb_btn01" id="registerbut" value="註 冊"/></li>
                                <li class="tips">已有帳號，<a href="${storeDomain}/web/userCenterManage!toLoginRegister.action?jumpFlag=1&jumpurl=${param.jumpurl}">立即登錄</a></li>
                               
                            </ul>
                        </div>
                </div>
                </form>
                
				<!-- 登录tab -->
				<form action="#" id="login" method="post" onsubmit="">		
				<input type="hidden" name="loginstyle" value="1"/>
                <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
				<div class="tjcontent" id="f_Pic1" style="display:none;">
						<div class="register_gb">
                        	<ul>
                        	    <li class="tips"><font class="redfont" id="msg1"></font></li>                      	
                            	<li><input name="uname" id="loginname"  value="${uname}" type="text" dvalue="Email或者暱稱" onblur="checkLoginEmail();" class="login_input01"/></li>
                                <li id="remind_loginname" class="tips" style="display:none">請在英文狀態下輸入您的Email或暱稱！</li>
                                <li><input name="pwd" id="loginpwd" type="password" onblur="checkLoginPassword();" placeholder="請輸入密碼 " class="login_input02"/><a href="${storeDomain}/web/userCenterManage!toFindPassword.action"> 忘記密碼?</a></li>
                                <li id="remind_loginpwd" class="tips" style="display:none">6-16位字符，可使用字母、數字或符號的組合</li>
                                <li> <input name="code" id="logincode" onblur="checkLoginVcode();" type="text"  dvalue="驗證碼" class="login_input03" /> 
                                <img src="/web/imager.jsp" width="47" height="21" id="rcloginId" onclick="javascript:this.src=this.src+'?'+new Date()"/> 看不清楚？<a href="javascript:void(0);" onclick="javascript:document.getElementById('rcloginId').src=document.getElementById('rcloginId').src+'?'+new Date()">換一張</a> </li>
                                <li id="remind_logincode" class="tips" style="display:none">請輸入驗證碼</li>
                                <li class="btn"><input name="" type="submit" class="login_gb_btn01" value="登 錄"/></li>
                                <li class="tips">還未加入？ <a href="${storeDomain}/web/userCenterManage!toLoginRegister.action?jumpFlag=2&jumpurl=${param.jumpurl}">立即註冊</a></li>
                            </ul>
                        </div>
				</div>				
				</form>
				
				</div>									
            </div>
        </div>
<!--登录注册合并END-->

<!-- 注册就送活动-->
<div class="longin_sales" id="c_regist_tip">

</div>
<!--注册就送活动END-->

 <input type="hidden" value="${jumpFlag }" id="jumpFlag"/>
<!-- footer -->
<!-- 第三方统计代码 -->
<div class="bottom">
  <div class="content"> Copyright(C) 2007-2015 TVpad國際科技有限公司（HUA YANG INTERNATIONAL TECHNOLOGY LIMITED）.All Rights Reserved</div>
</div>

<script type="text/javascript">
$.post('/web/activity!getRegistActivity.action', function(data){
	$('#c_regist_tip').append(data);
});


function MainItem(suf_Id){
	 for(var i=0;i<2;i++){
		var num=i+1;
		var tempMenuId = "index"+num;
		var tempMainId ="f_Pic"+num;
		if(num==suf_Id){
			$("#"+tempMenuId).addClass("choose");
			$("#"+tempMainId).show();
		}else{
			$("#"+tempMenuId).removeClass("choose");
			$("#"+tempMainId).hide();
		}
		
		
	 }
	 
	 if(suf_Id==2){
		$('#c_regist_tip').show();
	 }else{
		$('#c_regist_tip').hide();
	 }
}


$(function(){
	var jumpFlag=$("#jumpFlag").val();
	if(jumpFlag==null){
		MainItem(1);
	}else{
		MainItem(jumpFlag);
	}
	
});

</script>
</body>
</html>
