<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">
//动态刷新国家数据信息
function changeLanguage(){
	var languageVal =$("#language").find("option:selected").val();
    $.ajax({
			  type: "POST",
			  url: "lvStoreAreaAction!toArea.action",
			  data: "language="+languageVal,
			  dataType:"json",
			  success: function(data){
			     $("#contryid option").remove();
			     $("#contryid").append("<option value=''>==请选择==</option>");
			     if(data!=null){
					 var areaListTmp=data.areaListTmp;
					 for(var num=0;num<areaListTmp.length;num++){ 
						var areaId = areaListTmp[num].areaId; 
						var areaName = areaListTmp[num].areaName; 
						$("#contryid").append("<option value="+areaId+">"+areaName+"</option>");
					 }
			     }
			  }
	});
}
//动态刷新洲数据数据信息
function changeStore(){
     var storeVal =$("#storeId").find("option:selected").val();
     $.ajax({
			  type: "POST",
			  url: "lvStoreAreaAction!toStoreArea.action",
			  data: "lvStoreArea.storeId="+storeVal,
			  dataType:"json",
			  success: function(data){
			     $("#continent option").remove();
			     $("#continent").append("<option value=''>==请选择==</option>");
			     if(data!=null){
			        var listTmp=data.listTmp;  	
			        if(listTmp!=null){
			        	for(var k=0;k<listTmp.length;k++){ 
							var storeAreaName = listTmp[k].storeAreaName; 
							var storeAreaCode = listTmp[k].storeAreaCode; 
							$("#continent").append("<option value="+storeAreaCode+">"+storeAreaName+"</option>");
						 }
			        }
			     }
			  }
	  });
}

//国家名称更新
function changeCountry(){
	$("#contrynameId").val($("#contryid").find("option:selected").text());
	$("#contrynameEn").val($("#contryid").find("option:selected").text());
}
</script>


<div class="page">
	<div class="pageContent">
		<form method="post" action="lvStoreAreaAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvStoreArea.areaName" type="hidden" size="30" maxlength="32" class="required" id="contrynameId"/>
			<input name="lvStoreArea.areaNameEn" type="hidden" size="30" maxlength="32" id="contrynameEn"/>
				<!-- 生成表单 -->
				      <p>
							<label>所属商城：</label>
							<select name="lvStoreArea.storeId" class="required" id="storeId" onchange="changeStore()">
							<option value="">==请选择==</option>
								<c:foreach items="${storeList}" var="shop">
								<option value="${shop.storeFlag }">${shop.name }</option>
								</c:foreach>
								</select>	
						</p>
				       <p>
							<label>所属洲：</label>
							<select name="lvStoreArea.parentCode" id="continent" style="width:150px" >
	                       <option value="">==请选择==</option>
	                       <c:foreach items="${continentList}" var="area">
						   <option value="${area.code }">${area.areaName }</option>
						   </c:foreach>
						   </select>
						</p>
						<p>
						<label>语言：</label>
						<select id="language" class="required" onchange="changeLanguage()">
							<option value="">==请选择==</option>
							<option value="zh">中文</option>
							<option value="en">英文</option>	
						</select>	
						</p>
						<p>
							<label>国家名称：</label>
							<select name="lvStoreArea.areaId"  id="contryid" onchange="changeCountry()" style="width:150px">
	                       <option value="">==请选择==</option>
	                       <c:foreach items="${areaList}" var="area">
						   <option value="${area.id }">${area.namecn }</option>
						   </c:foreach>
						   </select>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvStoreArea.orderValue" type="text" size="10" maxlength="10" class="required digits" value="0"/>
						</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>

