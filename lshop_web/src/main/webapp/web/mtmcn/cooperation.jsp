<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad3 招商</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp"%>
<link href="${resDomain}/mtmcn/res/css/tvpadzt.css" rel="stylesheet" type="text/css">

</head>

<body>
	<header>
		<div class="top">
			<div class="shopping">
				<div class="shoplebg1">
					<div class="shopicon1">
						<a href="/"></a>
					</div>
				</div>
			</div>
			<div class="title">
				<h1>合作招商</h1>
			</div>
		</div>
	</header>
	<article>
		<div class="zaoshan">
			<div>
				<img src="${resDomain}/mtmcn/res/images/zaoshang.jpg" width="100%" />
			</div>
			<div class="why">
				<div class="why_left">
					<div class="why_box">
						<strong>如何加入TVpad事业</strong> 有意着请email联系我们，相关人员将在3-5个工作日内给予答复。<br />
						Emai：<span style="color: #fbfe01">apply@mtvpad.com</span>
					</div>
				</div>
				<div class="why_rith">
					<strong>TVpad是什么</strong>
					<p>TVpad是全球唯一真正意义上的海外旅居人士专属智能机顶盒
						。安装第三方APP可免费看中港台、日韩、欧美等上百个频道及20万部热门影视。您只需要连接一个2M以上的网络，就可以在电视大屏幕上享受体感游戏、家庭KTV等客厅娱乐，目前已有超过10万的全球活跃用户。
					</p>
					<a href="#"> 了解更多>> </a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</article>
	<!-- 分享 -->
	<%@include file="/web/mtmcn/common/share.jsp"%>

	<!-- footer -->
	<%@include file="/web/mtmcn/common/footer.jsp"%>
</body>
</html>