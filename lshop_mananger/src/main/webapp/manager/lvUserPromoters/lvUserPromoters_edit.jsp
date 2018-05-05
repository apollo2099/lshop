<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
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
		<form method="post" action="lvUserPromotersMngAction!saveEdit.action"class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
			<input type="hidden" name="lvUserPromoters.id" value="${lvUserPromoters.id }" />
			<input type="hidden" name="lvUserPromoters.realName" value="${lvUserPromoters.realName }" />
			<input type="hidden" name="lvUserPromoters.accountTypes" value="${lvUserPromoters.accountTypes }" />
			<input type="hidden" name="lvUserPromoters.accountNumber" value="${lvUserPromoters.accountNumber }" />
			<input type="hidden" name="lvUserPromoters.contryId" value="${lvUserPromoters.contryId }" />
			<input type="hidden" name="lvUserPromoters.contryName" value="${lvUserPromoters.contryName }" />
			<input type="hidden" name="lvUserPromoters.provinceId" value="${lvUserPromoters.provinceId }" />
			<input type="hidden" name="lvUserPromoters.provincename" value="${lvUserPromoters.provincename }" />
			<input type="hidden" name="lvUserPromoters.cityId" value="${lvUserPromoters.cityId }" />
			<input type="hidden" name="lvUserPromoters.cityName" value="${lvUserPromoters.cityName }" />
			<input type="hidden" name="lvUserPromoters.approvalStatus" value="${lvUserPromoters.approvalStatus }" />
			<input type="hidden" name="lvUserPromoters.description" value="${lvUserPromoters.description }" />
			<input type="hidden" name="lvUserPromoters.settlementStatus" value="${lvUserPromoters.settlementStatus }" />
			<input type="hidden" name="lvUserPromoters.settlementedAmount" value="${lvUserPromoters.settlementedAmount }" />
			<input type="hidden" name="lvUserPromoters.settlementedNum" value="${lvUserPromoters.settlementedNum }" />
			<input type="hidden" name="lvUserPromoters.settlementAmount" value="${lvUserPromoters.settlementAmount }" />
			<input type="hidden" name="lvUserPromoters.settlementNum" value="${lvUserPromoters.settlementNum }" />
			<input type="hidden" name="lvUserPromoters.nonSettlementAmount" value="${lvUserPromoters.nonSettlementAmount }" />
			<input type="hidden" name="lvUserPromoters.nonSettlementNum" value="${lvUserPromoters.nonSettlementNum }" />
			<input type="hidden" name="lvUserPromoters.userRating" value="${lvUserPromoters.userRating }" />
			<input type="hidden" name="lvUserPromoters.uid" value="${lvUserPromoters.uid }" />
			<input type="hidden" name="lvUserPromoters.userType" value="${lvUserPromoters.userType }">
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>邮箱：</label>
					<input name="lvUserPromoters.email"   class="required email" type="text" size="30" maxlength="40" value="${lvUserPromoters.email}" lang="email"/>
				</p>
				<p>
					<label>电话：</label>
					<input name="lvUserPromoters.tel" class="required number" type="text" size="30" maxlength="20" value="${lvUserPromoters.tel }" lang="tel"/>
				</p>
				<p>
					<label>地址：</label>
					<input name="lvUserPromoters.adress" id="alias" class="required"  type="text" size="30" maxlength="50" value="${lvUserPromoters.adress }" lang="adress"/>
				</p>
				<!-- 
				<p>
				    <label>用户类型：</label>
				    <select style="width: 175px" name="lvUserPromoters.userType" class="required" id="userType" onchange="changeType(this.options[this.selectedIndex].value);" >
				        <option value="0" <c:if test="${lvUserPromoters.userType==0}">selected</c:if>>普通用户</option>
				        <option value="1" <c:if test="${lvUserPromoters.userType==1}">selected</c:if>>特殊用户</option>
				    </select>
				</p>
				 -->
			     <p id="p1" <c:if test="${lvUserPromoters.userType==0}">style="display:none;"</c:if>>
					<label>特殊金额：</label>
					<input name="lvUserPromoters.specialAmount" id="specialAmount"  type="text" size="30" maxlength="20" value="${lvUserPromoters.specialAmount }" lang="tel"/>
				</p>
			</div>
			
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
					</li>
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
	var type=$("#userType").val();
    if(type==1){
      $("#p1").show();
      $("#specialAmount").attr("class","required")
	}else if(type==0){
	  $("#p1").hide();
	  $("#specialAmount").attr("class","")
	}
})
</script>