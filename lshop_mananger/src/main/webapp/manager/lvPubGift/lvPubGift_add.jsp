<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPubGiftAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>赠品中文名称：</label>
							<input name="lvPubGift.giftName" type="text" size="32" maxlength="32" class="required"/>
						</p>
						<p>
							<label>赠品英文名称：</label>
							<input name="lvPubGift.giftNameEn" type="text" size="32" maxlength="32" />
						</p>
						<p>
							<label>SAS对接code：</label>
							<input name="lvPubGift.pcode" type="text" size="32" maxlength="32" class="required"/>
						</p>
						<p>
							<label>状态：</label>
							<select name="lvPubGift.status" class="required">
								<option value="">=请选择=</option>
								<option value="1">启用</option>
								<option value="0">停用</option>
						    </select>
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