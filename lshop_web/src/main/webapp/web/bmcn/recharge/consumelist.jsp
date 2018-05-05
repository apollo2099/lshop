<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心_banana商城</title>
<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/bmcn/common/top.jsp"%>
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
<%@include file="/web/bmcn/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 交易记录</h1>
    <div class="usercenter_box">
      <div class="order_record">
        <form action="/web/recharge!tradeList.action" method="get">
          <p class="js"> <span><font class="fontwz">年份：</font>
            <select name="year" id="yearId" class="input04">
            </select>
            <select name="recordType" class="input04">
              <option value="1" <s:if test="recordType==1">selected="selected"</s:if>>充值记录</option>
              <option value="2" <s:if test="recordType==2">selected="selected"</s:if>>消费记录</option>
            </select>
            </span> <span>
            <input type="image" src="${resDomain}/bmcn/res/images/chaxun.jpg" />
            </span> </p>
        </form>
        <p class="js">
			<span class="je">总金额： <font class="redfont">${totalamt}</font> V币</span>.
		</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e3e2e2">
          <s:if test="recordType==1">
            <tr>
              <td width="25%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">交易流水号 </td>
              <td width="25%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">充值来源</td>
              <td width="25%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">时间</td>
              <td width="25%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">金额</td>
            </tr>
            <s:iterator value="page.list" id="item">
              <tr>
                <td width="25%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${item.tradeno}</td>
                <td width="25%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">
                  <c:choose>
					<c:when test="${item.source eq '1'}">启创网站</c:when>
					<c:when test="${item.source eq '2'}">机顶盒</c:when>
					<c:when test="${item.source eq '3'}">手动充值</c:when>
					<c:when test="${item.source eq '4'}">TVpad中文商城</c:when>
					<c:when test="${item.source eq '5'}">TVpad英文商城</c:when>
					<c:when test="${item.source eq '6'}">TVpad中文移动商城</c:when>
					<c:when test="${item.source eq '8'}">banana中文商城</c:when>
					<c:when test="${item.source eq '9'}">banana英文商城</c:when>
					<c:when test="${item.source eq '10'}">banana中文移动商城</c:when>
				  </c:choose>
                </td>
                <td width="25%" height="30" align="center" valign="middle" bgcolor="#FFFFFF"><s:property value="%{createDate.substring(0,createDate.lastIndexOf(':'))}"/></td>
                <td width="25%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${item.amt} V币</td>
              </tr>
            </s:iterator>
            <s:if test="page.list.size==0">
              <tr height="30">
                <td height="30" align="center" valign="middle" bgcolor="#FFFFFF" colspan="4" align="center">找不到相关信息</td>
              </tr>
            </s:if>
          </s:if>
          <s:elseif test="recordType==2">
            <tr>
              <td width="20%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">交易流水号</td>
              <td width="20%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">MAC码</td>
              <td width="20%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">时间</td>
              <td width="20%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">金额</td>
              <td width="20%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">备注</td>
            </tr>
            <s:iterator value="page.list" id="item">
              <tr>
                <td width="20%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${item.tradeno}</td>
                <td width="20%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${item.mac}</td>
                <td width="20%" height="30" align="center" valign="middle" bgcolor="#FFFFFF"><s:property value="%{createDate.substring(0,createDate.lastIndexOf(':'))}"/></td>
                <td width="20%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${item.amt} V币</td>
                <td width="20%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">
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
                <td height="30" align="center" valign="middle" bgcolor="#FFFFFF" colspan="5" align="center">找不到相关信息</td>
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
<%@include file="/web/bmcn/common/footer.jsp" %>
</body>
</html>
