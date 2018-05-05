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
					<dt>id：</dt>
					<dd>
						${lvHelpType.id}
					</dd>				  
				</dl>
				<dl>
					<dt>类别名称：</dt>
					<dd>
						${lvHelpType.name}
					</dd>				  
				</dl>
				<dl>
					<dt>语言：</dt>
					<dd>
							<s:if test="lvHelpType.weblanguage=='tw'">中文</s:if>
					<s:if test="lvHelpType.weblanguage=='en'">英文</s:if>
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
