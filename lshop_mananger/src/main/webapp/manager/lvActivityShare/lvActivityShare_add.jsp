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
	   $("#mrcs").attr("class","");
	   $("#total").attr("class","required digitsNoZore");
	}else if(parseInt(activity.val())==2){
       $("#cj").show();
	   $("#coupon").hide();
	   $("#cj input").attr("class","required");
	   $("#coupon input").attr("class","");
	   $("#cjcs").attr("class","required digitsNoZore");
	   $("#mrcs").attr("class","required digitsNoZore");
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
		<form method="post" action="lvActivityAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input id="txtCouponType" type="hidden" value="lvCouponTypeAction!selectSimpleCouponType.action"/>
			    <input id="txtActivityLottery" type="hidden" value="lvActivityAction!selectSimpleACLottery.action?lvActivity.typeKey=7"/>
				<input name="lvActivity.typeKey" type="hidden" value="${lvActivity.typeKey }"/>
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
				    <select name="lvActivityShare.mallFlag" style="width:200px;" onchange="changeSelectURL()" id="mallSysFlag" class="required" >
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
				     <select name="lvActivityShare.givenTypeNum" style="width:200px;" onchange="changeGivenType()" id="givenTypeSel">
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
				   <input name="lvActivityShare.givenProductCode"  type="hidden"/>
				   <input class="required" name="lvActivityShare.givenTypeName" type="text" readonly="readonly" postField="keyword" lookupGroup="lvActivityShare"/>
				   <a id="selCouponType" class="btnLook" href="lvCouponTypeAction!selectSimpleCouponType.action" lookupGroup="lvActivityShare">查找带回</a>	
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     <input name="lvActivityShare.uncollectedTotal" type="text" size="32" maxlength="4" class="required digitsNoZore" id="total"/>
				    </dd>
				</dl>
				</div>
				<div id="cj" style="display:none">
					<dl class="nowrap">
						<dt>抽奖活动：</dt>
						<dd>
				   <input name="lvActivityShare.acLotteryCode"  type="hidden"/>
				   <input class="required" name="lvActivityShare.acLotteryName" type="text" readonly="readonly" postField="keyword" lookupGroup="lvActivityShare"/>
				   <a id="selActivityLottery" class="btnLook" href="lvActivityAction!selectSimpleACLottery.action?lvActivity.typeKey=7" lookupGroup="lvActivityShare">查找带回</a>	
						</dd>
					</dl>
					<dl>
						<dt>抽奖次数：</dt>
						<dd>
						 <input name="lvActivityShare.lotteryTotal" type="text"  maxlength="4" class="required digitsNoZore" size="32" id="cjcs"/>
						</dd>
					</dl>
					<dl>
						<dt>每日参与活动次数：</dt>
						<dd>
						 <input name="lvActivityShare.partakeNum" type="text" size="32" maxlength="4" class="required digitsNoZore" id="mrcs"/>
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
				     	<gv:dictionary type="select" code="ACTIVITY_STATUS" name="lvActivity.status" value="0" />
				    </dd>
				</dl>
				<dl>
				    <dt>微信分享连接：</dt>
				    <dd>
				      <input name="lvActivityShare.shareLink" type="text"  maxlength="128" class="required" size="32"/>
				    </dd>
				</dl>
				 <dl>
				    <dt>微博分享图片：</dt>
				    <dd>
				      <input name="img" type="file"  maxlength="64" class="required" size="32"/>
				    </dd>
				</dl>
					<dl class="nowrap">
					<dt>微博分享描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivityShare.shareDesc" maxlength="200" class="required"></textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>活动描述：</dt>
					<dd>
				       <textarea rows="5" cols="60" name="lvActivity.remark" maxlength="200"></textarea>
					</dd>				  
				</dl>
				
				<!-- 
						<p>
							<label>id：</label>
							<input name="lvActivityShare.id" type="text" size="30" maxlength="10" class="false"/>
						</p>
						<p>
							<label>赠送类型名称：</label>
							<input name="lvActivityShare.givenTypeName" type="text" size="30" maxlength="32" class="true"/>
						</p>
						<p>
							<label>赠送物品关联：</label>
							<input name="lvActivityShare.givenProductCode" type="text" size="30" maxlength="32" class="true"/>
						</p>
						<p>
							<label>赠送物品已发放总数：</label>
							<input name="lvActivityShare.grantTotal" type="text" size="30" maxlength="10" class="true"/>
						</p>
						<p>
							<label>赠送物品未领取总数：</label>
							<input name="lvActivityShare.uncollectedTotal" type="text" size="30" maxlength="10" class="true"/>
						</p>
						<p>
							<label>赠送类型字典(1赠送优惠券,2赠送抽奖机会)：</label>
							<input name="lvActivityShare.givenTypeNum" type="text" size="30" maxlength="5" class="true"/>
						</p>
						<p>
							<label>抽奖机会次数：</label>
							<input name="lvActivityShare.lotteryTotal" type="text" size="30" maxlength="10" class="true"/>
						</p>
						<p>
							<label>每日参与活动次数：</label>
							<input name="lvActivityShare.partakeNum" type="text" size="30" maxlength="10" class="true"/>
						</p>
						<p>
							<label>分享图片：</label>
							<input name="lvActivityShare.shareImage" type="text" size="30" maxlength="128" class="true"/>
						</p>
						<p>
							<label>分享描述：</label>
							
						</p>
						<p>
							<label>code：</label>
							<input name="lvActivityShare.code" type="text" size="30" maxlength="32" class="true"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvActivityShare.createTime" class="textInput date" readonly="readonly" pattern="yyyy-MM-dd HH:mm:ss" type="text" size="30"/>
						</p>
						<p>
							<label>修改时间：</label>
							<input name="lvActivityShare.modifyTime" class="textInput date" readonly="readonly" pattern="yyyy-MM-dd HH:mm:ss" type="text" size="30"/>
						</p>
						<p>
							<label>修改人编号：</label>
							<input name="lvActivityShare.modifyUserId" type="text" size="30" maxlength="10" class="true"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvActivityShare.modifyUserName" type="text" size="30" maxlength="32" class="true"/>
						</p>
						 -->
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