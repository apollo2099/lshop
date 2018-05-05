<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>	
<title>banana商城_账户余额</title>
<style type="text/css">
	.country{ position:relative;left:1px;top:0px;z-index:999; display: none;}

	.fenye{width:100%; height: 6px;margin:15px 0px 0px 5px;}
	.fenye ul.fy {float:left; display:block; width:auto; overflow:hidden;}
	.fenye li{ width:22px; height:24px; text-align:center; display:block; float:left; margin:0 3px 0 0;background-color:#fff;}
	.fenye li.zd{ width:auto; height:22px; text-align:center; display:block; float:left; margin:0 3px 0 0;}
	.fenye li a{ width:22px; height:22px; border:1px #d2dce6 solid; display:block;}
	.fenye li a:hover{ width:22px; height:22px; background-color:#177dc7; border:1px #d2dce6 solid; color:#ffffff; display:block;}
	.fenye li.fy_dq a{ height:22px; background-color:#177dc7; border:1px #d2dce6 solid; color:#ffffff; display:block;}
	.fenye li.sf{ width:22px; height:22px;background-color:#fff;}
	.fenye li.sf a{ width:22px; height:22px; border:1px #d2dce6 solid; display:block;}
	.fenye li.sf a:hover{ width:22px; height:22px; background-color:#177dc7; border:1px #d2dce6 solid; color:#ffffff; display:block;}
	.pro_bd{ width:22px; height:22px;border:1px #cecece solid; text-align:center; overflow:hidden;}
	.fenye p{ float:left; padding:0 0 0 5px;font-size: small;}
	.jumpto{display: none;}
	.button03{width:26px; height:24px; color:#666666; text-align:center; border:none; line-height:24px; overflow:hidden; background-color:#ececec; border:1px #d2dce6 solid;}
</style>
</head>

<script type="text/javascript">
$(function(){
	$("#ordertime").mouseleave(function(){
		$('#country').css("display","none");
	});
	
	var year=${requestScope.dateYear}; //开始年份
	var str="<ul>";
	for(var i=year;i>=2008;i--){
		str+="<li onClick='queryAction("+i+")'>"+i+"</li>";
	}
	str = str + "</ul>";
	$("#country").html(str);
});

function queryAction(y){
location.href="/web/recharge!list.action?year="+y;
}

function showhide(){
	$('#country').slideDown('fast');
}

</script>
<body>
<%@include file="/web/mbmcn/user_center/c_top.jsp"%>

<article>
  
  <div class="account_ban">
  <s:if test="#request.isOrNOLoginFlag!=0">
    <div class="on_Balance">帐户余额：<span style="color:#ff0000">${userBean.balance} V币</span></div>
   </s:if>
   <div class="vcstyle">请选择充值方式</div>
    <div class="recharge">
      <a href="/web/recharge!card.action">充值卡充值</a>
      <span>通过填写充值卡卡号和密码充值V币</span>
    </div>
    
    <div class="recharge">
      <a href="/web/recharge!online.action">在线充值</a>
      <span>通过在线支付充值V币</span>
    </div>
  </div>
   <s:if test="#request.isOrNOLoginFlag!=0">
<div style="width:94%; margin:0 auto">
	<div class="orderrecord">
	充值订单记录：
	</div>
	<div id="ordertime" style="position:absolute;margin-left: 100px;">
	<span class="spanchose" onClick="showhide()" id="spanchos">${year}</span>
	<div class="country" id="country">
	</div>
	</div>
</div>
     
<table width="94%" border="0" align="center" cellspacing="1" class="records">    
  <tr>
    <th height="50" align="center" valign="middle" bgcolor="#dddddd">订单号</th>
    <th height="50" align="center" valign="middle" bgcolor="#dddddd">时间</th>
    <th height="50" align="center" valign="middle" bgcolor="#dddddd">充值金额</th>
    <th height="50" align="center" valign="middle" bgcolor="#dddddd">状态</th>
    <th height="50" align="center" valign="middle" bgcolor="#dddddd">操作</th>
  </tr>
  
  <s:iterator value="page.list" id="p">
	  <tr>
	    <td height="50" align="center" valign="middle" bgcolor="#FFFFFF">${p.rnum}</td>
	    <td height="50" align="center" valign="middle" bgcolor="#FFFFFF"><s:date name="createDate" format="yyyy-MM-dd HH:mm"/></td>
	    <td height="50" align="center" valign="middle" bgcolor="#FFFFFF" class="balance">${p.currency} ${p.money}</td>
	    <td height="50" align="center" valign="middle" bgcolor="#FFFFFF">
	    	<s:if test="#p.status==1&#p.payStatus==1">已充值成功</s:if>
		    <s:elseif test="#p.status==0&#p.payStatus==1">受理中</s:elseif>
		    <s:else>
		    <s:if test="#p.payStatus==0&&#p.status==0">未支付</s:if>
		    <s:elseif test="#p.status==3">已取消</s:elseif>
		    </s:else>
	    </td>
	    <td height="50" align="center" valign="middle" bgcolor="#FFFFFF">
	    	<s:if test="#p.payStatus==0&&#p.status==0">
           		<a href="/web/recharge!trueOrder.action?rnum=${p.rnum}" style="color:#005aa0;">支付</a>
           	</s:if>
	    </td>
	  </tr>
  </s:iterator>
  <s:if test="page.list.size==0">
  	  <tr height="30" bgcolor="#FFFFFF"><td colspan="5" align="center">找不到相关信息</td></tr>
  </s:if>
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
<div class="fenye">
</div>
</s:if>
</article>

<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>

<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '帐户余额';
</script>
</body>
</html>
