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
		<form method="post" action="lvActivityAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="lvActivity.typeKey" type="hidden" value="${lvActivity.typeKey }"/>
			<input id="txtProduct" type="hidden" value="lvProductAction!selectSimpleProduct.action?lvProduct.isActivity=1"/>
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<dl>
					<dt>活动名称：</dt>
					<dd>
						<input name="lvActivity.activityTitle" type="text" size="64" maxlength="120" class="required"/>
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
						  <option value="${s.storeFlag }">${s.name }</option>
						</c:foreach>
					  </select>
				    </dd>
				</dl>
				<dl>
				    <dt>选择商品：</dt>
				    <dd>
				   <input id="inputOrg1" name="lvActivityLimitTime.productCode" value="" type="hidden"/>
				   <input class="required" name="lvActivityLimitTime.productName" type="text" postField="keyword" lookupGroup="lvActivityLimitTime" readonly="readonly"/>
				   <a class="btnLook" href="#" lookupGroup="lvActivityLimitTime" id="selProduct" style="display:none">查找带回</a>	
				    </dd>
				</dl>
				<dl>
				    <dt>活动金额：</dt>
				    <dd>
				     <input name="lvActivityLimitTime.activityPrice" type="text" size="32" maxlength="5" class="required numberAPoint"/>
				    </dd>
				</dl>
								<dl>
				    <dt>活动开始时间：</dt>
				    <dd>
				     <input name="lvActivity.startTime" class="required date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="32"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动结束时间：</dt>
				    <dd>
				     <input name="lvActivity.endTime" class="required date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="32"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动状态：</dt>
				    <dd>
				     	<gv:dictionary type="select" code="ACTIVITY_STATUS" name="lvActivity.status"/>
				    </dd>
				</dl>
				<dl>
					<dt>活动描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivity.remark" maxlength="200"></textarea>
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