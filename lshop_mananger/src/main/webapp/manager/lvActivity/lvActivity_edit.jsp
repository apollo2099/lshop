<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvActivityAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="lvActivity.id" value="${lvActivity.id }">
		    <input type="hidden" name="lvActivity.storeId" value="${lvActivity.storeId }">
		    <input type="hidden" name="lvActivity.code" value="${lvActivity.code }">
            <input type="hidden" name="lvActivity.oldStatus" value="${lvActivity.status }">
            <input type="hidden" name="lvActivity.activityPriceRmb" value="${lvActivity.activityPriceRmb }">
            <input type="hidden" name="lvActivity.activityPriceUsd" value="${lvActivity.activityPriceUsd }">
            
  		<div class="pageFormContent" layoutH="56">
			  <dl>
					<dt>产品名称：</dt>
					<dd>
				     <select name="lvActivity.productCode" class="required"> 
					<option value="">
						请选择类别
					</option>
				         <s:iterator value="#request.productList" id="product">
							<option value="${product.code}"
							<c:if test="${lvActivity.productCode==product.code }">selected="selected"</c:if> 
							>
								${product.productName }
							</option>
						</s:iterator>
				  </select>
					</dd>				  
				</dl>
				<dl >
				<dt>活动类型：</dt>
					<dd>
                        <select id="activityId" onchange="showActivity()" name="lvActivity.activityType" class="required">
                          <option value="">==请选择==</option>
                          <option value="1" <c:if test="${lvActivity.activityType==1 }">selected="selected"</c:if>>限时打折</option>
                          <option value="2" <c:if test="${lvActivity.activityType==2 }">selected="selected"</c:if>>限量打折</option>
                        </select>
					</dd>				  
				</dl>
				
				<script type="text/javascript">
							 function showActivity(){
								var flag=$("#activityId").find("option:selected").val();
								if(flag!=null&&flag.length>0){
									if(flag==1){
									   $("#limitTime").show();
									   $("#limitNum").hide(); 
									   $("#startTime").attr("class","required date");
									   $("#endTime").attr("class","required date");
									   $("#counts").attr("class","digits");
									}else if(flag==2){
									   $("#limitNum").show();
									   $("#limitTime").hide();
									   $("#startTime").attr("class","date");
									   $("#endTime").attr("class","date");
									   $("#counts").attr("class","required digits");
									}
								}else{
								    $("#limitNum").hide();
								    $("#limitTime").hide();
								    $("#startTime").attr("class","date");
									$("#endTime").attr("class","date");
								    $("#counts").attr("class","digits");
								}
								
							 }
							</script>
				<dl>
				<dt>活动标题：</dt>
					<dd>
                       <input name="lvActivity.activityTitle" type="text" size="32" maxlength="128" class="required" value="${lvActivity.activityTitle }"/>
					</dd>				  
				</dl>
                <dl>
				<dt>活动价格：</dt>
					<dd>
                       <input name="lvActivity.activityPrice" type="text" size="32" maxlength="11" class="required numberNew" value="${lvActivity.activityPrice }"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动状态：</dt>
					<dd>
                       <s:select list="#{'':'==请选择==',0:'关闭',1:'启用'}" name="lvActivity.status" cssClass="required"></s:select>
					</dd>				  
				</dl>
				<dl>
				<dt>排序值：</dt>
					<dd>
                       <input name="lvActivity.sortId" type="text" size="32" maxlength="4" class="digits" value="${lvActivity.sortId }"/>
					</dd>				  
				</dl>
				
				<div style="display:none" id="limitNum">
				<dl  class="nowrap">
				<dt>库存数量：</dt>
					<dd>
                       <input name="lvActivity.counts" id="counts" type="text" size="32" value="${lvActivity.counts }"/>
					</dd>				  
				</dl>
				</div>
				
				<div style="display:none" id="limitTime">
				<dl>
				
				<dt>活动开始时间：</dt>
					<dd>
                       <input name="lvActivity.startTime" id="startTime" type="text" size="32" class="date" format="yyyy-MM-dd HH:mm:ss"
                       value="<s:date name="lvActivity.startTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动结束时间：</dt>
					<dd>
                       <input name="lvActivity.endTime" id="endTime" type="text" size="32" class="date" format="yyyy-MM-dd HH:mm:ss" 
                       value="<s:date name="lvActivity.endTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
                       
					</dd>				  
				</dl>
				</div>
				
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <input type="text" name="lvActivity.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvActivity.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                         <input type="text" name="lvActivity.modifyUserName"  readonly="true"  value="${lvActivity.modifyUserName }" size="32"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<input type="text" name="lvActivity.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvActivity.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
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

<script type="text/javascript">
<!--
$(document).ready(function(){
	var type=$("#activityId").val();
	if(type==1){
	  $("#limitTime").show();
	  $("#limitNum").hide(); 
	  $("#startTime").attr("class","required date");
	  $("#endTime").attr("class","required date");
	  $("#counts").attr("class","digits");
	}else if(type==2){
	  $("#limitNum").show();
	  $("#limitTime").hide();
	  $("#startTime").attr("class","date");
	  $("#endTime").attr("class","date");
	  $("#counts").attr("class","required digits");
	}


})
//-->
</script>
