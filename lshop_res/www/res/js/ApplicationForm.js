$(function(){
	var arrayId=new Array("#applyCmp","#applyName","#applyTel","#applyEmail","#applyAddr","#applyArea","#applyReason","#applyIntro");
	var addrMsg="国家 省/州 城市 区 ",areaMsg="国家 省/州  市",promptMsg="请完整填写信息！";
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
			$("#promptMsg").text("请输入正确的邮箱格式！");
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