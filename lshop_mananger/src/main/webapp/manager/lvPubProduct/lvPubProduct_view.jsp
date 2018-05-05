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
					<dt>商品名称：</dt>
					<dd>
						${lvPubProduct.productName}
					</dd>				  
				</dl>
				<dl>
					<dt>商品型号：</dt>
					<dd>
						${lvPubProduct.productModel}
					</dd>				  
				</dl>
				<dl>
					<dt>SAS对接code：</dt>
					<dd>
						${lvPubProduct.pcode}
					</dd>				  
				</dl>
				<dl>
					<dt>商品code：</dt>
					<dd>
						${lvPubProduct.code}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvPubProduct.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						<s:date name="lvPubProduct.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${lvPubProduct.modifyUserName}
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
