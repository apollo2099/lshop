<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="lvPromtPayMngAction!addMaterial.action" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
		<div class="pageFormContent" layoutH="56">
		     <p>
				<label>素材名称：</label>
				<input name="name" type="text" class="required" maxlength="32" alt="素材名称" />
			</p>
			<p>
				<label>小图片：</label>
				<input name="smallimg" type="file" class="accept required" style="width: 175px;" alt="请选择小图片" />
			</p>
	        <p>
				<label>大图片：</label>
				<input name="bigimg" type="file" class="accept required" style="width: 175px;" alt="请选择大图片" />
			</p>
			<p>
				<label>压缩包：</label>
				<input name="compress" type="file" class="accept" style="width: 175px;" alt="请选择压缩包" />
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

