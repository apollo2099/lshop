<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<gv:esi uri="/web/mbmcn/common/footer.jsp">
<article>
	<div class="fastchannel"></div>
	<div class="mark1" id="mark1"></div>
	<div class="maketips">
		<div class="fastnav">
			<a href="${storeDomain}/web/userOrder!getOrderlist.action"
				id="myorder"></a> <a
				href="${storeDomain}/web/mall!getShopCartList.action" id="mycatea"></a>
			<a href="/web/userCenterManage!getAccount.action" id="usersa"></a> <a
				href="#" id="tvpada"></a> <a
				href="${storeDomain}/" id="homscolse"></a>
		</div>
	</div>
</article>
<footer>
	<div class="lo_su_top">
		<a id="loginId" href="javascript:toLo('${storeDomain }');">登录</a> <a
			id="registerId" href="javascript:toRe('${storeDomain }');"
			class="suba">注册</a> <a id="myorderId" style="display: none;"
			href="${storeDomain}/web/userOrder!getOrderlist.action">我的订单</a> <a
			id="logoutId" style="display: none;"
			href="${storeDomain}/web/userCenterManage!logout.action" class="suba">退出</a>
		<a href="javascript:scroll(0,0)" class="totopa">返回顶部</a>
	</div>
	<div class="computer">
		<a href="http://www.bananatv.com">电脑版</a>
	</div>
	<div class="coperight">
		<dd>
			<cus:store param="footerinfo" />
		</dd>
	</div>
</footer>
<!-- 第三方统计代码 -->
<cus:store param="statcode" />
<script type="text/javascript">
$(function(){
var mark1=document.getElementById("mark1");
function markClose(){
	$('#mark1').hide();
	$(".maketips").hide();
	$('.mark_tip').hide();
	$(window).unbind("scroll");
	   $(window).unbind("resize");
	$(".fastchannel").removeAttr("id");
}
var hammertiem=Hammer(mark1,{
 		prevent_default:true
 	}).on("tap",markClose).on('drag', markClose);
$(".fastchannel").click(function(){
		chanCenter($(".maketips"));
		$(this).attr("id","fastchannel");
	});
function chanCenter(obj){
	var screenWidth = $(window).width(), screenHeight = $(window).height();
	var objLeft = (screenWidth - obj.width())/2 ;
	   var objTop = (screenHeight - obj.height())/2;
	   var srollTop=$(document).scrollTop();
	   obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	$(".mark1").show();
		
	$(window).resize(function(){
		var screenWidth = $(window).width(), screenHeight = $(window).height();
		var objLeft = (screenWidth - obj.width())/2 ;
	    var objTop = (screenHeight - obj.height())/2;
		var srollTop=$(document).scrollTop();
	    obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	});
	 $(window).scroll(function(){
		var screenWidth = $(window).width(), screenHeight = $(window).height();
		var objLeft = (screenWidth - obj.width())/2 ;
	    var objTop = (screenHeight - obj.height())/2;
		var srollTop=$(document).scrollTop();
	    obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	});
}

});
</script>
<script>
//表单错误提示焦点问题
$(document).delegate('.tip', 'click', function(e){
	var $t = $(this);
	if($t.is(':visible') == true){
		$t.hide().prev('.inpu').focus();
	}
});
$(document).delegate('select','change', function(e){
	$(this).find('option[value=""]').remove();
});
</script>
</gv:esi>