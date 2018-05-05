<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function (){
	   var users=lshop.getCookieToJSON('rankpromoter');
		if(users.nickname!=null&&users.nickname!=''){
		 $('#nicknameId').text(users.nickname)
		}
}
);
</script>
<div class="top">
	<ul>
		<li class="left"><img src="images/top_logo.gif" /></li>
		<li class="right">您好！ <span id="nicknameId"></span>  <a href="rankpromt!getPromtCodeList.action" class="link01">个人中心</a>  <a href="rankpromt!logout.action">[退出]</a></li>
	</ul>
</div>