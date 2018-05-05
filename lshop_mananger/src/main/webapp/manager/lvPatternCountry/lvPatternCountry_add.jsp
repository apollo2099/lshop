<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<script type="text/javascript">
//根据国家编码查询国家信息
function changeCountry(){
var countryId=$("#countryId").find("option:selected")
var countryVal = countryId.val();
    $.ajax({
		  type: "POST",
		  url: "lvAreaAction!toArea.action",
		  data: "areaId="+countryVal,
		  dataType:"json",
		  success: function(data){
			 $("#countryName").val(data.namecn);
			 $("#countryCode").val(data.code);
		  }
	});
}
</script>


<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPatternCountryAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvPatternCountry.countryName" type="hidden" size="32" id="countryName"/>
			<input name="lvPatternCountry.countryCode" type="hidden" size="32" id="countryCode"/>
				<!-- 生成表单 -->
						<p>
							<label>规格：</label>
							<gv:dictionary type="select" code="PRODUCT_PATTERN_KEY"  name="lvPatternCountry.patternCode" style="required"/>
						</p>
						<p>
							<label>国家名称：</label>
							<select name="lvPatternCountry.countryId" style="width:200px;" id="countryId" onchange="changeCountry()" class="required">
							 <option value="">--请选择--</option>
							 <c:foreach items="${countryList}" var="c">
							 <option value="${c.id }">${c.namecn }</option>
							 </c:foreach>
							</select>
						</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="close">取消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>