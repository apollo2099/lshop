<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
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
});

function queryAction(f){
location.href="/web/recharge!list.action?year="+f.value;
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
    <h1><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 账户余额</h1>
    <div class="usercenter_box">
      <div class="balance">
      <s:if test="#request.isOrNOLoginFlag!=0">
        <p>账户余额：<font class="redfont">${userBean.balance} V币</font></p>
      </s:if>
        <ul>
          <li> <a href="/web/recharge!card.action"><img src="${resDomain}/bmcn/res/images/cz01-.gif" border="0" /></a><span>通过填写充值卡卡号和密码充值V币</span> </li>
          <li> <a href="/web/recharge!online.action"><img src="${resDomain}/bmcn/res/images/cz02-.gif" border="0" /></a><span>通过在线支付充值V币</span> </li>
        </ul>
      </div>
      <s:if test="#request.isOrNOLoginFlag!=0">
      <div class="order_record">
        <p><font class="fontwz">充值订单记录：</font>
          <select id="yearId" onchange="queryAction(this)" size="1" class="input04"></select>
        </p>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e3e2e2">
          <tr>
            <td width="35%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">订单号</td>
            <td width="25%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">时间</td>
            <td width="15%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">充值金额</td>
            <td width="15%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">状态</td>
            <td width="10%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">操作</td>
          </tr>
          <s:iterator value="page.list" id="p">
          <tr>
            <td width="35%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${p.rnum}</td>
            <td width="25%" height="30" align="center" valign="middle" bgcolor="#FFFFFF"><s:date name="createDate" format="yyyy-MM-dd HH:mm"/></td>
            <td width="15%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">${p.currency} ${p.money}</td>
            <td width="15%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">
            	<s:if test="#p.status==1&#p.payStatus==1">已充值成功</s:if>
			    <s:elseif test="#p.status==0&#p.payStatus==1">受理中</s:elseif>
			    <s:else>
			    <s:if test="#p.payStatus==0&&#p.status==0">未支付</s:if>
			    <s:elseif test="#p.status==3">已取消</s:elseif>
			    </s:else>
            </td>
            <td width="10%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">
            	<s:if test="#p.payStatus==0&&#p.status==0">
            		<font class="bluefont"><a href="/web/recharge!trueOrder.action?rnum=${p.rnum}">支付</a></font>
            	</s:if>
            </td>
          </tr>
          </s:iterator>
          <s:if test="page.list.size==0"><tr height="30" bgcolor="#FFFFFF"><td colspan="5" align="center">找不到相关信息</td></tr></s:if>
        </table>
        <div class="fenye">
          <s:if test="page.totalPage>1">
		  <u:newPage href="/web/recharge!list.action?year=${year}&page.pageNum=@"></u:newPage>
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
<%@include file="/web/bmcn/common/footer.jsp" %>
</body>
</html>
