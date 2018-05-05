<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
//根据产品code查询产品信息(价格)
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
		<form method="post" action="lvSpecialtyStoreAction!save.action?json.navTabId=${json.navTabId}"
		 class="pageForm required-validate" 
		 onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>店铺名称：</label>
							<input name="lvSpecialtyStore.storeName" type="text" size="30" maxlength="150" class="required"/>
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
							</select>
						</p>
						
						<p>
							<label>店铺URL：</label>
							<input name="lvSpecialtyStore.storeUrl" type="text" size="30" maxlength="150" />
						</p>
						<p>
							<label>LOGO图片：</label>
							<input name="img" type="file" size="20" class="required" />
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvSpecialtyStore.orderValue" type="text" size="30" maxlength="10" class="required digits"/>
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