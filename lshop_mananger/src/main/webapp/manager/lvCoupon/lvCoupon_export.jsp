<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script type="text/javascript">
var startTime;
var progressName = $("#progressName").val();

$(document).ready(function () {
	$("[name=importButton]").click(function () {
		//初始化进度条样式
		$("#resultstatus").removeClass();
		$("#resultstatus").addClass("info");
		$("#resultMsg").html("进度");
		$("#showupdate").css("width","230px");
		
		
		$("#progressbarBox").show();
		$("#importText").css("padding-top","125px");
		$("#info").html("信息提示: 正在上传文件请等待......");
	    	 
		 $.ajax({
			 beforeSend : function() {
			    $("#background").hide();
			    $("#progressBar").hide();
			 },
		     url:'progress.action?progressName='+progressName, 
		     type:'post',
		     success:function(json){
		    	 var b = true;
		    	 
		    	 if(json != ""){
		 			var json = eval('(' + json + ')'); 
		 			var flag = parseInt(json.flag);
		 			if(1 == flag){
		 				b = false;
		 			}
		    	 }	
		    	 if(b){
		    		 var myDate = new Date();
	    			 startTime = myDate.getTime();
		    		 $("#importForm").submit(); 
		    		 window.setTimeout("getImportProgressBar()", 2000);
		    		 $("#importText").hide();
		    		 $(".formBar li .buttonActive").hide();
		    	 }else{
		    		 alert("后台正在导出数据");
		    	 }
		    	
		     }
		  });		 
	});	
});

function getImportProgressBar() {
	var timestamp = (new Date()).valueOf();
	  $.ajax({
		 beforeSend : function() {
		    $("#background").hide();
		    $("#progressBar").hide();
		 },
	     url:'progress.action?progressName='+progressName, 
	     type:'post',
	     success : progressCallBack
	  });
		
	var interval = window.setTimeout("getImportProgressBar()", 500);

	function progressCallBack(json){ 
		 if(json != ""){
			var json = eval('(' + json + ')'); 
			var currentCnt = parseInt(json.currentCnt);
			var totalCnt = parseInt(json.totalCnt);
			var failCnt = parseInt(json.failCnt);
			var repeatCnt = parseInt(json.repeatCnt);
			var importCnt = parseInt(json.importCnt);
			var flag = parseInt(json.flag);
			var msg = json.msg;
			
			//flag ： 0=正在上传文件；1=运行中； 2=结束，无错误或失败；3=结束，有错误或失败；4=上传文件出错
			if(flag == 0) {
				$("#info").html("信息提示: 正在输出文件请等待......");
			} else if(flag == 4) {
				$("#resultstatus").removeClass();
				$("#resultstatus").addClass("error");
				$("#info").html("错误信息: 输出的清单（Excel）文件错误，请重新选择");
				$("#checkfile").html("<img src='images/no.png'><font color='#000000'> 上传的清单（Excel）文件错误，请重新选择</font>");
				window.clearTimeout(interval);
				$("#close").show();
			} else {
				$("#info").html("共" + totalCnt + "条, 导出" + importCnt + '条，重复' + repeatCnt + "条，失败" + failCnt + "条，当前" + currentCnt);
				if( flag == 1) {
					var percent =Math.floor( (totalCnt-currentCnt) / totalCnt*230)  + "px";
					$("#showupdate").css("width", percent);	
				} else {
					window.clearTimeout(interval);
					$("#showupdate").css("width", "0px");
					$("#resultMsg").html("完成!");
					if(flag == 3) {
						$("#resultstatus").removeClass();
						$("#resultstatus").addClass("error");
						$("#resultMsg").html("失败"+failCnt + "条!重复"+repeatCnt + "条!");
						$("#failMsg").html("点击查看 <a href='"+msg+ "' ><font color='red'>结果报告</font></a>");
					}else {
						$("#resultstatus").removeClass();
						$("#resultstatus").addClass("correct");
						$("#failMsg").html("点击查看 <a href='"+msg+ "' ><font color='red'>结果报告</font></a>");
					}
				}
			}
			
		}
	}

}
</script>



<div class="page">
	<div class="pageContent">
		<form method="post" id="importForm" action="lvCouponAction!exportCouponList.action?json.navTabId=${json.navTabId}" class="btnAttach"" class="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);"  target="target_upload">
			<input type="hidden" name="lvCoupon.couponTypeCode" value="${lvCoupon.couponTypeCode}" />
			<input type="hidden" id="progressName" name="progressName" value="progressName" />
			<div class="pageFormContent" layoutH="56">
				
				<iframe id='target_upload' name='target_upload' style='display: none'></iframe>
				
				<div class="alert" id="progressbarBox" style="display:none; " >
					<div class="alertContent">
						<div class="info" id="resultstatus">
							<div class="alertInner" style="background-color: white;">
							<h1 id="resultMsg"> 进度 </h1>
							<div class="msg"  >
								<img width="100%;"  src="/manager/common/images/progressBar.gif"/>
							<div ><div id="showupdate" style="position:absolute; top:57px; height: 13px ;width: 230px; background-color: white; right:  35px" ></div> </div>
							
							</div>
							<div id="failMsg" style="text-align: center;"></div>
							<div align="center" id="info"></div>
							
							</div>

						</div>
					</div>
					<div class="alertFooter">
						<div class="alertFooter_r">
							<div class="alertFooter_c"></div>
						</div>
					</div>
				</div>
				<div id="importText" >
				<dl class="nowrap">
					<dt>操作人：</dt>
					<dd>
						<input name="lvCoupon.operator" type="text" size="32" value="${lvCoupon.operator}"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>使用人：</dt>
					<dd>
						<input name="lvCoupon.applyName" type="text" size="32" value="${lvCoupon.apply}" maxlength="32"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>获取时间：</dt>
					<dd>
						<input name="lvCoupon.obtainStartTime" type="text" size="32" class="date" format="yyyy-MM-dd" value="${lvCoupon.obtainStartTime }"/>-
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt></dt>
					<dd>
						<input name="lvCoupon.obtainEndTime" type="text" size="32" class="date" format="yyyy-MM-dd" value="${lvCoupon.obtainEndTime }"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>发放途径</dt>
					<dd>
						<select name="lvCoupon.grantWay" style="width:200px;">
						<option value="">==请选择==</option>
						<option value="0000" <c:if test="${lvCoupon.grantWay=='0000' }">selected="selected"</c:if>>管理员下载</option>
						<c:foreach items="${listOrder }" var="way">
						<option value="${way.code }" <c:if test="${lvCoupon.grantWay==way.code }">selected="selected"</c:if>>${way.activityTitle }</option>
						</c:foreach>
						<c:foreach items="${listLink }" var="way2">
						<option value="${way2.code }" <c:if test="${lvCoupon.grantWay==way2.code }">selected="selected"</c:if>>${way2.activityTitle }</option>
						</c:foreach>
					    </select>
					</dd>				  
				</dl>
				</div>
			</div>
			
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button name="importButton" id="importButton" >导出Excel</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="close">取消</button></div></div></li>
				</ul>
			</div>
			
		</form>
	</div>
</div>