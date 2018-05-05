<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<div class="my_prize_details_box">
<ul>
   	<li><span class="wd">Full Name ：</span>${deliver.relName}</li>
       <li><span class="wd">Mobile No .：</span>${deliver.mobile}</li>
       <li><span class="wd">Tel ：</span>${deliver.tel}</li>
       <li><span class="wdd">Address ：</span><p class="pwd"><c:if test="${deliver.contryId==100023}">
										${deliver.contryName }&nbsp;${deliver.provinceName}&nbsp;${deliver.cityName }&nbsp;${deliver.adress }
									</c:if>
									<c:if test="${deliver.contryId!=100023}">
										${deliver.adress } ${deliver.cityName } ${deliver.provinceName} ${deliver.contryName }
									</c:if></p></li>
       <li><span class="wd">Zip ：</span>${deliver.postCode}</li>
       <li><span class="wd">&nbsp;</span><input type="button" value="Confirm" class="btn07 c-close-mask"/></li>
   </ul>
</div>