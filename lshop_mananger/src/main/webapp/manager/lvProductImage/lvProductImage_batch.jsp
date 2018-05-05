<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>


<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>

<div class="pageContent"  >
    <div class="pageFormContent">
		<dl>
		<dt>商品名称：</dt>
		<dd>
		<input value="${lvProduct.productName }" size="32" readonly="readonly">
		</dd>				  
		</dl>
	</div>
				
				
	<input id="testFileInput2" type="file" name="img" 
		uploaderOption="{
			swf:'uploadify/scripts/uploadify.swf',
			uploader:'/manager/lvProductImageAction!add.action?lvProductImage.productCode=${lvProductImage.productCode }&lvProductImage.sortId=0&json.navTabId=${json.navTabId }',
			formData:{PHPSESSID:'xxx', ajax:1},
			queueID:'fileQueue',
			buttonImage:'uploadify/img/add.jpg',
			buttonClass:'my-uploadify-button',
			fileTypeDesc:'*.jpg;*.jpeg;*.gif;*.png;',
			fileTypeExts:'*.jpg;*.jpeg;*.gif;*.png;',
			width:102,
			auto:false
		}"
	/>
	<div id="fileQueue" class="fileQueue"></div>
	
	<input type="image" src="uploadify/img/upload.jpg" onclick="$('#testFileInput2').uploadify('upload', '*');"/>
	<input type="image" src="uploadify/img/cancel.jpg" onclick="$('#testFileInput2').uploadify('cancel', '*');"/>
	<span id="result" style="font-size: 13px; color: red"></span>
	<div class="formBar">
	<ul>
	<li><div class="button" id="close"><div class="buttonContent"><button  class="close" onclick="navTabPageBreak();">刷新</button></div></div></li>
	</ul>
	</div>
</div>