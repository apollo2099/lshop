<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>免费领TVpad4啦！</title>
<link href="${resDomain}/mtmcn/res/weixin/css/css.css?v=20150207" rel="stylesheet" type="text/css">
</head>

<body>
  <article>
    <div class="tv4_yhhd1">
     <div class="tv4_mfn">
       <img src="${resDomain}/mtmcn/res/weixin/images/tvpad_im.jpg" width="87.5%" />
     </div>
    </div>
  </article>
  
  <article>
     <div class="h_ti">排行榜</div>
     <div class="phb">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td height="40" align="center" valign="middle"><strong>排名</strong></td>
			    <td height="40" align="center" valign="middle"><strong>总金额 </strong></td>
			    <td height="40" align="center" valign="middle"><strong>用户名</strong></td>
			  </tr>
			  <c:foreach var="item" items="${mapList}">
			  <tr>
			    <td height="40" align="center" valign="middle">${item["rankNum"]}</td>
			    <td height="40" align="center" valign="middle">${item["obtainAmount"]}</td>
			    <td height="40" align="center" valign="middle">${item["nickname"]}</td>
			  </tr>
			  </c:foreach>
		</table>
    </div>
</article>
</body>
</html>
