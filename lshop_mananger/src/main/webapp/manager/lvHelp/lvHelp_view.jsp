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
						${lvHelp.id}
					</dd>				  
				</dl>
				<dl>
					<dt>帮助类别：</dt>
					<dd>
						${lvHelpType.name}
					</dd>				  
				</dl>
				<dl>
					<dt>名称：</dt>
					<dd>
						${lvHelp.name}
					</dd>				  
				</dl>
				<dl>
					<dt>排序值：</dt>
					<dd>
						${lvHelp.orderValue}
					</dd>				  
				</dl>
				<dl>
					<dt>帮助内容：</dt>
					<dd>
						${lvHelp.content}
					</dd>				  
				</dl>
				<dl>
					<dt>语言：</dt>
					<dd>
					<c:if test="${lvHelp.webLanguage=='cn'}">中文简体</c:if>
					<c:if test="${lvHelp.webLanguage=='tw'}">中文繁体</c:if>
					<c:if test="${lvHelp.webLanguage=='en'}">英文</c:if>
					<c:if test="${lvHelp.webLanguage=='kn'}">韩文</c:if>
					<c:if test="${lvHelp.webLanguage=='ja'}">日文</c:if>
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
