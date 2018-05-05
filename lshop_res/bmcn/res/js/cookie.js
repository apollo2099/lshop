/*
 * JS操作cookie
 * 将id和数量（sum）以#000001:0001的形式存到cookie，"#000001"为id，":0001"为数量
 * id最大为6位，数量最大为4位，操作前須保證id和数量在最大範圍內
 * id从页面标签id为"proId"中获取
 * 数量从页面标签id为"proSum"中获取
 */


//添加至cookie
function addCookie(){	
	var proId = document.getElementById("proId").value;
	var proSum = document.getElementById("proSum").value;	
	if(proId != ""&&proSum != ""){
		addCookieBy(proId, proSum);
	}else{
		alert("请输入！");
	}
}

//根据id和数量存储至cookie
function addCookieBy(proId,proSum){
	var oldCookie = getCookieByName("appStoreCookie");
	if(oldCookie == ""){//如果cookie不存在，则直接添加至cookie
		var appStoreCookie = toCookieString(proId, proSum);
		setCookie("appStoreCookie", appStoreCookie, null);
	}else{//如果cookie存在
		var pid = getSuitableString(proId.toString(), 6, "#");
		if(oldCookie.indexOf(pid) !=-1){//判断存于cookie字符串中是否已经存在此id，如果存在则修改数量
			var index = oldCookie.indexOf(pid);
			var oldSum = oldCookie.substring(index+8,index+12);
			var newSum = parseFloat(oldSum) + parseFloat(getSuitableString(proSum, 4, ""));
//			alert(proId+":"+oldSum.toString()+":"+newSum.toString());
			var oldStr = toCookieString(proId, oldSum);
			var newStr = toCookieString(proId, newSum.toString());
			var appStoreCookie = oldCookie.replace(oldStr,newStr);
			setCookie("appStoreCookie", appStoreCookie, null);
		}else{//不存在，则添加至字符串
			var appStoreCookie = toCookieString(proId, proSum);
			setCookie("appStoreCookie", oldCookie.concat(appStoreCookie), null);
		}
	}	
}

//获取cookie
function getCookie(){
	var str = getCookieByName("appStoreCookie");
	alert(str);
	return str;
}

//修改cookie
function updateCookie(){
	var proId = document.getElementById("proId").value;
	var proSum = document.getElementById("proSum").value;
	updateCookieById(proId,proSum);
}


//修改指定id数量
function updateCookieById(id,sum){
	var oldCookie = getCookieByName("appStoreCookie");
	var pid = getSuitableString(id, 6, "#");
//	alert("cookie pid----"+pid);
	if(oldCookie.indexOf(pid) !=-1){//判断存于cookie字符串中是否已经存在此id，如果存在则修改数量
		var index = oldCookie.indexOf(pid);
		var oldSum = oldCookie.substring(index+8,index+12);
		var oldStr = toCookieString(id, oldSum);
		var newStr = toCookieString(id, sum);
//		alert(oldStr+"-------------"+newStr);
		var appStoreCookie = oldCookie.replace(oldStr,newStr);
		setCookie("appStoreCookie", appStoreCookie, null);
	}else{//id不存在
		
	}	
}

//删除cookie
function delAppCookie(){
	var proId = document.getElementById("proId").value;
	delCookieById(proId);
}

//删除指定id
function delCookieById(id){
	var oldCookie = getCookieByName("appStoreCookie");
	var str = getSuitableString(id, 6, "#");
	if(oldCookie.indexOf(str) != -1){
		var index = oldCookie.indexOf(str);
		var oldStr = oldCookie.substring(index,index+12);
		var appStoreCookie = oldCookie.replace(oldStr,"");
		setCookie("appStoreCookie", appStoreCookie, null);
	}
}

//清空指定cookie
function delCookieByName(){
	var oldCookie = getCookieByName("appStoreCookie");
	if(null != oldCookie||"" != oldCookie){
		setCookie("appStoreCookie", "", 1);
	}	
}


//设置cookie
function setCookie(c_name, value, expireTime) {
	if(null == expireTime){//如果传过来的时间为NULL值，则为默认时间
		expireTime = 24*60*60*1000;//为毫秒数
	}
	var exdate = new Date()
	exdate.setTime(exdate.getTime() + expireTime)
	document.cookie = c_name + "=" + escape(value)
			+ (";expires=" + exdate.toGMTString())
}


//根据c_name获取指定的cookie
function getCookieByName(c_name) {
	if (document.cookie.length>0)
	  {
	  c_start=document.cookie.indexOf(c_name + "=")
	  if (c_start!=-1)
	    { 
	    c_start=c_start + c_name.length+1 
	    c_end=document.cookie.indexOf(";",c_start)
	    if (c_end==-1) c_end=document.cookie.length
	    return unescape(document.cookie.substring(c_start,c_end))
	    }
	  return "";
	  }
	return "";
}

// 根据id和数量生成存储到cookie的字符串
function toCookieString(id,sum){
	var newId = getSuitableString(id, 6, "#");
	var newSum = getSuitableString(sum, 4, ":");
	return newId.concat(newSum);
}

//获取合适的字符串
function getSuitableString(source,len,prefix){
	var newSource = "";
	var length = source.length;
	if(len == length){
		return prefix.concat(source.substring(0,len));
	}else{
		for(i=0;i<len-length;i++){
			newSource = newSource.concat("0");
		}
		return prefix.concat(newSource).concat(source);
	}
	if(len < length){
		alert("给定的id不匹配！");
	}
}

//获取cookie存储数量
function getCookieNum(){
	var oldStr = getCookieByName("appStoreCookie");
	if(oldStr==""){
		return 0;
	}else{
		var oldLen=oldStr.length;
		while(oldStr.indexOf("#")>=0){
			oldStr=oldStr.replace("#","");
		}
		var newLen=oldStr.length;
	    return oldLen-newLen;
	}
}

