<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<c:foreach items="${page.list }" var="lvCoupon">
	<div class="massinfomaw"
		style="background-color: #fff; margin-top: 20px; padding-bottom: 20px">

		<table width="" border="0" align="center" class="nousertable">
			<tr>
				<td width="31%" height="12" align="right"></td>
				<td height="12" colspan="2"></td>
			</tr>
			<tr>
				<td height="25" align="right" valign="top">优惠券名称：</td>
				<td height="25" colspan="2" align="left" valign="top">${lvCoupon.lvCouponType.name }
		    	<c:if test="${lvCoupon.lvCouponType.status==0}">
		      		<a href="#"><img src="${resDomain}/mbmcn/res/images/wx.gif" title="${lvCoupon.lvCouponType.disableReasons }"/></a>
		      	</c:if>
				</td>
			</tr>
			<tr>
				<td width="31%" height="25" align="right" valign="top">面&nbsp;&nbsp;&nbsp;&nbsp;值：</td>
				<td height="25" colspan="2" valign="top">${lvCoupon.lvCouponType.currency } ${lvCoupon.lvCouponType.amount }</td>
			</tr>
			<tr>
				<td width="31%" height="25" rowspan="2" align="right" valign="top">使用规则：</td>
				<td height="25" colspan="2" valign="top">1.<c:if test="${lvCoupon.lvCouponType.relationType==1}">限品类</c:if><c:if test="${lvCoupon.lvCouponType.relationType==2}">限商品</c:if>：${lvCoupon.limitProducts }</td>
			</tr>
			<tr>
				<td height="25" colspan="2" valign="top">2.商品总金额满${lvCoupon.lvCouponType.currency } ${lvCoupon.lvCouponType.limitAmount }</td>
			</tr>
			<tr>
				<td width="31%" height="25" align="right" valign="top">有效期：</td>
				<td height="25" colspan="2" valign="top"><fmt:formatDate value="${lvCoupon.lvCouponType.startTime }"  type="date" dateStyle="medium" />至 <fmt:formatDate value="${lvCoupon.lvCouponType.endTime }"  type="date" dateStyle="medium" /></td>
			</tr>
			<tr>
				<td width="31%" height="12" align="right"></td>
				<td width="49%" height="12" align="right"></td>
				<td width="25%" align="right"><a href="${storeDomain}/index.html"
						class="bttip">立即使用</a></td>
				</tr>
			</table>
		</div>
</article>
</c:foreach>