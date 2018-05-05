window.onload=function(){
	function $$(objId){
		var obj=document.getElementById(objId);
		return obj;
	}
	window.URL=window.URL||window.webkitURL;
	var objW=window.innerWidth,objH=window.innerHeight;
	var objAbove=$$("objAbove"),
	objImg=$$("objImg"),
	objIcon=$$("objIcon"),
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
	objHiddenS=$$("objHidden-S"),
	objArrow=$$("objArrow"),
	
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
	
	
	objSender.style.height=objH+"px";
	objSenderCon.style.height=parseInt(0.9*objW*347/594)+"px";
	objSenderCon.style.marginTop=parseInt(521*objH/969)-intervalH+"px";
	
	objRecipient.style.height=objH+"px";
	objRecipientCon.style.height=parseInt(0.9*objW*357/583)+"px";
	objRecipientCon.style.marginTop=parseInt(234*objH/969)+"px";
	
	objSentiment.style.height=objH+"px";
	objSentimentCon.style.height=parseInt(0.9*objW*349/592)+"px";
	objSentimentCon.style.marginTop=parseInt(632*objH/969)+"px";
	
	objCnt.strokeStyle="red";
	var startX=parseInt(objW/2),startY=objH-bottomH,endX=objW;
	objCnt.moveTo(0,110);
	objCnt.lineTo(endX,0);
	objCnt.stroke();
	
	
	/*objCite.onclick=function(){
		objBtn.click();
	}*/
	
	loadImg=function(obj){
		var objFiles=obj.files,img=new Image();
		if(window.URL){
			img.src=window.URL.createObjectURL(objFiles[0]);
			objCite.style.display="none";
			//img.style.width=parseInt(objW*0.7)+"px";
			img.id="img";
			img.style.display="block";
			objImg.appendChild(img);
			img.onload=function(e){
				window.URL.revokeObjectURL(this.src);
				var obj=$$("img"),h=obj.offsetHeight,w=obj.offsetWidth;
				if(w<=h){
					img.style.width=parseInt(objW*0.7)+"px";
				}
				else{
					img.style.height=parseInt(objH*0.4)+"px";
				}
				objHiddenS.value=obj.offsetWidth+","+obj.offsetHeight;
			}
			objAboveImg.src="images/aboveTop1.png";
			//objIcon.style.backgroundSize="100% 100%";
		}
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
	/*;(function(){
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
	*/
	
	/*senderClose.onclick=function(){
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
	}
	
	
	sentimentClose.onclick=function(){
		objSentiment.style.display="none";
	}
	sentimentBtn.onclick=function(){
		if(sentimentTxt.value!=""){
			var objStr=sentimentTxt.value,strLen=objStr.length,str="";
			for(var i=0;i<strLen;i+=12){
				str+="<li>"+objStr.substr(i,12)+"</li>";
			}
			sentimentAll.innerHTML=str;
			objSentiment.style.display="none";
		}
	}
	sentimentAll.onclick=function(){
		objSentiment.style.display="block";
	}*/
}