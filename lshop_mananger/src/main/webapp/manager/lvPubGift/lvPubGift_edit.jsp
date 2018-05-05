<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPubGiftAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input name="lvPubGift.id" type="hidden" size="30" value="${lvPubGift.id}"/>
				<input name="lvPubGift.code" type="hidden" size="30" value="${lvPubGift.code}"/>
				<input name="lvPubGift.createTime" type="hidden" size="30" value="${lvPubGift.createTime}"/>
				<input name="lvPubGift.modifyTime" type="hidden" size="30" value="${lvPubGift.modifyTime}"/>
				
		
						<p>
							<label>赠品中文名称：</label>
							<input name="lvPubGift.giftName" type="text" size="32" maxlength="32" class="required" value="${lvPubGift.giftName}"/>
						</p>
						<p>
							<label>赠品英文名称：</label>
							<input name="lvPubGift.giftNameEn" type="text" size="32" maxlength="32" value="${lvPubGift.giftNameEn}"/>
						</p>
						<p>
							<label>SAS对接code：</label>
							<input name="lvPubGift.pcode" type="text" size="32" maxlength="32" class="required" value="${lvPubGift.pcode}"/>
						</p>
						<p>
							<label>状态：</label>
							<select name="lvPubGift.status" class="required">
								<option value="">=请选择=</option>
								<option value="1" <c:if test="${lvPubGift.status==1 }">selected="selected"</c:if>>启用</option>
								<option value="0" <c:if test="${lvPubGift.status==0 }">selected="selected"</c:if>>停用</option>
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