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
						${lvUserPromoters.realName}
					</dd>				  
				</dl>
				
				<dl>
					<dt>邮箱地址：</dt>
					<dd>
						${lvUserPromoters.email}
					</dd>				  
				</dl>
				
					<dl>
					<dt>收款帐户类型：</dt>
					<dd>
					<c:if test="${lvUserPromoters.accountTypes==1}">PayPal</c:if>
					<c:if test="${lvUserPromoters.accountTypes==2}">支付宝</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>收款帐户：</dt>
					<dd>
						${lvUserPromoters.accountNumber}
					</dd>				  
				</dl>
				<dl>
					<dt>联系电话：</dt>
					<dd>
						${lvUserPromoters.tel}
					</dd>				  
				</dl>
				<dl>
					<dt>国家：</dt>
					<dd>
						${lvUserPromoters.contryName}
					</dd>				  
				</dl>
				
				<dl>
					<dt>省/洲：</dt>
					<dd>
						${lvUserPromoters.provincename}
					</dd>				  
				</dl>
				
				<dl>
					<dt>城市：</dt>
					<dd>
						${lvUserPromoters.cityName}
					</dd>				  
				</dl>
				<dl>
					<dt>地址：</dt>
					<dd>
						${lvUserPromoters.adress}
					</dd>				  
				</dl>
				<dl>
					<dt>描述：</dt>
					<dd>
						${lvUserPromoters.description}
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
