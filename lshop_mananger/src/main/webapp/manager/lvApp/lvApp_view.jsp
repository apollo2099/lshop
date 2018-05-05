<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
                 <dl>
					<dt>应用类型：</dt>
					<dd>
				    <c:foreach items="${typeList}" var="t">
					  <c:if test="${t.code==lvApp.typeCode}">${t.typeName }</c:if>
					</c:foreach>
					</dd>				  
				</dl>
			    <dl>
					<dt>应用名称：</dt>
					<dd>
						${lvApp.name }
					</dd>				  
				</dl>
				<dl>
				    <dt>版本：</dt>
				    <dd>
				        ${lvApp.version }
				    </dd>
				</dl>
				<dl>
					<dt>适用机型：</dt>
					<dd>
						${lvApp.applyModel }
					</dd>				  
				</dl>
					<dl>
					<dt>适用版本：</dt>
					<dd>
                        ${lvApp.applyVersion }
					</dd>				  
				</dl>
				<dl  >
					<dt>应用语言：</dt>
					<dd>
						${lvApp.language }
					</dd>				  
				</dl>
				<dl>
					<dt>第三方提供者：</dt>
					<dd>
						${lvApp.thirdParty }
					</dd>				  
				</dl>
				<dl>
					<dt>评分：</dt>
					<dd>
						${lvApp.grade }
					</dd>				  
				</dl>
					<dl class="nowrap">
					<dt>下载地址：</dt>
					<dd>
					   ${lvApp.download }
					</dd>				  
				</dl>
					<dl class="nowrap">
					<dt>应用图片：</dt>
					<dd>
						<s:if test="lvApp.appImage!=''"><img src="${lvApp.appImage}" width="60"  height="60"/></s:if>
					  
					</dd>				  
				</dl>
				<dl >
					<dt>文件大小：</dt>
					<dd>
						  ${lvApp.appSize }
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>应用介绍：</dt>
					<dd>
					<textarea class="editor" rows="10" cols="80" name="lvApp.introduce">${lvApp.introduce }</textarea>
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvApp.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
					<dl>
					<dt>最后修改人：</dt>
					<dd>
						${lvApp.modifyUserName }
					</dd>				  
				</dl>
					<dl>
					<dt>最后修改时间：</dt>
					<dd>
						<s:date name="lvApp.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
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
