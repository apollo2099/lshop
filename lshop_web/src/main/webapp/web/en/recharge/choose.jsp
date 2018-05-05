<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center_TVpad Mall</title>
<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/en/common/top.jsp"%>
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

function queryAction(f){
location.href="/web/recharge!list.action?year="+f.value;
}
</script>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/en/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/en/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><span class="s_r"><font class="bfont"><img
								src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a
							href="/index.html">Home</a> --><a
							href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Account Balance</span></h1>
    <div class="usercenter_box">
      <div class="balance">
      <s:if test="#request.isOrNOLoginFlag!=0">
        <p>Account Balance：<font class="redfont">${userBean.balance} V</font></p>
        </s:if>
        <ul>
          <li> <a href="/web/recharge!card.action"><img src="${resDomain}/en/res/images/cz01-.gif" border="0" /></a><span>Recharge V coin by filling in the recharge card No. and password
</span> </li>
          <li> <a href="/web/recharge!online.action"><img src="${resDomain}/en/res/images/cz02-.gif" border="0" /></a><span>Recharge V coin by online payment</span> </li>
        </ul>
      </div>
      <s:if test="#request.isOrNOLoginFlag!=0">
      <div class="order_record">
        <p><font class="fontwz">Recharge records：</font>
          <select id="yearId" onchange="queryAction(this)" size="1" class="input04"></select>
        </p>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e3e2e2">
          <tr>
            <td width="35%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Order No.</td>
            <td width="25%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Time</td>
            <td width="15%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Amount</td>
            <td width="15%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Status</td>
            <td width="10%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Operation</td>
          </tr>
          <s:iterator value="page.list" id="p">
          <tr>
            <td width="35%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${p.rnum}</td>
            <td width="25%" height="30" align="center" valign="middle" bgcolor="#FFFFFF"><s:date name="createDate" format="yyyy-MM-dd HH:mm"/></td>
            <td width="15%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${p.currency} ${p.money}</td>
            <td width="15%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">
            	<s:if test="#p.status==1&#p.payStatus==1">succeeded</s:if>
			    <s:elseif test="#p.status==0&#p.payStatus==1">processing</s:elseif>
			    <s:else>
			    <s:if test="#p.payStatus==0&&#p.status==0">unpaid</s:if>
			    <s:elseif test="#p.status==3">canceled</s:elseif>
			    </s:else>
            </td>
            <td width="10%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">
            	<s:if test="#p.payStatus==0&&#p.status==0">
            		<font class="bluefont"><a href="/web/recharge!trueOrder.action?rnum=${p.rnum}">Pay</a></font>
            	</s:if>
            </td>
          </tr>
          </s:iterator>
          <s:if test="page.list.size==0"><tr height="30" bgcolor="#FFFFFF"><td colspan="5" align="center">No related info found</td></tr></s:if>
        </table>
        <div class="fenye">
          <s:if test="page.totalPage>1">
		  <u:newPage href="/web/recharge!list.action?year=${year}&page.pageNum=@" language="en"></u:newPage>
		  <script type="text/javascript">
				function toPage(){
				   var pageValue=$.trim($("#pageValue").val());
				   if(pageValue!=""&&/^[0-9]*$/.test(pageValue)){
				   window.location.href="/web/recharge!list.action?year=${year}&page.pageNum="+pageValue;
				   }
				}
		  </script>
		  </s:if>
        </div>
      </div>
      </s:if>
      <div class="cb"></div>
    </div>
  </div>
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html>
