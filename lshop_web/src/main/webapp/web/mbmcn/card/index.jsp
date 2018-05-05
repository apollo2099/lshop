<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>BananaTv商城   ${model.title}</title>
<%
String randomstr=System.currentTimeMillis()+"";
%>
<link href="${resRoot}/${model.cssName}/css/${model.cssName}.css?id=<%=randomstr%>}" type="text/css"
	rel="stylesheet" />
<script src="${resRoot}/${model.cssName}/js/${model.cssName}.js?id=<%=randomstr%>" type="text/javascript"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/card/js/jquery-2.1.1.min.js?id=<%=randomstr%>" ></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/jquery.form.js?id=<%=randomstr%>" ></script>
<style type="text/css">
.progress { position:absolute;width:100%;text-align:center;left:0px;top:0px;display:none;background-color: rgba(0,0,0,0.5);}
.bar{background-color:#0056a7;  width:0%; height:20px; border-radius: 3px; display: block;}
.bardiv{background-color:#FFF;height:20px; border-radius: 3px; display: inline-block;width:70%;}
.percent {height:20px; display:inline-block;color:#fff;}
#percent {width: 80%;margin-left: 10%;height: 20px;line-height: 20px;}
</style>
</head>
<body>
<script type="text/javascript">
var uploadFlag=false;
$(function(){
	var indexI=0;
    var handler = function(){
    	var bar = $('.bar');
    	var percent = $('.percent');
    	var progress = $(".progress");
    	if(uploadFlag==true){
	    	var percentComplete=Math.round(Math.random()*3);
	    	indexI+=percentComplete;
	    	if(indexI<95){
		    	var percentVal = indexI + '%';
				bar.width(percentVal);
				percent.html(percentVal);
	    	}
    	}
    }
    var timer = setInterval( handler , 400);
    
    var clear = function(){
        clearInterval(timer);
    }
});
function uploadImag(){
	$("#objSub").val("提交中..");
	$("#objSub").attr('disabled',false)
	if($("#objBtn").val()==""){
		$$("form").submit();
		return;
	}
	var bar = $('.bar');
	var percent = $('.percent');
	var progress = $(".progress");
	$("#objBtn").wrap("<form id='myupload'  action='/web/card!uploadImg.action' method='post' enctype='multipart/form-data'></form>");
		$("#myupload").ajaxSubmit({
			dataType:  'json',
			beforeSend: function() {
        		var percentVal = '0%';
        		bar.width(percentVal);
        		percent.html(percentVal);
        		progress.show();
        		uploadFlag=true;
    		}/*,
    		uploadProgress: function(event, position, total, percentComplete) {
    			var percentVal = percentComplete + '%';
    			bar.width(percentVal);
    			percent.html(percentVal);
    		}*/
    		,
			success: function(data) {
				uploadFlag==false;
    			bar.width(100+"%");
    			percent.html("100%");
				$("#imgNameId").val(data.imgName);
				$$("form").submit();
			},
			error:function(xhr){
				uploadFlag==false;
				btn.html("上传失败");
				bar.width('0');
				$("#objSub").val("生成贺卡");
				$("#objSub").attr('disabled',true);
			}
		});
}
</script>

	<form id="form" action="/web/card!docard.action" method="post">
	    <input type="hidden" name="imgName" id="imgNameId" value="">
		<input type="hidden" name="code" value="${model.code}" />
		<div class="above" id="objAbove">
			<div class="move" id="move1" style="top: 0px;">
				<div id="objIcon" class="icon">
					<img id="objAboveImg" src="${resRoot}/${model.cssName}/images/aboveTop.png" /> <input
						type="file" id="objBtn" name="img" accept="image/*"
						onChange="loadImg(this);" /> <cite id="objCite">+添加图片</cite>
					<span ><img class="rotateImg" id="rotateImg" src="${resRoot}/${model.cssName}/images/rotate1.png" /></span>
				</div>
			</div>
			<div class="move" id="move2" style="top: 0px;">
				<div id="tInterval" class="interval"></div>
				<div class="content clearfix" id="objContent">
					<p id="recipient-person">添加收件人</p>
					<ul id="sentiment-all" class="sentimentAll">
						<li style="text-align: left;"><span id="objGreet"
							style="width: auto; min-width: 10px; display: inline; font-family:黑体;">添加祝福语</span></li>
						<li>&nbsp;</li>
					</ul>
					<p id="sender-person" class="senderPerson">添加发件人</p>
				</div>
				<div id="bInterval" class="interval"></div>
			</div>
		</div>

<!-- 		<input type="button" value="编辑" onclick="showdiv('template');"> -->

		<div class="move1" id="move3">
			<div class="bgBottom" id="objBgBottom">
				<img src="${resRoot}/${model.cssName}/images/aboveBottom.png" id="objBottomImg"/> <input
					type="button" id="objSub" value="生成贺卡" />
                 
			</div>
		</div>

		<div class="below" id="objBelow">
			<div id="objImg" class="objImg"
				style="position: absolute; top: 0px; left: 0px;"></div>
		</div>
		<div class="sender" id="objSender">
			<div class="senderCon" id="objSenderCon">
				<span class="sender_close" id="sender-close"></span>
				<div style="clear: both;"></div>
				<p>
					发件人：可输入(<span id="sendernum">5</span>)个字
				</p>
				<input type="text" name="cardsUser.sender" id="sender-txt"
					maxlength="5" /> <span class="sender_btn" id="sender-btn"></span>
			</div>
		</div>
		<div class="recipient" id="objRecipient">
			<div class="recipientCon" id="objRecipientCon">
				<span class="recipient_close" id="recipient-close"></span>
				<div style="clear: both;"></div>
				<p>
					收件人：可输入(<span id="recipientnum">5</span>)个字
				</p>
				<input type="text" name="cardsUser.recipient" id="recipient-txt"
					maxlength="5" /> <span class="recipient_btn" id="recipient-btn"></span>
			</div>
		</div>
		<div class="sentiment" id="objSentiment">
			<div class="sentimentCon" id="objSentimentCon">
				<span class="sentiment_close" id="sentiment-close"></span>
				<div style="clear: both;"></div>
				<p>
					祝福语：可输入(<span id="descnum">70</span>)个字
				</p>
				<input type="text" name="cardsUser.cdesc" id="sentiment-txt"
					maxlength="70" /> <span class="sentiment_btn" id="sentiment-btn"></span>
			</div>
		</div>
		
		<s:action name="cardAction!getList" namespace="/web" executeResult="true">
		</s:action>
		
		<div id="prompt" class="prompt">
			<canvas id="objCnv" width="300" height="500"></canvas>
		</div>
		<div class="error" id="objError">
			<div class="errorCon" id="objErrorCon">
				<p id="errorMsg">请填写完整信息才能生成祝福哦<br/>添加图片建议在wifi情况下操作</p>
				<input id="btnError" />
			</div>
			<input type="hidden" id="objHidden-P" name="P" value="0,0" /> <input
				type="hidden" id="objHidden-S" name="S" />
				 <input type="hidden"
				id="objHidden-W" name="W" />
				<input type="hidden" id="objHidden-D" name="D" value="0"/>
				</div>
	</form>
	<div class="progress" id="progress" >
	    <div id="percent">
	    <div class="bardiv">
	    <span class="bar"></span>
	    </div>
        <span class="percent"  >0%</span >
        </div>
	</div>
	<script type="text/javascript">
	var resRoot = '${resRoot}';
	var cssName = '${model.cssName}';
	
		function $$(objId) {
			var obj = document.getElementById(objId);
			return obj;
		}
		window.URL = window.URL || window.webkitURL;
		var objW = window.innerWidth, objH = window.innerHeight, objHiddenS = $$("objHidden-S");
		$$("objHidden-W").value = objW;
		loadImg = function(obj) {
			objImg.innerHTML = "";
			var objFiles = obj.files, img = new Image(),objRotateImg=$$("rotateImg");
			if (window.URL) {
				img.src = window.URL.createObjectURL(objFiles[0]);
				objCite.style.marginTop = "8%";
				objCite.innerHTML = "+更改图片";
				//img.style.width=parseInt(objW*0.7)+"px";
				img.id = "img";
				img.style.display = "block";
				objImg.appendChild(img);
				img.onload = function(e) {
					window.URL.revokeObjectURL(this.src);
					var obj = $$("img"), h = obj.offsetHeight, w = obj.offsetWidth;
					if (w <= h) {
						img.style.width = parseInt(objW * 0.8) + "px";
					} else {
						img.style.height = parseInt(objH * 0.5) + "px";
					}
					objHiddenS.value = obj.offsetWidth + "," + obj.offsetHeight;
					objRotateImg.style.display="block";
				}
				objAboveImg.src = resRoot + "/" + cssName + "/images/aboveTop1.png";
				//objIcon.style.backgroundSize="100% 100%";
			}
		}
/**	*/	
		function changeStyle(styleName){
			var oldCssName = resRoot + "/" + cssName + "/css/" + cssName + ".css";
			var newCssName = resRoot + "/" + styleName + "/css/" + styleName + ".css";
			replacejscssfile(oldCssName, newCssName, "css");
			var topImg = document.getElementById("objAboveImg");
			var bottomImg = document.getElementById("objBottomImg");
			topImg.src = resRoot + "/" + styleName + "/images/aboveTop.png";
			bottomImg.src = resRoot + "/" + styleName + "/images/aboveBottom.png";
			document.getElementById("template").style.display="none";
		}

		function loadjscssfile(filename, filetype) {
			var fileref = document.createElement('script');
			if (filetype == "js") {
				fileref.setAttribute("type", "text/javascript");
				fileref.setAttribute("src", filename);
			} else if (filetype == "css") {
				var fileref = document.createElement("link");
				fileref.setAttribute("rel", "stylesheet");
				fileref.setAttribute("type", "text/css");
				fileref.setAttribute("href", filename);
			}
			if (typeof fileref != "undefined") {
				document.getElementsByTagName("head")[0].appendChild(fileref);
			}
		}

		function removejscssfile(filename, filetype) {
			var targetelement = (filetype == "js") ? "script"
					: (filetype == "css") ? "link" : "none";
			var targetattr = (filetype == "js") ? "src"
					: (filetype == "css") ? "href" : "none";
			var allsuspects = document.getElementsByTagName(targetelement);
			for (var i = allsuspects.length; i >= 0; i--) {
				if (allsuspects[i]
						&& allsuspects[i].getAttribute(targetattr) != null
						&& allsuspects[i].getAttribute(targetattr).indexOf(
								filename) != -1)
					allsuspects[i].parentNode.removeChild(allsuspects[i]);
			}
		}

		function createjscssfile(filename, filetype) {
			var fileref = document.createElement('script');
			if (filetype == "js") {
				fileref.setAttribute("type", "text/javascript");
				fileref.setAttribute("src", filename);
			} else if (filetype == "css") {
				var fileref = document.createElement("link");
				fileref.setAttribute("rel", "stylesheet");
				fileref.setAttribute("type", "text/css");
				fileref.setAttribute("href", filename);
			}
			return fileref;
		}

		function replacejscssfile(oldfilename, newfilename, filetype) {
			var targetelement = (filetype == "js") ? "script"
					: (filetype == "css") ? "link" : "none";
			var targetattr = (filetype == "js") ? "src"
					: (filetype == "css") ? "href" : "none";
			var allsuspects = document.getElementsByTagName(targetelement);
			for (var i = allsuspects.length; i >= 0; i--) {
				if (allsuspects[i]
						&& allsuspects[i].getAttribute(targetattr) != null
						&& allsuspects[i].getAttribute(targetattr).indexOf(
								oldfilename) != -1) {
					var newelement = createjscssfile(newfilename, filetype);
					allsuspects[i].parentNode.replaceChild(newelement,
							allsuspects[i]);
				}
			}
		}
		
		

		var imgUrl = "${resRoot}/${model.cssName}/images/share.gif";
		var localurl=document.location.href;
		var lineLink = localurl;
		var descContent = 'BananaTv为您提供温馨祝福贺卡~给您可爱的亲朋好友送上私人定制的节日祝福吧^_^';
		var shareTitle = 'BananaTv商城   ${model.title}';
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
		

</script>
</body>
</html>