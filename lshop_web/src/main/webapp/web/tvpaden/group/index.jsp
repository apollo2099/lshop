<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Group Buying-TVpad MALL</title>
<script type="text/javascript" src="${resDomain}/en/res/js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="${resDomain}/en/res/js/FomrValidate.js" ></script>
<script type="text/javascript" src="${resDomain}/en/res/js/jquery.validate.min.1.8.1.js" ></script>
<script type="text/javascript" src="${resDomain}/en/res/js/jquery_validate_extend.js" ></script>
<script type="text/javascript" src="${resDomain}/en/res/js/jquery.form.js" ></script>
<script type="text/javascript" src="${resDomain}/en/res/js/cookie.js"></script>
<link rel="stylesheet" type="text/css" href="${resDomain}/en/res/js/ymPrompt/skin/vista/ymPrompt.css" />
<script type="text/javascript" src="${resDomain}/en/res/js/ymPrompt/ymPrompt.js" ></script>
<script type="text/javascript" src="${resDomain}/en/res/js/header.js" ></script>
<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/bi.js"></script>
<link href="${resDomain}/en/res/tuan/css/tuan.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/en/res/css/development.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/group/css/self.css" rel="stylesheet" type="text/css" />
<style type="text/css">
/*登陆框*/
.pop_up_box li:last-child {height:38px;}
/*欢迎文字*/
#help li.line{width:180px;}
/* 店铺顶部广告位 */
.ggt{width:1002px;padding: 10px 0 0 0;height: 80px;}
</style>
<script language="javascript" src="${resDomain}/tvpaden/res/group/js/js.js"></script>
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
			$("#curTime").html("<font class='redfont fontwz'>"+tmpDay+ "</font>day(s) <font class='redfont fontwz'>"+tmpHour+ "</font>:<font class='redfont fontwz'>"+tmpMinute+ "</font>:<font class='redfont fontwz'>"+tmpSecond+ "</font>");
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
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    ymPrompt.alert({title:'Tips',message:'Qty. shall be at least 1.'}) ;
	}
	$("#num").val(nowNum);
}

function subGroup(){
	if($("#num").val()<1){
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    ymPrompt.alert({title:'Tips',message:'Qty. shall be at least 1.'}) ;
		$("#num").val(1);
		$("#num").focus();
	}else{
		var users=lshop.getCookieToJSON('user');
		if(users.uid!=null){
			$.ajax({
			   type: "POST",
			   url: "/web/group!checkGroupOrder.action",
			   data: "groupCode="+'${groupBuy.code}',
			   success: function(flag){
			   	 	if(flag==1){
						ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		    			ymPrompt.alert({title:'Tips',message:'Sorry, a user can make an order only!'}) ;
			   	 	}else{
			   	 		$("#buyForm").submit();
			   	 	}
			   	 }
			});
			
		}else{
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			ymPrompt.confirmInfo('<strong>Please login first. If you don\'t have an account yet, please register!</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
				  onshow('tx_b','loginDiv');
			  }
			if(tp=='cancel'){
				ymPrompt.close();
			  }} );
		}
	}

	
}

</script>
</head>
<body onload="setTime()">
<%@include file="/web/en/common/header.jsp" %>
		
<div class="tuan">
	<div class="tuan_top">
		<h1>${groupBuy.title }</h1>
		<div class="tuan_content">
			<div class="tuan_content_l">
					<div class="tuan_content_l_price1"><font style="font-size:18px; padding-right:5px; padding-left:5px;">USD</font><u:formatnumber value="${groupBuy.presentPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/>
					<c:if test="${mark==1}"><a href="javascript:subGroup();"><img src="${resDomain}/tvpaden/res/group/images/buy.gif" width="117" height="50" border="0" /></a></c:if>
					<c:if test="${mark==2}"><img src="${resDomain}/tvpaden/res/group/images/tuan_jjks.gif" width="117" height="50" border="0"/></c:if>
					<c:if test="${mark==3}"><img src="${resDomain}/tvpaden/res/group/images/tuan_jieshu.gif" width="117" height="50" border="0"/></c:if>
					</div>
					<div class="tuan_content_l_price2">
					<form id="buyForm" action="${storeDomain }/web/group!saveGroup.action" method="post" >
					<input type="hidden" name="lvGroupBuy.code" value="${groupBuy.code}"/>
						<ul>
							<li class="long">Retail price</li>
							<li class="long">Discount</li>
							<li >Price</li>
						</ul>
						<ul>
							<li class="long bold">USD <u:formatnumber value="${groupBuy.primeCost }"  type="number" groupingUsed="false" maxFractionDigits="2"/></li>
							<li class="bold">${groupBuy.discount*10 }%</li>
							<li class="long bold">USD <u:formatnumber value="${groupBuy.presentPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></li>
						</ul>
						<ul>Quantity 
						    <a href="javascript:changeNum('del');"><img src="${resDomain}/tvpaden/res/group/images/jian.gif" border="0"/></a>
                			<input type="text"  class="input33" id="num" name="shopNum" value="1" onkeypress="onlyNumber(event)"/>
                			<a href="javascript:changeNum('add');"><img src="${resDomain}/tvpaden/res/group/images/jia.gif" border="0"/></a>
						</ul>
						<ul>Min. group buyers ：<font class="redfont fontwz">${groupBuy.groupNum }</font><br /><font class="redfont fontwz">${groupBuy.purchasedNum } </font>bought</ul>
						<ul>
							<c:if test="${mark==1}">Limited quantity available!<br />Left time </c:if>
							<c:if test="${mark==2}">Limited quantity available!<br />Left time </c:if>
							<c:if test="${mark==3}">Limited quantity available!</c:if>
							<span id="curTime"></span>
						</ul>
						
						<p><img src="${resDomain}/tvpaden/res/group/images/tuan_01.gif"/></p>
					</form>
					</div>
			</div>
			<div class="tuan_content_r"><img src="${resDomain}/tvpaden/res/group/images/tuan.gif"/></div>
		</div>
	
	</div>

	<div class="indextj commargin1">
        <div class="product_item">
            <ul>
	              <c:foreach items="${groupPropertyList}" var="pro" varStatus="status">
	              		<li id="index${status.count}"><a href="javascript:MainItem(${status.count});">${pro.title }</a></li>
	              </c:foreach>
	              <li id="index${propertyNum+1}"><a href="javascript:MainItem(${propertyNum+1});">Comments<font class="redfont">（${commentNum}）</font></a></li>
            </ul>
      </div>	
	     <c:foreach items="${groupPropertyList}" var="pr" varStatus="status">
	       	 <div class="tjcontent" id="f_Pic${status.count}" style="display:none;">${pr.content }</div>
	      </c:foreach>	
     	<div class="tjcontent" id="f_Pic${propertyNum+1}" style="display:none;">
     	  <div class="index_comments">
              <ul class="title1">
                <li class="title_s">Buyer</li>
                <li class="title_s">Transaction Details</li>
                <li>Comments</li>
              </ul>
              <c:if test="${not empty objList}">
                 <s:iterator value="page.list" status="stat" id="obj">
              	<ul>
	              	<li class="short">${obj[1].nickname }<br /><img src="${resDomain }/tvpaden${obj[0] }" /></li>
	              	<li class="short">${obj[1].pnum } Tables<br /><s:date name="#obj[1].createTime" format="yyyy-MM-dd HH:mm:ss"/></li>
	              	<li>
	              		<c:foreach begin="1" end="${obj[1].score}">
	              			 <img src="${resDomain}/tvpaden/res/images/wjx.gif" width="30" height="29" />
	              		</c:foreach>
	              		<br />${obj[1].content }
	              	</li>
              	</ul>
              </s:iterator>
              <c:if test="${page.totalCount>page.numPerPage}">
					<ul class="page">
						All<span style="color:#f00">${page.totalCount}</span>records&nbsp;
						<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage(1);">Home</a></c:if><c:if test="${page.pageNum<=1}">Home</c:if></span>&nbsp;
						<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage('${page.prePage}');">Pre</a></c:if><c:if test="${page.pageNum<=1}">Pre</c:if></span>&nbsp;
						<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.nextPage}');">Next</a></c:if><c:if test="${page.totalPage<=page.pageNum}">Next</c:if></span>&nbsp;
						<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.totalPage}');">Last Page</a></c:if><c:if test="${page.totalPage<=page.pageNum}">Last Page</c:if></span>&nbsp;
						<span style="margin-left:10px">Page: <span style="color:#f00">${page.pageNum}</span> / ${page.totalPage}</span>&nbsp;
						<span style="margin-left:10px"><span style="color:red">${fn:length(page.list)}</span>Per Page</span>&nbsp;
						<span style="margin-left:10px">Go to
							<select onchange='gotoPage(this.options[this.selectedIndex].value)'>
								<c:foreach var="i" begin="1" end="${page.totalPage}">
									<option  value="${i}" <c:if test='${i==page.pageNum}'>selected="selected"</c:if>>${i}</option>
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
               <font color="red">No comment, welcome to make the first comment!</font>
               </c:if>
         	</div>
     </div>
	</div>				
					
				
</div>			

</div>

<!-- footer-->
<%@include file="/web/en/common/footer.jsp" %>

</body>
</html> 
<script type="text/javascript">
$().ready(function() {
	if(${pageMark}==1){
		MainItem(${propertyNum+1});
	}else{
		MainItem(1);
	}
});	
</script>
