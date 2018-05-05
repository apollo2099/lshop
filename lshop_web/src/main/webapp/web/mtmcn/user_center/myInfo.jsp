<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mtmcn/user_center/c_meta.jsp"%>
<title>TVpad商城_用户中心</title>
<%Integer statu = (Integer)session.getAttribute("statu");
String msg = "";
if(null != statu){
	if(statu == 0){
		msg = "修改失败！";
	}
	if(statu == 1){
		msg = "修改成功！";
	}
	if(statu == -1){
		msg = "修改失败，该昵称已存在！";
	}
}
%>	
</head>
<body>
	<%@include file="/web/mtmcn/user_center/c_top.jsp"%>
	<article>
		<form action="${MallPath}/web/userCenterManage!editInfo.action"
			method="post" id="infoForm">
			<input type="hidden" name="lvAccountInfo.id" value="${lvAccountInfo.id}" />
			<input type="hidden" name="lvAccountInfo.userCode" value="${lvAccountInfo.userCode}" />
			<input type="hidden" name="lvAccountInfo.lastTime" value="${lvAccountInfo.lastTime}" />
			<input type="hidden" name="lvAccountInfo.storeId" value="${lvAccountInfo.storeId}" />
			<input type="hidden" name="lvAccountInfo.code" value="${lvAccountInfo.code}" />
			<input type="hidden" name="lvAccountInfo.createTime" value="${lvAccountInfo.createTime}" />
			<input type="hidden" name="lvAccountInfo.qq" value="${lvAccountInfo.qq}" />
			<input type="hidden" name="lvAccountInfo.msn" value="${lvAccountInfo.msn}" />
			<table width="94%" border="0" align="center" style="margin-top: 20px"
				class="infoma">
				<c:if test="${not empty statu}">
				<tr>
					<td width="25%" height="40" colspan="3" align="center" 
						style="color: red;"><%=msg %></td>
				</tr>
				</c:if>
				<%session.removeAttribute("statu"); %>
				<tr>
					<td width="25%" height="40" align="right" class="fontso">账 号：</td>
					<td height="40" colspan="2">${lvAccount.email }</td>
				</tr>
				<tr>
					<td width="25%" height="60" align="right" class="fontso">昵 称：</td>
					<c:if test="${not empty lvAccount.nickname}">
						<input type="hidden" name="lvAccount.nickname" class="input4" value="${lvAccount.nickname}"/>
						<td height="60" colspan="2">${lvAccount.nickname}</td>
					</c:if>
					<c:if test="${empty lvAccount.nickname}">
						<td height="60" colspan="2">
							<div class="inputd">
								<input type="text" class="inpu" id="ordnickname" value=""
									name="lvAccount.nickname" placeholder="请输入昵称" maxlength="32" />
								<div class="tip" id="tip1">
									<em></em> <span>请输入正确的昵称</span> <i></i> <b></b>
								</div>
							</div>
						</td>
					</c:if>
				</tr>
				<tr>
					<td width="25%" height="40" align="right" class="fontso">性 别：</td>
					<td height="40" colspan="2" valign="middle"><div class="inputd" style="line-height:54px;">
							<p>
								<label> <input type="radio" name="lvAccountInfo.sex"
									value="0"
									<s:if test="lvAccountInfo.sex==0 || lvAccountInfo.sex==null">checked="checked" </s:if> />
									男
								</label> <label> <input name="lvAccountInfo.sex" type="radio"
									<s:if test="lvAccountInfo.sex==1">checked="checked" </s:if>
									value="1" /> 女
								</label> <label> <input name="lvAccountInfo.sex" type="radio"
									value="2"
									<s:if test="lvAccountInfo.sex==2">checked="checked" </s:if> />
									保密
								</label>
							</p></div></td>
				</tr>
				<tr>
					<td width="25%" height="80" align="right" class="fontso">真实姓名：</td>
					<td height="80" colspan="2"><div class="inputd">
							<input name="lvAccountInfo.name" id="realName"
								value="${lvAccountInfo.name}" class="inpu" type="text"
								placeholder="请输入真实姓名" />
							<div class="tip" id="tip1">
								<em></em> <span>请输入正确的姓名</span> <i></i> <b></b>
							</div>
						</div></td>
				</tr>
				<tr>
					<td width="25%" height="80" align="right" class="fontso">手机号码：</td>
					<td height="80" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="mobile"
								value="${lvAccountInfo.mobile }" placeholder="请输入手机号码"
								name="lvAccountInfo.mobile" />
							<div class="tip" id="tip1">
								<em></em> <span>请输入正确的手机号码</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" height="80" align="right" class="fontso">电话号码：</td>
					<td height="80" colspan="2"><div class="inputd">
							<input name="lvAccountInfo.tel" value="${lvAccountInfo.tel}"
								id="tel" class="inpu" placeholder="请输入电话号码" type="text" />
							<div class="tip" id="tip1">
								<em></em> <span>请输入正确的电话号码</span> <i></i> <b></b>
							</div>
						</div></td>
				</tr>
				<tr>
					<td width="25%" height="60" align="right" valign="top"
						class="fontso">地 址：</td>
					<td height="60" colspan="2" valign="top">
						<div class="boxsele" style="width:100%">
							<select id="c_contry" class="spanchose1" name="lvAccountInfo.contryName">
								<option value="">请选择国家</option>
								<c:foreach items="${contryList}" var="c">
									<option value="${c.code}" <c:if test="${c.code==lvAccountInfo.contryName  }">selected</c:if> >${c.nameen}</option>
								</c:foreach>
							</select>
							<div id="contry_tip" class="tip">
								<em></em>
								<span>请选择国家</span>
								<i></i>
								<b></b>
							</div>
						</div></td>
				</tr>
				<tr>
					<td width="25%" height="60" align="center" class="fontso"></td>
					<td width="40%" height="60" valign="top">
						<div class="inputd" style="width: 94%">
							<input name="lvAccountInfo.provinceName" id="provinceName"
								type="text" class="inpu" value="${lvAccountInfo.provinceName }"
								placeholder="请输入洲/省" />
							<div class="tip">
								<em></em> <span>请输入正确洲/省</span> <i></i> <b></b>
							</div>
						</div>
					</td>
					<td width="40%" height="60" valign="top">
						<div class="inputd" style="width: 94%; float: right">
							<input name="lvAccountInfo.cityName" id="cityName" type="text"
								class="inpu" value="${lvAccountInfo.cityName }"
								placeholder="请输入市/县" />
							<div class="tip">
								<em></em> <span>请输入正确市/县</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" height="60" align="right" class="fontso"></td>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" value="${lvAccountInfo.address }"
								name="lvAccountInfo.address" id="adress" placeholder="请输入详细街道地址" />
							<div class="tip">
								<em></em> <span>请输入正确街道地址</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" height="80" align="right" class="fontso">邮 编：</td>
					<td height="80" colspan="3">
						<div class="inputd">
							<input type="text" class="inpu" name="lvAccountInfo.postCode"
								id="postcodeId" value="${lvAccountInfo.postCode}"
								placeholder="请输入邮编" />
							<div class="tip">
								<em></em> <span>请输入正确邮编</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<td height="80" colspan="4"><input type="submit" value="保存修改"
						class="logins" /></td>
				</tr>
			</table>
		</form>

	</article>

	<%@include file="/web/mtmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
		document.getElementById('c_title').innerHTML = '用户中心';
		
		function checkPhone(){
			if($('#tel').val() == "" && $('#mobile').val() == ""){
				errorTip('#tel', '电话和手机必须填写其中一个');
				errorTip('#mobile', '电话和手机必须填写其中一个');
			}
		}
		$('#ordnickname').bind('valiField', {name : '昵称', required : true, max : 32, reg : '^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$',
			txt: '不能使用非法字符'}, valiField);
		$('#realName').bind('valiField', {name : '真实姓名', required : true, max : 32, reg : '^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$',
			txt: '不能使用非法字符'}, valiField);
		$('#tel').bind('valiField', {name : '电话号码', max : 16, reg : '^[0-9\\-]{3,16}$',
			txt: '格式不正确', callback: 'checkPhone'}, valiField);
		$('#mobile').bind('valiField', {name : '手机号码', max : 16, reg : '^[0-9]{3,16}$',
			txt: '格式不正确', callback: 'checkPhone'}, valiField);
		$('#postcodeId').bind('valiField', {name : '邮编',required:true, max : 16, reg : '(?!\.*[\\u4E00-\\u9FA5])^\.*$',
			txt: '不能含有特殊字符'}, valiField);
		$('#provinceName').bind('valiField', {name : '洲/省',required:true, max: 32}, valiField);
		$('#cityName').bind('valiField', {name : '市/县',required:true, max: 32}, valiField);
		$('#adress').bind('valiField', {name : '街道地址',required:true, max: 128}, valiField);
		$('#c_contry').bind('valiField', function(e){
			//国家不能为空
			if($('#c_contry').val()){
				$('#c_contry').removeClass('error');
				$('#contry_tip').hide();
			} else {
				$('#c_contry').addClass('error');
				$('#contry_tip').show();
			}
		});
		//提交前执行的函数
		function beforeFormSubmit(){
			$('#c_contry').trigger('valiField');
		}
	</script>
</body>
</html>