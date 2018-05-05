<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<script>
function changeGivenType(){
var activity=$("#givenTypeSel").find("option:selected");
    if(parseInt(activity.val())==1){
       $("#coupon").show();
	   $("#cj").hide();
	   $("#zp").hide();
	   $("#cj input").attr("class","");
	   $("#coupon input").attr("class","required");
	   $("#cjcs").attr("class","");
	   $("#total").attr("class","required digitsNoZore");
	}else if(parseInt(activity.val())==2){
       $("#cj").show();
	   $("#coupon").hide();
	   $("#zp").hide();
	   $("#cj input").attr("class","required");
	   $("#coupon input").attr("class","");
	   $("#cjcs").attr("class","required digitsNoZore");
	   $("#total").attr("class","");
	}else if(parseInt(activity.val())==3){
		$("#zp").show();
		$("#cj").hide();
		$("#coupon").hide();
		$("#cj input").attr("class","");
		$("#coupon input").attr("class","");
		$("#total").attr("class","");
		$("#cjcs").attr("class","");
	}
}

//根据体系变更，修改查询返回连接
function changeSelectURL(){
	var mallSysFlag=$("#mallSysFlag").find("option:selected");
	$("#selCouponType").attr("href",$("#txtCouponType").val()+"?mallSysFlag="+mallSysFlag.val()+"");
	$("#selActivityLottery").attr("href",$("#txtActivityLottery").val()+"&mallSysFlag="+mallSysFlag.val()+"");
}

//删除商品
function delDetails(flag){
	$("#sp"+flag).remove();
}
//删除赠品
function delGiftDetails(flag){
	$("#zp"+flag).remove();
}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvActivityAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input name="lvActivity.typeKey" type="hidden" value="${lvActivity.typeKey }"/>
				<input id="txtCouponType" type="hidden" value="lvCouponTypeAction!selectSimpleCouponType.action"/>
			    <input id="txtActivityLottery" type="hidden" value="lvActivityAction!selectSimpleACLottery.action?lvActivity.typeKey=7"/>
				<dl>
					<dt>活动名称：</dt>
					<dd>
						<input name="lvActivity.activityTitle" type="text" size="64" maxlength="120" class="required"/>
					</dd>				  
				</dl>
				<dl>
					<dt>活动英文名称：</dt>
					<dd>
						<input name="lvActivity.activityTitleEn" type="text" size="64" maxlength="120" class="required"/>
					</dd>				  
				</dl>
				<dl>
				    <dt>活动类型：</dt>
				    <dd>
					  <gv:dictionary type="select" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}" disabled="disabled"/>
				    </dd>
				</dl>
				<dl>
				    <dt>指定商品：</dt>
				    <dd>
					<a target="dialog" href="lvProductAction!selectMultipleProduct.action?json.navTabId=${json.navTabId}" lookupGroup="lvProduct" width="750" height="650" mask="true"><input type="button" value="选择商品"></a>
				    </dd>
				</dl>
				
                <div class="divider"></div>
				<table id="tab" border="1" width="80%"  >
				   <thead>
				   <tr>
						<th width="5%">ID</th>
					    <th width="20%">所属关系</th>
					    <th width="45%">商品名称</th>
						<th width="25%">商品价格</th>
						<th width="10%">操作</th>
				   </tr>
				   </thead>
				   <tbody id="tabDetails">
				   </tbody>
			   </table>
				 <div class="divider"></div>
                  <div style="display:block" id="givenType">
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
					  <gv:dictionary type="select" code="ACTIVITY_GIVEN_TYPE_NUM"  name="lvActivityAppointProduct.givenTypeNum"  change="changeGivenType()" id="givenTypeSel"/>
				    </dd>
				</dl>
				<div id="coupon" style="display:none">
				<dl class="nowrap">
				    <dt>优惠券：</dt>
				    <dd>
				   <input name="lvActivityAppointProduct.givenProductCode"  type="hidden"/>
				   <input class="required" name="lvActivityAppointProduct.givenTypeName" type="text" readonly="readonly" postField="keyword" lookupGroup="lvActivityAppointProduct"/>
				   <a id="selCouponType" class="btnLook" href="lvCouponTypeAction!selectSimpleCouponType.action" lookupGroup="lvActivityAppointProduct">查找带回</a>	
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     <input name="lvActivityAppointProduct.uncollectedTotal" type="text" size="32" maxlength="4" class="required digitsNoZore" id="total"/>
				    </dd>
				</dl>
				</div>
				<div id="cj" style="display:none">
					<dl class="nowrap">
						<dt>抽奖活动：</dt>
						<dd>
				   <input name="lvActivityAppointProduct.acLotteryCode"  type="hidden"/>
				   <input class="required" name="lvActivityAppointProduct.acLotteryName" type="text" readonly="readonly" postField="keyword" lookupGroup="lvActivityAppointProduct"/>
				   <a id="selActivityLottery" class="btnLook" href="lvActivityAction!selectSimpleACLottery.action?lvActivity.typeKey=7" lookupGroup="lvActivityAppointProduct">查找带回</a>	
						</dd>
					</dl>
					<dl>
						<dt>抽奖次数：</dt>
						<dd>
						 <input name="lvActivityAppointProduct.lotteryTotal" type="text"  maxlength="4" class="required digitsNoZore" size="32" id="cjcs"/>
						</dd>
					</dl>
				</div>
				
				<div id="zp" style="display:none">
					<dl class="nowrap">
						<dt>指定赠品：</dt>
						<dd>
				   <a target="dialog" href="lvPubGiftAction!selectMultipleGift.action?lvPubGift.status=1&json.navTabId=${json.navTabId}" lookupGroup="lvPubGift" width="750" height="650" mask="true"><input type="button" value="选择赠品"></a>	
						</dd>
					</dl>
				<div class="divider"></div>
				   <table id="tabGift" border="1" width="100%"  >
				   <thead>
				   <tr>
						<th width="5%">ID</th>
					    <th width="35%">赠品中文名称</th>
					    <th width="20%">每次赠送数量</th>
						<th width="15%">赠品总量</th>
						<th width="15%">排序</th>
						<th width="10%">操作</th>
				   </tr>
				   </thead>
				   <tbody id="tabGiftDetails">
				   </tbody>
			   </table>
				</div>
				
				</div>
				
                 <div class="divider"></div>
                 <dl>
				    <dt>活动是否允许使用优惠券：</dt>
				    <dd>
				     <input name="lvActivityAppointProduct.isUseCoupon" type="radio"  value="1" checked="checked"/>是
				     <input name="lvActivityAppointProduct.isUseCoupon" type="radio"  value="0" disabled="disabled"/>否
				    </dd>
				</dl>
				<dl>
				    <dt>活动开始时间：</dt>
				    <dd>
				     <input name="lvActivity.startTime" class="required date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="32"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动结束时间：</dt>
				    <dd>
				     <input name="lvActivity.endTime" class="required date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="32"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动状态：</dt>
				    <dd>
					  <gv:dictionary type="select" code="ACTIVITY_STATUS" name="lvActivity.status" />
				    </dd>
				</dl>
				<dl class="nowrap">
					<dt>活动描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivity.remark" maxlength="200"></textarea>
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