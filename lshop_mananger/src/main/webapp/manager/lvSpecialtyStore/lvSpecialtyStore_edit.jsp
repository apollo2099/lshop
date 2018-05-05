<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
//根据专卖店查询专卖店分类下面的店铺信息
function changeStore(){
	var parentCode=$("#parentCode").find("option:selected")
	var storeVal = parentCode.val();
    $.ajax({
		  type: "POST",
		  url: "lvSpecialtyStoreTypeAction!toStoreType.action",
		  data: "parentCode="+storeVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
		        $("#tab option").remove();
		        $("#tab").append("<option value=''>==请选择==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var typeName = listTmp[k].typeName; 
					var code = listTmp[k].code; 
					$("#tab").append("<option value="+code+">"+typeName+"</option>");
				 }
		     }else{
		        $("#tab option").remove();
		        $("#tab").append("<option value=''>==请选择==</option>");
		     }
		  }
	});
}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvSpecialtyStoreAction!edit.action?json.navTabId=${json.navTabId}"
		 class="pageForm required-validate" 
		 onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
			<input name="lvSpecialtyStore.id" type="hidden" size="30" value="${lvSpecialtyStore.id}"/>
			<input name="lvSpecialtyStore.storeLogo" type="hidden" size="30" value="${lvSpecialtyStore.storeLogo}"/>
			<input name="lvSpecialtyStore.storeId" type="hidden" size="30" value="${lvSpecialtyStore.storeId}"/>
			<input name="lvSpecialtyStore.code" type="hidden" size="30" value="${lvSpecialtyStore.code}"/>
			<input name="lvSpecialtyStore.modifyUserId" type="hidden" size="30" value="${lvSpecialtyStore.modifyUserId}"/>
				<!-- 生成表单 -->
						<p>
							<label>店铺名称：</label>
							<input name="lvSpecialtyStore.storeName" type="text" size="30" maxlength="150" value="<s:property escapeHtml="true" value="lvSpecialtyStore.storeName"/>" class="required"/>
						</p>
						<p>
							<label>专卖店：</label>
							<select name="lvSpecialtyStoreType.parentCode" id="parentCode" onchange="changeStore()" class="required">
							<option value="">==请选择==</option>
							<c:foreach items="${ptypeList}" var="temp">
							<option value="${temp.code }" <c:if test="${temp.code==lvSpecialtyStoreType.parentCode }">selected="selected"</c:if>>${temp.storeTypeName }</option>
							</c:foreach>
							</select>
						</p>
						<p>
							<label>分类名称：</label>
							<select name="lvSpecialtyStore.storeTypeCode" id="tab" class="required" style="width:200px;">
							<option value="">==请选择==</option>
							<c:foreach items="${typeList}" var="temp">
							<option value="${temp.code }" <c:if test="${temp.code==lvSpecialtyStore.storeTypeCode }">selected="selected"</c:if>>${temp.storeTypeName }</option>
							</c:foreach>
							</select>
						</p>
						
						<p>
							<label>店铺URL：</label>
							<input name="lvSpecialtyStore.storeUrl" type="text" size="30" maxlength="150" value="${lvSpecialtyStore.storeUrl}"/>
						</p>
						<p>
							<label>LOGO图片：</label>
							<input name="img" type="file" size="20" class="" />
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvSpecialtyStore.orderValue" type="text" size="30" maxlength="10" value="${lvSpecialtyStore.orderValue}" class="required digits"/>
						</p>

						<p>
							<label>创建时间：</label>
							<input name="lvSpecialtyStore.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvSpecialtyStore.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改时间：</label>
							<input name="lvSpecialtyStore.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvSpecialtyStore.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvSpecialtyStore.modifyUserName" type="text" size="30" value="${lvSpecialtyStore.modifyUserName}"/>
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