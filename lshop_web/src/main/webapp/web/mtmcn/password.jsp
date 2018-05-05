<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_找回密码</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/checkForm.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/findPass.js"></script>
</head>

<%String erromsg = (String)request.getAttribute("erromsg");
String msg = "";
if(null != erromsg){
	if(erromsg.equals("0")){
		msg = "账号未激活！";
	}
	if(erromsg.equals("-1")){
		msg = "账号不存在！";
	}
	if(erromsg.equals("-2")){
		msg = "验证码错误！";
	}
}
%>

<body>
<header>
   <div class="top">
      
      <div class="shopping">
        <div class="shoplebg1">
            <div class="shopicon1"><a href="/"></a></div>
         </div>
      </div>
      <div class="title">
        <h1>找回密码</h1>
       
      </div>
   </div>
</header>

<article>
  <form name="myForm" action="/web/userCenterManage!password.action"  method="post" id="findPassForm" onsubmit="return submitFindPass();">
  <table width="94%" border="0" align="center" style="margin-top:20px">
	  <tr>
	    <td height="80" colspan="3" align="left"  class="fonsi">请输入你注册时填写的Email地址</td>
	  </tr>
	  
	  <tr>
	    <td height="40" colspan="3" align="center">
	      <div  class="inputd">
	        <input type="text"  class="inpu" value="请输入Email地址" name="lvAccount.email" defalt="请输入Email地址"/>
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
	    <td height="40" colspan="3" align="left"  class="fonsi">验证码：</td>
	  </tr>
	  
	  <tr>
	     <td width="31%" height="80" align="right">
	     <div  class="inputd">
	         <input type="text"  class="inpu" id="validCode" name="code" maxlength="4" 
	         value="" style="text-indent:0; text-align:center"/>
	         <div class="tip">
	          <em></em>
	          <span class="errInfo"></span>
	          <i></i>
	          <b></b>
	         </div>
	     </div>
	     </td>
	     <td width="18%" height="80" align="right" class="asty">
	     	<img src="/web/phoneimager.jsp" id="rcId" style="cursor:pointer;"
				onclick="javascript:this.src=this.src+'?'+new Date()"/></td>
	     <td width="34%" height="80" align="right" class="asty">
	     	
     		<a href="javascript:void(0);"
     		onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">
     			<span style="color:#0068b7">看不清楚? 换一张</span>
     		</a>
	     	
	     </td>
	  </tr>
	  
	  <c:if test="${erromsg != null }">
	  	<td height="60" colspan="3"><font style="color: red;" id="pwd_tip"><%=msg %></font></td>
	  </c:if>
	  
	  <tr>
	    <td height="125" colspan="3"><input type="submit" value="下一步"  class="logins"/></td>
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
