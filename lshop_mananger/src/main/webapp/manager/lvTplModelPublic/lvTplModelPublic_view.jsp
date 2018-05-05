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
					<dt>ID：</dt>
					<dd>
						${lvTplModelPublic.id}
					</dd>				  
				</dl>
				<dl>
					<dt>名称：</dt>
					<dd>
						${lvTplModelPublic.modelName}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvTplModelPublic.createTime" format="yyyy-MM-dd HH:mm"/>
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
					<s:date name="lvTplModelPublic.modifyTime" format="yyyy-MM-dd HH:mm"/>
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
