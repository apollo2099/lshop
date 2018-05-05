<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>banana</title>
<link href="${resDomain}/bscn/res/group/css/self.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/bscn/res/js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="${resDomain}/bscn/res/js/FomrValidate.js" ></script>
<script type="text/javascript" src="${resDomain}/bscn/res/js/jquery.validate.min.1.8.1.js" ></script>
<script type="text/javascript" src="${resDomain}/bscn/res/js/jquery_validate_extend.js" ></script>
<script type="text/javascript" src="${resDomain}/bscn/res/js/jquery.form.js" ></script>
<script type="text/javascript" src="${resDomain}/bscn/res/js/cookie.js"></script>
<script type="text/javascript" src="${resDomain}/bscn/res/js/header.js" ></script>
<script type="text/javascript" src="${resDomain}/bscn/res/js/regandlog.js"></script>
<script type="text/javascript">
<!--
var isIE = false;
var isFF = false;
var isSa = false;

if ((navigator.userAgent.indexOf("MSIE")>0) && (parseInt(navigator.appVersion) >=4))isIE = true;
if(navigator.userAgent.indexOf("Firefox")>0)isFF = true;
if(navigator.userAgent.indexOf("Safari")>0)isSa = true; 

function onlyNumber(e)
{
    var key;
    iKeyCode = window.event?e.keyCode:e.which;
    if( !(((iKeyCode >= 48) && (iKeyCode <= 57)) || (iKeyCode == 13) || (iKeyCode == 46) || (iKeyCode == 45) || (iKeyCode == 37) || (iKeyCode == 39) || (iKeyCode == 8)))
    {    
        if (isIE)
        {
            e.returnValue=false;
        }
        else
        {
            e.preventDefault();
        }
    }
} 
//-->

var t=0;
var tmpInt;
if(${not empty beforeTime}){
	tmpInt=parseInt('${beforeTime}')/1000;
}else{
	tmpInt=parseInt('${afterTime}')/1000;
}
function setTime(){
	if(parseInt('${mark}')!=3){
		var tmpDay,tmpHour,tmpMinute,tmpSecond; 
	    tmpDay=parseInt(tmpInt/(24*60*60));
	    tmpHour=parseInt((tmpInt%(24*60*60))/(60*60)); 
	    tmpMinute=parseInt((tmpInt%(24*60*60)%(60*60))/60); 
	    tmpSecond=parseInt((tmpInt%(24*60*60)%(60*60))%60);  
	    if(tmpInt>0&&tmpDay>=0&&tmpHour>=0&&tmpMinute>=0&&tmpSecond>=0){
	    	t=1;
			$("#curTime").html("<font class='redfont fontwz'>"+tmpDay+ "</font>天<font class='redfont fontwz'>"+tmpHour+ "</font>小时<font class='redfont fontwz'>"+tmpMinute+ "</font>分<font class='redfont fontwz'>"+tmpSecond+ "</font>秒"); 
			tmpInt=tmpInt-1;
			setTimeout('setTime()',1000);
	    }else{
	    	if(t!=0){
	    		window.location.reload(true); 
	    	}
		}
	}
}

function changeNum(obj){
	var nowNum=0;
	var num=$("#num").val();
	if(obj=="add"){
		nowNum=Number(num)+1;
	}else if(obj=="del"&&num>1){
		nowNum=Number(num)-1;
	}else{
		nowNum=Number(num);
		alert("数量不能小于1！");
	}
	$("#num").val(nowNum);
}

function subGroup(){
	var users=lshop.getCookieToJSON('user');
	if(users.uid!=null){
		$.ajax({
		   type: "POST",
		   url: "/web/group!checkGroupOrder.action",
		   data: "groupCode="+'${groupBuy.code}',
		   success: function(flag){
		   	 	if(flag==1){
					alert("很抱歉，一个用户只能下一个订单！");
		   	 	}else{
		   	 		$("#buyForm").submit();
		   	 	}
		   	 }
		});
		
	}else{
		alert("您还未登录，请先登录！如果您没有登录账户，请先注册！");
		onshow('tx_b','loginDiv');
	}
	
}

</script>



</head>
<body onload="setTime()">

<!--end top-->

<%@include file="/web/bscn/common/header.jsp" %>
<!--end menu-->



<div class="tuan">
	<div class="tuan_top">
		<h1>${groupBuy.title }</h1>
		<div class="tuan_content">
			<div class="tuan_content_l">
					<div class="tuan_content_l_price1"><font style="font-size:18px; padding-right:5px; padding-left:5px; ">${lvStore.currency} </font><u:formatnumber value="${groupBuy.presentPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/>
					<c:if test="${mark==1}"><a href="javascript:subGroup();"><img src="${resDomain}/bscn/res/group/images/buy.gif" width="117" height="50" border="0" /></a></c:if>
					<c:if test="${mark==2}"><img src="${resDomain}/bscn/res/group/images/tuan_jjks.gif" width="117" height="50" border="0"/></c:if>
					<c:if test="${mark==3}"><img src="${resDomain}/bscn/res/group/images/tuan_jieshu.gif" width="117" height="50" border="0"/></c:if>
					</div>
						<form id="buyForm" action="${storeDomain }/web/group!saveGroup.action" method="post" >
						<input type="hidden" name="lvGroupBuy.code" value="${groupBuy.code}"/>
					<div class="tuan_content_l_price2">
						<ul>
							<li  class="long">原价</li>
							<li>折扣</li>
							<li  class="long">现价</li>
						</ul>
						<ul>
							<li class="bold long">${lvStore.currency} <u:formatnumber value="${groupBuy.primeCost }"  type="number" groupingUsed="false" maxFractionDigits="2"/></li>
							<li class="bold">${groupBuy.discount }折</li>
							<li class="bold long">${lvStore.currency} <u:formatnumber value="${groupBuy.presentPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></li>
						</ul>
						<ul>我要买
						  <a href="javascript:changeNum('del');"><img src="${resDomain}/bscn/res/group/images/jian.gif" border="0"/></a>
                			<input type="text"  class="input3" id="num" name="shopNum" value="1" onkeypress="onlyNumber(event)"/>
                			<a href="javascript:changeNum('add');"><img src="${resDomain}/bscn/res/group/images/jia.gif" border="0"/></a>
						</ul>
						<ul>最低参团人数：<font class="redfont fontwz">${groupBuy.groupNum }</font><br /><font class="redfont fontwz">${groupBuy.purchasedNum } </font>人已购买</ul>
						<ul>
							<c:if test="${mark==1}">数量有限，下手要快哦！<br />本团购剩余：</c:if>
							<c:if test="${mark==2}">数量有限，下手要快哦！<br />距团购开始：</c:if>
							<c:if test="${mark==3}">谢谢您的关注，本团购已结束！</c:if>
							<span id="curTime"></span>
						</ul>
						
						<p><img src="${resDomain}/bscn/res/group/images/tuan_01.gif" /></p>
						</from>
					</div>
			</div>
			<div class="tuan_content_r"><img src="${groupBuy.gimage }"  /></div>
		</div>
	
	</div>

	<div class="indextj commargin1">
        <div class="product_item">
            <ul>
               <c:foreach items="${groupPropertyList}" var="pro" varStatus="status">
	              		<li id="index${status.count}" <c:if test="${status.count==1 }">class="choose"</c:if>><a href="javascript:MainItem(${status.count});">${pro.title }</a></li>
	           </c:foreach>
	           <li id="index${propertyNum+1}" <c:if test="${propertyNum==0}">class="choose"</c:if>><a href="javascript:MainItem(${propertyNum+1});">用户评价<font class="redfont">（${commentNum}）</font></a></li>
            </ul>
      	</div>
				
		 <c:foreach items="${groupPropertyList}" var="pr" varStatus="status">
	       	 <div class="tjcontent" id="f_Pic${status.count}" <c:if test="${status.count!=1 }">style="display:none;"</c:if>>${pr.content }</div>
	      </c:foreach>	
     	<div class="tjcontent" id="f_Pic${propertyNum+1}" <c:if test="${propertyNum>0}">style="display:none;"</c:if>>
     	  <div class="index_comments">
              <ul class="title1">
                <li class="title_s">买 家</li>
                <li class="title_s">交易详情</li>
                <li>评 价</li>
              </ul>
              <c:if test="${not empty objList}">
               <s:iterator value="page.list" status="stat" id="obj">
              	<ul>
	              	<li class="short">${obj[1].nickname }<br /><img src="${resDomain }/bscn${obj[0] }" /></li>
	              	<li class="short">${obj[1].pnum }台<br /><s:date name="#obj[1].createTime" format="yyyy-MM-dd HH:mm:ss"/></li>
	              	<li>
	              		<c:foreach begin="1" end="${obj[1].score}">
	              			 <img src="${resDomain}/bscn/res/images/wjx.gif" width="30" height="29" />
	              		</c:foreach>
	              		<br />${obj[1].content }
	              	</li>
              	</ul>
              </s:iterator>
              <c:if test="${page.totalCount>page.numPerPage}">
					<ul class="page">
						共有<span style="color:#f00">${page.totalCount}</span>条记录&nbsp;
						<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage(1);">首页</a></c:if><c:if test="${page.pageNum<=1}">首页</c:if></span>&nbsp;
						<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage('${page.prePage}');">上一页</a></c:if><c:if test="${page.pageNum<=1}">上一页</c:if></span>&nbsp;
						<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.nextPage}');">下一页</a></c:if><c:if test="${page.totalPage<=page.pageNum}">下一页</c:if></span>&nbsp;
						<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.totalPage}');">尾页</a></c:if><c:if test="${page.totalPage<=page.pageNum}">尾页</c:if></span>&nbsp;
						<span style="margin-left:10px">页次: <span style="color:#f00">${page.pageNum}</span> / ${page.totalPage}</span>&nbsp;
						<span style="margin-left:10px">本页有<span style="color:red">${fn:length(page.list)}</span>条记录</span>&nbsp;
						<span style="margin-left:10px">跳转到
							<select onchange='gotoPage(this.options[this.selectedIndex].value)'>
								<c:foreach var="i" begin="1" end="${page.totalPage}">
									<option  value="${i}" <c:if test='${i==page.pageNum}'>selected="selected"</c:if>>第${i}页</option>
								</c:foreach>
							</select>
						</span>
					  <!--<u:page href="/web/group!toGroupBuy.action?code=${groupBuy.code}&page.pageNum=@" language="cn">
					  </u:page>-->
					   <script type="text/javascript">
						<!--
						function gotoPage(num){
						   window.location.href="/web/group!toGroupBuy.action?code=${groupBuy.code}&page.pageNum="+num+"&pageMark=1"+"#index${propertyNum+1}";
						
						}
						//-->
						</script>
					</ul>	
					</c:if>
               </c:if>
               <c:if test="${empty objList}">
               <font color="red">暂无评价，欢迎您第一个留言！</font>
               </c:if>
         	</div>					
		</div>			
	</div>
</div>




<%@include file="/web/bscn/common/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
$().ready(function() {
	if(parseInt('${pageMark}')==1){
		MainItem(${propertyNum+1});
	}else{
		MainItem(1);
	}
});	
</script>