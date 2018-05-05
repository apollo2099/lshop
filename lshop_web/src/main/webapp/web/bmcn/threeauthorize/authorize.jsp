<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>banana TV</title>
<%@include file="/web/bmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/bmcn/res/js/threeauthorize.js" ></script>
</head>
<%
String uname="";
Object ouname = request.getAttribute("uname");
if(null == ouname){
	uname = "请输入Email地址或者昵称";
}
%>	
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/bmcn/common/header.jsp" %>
<!--end menu-->

<div class="sc_main">
  <div class="sc_main_left">
    <div class="sc_product">
	  <h2 class="bt3">
	    <p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p><p><a href="index.html">首页</a> &gt; 授权绑定</span> </p>
	  </h2>
      <div class="message2">
      <input id="thnick" value="${thnick}" type="hidden"/>
            <ul>
					<li class="pp">
                    <img src="${resDomain}/bmcn/res/images/suc.gif" /><br />
                    <font id="thnickstr"></font>,您已经通过<c:if test="${lvUserTh.thType==1}">QQ</c:if><c:if test="${lvUserTh.thType==2}">微博</c:if>授权成功!<br />
                    请绑定banana账号，以后登陆banana商城会更方便！
                    </li>
				  
				</ul>	
			
		</div>
	  
      
        <div class="product_item product_item2">
            <ul>
              <li id="index1" class="choose"><a href="javascript:boxLoginChoose(1);">已有账号登录绑定</a></li>
              <li id="index2"><a href="javascript:boxLoginChoose(2);">新注册用户绑定</a></li>
              
            </ul>
          </div>
          <div class="box_kuan" id="box_login1">
           <div class="login">
			  			<form id="loginForm"  action="/web/threeauth!buildAccount.action" method="post" onsubmit="">
						  <input type="hidden" name="loginstyle" value="1"/>
						  <input type="hidden" name="lvUserTh.thType" value="${lvUserTh.thType}"/>
						  <input type="hidden" name="token" value="${token}"/>
						  <input type="hidden" name="jumpurl" value="${storeDomain}/web/threeauth!success.action?lvUserTh.thType=${lvUserTh.thType}"/>
						  <ul>
								<li><p style="margin-left: 100px;">
								<font class="redfont" id="msg1"></font>
							    </p>
								</li>
						  </ul>
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>账号：</p>
							  <p class="input">
							    <input name="uname" id="name"  type="text" class="input02" maxlength="60"
							    onfocus="textFocus('name');" onblur="validateLogAccount();" value="<%=uname%>"  dvalue="<%=uname%>"/></p>
							  <p><a href="${storeDomain}/web/userCenterManage!toRegister.action?jumpurl=${param.jumpurl }" class="ljl">注册</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="name_tip1">请输入您注册时所填写的Email地址或者昵称</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="name_tip2"></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>密码：</p>
							  <p class="input">
							    <input name="pwd" id="log_pwd"  type="password" class="input02" maxlength="16"
							    onfocus="textFocus('pwd');" onblur="validateLogPwd();"/></p>
							  <p><a href="${storeDomain}/web/userCenterManage!toFindPassword.action" class="ljh">忘记密码？</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="pwd_tip1">请输入您的密码</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="pwd_tip2" ></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						  
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>验证码：</p>
							  <p><input name="code" id="rcode" onfocus="textFocus('code');" onblur="validateLogCode();"
							  type="text" class="input22" style="width: 40px" maxlength="4"/></p>
							  <p><img src="/web/imager.jsp" width="47" height="21" id="rcIdA" style="cursor:pointer;"
							  onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
							  <p><font class="kbqc">看不清楚？</font>
							  <a href="javascript:void(0);" class="ljh" 
							  onclick="javascript:document.getElementById('rcIdA').src=document.getElementById('rcIdA').src+'?'+new Date()">换一张</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="code_tip1">请输入验证码！</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="code_tip2"></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						 
						  <ul class="btn">
							<li><input type="submit" value="登录" class="user_center_bt"/></li>
						  </ul>
						  </form>
						  </div>
            <div class="clear"></div>
          </div>
       <div class="box_kuan1" id="box_login2">       
       <div class="login">
		  				<form action="${storeDomain}/web/threeauth!registerBuild.action" id="regeditForm" method="post" onsubmit="">
		  				  <input type="hidden" name="lvUserTh.thType" value="${lvUserTh.thType}"/>
						  <input type="hidden" name="token" value="${token}"/>
						  <input type="hidden" name="jumpurl" value="${storeDomain}/web/threeauth!success.action?lvUserTh.thType=${lvUserTh.thType}"/>
						  	
	<ul>
		<li id="erro_msg0" style="display: none;">
		  <p style="padding-left: 100px;"><span id="msgg" class="redfont"></span></p>
		</li>

		
		<li>
		  <p class="text"><font class="redfont">*</font>账号：</p>
		  <p class="input">
		    <input type="text" class="input02" id="email" onblur="validateEmail();" onfocus="textFocus('email');"
		    name="lvAccount.email" maxlength="32" value="请输入Email地址"  dvalue="请输入Email地址"/></p>
		</li>
		<li class="prompt">
		  <p class="pt" style="display:none;" id="email_tip1">请输入Email地址，该地址将成为登录账户及系统邮件接收地址，请确认可以使用！</p>
		  <!--输入框获得焦点状态提示语-->
		  <p class="er" style="display:none;" id="email_tip2"></p>
		  <!--错误状态提示语-->
		</li>
	  </ul>
	  
	  <ul>
		<li>
		  <p class="text"><font class="redfont">*</font>密码：</p>
		  <p class="input">
		    <input name="lvAccount.pwd" id="rpwd"  type="password" class="input02" maxlength="16"
		    onfocus="textFocus('pwd')" onblur="validatePwd();" value=""/></p>
		</li>
		<li class="prompt">
		  <p class="pt" style="display:none;" id="pwd_tip1">6-16位字符，可使用字母、数字或符号的组合，不建议使用纯字母、纯数字、纯符号！</p>
		  <!--输入框获得焦点状态提示语-->
		  <p class="er" style="display:none;" id="pwd_tip2"></p>
		  <!--错误状态提示语-->
		</li>
	  </ul>
	  
	  <ul>
		<li>
		  <p class="text"><font class="redfont">*</font>确认密码：</p>
		  <p class="input">
		    <input name="truePwd" id="truePwd"  type="password" class="input02" maxlength="16"
		    onfocus="textFocus('tpwd')" onblur="validateTruePwd();" value=""/></p>
		</li>
		<li class="prompt">
		  <p class="pt" style="display:none;" id="tpwd_tip1">请再次输入密码！</p>
		  <!--输入框获得焦点状态提示语-->
		  <p class="er" style="display:none;" id="tpwd_tip2">两次密码输入不一致！</p>
		  <!--错误状态提示语-->
		</li>
	  </ul>
	  
	  
	  <ul>
		<li>
		  <p class="text"><font class="redfont">*</font>验证码：</p>
		  <p><input name="code" id="rcode2"  type="text" class="input22" maxlength="4" onfocus="textFocus('code')"
		  style="width: 40px" onblur="validateCode();"/></p>
		  <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;"
		  onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
		  <p><font class="kbqc">看不清楚？</font><a href="javascript:void(0);" 
		  onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()"class="ljh">换一张</a></p>
		</li>
		<li class="prompt" >
		  <p class="pt" style="display:none;" id="code_tip1">请输入验证码！</p>
		  <!--输入框获得焦点状态提示语-->
		  <p class="er" style="display:none;" id="code_tip2">验证码错误！</p>
		  <!--错误状态提示语-->
		</li>
	  </ul>
	  
	  <ul class="btn">
		<li><input type="submit" value="注册" class="user_reg_bt" id="registerbut"/></li>
	  </ul>
		  </form>
		 </div>
        <div class="clear"></div>
       </div>
    </div>
  </div>
  <!--End left-->
  	  	<!--End left-->
		  
		  	<div class="sc_main_right">
			    <!-- 右侧第一个广告 -->
				<ad:ad adkey="AD_TVPAD_RIGHT1"></ad:ad>
				<!-- 右侧第二个广告 -->
				<ad:ad adkey="AD_TVPAD_RIGHT2"></ad:ad>
		  	</div>
		  	<!--End right-->
		  
			<div class="cb"></div>  
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
</body>
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
</html>
