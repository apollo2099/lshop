<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>404</title>
<link href="${resDomain}/mbscn/res/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${resDomain}/mbscn/res/js/jquery-1.4.4.min.js" ></script>
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

<article>
     <div class="img404">
        <img src="${resDomain}/mbscn/res/images/404.gif" /> 
     </div>
     <div class="tip404">
        	很抱歉！您访问的页面不存在！<br />
        <font style="color:#ff0000;" id="num"></font>秒钟之后自动跳转至之前页面
     </div>
     
      <div class="returna">
         <a href="${storeDomain }/index.html">返回首页</a>
     </div>
</article>

</body>
</html>




