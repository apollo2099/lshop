<%@ page language="java" pageEncoding="utf-8" import="com.gv.base.springSecurity.common.pojo.BaseUsers,com.lshop.common.util.Constants,java.util.*"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<div class="headerNav">
	<a class="logo" href="#">标志</a>
	<ul class="nav">
		<li><a href="#">${sessionScope.USER.userName}</a></li>
		<%
		BaseUsers user=	(BaseUsers) session.getAttribute("USER");
		%>
		<%if(user!=null&&user.getStoreFlag()!=null&&!"".equals(user.getStoreFlag())) {%>
		<sec:authorize url="sysInitHtml">
		<li><a id="inithtml" href="javascript:inithtml()">更新緩存</a></li>
		</sec:authorize>
		<%} %>
		<sec:authorize url="sysInitIndex">
		<li><a id="initIndex" href="javascript:initIndex()">初始化索引</a></li>
		</sec:authorize>
		<li><a href="baseUser!bfChangePassword.action" target="dialog" mask="true">设置</a></li>
		<li><a href="#" target="_blank">帮助</a></li>
		<li><a href="<c:url value='/manager/j_spring_security_logout'/>">退出</a></li>
	</ul>
	<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
	</ul>
</div>


<script type="text/javascript">
//更新缓存，生成静态化页面
function inithtml(){
$("#inithtml").attr("disabled","disabled");
$("#inithtml").attr("href","#");
$.ajax({
		  type: "POST",
		  url: "/manager/cache/init!inithtml.action",
		  data: "",
		  dataType:"json",
		  success: function(data){
		      $.msgWin.showMsg('<div></br></br><p><h1>更新缓存,请稍后! </h1></p></br> <p><h1></h1></p><div>'); 
			  setTimeout(function(){$("#msg").animate({right:5,bottom:-250},1000);}, 5500);
			  setTimeout(function(){$("#msg").hide();}, 6500);
		  }
	});

}
//初始化索引
function initIndex(){
   $("#initIndex").attr("disabled","disabled");
   $("#initIndex").attr("href","#");
   $.ajax({
		  type: "POST",
		  url: "/manager/cache/initIndex!initIndex.action",
		  data: "",
		  dataType:"json",
		  success: function(data){
		      $.msgWin.showMsg('<div></br></br><p><h1>初始化索引,请稍后! </h1></p></br> <p><h1></h1></p><div>'); 
			  setTimeout(function(){$("#msg").animate({right:5,bottom:-250},1000);}, 5500);
			  setTimeout(function(){$("#msg").hide();}, 6500);
		  }
	});
}
</script>