<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script>
function changeGivenType(){
var activity=$("#givenTypeSel").find("option:selected");
    if(parseInt(activity.val())==1){
       $("#coupon").show();
	   $("#product").hide();
	   $("#product input").attr("class","");
	   $("#coupon input").attr("class","required");
	}else if(parseInt(activity.val())==2){
       $("#product").show();
	   $("#coupon").hide();
	   $("#product input").attr("class","required");
	   $("#coupon input").attr("class","");
	}else{
	   $("#coupon").hide();
	   $("#product").hide();
	   $("#product input").attr("class","");
	   $("#coupon input").attr("class","");
	}
}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvLotteryPrizeAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate"  onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
			<input name="lvLotteryPrize.activityCode" type="hidden" value="${lvLotteryPrize.activityCode }"/>
				<!-- 生成表单 -->
				<dl>
				    <dt>奖项类型：</dt>
				    <dd>
				     <select name="lvLotteryPrize.prizeType" onchange="changeGivenType();" id="givenTypeSel" class="required">
						<option value="">==请选择==</option>
						<option value="1">优惠券</option>
						<option value="2">实物</option>
					  </select>
				    </dd>
				</dl>

              <div id="product" style="display:none">
			    <dl>
					<dt>奖项名称：</dt>
					<dd>
						<input name="lvLotteryPrize.prizeName" type="text" maxlength="32"  class="required" size="32" />
					</dd>				  
				</dl>
            </div>
			<div id="coupon" style="display:none">
			        <dl class="nowrap">
						<dt>奖项名称：</dt>
						<dd>
				   <input  name="lvLotteryPrize.givenProductCode"  type="hidden"/>
				   <input  name="lvLotteryPrize.givenTypeName" type="text" readonly="readonly" postField="keyword" lookupGroup="lvLotteryPrize" size="32"/>
				   <a class="btnLook" href="lvCouponTypeAction!selectSimpleCouponType.action?lvActivity.endTime=${lvActivity.endTime }" lookupGroup="lvLotteryPrize">查找带回</a>	
						</dd>
					</dl>
            </div>
				<dl>
				    <dt>奖项数量：</dt>
				    <dd>
				   <input name="lvLotteryPrize.prizeTotal" type="text" size="32" maxlength="6" class="required digits"/>
				    </dd>
				</dl>
				<dl>
				    <dt>奖项图片：</dt>
				    <dd>
				     <input name="img" type="file"  maxlength="64" class="required" size="32"/>
				    </dd>
				</dl>
				<dl>
				    <dt>是否可抽中的奖项：</dt>
				    <dd>
				     <select name="lvLotteryPrize.isDraw">
						<option value="">==请选择==</option>
						<option value="1">是</option>
						<option value="0">否</option>
					  </select>
				    </dd>
				</dl>
				
				<dl>
				    <dt>排序值：</dt>
				    <dd>
				   <input name="lvLotteryPrize.sortId" type="text" size="32" maxlength="5" class="required digits" value="0"/>
				    </dd>
				</dl>
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