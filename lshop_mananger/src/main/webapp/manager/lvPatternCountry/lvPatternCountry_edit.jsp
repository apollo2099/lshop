<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPatternCountryAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>id：</label>
							<input name="lvPatternCountry.id" type="text" size="30" value="${lvPatternCountry.id}"/>
						</p>
						<p>
							<label>VERSION：</label>
							<input name="lvPatternCountry.version" type="text" size="30" value="${lvPatternCountry.version}"/>
						</p>
						<p>
							<label>规格(1=美规,2=澳规,3=英规,4=欧规,5=韩规)：</label>
							<input name="lvPatternCountry.patternCode" type="text" size="30" value="${lvPatternCountry.patternCode}"/>
						</p>
						<p>
							<label>国家名称：</label>
							<input name="lvPatternCountry.countryName" type="text" size="30" value="${lvPatternCountry.countryName}"/>
						</p>
						<p>
							<label>国家编码：</label>
							<input name="lvPatternCountry.countryCode" type="text" size="30" value="${lvPatternCountry.countryCode}"/>
						</p>
						<p>
							<label>-1=删除,1=正常：</label>
							<input name="lvPatternCountry.status" type="text" size="30" value="${lvPatternCountry.status}"/>
						</p>
						<p>
							<label>创建人：</label>
							<input name="lvPatternCountry.createdBy" type="text" size="30" value="${lvPatternCountry.createdBy}"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvPatternCountry.createdDate" class="textInput date" readonly="readonly" pattern="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value=""/>
								 <s:date name="createdDate" format="yyyy-MM-dd HH:mm"/>
						</p>
						<p>
							<label>最后更新人：</label>
							<input name="lvPatternCountry.lastUpdatedBy" type="text" size="30" value="${lvPatternCountry.lastUpdatedBy}"/>
						</p>
						<p>
							<label>最后更新时间：</label>
							<input name="lvPatternCountry.lastUpdatedDate" class="textInput date" readonly="readonly" pattern="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value=""/>
								 <s:date name="lastUpdatedDate" format="yyyy-MM-dd HH:mm"/>
						</p>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>