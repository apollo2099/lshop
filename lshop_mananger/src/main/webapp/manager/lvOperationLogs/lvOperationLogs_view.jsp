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
					<dt>ID：</dt>
					<dd>
						${lvOperationLogs.id }
					</dd>				  
				</dl>
				<dl >
					<dt>操作人：</dt>
					<dd>
				       ${lvOperationLogs.operator}
					</dd>				  
				</dl>
				<dl >
					<dt>操作模块：</dt>
					<dd>
				       ${lvOperationLogs.operationModule}
					</dd>				  
				</dl>
				<dl >
					<dt>操作关键字：</dt>
					<dd>
				       ${lvOperationLogs.operationKey}
					</dd>				  
				</dl>
				<dl >
					<dt>操作详情：</dt>
					<dd>
				       ${lvOperationLogs.operationDetails }
					</dd>				  
				</dl>
				<dl >
					<dt>操作时间：</dt>
					<dd>
				      <s:date name="lvOperationLogs.createtime" format="yyyy-MM-dd HH:mm:ss"/>
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
