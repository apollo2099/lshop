<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<script language="javascript" src="${resDomain}/bmcn/res/js/js.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>	
	
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 我的评论</h1> 
				
				<div class="usercenter_box">		
			<div class="indextj commargin1">
		          <div class="product_item2">
		            <ul>
		              <li <s:if test="#request.action=='mylist'"> class="choose" </s:if> id="index1"><a href="/web/userCenterManage!getCommentList.action">我发表的评论(${requestScope.coutMyComment})</a></li>
		              <li <s:if test="#request.action=='replylist'"> class="choose" </s:if> id="index2"><a href="/web/userCenterManage!getReplyList.action">被回复的评论(${requestScope.coutReplyComment })</a></li>
		            </ul>
		          </div>
		          <s:if test="#request.action=='mylist'"> 
		          <div class="tjcontent" id="f_Pic1">
						<div class="comment_list">
							  <ul class="title">
									<li class="wdx1">订单号</li>
									<li class="wdx2">评论时间</li>
									<li class="pl">评论內容</li>
							  </ul>
							  <s:iterator value="page.list" id="c">
						  		<ul>
									<li class="wdx1"><a href="/web/userOrder!viewOrderInfo.action?oid=${c.orderId}"><s:property value="orderId"/></a></li>
									<li class="wdx2"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></li>
									<li class="pl" style="word-break:break-all"> <s:property value="content"/></li>
							  	</ul>
					 		 </s:iterator>		
				  		</div>
				  </div>
			 			<c:if test="${page.totalPage>1}">
						  <u:newPage href="/web/userCenterManage!getCommentList.action?page.pageNum=@"></u:newPage>
						  <script type="text/javascript">
							function toPage(){
								var pageNum = $("#pageValue").val();
							   	window.location.href="/web/userCenterManage!getCommentList.action?page.pageNum="+pageNum;
							
							}
						 </script>
					 </c:if>	
				  </s:if>
		          <!-- End 产品介紹-->
		          <s:if test="#request.action=='replylist'">
		          <div class="tjcontent2" id="f_Pic2">
				  
				  		<div class="comment_list">
							  <ul class="title">
									<li class="wdx1">订单号</li>
									<li class="wdx2">评论时间</li>
									<li class="pl">评论內容</li>
							  </ul>
							  <s:iterator value="page.list" id="c">
								  <ul>
										<li class="wdx1"><a href="/web/userOrder!viewOrderInfo.action?oid=${c.orderId}"><s:property value="orderId"/></a></li>
										<li class="wdx2"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></li>
										<li class="pl" style="word-break:break-all"> <s:property value="content"/></li>
								  </ul>
								  <s:iterator value="#request.replyList" id="r">
								  <s:if test="#r.replyId==#c.id">
									  <ul class="reply">
											<li><font class="redfont">管理员回复:</font></li>
											<li style="word-break:break-all"> <s:property value="#r.content"/></li>
									  </ul>
								  </s:if>
								  </s:iterator>
							  </s:iterator>
				  		</div>
		          </div>
			          <c:if test="${page.totalPage>1}">
						  <u:newPage href="/web/userCenterManage!getReplyList.action?page.pageNum=@"></u:newPage>
						  <script type="text/javascript">
							function toPage(){
								var pageNum = $("#pageValue").val();
							   	window.location.href="/web/userCenterManage!getReplyList.action?page.pageNum="+pageNum;
							
							}
						 </script>
					 </c:if>
		          </s:if>
		          <!-- End 規格參數-->
		
		   
		        </div>
						
		  		</div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		</div>	
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 