<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxKeywordsReplyMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
					    <p>
							<label>公众号名称：</label>
							<select name="wxKeywordsReply.wxhConfigId" id="wxhConfigId" onchange="changeGzh();" class="required" style='width:196px;'>
			                <option value="">==请选择==</option>
			                <c:foreach items="${wxhConfigList}" var="wxhConfig">
			                <option  value="${wxhConfig.id }">${wxhConfig.wxhName}</option>
		                    </c:foreach>
		                    </select>
						</p>
						<p>
							<label>规则名称：</label>
							<input name="wxKeywordsReply.name" type="text" size="30" maxlength="60" class="required"/>
						</p>
						<p>
							<label>关键词：</label>
							<input name="wxKeywordsReply.keywords" type="text" size="30" maxlength="154" class="required"/>
						</p>
						<p>
							<label>素材类型：</label>
							
							<select name="wxKeywordsReply.materialType" id="materialType" onchange="changeType();" class="required" style='width:196px;'>
						<option value="">
						==请选择==
						</option>
						<option value="1">
					  文本
						</option>
						<option value="6">
					  图文
						</option>
						</select>
						</p>
						<p>
							<label>对应素材名称：</label>
							<select name="wxKeywordsReply.materialId" id="materialId" class="required" style='width:196px;'>
						<option value="">==请选择==</option>
					
					    </select>
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

<script>

function changeGzh(){
	changeType();
}
function changeType(){
	var type =$('#materialType').val();
	var gzh = $('#wxhConfigId').val();
	if(type == '1'){
		  $("#materialId").empty();
		var options ="<option value='' >==请选择==</option> ";
		<c:foreach items="${textMaterials}" var="item">
		var id = '${item.wxhConfigId}';
		if(id == gzh){
		options += "<option  value='${item.id }' >${item.name}</option> ";
		}
	    </c:foreach>
		 $("#materialId").append(options);
		 
	}else if(type=='6'){
		$("#materialId").empty();
		var options ="<option value='' >==请选择==</option> ";
		<c:foreach items="${newsMaterials}" var="item">
		var id = '${item.wxhConfigId}';
		if(id == gzh){
		options += "<option  value='${item.id }' >${item.name}</option> ";
		}
	    </c:foreach>
		 $("#materialId").append(options);
		
	}else{
		$("#materialId").empty();
		var options ="<option value='' >==请选择==</option> ";
		 $("#materialId").append(options);
	}
	
}

</script>