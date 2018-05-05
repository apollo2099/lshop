<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>BananaTV商城  ${model.title}</title>
<%
String timestr=System.currentTimeMillis()+"";
%>
<link href="${resRoot}/css/${model.cssName}.css?id=${timestr}" type="text/css"
	rel="stylesheet" />
	<script src="${resRoot}/js/${model.cssName}_view.js?id=${timestr}" type="text/javascript"></script>
	
</head>
<body>
	 <div class="above" id="objAbove">
            <div class="move" id="move1" style="top:0px;">
                <div id="objIcon" class="icon">
                    
                    <img id="objAboveImg"
                     <c:if test="${empty model.imgUrl}">
                         src="${resRoot}/images/aboveTop.png" 
                     </c:if>
                     <c:if test="${not empty model.imgUrl}">
                          src="${resDomain}/mbmcn/res/card/uploadimg/${model.imgUrl}" 
                     </c:if>
                     />
                     <!--<cite id="objCite" style="margin-left:24%;margin-top:44%;">+添加图片</cite>-->
                </div>
            </div>
            <div class="move" id="move2" style="top:0px;">
                <div id="tInterval" class="interval"></div>
                <div class="content clearfix" id="objContent" style="padding-left:11%;padding-right:11%;">
                    <p id="recipient-person">${model.recipient}  
                    </p>
                    <ul id="sentiment-all" class="sentimentAll">
					<li style="text-align:left;" ><span id="objGreet" style="width: auto; min-width: 10px; display: inline; font-family:黑体;">祝福语</span></li>
                    </ul>
                    <p id="sender-person" class="senderPerson">${model.sender}</p>
                </div>
                <div id="bInterval" class="interval"></div>
            </div>
        </div>
        <div class="move1" id="move3" >
            <div class="bgBottom1" id="objBgBottom">
                <img src="${resRoot}/images/aboveBottom.png" />
                <input type="button" id="objSub" value="" />
            </div>
        </div>
        <div class="below" id="objBelow">
            <div id="objImg" class="objImg" style="position:absolute;top:0px;left:0px;">
            </div>
        </div>
        
        <div id="prompt" class="prompt">
            <canvas id="objCnv" width="300" height="500"></canvas>
        </div>
	        <div class="arrow" id="objArrow" style="display:none">
	            <img src="${resRoot}/images/behind.png" />
	        </div>
        <input type="hidden" name="cardsUser.cdesc" id="sentiment-txt" value=" ${model.cdesc}" /> 
        
         <div class="sender" id="objSender">
            <div class="senderCon" id="objSenderCon">
                <span class="sender_close" id="sender-close"></span>
                <div style="clear:both;"></div>
                <p>发件人：</p>
                <input type="text" id="sender-txt" />
                <span class="sender_btn" id="sender-btn" ></span>
            </div>
        </div>
        <div class="recipient" id="objRecipient">
            <div class="recipientCon" id="objRecipientCon">
                <span class="recipient_close" id="recipient-close"></span>
                <div style="clear:both;"></div>
                <p>收件人：</p>
                <input type="text" id="recipient-txt" />
                <span class="recipient_btn" id="recipient-btn" ></span>
            </div>
        </div>
        <div class="sentiment" id="objSentiment">
            <div class="sentimentCon" id="objSentimentCon">
                <span class="sentiment_close" id="sentiment-close"></span>
                <div style="clear:both;"></div>
                <p>祝福语：</p>
                <input type="text" id="sentiment-txt" />
                <span class="sentiment_btn" id="sentiment-btn" ></span>
            </div>
        </div>
        <div id="prompt" class="prompt">
            <canvas id="objCnv" width="300" height="500"></canvas>
        </div>

        <input type="hidden" id="objHidden-P" />
        <input type="hidden" id="objHidden-S" />
        <input type="hidden" id="objBtn" accept="image/*" onChange="loadImg(this);"/>
        
</body>

<script type="text/javascript">



var imgUrl = "${resRoot}/images/share.gif";
var localurl=document.location.href;
var lineLink = localurl;
var descContent = '${model.sender}向您祝福:${model.cdesc}';
var shareTitle = '${model.title}';
var appid = '';
function shareFriend() {
    WeixinJSBridge.invoke('sendAppMessage',{
        "appid": appid,
        "img_url": imgUrl,
        "img_width": "200",
        "img_height": "200",
        "link": lineLink,
        "desc": descContent,
        "title": shareTitle
    }, function(res) {
        //_report('send_msg', res.err_msg);
    });
}
function shareTimeline() {
    WeixinJSBridge.invoke('shareTimeline',{
        "img_url": imgUrl,
        "img_width": "200",
        "img_height": "200",
        "link": lineLink,
        "desc": descContent,
        "title": shareTitle
    }, function(res) {
           //_report('timeline', res.err_msg);
    });
}
function shareWeibo() {
    WeixinJSBridge.invoke('shareWeibo',{
        "content": descContent,
        "url": lineLink,
    }, function(res) {
        //_report('weibo', res.err_msg);
    });
}
// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 发送给好友
    WeixinJSBridge.on('menu:share:appmessage', function(argv){
        shareFriend();
    });
    // 分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function(argv){
        shareTimeline();
    });
    // 分享到微博
    WeixinJSBridge.on('menu:share:weibo', function(argv){
        shareWeibo();
    });
}, false);

function $$(objId){
	var obj=document.getElementById(objId);
	return obj;
}

$$("objSub").onclick=function(){
	document.location.href="http://m.BananaTV.com/web/card.action?code=${model.tempCode}";
};

if(localurl.indexOf("from")<0){
	$$("objArrow").style.display="";
}

window.URL=window.URL||window.webkitURL;
var objW=window.innerWidth,objH=window.innerHeight,objHiddenS=$$("objHidden-S");

objGreetWidth=$$("objGreet").offsetWidth;
objGreetNum=$$("objGreet").innerHTML.length;

function trimStr(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function setDesc(){
	var sentimentTxt1=$$("sentiment-txt");
	var sentimentAll1=$$("sentiment-all");
	var objSentiment1=$$("objSentiment");

	if(sentimentTxt1.value!=""){
		var objStr=sentimentTxt1.value;
		objStr=trimStr(objStr);
		var strLen=objStr.length,str="";
		var ulw=$$("sentiment-all").offsetWidth;
		var fontw=objGreetWidth/objGreetNum;
        var textnum=parseInt(ulw/fontw);
        
        alert("textnum"+textnum+" strLen"+strLen+" ulw"+ulw+" fontw"+fontw);

		for(var i=0;i<strLen;i+=textnum){
			str+="<li>"+objStr.substr(i,textnum)+"</li>";
		}
		sentimentAll1.innerHTML=str;
		objSentiment1.style.display="none";
	}
}

        setDesc();//设置祝福语显示

</script>

</html>