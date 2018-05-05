<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
   <script type="text/javascript">
   function selectContent(f){
     if(f.value==1){
        $('#contentid').show();
        }else{
        $('#contentid').hide();
        }
   }
   
//根据店铺编号查询对应店铺的模板详情
function changeStore(){
	var storeId=$("#storeId").find("option:selected")
	var storeVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvContentMngAction!toTplDetail.action",
		  data: "lvContent.storeFlag="+storeVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
		        $("#tpl option").remove();
		        $("#tpl").append("<option value=''>==请选择==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var pagePath = listTmp[k].pagePath; 
					var name = listTmp[k].name; 
					$("#tpl").append("<option value="+pagePath+" title="+name+" >"+pagePath+"</option>");
				 }
		     }else{
		        $("#tpl option").remove();
		        $("#tpl").append("<option value=''>==请选择==</option>");

		     }
		  }
	});
}
   </script>
	<div class="pageContent">
		<form method="post" id="postFromId" action="lvContentMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				       
						
						<p>
							<label>所属关系：</label>
							<select name="lvContent.storeFlag" class="required" id="storeId" onchange="changeStore()">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						    </select>
						</p> 
						<p>
							<label>请选择模板：</label>
							<select name="lvContent.templatePath"  class="required"  style="width:230px;" id="tpl">
							<option value="">请选择</option>
							<s:iterator value="detailList" id="d">
							<option value="${d.pagePath}" title="${d.name}">${d.pagePath}</option>
							</s:iterator>
							</select>
						</p>
						<dl class="nowrap" >
							<dt>页面名称：</dt>
							<dd>
							<input name="lvContent.pageName" type="text" size="30" maxlength="32" class="required"/>
							</dd>
						</dl>
						<dl class="nowrap" >
							<dt>页面标题：</dt>
							<dd>
							<input name="lvContent.pageTitle" type="text" size="30" maxlength="32" />
							</dd>
						</dl>
						
						<dl class="nowrap" >
					      <dt>页面关键字：</dt>
					      <dd>
					        <textarea rows="4" cols="80" name="lvContent.pageKeywords"></textarea>
						  </dd>
					    </dl>
						<dl class="nowrap" >
							<dt>页面描述：</dt>
							<dd>
							<textarea rows="4" cols="80" name="lvContent.pageDescription"></textarea>
							</dd>
						</dl>
						<dl class="nowrap" >
							<dt>页面路径：</dt>
							<dd><input name="lvContent.pagePath" type="text" size="30" maxlength="128" class="required"/></dd>
						</dl>
						<dl class="nowrap" >
						    <dt>是否有主体内容：</dt>
							<dd>
							<select name="lvContent.isHasContent"  class="required" onchange="selectContent(this)"><option value="">请选择</option><option value="1">有</option><option value="0">无</option></select>
						    </dd>
						</dl>
						<dl class="nowrap"  id="contentid" style="display:none;">
							<dt>页面内容：</dt>
							<dd>
								<textarea name="lvContent.pageContent" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=content" upImgExt="jpg,jpeg,gif,png"  rows="13" cols="70" class="editor {internalScript:true,inlineScript:true,linkTag:true}"></textarea>
							</dd>
					    </dl>
		 
			    </div>
			   </form>
				<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="$('#postFromId').submit();">保存</button></div></div></li>
						<li>
						 <div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
						</li>
					</ul>
				</div>
			
	</div>
</div>