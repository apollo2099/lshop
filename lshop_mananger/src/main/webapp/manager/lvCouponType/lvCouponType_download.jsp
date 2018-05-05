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
		    		 //alert(b);
		    		 var myDate = new Date();
	    			 startTime = myDate.getTime();
		    		 //$("#importForm").submit(); 
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
				$("#info").html("错误信息:下载数目不能大于剩余库存，请重新下载！");
				//$("#checkfile").html("<img src='images/no.png'><font color='#000000'> 上传的清单（Excel）文件错误，请重新选择</font>");
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

function stopProgressBar(){
        $.ajax({
			 beforeSend : function() {
			    $("#background").hide();
			    $("#progressBar").hide();
			 },
		     url:'lvProductAction!stopProgressBar.action?progressName='+progressName, 
		     type:'post',
		     success:function(json){
		        $("#failMsg").html("");
		     	$("#resultstatus").removeClass();
				$("#resultstatus").addClass("info");
		        $("#showupdate").css("width", "0px");
				$("#resultMsg").html("异常终止");
		        $("#info").html("终止异常进度条!");
		     }
		  });
}
</script>



<div class="page">
	<div class="pageContent">
		<form method="post" id="importForm" action="lvCouponTypeAction!couponDownload.action?json.navTabId=${json.navTabId}" class="btnAttach"" class="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);" target="target_upload">
			<input type="hidden" id="progressName" name="progressName" value="progressName" />
			<input name="lvCouponType.id" type="hidden" size="30" value="${lvCouponType.id}"/>
			<input name="lvCouponType.total" type="hidden" size="30" value="${lvCouponType.total}"/>
			<input name="lvCouponType.grantNumber" type="hidden" size="30" value="${lvCouponType.grantNumber}"/>
			<input name="lvCouponType.noGrantNumber" type="hidden" size="30" value="${lvCouponType.noGrantNumber}"/>
			<input name="lvCouponType.usedNumber" type="hidden" size="30" value="${lvCouponType.usedNumber}"/>
			
			<input name="lvCouponType.code" type="hidden" size="30" value="${lvCouponType.code}"/>
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
			        <p>
					<label>输入下载数量</label>
	                       <input name="couponDownloadNum" type="text" size="32" class="required digitsNoZore"  maxlength="4">
					</p>
					<p>
						相同的优惠劵码不能赠送给不同的用户,
						已下载的优惠劵码状态变成“已发放“
					</p>
					<div class="divider"></div>
			        <p>
					1)优惠券总数${lvCouponType.total}张
					</p>
					 <p>
					2)未发放数目${lvCouponType.noGrantNumber}张
					</p>
			        <p>
					3)已发放数目${lvCouponType.grantNumber}张
					</p>
					<p>
					4)已使用数目${lvCouponType.usedNumber}张
					</p>
			        <p>
					5)可剩余分配库存${lvCouponType.total-lvCouponType.noGrantNumber-lvCouponType.grantNumber-lvCouponType.usedNumber}张
					</p>
				</div>
			</div>
			
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" name="importButton" id="importButton" >下载</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close" onclick="navTabPageBreak();">关闭</button></div></div>
					</li>
				</ul>
			</div>
			
		</form>
	</div>
</div>