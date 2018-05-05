<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var img_flag=true;
function dograde(v){
	for(i=1;i<=5;i++){
   	 	document.getElementById('image_'+i).src="${resDomain}/tvpaden/res/images/wjx_h.gif";
    }
	for(i=1;i<=v;i++){
		document.getElementById('image_'+i).src="${resDomain}/tvpaden/res/images/wjx.gif";
	}
	var checkboxs=document.getElementsByName("grade");
	checkBoxsFalse();
	if(v<3){
		checkboxs[2].checked=true;
		document.getElementById('gradeId').value=1;
	}else if(v==3){
		checkboxs[1].checked=true;
		document.getElementById('gradeId').value=2;
	}else if(v>3){
		checkboxs[0].checked=true;
		document.getElementById('gradeId').value=3;
	}
	document.getElementById('scoreid').value=v;
	img_flag=false;

}
function checkBoxsFalse(){
	var checkboxs=document.getElementsByName("grade");
       	for(i=0;i<checkboxs.length;i++){
          checkboxs[i].checked=false;
     	}
}

function outStar(){
    if(true==img_flag){
      for(i=1;i<=5;i++){
    	  document.getElementById('image_'+i).src="${resDomain}/tvpaden/res/images/wjx_h.gif";
          }
      checkBoxsFalse();
     
    }
}
function moveStar(v){
	 if(true==img_flag){
	for(i=1;i<=v;i++){
                      document.getElementById('image_'+i).src="${resDomain}/tvpaden/res/images/wjx.gif";
		}
	 checkBoxsFalse();
	 var checkboxs=document.getElementsByName("grade");
		if(v<3){
			checkboxs[2].checked=true;
		}else if(v==3){
			checkboxs[1].checked=true;
		}else if(v>3){
			checkboxs[0].checked=true;
		}
	 }
	}
	
function changNumKeyValue(){
 	var strLength=$('#contentid').val().length;
 	$('#contentNumId').text(strLength);
 	if(strLength>500){
        ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  		ymPrompt.alert({title:'Tips',message:'Your comment is too long, please make it within 500 characters!'}) ;
        return;
    }
 }
 
function doAddComment(){
	var checkboxs=document.getElementsByName("grade");
	var f=false;
    for(i=0;i<checkboxs.length;i++){
		if(checkboxs[i].checked==true){
            f=true;
            break;
       }
    }
    if(f==false){
        ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  		ymPrompt.alert({title:'Tips',message:'Please click on the star to make comments!'}) ;
        return;
    }
    var content=$.trim($('#contentid').val());
    if(content==''||content=='0/500'){
        ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  		ymPrompt.alert({title:'Tips',message:'Please enter your comment!'}) ;
        $('#contentid').focus();
      	return;
    }
    if($('#contentid').val().length>500){
    	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  		ymPrompt.alert({title:'Tips',message:'Your comment is too long, please make it within 500 characters!'}) ;
        return;
    }
    document.myform.submit();		      
}
</script>
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> Feedback</span></h1> 

			<div class="buy_order">
							<ul class="title">Product Info</ul>
							<ul>
								<li class="buy_order01">
									<p>&nbsp;</p>
									<p class="title">Items Name</p>
									<p>Price</p>
									<p>Quantity</p>
									<p>Subtotal</p>
								</li>
								<c:foreach items="${objList}" var="obj">
								<li>
									<p><img src="${obj[0].pimage }" width="70px" height="60px"/></p>
									<p class="title">${obj[0].productName }</p>
									<p><font class="redfont fontwz">${moneyMark}${obj[1].oprice }</font></p>
									<p>${obj[1].pnum }</p>
									<p><font class="redfont fontwz">${moneyMark}<u:formatnumber value="${obj[1].pnum*obj[1].oprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></p>
								</li>
								</c:foreach>
							</ul>
							<ul class="sum">Total: ${moneyMark}<u:formatnumber value="${allAmount}"  type="number" groupingUsed="false" maxFractionDigits="2"/> - Coupon: ${moneyMark}<u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/> + Shipping Cost: ${moneyMark}<u:formatnumber value="${lvOrder.postprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></a></ul>
							<ul class="sum01">Merchandise Subtotal: <font class="redfont">${moneyMark}<u:formatnumber value="${lvOrder.totalPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></a></ul>
							<ul class="wz">
							  Please comment on merchandise itself rather than service process like transaction, delivery, etc. For 
							  any questions about service process, please contact Customer Service. Thank you for your participation!
							</ul>
			</div>
			<div class="comment">
			<form  name="myform" action="/web/userOrder!saveComment.action" method="post">
				<input type="hidden" name="lvOrderComment.score" id="scoreid"  value="0"/>
				<input type="hidden" name="lvOrderComment.orderId"   value="${lvOrder.oid}"/>
				<input type="hidden" name="lvOrderComment.contryId" value="${lvOrderAdress.contryId}">
				<input type="hidden" name="lvOrderComment.grade" id="gradeId"  value="0"/>		
				<ul>Merchandise Score</ul>
				<ul class="pf">
					<li>
						<img src="${resDomain}/tvpaden/res/images/wjx.gif" border="0" onclick="dograde(1)" onmousemove="moveStar(1)"  onmouseout="outStar()"    id="image_1"/>
						<img src="${resDomain}/tvpaden/res/images/wjx.gif" onmousemove="moveStar(2)"  onmouseout="outStar()"  border="0" onclick="dograde(2)" id="image_2"/>
						<img src="${resDomain}/tvpaden/res/images/wjx.gif" onmousemove="moveStar(3)"  onmouseout="outStar()"  onclick="dograde(3)"  id="image_3" border="0"/>
						<img src="${resDomain}/tvpaden/res/images/wjx.gif" onclick="dograde(4)" onmousemove="moveStar(4)"  onmouseout="outStar()" id="image_4"  width="30" height="29" border="0"/>
						<img src="${resDomain}/tvpaden/res/images/wjx_h.gif" onclick="dograde(5)" onmousemove="moveStar(5)"  onmouseout="outStar()" id="image_5"  width="30" height="29" border="0" />
					</li>
					<li>Please click stars to score</li>
					<li>
						<input name="grade" type="checkbox" disabled="disabled"  onclick="checkBoxValidate(0)" value="3" /> Excellent
						<input name="grade" type="checkbox"  disabled="disabled" onclick="checkBoxValidate(1)"  value="2" /> Common
						<input name="grade" type="checkbox"  disabled="disabled" onclick="checkBoxValidate(2)" value="1" /> Bad
					</li>
				</ul>
				<ul>Comment text</ul>
				<ul class="pf1">
					<li><textarea name="lvOrderComment.content" id="contentid" class="input1" onkeyup="changNumKeyValue()" onkeypress="changNumKeyValue()"    onfocus="if(this.value=='0/500')this.value=''">0/500</textarea><span id="contentNumId">0</span>/500</li>
					<li><p class="user_bt"><input name="" type="button" onclick="javascript:doAddComment();" value="Publish"  class="qrtj" /></p></li>
					
				</ul>
				</form>	
			</div>
		
	 <!--End right_Frame-->
</div>
<!--End content-->	
</div>		
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 