<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>错误提示_banana商城</title>
		<link href="${resDomain}/bscn/res/css/error_message.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/bscn/res/js/jquery-1.4.4.min.js" ></script>
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
						window.location.href="${storeDomain }/index.html";
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
			<p class="wz"><a href="${storeDomain }/index.html"><img src="${resDomain}/bscn/res/images/logo.gif" /></a></p>
			<p>
				<s:if test="#request.flag==1">很抱歉，该产品不存在！</s:if>
				<s:elseif test="#request.flag==2">很抱歉，该产品已下架！</s:elseif>
				<s:elseif test="#request.flag==3">很抱歉，该产品已下架或不存在！</s:elseif>
				<s:else>很抱歉！您访问的页面不存在！</s:else>
			</p>
			<p>
				<font style="color:#ff0000;" id="num"></font>秒钟之后自动跳转至之前页面......
			</p>	
			<p><a href="${storeDomain }/index.html"><img src="${resDomain}/bscn/res/images/fhsy.jpg" /></a></p>
		</div>
	</body>

</html>
