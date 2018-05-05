<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<head>
<%@include file="/web/mtmcn/user_center/c_meta.jsp"%>	
<title>用户中心_在线充值</title>
<c:set var="MallPath" value="" />
<c:set var="ShopPath" value="" />
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta content="telephone=no" name="format-detection" />
<link href="${MallPath}${resDomain}/mtmcn/res/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/FomrValidate.js"></script>
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/header.js"></script>
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/hammer.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/checkForm.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/pay.js"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/jquery_validate_extend.js"></script>
<script type="text/javascript">
function doAction(){
	var paymethod=$("input[name='paymethod']:checked");
    if(paymethod.length==0){
       $("#msgbox").html("请选择支付方式！");
       return false;
    }
    $("#msgbox").html("");
    if(paymethod.val()==3){
     var formUrl=$("#addressFormVbId").attr("action")+"?rnum=${rnum}&paymethod="+paymethod.val();
     location.href=formUrl;
     
    }
	return true;
}
function show(id){
    var W=$(document).width();
    var H=$(document).height();
    var mask=document.getElementById("mask");
    mask.style.cssText="position:absolute;z-index:5;width:"+W+"px;height:"+H+"px;background:#000;filter:alpha(opacity=30);opacity:0.3;top:0;left:0;";
    $("#mask").show();
	  var tx_b=document.getElementById(id);
	    tx_b.style.left=(window.screen.width/2-400)+"px";
	    tx_b.style.top=(150+document.documentElement.scrollTop||document.body.scrollTop)+'px';
	    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
	    	//你是使用IE
	    }else if (navigator.userAgent.indexOf('Firefox') >= 0){
	    	//你是使用Firefox
	    }else if (navigator.userAgent.indexOf('Opera') >= 0){
	    	//你是使用Opera
	    }
	    $(tx_b).fadeIn("fast",function(){});
	   //	document.getElementById("#"+id).style.height=document.body.clientHeight+"px";
	 //   document.getElementById("#"+id).style.width=document.body.clientWidth+"px";
	$("#"+id).show();
}
function hide(id){
	$("#"+id).hide();
	$("#mask").hide();
}

// 选择支付方式事件
function changePayType(e) {
	var payType = e.value;	// 当前选择的支付方式
	var rate = "${rate}";	// 美元与人民币的汇率
	var currency = $("#currency").text();	// 应付币种
	var money = $("#money").text();			// 应付金额
	var RMB = "CNY";
	var USD = "USD";
	/*
	*  人民币支付：3=支付宝内卡  17=支付宝(old外卡jcb)
	*/
	if (payType == 3 || payType == 17) {
		if (currency == USD) {
			currency = RMB;
			money = money * rate;
		}
	} else {
		if (currency == RMB) {
			currency = USD;
			money = money / rate;
		}
	}
	// 四舍五入取两位小数
	money = Math.round(money * 100) / 100;
	$("#currency").text(currency);
	$("#money").text(money);
}
function useDefaultAddress(){
	var jsonStr=$('input[name="address"]:checked').val();
	if(""==jsonStr){
		return;
	}
	var obj = eval('('+jsonStr+')');
	$("#relName").val(obj.relName);
	$("#mobile").val(obj.mobile);
	$("#tel").val(obj.tel);
	$("#adress").val(obj.adress);
	$("#provinceId").val(obj.provinceNameId);
	$("#countryId").val(obj.contryId);
	$("#cityId").val(obj.cityNameId);
	$("#cityName").val(obj.cityName);
	$("#contryName").val(obj.contryName);
	$("#postcodeId").val(obj.postCode);
	$("#provinceName").val(obj.provinceName);
}

$(function(){
	  
    $(".fid_zd").click(function(){
		$(".myconmmentsubdivbox1").slideToggle(); 
		});	
	 var spans=$(".massinfomaw").find(".chiredio");	 
	  $.each(spans,function(m){
		$(this).click(function(){
			if($(this).attr("id")){
				//$(this).removeAttr("id");
				}else{
		         $(this).attr("id","mdu");
				 $(this).parents(".massinfomaw").siblings(".massinfomaw").find(".chiredio").removeAttr("id");
		         $(this).children().attr("checked","true");
		         $(this).parents(".massinfomaw").siblings(".massinfomaw").find(".chiredio").children().removeAttr("checked");
				}
		         useDefaultAddress();
			});
		  });
	  $(".nousers").click(function(){
			$("#nousconp").slideToggle();
	  });
			  
})

</script>
</head>

<body>
<%@include file="/web/mtmcn/user_center/c_top.jsp"%>

<article>
<div class="vb_box1">  
	<form action="/web/recharge!toPay.action" method="post" onsubmit="return doAction();"  id="addressFormVbId">
	<input type="hidden" name="rnum" value="${rnum}">
	<div class="vb_order">
		<strong>订单：</strong>
		<span>订单号：${recharge.rnum}</span>
          <span>账号：${recharge.accounts}</span>
		<span>购买V币数量：${recharge.vbNum }</span>
		<span>应付金额：<label id="currency">${recharge.currency}</label> <label id="money">${recharge.money}</label></span>
	</div>
	
	<div class="vb_order" style="margin-bottom:30px">
		<strong>支付方式：</strong>
		<c:foreach var="paymentStyle" items="${paymentStyleList}">
			<span class="visa">
				<label><input name="paymethod" type="radio" value="${paymentStyle.payValue}" 
						<c:if test="${paymentStyle.payValue eq recharge.rtype}">checked="checked"</c:if>/>${paymentStyle.payName}</label>
			</span>
        </c:foreach>
        <div id="msgbox" style="color:#f40000;margin-top: 25px;text-align: center;">${requestScope.resultMsg}</div>
         <script type="text/javascript">
	 $(function(){
     	var paymethod=$("input[name='paymethod']:checked");
		    if(paymethod.length!=0){
		    	if(paymethod.val()!=3){
		         $("#payInfoId").show();
		    	}
		    }
		   $("input[name='paymethod']").click(function(){
			 paymethod=$("input[name='paymethod']:checked");
			   if(paymethod.val()==3){
				 $("#payInfoId").hide();
			   }else{
			      $("#payInfoId").show();
			   }
			});
       })
      </script>
       <div style="display:none;" id="payInfoId">
	 <div class="bo_boto">
  <div class="vb_order">
     <strong>账单信息 : <s:if test="#request.addressList!=null"> <b class="fid_zd">使用收货地址</b></s:if></strong>
     
  <s:if test="#request.addressList!=null">
  <div style="margin-top:20px; background-color:#ebecee;" class="mycomment">
    
     <div class="myconmmentsubdivbox1" style="padding-bottom:0;display:none;">    
       
               <s:iterator value="#request.addressList" id="address" status="stat">
      <div class="massinfomaw" style="min-height:100px;">
      <div class="conmaseg" style="min-height:100px">
        
     <table width="94%" border="0" align="center">
     <tr>
      <td width="32%" height="12" align="right"></td>
      <td width="68%" height="12"></td>
    </tr>
  <tr>
    <td height="25" align="right" valign="top">姓名：</td>
    <td height="25" align="left" valign="top">${address.relName}</td>
    </tr>
  <tr>
    <td width="32%" height="25" align="right" valign="top">地址：</td>
    <td height="25" valign="top">${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName}&nbsp;${address.adress }</td>
  </tr>
  <tr>
    <td width="32%" height="25" align="right" valign="top">邮编区号：</td>
    <td height="25" valign="top">${address.postCode}</td>
  </tr>
   <tr>
    <td width="32%" height="25" align="right" valign="top">固定电话：</td>
    <td height="25" valign="top">${address.tel}</td>
  </tr>
   <tr>
    <td width="32%" height="25" align="right" valign="top">手机号码：</td>
    <td height="25" valign="top">${address.mobile}</td>
  </tr>
  <tr>
      <td width="32%" height="12" align="right"></td>
      <td height="12"></td>
    </tr>
</table>

        
    </div>
      <div class="massrediou">
         <span class="chiredio" style="padding-left:0; min-height:178px">
            <input name="address"  type="radio" value="{relName:'${address.relName}',mobile:'${address.mobile}',tel:'${address.tel}',adress:'${address.adress}',provinceName:'${address.provinceName}',contryId:'${address.contryId}',cityName:'${address.cityName}',postCode:'${address.postCode}',contryName:'${address.contryName}'}" style="visibility:hidden">
         </span>
        
      </div>
      <div class="clear"></div>
   </div>
</s:iterator>

   </div>
       
  </div>
</s:if>
  </div>
  </div>
  <div class="vb_order1" style="margin-bottom:30px;">
     <table width="100%" border="0" align="center" style="margin-top:20px;">
     
  <tr>
    <td width="30%" height="80" align="right" class="fonsi">姓名：</td>
    <td width="70%" height="80" colspan="3">
     <div  class="inputd">
       <input type="text" name="lvAccountAddress.relName" id="relName" class="inpu" placeholder="请输入姓名"/>
        <div class='tip' ><em></em><span>请输入姓名</span><i></i><b></b></div>
    </div>
    </td>
  </tr>
  <tr>
    <td width="30%" height="80" align="right" class="fonsi">手机号码：</td>
    <td width="70%" height="80" colspan="3">
     <div  class="inputd">
       <input type="text"  id="mobile" name="lvAccountAddress.mobile" class="inpu" placeholder="请输入手机号码"/>
        <div id="mobileInfo" class="tip">
          
        </div>
    </div>
    </td>
  </tr>
  <tr>
    <td width="30%" height="80" align="right" class="fonsi">固定电话：</td>
     <td width="70%" height="80" colspan="3">
       <div  class="inputd">
         <input type="text"  class="inpu" placeholder="请输入固定电话" id="tel" name="lvAccountAddress.tel" />
         <div id="telInfo" class="tip">
          
        </div>
       </div>
    </td>
  </tr>
  <tr>
    <td width="30%" height="-2" align="right" class="fonsi">选择国家：</td>
    <td width="70%" height="60" colspan="3">
    <div class="boxsele" style="width:100%">
    <select id="countryId" class="spanchose1" name="lvAccountAddress.contryId" onchange="areaSerach('provinceId',this.value)"></select>    
    <input type="hidden" name="lvAccountAddress.contryName" id="contryName"  value=""/> 
        <div id="contryIdInfo" class="tip">
          <em></em>
          <span>请选择国家</span>
          <i></i>
          <b></b>
          </div>
      </div></td>
  </tr>
  <tr>
    <td width="30%" height="-2" align="right" class="fonsi">洲/省：</td>
    <td width="70%" height="60" colspan="3">
    
    <div class="boxsele" style="width:100%">
       <select id="provinceId" name="lvAccountAddress.provinceId" class="spanchose1" onchange="areaSerach('cityId',this.value)" style="display:none;" ></select>
	   <input type="text" class="inpu" value=""  type="hidden" name="lvAccountAddress.provinceName" id="provinceName" placeholder="请输入洲/省" />
        <div id="provinceNameInfo"  class="tip">
          <em></em>
          <span>请输入洲/省</span>
          <i></i>
          <b></b>
          </div>
      </div></td>
  </tr>
  <tr>
    <td width="30%" height="-2" align="right" class="fonsi">市/县：</td>
    <td width="70%" height="60" colspan="3">
       <input type="hidden" id="cityNameId" name="" value="" />
						<div class="boxsele" style="width:100%">
						<select id="cityId" name="lvAccountAddress.cityId" class="spanchose1" style="display:none;" onchange="changeCityValue()" ></select>
						<input type="text" class="inpu" value="" name="lvAccountAddress.cityName" id="cityName" placeholder="请输入市/县" />
        <div id="cityNameInfo" class="tip">
          <em></em>
          <span>请输入市/县</span>
          <i></i>
          <b></b>
          </div>
      </div></td>
  </tr>
  <tr>
    <td width="30%" height="80" align="right" class="fonsi">街道地址：</td>
    <td height="80" colspan="3"><div  class="inputd">
      <input type="text"  class="inpu" placeholder="请输入街道地址"  name="lvAccountAddress.adress" id="adress"  />
      <div class="tip" id="addressInfo"></div>
      </div></td>
  </tr>
   <tr>
    <td width="30%" height="80" align="right" class="fonsi">邮政编号：</td>
     <td width="70%" height="80" colspan="3">
       <div  class="inputd">
         <input type="text"  class="inpu" placeholder="请输入邮政区号" name="lvAccountAddress.postCode" id="postcodeId"/>
       </div>
    </td>
  </tr>
  
</table>
     
    
  </div>  
  </div>
  <div id="msgbox" style="color:#f40000;margin-top: 25px;text-align: center;">${requestScope.resultMsg}</div>
	<div class="recharge">
	     <a href="javascript:void();" onclick="$('#paysubmit').submit()" id="paysubmit">前往支付</a>  
	</div>
	</form>
</div>
</article>

<%@include file="/web/mtmcn/user_center/c_bottom.jsp"%>
<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '在线充值';
	initNum();
</script>
<script type="text/javascript">
		$().ready(function() {
			$.getScript("${resDomain}/mbmcn/res/js/sysarea.js", function(){
				areaSerach('countryId',null);
				});
			$("#addressFormVbId").validate({
				rules: {
				    'lvAccountAddress.relName':{required: true,minlength:2,maxlength:32},
					'lvAccountAddress.mobile':{isChineseChar:true,maxlength:16},
					'lvAccountAddress.tel':{isChineseChar:true,maxlength:16},
					'lvAccountAddress.postCode':{required: true,isChineseChar:true,maxlength:16}
				},
				messages: {
					'lvAccountAddress.relName': {
						required: "<div class='tip' style='display:block'><em></em><span>姓名不能空</span><i></i><b></b></div>"
					
					 },
					 'lvAccountAddress.mobile': {
						isChineseChar: "<div class='tip' style='display:block'><em></em><span>手机不能含有特殊字符!</font></span><i></i><b></b></div></span><i></i><b></b></div>",
						maxlength: "<div class='tip' style='display:block'><em></em><span>手机不能大于16位字符!</font></span><i></i><b></b></div></span><i></i><b></b></div>"
					 },
					 'lvAccountAddress.tel': {
						 isChineseChar: "<div class='tip' style='display:block'><em></em><span>电话不能含有特殊字符!</font></span><i></i><b></b></div></span><i></i><b></b></div>",
						 maxlength: "<div class='tip' style='display:block'><em></em><span>电话不能大于16位字符!</font></span><i></i><b></b></div></span><i></i><b></b></div>"
					 },
					'lvAccountAddress.postCode': {
						required: "<div class='tip' style='display:block'><em></em><span>请输入邮政区号</font></span><i></i><b></b></div></span><i></i><b></b></div>",
						isChineseChar: "<div class='tip' style='display:block'><em></em><span>邮政区号不能含有特殊字符</font></span><i></i><b></b></div></span><i></i><b></b></div>",
						maxlength: "<div class='tip' style='display:block'><em></em><span>邮政区号不能大于16位字符</font></span><i></i><b></b></div></span><i></i><b></b></div>"
					 }
				},
				submitHandler:function(form){
					var paymethod=$("input[name='paymethod']:checked");
				    if(paymethod.length==0){
				       $("#msgbox").html("请选择支付方式");
				       return;
				    }
				 
				    $("#msgbox").html("");
				    var isChineseChar = /[\u4E00-\u9FA5\uF900-\uFA2D]/; 
					var tel=$("#tel");//电话号码
					var mobile=$("#mobile");//手机号码
					var adress=$("#adress");//
					var cityName=$("#cityName");
					var provinceName=$("#provinceName");
					var contryId=$("#contryId");
					 $("#mobileInfo").hide();
					 $("#telInfo").hide();
					if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
					  $("#mobileInfo").html("<em></em><span>电话和手机必须填写其中一个</font></span><i></i><b></b>");
					  $("#mobileInfo").show();
					  $("#telInfo").html("<em></em><span>电话和手机必须填写其中一个</font></span><i></i><b></b>");
					  $("#telInfo").show();
					   mobile.focus();
					   return ;
				    }
					
					if($.trim($("#contryName").val())==''){
					      $("#contryIdInfo").html("<em></em><span>国家不能为空！</font></span><i></i><b></b>");
					      $("#contryIdInfo").show();
						  return ;
					}
					if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='州/省'){
					      $("#provinceNameInfo").html("<em></em><span>州/省不能为空！</font></span><i></i><b></b>");
					      $("#provinceNameInfo").show();
						  provinceName.focus();
						  return ;
					}
					if($.trim(cityName.val())==''||$.trim(cityName.val())=='县/市'){
					      $("#cityNameInfo").html("<em></em><span>县/市不能为空！</font></span><i></i><b></b>");
					      $("#cityNameInfo").show();
						  cityName.focus();
						  return ;
					}
					if($.trim(adress.val())==''||$.trim(adress.val())=='街道详细地址'){
					  $("#addressInfo").html("<style='display:block'><em></em><span>街道地址不能空</font></span><i></i><b></b></div></span><i></i><b></b>");
					  $("#addressInfo").show();
					  return ;
				    }
				    form.submit();
				    
				}
			});
		});
		
		    function areaSerach(id,areaid){	
		    	  
		     	  var obj,area='';
		     	    if(id=='countryId')
		     	     areastr='<option value="">请选择</option>';
		     	     else
		     	     areastr='<option value="">请选择</option>';
		     	    	 
		     	    
		     	    	for(var i=0;i<sysarea.length;i++){
		        		     obj=sysarea[i];
		        		    if(obj.parentid==areaid)
		        			{
		        			   if(obj.id=='100023')
		        			       areastr+='<option value="'+obj.id+'">'+obj.namecn+'</option>';
		        			   else{
		        				   if(id=='countryId'){
		        			       area+='<option value="'+obj.id+'">'+obj.nameen+'</option>';
		        				   }else{
		        				    area+='<option value="'+obj.id+'">'+obj.namecn+'</option>';
		        				   }
		        			   }
		                    }
		        		}
		     	    	 if(id=='countryId'){
		     	    		
		     	    		
		     	    		$("#provinceName").val("");
		     	    		$("#cityId").val("");
		     	    	 }
		     		if(id=="provinceId"){
		     			if(area==''){
		     				$("#provinceId").hide();
		         			$("#cityId").hide();
		         			$("#provinceName").show();
		         			$("#cityName").show();	
		     			}else{
		     				$("#provinceId").show();
		         			$("#cityId").show();
		         			$("#provinceName").hide();
		         			$("#cityName").hide();	
		         			$("#contryName").val($("#countryId").find("option:selected").text());
		         			$("#contryNameInfo").hide();
		     			}
		     		}
		     		
		     		if(id=="cityId"){
		     			if(area==''){
		     				$("#cityId").hide();	
		     				$("#cityName").show();
		     				$("#provinceName").val("");
		     			}else{
		     				$("#cityId").show();	
		     				$("#cityName").hide();
		     				$("#provinceName").val($("#provinceId").find("option:selected").text());
		     				$("#provinceNamefo").hide();
		     			}
		     			
		     		}
		     		$('#'+id).html(areastr+area);
		      }
		    
		    function changeCityValue(){
		    	$("#cityName").val($("#cityId").find("option:selected").text());	
		    }
	</script>
</body>
</html>
