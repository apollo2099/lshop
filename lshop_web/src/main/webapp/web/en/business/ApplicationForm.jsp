<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>business_cooperation</title>
<%@include file="/web/en/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/en/res/js/ApplicationForm.js"></script>
<script type="text/javascript">
$(function(){
	  var obj= window.location.hash;
	  if(obj=="#true"){
	     alert('Has been successfully submitted an application, we will timely treatment and get in touch with you! Thank you!');
	     window.location.hash="";
	   }else if(obj=="#false"){
	     alert('Submit failed, please try again later!');
	     window.location.hash="";
	   }
	});
</script>
</head>
<body>
<% request.setAttribute("navFlag","en_tvpadZs"); %>
<%@include file="/web/en/common/header.jsp" %>
<div class="content_main">
  
  <!--End left_Frame-->
<div class="right_frame" id="user_box">
	 	
		<div class="form_box" id="user_box1">
          <ul style="margin:0 auto">
				<li class="fometite">
				  <h2>bananaTV Distributor Application Form</h2>
				</li>
				
			</ul>
<form action="/web/applicationEmail!applyApplicationFromEmail.action" method="post" onsubmit="return verifyApplyForm();">
<table width="100%" border="0" cellspacing="1"  class="fonmtable">
  <tr>
    <td height="50"><strong><span class="star">*</span>Applicant:</strong></td>
    <td height="50" colspan="3"><input type="text" name="app.applyCmp" id="applyCmp"  class="applicant"/></td>
    </tr>
  <tr>
    <td width="14%" height="50"><span class="star"><strong>*</strong></span><strong>ContactPerson:</strong></td>
    <td width="21%" height="50"><input type="text" name="app.applyName" id="applyName" class="mailingaddress"/></td>
    <td width="11%" height="50"><span class="star"><strong>*</strong></span><strong>Tel:</strong></td>
    <td width="54%" height="50"><input type="text" name="app.applyTel" id="applyTel" class="mailingaddress"/></td>
    </tr>
  <tr>
    <td height="50"><span class="star"><strong>*</strong></span><strong>E-mail:</strong></td>
    <td height="50" colspan="3">
        <input type="text" name="app.applyEmail" id="applyEmail" class="mailingaddress"/></td>
    </tr>
  <tr>
    <td height="50"><span class="star"><strong>*</strong></span><strong>Address:</strong></td>
    <td height="50" colspan="3">
        <input type="text" name="app.applyAddr" id="applyAddr" class="applicant" value="(street address)(city)(state)(country) "/></td>
    </tr>
  <tr>
    <td height="50"><p><span class="star"><strong>*</strong></span><strong>AgentRegion:</strong></p></td>
    <td height="50" colspan="3">
    <input type="text" name="app.applyArea" id="applyArea" class="applicant" value="(street address)(city)(state)(country)"/></td>
    </tr>
  <tr>
    <td height="50" colspan="4"><p><span class="star"><strong>*</strong></span><strong>Reasons for application (region features, industry advantages, resources, etc.)</strong></p></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><textarea name="app.applyReason" id="applyReason" cols="45" rows="5"></textarea></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><p><span class="star"><strong>*</strong></span><strong>Applicant Introduction</strong></p></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><textarea name="app.applyIntro" id="applyIntro" cols="45" rows="5"></textarea></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><strong>Marketing Plan for banana TV</strong></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><textarea name="app.applyPlan" id="textarea3" cols="45" rows="5"></textarea></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><strong>Advice & suggestions</strong></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><textarea name="app.appySuggest" id="textarea4" cols="45" rows="5"></textarea></td>
  </tr>
  <tr>
    <td height="50" colspan="4"><span class="star"><strong>*</strong></span><strong>Your access to banana TV agent recruitment information</strong></td>
  </tr>
  <tr>
    <td height="50" colspan="4">
    
      <p class="p_label" id="getMsg">
        <label><input type="radio" name="app.type" value="Internet" id="RadioGroup1_0" />1. Internet</label>
       
        <label><input type="radio" name="app.type" value="Newspaper/Magazine" id="RadioGroup1_1" />2. Newspaper/Magazine</label>
        
        <label><input type="radio" name="app.type" value="Friends" id="RadioGroup1_2" />3. Friends</label>
       
        <label><input type="radio" name="app.type" value="Other" id="RadioGroup1_3" />4. Other</label>
        <input type="text" name="app.otherText" id="textfield13" />
      </p>
    </td>
  </tr>
  <tr>
    <td height="30" colspan="4" align="center"><p class="star" id="promptMsg"></p></td>
  </tr>
  
  <tr>
    <td height="50" colspan="4" align="center"><input type="submit"  value="submit" class="user_center_bt" style="height:34px; margin:0 auto; color:#fff"/></td>
  </tr>
 
</table>
</form>

</div>
  </div>
  <!--End right_Frame-->
  <div class="cb"></div>	 
</div>
<!--foot-->
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html> 