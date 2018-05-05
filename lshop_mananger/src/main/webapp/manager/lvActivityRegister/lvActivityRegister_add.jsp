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
	   $("#cj input").attr("class","");
	   $("#coupon input").attr("class","required");
	   $("#cjcs").attr("class","");
	   $("#total").attr("class","required digitsNoZore");
	}else if(parseInt(activity.val())==2){
       $("#cj").show();
	   $("#coupon").hide();
	   $("#cj input").attr("class","required");
	   $("#coupon input").attr("class","");
	   $("#cjcs").attr("class","required digitsNoZore");
	   $("#total").attr("class","");
	}
}

//根据体系变更，修改查询返回连接
function changeSelectURL(){
	var mallSysFlag=$("#mallSysFlag").find("option:selected");
	$("#selCouponType").attr("href",$("#txtCouponType").val()+"?mallSysFlag="+mallSysFlag.val()+"");
	$("#selActivityLottery").attr("href",$("#txtActivityLottery").val()+"&mallSysFlag="+mallSysFlag.val()+"");
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
				    <dt>活动类型：</dt>
				    <dd>
				    <gv:dictionary type="select" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}" disabled="disabled"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动体系：</dt>
				    <dd>
				    <select name="lvActivityRegister.storeId" style="width:200px;" onchange="changeSelectURL()" id="mallSysFlag" class="required">
						<option value="">==请选择==</option>
						<c:foreach items="${mallList }" var="s">
						  <option value="${s.mallSystemFlag }">${s.mallSystemName }</option>
						</c:foreach>
					  </select>
				    </dd>
				</dl>
				 <div class="divider"></div>
                  <div style="display:block" id="givenType">
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
				     <select name="lvActivityRegister.givenTypeNum" style="width:200px;" onchange="changeGivenType()" id="givenTypeSel">
						<option value="">==请选择==</option>
						<option value="1" >赠送优惠券</option>
						<option value="2" >赠送抽奖机会</option>
					  </select>
				    </dd>
				</dl>
				<div id="coupon" style="display:none">
				<dl class="nowrap">
				    <dt>优惠券：</dt>
				    <dd>
				   <input name="lvActivityRegister.givenProductCode"  type="hidden"/>
				   <input class="required" name="lvActivityRegister.givenTypeName" type="text" readonly="readonly" postField="keyword" lookupGroup="lvActivityRegister"/>
				   <a id="selCouponType" class="btnLook" href="lvCouponTypeAction!selectSimpleCouponType.action" lookupGroup="lvActivityRegister">查找带回</a>	
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     <input name="lvActivityRegister.uncollectedTotal" type="text" size="32" maxlength="4" class="required digitsNoZore" id="total"/>
				    </dd>
				</dl>
				</div>
				<div id="cj" style="display:none">
					<dl class="nowrap">
						<dt>抽奖活动：</dt>
						<dd>
				   <input name="lvActivityRegister.acLotteryCode"  type="hidden"/>
				   <input class="required" name="lvActivityRegister.acLotteryName" type="text" readonly="readonly" postField="keyword" lookupGroup="lvActivityRegister"/>
				   <a id="selActivityLottery" class="btnLook" href="lvActivityAction!selectSimpleACLottery.action?lvActivity.typeKey=7" lookupGroup="lvActivityRegister">查找带回</a>	
						</dd>
					</dl>
					<dl>
						<dt>抽奖次数：</dt>
						<dd>
						 <input name="lvActivityRegister.lotteryTotal" type="text"  maxlength="4" class="required digitsNoZore" size="32" id="cjcs"/>
						</dd>
					</dl>
				</div>
				
				</div>
				
                 <div class="divider"></div>
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
				     	<gv:dictionary type="select" code="ACTIVITY_STATUS" name="lvActivity.status" value="0"/>
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