<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_TVpad商城</title>
<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp"%>
<script type="text/javascript">
$(function(){
var year=${requestScope.dateYear}; //开始年份
var str="";
for(var i=year;i>=2008;i--){
 if(i=="${year}")
 str+="<option value='"+i+"' selected='selected'>"+i+"</option>";
 else
 str+="<option value='"+i+"'>"+i+"</option>";
}
$("#yearId").html(str);
})

function show(id,valstr){
     var W=$(document).width();
     var H=$(document).height();
 	  $("#remarkId").text(valstr);
     var mask=document.getElementById("mask");
     mask.style.cssText="position:absolute;z-index:5;width:"+W+"px;height:"+H+"px;background:#000;filter:alpha(opacity=30);opacity:0.3;top:0;left:0;";
     $("#mask").show();
	  var tx_b=document.getElementById(id);
	    tx_b.style.left=(window.screen.width/2-250)+"px";
	    tx_b.style.top=(300+document.documentElement.scrollTop||document.body.scrollTop)+'px';
	    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
	    	//你是使用IE
	    }else if (navigator.userAgent.indexOf('Firefox') >= 0){
	    	//你是使用Firefox
	    }else if (navigator.userAgent.indexOf('Opera') >= 0){
	    	//你是使用Opera
	    }
	    $(tx_b).fadeIn("fast",function(){});
	   	document.getElementById("#"+id).style.height=document.body.clientHeight+"px";
	    document.getElementById("#"+id).style.width=document.body.clientWidth+"px";
	
	$("#"+id).show();
}
function hide(id){
	$("#"+id).hide();
	$("#mask").hide();
}
</script>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/www/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a> --><a href="/web/userCenterManage!getAccount.action"> 用戶中心</a> --><a>交易記錄</a></font> </h1>
    <div class="usercenter_box">
      <div class="order_record">
        <form action="/web/recharge!tradeList.action" method="get">
          <p class="js" style="margin-top:0px;"> <span><font class="fontwz">年份：</font>
            <select name="year" id="yearId" class="input4">
            </select>
            <select name="recordType" class="input4">
              <option value="1" <s:if test="recordType==1">selected="selected"</s:if>>充值記錄</option>
              <option value="2" <s:if test="recordType==2">selected="selected"</s:if>>消費記錄</option>
            </select>
            </span> <span>
            <input type="image" src="${resDomain}/www/res/images/chaxun.jpg" />
            </span> </p>
        </form>
        <p class="js">
			<span class="je">總金額： <font class="redfont">${totalamt}</font> V幣</span>
		</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e3e2e2" class="w_rechargeTable">
            <s:if test="recordType==1">
                <!-- <tr>
                    <td height="35" colspan="4" bgcolor="#FFFFFF" style="text-align:right; padding-right:20px;">充值總金額：${totalamt} V币</td>
                </tr> -->
                <tr bgcolor="#f2f1f1">
                    <td width="25%" height="30">交易流水號 </td>
                    <td width="25%" height="30">充值來源</td>
                    <td width="25%" height="30">時間</td>
                    <td width="25%"  height="30">金額</td>
                </tr>
                <s:iterator value="page.list" id="item">
                    <tr>
                        <td height="30" bgcolor="#FFFFFF">${item.tradeno}</td>
                        <td height="30" bgcolor="#FFFFFF">
                            <c:choose>
								<c:when test="${item.source eq '1'}">啟創網站</c:when>
								<c:when test="${item.source eq '2'}">機頂盒</c:when>
								<c:when test="${item.source eq '3'}">手動充值</c:when>
								<c:when test="${item.source eq '4'}">TVpad中文商城</c:when>
								<c:when test="${item.source eq '5'}">TVpad英文商城</c:when>
								<c:when test="${item.source eq '6'}">TVpad中文移動商城</c:when>
								<c:when test="${item.source eq '8'}">banana中文商城</c:when>
								<c:when test="${item.source eq '9'}">banana英文商城</c:when>
								<c:when test="${item.source eq '10'}">banana中文移動商城</c:when>
							 </c:choose>
                        </td>
                        <td height="30" bgcolor="#FFFFFF">
                            <s:property value="%{createDate.substring(0,createDate.lastIndexOf(':'))}"/>
                        </td>
                        <td height="30" bgcolor="#FFFFFF">${item.amt} V幣</td>
                    </tr>
                </s:iterator>
                <s:if test="page.list.size==0">
                    <tr height="30" bgcolor="#FFFFFF">
                        <td colspan="4" align="center">找不到相關信息</td>
                    </tr>
                </s:if>
            </s:if>
            <s:elseif test="recordType==2">
            <tr>
              <td height="35" colspan="5" bgcolor="#FFFFFF" style="text-align:right; padding-right:20px;">消費總金額：${totalamt} V幣</td>
            </tr>
            <tr>
              <td height="45" width="275" bgcolor="#f6f6f5">交易流水號</td>
              <td height="45" bgcolor="#f6f6f5" width="268">MAC碼</td>
              <td width="158" height="45" width="150" bgcolor="#f6f6f5">時間</td>
              <td width="95" height="45" bgcolor="#f6f6f5">金額</td>
              <td width="268" height="45" bgcolor="#f6f6f5">備註></td>
            </tr>
            <s:iterator value="page.list" id="item">
              <tr>
                <td height="30" bgcolor="#FFFFFF">${item.tradeno}</td>
                <td height="30" bgcolor="#FFFFFF">${item.mac}</td>
                <td height="30" bgcolor="#FFFFFF"><s:property value="%{createDate.substring(0,createDate.lastIndexOf(':'))}"/></td>
                <td height="30" bgcolor="#FFFFFF">${item.amt} V幣</td>
                <td height="30" bgcolor="#FFFFFF" align="left">
                <s:if test="#item.remark.length()<20">${item.remark}</s:if>
                <s:else>
	                <span onclick="show('win_1','<s:property value="remark" default="false"/>')" style="color:blue;">
	                	<s:property value="remark.substring(0,19)"/>...
	                </span>
                </s:else>
                </td>
              </tr>
            </s:iterator>
            <s:if test="page.list.size==0">
              <tr height="30" bgcolor="#FFFFFF">
                <td colspan="5" align="center">找不到相關信息</td>
              </tr>
            </s:if>
          </s:elseif>
        </table>
        <s:if test="page.totalPage>-1">
		  <u:newPage href="/web/recharge!tradeList.action?recordType=${recordType}&year=${year}&page.pageNum=@"></u:newPage>
		  <script type="text/javascript">
				function toPage(){
					var pageValue=$.trim($("#pageValue").val());
				   if(pageValue!=""&&/^[0-9]*$/.test(pageValue)){
				   window.location.href="/web/recharge!tradeList.action?recordType=${recordType}&year=${year}&page.pageNum="+pageValue;
				   }
				}
		  </script>
	   </s:if>
      </div>
      <div class="cb"></div>
    </div>
  </div>
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
