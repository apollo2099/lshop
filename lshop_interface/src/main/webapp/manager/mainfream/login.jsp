<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>华扬国际管理平台</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascripts/jquery-1.4.4.min.js"></script>

</head>

<body>
	<c:set var="cfg_use_vdcode" value="Y" />
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#"><img src="themes/default/images/login_logo.gif" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul><!-- 
						<li><a href="#">设为首页</a></li>
						
						<li><a href="#">升级说明</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#">帮助</a></li>
						 -->						
					</ul>
				</div>
				<h2 class="login_title"><img src="themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<div class="error ${param.error==true?"":'hide'} " align="center"> 
				
				<font color="red" id="message">${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message }</font>
				<c:if test="${param.error=='4'}"><font color="red">权限不足!</font></c:if>
				<c:if test="${param.error=='3'}"><font color="red">该用户已在其他地址登陆!</font></c:if>
				<c:if test="${param.error=='2'}"><font color="red">登录超时,请重新登录!</font></c:if>
				<c:if test="${param.error=='1'}"><font color="red">退出成功!</font></c:if>
				</div>
				
				<form action="${pageContext.request.contextPath}/manager/j_spring_security_check" method="post" id="userloginform">
					<input type="hidden" name="validateflag" id="validateflag" value="${cfg_use_vdcode }" />
            		<input type="hidden" name="forwardUrl" value="${json.forwardUrl }" />			
					<p>
						<label>帐号：</label>
						<input type="text" name="j_username"  size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="j_password"  size="20" class="login_input" onpaste="return false" />
					</p>
					<c:if test="${cfg_use_vdcode == 'N'}">
					<p>
						<label>验证码：</label>
						<input class="code" name="validatecode" size="4" maxlength="4" id="rcode" value="${validatecode }"/>
						<span><img src="validate_code.jsp" id="img1" alt="" width="75" height="24" onclick="changeImage();" style="cursor: pointer;"/></span>
					</p>
					</c:if>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
			<!-- 
				<ul class="helpList">	 	
					请使用FireFox浏览器访问
					<li><a href="#" onclick="window.open('http://firefox.com.cn/');">下载FireFox浏览器</a></li> 
				</ul>
				 -->
				<div class="login_inner"><!-- 	
					<p>您可以使用 网易网盘 ，随时存，随地取</p>
					<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
					<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p> -->		
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2007-2015 華揚國際科技有限公司（HUA YANG INTERNATIONAL TECHNOLOGY LIMITED）.All Rights Reserved
		<!-- 
			Copyright &copy; 2009-2012 <a href="#" onclick="window.open('http://www.gvtv.com.cn/');">深圳好视网络科技有限公司</a>.All Rights Reserved
			 -->
		</div>
	</div>
	
	<script type="text/javascript" language="javascript">


function changeImage() 
{ 
  var time = (new Date()).valueOf();
  document.getElementById("img1").src="validate_code.jsp?time="+time;
}

function checkCode(){
    var msg = '';
	FF = (navigator.userAgent.indexOf("Firefox")!=-1);
	IE = (navigator.userAgent.indexOf("MSIE")!=-1);
  if(IE){
	 //msg += "请使用FireFox浏览器访问!";
  }


    var flag = '${cfg_use_vdcode}';
    
    if(flag == "Y"){
        $("#message").html('验证中...');
    
    var time = (new Date()).valueOf();
    var rcode = $("#rcode").val();
    var joinname = $("#joinname").val();
    var password = $("#password").val();
    

    
    if($.trim(joinname).length==0){
       msg += "请输入用户名!" + "<br/>";
    }
    if($.trim(password).length==0){
       msg += "请输入密码!" + "<br/>";
    }
    if($.trim(rcode).length==0){
       msg += "请输入验证码!";
    }
    
    if($.trim(msg).length > 0){
       $("#message").html(msg);
       return false;
    }
    var url = "checkSessionCode.jsp?rcode=" + rcode + "&time=" + time;
    $.post(url, null, function(json){

         if(json.statusCode == 300){
           $("#message").html(json.message);
           changeImage();
           return false;
         }else if(json.statusCode == 200){
           //$("#validateflag").val("N");
           $("#userloginform").submit();
           return true;
         }

     },"json");
     
     return false;
    }else{
      return true;
    }
  } 

</script>
	
</body>
</html>
