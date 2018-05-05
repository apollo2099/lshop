<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvUserPromotersMngAction!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>昵称：</label>
					<input name="rankFirstLogin.nickname" class="required" type="text" size="25" maxlength="32" value="" lang="昵称"/>
				</p>
				<p>
				    <label>邮箱:</label>
				    <input name="rankFirstLogin.email" class="required email" type="text" size="25" maxlength="32" value="" lang="邮箱"/>
				</p>
				<p>
					<label>密码：</label>
					 <input name="rankFirstLogin.pwd" class="required" type="text" size="25"  maxlength="19" value="" lang="密码"/>
				</p>
				<p>
				   <label>电话：</label>
				   <input type="text" size="25" name="rankFirstInfo.tel" class="required number"  maxlength="60" />
				</p>
				<p>
				   <label>所在地区：</label>
				   <input type="text" size="25" name="rankFirstInfo.adress" class="required"  maxlength="60" />
				</p>
				<p>
				   <label>推广一台获得佣金：</label>
				   <input type="text" size="25" name="rankFirstInfo.specialAmount" class="required number"  maxlength="9" />美元
				</p>
				<p>
				   <label>备注：</label>
				   <input type="text" size="40" name="rankFirstInfo.description"  maxlength="100" />
				</p>
				
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保存
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>

		</form>
	</div>
</div>