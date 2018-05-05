<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="wxObtainNewsTplMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input name="wxObtainNewsTpl.id" type="hidden" size="30" value="${wxObtainNewsTpl.id}"/>
						<p>
							<label>公众号名称：</label>
							<select name="wxObtainNewsTpl.wxhConfigId" id="wxhConfigId" class="required" onchange="changeGzh();" style='width:196px;'>
								<c:foreach items="${wxhConfigs}" var="wxhConfig">
								<c:if test="${wxObtainNewsTpl.wxhConfigId==wxhConfig.id}">
								<option value="${wxhConfig.id }" selected="selected">${wxhConfig.wxhName}</option>
								</c:if>
						   		</c:foreach>
							</select>
						</p>
				
				<p>
							<label>图文素材名称 ：</label>
							<select name="wxObtainNewsTpl.newsId" id="newsId" class="required" style='width:196px;'>
						<option value="">==请选择==</option>
						<c:foreach items="${newsMaterials}" var="newsMaterial">
						<c:if test ="${wxObtainNewsTpl.wxhConfigId == newsMaterial.wxhConfigId }">
						<option  value="${newsMaterial.id }" <c:if test="${newsMaterial.id==wxObtainNewsTpl.newsId}">selected="selected"</c:if>>${newsMaterial.name}</option>
					    </c:if>
					    </c:foreach>
					    </select>
						</p>

						<p>
							<label>参加关键词：</label>
							<input name="wxObtainNewsTpl.pushKeyword" class="required" type="text" size="30" maxlength="32" value="${wxObtainNewsTpl.pushKeyword}"/>
						</p>
						<p>
							<label>查询关键词：</label>
							<input name="wxObtainNewsTpl.queryKeyword" class="required" type="text" size="30" maxlength="32" value="${wxObtainNewsTpl.queryKeyword}"/>
						</p>
						<p>
							<label>活动结束时间：</label>
							<input name="wxObtainNewsTpl.endTime" class="required date" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="wxObtainNewsTpl.endTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
								
						</p>
						
							<input name="wxObtainNewsTpl.createTime" type="hidden" size="30" 
								   value="${wxObtainNewsTpl.createTime }"/>

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
	var gzh = $('#wxhConfigId').val();
	 $("#newsId").empty();
     var options ="<option value='' >==请选择==</option> ";
	<c:foreach items="${newsMaterials}" var="item">
	var id = '${item.wxhConfigId}';
	if(gzh == id){
		options += "<option  value='${item.id }' >${item.name}</option> ";
	}
	 </c:foreach>
	$("#newsId").append(options);
	
}

</script>