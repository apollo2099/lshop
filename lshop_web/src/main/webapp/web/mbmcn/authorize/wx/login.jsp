<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>Banana商城_微信同步登陆</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/checkForm.js?time=<%=timeFlag %>"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/authlogin.js?time=<%=timeFlag %>"></script>
</head>

<body>
	<header>
		<div class="top">

			<div class="shopping">
				<div class="shoplebg1">
					<div class="shopicon1">
						<a href="/index.html"></a>
					</div>
				</div>
			</div>
			<div class="title">
				<h1>微信同步登陆</h1>

			</div>
		</div>
	</header>
	<article>
		<section>
			<div class="tucubg">

				<div class="tucubg_wel1">
					您已经通过微信登陆成功!<br /> 请绑定Banana账号，以后登陆Banana会更方便！
				</div>

			</div>
		</section>

	</article>

	<article>
		<div style="margin-top: 20px; background-color: #ebecee"
			class="mycomment">
			<div class="micor_commentbt">
				<span id="curspan1">已有账号登录绑定</span> <span>新注册用户绑定</span>

			</div>

			<div class="myconmmentsubdivbox"
				style="padding-bottom: 0; display: block; background-color: #ebecee">
				<form action="" id="authloginform" method="post">
				<input type="hidden" name="sessionId" value="${sessionId }">
				<input type="hidden" name="token" value="${token }">
				<input type="hidden" name="authtype" value="${authtype }">
  <table width="94%" border="0" align="center" style="margin-top:20px">
  <tr>
    <td height="80" align="right"  class="fonsi">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
    <td height="80" colspan="2" align="right"  class="fonsi">
      <div  class="inputd">
        <input type="text"  class="inpu"  value="${email }" name="uname" id="l_uname"
        defalt="请输入Email地址"/>
        <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
          </div>
      </div>    </td>
    </tr>
  <tr>
    <td height="80" align="right"  class="fonsi">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
    <td height="80" colspan="2" align="right"  class="fonsi">
      <div  class="inputd">
        <input type="password"  class="inpu" value="" name="pwd" defalt="" id="l_pwd"/>
        <div class="tip" id="p_tip">
          <em></em>
          <span class="errInfo" id="p_errInfo"></span>
          <i></i>
          <b></b>
          </div>
      </div>    
      </td>
     </tr>
  
  <tr>
    <td width="16%"  height="80" align="right" class="fonsi">验证码：</td>
    <td height="80" colspan="2" align="right" >
      <div  class="inputd">
        <input type="text"  class="inpu" value="" name="code" id="l_code" 
        style="text-indent:0; text-align:center" maxlength="4"/>
        <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
          </div>
       </div> 
      </td>
    </tr>
    <tr>
    <td width="16%"  height="80" align="right" ></td>
    <td height="80" align="left" >
         <img src="/web/phoneimager.jsp" id="l_rcId" style="cursor:pointer;"
				onclick="javascript:this.src=this.src+'?'+new Date()"/>
      </td>
    <td height="80" align="right" class="asty"><span style="color:#0068b7"
     		onclick="javascript:document.getElementById('l_rcId').src=document.getElementById('l_rcId').src+'?'+new Date()">看不清楚? 换一张</span></td>
    </tr>
    
	 <tr id="login_tr_msg">
	 	<td width="16%"  height="30" align="right" ></td>
	 	<td height="30" colspan="3"><font id="login_msgg" color="red"></font></td>
	 </tr>
 
  <tr>
    <td height="80" colspan="3"><input type="button" value="登录绑定"  class="logins" onclick="authloginformSubmit()" id="loginbut"/></td>
    </tr>
</table>
</form>
			</div>

<!-- 注册部分 --> 
			<div class="replayconmmentsubdivbox"
				style="background-color: #ebecee">
				<form action="" id="authregistform" method="post">
				<input type="hidden" name="sessionId" value="${sessionId }">
				<input type="hidden" name="token" value="${token }">
				<input type="hidden" name="authtype" value="${authtype }">
  <table width="94%" border="0" align="center" style="margin-top:20px">
  <tr>
    <td height="80" align="right"  class="fonsi">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
    <td height="80" colspan="2" align="right"  class="fonsi">
      <div  class="inputd">
        <input type="text"  class="inpu"  value="请输入Email地址" name="lvAccount.email" 
        defalt="请输入Email地址" id="r_uname"/>
        <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
          </div>
      </div>    </td>
    </tr>
  <tr>
    <td height="80" align="right"  class="fonsi">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
    <td height="80" colspan="2" align="right"  class="fonsi">
      <div  class="inputd">
        <input type="password" class="inpu" value="" name="lvAccount.pwd" defalt="" id="r_pwd"/>
        <div class="tip" >
          <em></em>
          <span class="errInfo" ></span>
          <i></i>
          <b></b>
          </div>
      </div>    </td>
     </tr>
  
  <tr>
    <td height="80" align="right"  class="fonsi">确认密码：</td>
    <td height="80" colspan="2" align="right"  class="fonsi">
      <div  class="inputd">
        <input type="password"  class="inpu" value="" name="truePwd" defalt="" id="r_truePwd"/>
        <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
          </div>
      </div>    </td>
     </tr>
  
  <tr>
    <td width="16%"  height="80" align="right" class="fonsi">验证码：</td>
    <td height="80" colspan="2" align="right" >
      <div  class="inputd">
        <input type="text"  class="inpu" value="" name="code" defalt="验证码" id="r_code"
        style="text-indent:0; text-align:center" maxlength="4"/>
        <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
          </div>
       </div> 
      </td>
    </tr>
    <tr>
    <td width="16%"  height="80" align="right" ></td>
    <td height="80" align="left" >
         <img src="/web/phoneimager.jsp" id="r_rcId" style="cursor:pointer;"
				onclick="javascript:this.src=this.src+'?'+new Date()"/>
      </td>
    <td height="80" align="right" class="asty"><span style="color:#0068b7"
    onclick="javascript:document.getElementById('r_rcId').src=document.getElementById('r_rcId').src+'?'+new Date()">看不清楚? 换一张</span></td>
    </tr>
 
 	<tr id="reg_tr_msg">
	 	<td width="16%"  height="30" align="right" ></td>
	 	<td height="30" colspan="3"><font id="reg_msgg" color="red"></font></td>
	 </tr>
	 
  <tr>
    <td height="80" colspan="3"><input type="button" value="注册绑定" class="logins" onclick="authregistformSubmit();" id="registerbut"/></td>
    </tr>
</table>
</form>
			</div>
		</div>
		</div>
	</article>

	<!-- 分享 -->
	<%@include file="/web/mbmcn/common/share.jsp"%>

	<!-- footer -->
	<%@include file="/web/mbmcn/common/footer.jsp"%>

</body>
</html>

