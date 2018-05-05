<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
//展现信息不同，隐藏显示图片上传控件
function exhibitChange(){
    var exhibitFlag= $("#exhibitFlag").find("option:selected").val()
    if(exhibitFlag==1){
     $("#imgInfo").show();
    }else{
     $("#imgInfo").hide();
    }
}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvShopPartnerAction!save.action?json.navTabId=${json.navTabId}" 
		class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);"  enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>所属商城：</label>
							<select name="lvShopPartner.storeId" class="required">
								<c:foreach items="${shopList}" var="shop">
								<option value="${shop.storeFlag }">${shop.name }</option>
								</c:foreach>
								</select>
						</p>
						<p>
							<label>商家名称：</label>
							<input name="lvShopPartner.shopName" type="text" size="30" maxlength="150" class="required"/>
						</p>
						<p>
							<label>商家URL：</label>
							<input name="lvShopPartner.shopUrl" type="text" size="30" maxlength="150" />
						</p>
						<p>
							<label>展现形式：</label>
							<s:select list="#{1:'图片',2:'文字'}" name="lvShopPartner.exhibitType" cssClass="required" id="exhibitFlag" onchange="exhibitChange()"></s:select>
						</p>
						<div id="imgInfo">
						<p>
							<label>LOGO图片：</label>
							<input name="img" type="file" size="20" class="" />
						</p>
						</div>
						<p>
							<label>排序值：</label>
							<input name="lvShopPartner.orderValue" type="text" size="30" maxlength="10" class="required digits" value="0"/>
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