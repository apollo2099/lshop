<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" >
				 <dl>
					<dt>产品名称：</dt>
					<dd>
				         <s:iterator value="#request.productList" id="product">
							    <c:if test="${lvProductProperty.productCode==product.code }">${product.productName }</c:if>
						</s:iterator>
					</dd>				  
				</dl>
				<dl>
				<dt>属性标题：</dt>
				    <dd>
				       ${lvProductProperty.title }
				    </dd>
				</dl>
				
				<dl class="nowrap">
				<dt>属性内容：</dt>
					<dd>
						<textarea class="editor" name="lvProductProperty.content" rows="20" cols="80 ">${lvProductProperty.content }</textarea>
					</dd>				  
				</dl>
				<dl>
				<dt>排序：</dt>
					<dd>
                        ${lvProductProperty.sortId }
					</dd>				  
				</dl>
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <s:date name="lvProductProperty.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                        ${lvProductProperty.modifyUserName }
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<s:date name="lvProductProperty.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
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
