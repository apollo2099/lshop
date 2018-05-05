<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品阶梯价_banana商城</title>
		<link href="${resDomain}/bscn/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bscn/res/css/user_center.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<div class="pop_up_box">
			<div class="tips">
				<div class="ladder_price">
				<ul class="title">
					<li class="title">订购数量</li>
					<c:if test="${not empty ladderList}">
						<c:foreach items="${ladderList}" var="lader1">
							<c:choose>
								<c:when test="${empty lader1.upInterval and empty lader1.downInterval}">
									<li>0台</li>
								</c:when>
								<c:when test="${not empty lader1.upInterval and empty lader1.downInterval}">
									<li>${lader1.upInterval }台以上</li>
								</c:when>
								<c:when test="${empty lader1.upInterval and not empty lader1.downInterval}">
									<li>${lader1.downInterval }台以下</li>
								</c:when>
								<c:otherwise>
									<li>${lader1.upInterval }-${lader1.downInterval }台</li>
								</c:otherwise>
							</c:choose>
						</c:foreach>
					</c:if>
				</ul>	
				<ul class="value">
					<li class="title">价格（每台）</li>
					<c:foreach items="${ladderList}" var="lader2">
						<li>USD ${lader2.price }</li>
					</c:foreach>
				</ul>
				</div>	
			</div>
		</div>
	</body>
</html>
