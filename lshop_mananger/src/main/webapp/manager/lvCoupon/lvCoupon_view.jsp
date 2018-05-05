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
		
				<dl >
					<dt>优惠卷名称:</dt>
					<dd>
							${lvCoupon.couponName}
					</dd>
				</dl>
				
				<dl >
					<dt>优惠码:</dt>
					<dd>
							${lvCoupon.couponCode}
					</dd>
				</dl>	
					
				<dl >
					<dt>有效期:</dt>
					<dd>
							<s:date name="lvCoupon.validityDate" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>
				</dl>	
				<dl >
					<dt>面值:</dt>
					<dd>
							${lvCoupon.faceValue}
					</dd>
				</dl>	
				<dl >
					<dt>币种:</dt>
					<dd>
							${lvCoupon.currency}
					</dd>
				</dl>

				<dl >
					<dt>是否激活:</dt>
					<dd>
							<c:if test="${lvCoupon.isActivate==1}">激活</c:if>
							 <c:if test="${lvCoupon.isActivate==0}">未激活</c:if>
					</dd>
				</dl>
				
				<dl >
					<dt>优惠卷类型:</dt>
					<dd>
							<c:if test="${lvCoupon.couponType==1}">特殊优惠卷</c:if>
							<c:if test="${lvCoupon.couponType==0}">普通优惠卷</c:if>
					</dd>
				</dl>
				<dl >
					<dt>创建时间:</dt>
					<dd>
							 <s:date name="lvCoupon.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>
				</dl>
				
				<dl >
					<dt>修改时间:</dt>
					<dd>
							  <s:date name="lvCoupon.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>
				</dl>
				<dl >
					<dt>修改人名称:</dt>
					<dd>
							 ${lvCoupon.modifyUserName}
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
