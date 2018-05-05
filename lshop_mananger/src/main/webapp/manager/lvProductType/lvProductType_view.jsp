<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" >
				<dl >
					<dt>类别标示：</dt>
					<dd>
						<c:if test="${lvProductType.typeFlag==1}">产品</c:if>
						<c:if test="${lvProductType.typeFlag==2}">应用</c:if>
					</dd>				  
				</dl>
				<dl >
					<dt>类别名称：</dt>
					<dd>
				       ${lvProductType.typeName}
					</dd>				  
				</dl>
				<dl >
					<dt>排序值：</dt>
					<dd>
				       ${lvProductType.orderId}
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
