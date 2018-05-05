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
					<dt>订单号：</dt>
					<dd>
						${lvOrderMac.orderId}
					</dd>				  
				</dl>
				<dl>
					<dt>Mac：</dt>
					<dd>
						${lvOrderMac.mac}
					</dd>				  
				</dl>
				<dl>
					<dt>Mac兑换来源：</dt>
					<dd>
						${lvOrderMac.sourceUrl}
					</dd>				  
				</dl>
				<dl>
					<dt>IP：</dt>
					<dd>
						${lvOrderMac.ip}
					</dd>				  
				</dl>
				<dl>
					<dt>邮箱：</dt>
					<dd>
						${lvOrderMac.userEmail}
					</dd>				  
				</dl>
				<dl>
					<dt>电话：</dt>
					<dd>
						${lvOrderMac.tel}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvOrderMac.createTime}
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
