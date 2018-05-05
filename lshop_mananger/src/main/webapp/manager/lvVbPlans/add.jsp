<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script type="text/javascript">
	function changeVbType(val){
		if(val=="false"){
			$(".vb_input_type").css("display","none");
			$(".vb_choose_type").css("display","");
			$("#vbNumClassId").addClass("required");
			$("#minNumId").removeClass("required");
		}else{
			$(".vb_input_type").css("display","");
			$(".vb_choose_type").css("display","none");
			$("#vbNumClassId").removeClass("required");
			$("#minNumId").addClass("required");
			
		}
		
	}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="/manager/vbPlansMngAction!save.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<dl>
					<dt style="text-align: right;">
						所属商城：
					</dt>
					<dd>
					 <select name="lvVbPlans.mallFlag" class="required">
							<option value="">--请选择--</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }">${store.name} <s:iterator value="#request.exchangeRateList" id="o"><c:if  test="${o.currency==store.currency}">(lVB=${o.exchangeRate}${o.currency})</c:if></s:iterator></option>
						    </c:foreach>
					   </select>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						排序值：
					</dt>
					<dd>
						<input name="lvVbPlans.porder" type="text" style="width: 200px;"
							style="" maxlength="6" class="required digitsNoZore" />
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						选择VB选项类型：
					</dt>
					<dd>
						<select name="lvVbPlans.ptype" onchange="changeVbType(this.value)">
							<option value="false">选择类型</option>
							<option value="true">输入类型</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						选项名称：
					</dt>
					<dd>
						<input name="lvVbPlans.title" type="text" style="width: 200px;"
							style="" maxlength="16" class="required" />
					</dd>
				</dl>
				<dl  class="vb_choose_type">
					<dt style="text-align: right;">
						选项VB数量：
					</dt>
					<dd>
						<input name="lvVbPlans.vbNum" id="vbNumClassId" type="text" style="width: 200px;"
							style="" maxlength="8" class="required digitsNoZore" />
					</dd>
				</dl>
				
				<dl class="vb_input_type" style="display:none">
					<dt style="text-align: right;">
						VB输入最小值：
					</dt>
					<dd>
						<input name="lvVbPlans.minNum" c type="text" style="width: 200px;"
							style="" maxlength="6" id="minNumId" class="digitsNoZore"/>
					</dd>
				</dl>
				<dl  class="vb_input_type" style="display:none">
					<dt style="text-align: right;">
						是否填写倍数：
					</dt>
					<dd>
						是<input type="radio" name="lvVbPlans.isMul" value="true"/>  否<input type="radio" name="lvVbPlans.isMul" checked="checked" value="false"/>
					</dd>
				</dl>
				<dl  class="vb_input_type" style="display:none">
					<dt style="text-align: right;">
					*
					</dt>
					<dd>
						<span class="red" style="color:red">  温馨提示:输入类型的数据只能添加一条</span>
					</dd>
				</dl>
				
				
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