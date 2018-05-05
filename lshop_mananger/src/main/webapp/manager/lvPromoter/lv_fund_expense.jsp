<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPromtPayMngAction!expenseFund.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<input type="hidden" name="uid" value="${uid}"/>
			<div class="pageFormContent" layoutH="56">
			    <p>
			       <label>当前报销用户:</label><input type="text" value="${realname}" readonly="readonly"/>
			    </p>
			    <p>
			       <label>广告基金可用报销金额:</label><input type=text value="${enablefund}" readonly="readonly"/> 
			    </p>
				<p>
					<label>报销金额：</label>
					<input name="expenseFund.fund" class="required" type="text digit" size="25" maxlength="32" value="" lang="报销金额"/>
				</p>
				<p>
					<label>报销说明：</label>
					<input name="expenseFund.detail" class="required" type="text" size="25"  value="" lang="报销说明"/>
				</p>
				<p>
					<label>报销时间：</label>
					<input name="expenseFund.expensetime" readonly="readonly" class="required date" type="text" size="25" maxlength="32"  lang="报销时间"/>
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