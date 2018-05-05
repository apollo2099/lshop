<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxObtainMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>id：</label>
							<input name="wxObtain.id" type="text" size="30" maxlength="10" class="false"/>
						</p>
						<p>
							<label>用户的标识，对当前公众号唯一：</label>
							<input name="wxObtain.openid" type="text" size="30" maxlength="64" class="false"/>
						</p>
						<p>
							<label>用户的昵称：</label>
							<input name="wxObtain.nickname" type="text" size="30" maxlength="64" class="false"/>
						</p>
						<p>
							<label>领取类型：1-自己领取，2-好友支持：</label>
							<input name="wxObtain.obtainType" type="text" size="30" maxlength="10" class="false"/>
						</p>
						<p>
							<label>公众号配置id：</label>
							<input name="wxObtain.wxhConfigId" type="text" size="30" maxlength="10" class="false"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="wxObtain.createTime" class="textInput date" readonly="readonly" pattern="yyyy-MM-dd HH:mm:ss" type="text" size="30"/>
						</p>
						<p>
							<label>ip地址：</label>
							<input name="wxObtain.ipAddress" type="text" size="30" maxlength="15" class="true"/>
						</p>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>