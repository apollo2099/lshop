<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script  type="text/javascript">
//根据类型查询奖项物品或者优惠券信息
function changePrizeType(){
	var prizeType = $("#prizeType").find("option:selected").val();
	var activityCode=$("#activityCode").val();
    $.ajax({
		  type: "POST",
		  url: "lvLotteryLogsAction!toPrizeByType.action",
		  data: "prizeType="+prizeType+"&activityCode="+activityCode,
		  dataType:"json",
		  success: function(data){
		     $("#prize option").remove();
		     $("#prize").append("<option value=''>==请选择==</option>");
		     if(data!=null){
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var prizeName = listTmp[k].prizeName; 
					var prizeCode = listTmp[k].prizeCode; 
					$("#prize").append("<option value="+prizeCode+">"+prizeName+"</option>");
				 }
		     }
		  }
	});
}
//根据选择物品确定物品名称
function changePrizeName(){
	   $("#prizeName").val($("#prize").find("option:selected").text());
}
</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvLotteryLogsAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input name="lvLotteryLogs.activityCode" type="hidden" size="30" maxlength="32" value="${lvLotteryLogs.activityCode }" id="activityCode"/>
				<input name="lvLotteryLogs.prizeName" type="hidden" id="prizeName"/>
				<dl>
					<dt>中奖人名称：</dt>
					<dd>
						<input name="lvLotteryLogs.userName" type="text" size="32" maxlength="64" class="required"/>
					</dd>				  
				</dl>
				<dl>
				    <dt>奖项类型：</dt>
				    <dd>
				     <select style="width:150px;" onchange="changePrizeType()" id="prizeType">
						<option value="">==请选择==</option>
						<option value="1">优惠券</option>
						<option value="2">实物</option>
					  </select>
				    </dd>
				</dl>

				<dl>
				    <dt>奖项物品：</dt>
				    <dd>
				      <select  name="lvLotteryLogs.prizeCode" style="width:150px;" onchange="changePrizeName()" id="prize">
						<option value="">==请选择==</option>
					  </select>
				    </dd>
				</dl>
				<dl>
				    <dt>奖项数目：</dt>
				    <dd>
				     <input name="lvLotteryLogs.prizeNum" type="text"  maxlength="6" class="required digits" size="32"/>
				    </dd>
				</dl>
				<dl>
				    <dt>中奖时间：</dt>
				    <dd>
				     <input name="lvLotteryLogs.createTime" class="required date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="32"/>
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