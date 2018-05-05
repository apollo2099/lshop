<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="wxMenuMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
							<input name="wxMenu.id" type="hidden" size="30" value="${wxMenu.id}"/>
								<input name="wxMenu.createTime" type="hidden" size="30" value="${wxMenu.createTime}"/>
						<p>
							<label>公众号名称：</label>
							<select name="wxMenu.wxhConfigId" id="wxhConfigId" onchange="changeGzh();" class="required" style='width:196px;'>
			                <option value="">==请选择==</option>
			                <c:foreach items="${wxhConfigList}" var="wxhConfig">
			                <option  value="${wxhConfig.id }" <c:if test="${wxhConfig.id==wxMenu.wxhConfigId }">selected="selected"</c:if>>${wxhConfig.wxhName}</option>
		                    </c:foreach>
		                    </select>
							
						</p>
						<p>
							<label>父菜单：</label>
							<select name="wxMenu.menuParent" id="menuParent" class="required" style='width:196px;'>
							<option value="0">
							==无==
							</option>
							<c:foreach items="${menus}" var="item">
							<c:if test ="${wxMenu.wxhConfigId == item.wxhConfigId && item.menuParent ==0 }">
							<option value="${item.id }" <c:if test="${item.id==wxMenu.menuParent }">selected="selected"</c:if>>
							${item.name}
							</option>
							</c:if>
							</c:foreach>
							</select>
						</p>
						<p>
							<label>菜单名称：</label>
							<input name="wxMenu.name" type="text" class="required" size="30" maxlength="16" value="${wxMenu.name}"/>
						</p>
						
						<p>
							<label>排序值：</label>
							<input name="wxMenu.orderValue" type="text" class="required digits" size="30" value="${wxMenu.orderValue}" maxlength="8" />
						</p>
						<p>
							<label>菜单类型</label>
						<select name="wxMenu.menuType" id="menuType" onchange="changeMenutype();" class="required">
						<option value="click" <c:if test="${wxMenu.menuType=='click' }">selected="selected"</c:if>>
					发送信息
						</option>
						<option value="view" <c:if test="${wxMenu.menuType=='view' }">selected="selected"</c:if>>
					  跳转到网页
						</option>
						</select>
						</p>
						<p id="menuKeyDiv">
							<label>对外标识：</label>
							<input name="wxMenu.menuKey" id="menuKey" type="text" size="30" maxlength="64" class="required" value="${wxMenu.menuKey}"/>
						</p>
						<p id="menuUrlDiv">
							<label >URL地址：</label>
							<input name="wxMenu.menuUrl" type="text" id="menuUrl" size="30" maxlength="256" class="required" value="${wxMenu.menuUrl}"/>
						</p>
						<p id="materialTypeDiv">
							<label>素材类型：</label>
						<select name="wxMenu.materialType" id="materialType"  onchange="changeType();" class="required" style='width:196px;'>
						<option value="">
						==请选择==
						</option>
						<option value="1" <c:if test="${wxMenu.materialType==1 }">selected="selected"</c:if>>
					  文本
						</option>
						<option value="6" <c:if test="${wxMenu.materialType==6 }">selected="selected"</c:if>>
					  图文
						</option>
						</select>
						</p>
						<p id="materialIdDiv">
							<label>对应素材名称：</label>
							<select name="wxMenu.materialId" id="materialId" class="required" style='width:196px;'>
						<option value="">==请选择==</option>
						<c:if test="${ wxMenu.materialType==1 }">
						<c:foreach items="${textMaterials}" var="text">
						<c:if test="${text.wxhConfigId == wxMenu.wxhConfigId }">
						<option  value="${text.id }" <c:if test="${text.id==wxMenu.materialId  }">selected="selected"</c:if>>${text.name}</option>
					    </c:if>
					    </c:foreach>
					    </c:if>
					    <c:if test="${ wxMenu.materialType==6 }">
					    <c:foreach items="${newsMaterials}" var="news">
					    <c:if test="${news.wxhConfigId == wxMenu.wxhConfigId }">
						<option  value="${news.id }" <c:if test="${news.id==wxMenu.materialId}">selected="selected"</c:if>>${news.name}</option>
					   </c:if>
					    </c:foreach>
					    </c:if>
					    </select>
						</p>
						
						<p>
						
						<font color="red">&nbsp;&nbsp;注释：最多允许3个父菜单，父菜单最多有5个子菜单</font>
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
var menuType='${wxMenu.menuType}';
var menuKey ='${wxMenu.menuKey}';
var menuUrl = '${wxMenu.menuUrl}';
var materialType ='${wxMenu.materialType}';
var materialId ='${wxMenu.materialId}';
$(function(){
	beReady();
	});

	
function beReady(){
	var type = $('#menuType').val();
	if(type == 'click' ){
		//是有子菜单的
	  	$('#menuKeyDiv').show();
	  	$('#menuUrlDiv').hide();
	  	;
	  	$('#materialTypeDiv').show();
	  	$('#materialIdDiv').show();
		$('#menuKey').addClass("required");
	  	$('#menuUrl').removeClass("required");
	  	$('#materialType').addClass("required");
	  	$('#materialId').addClass("required");
	  	
	}else if(type == 'view'){
		$('#menuKeyDiv').show();
	  	$('#menuUrlDiv').show();
	  	$('#menuKey').addClass("required");
	  	$('#menuUrl').addClass("required");
	  	$('#materialTypeDiv').hide();
	  	$('#materialIdDiv').hide();
		$('#materialType').removeClass("required");
	  	$('#materialId').removeClass("required");
	}else {
		$('#menuKeyDiv').show();
	  	$('#menuUrlDiv').show();
	  	$('#materialTypeDiv').show();
	  	$('#materialIdDiv').show();
	  	$('#menuKey').addClass("required");
	  	$('#menuUrl').addClass("required");
	  	$('#materialType').addClass("required");
	  	$('#materialId').addClass("required");
	}
	
}
function changeMenutype(){
	var type = $('#menuType').val();
	if(type == 'click' ){
		//是有子菜单的
	  	$('#menuKeyDiv').show();
	  	$('#menuUrlDiv').hide();
	  	
	  	$('#materialTypeDiv').show();
	  	$('#materialIdDiv').show();
	  	
		$('#menuKey').addClass("required");
	  	$('#menuUrl').removeClass("required");
	  	$('#materialType').addClass("required");
	  	$('#materialId').addClass("required");
	  	
		if(type == menuType){
			$('#menuUrl').val(menuUrl);
			$('#materialType').val(materialType);
			$('#materialId').val(materialId);
			$('#menuKey').val(menuKey);
		}else{
			$('#menuUrl').val('');
			$('#materialType').val('');
			$('#materialId').val('');
		}
	  	
	}else if(type == 'view'){
		$('#menuKeyDiv').show();
		
	  	$('#menuUrlDiv').show();
	  	$('#menuKey').addClass("required");
	  	$('#menuUrl').addClass("required");
	  	$('#materialTypeDiv').hide();
	  	$('#materialIdDiv').hide();
		$('#materialType').removeClass("required");
	  	$('#materialId').removeClass("required");
	  
	  	if(type == menuType){
			$('#menuUrl').val(menuUrl);
			$('#materialType').val(materialType);
			$('#materialId').val(materialId);
			$('#menuKey').val(menuKey);
		}else{
			
		  	$('#materialType').val('');
		  	$('#materialId').val('');
		}
	}else {
		$('#menuKeyDiv').show();
	  	$('#menuUrlDiv').show();
	  	$('#materialTypeDiv').show();
	  	$('#materialIdDiv').show();
	  	$('#menuKey').addClass("required");
	  	$('#menuUrl').addClass("required");
	  	$('#materialType').addClass("required");
	  	$('#materialId').addClass("required");
	}
	
}


function changeGzh(){
	var configId = $('#wxhConfigId').val();
	$("#menuParent").empty();
	var options ="<option value='0' >==无==</option> ";
	<c:foreach items="${menus}" var="item">
	var wxhId = '${item.wxhConfigId}';
	var menuParent = '${item.menuParent}';
	if(wxhId == configId && menuParent == '0'){
	options += "<option  value='${item.id }' >${item.name}</option> ";
	}
    </c:foreach>
	 $("#menuParent").append(options);
	changeType();
}

function changeType(){
	var type =$('#materialType').val();
	var configId = $('#wxhConfigId').val();
	if(type == '1'){
		  $("#materialId").empty();
		var options ="<option value='' >==请选择==</option> ";
		<c:foreach items="${textMaterials}" var="item">
		var wxhId = '${item.wxhConfigId}';
		if(wxhId == configId){
		options += "<option  value='${item.id }' >${item.name}</option> ";
		}
	    </c:foreach>
		 $("#materialId").append(options);
		 
	}else if(type=='6'){
		$("#materialId").empty();
		var options ="<option value='' >==请选择==</option> ";
		<c:foreach items="${newsMaterials}" var="item">
		var wxhId = '${item.wxhConfigId}';
		if(wxhId == configId){
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