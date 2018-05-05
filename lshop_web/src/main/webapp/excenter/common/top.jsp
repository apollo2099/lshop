<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function (){
	   var users=lshop.getCookieToJSON('exuser');
		if(users.nickname!=null&&users.nickname!=''){
		 $('#nicknameId').text(users.nickname)
		}
}
);
</script>
<div class="top">
	<ul>
		<li class="left"><a href="index.html"><img src="/excenter/images/top_logo.gif" /></a></li>
		<li class="right">您好！ <span id="nicknameId"></span>  <a href="/excenter/promtManager!promtCodeList.action" class="link01">个人中心</a>  <a href="/excenter/excenter!logout.action">[退出]</a></li>
	</ul>
</div>