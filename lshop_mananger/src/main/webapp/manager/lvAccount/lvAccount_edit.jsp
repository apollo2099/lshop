<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
<div class="pageContent">
	<form method="post" action="lvAccountAction!edit.action?json.navTabId=${json.navTabId}" 
	        class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);" >
		<input type="hidden" name="oldpwd" value="${unifiedUser.password}" />
		<input type="hidden" name="unifiedUser.code" value="${unifiedUser.code}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>登录账户：</label>
				<input name="unifiedUser.account" class="required email" type="text" size="30" maxlength="32" readonly="readonly" value="${unifiedUser.account}" alt="请输入用户登录账号" />
			</p>
			<p>
				<label>昵称：</label>
				<input name="unifiedUser.nickname" class="required" type="text" size="30" maxlength="32" value="${unifiedUser.nickname}" alt="请输入昵称"/>
			</p>
			<p>
				<label>密码：</label>
				<input name="unifiedUser.password" class="required" type="password" size="30" maxlength="32" value="${unifiedUser.password}" alt="请输入重置的密码"/>
			</p>
			<p>
				<label>性别：</label>
				<select name="unifiedUser.sex">
					<option value="0" <c:if test="${unifiedUser.sex eq '0'}">selected</c:if>>男</option>
					<option value="1" <c:if test="${unifiedUser.sex eq '1'}">selected</c:if>>女</option>
				</select>
			</p>
			<p>
				<label>QQ：</label>
				<input name="unifiedUser.qq"  type="text" size="30" maxlength="16" value="${unifiedUser.qq}" alt="请输入QQ"/>
			</p>
			<p>
				<label>MSN：</label>
				<input name="unifiedUser.msn"  type="text" size="30" maxlength="32" value="${unifiedUser.msn}" alt="请输入MSN"/>
			</p>
			<p>
				<label>真实姓名：</label>
				<input name="unifiedUser.name" class="required" type="text" size="30" maxlength="32" value="${unifiedUser.name}" alt="请输入真实姓名"/>
			</p>
			<p>
				<label>电话：</label>
				<input name="unifiedUser.tel"  type="text" size="30" maxlength="16" value="${unifiedUser.tel}" alt="电话"/>
			</p>
			<p >
				<label>手机：</label>
				<input name="unifiedUser.phone"  type="text" size="30" maxlength="32" value="${unifiedUser.phone}" alt="请输入手机"/>
			</p>
			<p >
				<label>用户状态：</label>
				<select name="unifiedUser.status">
					<option value="1" <c:if test="${unifiedUser.status eq '1'}">selected</c:if>>启用</option>
					<option value="0" <c:if test="${unifiedUser.status eq '0'}">selected</c:if>>停用</option>
				</select>
			</p>
             <dl class="nowrap">
				    <dt>联系地址：</dt>
				   <dd>
				    <label >
                   <input name="unifiedUser.address" type="text" size="20" maxlength="32" value="${unifiedUser.address}" alt="街道" />
                   </label>
                     <label >
                   <input name="unifiedUser.city" type="text" size="20" maxlength="32" value="${unifiedUser.city }" alt="县/市" />
                   </label>
                    <label >
                   <input id="txtProvince" name="unifiedUser.province" type="text" size="20" maxlength="32" value="${unifiedUser.province}" alt="洲/省" />
                   </label>
                    <label >
                   <select name="unifiedUser.countryCode">
						<option value="">==请选择==</option>
						<s:iterator value="#request.areaList"  id="c">
						  <option value="${c.code}" <c:if test="${unifiedUser.countryCode eq c.code}">selected</c:if>>${c.nameen}</option>
						</s:iterator>
						</select>
                   </label>
				   </dd>				  
			  </dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
</div>