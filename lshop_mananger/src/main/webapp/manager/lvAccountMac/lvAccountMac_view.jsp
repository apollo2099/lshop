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
					<dt>用户邮箱：</dt>
					<dd>
						${lvAccountMac.userEmail}
					</dd>				  
				</dl>
				<dl>
					<dt>mac：</dt>
					<dd>
						${lvAccountMac.mac}
					</dd>				  
				</dl>
				<dl>
					<dt>联系手机：</dt>
					<dd>
						${lvAccountMac.contactPhone}
					</dd>				  
				</dl>
				<dl>
					<dt>联系固话：</dt>
					<dd>
						${lvAccountMac.contactTel}
					</dd>				  
				</dl>

				<dl>
					<dt>ip：</dt>
					<dd>
						${lvAccountMac.ip}
					</dd>				  
				</dl>
				<dl>
					<dt>兑换来源：</dt>
					<dd>
						${lvAccountMac.sourceUrl}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvAccountMac.createTime}
					</dd>				  
				</dl>
				<dl>
					<dt>状态：</dt>
					<dd>
						<c:if test="${lvAccountMac.status==1 }">已兑换</c:if>
						<c:if test="${lvAccountMac.status==-1 }">已下单</c:if>
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
