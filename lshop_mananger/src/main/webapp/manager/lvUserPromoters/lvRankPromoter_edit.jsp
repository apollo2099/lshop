<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="page">
	<div class="pageContent">
		<form method="post" 
		      action="lvUserPromotersMngAction!editRankPromoter.action?json.navTabId=${json.navTabId}" 
		      class="pageForm required-validate" 
		      onsubmit="return validateCallback(this, dialogAjaxDone);">
			  <input type="hidden" name="rankFirstLogin.id" value="${rankFirstLogin.id}"/>
			  <input type="hidden" name="rankFirstLogin.couponNum" value="${rankFirstLogin.couponNum}"/>
			  <input type="hidden" name="rankFirstLogin.couponedNum" value="${rankFirstLogin.couponedNum}"/>
			  <input type="hidden" name="rankFirstLogin.createTime" value="${rankFirstLogin.createTime}"/>
			  <input type="hidden" name="rankFirstLogin.lasttime" value="${rankFirstLogin.lastTime}"/>
			  <input type="hidden" name="rankFirstLogin.pwd" value="${rankFirstLogin.pwd}"/>
			  <div class="pageFormContent" layoutH="56">
				<p>
					<label>昵称：</label>
					<input name="rankFirstLogin.nickname" class="required" type="text" size="25" maxlength="32" value="${rankFirstLogin.nickname}" lang="昵称"/>
				</p>
				<p>
				    <label>邮箱:</label>
				    <input name="rankFirstLogin.email" class="required email" type="text" size="25" maxlength="32" value="${rankFirstLogin.email}" lang="邮箱"/>
				</p>
			    <p>
				   <label>电话</label>
				   <input type="text" size="25" name="rankFirstInfo.tel" class="required number"  maxlength="60" value="${rankFirstInfo.tel}"/>
				</p>
				<p>
				   <label>所在地区</label>
				   <input type="text" size="25" name="rankFirstInfo.adress" class="required"  maxlength="60" value="${rankFirstInfo.adress}"/>
				</p>
				<p>
				   <label>推广一台获得佣金：</label>
				   <input type="text" size="25" name="rankFirstInfo.specialAmount" class="required number"  maxlength="9" value="${rankFirstInfo.specialAmount}"/>美元
				</p>
				<p>
				   <label>备注：</label>
				   <input type="text" size="40" name="rankFirstInfo.description"  maxlength="100" value="${rankFirstInfo.description}"/>
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