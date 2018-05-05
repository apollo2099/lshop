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
					<dt>赠品中文名称：</dt>
					<dd>
						${lvPubGift.giftName}
					</dd>				  
				</dl>
				<dl>
					<dt>赠品英文名称：</dt>
					<dd>
						${lvPubGift.giftNameEn}
					</dd>				  
				</dl>
				<dl>
					<dt>SAS对接code：</dt>
					<dd>
						${lvPubGift.pcode}
					</dd>				  
				</dl>
				<dl>
					<dt>状态：</dt>
					<dd>
						<c:if test="${lvPubGift.status==1 }">启用</c:if>
						<c:if test="${lvPubGift.status==0 }">停用</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>code：</dt>
					<dd>
						${lvPubGift.code}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvPubGift.createTime}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${lvPubGift.modifyTime}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${lvPubGift.modifyUserName}
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
