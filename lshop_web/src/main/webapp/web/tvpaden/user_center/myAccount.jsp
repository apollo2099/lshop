<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/jquery.validate.min.1.8.1.js"></script>

<script type="text/javascript">

function emailSubScribe(obj){
	var flag=null;
	if(obj.checked){
		flag=true;
	}else{
		flag=false;
	}

    $.ajax({   
	     url: "/web/userCenterManage!subscribe.action",
		 data:"lvUserSubscribe.id="+$("#lsId").val()+"&lvUserSubscribe.uid="+$("#uid").val()+"&lvUserSubscribe.userName="+$("#userName").val()+"&lvUserSubscribe.email="+$("#myemail").val()+"&lvUserSubscribe.isSubscribe="+flag,   
		 type: "POST",  
		 dataType:"json",   
		 success: function(data){   
			 if(data!=null){
			    if(data.isSubscribe){
			      ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    		  ymPrompt.succeedInfo({title:'Tips',message:'Email subscription success!'}) ;
			      $("#lsId").val(data.id);
			    }else{
			       ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    		   ymPrompt.succeedInfo({title:'Tips',message:'Email subscription canceled!'}) ;
			    }
			 }else{
			 	  ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    		  ymPrompt.alert({title:'Tips',message:'Operation failure!'}) ;
			 }  
		 } 
		});
}
</script>

</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>

<!-- 用户邮件订阅关联用户数据 -->
<input type="hidden" name="lvUserSubscribe.id" id="lsId" value="${lvUserSubscribe.id }"/>
<input type="hidden" name="lvUserSubscribe.uid" id="uid" value="${lvUserSubscribe.uid }"/>
<input type="hidden" name="lvUserSubscribe.userName" id="userName" value="${lvUserSubscribe.userName }"/>
<input type="hidden" name="lvUserSubscribe.email" id="myemail" value="${lvUserSubscribe.email }"/>

<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font>  My Account</span></h1> 
		<div class="usercenter_box">
			<ul class="user_center_pp"><img src="${resDomain}/tvpaden/res/images/personel_center_icon01.gif" /></ul>	
			<ul class="user_center_tx">
			  <li>
			  		Welcome! <font class="fontwz" id="lvnicknameId"></font>,You last visited: <span id="lasttimeId"></span>			    
			    	<input type="checkbox"  value="1" onclick="javascript:emailSubScribe(this)" <s:if test="#request.lvUserSubscribe.id!=null">checked="checked"</s:if>/> E-mail Subscription
			   </li>
			</ul>
			<ul class="user_center_tips">
				<li>My Transaction Reminders</li>
				<li><a href="/web/userOrder!getOrderlist.action?payStatus=0">Awaiting Payment<font class="redfont">(${notPlayNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=0"> Awaiting Shipping<font class="redfont">(${notFaHuoNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=1"> Awaiting Receipt<font class="redfont">(${notShouHuoNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=2"> Awaiting Feedback<font class="redfont">(${notCommentNum})</font></a></li>
			</ul>		
		</div>
  </div>
	 <!--End right_Frame-->
</div>
<!--End content-->	
		
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 