<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
function delDetails(flag){
	$("#"+flag).remove();
}
</script>


<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPubPackageAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->

						<p>
							<label>套餐名称：</label>
							<input name="lvPubPackage.packageName" type="text" size="30" maxlength="64" class="required"/>
						</p>
						
						
		<div> 
		        <dl class="nowrap">
					<dt>商品信息：</dt>
					<dd>
					<a href="lvPubProductAction!selectMultipleProduct.action?json.navTabId=multiplePubProduct"  lookupGroup="fLookup" style="color:blue;" target="dialog" width="850" height="600" title="添加产品" mask="true"><input type="button" onclick="javascript:void(0)" value="添加商品" id="but"></a>
					</dd>				  
				</dl> 
		<table  width="100%" border="1" class="table">
		<thead>
			<tr>
				<th width="5%" >ID</th>
				<th width="25%" >商品名称</th>
				<th width="10%" >商品型号</th>
				<th width="30%" >商务对接code</th>
				<th width='20%' >商品数量</th>
				<th width='10%' >操作</th>
		   </tr>
		</thead>
		<tbody id="tab">
		</tbody>
		</table>
		</div>
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