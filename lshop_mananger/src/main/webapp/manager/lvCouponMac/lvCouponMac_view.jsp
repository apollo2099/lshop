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
						${lvCouponMac.userEmail}
					</dd>				  
				</dl>
				<dl>
					<dt>mac：</dt>
					<dd>
						${lvCouponMac.mac}
					</dd>				  
				</dl>
				<dl>
					<dt>优惠码：</dt>
					<dd>
						${lvCouponMac.couponCode}
					</dd>				  
				</dl>
				<dl>
					<dt>兑换来源：</dt>
					<dd>
						${lvCouponMac.sourceUrl}
					</dd>				  
				</dl>
				<dl>
					<dt>ip地址：</dt>
					<dd>
						${lvCouponMac.ip}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvCouponMac.createTime}
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
