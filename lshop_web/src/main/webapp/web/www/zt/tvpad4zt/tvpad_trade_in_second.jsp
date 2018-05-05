<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad以舊換新</title>
<link href="${resDomain}/www/res/css/css_zt4_trade.css" rel="stylesheet" type="text/css" />
<%@include file="/web/www/common/top.jsp" %>


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
		//验证输入mac是否为空
		if (macCode == null || macCode.length <= 0) {
			alert("请输入正确的MAC码");
			return false;
		}

		//验证码格式是否正确
		if (!/^ac867e[a-fA-F0-9]{6}$/.test(macCode.toLowerCase())) {
			alert("请输入正确的MAC码");
			return false;
		}

		//验证cookie是否已经存在该验证码
		var macCookie = getCookie("macCookie");
		if (macCookie != null && macCookie.length > 0 && macCookie == macCode) {
			alert("该MAC码已经领取过优惠券了！");
			return false;
		} else {
			setCookie("macCookie", macCode,1)
		}
		return true;
	}
</script>

</head>

<body>
<% request.setAttribute("navFlag","www_tvpadzt"); %>
<%@include file="/web/www/common/header.jsp" %>
<!--End top-->

<!--内容区-->
<div id="content_xz">
	<div id="ad_one">
    	<div id="ad_img">
            <img src="${resDomain}/www/res/images/tvpad4zt_trade/bananer1.jpg" usemap="#Map" alt="TVpad以旧换新" /> 
            <map name="Map" id="Map">
                <area shape="rect" coords="72,339,280,405" href="http://en.mtvpad.com/2/index.html" target="_blank" />
            </map>
        </div>
    </div>
    
    <div id="notice">
    	<div id="notice_x">
    		<span>親愛的老用戶，很抱歉通知您因後臺系統升級，舊TVpad因硬體配置過低將無法繼續獲取支持，即將停止服務。<em>爲此我們特意推出以舊换新活動，您可憑舊機Mac碼獲取130美金優惠券，以169美金/臺的價格獲取TVpad最新機型TVpad4。</em></span>
    	</div>
    </div>
    
    <div id="coupon">
    	<div id="coupon_cash">
        	<img src="${resDomain}/www/res/images/tvpad4zt_trade/cou.jpg" alt="TVpad以旧换新" / >
             <form class="cou_search" action="http://www.mtvpad.com/web/activity!getCouponByActivityLinkLogout.action?activityCode=dc950d8ed8224021821cdf94330c2abc" method="post" onsubmit="return checkMac()">
            	<input id="macCode" name="macCode" class="cousearch_tet" type="text" value="輸入舊機Mac碼領取" onfocus="if(this.value == this.defaultValue) this.value = ''" onblur="if(this.value == '') this.value = this.defaultValue"/>
                <input class="cousearch_sub" type="submit" value="" >
            </form>
            <div class="clear"></div>
        </div>
        <div id="flow">
        	<div id="flow_h"><h1>官網換購流程</h1></div>
            <div id="flow_chart">
            	<div class="chart"><p>輸入Mac碼<br/>領取130美元<br/>優惠券</p></div>
                <div class="chart"><p>登陸TVpad<br/>商城下單購買<br/>TVpad4</p></div>
                <div class="chart"><p>在結算頁面<br/>輸入優惠券碼<br/>憑券立即減免<br/>130美金</p></div>
                <div class="chart"><p id="chart">進行付款<br/>完成換購</p></div>
                <div class="clear"></div>
            </div>
        </div>

        <div id="video" >
      		<div id="video_h"><h1>詳細購物流程請觀看以下視頻:</h1></div>
			<div id="video_con">
				<iframe src="https://www.youtube.com/embed/pC4vc-V3WVg" allowfullscreen="" frameborder="0" width="855" height="508"></iframe>
			</div>
		</div>
       
        <div id="contact">
            <div id="contact_left">
                <h2>若有疑問，請聯繫7*24小時客服服務中心：</h2>
                <img src="${resDomain}/www/res/images/tvpad4zt_trade/icon_01.jpg" alt="TVpad以旧换新"/><p>24小時電話客服：<em>（00852）21349910  /（00852）21349920 <span>（00852）21349901</span></em></p>
                <img src="${resDomain}/www/res/images/tvpad4zt_trade/icon_03.jpg" alt="TVpad以旧换新"/><p>24小時客服QQ：<em> &nbsp;2389075307 / 2276814617</em></p>
                <img src="${resDomain}/www/res/images/tvpad4zt_trade/icon_05.jpg" alt="TVpad以旧换新"/><p>EMAIL：<em>service@mtvpad.com</em></p>
        	</div>
            <div id="contact_right">
            	<img src="${resDomain}/www/res/images/tvpad4zt_trade/erweima.jpg" alt="TVpad以旧换新">
                <p>TVpad官方微信</p>
            </div>
        </div>
    </div>
    <div id="ad_two">
    	<div id="adtwo_img">
    		<img src="${resDomain}/www/res/images/tvpad4zt_trade/bananer2.jpg" alt="TVpad以旧换新"/>
        </div>
    </div>
    <div id="rule">
    	<div id="rule_top">
        	<h1>活動細則</h1>
            <p>1、活動時間：以彈窗通知時間為準</p>
            <p>2、參與活動的條件：TVpad老用户憑舊機MAC碼，均有資格參與此次以舊換新活動，每個MAC碼只有一次參與機會。</p>
            
            <p>3、活動優惠券只限于TVpad商城TVpad4單品使用，優惠券存在有效期，請儘快使用。</p>
        </div>
        
        <div id="rule_buttom">
        	<h1>常見問題</h1>
            <p><em class="em_one">Q</em>: 在哪裡查看MAC碼？一個MAC碼能重複使用嗎？<br/>
			   <em class="em_one">A</em>: 請在盒子的背面查看（附圖）<br/>
               <img src="${resDomain}/www/res/images/tvpad4zt_trade/img1.jpg" alt="TVpad以旧换新"/></p>
			
            <p><em class="em_one">Q</em>: 在哪裡輸入優惠券？<br/>
			   <em class="em_one">A</em>: 請在“填寫核對訂單”頁面的左下角，點擊“（+）使用優惠券抵消部分總額，單筆訂單僅可使用一次”處輸入優<em class="em_two">惠券碼，並點擊“確定”按鈕，即可減免相應金額。請注意優惠券的使用日期，為確保您的權益，請儘快使用！</em></p>
			
            <p><em class="em_one">Q</em>: 優惠券是否一定要在TVpad商城使用？Amazon,Aliexpress授權店鋪可以用嗎？<br/>
			   <em class="em_one">A</em>: 優惠券是TVpad官網專屬(www.mtvpad.com)，其他線上管道途徑無效</p>
			
            <p><em class="em_one">Q</em>: 兌換新TVpad4的盒子後，舊盒子還能繼續使用嗎？<br/>
			   <em class="em_one">A</em>: 舊盒子即將停機，無法繼續使用，無法繼續使用！請抓緊時間換新機。現在換新機立減130元美金，包郵送貨上門！</p>
            
            <p><em class="em_one">Q</em>: 支付完成之後幾天可以發貨？大概多少天可以收貨？<br/>
			   <em class="em_one">A</em>: 因以舊換新期間發貨量巨大，支付之後約一個星期之內發貨，具體各地區到貨時間可到幫助中心物流配送中查看。<em class="em_two">期間請您保持通訊方式順暢，確保順利收貨。</em></p>
               
             <p id="rule_buttom_one">★如標籤汙損或脫落，請在盒子系統設置-關於-系統資訊中查看一個MAC碼只能兌換一張優惠券，不能重複使用。請抓緊機會！<img src="${resDomain}/www/res/images/tvpad4zt_trade/img2.jpg" alt="TVpad以旧换新"/></p>
             <p id="rule_buttom_two">*TVpad官方在法律範圍內擁有本活動的最終解釋權</p>
         </div>
    </div>
</div>
<!--内容区--end-->


<!-- footer -->
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
