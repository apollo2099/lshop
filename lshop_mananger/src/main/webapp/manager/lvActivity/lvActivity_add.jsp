<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>


<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvActivityAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" >
  		    <div class="pageFormContent" layoutH="56">
			     <dl>
					<dt>产品名称：</dt>
					<dd>
				     <select name="lvActivity.productCode" class="required"> 
					<option value="">
						请选择类别
					</option>
				         <s:iterator value="#request.productList" id="product">
							<option value="${product.code}">
								${product.productName }
							</option>
						</s:iterator>
				  </select>
					</dd>				  
				</dl>
				<dl >
				<dt>活动类型：</dt>
					<dd>
                        <select id="activityId" onchange="showActivity()" name="lvActivity.activityType" class="required">
                          <option value="">==请选择==</option>
                          <option value="1">限时打折</option>
                          <option value="2">限量打折</option>
                        </select>
					</dd>				  
				</dl>
				
				<script type="text/javascript">
							 function showActivity(){
								var flag=$("#activityId").find("option:selected").val();
								if(flag!=null&&flag.length>0){
									if(flag==1){//限时
									   $("#limitTime").show();
									   $("#limitNum").hide(); 
									   $("#startTime").attr("class","required date");
									   $("#endTime").attr("class","required date");
									   $("#counts").attr("class","digits");
									}else if(flag==2){//限量
									   $("#limitNum").show();
									   $("#limitTime").hide();
									   $("#startTime").attr("class","date");
									   $("#endTime").attr("class","date");
									   $("#counts").attr("class","required digits");
									}
								}else{
								    $("#limitNum").hide();
								    $("#limitTime").hide();
								}
								
							 }
							</script>
				<dl>
				<dt>活动标题：</dt>
					<dd>
                       <input name="lvActivity.activityTitle" type="text" size="32" maxlength="128" class="required"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动价格：</dt>
					<dd>
                       <input name="lvActivity.activityPrice" type="text" size="32" maxlength="11" class="required numberNew"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动状态：</dt>
					<dd>
                       <s:select list="#{'':'==请选择==',0:'关闭',1:'启用'}" name="lvActivity.status" cssClass="required"></s:select>
					</dd>				  
				</dl>
				<dl>
				<dt>排序值：</dt>
					<dd>
                       <input name="lvActivity.sortId" type="text" size="32" maxlength="4" class="digits"/>
					</dd>				  
				</dl>
				
				<div style="display:none" id="limitNum">
				<dl>
				<dt>库存数量：</dt>
					<dd>
                       <input name="lvActivity.counts" id="counts" type="text" size="32" class="digits"/>
					</dd>				  
				</dl>
				</div>
				
				<div style="display:none" id="limitTime">
				<dl>
				
				<dt>活动开始时间：</dt>
					<dd>
                       <input name="lvActivity.startTime" id="startTime" type="text" size="32" class="date" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动结束时间：</dt>
					<dd>
                       <input name="lvActivity.endTime" id="endTime" type="text" size="32"class="date" format="yyyy-MM-dd HH:mm:ss" />
					</dd>				  
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
