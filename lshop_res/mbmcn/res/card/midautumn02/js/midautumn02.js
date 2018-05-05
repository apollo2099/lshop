var objGreetWidth;
window.onload=function(){

	var objAbove=$$("objAbove"),
	objImg=$$("objImg"),
	objIcon=$$("objIcon"),
	objCite=$$("objCite"),
	objBtn=$$("objBtn"),
	drag=0,mouseX,mouseY,objX,objY,x,y,
	objSender=$$("objSender"),
	senderClose=$$("sender-close"),
	senderBtn=$$("sender-btn"),
	senderTxt=$$("sender-txt"),
	senderPerson=$$("sender-person"),
	objSenderCon=$$("objSenderCon"),
	objBgBottom=$$("objBgBottom"),
	objContent=$$("objContent"),
	objBelow=$$("objBelow"),
	objTInterval=$$("tInterval"),
	objBInterval=$$("bInterval"),
	objSub=$$("objSub"),
	objPrompt=$$("prompt"),
	objCnv=$$("objCnv"),
	objCnt=objCnv.getContext("2d"),
	objAboveImg=$$("objAboveImg"),
	objHiddenP=$$("objHidden-P"),
	objGreet=$$("objGreet"),
	objError=$$("objError"),
	objErrorCon=$$("objErrorCon"),
	objBtnError=$$("btnError"),
	objErrorMsg=$$("errorMsg"),
	objRotateImg=$$("rotateImg"),
	objHiddenD=$$("objHidden-D"),
	
	objRecipient=$$("objRecipient"),
	recipientClose=$$("recipient-close"),
	recipientBtn=$$("recipient-btn"),
	recipientTxt=$$("recipient-txt"),
	recipientPerson=$$("recipient-person"),
	objRecipientCon=$$("objRecipientCon"),
	
	objSentiment=$$("objSentiment"),
	sentimentClose=$$("sentiment-close"),
	sentimentBtn=$$("sentiment-btn"),
	sentimentTxt=$$("sentiment-txt"),
	sentimentAll=$$("sentiment-all"),
	objSentimentCon=$$("objSentimentCon");
	
	var objMove2=$$("move2");
	var objMove3=$$("move3");
	
	var topH=parseInt(objW*467/582);
	//objIcon.style.height=topH+"px";
	//objCite.style.marginLeft=parseInt(26/100*objW)+"px";
	//objCite.style.marginTop=parseInt(54/100*topH)+"px";
	
	var bottomH=parseInt(objW*103/582);
	//objBgBottom.style.height=bottomH+"px";
	//objSub.style.width=parseInt(objW*156/582)+"px";
	//objSub.style.height=parseInt(bottomH*51/103)+"px";
	
	//objMove2.style.top=topH+"px";
	//objMove3.style.top=objH-bottomH+"px";
	
	//objContent.style.height=objH-topH-bottomH+"px";
	var centerH=parseInt(objW*232/582);
	objContent.style.minHeight=centerH+"px";
	
	var intervalH=(objH-topH-bottomH-centerH)/2;
	objTInterval.style.height=intervalH+"px";
	objBInterval.style.height=intervalH+"px";
	
	objBelow.style.height=objH+"px";
	
	recipientPerson.style.height="25%";
	recipientPerson.style.lineHeight=parseInt(0.25*centerH)+"px";
	sentimentAll.style.height="50%";
	senderPerson.style.height="25%";
	senderPerson.style.lineHeight=parseInt(0.25*centerH)+"px";
	sentimentAll.style.lineHeight=parseInt(0.25*centerH)+"px";
	//recipientPerson.style.paddingLeft="12.887%";
	//sentimentAll.style.paddingLeft="12.887%";
	//sentimentAll.style.paddingRight="12.887%";
	//senderPerson.style.paddingRight="12.887%";
	
	
	var contentH=parseInt(0.9*objW*357/583),contentT=parseInt((objH/2-contentH/2)*0.62);
	objSender.style.height=objH+"px";
	objSenderCon.style.height=contentH+"px";
	objSenderCon.style.marginTop=contentT+"px";
	
	objRecipient.style.height=objH+"px";
	objRecipientCon.style.height=contentH+"px";
	objRecipientCon.style.marginTop=contentT+"px";
	
	objSentiment.style.height=objH+"px";
	objSentimentCon.style.height=contentH+"px";
	objSentimentCon.style.marginTop=contentT+"px";
	
	objCnt.strokeStyle="red";
	var startX=parseInt(objW/2),startY=objH-bottomH,endX=objW;
	objCnt.moveTo(0,110);
	objCnt.lineTo(endX,0);
	objCnt.stroke();
	var deg=0;
	;(function(){
		objRotateImg.addEventListener("touchstart",function(e){
			e.stopPropagation();
			e.preventDefault();
			deg+=90;
		    objImg.style.transform="rotate("+deg+"deg)";
			objHiddenD.value=deg;
		},false);
	}());
	
	objCite.onclick=function(){
		objBtn.click();
	}
	
	
	objIcon.onmousedown=function(e){
		drag=1;
		mouseX=parseInt(e.clientX);
		mouseY=parseInt(e.clientY);
		objX=parseInt(objImg.style.left);
		objY=parseInt(objImg.style.top);
	}
	objIcon.onmousemove=function(e){
		if(drag==1){
			x=e.clientX-mouseX+objX;
			y=e.clientY-mouseY+objY;
			objImg.style.left=x+"px";
			objImg.style.top=y+"px";
		}
	}
	objIcon.onmouseup=function(e){
		drag=0;
	}
	;(function(){
		objIcon.addEventListener("touchstart",function(e){
			e.stopPropagation();
			e.preventDefault();
			if(e.target==objCite){
				objBtn.click();
			}
			else{
			    drag=1;
				mouseX=parseInt(e.touches[0].pageX);
				mouseY=parseInt(e.touches[0].pageY);
				objX=parseInt(objImg.style.left);
				objY=parseInt(objImg.style.top);
			}
		},false);
	}());
	;(function(){
		objIcon.addEventListener("touchmove",function(e){
			e.stopPropagation();
			e.preventDefault();
			if(drag==1){
				x=e.touches[0].pageX-mouseX+objX;
				y=e.touches[0].pageY-mouseY+objY;
				objImg.style.left=x+"px";
				objImg.style.top=y+"px";
			}
		},false);
	}());
	;(function(){
		objIcon.addEventListener("touchend",function(e){
			e.stopPropagation();
			e.preventDefault();
			drag=0;
			objHiddenP.value=objImg.offsetLeft+","+objImg.offsetTop;
		},false);
	}());
	
	function checkMax(obj,max,numid){
		var val=obj.value;
		var valnum=val.length;
		var less=max-valnum;
		if(less<0){
			var newval=val.substring(0,max);
			obj.value=newval;
			less=0;
		}
		$$(numid).innerHTML=less;
	}
	
//	var sendmax=5,recipientMax=5,descmax=70;
//	
//	
//	;(function(){
//		$$("objRecipient").addEventListener("touchstart",function(e){
//			checkMax(recipientTxt,recipientMax,"recipientnum");
//		},false);
//	}());
//	;(function(){
//		$$("objSentiment").addEventListener("touchstart",function(e){
//			checkMax(sentimentTxt,descmax,"descnum");
//		},false);
//	}());
//	
//	;(function(){
//		$$("objSender").addEventListener("touchstart",function(e){
//			checkMax(senderTxt,sendmax,"sendernum");
//		},false);
//	}());
//	
	
	
	senderClose.onclick=function(){
		objSender.style.display="none";
	}
	senderBtn.onclick=function(){
		if(senderTxt.value!=""){
			senderPerson.innerHTML=senderTxt.value;
			objSender.style.display="none";
		}
	}
	senderPerson.onclick=function(){
		objSender.style.display="block";
		var H=document.documentElement.offsetHeight,T=document.body.scrollTop;
		objSender.style.height=H+"px";
		objSenderCon.style.marginTop=contentT+T+"px";
	}
	
	
	recipientClose.onclick=function(){
		objRecipient.style.display="none";
	}
	recipientBtn.onclick=function(){
		if(recipientTxt.value!=""){
			recipientPerson.innerHTML=recipientTxt.value;
			objRecipient.style.display="none";
		}
	}
	recipientPerson.onclick=function(){
		objRecipient.style.display="block";
		var H=document.documentElement.offsetHeight,T=document.body.scrollTop;
		objRecipient.style.height=H+"px";
		objRecipientCon.style.marginTop=contentT+T+"px";
	}
	
	
	sentimentClose.onclick=function(){
		objSentiment.style.display="none";
	}
	sentimentBtn.onclick=function(){
		setDesc();
	}
	
	sentimentAll.onclick=function(){
		objSentiment.style.display="block";
		var H=document.documentElement.offsetHeight,T=document.body.scrollTop;
		objSentiment.style.height=H+"px";
		objSentimentCon.style.marginTop=contentT+T+"px";
	}
	
	
	
	
	objErrorCon.style.height=parseInt(objW*0.9*227/547)+"px";
	objBtnError.style.marginTop=parseInt(objW*0.9*0.18*227/547)+"px";
	objErrorMsg.style.paddingTop=parseInt(objW*0.9*0.26*227/547)+"px";
	
	objSub.onclick=function(){
		if(recipientPerson.innerHTML=="添加收件人"||sentimentTxt.value==""||senderPerson.innerHTML=="添加发件人"){
			var H=document.documentElement.offsetHeight;
			objError.style.height=H+"px";
			objErrorCon.style.marginTop=H-parseInt(objW*0.9*227/547)-200+"px";
			objError.style.display="block";
		}
		else{
			uploadImag();
		}
	}
	
	objBtnError.onclick=function(){
		objError.style.display="none";
	}
	
	objGreetWidth=$$("objGreet").offsetWidth;
	objGreetNum=$$("objGreet").innerHTML.length;
	
	var objTemClose=$$("temClose"),objTemplate=$$("template");
	objTemplate.style.minHeight=objH+"px";
	objTemClose.addEventListener("click",function(e){
		e.stopPropagation();
		e.preventDefault();
		objTemplate.style.display="none";
	},false);
	
}

function setDesc(){
	var sentimentTxt1=$$("sentiment-txt");
	var sentimentAll1=$$("sentiment-all");
	var objSentiment1=$$("objSentiment");

	if(sentimentTxt1.value!=""){
		var objStr=sentimentTxt1.value,strLen=objStr.length,str="";
		var ulw=$$("sentiment-all").offsetWidth;
		var fontw=objGreetWidth/objGreetNum;
        var textnum=parseInt(ulw/fontw);
		for(var i=0;i<strLen;i+=textnum){
			str+="<li>"+objStr.substr(i,textnum)+"</li>";
		}
		sentimentAll1.innerHTML=str;
		objSentiment1.style.display="none";
	}
}

function showdiv(targetid){
	var target = document.getElementById(targetid);
	target.style.display = "block";
}