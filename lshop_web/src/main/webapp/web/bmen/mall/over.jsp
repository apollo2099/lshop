<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Errors_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/error_message.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/bmen/res/js/jquery-1.4.4.min.js" ></script>
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
			<p class="wz"><a href="/index.html"><img src="${resDomain}/bmen/res/images/logo.gif" /></a></p>
			<p>
				<s:if test="#request.flag==1">Sorry, this product no longer exists!</s:if>
				<s:elseif test="#request.flag==2">Sorry, this product has been pulled off shelves!</s:elseif>
				<s:elseif test="#request.flag==3">Sorry, this product no longer exists or has been pulled off shelves!</s:elseif>
				<s:else>Sorry! The page you visited does not exist!</s:else>
			</p>
			<p>
				Automatically skip to previous page in <font style="color:#ff0000;" id="num"></font> seconds!
			</p>
			<p><a href="/index.html"><img src="${resDomain}/bmen/res/images/fhsy.jpg" /></a></p>
		</div>
	</body>

</html>
