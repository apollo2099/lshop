<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title></title>
</head>
<body>
<script type="text/javascript">
function JumpUrl() {
	var goto_url = '${jumpurl }';
	if(goto_url == ''){
		goto_url = '/index.html';
	}
	location.href = goto_url;
}
setTimeout('JumpUrl()', 200);
</script> 
</body>
</html>

