<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvNetworkAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>商城：</label>
					<select name="lvNetwork.storeId" class="required" onchange="storeChange()">
					<option value="">选择所属商城</option>
					<s:iterator value="#request.storeList" id="store"><option value="${store.storeFlag}">${store.name}</option></s:iterator>
					</select>
				</p>
				<p>
					<label>国家：</label>
					<select name="lvNetwork.contryId" class="required" id="contryId" onchange="contryChange()">
					<option value="">选择所属国家</option>
					<s:iterator value="#request.areaList" id="area"><option value="${area.id}">${area.namecn}</option></s:iterator>
					</select>
				</p>
				
				<p>
					<label>城市：</label>
					<input name="lvNetwork.city" type="text" size="30" maxlength="50"/>
				</p>
				<p>
					<label>网点名称：</label>
					<input name="lvNetwork.channelName" type="text" size="30" maxlength="100"/>
				</p>
				<p>
					<label>详细地址：</label>
					<input name="lvNetwork.address" type="text" size="30" maxlength="100"/>
				</p>
				<p>
					<label>负责人：</label>
					<input name="lvNetwork.responsiblePerson" type="text" size="30" maxlength="50"/>
				</p>
				<p>
					<label>排序值：</label>
					<input name="lvNetwork.orderValue" type="text" size="30" value="0" maxlength="5" class="required number"/>
				</p>
				<p>
					<label>语言：</label>
					<s:select list="#{'cn':'中文简体','tw':'中文繁体','en':'英文','kn':'韩文'}" name="lvNetwork.webLanguage"></s:select>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>