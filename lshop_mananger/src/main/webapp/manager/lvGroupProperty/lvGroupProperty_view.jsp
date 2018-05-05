<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" >
        <dl class="nowrap">
					<dt>团购产品：</dt>
					<dd>
				  <c:foreach items="${groupList}" var="group">
				    <c:if test="${group.code==lvGroupProperty.groupCode}">${group.title }</c:if>
				  </c:foreach>
					</dd>				  
				</dl>
				<dl >
				<dt>属性标题：</dt>
				    <dd>
				     ${lvGroupProperty.title }
				    </dd>
				</dl>
				
				<dl class="nowrap">
				<dt>属性内容：</dt>
					<dd>
						<textarea class="editor" name="lvGroupProperty.content" rows="20" cols="80 ">${lvGroupProperty.content }</textarea>
					</dd>				  
				</dl>
				<dl>
				<dt>排序值：</dt>
					<dd>
                    ${lvGroupProperty.sortId }
					</dd>				  
				</dl>
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <s:date name="lvGroupProperty.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                        ${lvGroupProperty.modifyUserName }
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<s:date name="lvGroupProperty.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
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
