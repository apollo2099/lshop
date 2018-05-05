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
					<dt>id：</dt>
					<dd>
						${lvActivityLogs.id}
					</dd>				  
				</dl>
				<dl>
					<dt>活动code：</dt>
					<dd>
						${lvActivityLogs.activityCode}
					</dd>				  
				</dl>
				<dl>
					<dt>活动参与订单号：</dt>
					<dd>
						${lvActivityLogs.orderId}
					</dd>				  
				</dl>
				<dl>
					<dt>活动物品信息：</dt>
					<dd>
						${lvActivityLogs.productInfo}
					</dd>				  
				</dl>
				<dl>
					<dt>活动参与人：</dt>
					<dd>
						${lvActivityLogs.actors}
					</dd>				  
				</dl>
				<dl>
					<dt>活动参与人ip：</dt>
					<dd>
						${lvActivityLogs.actorsIp}
					</dd>				  
				</dl>
				<dl>
					<dt>活动参与时间：</dt>
					<dd>
						${lvActivityLogs.actorsTimeString}
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
