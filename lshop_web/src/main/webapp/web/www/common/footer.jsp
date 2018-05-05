<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="help" uri="/WEB-INF/tld/gv-help.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="store" uri="/WEB-INF/tld/gv-store.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<gv:esi uri="/web/www/common/footer.jsp">
<!-- 正品保障 -->
<div class="zpbz">
   <div class="jybz">
      <p class="zpbz_p">
        <span>正品保證</span>
        <span>0月租</span>
      </p>
      <p class="jybz_p">
        <span>交易保障</span>
        <span>第三方支付平臺</span>
      </p>
      <p class="qqfw_p">
        <span>DHL/UPS/TNT</span>
        <span>全球範圍安全放心</span>
      </p>
       <p class="shzc_p">
        <span>售後政策</span>
        <span>質量問題7天退換1年保修</span>
      </p>
   </div>
</div>
<!-- help center -->
<help:help></help:help>
<div class="bottom">
  <div class="content"> 		
  		<cus:store param="footerinfo"/>
  </div>
</div>
<!-- 回到项部 -->
<div id="topcontrol" style="display:none;">
	<div class="attention">
	  <img src="${resDomain}/www/res/images/attention_03.jpg" width="50" height="50">
	  <span>關注<font class="fo_en">TVpad</font></span>
	</div>
	<div class="re_top">
	 <img src="${resDomain}/www/res/images/top_er.png" width="37" height="36">
	 <a href="#sc_top">返回頂部</a>
	</div>  
	<div class="topcontrol_bg">
	<img src="${resDomain}/www/res/images/attention_03.jpg" width="122" height="122">
	<span><font class="fo_en">TVpad</font>官方微信</span>
	</div>
</div>
<script type="text/javascript">
$('#topcontrol .attention').hover(function(e){
	$('#topcontrol .topcontrol_bg').show();
}, function(e){
	$('#topcontrol .topcontrol_bg').hide();
});
$('#topcontrol .topcontrol_bg').hover(function(e){
	$(this).show();
}, function(e){
	$(this).hide();
});
$('#topcontrol .re_top').click(function(e){
	$('html,body').animate({
		scrollTop : '0px'
	}, 300);
});
$(window).scroll(function(){
	var t = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
    if( t >= 150 ) {
    	$('#topcontrol').css("display","block");
    } else {
    	$('#topcontrol').css('display', 'none');
    }
});
</script>
<!--End Bottom -->
<!-- 第三方统计代码 -->
<cus:store param="statcode"/>
</gv:esi>