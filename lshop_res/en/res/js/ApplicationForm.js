$(function(){
	var arrayId=new Array("#applyCmp","#applyName","#applyTel","#applyEmail","#applyAddr","#applyArea","#applyReason","#applyIntro");
	var addrMsg="(street address)(city)(state)(country) ",areaMsg="(street address)(city)(state)(country)",promptMsg="please complete the information!";
	$(arrayId[4]).focus(function(){
		if($(arrayId[4]).val()==addrMsg){
			$(arrayId[4]).val("");
		}
	}).blur(function(){
		if($(arrayId[4]).val()==""){
			$(arrayId[4]).val(addrMsg);
		}
	});
	$(arrayId[5]).focus(function(){
		if($(arrayId[5]).val()==areaMsg){
			$(arrayId[5]).val("");
		}
	}).blur(function(){
		if($(arrayId[5]).val()==""){
			$(arrayId[5]).val(areaMsg);
		}
	});
	verifyApplyForm=function(){
		if(!$("#getMsg input[type='radio']:checked").val()){
			$("#promptMsg").text(promptMsg);
			return false;
		}
		if(!$(arrayId[3]).val().match(/([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])/)){
			$("#promptMsg").text("please enter a valid email format!");
			return false;
		}
		for(var i=0,len=arrayId.length;i<len;i++){
			if($(arrayId[i]).val().match(/^\s*$/)){
				$("#promptMsg").text(promptMsg);
				return false;
			}
			if(i==4){
				if($(arrayId[4]).val()==addrMsg){
					$("#promptMsg").text(promptMsg);
					return false;
				}
			}
			if(i==5){
				if($(arrayId[5]).val()==areaMsg){
					$("#promptMsg").text(promptMsg);
					return false;
				}
			}
		}
		return true;
	}
});