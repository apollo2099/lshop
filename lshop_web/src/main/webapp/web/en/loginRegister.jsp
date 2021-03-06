<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad Mall</title>
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/en/res/js/myjs.js"></script>
		<script type="text/javascript" src="${resDomain}/en/res/js/page/register.js"></script>
		<script type="text/javascript" src="${resDomain}/en/res/js/page/noLoginInfo.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
        

        <!--登录-->
		<div class="login_gb_warp">       
        	<div class="login_gb_warp_c">
            <!--第三方登錄-->            
          	<div class="third_login"> 
            	<ul>
                	<li class="title">Another way to login</li>
                	<li class="fb"><span class="title">log in</span><a href="/web/threeauth!facebook.action"> with Facebook</a></li>
                    <li class="qq"><span class="title">log in</span> <a href="/web/threeauth!qq.action">with QQ</a></li>
                    <%--
                    <li class="wx"><span class="title">log in</span> with WeChat</li>
                     --%>
                </ul>
            </div> 
            <!--第三方登錄END-->
            
            	<div class="login_gb_warp_c_yq commargin1">
        			<div class="title">
					<ul>
						<li id="index1"><a href="javascript:MainItem(1);">User Login</a></li>
						<li class="choose"  id="index2"><a href="javascript:MainItem(2);">User Register</a></li>
		  			</ul>
		        </div>	
		            <!-- 注册 -->
		            <form action="#" id="regedit" method="post" onsubmit="">
		            <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>					
				    <div class="tjcontent" id="f_Pic2">
						<div class="register_gb">
                        	<ul> 
                        	    <li class="tips"><font class="redfont" id="msgg"></font></li>                    	
                            	<li><input name="lvAccount.email" id="email" value="${lvUser.email}" type="text" dvalue="Please enter account!"   onblur="checkEmail();" class="login_input01"/></li>
                                <li id="remind_email" class="tips" style="display:none">Please enter account</li>
                                
                                <li><input name="lvAccount.pwd" id="pwd" onblur="checkPassword();" type="password"  placeholder="Password"  class="login_input02"/></li>
                                <li id="remind_pwd" class="tips" style="display:none">6-16characters,you can use a combination of letters,numbers or symbols</li>
                                
                                <li><input name="truePwd" onblur="checkTruePassword();" id="truePwd"  type="password" placeholder="Confirm password"   class="login_input02" /></li>
                                <li id="remind_truePwd" class="tips" style="display:none">6-16characters,you can use a combination of letters,numbers or symbols</li>
                                
                                <li> <input name="code" id="vcode" onblur="checkVcode();" type="text"  dvalue="Captcha"  class="login_input03" /> 
                                <img src="/web/imager.jsp" width="47" height="21" id="rcId" onclick="javascript:this.src=this.src+'?'+new Date()" /> Unclear?<a href="javascript:void(0);" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()"> Refresh</a> </li>
                                 <li id="remind_vcode" class="tips" style="display:none">Please enter captcha !</li>
                                 
                                <li class="btn"><input name="" type="submit" class="login_gb_btn01" id="registerbut" value="Register"/></li>
                               <li class="tips"> Already registered with TVpad? <a href="${storeDomain}/web/userCenterManage!toLoginRegister.action?jumpFlag=1&jumpurl=${param.jumpurl}">Log in here</a></li>
                            </ul>
                        </div>
                    </div>
                    </form>

				    <!-- 登录 -->
				    <form action="#" id="login" method="post" onsubmit="">
				    <input type="hidden" name="loginstyle" value="1"/>
                    <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>		
					<div class="tjcontent" id="f_Pic1" style="display:none;">
						<div class="register_gb">
                        	<ul>
                        	    <li class="tips"><font class="redfont" id="msg1"></font></li>                  	
                            	<li><input name="uname" id="loginname"  value="${uname}" type="text" dvalue="Please enter account!" onblur="checkLoginEmail();"  class="login_input01"/></li>
                                <li id="remind_loginname" class="tips" style="display:none">Please enter account!</li>
                                <li><input name="pwd" id="loginpwd" type="password" onblur="checkLoginPassword();" placeholder="Password" class="login_input02" /><a href="${storeDomain}/web/userCenterManage!toFindPassword.action">  Forget password?</a></li>
                                <li id="remind_loginpwd" class="tips" style="display:none">6-16characters,you can use a combination of letters,numbers or symbols</li>
                                <li> <input name="code" id="logincode" onblur="checkLoginVcode();" type="text"  dvalue="Captcha"  class="login_input03" /> 
                                <img src="/web/imager.jsp" width="47" height="21" id="rcloginId" onclick="javascript:this.src=this.src+'?'+new Date()" /> Unclear?<a href="javascript:void(0);" onclick="javascript:document.getElementById('rcloginId').src=document.getElementById('rcloginId').src+'?'+new Date()"> Refresh</a> </li>
                                <li id="remind_logincode" class="tips" style="display:none">Please enter captcha Please enter captcha </li>
                                <li class="btn"><input name="" type="submit" class="login_gb_btn01" value="Login"/></li>
                                <li class="tips">Not registered with TVpad? <a href="${storeDomain}/web/userCenterManage!toLoginRegister.action?jumpFlag=2&jumpurl=${param.jumpurl}">Register now</a></li>
                            </ul>
                        </div>
					</div>		
					</form>
			  </div>									
				
             </div>			
            </div>
        </div>
        <!--登录END-->
 <input type="hidden" value="${jumpFlag }" id="jumpFlag"/>
 
 
 
 <!-- 注册就送活动-->
<div class="longin_sales" id="c_regist_tip">

</div>
<!--注册就送活动END-->


<!-- footer -->
<div class="bottom">
  <div class="content">Copyright &copy; 2007-2015, CREATE NEW TECHNOLOGY(HK) LIMITED. All Rights Reserved.</div>
</div>
	<script type="text/javascript">
		$.post('/web/activity!getRegistActivity.action', function(data) {
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

		$(function() {
			var jumpFlag = $("#jumpFlag").val();
			if (jumpFlag == null) {
				MainItem(1);
			} else {
				MainItem(jumpFlag);
			}

		});
	</script>
</body>
</html>