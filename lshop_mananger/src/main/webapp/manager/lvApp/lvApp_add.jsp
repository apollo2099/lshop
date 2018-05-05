<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
//根据店铺编号查询对应店铺的产品信息
function changeStore(){
	var storeId=$("#storeId").find("option:selected")
	var storeVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvAppAction!toAppType.action",
		  data: "lvApp.storeId="+storeVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
		        $("#AppType option").remove();
		        $("#AppType").append("<option value=''>==请选择==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var productTypeName = listTmp[k].productTypeName; 
					var productTypeCode = listTmp[k].productTypeCode; 
					$("#AppType").append("<option value="+productTypeCode+">"+productTypeName+"</option>");
				 }
		     }else{
		        $("#AppType option").remove();
		        $("#AppType").append("<option value=''>==请选择==</option>");

		     }
		  }
	});
}
</script >
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvAppAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
  		<div class="pageFormContent" layoutH="56">
  		        <dl>
					<dt>所属关系：</dt>
					<dd>
						<select name="lvApp.storeId" class="required" id="storeId" onchange="changeStore()">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						    </select>
					</dd>				  
				</dl>
			     <dl>
					<dt>应用类型：</dt>
					<dd>
						<select name="lvApp.typeCode" class="required" id="AppType">
						  <option value="">==请选择类型==</option>
						  <s:iterator value="#request.typeList" id="t">
						     <option value="${t.code }">${t.typeName}</option> 
						  </s:iterator>
						</select>
					
					</dd>				  
				</dl>
			    <dl>
					<dt>应用名称：</dt>
					<dd>
						<input name="lvApp.name" type="text"  size="32" maxlength="32" class="required"/>
					</dd>				  
				</dl>
				<dl>
				    <dt>版本：</dt>
				    <dd>
				         <input name="lvApp.version" type="text" size="32" maxlength="16" class="required" />
				    </dd>
				</dl>
				<dl>
					<dt>适用机型：</dt>
					<dd>
						<input name="lvApp.applyModel" type="text" size="32" maxlength="32"/>
					</dd>				  
				</dl>
					<dl>
					<dt>适用版本：</dt>
					<dd>
                        <input name="lvApp.applyVersion" type="text" size="32" maxlength="16"/>
					</dd>				  
				</dl>
				<dl  >
					<dt>应用语言：</dt>
					<dd>
						<input name="lvApp.language" type="text" size="32" maxlength="32"/>
					</dd>				  
				</dl>
				<dl>
					<dt>第三方提供者：</dt>
					<dd>
						<input name="lvApp.thirdParty" type="text" size="32" maxlength="32"/>
					</dd>				  
				</dl>
				<dl>
					<dt>评分：</dt>
					<dd>
						<input name="lvApp.grade" type="text" size="32"  maxlength="4" class="digits"/>
					</dd>				  
				</dl>
					<dl class="nowrap">
					<dt>下载地址：</dt>
					<dd>
					   <input name="lvApp.download" type="text" size="64" maxlength="128"/>
					</dd>				  
				</dl>
					<dl class="nowrap">
					<dt>应用图片：</dt>
					<dd>
					   <input name="img" type="file" size="32" />
					</dd>				  
				</dl>
				<dl >
					<dt>文件大小：</dt>
					<dd>
						  <input name="lvApp.appSize" type="text" size="32" maxlength="16">
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>应用介绍：</dt>
					<dd>
					 <textarea  class="editor" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=app&storeFlag=${lvApp.storeId }" upImgExt="jpg,jpeg,gif,png"
				               name="lvApp.introduce" rows="15" cols="80"></textarea>
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
