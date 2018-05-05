<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/excenter/common/header.jsp" %>
<script type="text/javascript">
$().ready(function() {
	$("#myform").validate({
		rules: {
			pwd: {
				required: true,
				minlength: 6
				
			},
	        newPwd:{
		     required: true,
		     minlength: 6,
		     maxlength: 16
            }
            ,truePwd:{
		     required: true,
		     minlength: 6,
		     equalTo: "#newPwd"
            }
			
		},
		messages: {
			pwd: {
				required: "请输入原密码！",
				minlength: "密码不能少于6位字符！"
			},
			newPwd: {
				required: "请输入新密码！",
				minlength: "密码不能少于6位！",
				maxlength: "密码不能大于16位字符！"
			},
			truePwd: {
				required: "再输入一次上面的密码！",
				minlength: "密码不能少于6位！"
				,equalTo: "新密码确认与新密码不一样！"
			}
		}
	});
});
</script>
   <style type="text/css">
       label.error {    
           color: red;    
       }   
</style>
<!--顶部信息-->
<%@include file="top.jsp"%>
<div class="clear_p"></div>
<!--banner部份-->
<jsp:include page="menu.jsp">
<jsp:param value="profile" name="p"/>
</jsp:include>
<!--主要内容-->
<div class="main_conten3">
	<!--个人资料-->
	<div class="profile">
		<form action="rankpromt!doEditpwd.action" id="myform" method="post">
		<ol>修改密码</ol>
		<ul>
		    <li>
		    <p class="pr_left"></p>
		    <p class="pr_right" style="color:red;">${msg}</p>
		    <p></p>
		    </li>
			<li>
				<p class="pr_left">原密码：</p>
				<p class="pr_right"><input type="password" name="pwd" size="30" maxlength="60" /></p>
			</li>
			<li>
				<p class="pr_left">新密码：</p>
				<p class="pr_right"><input type="password" name="newPwd" id="newPwd" size="30" maxlength="30" /></p>
			</li>
			<li>
				<p class="pr_left">新密码确认：</p>
				<p class="pr_right"><input type="password" name="truePwd" id="truePwd" size="30" maxlength="30" /></p>
			</li>	
			<li>			
				<p class="pr_left">&nbsp;</p>
				<p class="pr_right"><input name="提交" type="submit" class="button_01" value="修改密码" style="cursor:pointer;"/></p>
			</li>
		</ul>
		</form>
		<% session.setAttribute("msg",""); %>
	</div>
	<div class="clear_p"></div>
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>

