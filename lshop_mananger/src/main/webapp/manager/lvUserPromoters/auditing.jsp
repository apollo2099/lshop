<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<script type="text/javascript">
  function changeType(id)
  {
    if(id==1)
    {
     $("#p1").show();
     $("#specialAmount").attr("class","required numberNew")
    }
    else
    {
     $("#p1").hide();
     $("#specialAmount").attr("class","")
    }
  }
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvUserPromotersMngAction!isChecked.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="vo.couponType" value="1">
			<input type="hidden" name="check" value="1"/><!-- 审核通过标志 -->
			<input type="hidden" name="lvUserPromoters.id" value="${lvUserPromoters.id}"/>
			<input type="hidden" name="lvUserPromoters.email" value="${lvUserPromoters.email}"/>
			<input type="hidden" name="lvUserPromoters.realName" value="${lvUserPromoters.realName}"/>
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<dl>
				<dt>优惠劵名称：</dt>
				<dd>
					<input type="text" name="vo.couponName" size="30" class="required" alt="请输入优惠劵名称" maxlength="30"/>
				</dd>
			</dl>
			<dl>
				<dt>优惠劵使用渠道：</dt>
				<dd>
				<select  name="type" class="required" style="width:185px;" >
				        <option value="">请选择使用渠道</option>
						<option value="9">经销商专属</option>
						<option value="8">媒体合作专属</option>
						<option value="1">个人网络经销商专属</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>优惠劵面值：</dt>
				<dd>
					<input type="text" name="vo.faceValue" size="30" class="required number" alt="请输入优惠劵面值" maxlength="5"/>
				</dd>
			</dl>
			<dl>
				<dt>币种：</dt>
				<dd>
					<select  name="vo.currency" class="required" style="width:185px;">
						<option value="USD">美元</option>
						<option value="RMB">人民币</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>有效期：</dt>
				<dd>
					<input readonly="readonly" type="text" name="validitydate" size="30" maxlength="20" class="required date"  format="yyyy-MM-dd HH:mm:ss"/>

				</dd>
			</dl>
			  			<p>
				    <label>用户类型：</label>
				    <select style="width: 175px" name="lvUserPromoters.userType" class="required" id="userType" onchange="changeType(this.options[this.selectedIndex].value);" >
				        <option value="1" <c:if test="${lvUserPromoters.userType==1}">selected</c:if>>特殊用户</option>
				        <option value="0" <c:if test="${lvUserPromoters.userType==0}">selected</c:if>>普通用户</option>
				    </select>
				</p>

			     <p id="p1" <c:if test="${lvUserPromoters.userType==0}">style="display:none;"</c:if>>
					<label>特殊金额：</label>
					<input name="lvUserPromoters.specialAmount" id="specialAmount" 
					<c:if test="${lvUserPromoters.userType==1}">class="required numberNew"</c:if>
					type="text" size="30" maxlength="20" value="${lvUserPromoters.specialAmount }" lang="tel"/>
				</p>
			<dl>
				<dt>邮箱模板：</dt>
				<dd>
				    <select name="tplKey" class="required"><option value="">请选择邮件模板</option>
				   <c:foreach items="${lvEmailTplList}" var="em" begin="0" end="0"><option value="${em.tplKey}">${em.enTitle}</option></c:foreach>
				     </select>
				</dd>
			</dl>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">审核通过并发邮件</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>