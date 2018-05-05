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
		<form method="post" action="lvShopPartnerAction!edit.action?json.navTabId=${json.navTabId}" 
		class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="lvShopPartner.modifyUserId"  size="30" value="${lvShopPartner.modifyUserId}"/>
			<input type="hidden" name="lvShopPartner.id"  size="30" value="${lvShopPartner.id}"/>
			<input type="hidden" name="lvShopPartner.storeId"  size="30" value="${lvShopPartner.storeId}"/>
			<input type="hidden" name="lvShopPartner.code"  size="30" value="${lvShopPartner.code}"/>
			<input type="hidden" name="lvShopPartner.shopLogo"  size="30" value="${lvShopPartner.shopLogo}"/>
				<!-- 生成表单 -->
						<p>
							<label>商家名称：</label>
							<input name="lvShopPartner.shopName" type="text" size="30" maxlength="150" class="required" value="<s:property escapeHtml="true" value="lvShopPartner.shopName"/>"/>
						</p>
						<p>
							<label>商家URL：</label>
							<input name="lvShopPartner.shopUrl" type="text" size="30" maxlength="150" value="${lvShopPartner.shopUrl}"/>
						</p>
						<p>
							<label>展现形式：</label>
							<s:select list="#{1:'图片',2:'文字'}" name="lvShopPartner.exhibitType" cssClass="required" id="exhibitFlag" onchange="exhibitChange()"></s:select>
						</p>
						<div id="imgInfo">
						<p>
							<label>LOGO图片：</label>
							<input name="img" type="file" size="20" class=""/>
						</p>
						</div>
						<p>
							<label>排序值：</label>
							<input name="lvShopPartner.orderValue" type="text" size="30" maxlength="10" class="required digits" value="${lvShopPartner.orderValue}"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvShopPartner.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopPartner.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
								 
						</p>
						<p>
							<label>修改时间：</label>
							<input name="lvShopPartner.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopPartner.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvShopPartner.modifyUserName" readonly="readonly" type="text" size="30" value="${lvShopPartner.modifyUserName}"/>
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

<script type="text/javascript">
$(document).ready(function(){　　
 var exhibitFlag= $("#exhibitFlag").find("option:selected").val()
    if(exhibitFlag==1){
     $("#imgInfo").show();
    }else{
     $("#imgInfo").hide();
    }
})
</script>