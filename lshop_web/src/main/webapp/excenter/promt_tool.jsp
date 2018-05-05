<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@include file="/excenter/common/header.jsp" %>

<script type="text/javascript">
 function changeModel1Content(value)
 {
	  $.ajax({
			 url:'promtManager!getContent.action?id='+value,
		     type:'post',
		     success:function(data)
		     { 
					$("#div1").html(data+"优惠码:"+$("#myCode").val());
					$("#myId").val(value);
		     }
		  });
 }

 function changeModel2Content(value)
 {
	 $.ajax({
		 url:'promtManager!getContent.action?model=2&id='+value,
	     type:'post',
	     success:function(data)
	     { 
			$("#div2").html(data+"优惠码:"+$("#myCode").val());
	     }
	  });
 }

 function clickCopy(value)
 {
   var div="#"+value;
   var txt=$(div).text();
  // window.clipboardData.setData('text',text); 


   if(window.clipboardData) {    
       window.clipboardData.clearData();    
       window.clipboardData.setData("Text", txt);    
} else if(navigator.userAgent.indexOf("Opera") != -1) {    
    window.location = txt;    
} else if (window.netscape) {    
    try {    
         netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");    
    } catch (e) {    
         alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");    
    }    
    var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);    
    if (!clip)    
         return;    
    var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);    
    if (!trans)    
         return;    
    trans.addDataFlavor('text/unicode');    
    var str = new Object();    
    var len = new Object();    
    var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);    
    var copytext = txt;    
    str.data = copytext;    
    trans.setTransferData("text/unicode",str,copytext.length*2);    
    var clipid = Components.interfaces.nsIClipboard;    
    if (!clip)    
         return false;    
    clip.setData(trans,null,clipid.kGlobalClipboard);    
    alert("复制成功！")    
} 


   
 }

 function clickCopyHtml()
 {
   var value=$("#myId").val();
   
   $.ajax({
		 url:'promtManager!getHtmlContent.action?id='+value,
	     type:'post',
	     success:function(data)
	     { 
          var txt=data+"优惠码:"+$("#myCode").val();
	   if(window.clipboardData) {    
	       window.clipboardData.clearData();    
	       window.clipboardData.setData("Text", txt);    
	} else if(navigator.userAgent.indexOf("Opera") != -1) {    
	    window.location = txt;    
	} else if (window.netscape) {    
	    try {    
	         netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");    
	    } catch (e) {    
	         alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");    
	    }    
	    var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);    
	    if (!clip)    
	         return;    
	    var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);    
	    if (!trans)    
	         return;    
	    trans.addDataFlavor('text/unicode');    
	    var str = new Object();    
	    var len = new Object();    
	    var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);    
	    var copytext = txt;    
	    str.data = copytext;    
	    trans.setTransferData("text/unicode",str,copytext.length*2);    
	    var clipid = Components.interfaces.nsIClipboard;    
	    if (!clip)    
	         return false;    
	    clip.setData(trans,null,clipid.kGlobalClipboard);    
	    alert("复制成功！")    
	} 

		  //    window.clipboardData.setData('text',data+"优惠码:"+$("#myCode").val()); 
	     }
	  });
 }

 function copyUrl(txt)
 {
	 if(window.clipboardData) {    
         window.clipboardData.clearData();    
         window.clipboardData.setData("Text", txt);    
 } else if(navigator.userAgent.indexOf("Opera") != -1) {    
      window.location = txt;    
 } else if (window.netscape) {    
      try {    
           netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");    
      } catch (e) {    
           alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");    
      }    
      var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);    
      if (!clip)    
           return;    
      var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);    
      if (!trans)    
           return;    
      trans.addDataFlavor('text/unicode');    
      var str = new Object();    
      var len = new Object();    
      var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);    
      var copytext = txt;    
      str.data = copytext;    
      trans.setTransferData("text/unicode",str,copytext.length*2);    
      var clipid = Components.interfaces.nsIClipboard;    
      if (!clip)    
           return false;    
      clip.setData(trans,null,clipid.kGlobalClipboard);    
      alert("复制成功！")    
 } 
 }
 

 var n=0;
 function go(url)
 {
  n==0?new function(){frames("frame1").location=url,n=1}:null;
  document.all("frame1").readyState!="complete"?setTimeout(go,10):so();
  function so()
  {
	  frames("frame1").document.execCommand("SaveAs"),n=0
  };
 }

 
</script>
<script type="text/javascript">
$(function (){
	   var users=lshop.getCookieToJSON('exuser');
		if(users.userType==1){
		   $("#p4").hide();
		   $("#p5").hide();
		   $("#p6").hide();
		}else{
		   $("#p4").show();
		   $("#p5").show();
		   $("#p6").show();
		}
}
);
</script>

<%@include file="/excenter/common/top.jsp" %>

  <iframe id="frame1" style="display:none"></iframe>
<div class="clear_p"></div>
<div class="clear_p"></div>
<!--banner部份-->
<div class="banner02">
	<ul>
		<ol>人个管理中心</ol>
		<div class="concenter_nv">
			<p><a href="promtManager!promtCodeList.action">我的推广码</a></p>
			<p><a href="/excenter/excenter!basic.action">个人资料</a></p>
			<p><a href="promtManager!getSettledList.action">结算查询</a></p>
			<p id="p4"><a href="promtManager!getToolDetail.action" class="choose">推广工具</a></p>
			<p id="p5"><a href="promtManager!getFundRelate.action">广告基金</a></p>
			<p id="p6"><a href="diffusionPB.html">推广百科</a></p>
		</div>
	</ul>
</div>
<!--主要内容-->
<div class="main_conten3">
	<!--推广工具-->
	<div class="tool">		
		<!--推广工具 左边栏-->
		<div class="tool_left">
			<div class="tool_left01">
				<ol><span class="f_orange">为了让您的推广更得心应手，我们特意为您准备了以下工具，</span>所有工具的推广内容和网页均含有您专属的推广码，便于其他用户购买时直使用您的推广码。（推广内容仅供参考，支持更精彩的原创内容。）</ol>
				<ol class="f_B f_grey">一键分享：</ol>
				<ul>
					<li>
						<!-- JiaThis Button BEGIN -->
						<div id="ckepop">
						<span class="jiathis_txt">分享到：</span>
						<a class="jiathis_button_fb">Facebook</a>
						<a class="jiathis_button_twitter">Twitter</a>
						<a class="jiathis_button_myspace">Myspace</a>
						<a class="jiathis_button_myshare">Myshare</a>
						<a class="jiathis_button_tumblr">Tumblr</a>
						<a class="jiathis_button_msn">MSN</a>
						<a class="jiathis_button_buzz">谷歌Buzz</a><br/>
						<a class="jiathis_button_friendfeed">FriendFeed</a>
						<a class="jiathis_button_tsina">新浪微博</a>
						<a class="jiathis_button_tqq">腾讯微博</a>
						<a class="jiathis_button_qzone">QQ空间</a>
						<a class="jiathis_button_tianya">天涯社区</a>
						<a class="jiathis_button_renren">人人网</a>
						<a href="http://www.jiathis.com/share?uid=1567080" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
						<a class="jiathis_counter_style"></a>
						</div>
						<script type="text/javascript" >
						var jiathis_config={
							data_track_clickback:true,
							url:"http://www.tvpad.hk/index/index.jsp?c=${couponCode}",
							summary:"TVpad一次性付费，0月租无合约，可观看数十套精选中文电视和上万最新影视点播；采用Android系统应用扩展无极限。使用优惠码${couponCode}即可优惠USD30!",
							title:"TVpad—海外华人看中文电视的明智选择！ #TVpad#",
							hideMore:false
						}
						</script>
						<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js?uid=1567080" charset="utf-8"></script>
						<!-- JiaThis Button END -->
					</li>
				</ul>
			</div>
			<div class="tool_left02">
				<form>
				<ol class="f_B f_grey">根据推广方式，选择复制推广内容：</ol>
				<ul>
					<li>1、分享专属推广页面网址：<a href="/index/index_${couponCode}.html" target="_blank">http://www.tvpad.hk/index/index_${couponCode}.html</a> </li>
					<li>2、推广邮件模版</li>
					<li>
					    <input type="hidden" id="myCode" value="${couponCode}"/>
						<div class="tool_left03" id="div1">${promtContent1.textContent}。优惠码:${couponCode}</div>
						<div class="tool_left04">
						   <p>
							    <c:foreach items="${promtContentModel1}" var="promtContent">
							      <input name="n1" type="radio" value="${promtContent.model}"  onclick="changeModel1Content(${promtContent.id})" <c:if test="${promtContent1.id==promtContent.id}">checked="checked"</c:if>	/>${promtContent.formName}<br />
								</c:foreach>
							</p>
							<p>
								<input type="button" value="复制内容" class="button_04" onclick="clickCopy('div1')"/>
								<input type="button" value="复制HTML" class="button_04" onclick="clickCopyHtml()" /><input type="hidden" value="${promtContent1.id}" id="myId"/>
							</p>	
						</div>
					</li>
					<li>3、Facebook、Twitte、新浪微博、QQ、论坛等简短推广内容</li>
					<li>
						<div class="tool_left05" id="div2">${promtContent2.textContent}。优惠码:${couponCode}</div>
						<div class="tool_left06">
							<p style="height:100px;">
							  <c:foreach items="${promtContentModel2}" var="promtContent">
								<input name="n2" type="radio" value="${promtContent.model}" onclick="changeModel2Content(${promtContent.id})" <c:if test="${promtContent2.id==promtContent.id}">checked="checked"</c:if>/> ${promtContent.formName}<br />
							  </c:foreach>
							</p>
							<p>
								<input type="button" value="复制内容" class="button_04" onclick="clickCopy('div2')"/>			
							</p>	
						</div>
					</li>
					<li>4、把推广页面挂在自己的网站上</li>
					<li>
						<div class="tool_left05_1">
						<textarea rows='12' cols='60' class='fm'  id="div3" style="width:465px; height:110px;">


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Tvpad海外华人看中文电视的明智选择</title>
<link href="http://www.tvpad.hk/excenter/css/style_sub.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="layout">
  <div class="header">
   <ul><li><img src="http://www.tvpad.hk/excenter/images/TVpad_01_01.jpg" /></li>
   <li><img src="http://www.tvpad.hk/excenter/images/TVpad_01_02.jpg" /></li>
   <li><img src="http://www.tvpad.hk/excenter/images/TVpad_01_03.jpg" border="0" usemap="#Map" />
       <map name="Map" id="Map">
       <area shape="rect" coords="451,123,612,165" href="http://chat.53kf.com/company.php?arg=lulucute&style=1" target="_blank"/>
    <area shape="rect" coords="621,123,780,165" href="http://www.tvpad.hk/center/quickbuy.action?pid=15" target="_blank"/></map></li></ul>
   </div>
   <div class="price"><p style="font-size:30px; line-height:26px;">${couponCode}</p></div>
   <div class="ensure"></div>
   <div class="video">
     <div class="video_left">
       
               <iframe width="553" height="400" src="http://www.youtube.com/embed/RruL8l0s4qE" frameborder="0" allowfullscreen></iframe>
              <!--  
       		   <script type="text/javascript">				
				var swf_width=540
				var swf_height=400
				var texts='TVpad安裝演示'
				var files='http://www.youtube.com/v/RruL8l0s4qE'
				document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
				document.write('<param name="movie" value="http://www.youtube.com/v/RruL8l0s4qE"><param name="quality" value="high">');
				document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
				document.write('<param name="FlashVars" value="vcastr_file='+files+'&vcastr_title='+texts+'">');
				document.write('<embed src="http://www.youtube.com/v/RruL8l0s4qE" wmode="opaque" FlashVars="vcastr_file='+files+'&vcastr_title='+texts+'& menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />'); document.write('</object>'); 
				</script>
				--> 
    </div>
     <div class="video_right">
     <ul>
        <li>该视频来自于用户的使用体验，方便你通过视频直接了解TVpad的操作界面、丰富内容和清晰画面。</li>
       <li>更多视频：<br />
       1、<a href="http://www.youtube.com/watch?v=Pd0mVFkBkyE">儿童动画节目播放情况</a><br />
       2、<a href="http://www.youtube.com/watch?v=Gr09xf1bQ2g">TVpad安装演示</a></li>
       <li style="width:250px">所有内容来自第三方内容提供方，后续我们将通过应用扩展为您带来更多的精彩节目。
       <p class="fon_O">精彩随行，影音不断。</p></li></ul></div>
  </div>
  <!-- 电视节目-->
  <div class="tv">
        <ol>数十款第三方中文电视直播、点播、教育、音乐等娱乐应用免费提供上百套中文电视直播及数十万部热门影视点播，让你应接不暇，仅需2M带宽即可轻松享用！</ol>
	<ul>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_09.gif" width="45" title="互动新闻台" height="40" border="0" /></a><br />
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="互动新闻台" target="_blank"><p>互动新闻台</p></a></li>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources"><img src="http://www.tvpad.hk/excenter/images/tb_46.gif" width="45"  title="明珠台"  height="40" border="0" target="_blank"/></a><br />
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="明珠台" target="_blank"> <p>明珠台</p></a></li>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_07.gif" width="45"  title="atv world" height="40" border="0" /></a><br />
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="atv world" target="_blank"><p>atv world</p></a></li>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_04.gif" border="0" title="本港台" target="_blank"/></a><br />
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="本港台" target="_blank"><p>本港台</p></a></li>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_03.gif" width="45" height="40" title="J2" border="0" /></a><br />
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="J2" target="_blank"><p>J2</p></a></li>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_10.gif" width="45" height="40"  title="凤凰卫视中文台"  border="0" /></a><br />
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="凤凰卫视中文台" target="_blank"><p>凤凰卫视中文台</p></a></li>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_11.gif" width="45" height="40" title="凤凰卫视资讯台" border="0" /></a><br />
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="凤凰卫视资讯台" target="_blank"><p>凤凰卫视资讯台</p></a></li>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_02.gif" width="45" height="40"  title="高清翡翠台"  border="0" /></a><br /> 
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="高清翡翠台" target="_blank"><p>高清翡翠台</p> </a></li>
				<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_01.gif" width="45" height="40"  title="亚洲高清台"  border="0" /></a><br />
					<a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="亚洲高清台" target="_blank"><p>亚洲高清台</p></a></li>
				 <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_08.gif" width="45" height="40" title="南方卫视"  border="0" /></a><br /> 
				   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="南方卫视" target="_blank"><p>南方卫视</p></a></li>												
    </ul>
	
		<ul>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_20.gif" width="45" height="40" title="CCTV-1" border="0" /></a><br /> 
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-1" target="_blank"><p>CCTV-1</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_21.gif" width="45" height="40" title="CCTV-2" border="0" /></a><br /> 
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-2" target="_blank"><p>CCTV-2</p></a></li>
			  
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_40.gif" width="45" height="40" title="CCTV-4" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-4" target="_blank"><p>CCTV-4</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_22.gif" width="45" height="40" title="CCTV-5" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-5" target="_blank"><p>CCTV-5</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_24.gif" width="45" height="40" title="CCTV-10" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-10" target="_blank"><p>CCTV-10</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_25.gif" width="45" height="40" title="CCTV-11" border="0" /></a><br />
		            <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-11"  target="_blank"><p>CCTV-11</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_26.gif" width="45" height="40" title="CCTV-12" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-12" target="_blank"><p>CCTV-12</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_27.gif" width="45" height="40" title="CCTV-新闻" border="0" /></a><br /> 
				   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-新闻" target="_blank"><p>CCTV-新闻</p> </a></li>
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_37.gif" width="45" height="40" title="CCTV-少儿" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="CCTV-少儿" target="_blank"><p>CCTV-少儿</p></a></li>
				  
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_36.gif" width="45" height="40" title="浙江卫视" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="浙江卫视" target="_blank"><p>浙江卫视</p></a></li>	
			  			 
	    </ul>
				
		<ul>
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_23.gif" width="45" height="40" title="黑龙江卫视" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="黑龙江卫视" target="_blank"><p>黑龙江卫视</p></a></li>  
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_39.gif" width="45" height="40" title="鳳凰衛視香港台" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="凤凰卫视香港台" target="_blank"><p>凤凰卫视香港台</p></a></li> 
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources"><img src="http://www.tvpad.hk/excenter/images/tb_14.gif" width="45" height="40" title="湖南卫视"  border="0" /></a><br />
				   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="湖南卫视" target="_blank"><p>湖南卫视</p></a></li>
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_15.gif" width="45" height="40" title="上海东方" border="0" /></a><br />
				   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="上海东方" target="_blank"><p>上海东方</p></a></li>
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_16.gif" width="45" height="40" title="江苏卫视"  border="0" /></a><br />
				   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="江苏卫视" target="_blank"><p>江苏卫视</p></a></li>
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_38.gif" width="45" height="40" title="北京卫视"  border="0" /></a><br />
				   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="北京卫视" target="_blank"><p>北京卫视</p></a></li>
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_47.gif" width="45" height="40" title="彭博财经"  border="0" /></a><br />
			     <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="彭博财经" target="_blank"><p>彭博财经</p></a></li>
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_32.gif" width="45" height="40" title="风云足球"  border="0" /></a><br />
				   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="风云足球" target="_blank"><p>风云足球</p></a></li>
			   <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_35.gif" width="45" height="40" title="欧洲足球"  border="0" /></a><br />
			   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="欧洲足球" target="_blank"><p>歐洲足球</p></a></li>
			    <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_17.gif" width="45" height="40" title="广东卫视"  border="0" /></a><br />
			   <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" title="广东卫视" target="_blank"><p>广东卫视</p></a></li>
			  
	    </ul>
	
		<ul>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_18.gif" width="45" height="40"  title="国家地理" border="0" /></a><br /> 
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"  title="国家地理" target="_blank"><p>国家地理</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_19.gif" width="45" height="40"  title="探索频道" border="0" /></a><br /> 
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"  title="探索频道" target="_blank"><p>探索频道</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_52.gif" width="45" height="40"  title="香港卫视" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"  title="香港卫视" target="_blank"><p>香港卫视</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_48.gif" width="45" height="40" title="翡翠台" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"  target="_blank"  title="翡翠台"><p>翡翠台</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_50.gif" width="45" height="40" title="生命电视台" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"   title="生命电视台"><p>生命电视台</p></a></li>
			 <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_51.gif" width="45" height="40" title="大爱台" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"   title="大爱台"><p>大爱台</p></a></li>
			 <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_53.gif" width="45" height="40" title="东森新闻" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"   title="东森新闻"><p>东森新闻</p></a></li>
			<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_54.gif" width="45" height="40" title="东森綜合" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"   title="东森綜合"><p>东森綜合</p></a></li>
			<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_55.gif" width="45" height="40" title="东风卫视" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"   title="东风卫视"><p>东风卫视</p></a></li>
			<li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_56.gif" width="45" height="40" title="台视" border="0" /></a><br />
				  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"   title="台视"><p>台视</p></a></li>
	    </ul>
		
	<ul>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank"><img src="http://www.tvpad.hk/excenter/images/tb_57.gif" width="45" height="40"  title="华视" border="0" /></a><br /> 
		  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"  target="_blank"  title="华视"><p>华视</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_58.gif" width="45" height="40"  title="非凡新闻" border="0" /></a><br /> 
		  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"   target="_blank" title="非凡新闻"><p>非凡新闻</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_59.gif" width="45" height="40"  title="三立国际" border="0" /></a><br />
		  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"  target="_blank"  title="三立国际"><p>三立国际</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_60.gif" width="45" height="40" title="唯心电视" border="0" /></a><br />
		  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"  target="_blank"  title="唯心电视"><p>唯心电视</p></a></li>
			  <li><a href="http://www.tvpad.hk/tvpad/01/index.html#resources" target="_blank" ><img src="http://www.tvpad.hk/excenter/images/tb_61.gif" width="45" height="40" title="法界卫视" border="0" /></a><br />
		  <a href="http://www.tvpad.hk/tvpad/01/index.html#resources"  target="_blank"  title="法界卫视"><p>法界卫视</p></a></li>			  
			  <li> <font class="bfont fontwz"><p>……</p></font> </li>
    </ul>		
   </div> 
   <div class="buy">
     <div class="buy_left"><p>${couponCode}</p></div>
     <div class="buy_right"><img src="http://www.tvpad.hk/excenter/images/btn.png" width="233" height="27" border="0" usemap="#Map2" />
     <map name="Map2" id="Map2">
     <area shape="rect" coords="0,0,112,27" href="http://chat.53kf.com/company.php?arg=lulucute&style=1" target="_blank"/> 
     <area shape="rect" coords="121,0,233,27" href="http://www.tvpad.hk/center/quickbuy.action?pid=15" target="_blank"/>
</map></div>
  </div>
  <div class="contrast"><table width="967" border="0" align="center" cellpadding="8"  cellspacing="1" bordercolor="#4f81bd" bgcolor="#4f81bd" style="text-align:center;">
<tr >
				<td width="142" height="0" align="left" bordercolor="#4f81bd" bgcolor="#d7e9ff"><font class=" bfont fontwz">收视方式</font> </td>
		<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="#d7e9ff"><font class="bfont fontwz">卫星接收</font></td>
		<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="#d7e9ff"><font class=" bfont fontwz">当地有线</font></td>
		<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="#d7e9ff"><font class=" bfont fontwz">网路机顶盒</font></td>
		<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="#d7e9ff"><font class=" bfont fontwz fon_O">TVpad  </font></td>
      </tr>
			  <tr>
				<td width="142" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff" ><font class="fontwz">中文直播频道</font> </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">< 50</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">< 10</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">> 50</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"> <font class="fon_O fontwz">> 60</font></td>
      </tr>
			  <tr>
				<td width="142" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fontwz">直播节目类型</font> </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">大陆电视台<br />
				港澳电视台<br />
				台湾电视台<br />
				国际电视台</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">当地电视台为主</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">大陆电视、港澳电视台为主</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fon_O">
				大陆电视台<br />
				港澳电视台<br />
				台湾电视台<br />
				国际电视台</font></td>
      </tr>
			  <tr>
				<td width="142" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fontwz ">影视点播</font></td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"> 无 </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"> 极 少 </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"> 丰 富 </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fon_O ">超过<font class="fontwz">100000</font>部影片日日更新</font></td>
      </tr>
			  <tr>
			    <td height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fontwz ">收费情况</font></td>
                <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">USD500-USD1000</td>
	            <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">USD 150/年</td>
	            <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">USD 300/年</td>
	            <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fon_O">一次性付费无月租</font></td>
	  </tr>
			  <tr>
				<td width="142" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fontwz ">清晰度</font> </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">高 清</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">高 清 </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">标 清    甚至以下 </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fon_O">高清（部分标清）</font> </td>
      </tr>
			  <tr>
				<td width="142" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fontwz">流畅度</font> </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">非常流畅</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">非常流畅 </td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">视用户网路环境而定</td>
				<td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fon_O">2M以上带宽非常流畅</font> </td>
      </tr>
			  <tr>
			    <td height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fontwz">稳定性</font></td>
                <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">不稳定</td>
	            <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">稳定</td>
	            <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">节目源不稳定</td>
	            <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fon_O">稳定</font></td>
	  </tr>
			  
			  <tr>
			    <td height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fontwz">使用者定位</font></td>
	            <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">全球用户</td>
		        <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">当地用户</td>
		        <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff">全球用戶</td>
		        <td width="200" height="0" align="left" bordercolor="#4f81bd" bgcolor="f1f7ff"><font class="fon_O">全球华人专属</font></td>
      </tr>
      </table>
  </div>
  <div class="bottom"><p style="padding-left:220px; font-size:24px; line-height:40px;">${couponCode}</p><p style="padding-left:168px;padding-top:48px;"><a href="http://www.tvpad.hk/center/quickbuy.action?pid=15" target="_blank"><img src="http://www.tvpad.hk/excenter/images/btn_buy.png" border="0" /></a></p>
  </div>
  <div style="text-align:center;"><br/>
   <script language="javascript" type="text/javascript" src="http://js.users.51.la/7189009.js"></script>
   <noscript><a href="http://www.51.la/?7189009" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/7189009.asp" style="border:none" /></a></noscript>	
</div>
</div>
</body>
</html>

</textarea>			
					
											
						</div>
						<div class="tool_left06">
							<p style="height:100px;">															
							</p>
							<p>
								<input type="button" value="复制内容" class="button_04" onclick="clickCopy('div3')"/>								
							</p>	
						</div>
					</li>
					<li>5、线下传单索取<br />
						<dl>如果您有线下华人资源、有合适的渠道，可向TVpad推广中心索取专属传单（印有您的推广码），宣传资料快递费由TVpad推广中心承担，您无须花费任何成本！</dl>
						<dl>如有需要请发送邮件至market@tvpad.hk索取，邮件内容请注明您的详细收货地址，需提供邮编、联系电话、邮箱、索取传单的具体用途。</dl>
					</li>
				</ul>
				</form>
			</div>
		</div>
		<!--推广工具 右边栏-->
		<div class="tool_right">
			<form>			
			<div class="tool_right01">
				<ol>宣传图片（图文结合更吸引眼球）</ol>
				<ul>
				   <c:foreach items="${materialList}" var="material">
				      <li><a href="../upload/${material.bigPath}" target="_blank"><img src="../upload/${material.smallPath}" id="pic1"  title="点击链接至大图" width="250px" height="180px;"/></a></li>
					  <li><input type="button" value="复制图片URL链接" class="button_03" onclick="copyUrl('http://www.tvpad.hk/upload/${material.bigPath}')"/>&nbsp;&nbsp;&nbsp;</li>
				   </c:foreach>
				<!--  
					<li><img src="images/Tpic01.jpg" id="pic1"/></li>
					<li><input type="button" value="复制图片URL链接" class="button_03" onclick="copyUrl(document.getElementById('pic1').src)"/>&nbsp;&nbsp;&nbsp;<input type="button" value="另存为" class="button_04" onclick="go(document.getElementById('pic1').src)"/></li>
					<li><img src="images/Tpic02.jpg" id="pic2"/></li>
					<li><input type="button" value="复制图片URL链接" class="button_03" onclick="copyUrl(document.getElementById('pic2').src)"/>&nbsp;&nbsp;&nbsp;<input type="button" value="另存为" class="button_04" onclick="go(document.getElementById('pic2').src)"/></li>
				-->
				</ul>										
			</div>			
			<div class="tool_right02">				
				<ol>视频素材</ol>
				<ul>
				   <c:foreach items="${videoList}" var="video">
				   <li>
				   <iframe width="250" height="200" src="${video.videoAdd}" frameborder="0" allowfullscreen></iframe>
				  
				     </li>
				     <p class="f_blue f_B">
				     ${video.videoName}
				     </p>
				     <li>
						<p class="pr_right">
					    ${video.videoDesc}
						</p>						
					 </li>
					 <li>
						<p>分享给站外好友</p>
						<p>视频网址：<input type="text" value="${video.videoAdd}" class="input01" id="input1"/> <input type="button" value="复制"  class="button_05" onclick="copyUrl($('#input1').val())"/></p>
					</li>
					<li>
						<p>把视频贴到blog或BBS</p>
						<p>flash地址：<input type="text" value="${video.flashAdd}" class="input01" id="input2"/> <input type="button" value="复制"  class="button_05" onclick="copyUrl($('#input2').val())"/></p>
						<p>Html代码：<input type="text" value="${video.htmlAdd}" class="input01" id="input3"/> <input type="button" value="复制"  class="button_05" onclick="copyUrl($('#input3').val())"/></p>
					</li>
				   </c:foreach>
				</ul>
			</div>
			</form>
		</div>		
	</div>	
  	<div class="clear_p"></div>
  <!--说明文字-->
  <div class="note">
  	说明：<br />
    当您提交申请后,将在最近的一个周三对截止当天所有可申请结算佣金进行审核处理。申请受理后，可申请的佣金归0，该笔费用将记入已支付佣金，请在5个工作日后查账。
  </div>
</div>
<!--版权-->
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
<div style="text-align:center;"><br/>
   <script language="javascript" type="text/javascript" src="http://js.users.51.la/7189009.js"></script>
   <noscript><a href="http://www.51.la/?7189009" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/7189009.asp" style="border:none" /></a></noscript>	
</div>
