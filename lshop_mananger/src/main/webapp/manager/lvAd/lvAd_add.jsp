<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.lshop.common.util.Constants" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script>
function validateCallbackFrom(form, callback){
  var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	var contentId=$("#contentId").val()	
	if(contentId==null||contentId.length<=0){
		alertMsg.error('请输入广告内容!');
		return false;
	}
	$.ajax({
		type: form.method || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(json){
		   callback(json);
		},
		error: DWZ.ajaxError
	});
	return false;
}

//判断选择是商家还是商城，对应的显示广告列表
function changeStore(){
    var storeId=$("#storeId").find("option:selected")
	var storeVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvAdAction!toStore.action",
		  data: "lvAd.storeId="+storeVal,
		  dataType:"json",
		  success: function(data){
		     $("#titleId").val("");
		     if(data!=null){
                if(data.parentCode=='0'){
                    $("#ad1").hide();
	                $("#ad2").hide();
	                $("#ad3").show();
	                $("#keyid1").attr("disabled",true);
	                $("#keyid2").attr("disabled",true);
	                $("#keyid3").attr("disabled",false);
                }else{
                	$("#ad1").hide();
	                $("#ad2").show();
	                $("#ad3").hide();
	                $("#keyid1").attr("disabled",true);
	                $("#keyid2").attr("disabled",false);
	                $("#keyid3").attr("disabled",true);
                }
		     }else{
                $("#ad1").show();
	            $("#ad2").hide();
	            $("#ad3").hide();
	            $("#keyid1").attr("disabled",false);
	            $("#keyid2").attr("disabled",true);
	            $("#keyid3").attr("disabled",true);
		     }
		  }
	});
}
</script>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvAdAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, dialogAjaxDone);">
  		    <div class="pageFormContent" layoutH="56">
  		     <dl >
				<dt>所属关系</dt>
				<dd>
				<select name="lvAd.storeId" class="required" id="storeId" onchange="changeStore()">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option value="${store.storeFlag }">${store.name }</option>
						</c:foreach>
						</select>
				</dd>
				</dl>
  		       <dl >
				<dt>广告标题：</dt>
				    <dd>
				    <div id="ad1">
					    <select name="lvAd.adKey" id="keyid1" onchange="titltChange1()" class="required" >
					          <option value="">==请选择==</option>
					    </select>
				    </div >
				    <div id="ad2" style="display:none">
				        <select name="lvAd.adKey" id="keyid2" onchange="titltChange2()" class="required">
				          <option value="">==请选择==</option>
				         <s:iterator value="@com.lshop.common.util.Constants@AD_LOCATIONS" status="stat" id="item">
				             <option value="${item.key}">${item.value }</option>    
				         </s:iterator>
				        </select>
				    </div>
				    <div id="ad3" style="display:none">
				        <select name="lvAd.adKey" id="keyid3" onchange="titltChange3()" class="required" >
				          <option value="">==请选择==</option>
				         <s:iterator value="@com.lshop.common.util.Constants@AD_TVPAD" status="stat" id="item">
				             <option value="${item.key}">${item.value }</option>    
				         </s:iterator>
				        </select>
				    </div>
				    </dd>
				</dl>
				<script type="text/javascript">
					function titltChange1(){
					  $("#titleId").val($("#keyid1").find("option:selected").text());
					}
					function titltChange2(){
					  $("#titleId").val($("#keyid2").find("option:selected").text());
					}
					function titltChange3(){
					  $("#titleId").val($("#keyid3").find("option:selected").text());
					}
				</script>
				<input type="hidden" id="titleId"  name="lvAd.adTitle" maxlength="128" value="${lvAd.adTitle }"/>
				<dl >
				<dt>广告描述：</dt>
				    <dd>
				        <input name="lvAd.adDescription" type="text" size="32" maxlength="50"  />
				    </dd>
				</dl>
				<dl class="nowrap">
				<dt>广告内容：</dt>
					<dd>
						<textarea class="editor {internalScript:true,inlineScript:true,linkTag:true}" id="contentId" name="lvAd.adContent" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=ad" upImgExt="jpg,jpeg,gif,png" rows="20" cols="80 ">${lvAd.adContent }</textarea>
					</dd>				  
				</dl>
				<dl >
				<dt>投放时间：</dt>
				    <dd>
				        <input name="lvAd.pickleTime" type="text" size="32" maxlength="128"  class="textInput date" format="yyyy-MM-dd HH:mm:ss"/>
				    </dd>
				</dl>
				 <dl >
				<dt>状态：</dt>
				    <dd>
				    <select name="lvAd.status" class="required">
				          <option value="">==请选择==</option>
				          <option value="1" <c:if test="${lvAd.status==1 }">selected="selected"</c:if>>启用</option>
				          <option value="-1" <c:if test="${lvAd.status==-1 }">selected="selected"</c:if>>停用</option>
				    </select>
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
