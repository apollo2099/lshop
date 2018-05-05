<%@ page language="java" pageEncoding="utf-8"%>
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
	var helpContent=$("#helpContent").val()	
	if(helpContent==null||helpContent.length<=0){
		alertMsg.error('请输入帮助信息内容!');
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


//根据店铺编号查询对应店铺的帮助类型
function changeStore(){
	var storeId=$("#storeId").find("option:selected")
	var storeVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvHelpMngAction!toHelpType.action",
		  data: "lvHelpType.storeId="+storeVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
		        $("#helpType option").remove();
		        $("#helpType").append("<option value=''>==请选择==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var helpId = listTmp[k].helpId; 
					var helpName = listTmp[k].helpName; 
					$("#helpType").append("<option value="+helpId+">"+helpName+"</option>");
				 }
		     }else{
		        $("#helpType option").remove();
		        $("#helpType").append("<option value=''>==请选择==</option>");

		     }
		  }
	});
}
</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvHelpMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
	
				       <p>
							<label>所属关系：</label>
							<select name="lvHelp.storeId" class="required" id="storeId" onchange="changeStore()">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						    </select>
						</p>  
						<p>
							<label>帮助类别：</label>
							<select name="lvHelp.helpId" class="required" id="helpType">
							<option value="">选择帮助类别</option>
							<s:iterator value="#request.helpTypeList" id="h"><option value="${h.id}">${h.name}</option></s:iterator>
							</select>
						</p>
						
						<p>
							<label>名称：</label>
							<input name="lvHelp.name" type="text" size="30" maxlength="32" class="required"/>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvHelp.orderValue" type="text" size="30" value="0" maxlength="5" class="required number"/>
						</p>
						<p>
							<label>语言：</label>
							<s:select list="#{'cn':'中文简体','tw':'中文繁体','en':'英文','kn':'韩文'}" name="lvHelp.webLanguage"></s:select>
						</p>
						<!-- 
						<p>
							<label>帮助内容：</label>
							<textarea name="lvHelp.content" id="helpContent" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=help" upImgExt="jpg,jpeg,gif,png"  rows="15" cols="90" class="editor"></textarea>
						</p>
						 -->
						<dl class="nowrap">
					<dt>
						帮助内容：
					</dt>
					<dd>
						<textarea name="lvHelp.content" id="helpContent" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=help" upImgExt="jpg,jpeg,gif,png"  rows="15" cols="80" class="editor"></textarea>
					</dd>
				</dl>
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