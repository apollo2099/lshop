<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<div class="my_prize_details_box">
	<ul>
    	<li><span class="wd">Coupon name：</span>${coupon.couponName}</li>
        <li><span class="wd">Coupon code：</span>${coupon.couponCode}</li>
        <li><span class="wd">Value：</span>${coupon.currency} ${coupon.faceValue}</li>
        <li><span class="wd">Validity：</span><fmt:formatDate value="${coupon.startTime}" pattern="yyyy-MM-dd"/>-<fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd"/></li>
        <li><span class="wdd">Rules of use：</span><p class="pwd">
        <c:if test="${coupon.limitedType == 1}">
        1.Available categories：
        </c:if>
        <c:if test="${coupon.limitedType == 2}">
        1.Available items：
        </c:if>
        <c:foreach items="${coupon.items}" var="it">
        ${it.itemName}(${it.itemStore})
        </c:foreach>
        <br />
        2.Order amount should be no less than ${coupon.currency} ${coupon.limitedAmount}</p></li>
        <li><span class="wd">&nbsp;</span><input type="button" value="Confirm" class="btn07 c-close-mask"/></li>
    </ul>
</div>
