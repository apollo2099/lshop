<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad Trade-in Program</title>
<link href="${resDomain}/en/res/css/css_zt4_trade.css" rel="stylesheet" type="text/css" />
<%@include file="/web/en/common/top.jsp" %>


<script type="text/javascript">
    //写cookies
	function setCookie(c_name,value,expiredays){
		var exdate=new Date()
		exdate.setDate(exdate.getDate()+expiredays)
		document.cookie=c_name+ "=" +escape(value)+
		((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
	}

	//读取cookies
	function getCookie(c_name) {
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_name + "=")
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1
				c_end = document.cookie.indexOf(";", c_start)
				if (c_end == -1)
					c_end = document.cookie.length
				return unescape(document.cookie.substring(c_start, c_end))
			}
		}
		return ""
	}

	//验证输入MAC码的有效性
	function checkMac() {
		var macCode = $("#macCode").val();
        var userEmail =$("#userEmail").val();
		
		//验证邮箱是否正确
		var emailFlag=checkEmail(userEmail);
		if(emailFlag){
			alert("Please enter the correct Email");
			return false;
		}
		//验证输入mac是否为空
		if (macCode == null || macCode.length <= 0) {
			alert("Please enter the correct MAC code");
			return false;
		}

		//验证码格式是否正确
		if (!/^ac867e[a-fA-F0-9]{6}$/.test(macCode.toLowerCase())) {
			alert("Please enter the correct MAC code");
			return false;
		}

		//验证cookie是否已经存在该验证码
		var macCookie = getCookie("macCookie");
		if (macCookie != null && macCookie.length > 0 && macCookie == macCode) {
			alert("The MAC code has been received a voucher!");
			return false;
		} else {
			setCookie("macCookie", macCode,1)
		}
		return true;
	}
	
	//验证邮箱是否正确
	function checkEmail(userEmail ){
		var regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(userEmail==null || userEmail.length<=0){
			return true;
		}		
		if (!regExp.test(userEmail)) {	
			return true;
		}
		return false;
	}
	
	
	//查询mac是否已经下过订单	
	function findMac(){    
	    var macCode = $("#macCode").val();//序列化表格内容为字符串    
	    $.ajax({    
	        type:'post',        
	        url:'activity!findByMac.action',    
	        data:"macCode="+macCode,    
	        cache:false,    
	        dataType:'json',    
	        success:function(data){
	        	if(data!=null){
	        		alert("The MAC code has been received a voucher!");
	        		setCookie("macCookie", macCode,1);
	        	}
	        }    
	    });    
	}
</script>

</head>

<body>
<% request.setAttribute("navFlag","www_tvpadzt"); %>
<%@include file="/web/en/common/header.jsp" %>
<!--End top-->

 <!--内容区-->
<div id="content_xz">
    <div id="ad_one">
    	<div class="btn"><a href="http://www.mtvpad.com/3/index.html"></a></div>
    	<div id="ad_img">
            <img src="${resDomain}/en/res/images/tvpad4zt_trade/bananer1bj.jpg"/>
        </div>
    </div>
    
    <div id="notice">
    	<div id="notice_x">
    		<p>Dear customers,</p>
            <p> We’re terribly sorry to inform you that we will start to stop the service to these old TVpad. The reason is that these TVpad hardware are too low to support our updated background OS. Therefore, we launch the TVpad trade-in program for you. You can get a $130 coupon to buy our latest products - TVpad4 by offering the MAC address of your old TVpad.</p>
    	</div>
    </div>
    
    <div id="coupon">
    	<div id="coupon_cash">
        	<img src="${resDomain}/en/res/images/tvpad4zt_trade/cou.jpg"/>
            <form class="cou_search" action="http://en.mtvpad.com/web/activity!getCouponByActivityLinkLogout.action?activityCode=4aea88e72b504e06ac0ed3117bbed336" method="post" onsubmit="return checkMac()">
            	<input name="sourceUrl" type="hidden" value="http://en.mtvpad.com/3/index.html"/>
            	<input class="cousearch_tet1" id="userEmail" name="userEmail" type="text"  dvalue="Email" />
            	<input class="cousearch_tet3" id="macCode" name="macCode" type="text"  dvalue="Enter Your MAC Address" onblur="findMac()"/>
                <input class="cousearch_sub" type="submit" value="" />
            </form>
            <div class="clear"></div>
        </div>
        <div id="flow">
        	<div id="flow_h"><h1>Steps to Trade in</h1></div>
            <div id="flow_chart">
            	<div class="chart"><p>Enter MAC<br/>address to <br/>get a $ 130<br/>coupon</p></div>
                <div class="chart"><p>Login TVpad<br/>Mall to buy<br/>TVpad4</p></div>
                <div class="chart"><p>Enter the coupon<br/>code on “Fill in & <br/>check the order”<br/>page</p></div>
                <div class="chart"><p id="chart">Make payment</p></div>
                <div class="clear"></div>
            </div>
        </div>
        
        <div id="video" >
      		<div id="video_h"><h1>Shopping process video:</h1></div>
			<div id="video_con" align="center">
				<iframe src="https://www.youtube.com/embed/mjkYqL_73XM"" allowfullscreen="" frameborder="0" width="560" height="315"></iframe>
			</div>
		</div>
        
        <div id="contact">
            <div id="contact_left">
                <h2>Please feel free to contact us, if you have any questions：</h2>
                <img src="${resDomain}/en/res/images/tvpad4zt_trade/icon_01.jpg" alt="TVpad trade-in program"/><p>Tell：<em>+00852-21349910 、+00852-21349920、+00852-21349901</em></p>
                <img src="${resDomain}/en/res/images/tvpad4zt_trade/icon_03.jpg" alt="TVpad trade-in program"/><p> QQ：<em>&nbsp;2389075307 / 2276814617</em></p>
                <img src="${resDomain}/en/res/images/tvpad4zt_trade/icon_05.jpg" alt="TVpad trade-in program"/><p>EMAIL：<em>service@mtvpad.com</em></p>
        	</div>
            <div id="contact_right">
            	<img src="${resDomain}/en/res/images/tvpad4zt_trade/erweima.jpg" alt="TVpad trade-in program"/>
                <p>Official WeChat: TVpad</p>
            </div>
        </div>
    </div>
    <div id="ad_two">
    	<div id="adtwo_img">
    		<img src="${resDomain}/en/res/images/tvpad4zt_trade/bananer2.jpg" alt="TVpad trade-in program"/>
        </div>
    </div>
    <div id="rule">
    	<div id="rule_top">
        	<h1>Rules</h1>
            <p>1. Time: Subject to the time released on TVpad popup</p>
            <p>2. Qualification:TVpad users can take part in TVpad trade-in program via the old TVpad’s MAC address. 
    Each <em>MAC address can only be used once.</em> </p>
            
            <p>3. Please note that the coupon is only available for TVpad4 device on TVpad Mall. For it has a validity, you’d 
    <em>better use it ASAP.</em></p>
        </div>
        
        <div id="rule_buttom">
        	<h1>Troubleshooting</h1>
            
            <div class="rule_buttom_d">
            	<p class="em_one">Q:</p><p class="em_th">Where is the MAC address?<br/>How many coupons can I get by one address?</p>
     		</div>
            <div class="rule_buttom_d rule_buttom_e">
			   <p class="em_one">A:</p><p class="em_th">You can find it on the back of the box</p>
               <img src="${resDomain}/en/res/images/tvpad4zt_trade/img1.jpg" alt="TVpad trade-in program"/>
			</div>
            <div class="rule_buttom_d">
            <p class="em_one">Q:</p><p class="em_th">Where can I enter the coupon code?</p>
            </div>
            <div class="rule_buttom_d rule_buttom_e">
			 <p class="em_one">A:</p><p class="em_th">You can enter the coupon code by clicking “+ Use Coupon: Each order can only be discounted by one coupon.” on the lower-left corner of “Fill in & check the order” page and click “Confirm” to get discounted.</p>
			</div>
            
            <div class="rule_buttom_d">
            <p class="em_one">Q:</p><p class="em_th">Is the coupon only available on TVpad Mall? Can I use it on TVpad authorized Amazon or AliExpress  store?</p>
            </div>
            <div class="rule_buttom_d rule_buttom_e">
				<p class="em_one">A:</p><p class="em_th">The coupon is only available on TVpad official website<a href="www.mtvpad.com"> (www.mtvpad.com)</a>.</p>
			</div>
            
            <div class="rule_buttom_d">
            <p class="em_one">Q:</p><p class="em_th">After trading in my old TVpad for the new TVpad4, will my old TVpad still work?</p>
            </div>
            <div class="rule_buttom_d rule_buttom_e">
			 <p class="em_one">A:</p><p class="em_th">We will start to stop the service to these old TVpad. Your old tvpad will not work.</p>
             </div>
             
             <div class="rule_buttom_d">
            <p class="em_one">Q:</p><p class="em_th">Can I trade in for TVpad4 at the local stores? Except the official website, is there any other ways to trade in?</p>
            </div>
            <div class="rule_buttom_d rule_buttom_e">
			 <p class="em_one">A:</p><p class="em_th">You can provide your name, contact, country/region, zip code, MAC address and other information to our customer services, and then we will contact you ASAP. However, we recommend you to trade in on our official website.</p>
             </div>
               
             <div class="rule_buttom_d">
               <p class="em_one">Q:</p><p class="em_th">How long does it take for you to arrange shipment after I finished the payment? And how long does it take to receive the package?</p>
               </div>
               <div class="rule_buttom_d rule_buttom_e">
               <p class="em_one">A:</p><p class="em_th">We will arrange shipment for you within a week after you finished the payment, because our shipment increased a lot during the trade-in program. You can check your order’s arrival time at “Help Center-Logistics”. And please keep yourself in touch before receipt of your package.</p>  
             </div>
            
             <p id="rule_buttom_one">★&nbsp;Or find it on Settings-About-System Info
    Each MAC address can only exchange one coupon, and 
    the coupon can only be used once.
<img src="${resDomain}/en/res/images/tvpad4zt_trade/img2.jpg" alt="TVpad trade-in program"/></p>
             <p id="rule_buttom_two">*TVpad reserves the right to interpret the program.</p>
         </div>
    </div>
</div>
<!--内容区--end-->

<!-- footer -->
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html>
