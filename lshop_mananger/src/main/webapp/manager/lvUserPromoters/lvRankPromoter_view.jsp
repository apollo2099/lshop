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
					<dt>真实姓名：</dt>
					<dd>
						${info.realName}
					</dd>				  
				</dl>
				
				<dl>
					<dt>邮箱地址：</dt>
					<dd>
						${info.email}
					</dd>				  
				</dl>
				
					<dl>
					<dt>收款帐户类型：</dt>
					<dd>
					<c:if test="${info.accountTypes==1}">PayPal</c:if>
					<c:if test="${info.accountTypes==2}">支付宝</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>收款帐户：</dt>
					<dd>
						${info.accountNumber}
					</dd>				  
				</dl>
				<dl>
					<dt>联系电话：</dt>
					<dd>
						${info.tel}
					</dd>				  
				</dl>
				<dl>
					<dt>地址：</dt>
					<dd>
						${info.adress}
					</dd>				  
				</dl>
				<dl>
					<dt>备注：</dt>
					<dd>
						${info.description}
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
