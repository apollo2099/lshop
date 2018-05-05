<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
		<!--取值 -->
				<dl>
					<dt>规格：</dt>
					<dd>
						<gv:dictionary type="text" code="PRODUCT_PATTERN_KEY" value="${lvPatternCountry.patternCode}"/>
					</dd>				  
				</dl>
				<dl>
					<dt>国家名称：</dt>
					<dd>
						${lvPatternCountry.countryName}
					</dd>				  
				</dl>
				<dl>
					<dt>国家编码：</dt>
					<dd>
						${lvPatternCountry.countryCode}
					</dd>				  
				</dl>
				<dl>
					<dt>创建人：</dt>
					<dd>
						${lvPatternCountry.createdBy}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvPatternCountry.createdDateString}
					</dd>				  
				</dl>
				<%--
				<dl>
					<dt>最后更新人：</dt>
					<dd>
						${lvPatternCountry.lastUpdatedBy}
					</dd>				  
				</dl>
				<dl>
					<dt>最后更新时间：</dt>
					<dd>
						${lvPatternCountry.lastUpdatedDateString}
					</dd>				  
				</dl>
				 --%>
		</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
