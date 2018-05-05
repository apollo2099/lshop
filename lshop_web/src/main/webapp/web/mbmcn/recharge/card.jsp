<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>	
<title>banana商城_充值卡充值</title>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/checkForm.js"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/recharge.js"></script>
</head>
<body>
<%@include file="/web/mbmcn/user_center/c_top.jsp"%>

<article>
	<form action="/web/docard.action" id="docartform">
  <table width="94%" border="0" align="center" style="margin-top:20px;">
	<tr  id="item_account">
	  <td width="30%" height="80" align="right" class="fonsi">账号：</td>
	  <td width="70%" height="80" colspan="3">
	   <div class="inputd">
	    <input id="accounts" name="recharge.accounts" value="${requestScope.email}" placeholder="请输入您的账号" 
	    maxlength="60" type="text" class="inpu"/>
	    	    
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
    <td width="30%" height="80" align="right" class="fonsi">充值卡卡号：</td>
    <td width="70%" height="80" colspan="3">
        <div class="inputd">
              <input id="rcardNum" name="recharge.rcardNum"  placeholder="请输入充值卡卡号"
              maxlength="20" type="text" class="inpu" value="${requestScope.recharge.rcardNum}"/>
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
    <td width="30%" height="80" align="right" class="fonsi">充值卡密码：</td>
     <td width="70%" height="80" colspan="3">
       <div  class="inputd">
         <input id="cardPwd" name="cardPwd" maxlength="30" type="password" class="inpu"/>
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
    <td width="30%" height="80" align="right"><span class="fonsi">验证码：</span></td>
    <td width="16%" height="80" class="md">
     <div  class="inputd">
     	<input id="validCode" name="validCode" maxlength="4" type="text" class="inpu" style="text-indent:0; text-align:center"/>
         <div class="tip">
          <em></em>
          <span class="errInfo" style="width:100px"></span>
          <i></i>
          <b></b>
         </div>
       </div>
    </td>
    <td width="14%" align="right" class="asty">
    	<img src="/web/phoneimager.jsp" id="rcId" style="cursor:pointer;"
				onclick="javascript:this.src=this.src+'?'+new Date()" />
    </td>
    <td width="20%" align="right" class="asty">
    	<a href="javascript:void(0);"
	     	onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">
	     	<span style="color:#0068b7">看不清楚? 换一张</span>
	    </a>
    </td>
  </tr>
  <c:if test="${not empty requestScope.resultMsg}">
	  <tr>
	    <td width="30%" height="60" align="right" class="fonsi"></td>
	    <td width="70%" height="60" colspan="3">
	       <span style="color:red">${requestScope.resultMsg}</span>
	    </td>
	  </tr>
  </c:if>
  <tr>
    <td height="80" colspan="4"><input type="button"  class="logins" value="提交" onclick="subFormCart();"/></td>
    </tr>
</table>
</form>
</article>


<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '充值卡充值';
</script>
</body>
</html>
