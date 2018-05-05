<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad海外高清中文直播電視—TVpad官方商城</title>
<%@include file="/web/www/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/www/res/js/threeauthorize.js" ></script>
<script type="text/javascript" src="${resDomain}/www/res/js/regandlog.js" ></script>

<%
String uname="";
Object ouname = request.getAttribute("uname");
if(null == ouname){
	uname = "請輸入Email地址或者暱稱";
}
%>	
</head>

<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp" %>
<!--End top-->
 <input id="thnick" value="${thnick}" type="hidden"/>
<!--绑定成功-->
<div class="longin_bd_ok">
	<p>
    	<img src="${resDomain}/bmcn/res/images/suc.gif" width="50" height="50" /> <br />
         <font id="thnickstr"></font>,您已經通過<c:if test="${lvUserTh.thType==1}">QQ</c:if><c:if test="${lvUserTh.thType==2}">微博</c:if><c:if test="${lvUserTh.thType==3}">Facebook</c:if>授權成功!<br />
                      請綁定TVpad帳號，以後登錄TVpad商城會更方便！
   </p>
</div>
<!--绑定成功END-->

<!--登录-->
 
		<div class="login_gb_warp">       
        	<div class="login_gb_warp_c">
            <!--第三方登錄-->            
          	<div class="third_login"> 
            	<ul>
                	<li class="title">使用合作網站登錄</li>
                    <li class="fb"><a href="/web/threeauth!facebook.action">用 Fcaebook帳號登錄</a></li>
                    <li class="qq"><a href="/web/threeauth!qq.action">用 QQ帳號登錄</a></li>
                    <%--
                    <li class="wx">用 微信帳號登錄</li>
                     --%>
                </ul>
            </div> 
            <!--第三方登錄END-->
            
            	<div class="login_gb_warp_c_yq commargin1">
        			<div class="title">
					<ul>
						<li class="choose"  id="index1"><a href="javascript:MainItem(1);">已有帳號登錄綁定</a></li>
						<li id="index2"><a href="javascript:MainItem(2);">新註冊用戶登錄</a></li>
		  			</ul>
		        </div>		
		        <!-- 注册模块 -->
		  		<form action="#" id="regeditForm" method="post" onsubmit="">
		  		<input type="hidden" name="lvUserTh.thType" value="${lvUserTh.thType}"/>
				<input type="hidden" name="token" value="${token}"/>
				<input type="hidden" name="jumpurl" value="${storeDomain}/web/threeauth!success.action?lvUserTh.thType=${lvUserTh.thType}"/>
				<div class="tjcontent" id="f_Pic2">
						<div class="register_gb">
                        	<ul>   
                                <li class="tips"><font class="redfont" id="msgg"></font></li>               	
                            	<li>
                            	<input type="text" class="login_input01" id="email" onblur="validateEmail();" onfocus="textFocus('email');"
		    name="lvAccount.email" maxlength="32"  dvalue="請輸入您的Email地址"/>
                            	
                            	</li>
                            	<li class="tips" style="display:none;" id="email_tip1">請輸入Email地址，該地址將成為登錄帳戶以及系統郵件接收地址，請確認可以使用！</li>
                                <li class="error_tips" style="display:none;" id="email_tip2"></li>
                                <li>
                                 <input name="lvAccount.pwd" id="rpwd"  type="password" class="login_input02" maxlength="16" placeholder="請輸入密碼"
		    onfocus="textFocus('pwd')" onblur="validatePwd();" value=""/>
                                </li>
                                <li class="tips" style="display:none;" id="pwd_tip1">6-16位字符，可使用字母、數字或者符號的組合，不建議使用純字母、純數字、純符號！</li>
                                <li class="error_tips" style="display:none;" id="pwd_tip2"></li>
                                <li>
                                 <input name="truePwd" id="truePwd"  type="password" class="login_input02" maxlength="16" placeholder="請再次輸入密碼"
		    onfocus="textFocus('tpwd')" onblur="validateTruePwd();" value=""/>
                                </li>
                                <li class="tips" style="display:none;" id="tpwd_tip1">請再次輸入密碼！</li>
                                <li class="error_tips" style="display:none;" id="tpwd_tip2">兩次密碼輸入不一致！</li>
                                <li> 
                                <input name="code" id="rcode2"  type="text" class="login_input03" maxlength="4" onfocus="textFocus('code')" dvalue="驗證碼"
		  style="width: 60px" onblur="validateCode();"/> 
                                <img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;"
		  onclick="javascript:this.src=this.src+'?'+new Date()"/> 看不清楚？<a href="javascript:void(0);" 
		  onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">換一張</a> </li>
                                <li class="tips" style="display:none;" id="code_tip1">請輸入驗證碼！</li>
                                <li class="error_tips" style="display:none;" id="code_tip2"></li>
                                <li class="btn">
                                <input type="submit" value="註冊" class="login_gb_btn01" id="registerbut"/>
                                </li>
                                <%--
                                <li class="tips">還未加入？ <a href="register.html">立即註冊</a></li>
                                --%>
                            </ul>
                        </div>
                 </div>
                 </form>
                 
				<!-- 登录模块 -->
				<form id="loginForm"  action="#" method="post" onsubmit="">
				<input type="hidden" name="loginstyle" value="1"/>
				<input type="hidden" name="lvUserTh.thType" value="${lvUserTh.thType}"/>
				<input type="hidden" name="token" value="${token}"/>
				<input type="hidden" name="jumpurl" value="${storeDomain}/web/threeauth!success.action?lvUserTh.thType=${lvUserTh.thType}"/>	
				 <div class="tjcontent" id="f_Pic1" style="display:none;">
						<div class="register_gb">
                        	<ul>       
                        	    <li class="tips"><font class="redfont" id="msg1"></font></li>                	
                            	<li>
                            	 <input name="uname" id="name"  type="text" class="login_input01" maxlength="60"
							    onfocus="textFocus('name');" onblur="validateLogAccount();" value="<%=uname%>"  dvalue="<%=uname%>"/></p>
                            	</li>
                            	<li class="tips" style="display:none;" id="name_tip1">請在英文狀態下輸入您的Email或暱稱！</li>
                                <li class="error_tips" style="display:none;" id="name_tip2"></li>
                                <li>
                                <input name="pwd" id="login_pwd"  type="password" class="login_input02" maxlength="16" placeholder="請輸入密碼"
							    onfocus="textFocus('login_pwd');" onblur="validateLogPwd();"/>
                                <a href="${storeDomain}/web/userCenterManage!toFindPassword.action"> 忘記密碼?</a></li>
                                <li class="tips" style="display:none;" id="login_pwd_tip1">6-16位字符，可使用字母、數字或符號的組合</li>
                                <li class="error_tips" style="display:none;" id="login_pwd_tip2"></li>
                                <li> 
                                <input name="code" id="rcode" onfocus="textFocus('login_code');" onblur="validateLogCode();" dvalue="驗證碼"
							  type="text" class="login_input03" style="width: 60px" maxlength="4"/>
                                <img src="/web/imager.jsp" width="47" height="21" id="rcIdA" style="cursor:pointer;"
							  onclick="javascript:this.src=this.src+'?'+new Date()"/>看不清楚？<a href="javascript:void(0);" onclick="javascript:document.getElementById('rcIdA').src=document.getElementById('rcIdA').src+'?'+new Date()">換一張</a> </li>
                                 <li class="tips" style="display:none;" id="login_code_tip1">請輸入驗證碼</li>
                                 <li class="error_tips" style="display:none;" id="login_code_tip2"></li>
                                 <li class="btn"><input name="" type="submit" class="login_gb_btn01" value="登 錄"/></li>
                                 <%--
                                <li class="tips">已有帳號，<a href="login.html">立即登錄</a></li>
                                 --%>
                            </ul>
                        </div>
				</div>				
                </form>
		
           </div>
    </div>
    </div>
<!--登录END-->




<div class="bottom">
  <div class="content"> Copyright(C) 2007-2015 TVpad國際科技有限公司（HUA YANG INTERNATIONAL TECHNOLOGY LIMITED）.All Rights Reserved</div>
</div>
<!--End Bottom -->
</body>
</html>



<script type="text/javascript" >
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
<script type="text/javascript">
		$(function(){
			var nickname=$("#thnick").val();
			　nickname = decodeURI(decodeURI(nickname));
			if(nickname!=""){
				nickname="亲爱的"+nickname;
			}else{
				nickname="亲";
			}
			$("#thnickstr").html(nickname);
		});
		
</script>