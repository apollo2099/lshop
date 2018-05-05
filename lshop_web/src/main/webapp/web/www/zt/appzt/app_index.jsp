<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad Store應用入駐—TVpad官方商城</title>
<link href="${resDomain}/www/res/css/css_zt_app.css" rel="stylesheet" type="text/css" />
<%@include file="/web/www/common/top.jsp" %>
</head>

<body>
<% request.setAttribute("navFlag","www_tvpadzt"); %>
<%@include file="/web/www/common/header.jsp" %>
<!--End top-->
<div class="application_top Jbg"><img src="${resDomain}/www/res/images/appzt/application_01.jpg" width="1920" height="500" /></div>

<div class="application_part1">
	<ul>
    	<li class="application_part1_left"><img src="${resDomain}/www/res/images/appzt/application_02.jpg" width="520" height="380" /></li>
        <li class="application_part1_right">擁有百萬用戶的海外中文電視第一品牌TVpad
專屬應用商城TVpad Store是基於Andriod系
統的開放平臺，通過這個平臺用戶可以下載安
裝第三方廠商應用。</li>
    </ul>	
</div>


<div class="application_part2">
	<ul>
    	<li class="application_part2_left">TVpad Store應用涉及影視、娛樂、遊戲、幼
教、養生多個種類，已經累計為上百萬OTT用
戶提供應用服務。歡迎有志在我們的平臺施展
技術才華同時獲得更多收益！</li>
        <li class="application_part2_right"><img src="${resDomain}/www/res/images/appzt/application_03.jpg"  /></li>
    </ul>	
</div>

<div class="application_part3">
<p class="application_part3_t">應用入駐流程</p>
	<ul>
    	<li class="posit01">用戶註冊</li>
        <li class="posit02">提交開發者申請</li>
        <li class="posit03">線上簽訂入駐協定</li>
        <li class="posit04">通過資質審核</li>
        <li class="posit05">提交應用</li>
        <li class="posit06">通過安全檢測</li>
        <li class="posit07">應用上架</li>
    </ul>
    <div class="application_part3_b"><a href="http://www.mtvpad.com/web/developer!developerIndex.action" target="_blank"><img src="${resDomain}/www/res/images/appzt/application_05.jpg" width="370" height="100" /></a><br />
      <font style="font-size:20px;">更多問題請聯繫: <a href="mailto:tvpadstore@mtvpad.com">tvpadstore@mtvpad.com</a></font><br />
      <font style="font-size:16px;">(所有諮詢會在3個工作日內處理回復)</font></div>
</div>



<div class="application_part4">
<p class="application_part4_t">經典案例</p>
<ul>
	<li><img src="${resDomain}/www/res/images/appzt/application_06.jpg" width="72" height="72" /><br />Nijishow</li>
    <li><img src="${resDomain}/www/res/images/appzt/application_07.jpg" width="72" height="72" /><br />516</li>
    <li><img src="${resDomain}/www/res/images/appzt/application_08.jpg" width="72" height="72" /><br />時下熱劇</li>
    <li><img src="${resDomain}/www/res/images/appzt/application_09.jpg" width="72" height="72" /><br />泰捷視頻</li>
    <li><img src="${resDomain}/www/res/images/appzt/application_10.jpg" width="72" height="72" /><br />VST全聚合</li>
    <li><img src="${resDomain}/www/res/images/appzt/application_11.jpg" width="72" height="72" /><br />兔子視頻</li>
    <li><img src="${resDomain}/www/res/images/appzt/application_12.jpg" width="72" height="72" /><br />萬花筒視頻</li>
</ul>

</div>

<!-- footer -->
<%@include file="/web/www/common/footer.jsp" %>
<!-- 引入图片居中JS处理-->
<script type="text/javascript">
	    var bgArr=$($(".Jbg")[0]).find("img"),W=window.screen.width,w;
		if(W<=1920){
			w=-parseInt((1920-W)/2)+"px";
			bgArr.css("margin-left",w);
		}
		else{
			w=parseInt((W-1920)/2)+"px";
			bgArr.css("margin-left",w);
		}
</script>
</body>
</html>
