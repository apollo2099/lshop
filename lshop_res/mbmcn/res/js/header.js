
	function doLoad(){
	    var users=lshop.getCookieToJSON('user');
		if(users.email!=null&&users.email!=''){
	       $("#logoutId").show();
	       $("#myorderId").show();
	       $("#loginId").hide();
	       $("#registerId").hide();
		 }
	}

	$(function(){doLoad();});	
	
	//前往购物车操作
	function toCar(storeDomain){
			location.href=storeDomain+"/web/mall!getShopCartList.action";
	}
	
	//前住注册
	function toRe(storeDomain){
		var url=window.location;
		window.location.href=storeDomain+"/web/userCenterManage!toRegister.action?jumpurl="+url;
	}
	
	//前往登陆
	function toLo(storeDomain){
		var url=window.location;
		window.location.href=storeDomain+"/web/mbmcn/noLoginInfo.jsp?jumpurl="+url;
	}
	
    //限制只能输入数字
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
	
	//控制小数位数
	function toFloat(x) {  
	    var f = parseFloat(x);  
	    if (isNaN(f)) {  
	        return false;  
	    }  
	    var f = Math.round(x*100)/100;  
	    return f;
	} 
	
	//制保留2位小数，如：2，会在2后面补上00.即2.00  
	function toFloat2(f){
		var s = f.toString();  
	    var rs = s.indexOf('.');  
	    if (rs < 0) {  
	        rs = s.length;  
	        s += '.';  
	    }  
	    while (s.length <= rs + 1) {  
	        s += '0';  
	    }  
	    return s;  
	} 
	
	//收藏
	function AddFavorite(sURL, sTitle)
	{
	    try
	    {
	        window.external.addFavorite(sURL, sTitle);
	    }
	    catch (e)
	    {
	        try
	        {
	            window.sidebar.addPanel(sTitle, sURL, "");
	        }
	        catch (e)
	        {
	            alert("加入收藏失败，请使用手动进行添加");
	        }
	    }
	}
	
	function toFloat(x) {  
	    var f = parseFloat(x);  
	    if (isNaN(f)) {  
	        return false;  
	    }  
	    var f = Math.round(x*100)/100;  
	    return f;
	} 
	
	//制保留2位小数，如：2，会在2后面补上00.即2.00  
	function toFloat2(f){
		var s = f.toString();  
	    var rs = s.indexOf('.');  
	    if (rs < 0) {  
	        rs = s.length;  
	        s += '.';  
	    }  
	    while (s.length <= rs + 1) {  
	        s += '0';  
	    }  
	    return s;  
	} 