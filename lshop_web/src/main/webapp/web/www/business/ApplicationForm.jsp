<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>business_cooperation</title>
<%@include file="/web/www/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/www/res/js/ApplicationForm.js"></script>
<script type="text/javascript">
$(function(){
	  var obj= window.location.hash;
	  if(obj=="#true"){
	     alert('已成功提交申请，我们将及时处理并与您取得联系！谢谢！');
	     window.location.hash="";
	   }else if(obj=="#false"){
	     alert('提交失败,请稍后再试!');
	     window.location.hash="";
	   }
	});
</script>
</head>
<body>
<% request.setAttribute("navFlag","www_tvpadZs"); %>
<%@include file="/web/www/common/header.jsp" %>
<div class="content_main">
  
  <!--End left_Frame-->
<div class="right_frame" id="user_box">
	 	
		<div class="form_box" id="user_box1">
          <ul style="margin:0 auto">
				<li class="fometite">
				  <h2>TVpad意向经销商申请表</h2>
				</li>
				
			</ul>
<form action="/web/applicationEmail!applyApplicationFromEmail.action" method="post" onsubmit="return verifyApplyForm();">
<table width="100%" border="0" cellspacing="1"  class="fonmtable">
  <tr>
    <td height="50"><strong><span class="star">*</span>申请人/公司:</strong></td>
    <td height="50" colspan="3"><input type="text" name="app.applyCmp" id="applyCmp"  class="applicant"/></td>
    </tr>
  <tr>
    <td width="14%" height="50"><span class="star"><strong>*</strong></span><strong>联系人:</strong></td>
    <td width="21%" height="50"><input type="text" name="app.applyName" id="applyName" class="mailingaddress"/></td>
    <td width="11%" height="50"><span class="star"><strong>*</strong></span><strong>联系电话:</strong></td>
    <td width="54%" height="50"><input type="text" name="app.applyTel" id="applyTel" class="mailingaddress"/></td>
    </tr>
  <tr>
    <td height="50"><span class="star"><strong>*</strong></span><strong>E-mail:</strong></td>
    <td height="50" colspan="3">
        <input type="text" name="app.applyEmail" id="applyEmail" class="mailingaddress"/></td>
    </tr>
  <tr>
    <td height="50"><span class="star"><strong>*</strong></span><strong>通讯地址:</strong></td>
    <td height="50" colspan="3">
        <input type="text" name="app.applyAddr" id="applyAddr" class="applicant" value="国家 省/州 城市 区 "/></td>
    </tr>
  <tr>
    <td height="50"><p><span class="star"><strong>*</strong></span><strong>申请代理的区域:</strong></p></td>
    <td height="50" colspan="3">
    <input type="text" name="app.applyArea" id="applyArea" class="applicant" value="国家 省/州  市"/></td>
    </tr>
  <tr>
    <td height="50" colspan="4"><p><span class="star"><strong>*</strong></span><strong>申请理由（区域特征、行业优势、资源）</strong></p></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><textarea name="app.applyReason" id="applyReason" cols="45" rows="5"></textarea></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><p><span class="star"><strong>*</strong></span><strong>申请人/公司介绍</strong></p></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><textarea name="app.applyIntro" id="applyIntro" cols="45" rows="5"></textarea></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><strong>TVpad营销计划</strong></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><textarea name="app.applyPlan" id="textarea3" cols="45" rows="5"></textarea></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><strong>对TVpad公司的建议</strong></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><textarea name="app.appySuggest" id="textarea4" cols="45" rows="5"></textarea></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><span class="star"><strong>*</strong></span><strong>您是通过哪种方式得到TVpad招商信息的？</strong></td>
  </tr>
  <tr>
    <td height="50" colspan="4">
    
      <p class="p_label" id="getMsg">
        <label><input type="radio" name="app.type" value="网络" id="RadioGroup1_0" />1. 网络</label>
       
        <label><input type="radio" name="app.type" value="报纸杂志" id="RadioGroup1_1" />2. 报纸杂志</label>
        
        <label><input type="radio" name="app.type" value="朋友推荐" id="RadioGroup1_2" />3. 朋友推荐</label>
       
        <label><input type="radio" name="app.type" value="其他" id="RadioGroup1_3" />4. 其他</label>
        <input type="text" name="app.otherText" id="textfield13" />
      </p>
    </td>
  </tr>
  <tr>
    <td height="30" colspan="4" align="center"><p class="star" id="promptMsg"></p></td>
  </tr>
  
  <tr>
    <td height="50" colspan="4" align="center"><input type="submit"  value="提交" class="user_center_bt" style="height:34px; margin:0 auto; color:#fff"></td>
  </tr>
 
</table>
</form>

</div>
  </div>
  <!--End right_Frame-->
  <div class="cb"></div>	 
</div>
<!--foot-->
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html> 