<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>抱歉,你请求的页面未找到!</title>
<style type="text/css">
<!--
body{ font-size:12px;}
A:link {
 font-size:12px;}
A:visited {
 font-size:12px;}
A:hover {
 font-size:12px;}
A:active {
 font-size:12px;}
.STYLE1 {
	color: #FF6600;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><div align="center"><img src="${pageContext.request.contextPath }/common/images/wrongicon.jpg" width="95" height="127" /><br />
    </div></td>
    <td><span class="STYLE1">很抱歉，出错啦！</span><br />
      <br />
    - 错误原因：<s:actionerror/><s:actionmessage/><s:fielderror /><br />
    <br />    
    </td>
  </tr>
  <tr>
    <td height="40" colspan="2"><label>
      <div align="center">
        <input type="button" name="Submit" value="返回" onclick="javascript:history.back();"/>
        </div>
    </label></td>
  </tr>
</table>
</body>
</html>