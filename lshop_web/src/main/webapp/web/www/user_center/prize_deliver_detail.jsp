<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<div class="my_prize_details_box">
<ul>
   	<li><span class="wd">收貨人姓名：</span>${deliver.relName}</li>
       <li><span class="wd">手機號碼：</span>${deliver.mobile}</li>
       <li><span class="wd">固定電話：</span>${deliver.tel}</li>
       <li><span class="wd">收貨地址：</span><span class="wd2"><c:if test="${deliver.contryId==100023}">
										${deliver.contryName }&nbsp;${deliver.provinceName}&nbsp;${deliver.cityName }&nbsp;${deliver.adress }
									</c:if>
									<c:if test="${deliver.contryId!=100023}">
										${deliver.adress }&nbsp;${deliver.cityName }&nbsp;${deliver.provinceName}&nbsp;${deliver.contryName }
									</c:if></span></li>
       <li><span class="wd">郵政編碼：</span>${deliver.postCode}</li>
       <li><span class="wd">&nbsp;</span><input type="button" value="確定" class="btn07 c-close-mask"/></li>
   </ul>
</div>