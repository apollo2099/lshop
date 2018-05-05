<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
		<!--取值 -->
				        				       <dl >
							<dt>城市：</dt>
							<dd>
								${lvStore.city }
							</dd>
					    </dl>
					     <dl >
							<dt>国家：</dt>
							<dd>
								${lvStore.country }
							</dd>
					    </dl>
					    <dl >
							<dt>店铺名称：</dt>
							<dd>
								${lvStore.name }
							</dd>
					    </dl>
					    <dl >
							<dt>服务电话：</dt>
							<dd>
							${lvStore.serviceTel }
							</dd>
					    </dl>
					    <dl >
							<dt>店铺域名：</dt>
							<dd>
								${lvStore.domainName }
							</dd>
					    </dl>
					   <dl style="height: 60px;" class="nowrap">
							<dt>店铺LOGO：</dt>
							<dd>
								<img alt="" width="70" height="60" src="${lvStore.logo}">
							</dd>
					    </dl>
					    <dl class="nowrap">
						<dt>服务承诺：</dt>
						<dd>${lvStore.servicePromise }</dd>
						</dl>
						
					   <dl class="nowrap">
					    <dt>店铺地址：</dt>
					   <dd> ${lvStore.address }</dd>
					   </dl>
					   
					    <dl >
							<dt>是否商务发货：</dt>
							<dd>
								<c:if test="${lvStore.thirdPartyShippingMark==1}">是</c:if>
								<c:if test="${lvStore.thirdPartyShippingMark==0}">否</c:if>
							</dd>
					    </dl>
 						<dl >
							<dt>选择默认模板：</dt>
							<dd>
							<!-- 
								<select name="lvTplModelPublic.id" class="required">
								<option value="">==请选择==</option>
								<s:iterator  value="#request.modelPublicList" id="mode"><option value="${mode.id}" <c:if test="${lvTplModelPublic.id==mode.id }">selected="selected"</c:if>>${mode.modelName}</option></s:iterator>
								</select>
								 --> 
								<s:if test="#request.tplModel!=null">${requestScope.tplModel.modelName}</s:if>
							</dd>
					    </dl>
					    <dl >
							<dt>是否开启优惠券：</dt>
							<dd>
								<c:if test="${lvStore.isPreferences==1}">是</c:if>
								<c:if test="${lvStore.isPreferences==0}">否</c:if>
							</dd>
					    </dl>
					   <dl >
							<dt>店铺状态：</dt>
							<dd>
							    <c:if test="${lvStore.status==1}">启用</c:if>
								<c:if test="${lvStore.status==0}">停用</c:if>
							</dd>
					    </dl>
					    <dl class="nowrap">
							<dt>第三方统计代码：</dt>
							<dd>
								<textarea name="lvStore.statCode"  rows="5" cols="80" >${lvStore.statCode }</textarea>
							</dd>
					    </dl>
					  	<dl >
							<dt>Email：</dt>
							<dd>
								${lvStore.email }
							</dd>
					    </dl>
					     <dl >
							<dt>商家标志：</dt>
						   <dd>${lvStore.storeFlag }</dd>
						</dl>
						 <dl >
							<dt>负责人：</dt>
						   <dd>${lvStore.principal }</dd>
						</dl>
						<dl >
							<dt>联系电话：</dt>
						   <dd>${lvStore.tel }</dd>
						</dl>
						<dl >
							<dt>创建时间：</dt>
						   <dd><s:date name="lvStore.createTime" format="yyyy-MM-dd HH:mm"/>
							</dd>
						</dl>
						<dl >
							<dt>修改时间：</dt>
						   <dd><s:date name="lvStore.modifyTime" format="yyyy-MM-dd HH:mm"/>
							</dd>
						</dl>
						<dl >
							<dt>修改人名称：</dt>
						   <dd>${lvStore.modifyUserName }</dd>
						</dl>
						<dl >
							<dt>是否样版店：</dt>
							<dd>
							    <c:if test="${lvStore.isTemplet==1}">是</c:if>
								<c:if test="${empty lvStore.isTemplet or lvStore.isTemplet==0}">否</c:if>
							</dd>
					    </dl>
					    <dl >
							<dt>商城体系：</dt>
							<dd>
								<c:foreach items="${mallSystemList}" var="m">
								 <c:if test="${lvStore.mallSystem==m.mallSystemFlag}">${m.mallSystemName }</c:if>
								</c:foreach>
							</dd>
					    </dl>
					    <dl >
							<dt>币种：</dt>
							<dd>
							    <c:if test="${lvStore.currency=='USD'}">USD</c:if>
							    <c:if test="${lvStore.currency=='CNY'}">CNY</c:if>
							</dd>
					    </dl>
		 </div>
		 </div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
