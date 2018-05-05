<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
//动态刷新返回选择区域参数
function changeStore(){
	var storeVal=$("#storeId").find("option:selected").val();
	if(storeVal!=null&&storeVal.length>0){
	   $(".btnLook").attr("href",$("#tempLookHref").val()+"&parentCode="+storeVal);
	}else{
	   alertMsg.error('请先选择商城关系!');
	}
}

//初始化加载
$(document).ready(function(){
	changeStore();
});
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvStoreMngAction!save.action?json.navTabId=${json.navTabId}" enctype="multipart/form-data"  class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvStore.orderValue" type="hidden"  value="0"/>
				<!-- 生成表单 -->
				       <dl >
							<dt>所属商城：</dt>
							<dd>
							    <select name="lvStore.parentCode" class="required" id="storeId" onchange="changeStore()">
								<c:foreach items="${shopList}" var="shop">
								<option value="${shop.code }">${shop.name }</option>
								</c:foreach>
								</select>
						</dd>
						</dl>
				        <dl>
							<dt>城市：</dt>
							<dd>
							    <input name="lvStore.continentCode" type="hidden" size="30" />
							    <input name="lvStore.countryCode" type="hidden" size="30"  />
							    <input name="lvStore.cityCode" type="hidden" size="30"  />
							    <input name="lvStore.country" type="hidden" />
							    <input name="lvStore.countryId" type="hidden" size="30"  class="required"/>
								<input name="lvStore.city" type="text" size="30" maxlength="10" class="required" readonly="readonly"/>
								<a class="btnLook" href="lvStoreAreaAction!selectArea.action?json.navTabId=lvStore_1" lookupGroup="lvStore">查找带回</a>	
								<input type="hidden" id="tempLookHref" value="lvStoreAreaAction!selectArea.action?json.navTabId=lvStore_1" >
							</dd>
					    </dl>
					  
						<dl >
							<dt>店铺名称：</dt>
							<dd>
								<input name="lvStore.name" type="text" size="30" maxlength="20" class="required" />
							</dd>
					    </dl>
					    <dl >
							<dt>服务电话：</dt>
							<dd>
							<input name="lvStore.serviceTel" type="text" size="30" maxlength="50" class="phone"/>
							</dd>
					    </dl>
					    <dl >
							<dt>店铺域名：</dt>
							<dd>
								<input name="lvStore.domainName" type="text" size="30" maxlength="160" class="required" id="domainName"/> 
								
							</dd>
					    </dl>
					     <dl class="nowrap">
							<dt>商家LOGO：</dt>
							<dd>
								<input name="img" type="file"  class="true"/> 
							</dd>
					    </dl>
					    <dl class="nowrap">
						<dt>服务承诺：</dt>
						<dd><input name="lvStore.servicePromise" type="text" size="92" maxlength="200"  /></dd>
						</dl>
						
					   <dl class="nowrap">
					    <dt>店铺地址：</dt>
					   <dd> <input name="lvStore.address" type="text" size="92" maxlength="200" /></dd>
					   </dl>
					    
					    <dl >
							<dt>是否商务发货：</dt>
							<dd>
								<s:select list="#{'':'==请选择==',1:'是',0:'否'}" name="lvStore.thirdPartyShippingMark" cssClass="required"></s:select> 
							</dd>
					    </dl>
 						<dl >
							<dt>选择默认模板：</dt>
							<dd>
								<select name="lvTplModelPublic.id" class="required">
								<option value="">==请选择==</option>
								<s:iterator  value="#request.modelPublicList" id="mode"><option value="${mode.id}">${mode.modelName}</option></s:iterator>
								</select> 
							</dd>
					    </dl>
					    <dl>
							<dt>是否开启优惠券：</dt>
							<dd>
								<s:select list="#{0:'否',1:'是'}" name="lvStore.isPreferences" cssClass="required"></s:select> 
							</dd>
					    </dl>
					    <dl >
							<dt>店铺状态：</dt>
							<dd>
								<s:select list="#{1:'启用',0:'停用'}" name="lvStore.status" cssClass="required"></s:select> 
							</dd>
					    </dl>
					    <!-- 
						<p>
							<label>店铺标志：</label>
						   <input type="text" name="lvStore.storeFlag" class="required lettersonly"  maxlength="8" value="${lvStore.storeFlag}">
						</p>
						
						
						<dl class="nowrap">
						<dt>页面描述：</dt>
						<dd><textarea name="lvStore.description"  rows="3" cols="30" maxlength="320"></textarea></dd>
						</dl>
						<dl class="nowrap">
						<dt>公告：</dt>
						<dd><textarea name="lvStore.affiche"  rows="3" cols="30" maxlength="150"></textarea></dd>
						</dl>
						
						
						<dl class="nowrap">
							<dt>底部版权信息：</dt>
							<dd><textarea name="lvStore.footerInfo"  rows="5" cols="80" maxlength="500"></textarea></dd>
							</dl>
						-->
						<dl class="nowrap">
							<dt>第三方统计代码：</dt>
							<dd>
								<textarea name="lvStore.statCode"  rows="5" cols="80" maxlength="1500"></textarea>
							</dd>
					    </dl>
					    <dl >
							<dt>Email：</dt>
							<dd>
								<input name="lvStore.email" type="text" size="30" maxlength="50" class="email"/>
							</dd>
					    </dl>
						 <dl >
							<dt>负责人：</dt>
						   <dd><input type="text" name="lvStore.principal" size="30" maxlength="20" ></dd>
						</dl>
						<dl >
							<dt>联系电话：</dt>
						   <dd><input name="lvStore.tel" type="text" size="30" maxlength="50" class="phone"/></dd>
						</dl>	
						<dl >
							<dt>是否样版店：</dt>
							<dd>
								<s:select list="#{0:'否',1:'是'}" name="lvStore.isTemplet" cssClass="required"></s:select> 
							</dd>
					    </dl>
					    <dl >
							<dt>商城体系：</dt>
							<dd>
							    <select name="lvStore.mallSystem" class="required">
								<c:foreach items="${mallSystemList}" var="m">
								<option value="${m.mallSystemFlag }">${m.mallSystemName }</option>
								</c:foreach>
								</select>
							</dd>
					    </dl>
					    <dl >
							<dt>币种：</dt>
							<dd>
							    <select name="lvStore.currency" class="required">
							    <option value="USD">USD</option>
							    <option value="CNY">CNY</option>
								</select>
							</dd>
					    </dl>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="close">取消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>