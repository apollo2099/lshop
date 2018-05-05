<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad海外高清中文直播電視—TVpad官方商城</title>
<link href="${resDomain}/www/res/css/buy.css" rel="stylesheet" type="text/css" />
<%@include file="/web/www/common/top.jsp" %>
<style type="">
.btn_1 a {
float: left;
margin-left: 40px;
color: #fff;
}
</style>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp" %>
<div class="buy">	
   <div class="message1">
            <ul>
					<li class="pp">
                    <img src="${resDomain}/www/res/images/suc.gif" /><br />
                    綁定成功！<br />
                    您下次可以用<c:if test="${lvUserTh.thType==1}">QQ</c:if><c:if test="${lvUserTh.thType==2}">微博</c:if><c:if test="${lvUserTh.thType==3}">Facebook</c:if>便捷登錄TVpad商城！
                    </li>
				  
				</ul>	
			<div class="btn_1">
                  <a href="/index.html" class="btn04">去購物</a>
                  <a href="http://www.mtvpad.com/web/userCenterManage!getAccount.action"  class="btn04">我的帳號</a>
                  <div class="clear"></div>
                </div>
		</div>		
            								
</div>
</div>
<!--End 购物车-->
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
