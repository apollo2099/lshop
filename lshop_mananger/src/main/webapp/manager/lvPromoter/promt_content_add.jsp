<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<script type="text/javascript">
 function changeType(selectValue)
 {
  if(selectValue==1)
  {
	  $("#type").val(1);
	  $("#type1").show();
	  $("#type2").hide();
	  $("#name1").addClass("required");
	  $("#name2").removeClass("required");
  }
  if(selectValue==2)
  {
	  $("#type").val(2);
	  $("#type2").show();
	  $("#type1").hide();
	  $("#name2").addClass("required");
	  $("#name1").removeClass("required");
  }
 }
</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPromtPayMngAction!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<input type="hidden" name="promtContent.status" value="0"/>
			<input type="hidden" id="type" name="type"/>
			<div class="pageFormContent" layoutH="56">
				<dl>
					<dt>推广类型模版：</dt>
					<dd>
						  <select name="promtContent.model" style="width: 160px" class="required" onchange="changeType(this.options[this.selectedIndex].value);" lang="推广类型模版">
						    <option value="">请选择</option>
						    <option value="1">邮件</option>
						    <option value="2">论坛博客</option>
					      </select>	
				     </dd>
				</dl>
				<div id="type1" style="display: none">
				    
					<dl>
					   <dt>方案名称：</dt>
					   <dd><input type="text" id="name1" name="promtContent.formName" value="" class="required"/></dd>
					</dl>
				    <dl class="nowrap">
			           <dt>HTML内容：</dt>
			           <dd>
				         <textarea class="editor" name="promtContent.htmlContent"  rows="15" cols="50" upImgUrl="/manager/res/upload.action" upImgExt="jpg,jpeg,gif,png" ></textarea>
			           </dd>				  
			        </dl>
			         <dl>
					   <dt>纯文本内容:</dt>
					   <dd><textarea rows="10" cols="40" name="promtContent.textContent"></textarea></dd>
					 </dl>
			        
				</div>
				<div id="type2" style="display: none">
					<dl>
					   <dt>方案名称：</dt>
					   <dd><input type="text" id="name2" name="promtContent2.formName" class="required"/></dd>
					</dl>
					<dl>
					   <dt>纯文本内容:</dt>
					   <dd><textarea rows="10" cols="40" name="promtContent2.textContent"></textarea></dd>
					</dl>
				</div>
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