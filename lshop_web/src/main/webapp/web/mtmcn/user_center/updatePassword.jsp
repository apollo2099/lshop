<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mtmcn/user_center/c_meta.jsp"%>
<title>TVpad商城_账户安全</title>
</head>
<body>
	<%@include file="/web/mtmcn/user_center/c_top.jsp"%>
	<article>
		<form action="${MallPath}/web/userCenterManage!updatePassword.action" method="post">
			<table width="94%" border="0" align="center" style="margin-top: 20px">
				<tr>
					<td width="25%" height="80" align="center" class="fontso">旧密码：</td>
					<td width="80%" height="80" colspan="2">
						<div class="inputd">
							<input name="pwd" type="password" class="inpu" id="pwd" value="" placeholder="请输入旧密码" />
							<div class="tip">
								<em></em> <span>请输入正确的密码</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" height="80" align="center" class="fontso">新密码：</td>
					<td width="80%" height="80" colspan="2">
						<div class="inputd">
							<input name="newPwd" type="password" class="inpu" id="newPwd" value="" placeholder="请输入新密码" />
							<div class="tip">
								<em></em> <span>请输入正确的密码</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" height="80" align="center" class="fontso">确认密码：</td>
					<td height="80" colspan="2">
						<div class="inputd">
							<input name="truePwd" type="password" class="inpu" id="truePwd" value="" placeholder="请再次输入新密码" />
							<div class="tip">
								<em></em> <span>两次密码不相同</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="80" colspan="3"><input type="submit" value="保存修改"
						class="logins" /></td>
				</tr>
			</table>
		</form>
	</article>
	<!-- 对话框 -->
	<div id="c_success_area" class="mark_tip">
	   <div class="mark_tip_titile"><h2>修改成功</h2></div>
	   <div class="tipdetail"></div>
	   <div class="bt_marktip">
	     <a id="c_cancle_btn" href="javascript:void(0);" style="margin:0 auto; float:none">返回</a>
	   </div>
	</div>
	
	<%@include file="/web/mtmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
		document.getElementById('c_title').innerHTML = '账户安全';
		//对话框
		$('#c_cancle_btn').click(function(e){
			uncenter($('.mark_tip'));
		});
		<c:if test="${not empty mFlag}">
		<c:if test="${mFlag==1}">
		//修改失败
		errorTip('#pwd', '密码输入错误!');
		$('#pwd').one('focus', function(e){
			//清除
			successTip(this);
		});
		</c:if>
		<c:if test="${mFlag==2}">
		//修改成功
		center($('#c_success_area'));
		</c:if>
		</c:if>
		//表单校验
		function checkPwd(){
			$('#truePwd').focus();
		}
		function checkRepetPwd(){
			var pwd1 = $('#newPwd').val(), $p2 = $('#truePwd'), pwd2 = $p2.val();
			if( pwd1 != pwd2){
				errorTip('#truePwd', '两次输入密码不一致');
			}
		}
		$('#pwd').bind('valiField', {name: '密码', min: 6, max: 16}, valiField);
		$('#newPwd').bind('valiField', {name: '密码', min: 6, max: 16, callback: 'checkPwd'}, valiField);
		$('#truePwd').bind('valiField', {name: '密码', min: 6, max: 16, callback: 'checkRepetPwd'}, valiField);
	</script>
</body>
</html>
