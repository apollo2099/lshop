<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_注册</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/checkForm.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/register.js"></script>
<style type="text/css">
.logins,.user_reg_bt,.user_reg_bt_disable{width:100%; height:70px; line-height:70px; background-color:#0099ff; color:#FFF; border:none; cursor:pointer; font-size:30px; font-family:"微软雅黑"}
.user_reg_bt_disable{background-color:#bbb;}
</style>
</head>

<body>
<header>
   <div class="top">
      
      <div class="shopping">
        <div class="shoplebg1">
            <div class="shopicon1"><a href="/"></a></div>
         </div>
      </div>
      <div class="title">
        <h1>注册</h1>
       
      </div>
   </div>
</header>

<article>
<form action="${storeDomain}/web/regeditAccount.action" id="regedit" method="post" >
  <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
  <table width="94%" border="0" align="center" style="margin-top:20px">
  <tr>
    <td height="80" align="right"  class="fonsi">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
    <td height="80" colspan="2" align="right"  class="fonsi">
     <div  class="inputd">
       <input type="text"  class="inpu"  value="请输入Email地址" name="lvAccount.email" defalt="请输入Email地址" id="email"  />
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
    <td height="80" align="right"  class="fonsi">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
     <td height="80" colspan="2" align="right"  class="fonsi">
       <div  class="inputd">
         <input type="text"  class="inpu" name="lvAccount.pwd" style="display:none;"/>
         <input type="password"  class="inpu" name="lvAccount.pwd"/>
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
    <td height="80" align="right"  class="fonsi">确认密码：</td>
     <td height="80" colspan="2" align="right"  class="fonsi">
       <div  class="inputd">
         <input type="password"  class="inpu" name="truePwd"/>
         <input type="text"  class="inpu" name="truePwd" style="display:none;"/>
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
		<td width="16%" height="80" align="right" class="fonsi">验证码：</td>
		<td height="80" colspan="2" align="right" >
		<div  class="inputd">
		<input type="text"  class="inpu" value="" name="code" 
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
			<img src="/web/phoneimager.jsp" id="rcId" style="cursor:pointer;" 
				onclick="javascript:this.src=this.src+'?'+new Date()"/>
		</td>
		<td height="80" align="right" class="asty" >
			<a href="javascript:void(0);"
     		onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">
     			<span style="color:#0068b7">看不清楚? 换一张</span>
     	</td>
	</tr>
    
  <tr>
    <td height="60">&nbsp;</td>
    <td height="60" width="20%" class="md"><input type="checkbox" name="checkbox" id="checkpwd">
      显示密码&nbsp;&nbsp;<font id="rmsg" color="red"></font></td>
    <td width="35%" height="60" class="md">&nbsp;</td>
    </tr>
  <tr>
    <td height="80" colspan="3">
       <input type="button" id="registerbut" onclick="submitRegister()" value="注 册"  class="user_reg_bt"/>
     </td>
    </tr>
</table>
</form>
  
</article>
<!-- 分享 -->
<%@include file="/web/mtmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mtmcn/common/footer.jsp" %>
</body>
</html>

