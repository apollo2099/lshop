<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<div class="my_prize_details_box">
	<ul>
    	<li><span class="wd">优惠券名称：</span>${coupon.couponName}</li>
        <li><span class="wd">优惠券码：</span>${coupon.couponCode}</li>
        <li><span class="wd">面值：</span>${coupon.currency} ${coupon.faceValue}</li>
        <li><span class="wd">有效期：</span><fmt:formatDate value="${coupon.startTime}" pattern="yyyy年MM月dd日"/>-<fmt:formatDate value="${coupon.endTime}" pattern="yyyy年MM月dd日"/></li>
        <li><span class="wdd">使用规则：</span><p class="pwd">
        <c:if test="${coupon.limitedType == 1}">
        1.限品类：
        </c:if>
        <c:if test="${coupon.limitedType == 2}">
        1.限商品：
        </c:if>
        <c:foreach items="${coupon.items}" var="it">
        ${it.itemName}(${it.itemStore})
        </c:foreach>
        <br />
        2.商品总金额满${coupon.currency} ${coupon.limitedAmount}</p></li>
        <li><span class="wd">&nbsp;</span><input type="button" value="确定" class="btn07 c-close-mask"/></li>
    </ul>
</div>
