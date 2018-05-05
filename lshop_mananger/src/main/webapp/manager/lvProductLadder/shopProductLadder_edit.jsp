<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">

//校验上下区间值是否合理
function checkInterval(){
    var upInterval=$("#upInterval").val();
	var downInterval=$("#downInterval").val();
	if(parseInt(upInterval)>parseInt(downInterval)){
	   alertMsg.error("请输入正确的值，上区间不能大于下区间的值!");
	   return false;
	}
	return true;
}

//提交表单
function validateCallbackFrom(form, callback){
  var $form = $(form);
	if (!$form.valid()) {
		return false;
	}

	//判断上下区间值是否合理
    if(!checkInterval()){
       return false;
    }
	
	//提交表单请求数据
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
</script>


<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvProductLadderAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, dialogAjaxDone);">
			<input type="hidden" name="lvProductLadder.id" value="${lvProductLadder.id }">
		    <input type="hidden" name="lvProductLadder.storeId" value="${lvProductLadder.storeId }">
		    <input type="hidden" name="lvProductLadder.code" value="${lvProductLadder.code }">
		    <input type="hidden" name="lvProductLadder.oldUpInterval" value="${lvProductLadder.upInterval }">
		    <input type="hidden" name="lvProductLadder.oldDownInterval" value="${lvProductLadder.downInterval }">
		    <input type="hidden" name="lvProductLadder.productCode" value="${lvProductLadder.productCode }">
  		    <div class="pageFormContent" layoutH="56">
					<dl>
				<dt>单价：</dt>
					<dd>
                    <input name="lvProductLadder.price" type="text" size="32" maxlength="7" class="required numberNew" value="${lvProductLadder.price }"/>
					</dd>				  
				</dl>
				<dl>
				<dt>上区间：</dt>
				    <dd>
				     <input id="upInterval" name="lvProductLadder.upInterval" type="text" size="32" maxlength="9" class="required digits" value="${lvProductLadder.upInterval}"/>
				    </dd>
				</dl>
				
				<dl >
				<dt>下区间：</dt>
					<dd>
					<input id="downInterval" name="lvProductLadder.downInterval" type="text" size="32" maxlength="9" class="required digits" value="${lvProductLadder.downInterval }"/>
					</dd>				  
				</dl>
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <input type="text" name="lvProductLadder.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvProductLadder.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                         <input type="text" name="lvProductLadder.modifyUserName"  readonly="true"  value="${lvProductLadder.modifyUserName }" size="32"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<input type="text" name="lvProductLadder.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvProductLadder.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
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
