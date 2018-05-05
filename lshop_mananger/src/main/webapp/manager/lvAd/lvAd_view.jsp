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
				<dt>所属关系</dt>
				<dd>
						<c:foreach items="${storeList}" var="store">
						 <c:if test="${lvAd.storeId==store.storeFlag }">${store.name }</c:if>
						</c:foreach>
				</dd>
				</dl>	  
		       <dl >
				<dt>广告标题：</dt>
				    <dd>
				       ${lvAd.adTitle }
				    </dd>
				</dl>
				
				<dl class="nowrap">
				<dt>广告内容：</dt>
					<dd>
						<textarea class="editor" name="lvAd.adContent" rows="20" cols="80 ">${lvAd.adContent }</textarea>
					</dd>				  
				</dl>
				<dl >
				<dt>广告描述：</dt>
				    <dd>
				       ${lvAd.adDescription }
				    </dd>
				</dl>
				<dl>
				<dt>投放时间：</dt>
				    <dd>
				    <s:date name="lvAd.pickleTime" format="yyyy-MM-dd HH:mm"/>
				    </dd>
				</dl>
				 <dl >
				<dt>状态：</dt>
				    <dd>
				       <c:if test="${lvAd.status==1 }">启用</c:if>
				       <c:if test="${lvAd.status==-1 }">停用</c:if>
				    </dd>
				</dl>
				<dl >
				<dt>创建时间：</dt>
					<dd>
                        <s:date name="lvAd.createTime" format="yyyy-MM-dd HH:mm"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                        ${lvAd.modifyUserName }
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<s:date name="lvAd.modifyTime" format="yyyy-MM-dd HH:mm"/>
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
