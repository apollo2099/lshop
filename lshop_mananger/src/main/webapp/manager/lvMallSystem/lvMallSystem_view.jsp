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
				<dl>
					<dt>商城体系名称：</dt>
					<dd>
						${lvMallSystem.mallSystemName}
					</dd>				  
				</dl>
				<dl>
					<dt>商城体系标示：</dt>
					<dd>
						${lvMallSystem.mallSystemFlag}
					</dd>				  
				</dl>
				<dl>
					<dt>商城体系域名后缀：</dt>
					<dd>
						${lvMallSystem.domainPostfix}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvMallSystem.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${lvMallSystem.modifyTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${lvMallSystem.modifyUserName}
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
