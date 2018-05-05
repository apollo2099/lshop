<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<style>

element.style {
    background: none repeat scroll 0 0 #330033;
}
a {
    color: #000000;
    text-decoration: none;
}

.tagColor{
    border: 1px solid #999999;
    display: inline-block;
    font-size: 0;
    height: 15px;
    margin: 1px;
    width: 60px;
}
</style>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvShopTagsAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>所属商城：</label>
							<select name="lvShopTags.storeId" class="required">
								<c:foreach items="${shopList}" var="shop">
								<option value="${shop.storeFlag }">${shop.name }</option>
								</c:foreach>
							 </select>
						</p>
						<p>
							<label>标签名称：</label>
							<input name="lvShopTags.tagName" type="text" size="30" maxlength="20" class="required"/>
						</p>
						<p>
							<label>标签连接：</label>
							<input name="lvShopTags.tagUrl" type="text" size="30" maxlength="150" class="required"/>
						</p>
						<p >
							<label>标签颜色：</label>
							<a id="tagColor" class="tagColor" aria-posinset="1" aria-setsize="70" role="option" style="background:#FFFFFF" title="#FFFFFF" xhev="#FFFFFF" href="javascript:void('#FFFFFF')"></a>
							<input type="hidden" id="cp3text" name="lvShopTags.tagFontColor" value="${lvShopTags.tagFontColor }" class="required"/><img src="themes/default/images/colorpicker.png" id="cp3" style="cursor:pointer"/>
						</p>
						
						<script type="text/javascript">
						    $(function(){
						        $("#cp3").colorpicker({
						            fillcolor:true,
						            success:function(o,color){
						                $("#cp3text").css("color",color);
						                $("#cp3text").val(color)
						                $("#tagColor").css("background",color);
						            }
						        });
						    });
						</script>

						<p>
							<label>标签样式：</label>
							<select name="lvShopTags.tagFontStyle">
							<option value="">字体样式</option>
							<option value="<B>tagFontStyle</B>">加粗</option>
							<option value="<I>tagFontStyle</I>">斜体</option>
							<option value="<U>tagFontStyle</U>">下划线</option>
							<option value="<S>tagFontStyle</S>">删除线</option>
							</select>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvShopTags.orderValue" type="text" size="30" maxlength="10" value="0" class="required digits"/>
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