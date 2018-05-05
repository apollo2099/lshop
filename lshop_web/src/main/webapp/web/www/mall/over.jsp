<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>錯誤提示_TVpad商城</title>
		<link href="${resDomain}/www/res/css/error_message.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery-1.4.4.min.js" ></script>
		<script type="text/javascript">
			var num = 3;
			function jump(){
				if(num>0){
					$("#num").html(num);
					window.setTimeout('jump()', 1000);
					num=num-1;
				}else{
					if(window.history.length>1){
						window.history.go(-1);
					}else{
						window.location.href="/index.html";
					}	
				}
			}
			
			$(function(){
				jump();
			});
		</script>
	</head>
	
	<body>
		<div class="main">
			<p class="wz"><a href="/index.html"><img src="${resDomain}/www/res/images/logo.gif" /></a></p>
			<p class="none">
				<s:if test="#request.flag==1">很抱歉，该产品不存在！</s:if>
				<s:elseif test="#request.flag==2">很抱歉，该产品已下架！</s:elseif>
				<s:elseif test="#request.flag==3">很抱歉，该产品已下架或不存在！</s:elseif>
				<s:else>很抱歉！您访问的页面不存在！</s:else>
			</p>
			<p>
				<font color="red" id="num"></font>秒鐘之後自動跳轉至之前頁面......
			</p>
			<p><a href="/index.html"><img src="${resDomain}/www/res/images/fhsy.jpg" /></a></p>
		</div>
	</body>

</html>
