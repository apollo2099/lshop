<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
	
</script>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action=""
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="lvVbPlans.createTime" value="<s:date name="lvVbPlans.createTime" format="yyyy-MM-dd HH:mm:ss"/>">
			<input type="hidden" name="lvVbPlans.mallFlag" value="${lvVbPlans.mallFlag}"/>
			<input type="hidden" name="lvVbPlans.id" value="${lvVbPlans.id}"/>
			<input type="hidden" name="lvVbPlans.ptype" value="${lvVbPlans.ptype}"/>
			<div class="pageFormContent" layoutH="56">
				<dl>
					<dt style="text-align: right;">
						名称：
					</dt>
					<dd>
					<input name="config.name" type="text" style="width: 500px;" value="${config.name}"
							style="" maxlength="128" class="required" />
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						配置值：
					</dt>
					<dd>
						<textarea name="config.val" type="text" style="width: 500px;height: 60px;"
						 maxlength="">${config.val}</textarea>
					</dd>
				</dl>
				<dl style="margin-top: 40px;">
					<dt style="text-align: right;">
						描述：
					</dt>
					<dd>
						<textarea name="" type="text" style="width: 500px;height: 60px;"
						 maxlength="">${config.cdesc}</textarea>				
					</dd>
				</dl>
				<dl style="margin-top: 40px;">
					<dt style="text-align: right;">
						类型：
					</dt>
					<dd>
						<select name="config.type" onchange="" 
						style="width: 500px;" class="required">
							<option value="">请选择类型</option>
							<option  value="0" <c:if test="${config.type==0 }">selected</c:if>>公共配置</option>
							<option  value="1" <c:if test="${config.type==1 }">selected</c:if>>tvpad项目</option>
							<option  value="2" <c:if test="${config.type==2 }">selected</c:if>>banana项目</option>
						</select>
					</dd>
				</dl>	
				
			</div>
			<div class="formBar">
				<ul>
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