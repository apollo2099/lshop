<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvBlogSubscribeAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<input type="hidden" id="subscribeId" name="lvBlogSubscribe.id" value="${lvBlogSubscribe.id }">
  		    <input type="hidden" id="navTableId" value="${json.navTabId }">
  		    <input type="hidden" name="lvBlogSubscribe.adBanner" value="${lvBlogSubscribe.adBanner }">
  		    <input type="hidden" name="lvBlogSubscribe.storeId" value="${lvBlogSubscribe.storeId }">
			<input type="hidden" name="lvBlogSubscribe.code" value="${lvBlogSubscribe.code}">
			<input type="hidden" name="lvBlogSubscribe.createTime" value="${lvBlogSubscribe.createTime}">
  		<div class="pageFormContent" layoutH="56">
				<dl class="nowrap">
					<dt>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</dt>
					<dd>
					    <input name="lvBlogSubscribe.title" type="text" size="50" class="required " value="${lvBlogSubscribe.title}"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt> 广告图片上传：</dt>
					<dd>
                         <input type="file" name="img" />
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt> 广告URL：</dt>
					<dd>
                        <input name="lvBlogSubscribe.adUrl" type="text" size="50" value="${lvBlogSubscribe.adUrl}"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt> 发送周期：</dt>
					<dd>
						<select name="lvBlogSubscribe.sendCycle" class="required">
						  <option>--</option>
						  <option value="0"  <c:if test="${lvBlogSubscribe.sendCycle==0 }">selected="selected"</c:if>>永不发送</option>
						  <option value="1"  <c:if test="${lvBlogSubscribe.sendCycle==1 }">selected="selected"</c:if>>每天</option>
						  <option value="2"  <c:if test="${lvBlogSubscribe.sendCycle==2 }">selected="selected"</c:if>>每周一</option>
						  <option value="3"  <c:if test="${lvBlogSubscribe.sendCycle==3 }">selected="selected"</c:if>>每周三</option>
						  <option value="4"  <c:if test="${lvBlogSubscribe.sendCycle==4 }">selected="selected"</c:if>>每个月</option>
						</select>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt> 发送时间：</dt>
					<dd>
                         <input name="lvBlogSubscribe.sendTime" maxlength="8" type="text" size="50" class="required date"  format="HH:mm:ss" value="${lvBlogSubscribe.sendTime}" />(注意：时间格式 HH:mm:ss)
					</dd>				  
				</dl>
		</div>
	
		<div class="formBar">
				<ul>
					<li>
					 	<div class="button">
							<div class="buttonContent">
								<button onclick="javasscript:show()" >
									预览
								</button>
							</div>
						</div>
					</li>
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
								<button onclick="javascript:sendEmail()">
									立即发送
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
			
</form>

<script type="text/javascript">
   function show(){
       var subscribeId=window.document.getElementById("subscribeId").value;
       var navTableId=window.document.getElementById("navTableId").value; 
        //window.location.href="lvBlogSubscribeAction!preview.action?lvBlogSubscribe.id="+subscribeId+"";
       window.open("lvBlogSubscribeAction!preview.action?lvBlogSubscribe.id="+subscribeId+"","","height=800,width=1020,status=yes,toolbar=yes,menubar=yes,location=no,scrollbars=yes");
   }
   
   function sendEmail(){
        $.ajax({   
	     url: "lvBlogSubscribeAction!sendEmailNow.action",
		 data:"lvBlogSubscribe.id="+$("#subscribeId").val(),   
		 type: "POST",     
		 success: function(data){   
		 if(data){
		   alert("发送订阅邮件成功！");
		 }else{
		   alert("发送订阅邮件失败！");
		 }  
		 } 
		});
   }
</script>
</div>
</div>
