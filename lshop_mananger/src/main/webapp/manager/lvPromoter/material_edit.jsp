<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="lvPromtPayMngAction!editMaterial.action" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
		<input type="hidden" id="myId" value="${id}" name="myId"/>
		<div class="pageFormContent" layoutH="56">
		   <p>
				<label>素材名称：</label>
				<input name="name" type="text" class="required" alt="素材名称" maxlength="32" value="${name}"/>
			</p>
			<p>
				<label>小图片：</label>
				<input name="smallimg" type="file"  class="accept" style="width: 175px;" alt="请选择小图片" id="si"/>
			</p>
	        <p>
				<label>大图片：</label>
				<input name="bigimg" type="file" class="accept" style="width: 175px;" alt="请选择大图片" src="${bigimg}"/>
			</p>
			<p>
				<label>压缩包：</label>
				<input name="compress" type="file" class="accept" style="width: 175px;" alt="请选择压缩包" src="${compress}"/>
			</p>
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

