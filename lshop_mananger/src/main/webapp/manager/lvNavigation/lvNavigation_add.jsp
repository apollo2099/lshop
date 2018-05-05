<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvNavigationMngAction!save.action?json.navTabId=${json.navTabId}" 
		class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				        <p>
							<label>所属关系：</label>
							<select name="lvNavigation.storeId" class="required" onchange="changeStore(this)">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						    </select>
						</p>  
						<p>
							<label>导航名称：</label>
							<input name="lvNavigation.navName" type="text" size="30" maxlength="32" class="required"/>
						</p>
						<p>
							<label>导航URL链接：</label>
							<input name="lvNavigation.navUrl" type="text" size="30" maxlength="128" class="required"/>
						</p>
						<p>
							<label>导航图片：</label>
							<input name="img" type="file" size="20" class="" maxlength="64"/>
						</p>
						<p>
							<label>着色标识：</label>
							<input name="lvNavigation.navFlag" type="text" size="30" maxlength="128" />
						</p>
						<p>
							<label>打开方式：</label>
							<select name="lvNavigation.openTarget" ><option value="0">本窗口打开</option><option value="1">新窗口打开</option></select>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvNavigation.orderValue" type="text" size="7" maxlength="4" value="0" class="required digits" />
						</p>
						<p>
							<label>父级菜单：</label>
							<select id="c_nav_par" name="lvNavigation.navParentId">
							<option value="0">==请选择==</option>
							</select>
							<label style="color:red">保存后不可修改!</label>
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
<script>
function changeStore(nav){
	var storeId = $(nav).val();
	//选择所属店铺第一级菜单
	var $nav = $('#c_nav_par');
	$nav.empty().append('<option value="0">==请选择==</option>');
	if(storeId){
		$.post('lvNavigationMngAction!getMallPrimNavi.action', {storeId: storeId}, function(data){
			if(data!=null){
				var html = '';
				for(var i=0;i<data.length;i++){
					html += '<option value="'+data[i].id+'">'+data[i].navName+'</option>';
				}
				$nav.append(html);
			}
		}, 'json');
	}
}
</script>