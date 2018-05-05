<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>	
<title>banana商城_交易记录</title>
<style type="text/css">
	.country{ position:relative;left:1px;top:0px;z-index:999; display: none;text-align: center;}

	.fenye{width:94%; height: 6px;margin:15px 0px 0px 22px;}
	.fenye ul.fy {float:left; display:block; width:auto; overflow:hidden;}
	.fenye li{ width:28px; height:24px; text-align:center; display:block; float:left; margin:0 3px 0 0;background-color:#fff;}
	.fenye li.zd{ width:auto; height:22px; text-align:center; display:block; float:left; margin:0 3px 0 0;}
	.fenye li a{ width:28px; height:22px; border:1px #d2dce6 solid; display:block;}
	.fenye li a:hover{ width:28px; height:22px; background-color:#177dc7; border:1px #d2dce6 solid; color:#ffffff; display:block;}
	.fenye li.fy_dq a{ height:22px; background-color:#177dc7; border:1px #d2dce6 solid; color:#ffffff; display:block;}
	.fenye li.sf{ width:28px; height:22px;background-color:#fff;}
	.fenye li.sf a{ width:28px; height:22px; border:1px #d2dce6 solid; display:block;}
	.fenye li.sf a:hover{ width:28px; height:22px; background-color:#177dc7; border:1px #d2dce6 solid; color:#ffffff; display:block;}
	.pro_bd{ width:28px; height:22px;border:1px #cecece solid; text-align:center; overflow:hidden;}
	.fenye p{ float:left; padding:0 0 0 5px;}
	.button03{width:28px; height:24px; color:#666666; text-align:center; border:none; line-height:28px; overflow:hidden; background-color:#ececec; border:1px #d2dce6 solid;}
</style>
<script type="text/javascript">
$(function(){
	$("#ordertime").mouseleave(function(){
		$('#country').css("display","none");
	});
	$("#orderRecode").mouseleave(function(){
		$('#recodeType').css("display","none");
	});
	
	var year=${requestScope.dateYear}; //开始年份
	var str="<ul>";
	for(var i=year;i>=2008;i--){
		str+="<li onClick='queryAction("+i+",1)'>"+i+"</li>";
	}
	str = str + "</ul>";
	$("#country").html(str);
});

function queryAction(param,type){
	if(type==1){
		var t = $.trim($("#currentType").val());
		location.href="/web/recharge!tradeList.action?recordType="+t+"&year="+param+"&page.pageNum="+1;
	}
	if(type==2){
		var y = $.trim($("#currentYear").val());
		location.href="/web/recharge!tradeList.action?recordType="+param+"&year="+y+"&page.pageNum="+1;
	}
}

function showhide(id){
	$('#'+id).slideDown('fast');
}
</script>
</head>
<body>
<%@include file="/web/mbmcn/user_center/c_top.jsp"%>

<article>
<div style="width:94%; margin:0 auto">
	<div class="orderrecord" style="font-size:16px"> 年份：</div>
	<div id="ordertime" style="position:absolute;margin-left: 50px;">
		<input type="hidden" value="${year}" id="currentYear">
		<span class="spanchose" onClick="showhide('country')" id="spanchos">${year}</span>
        <div class="country" id="country">
        </div>
	</div>
	
	<div id="orderRecode" style="position:absolute;width:100px; margin-left:150px;margin-top: 20px;">
		<span class="spanchose" onClick="showhide('recodeType')" id="spanchos">
		<input type="hidden" value="${recordType}" id="currentType">
			<s:if test="recordType==1">&nbsp;&nbsp;充值记录</s:if>
			<s:if test="recordType==2">&nbsp;&nbsp;消费记录</s:if>
		</span>
		<div class="country" id="recodeType" style="font-size:14px;">
			<ul>
				<li style="height: 26px;" onclick="queryAction(1,2)">充值记录</li>
				<li style="height: 27px;" onclick="queryAction(2,2)">消费记录</li>
			</ul>
		</div>
	</div>
</div>
    
    
<div class="clear"></div>   
<div class="on_Balance1" style="padding-bottom:20px">总金额：<span style="color:#ff0000">${totalamt} V币</span></div>

	<table width="94%" border="0" align="center" cellspacing="1" class="records">
 	<s:if test="recordType==1">
		<tr>
			<td width="25%" height="30" align="center" valign="middle" bgcolor="#dddddd">交易流水号 </td>
			<td width="25%" height="30" align="center" valign="middle" bgcolor="#dddddd">充值来源</td>
			<td width="25%" height="30" align="center" valign="middle" bgcolor="#dddddd">时间</td>
			<td width="25%" height="30" align="center" valign="middle" bgcolor="#dddddd">金额</td>
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

<div class="fenye">
	<s:if test="page.totalPage>1">
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
<div class="fenye">
</div>
</article>

<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '交易记录';
</script>
</body>
</html>
