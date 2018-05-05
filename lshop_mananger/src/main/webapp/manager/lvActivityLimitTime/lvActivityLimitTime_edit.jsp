<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<script>
//根据店铺变更，修改查询返回连接
function changeSelectURL(){
	var storeId=$("#storeId").find("option:selected");
	if(storeId!=null){
		$("#selProduct").attr("href",$("#txtProduct").val()+"&lvProduct.storeId="+storeId.val());
		$("#selProduct").show();
	}else{
		$("#selProduct").hide();
	}
}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvActivityAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvActivityLimitTime.id" type="hidden" size="30" value="${lvActivityLimitTime.id}"/>
			<input name="lvActivityLimitTime.activityCode" type="hidden" size="30" value="${lvActivityLimitTime.activityCode}"/>
			<input name="lvActivityLimitTime.code" type="hidden" size="30" value="${lvActivityLimitTime.code}"/>
			<input name="lvActivityLimitTime.createTime" type="hidden" size="30" value="${lvActivityLimitTime.createTime}"/>
			<input name="lvActivityLimitTime.oldProductCode" type="hidden" value="${lvActivityLimitTime.productCode}"/>
			
			<input name="lvActivity.id" type="hidden" value="${lvActivity.id }"/>
			<input name="lvActivity.code" type="hidden" value="${lvActivity.code }"/>
			<input name="lvActivity.typeKey" type="hidden" value="${lvActivity.typeKey }"/>
			<input name="lvActivity.createTime" type="hidden" value="${lvActivity.createTime }"/>
			<input name="lvActivity.oldStatus" type="hidden" value="${lvActivity.status}"/>
			<input name="lvActivity.status" type="hidden" value="${lvActivity.status}"/>
			
			<input id="txtProduct" type="hidden" value="lvProductAction!selectSimpleProduct.action?lvProduct.isActivity=1"/>
				<!-- 生成表单 -->
				<dl>
					<dt>活动名称：</dt>
					<dd>
						<input name="lvActivity.activityTitle" type="text" size="64" maxlength="120" class="required" value="${lvActivity.activityTitle}"/>
					</dd>				  
				</dl>
				<dl>
				    <dt>活动类型：</dt>
				    <dd>
				     <gv:dictionary type="select" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}" disabled="disabled"/>
				    </dd>
				</dl>
				<dl>
				    <dt>选择店铺：</dt>
				    <dd>
				     <select onchange="changeSelectURL()" name="lvActivityLimitTime.storeId" id="storeId" style="width:200px;">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList }" var="s">
						  <option value="${s.storeFlag }" <c:if test="${lvActivityLimitTime.storeId==s.storeFlag }">selected="selected"</c:if>>${s.name }</option>
						</c:foreach>
					  </select>
				    </dd>
				</dl>
				<dl>
				    <dt>选择商品：</dt>
				    <dd>
				   <input id="inputOrg1" name="lvActivityLimitTime.productCode" type="hidden" value="${lvActivityLimitTime.productCode }"/>
				   <input class="required" name="lvActivityLimitTime.productName" type="text" postField="keyword" value="${product.productName }"  lookupGroup="lvActivityLimitTime" readonly="readonly"/>
				   <a class="btnLook" href="lvProductAction!selectSimpleProduct.action?lvProduct.isActivity=1&lvProduct.storeId=${lvActivityLimitTime.storeId }" lookupGroup="lvActivityLimitTime" id="selProduct">查找带回</a>	
				    </dd>
				</dl>
				<dl>
				    <dt>活动金额：</dt>
				    <dd>
				     <input name="lvActivityLimitTime.activityPrice" type="text" size="32" maxlength="5" class="required numberAPoint" value="${lvActivityLimitTime.activityPrice}"/>
				    </dd>
				</dl>
								<dl>
				    <dt>活动开始时间：</dt>
				    <dd>
				        <input type="text" name="lvActivity.startTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvActivity.startTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动结束时间：</dt>
				    <dd>
				     	<input type="text" name="lvActivity.endTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvActivity.endTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动状态：</dt>
				    <dd>
				     	<gv:dictionary type="select" code="ACTIVITY_STATUS" name="lvActivity.status" value="${lvActivity.status }" disabled="disabled"/>
				    </dd>
				</dl>
				<dl>
					<dt>活动描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivity.remark" maxlength="200">${lvActivity.remark }</textarea>
					</dd>				  
				</dl>
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