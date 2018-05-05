<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${resDomain}/tvpaden/res/js/js.js"></script>
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>

<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> My Comment</span></h1> 
		
		<div class="usercenter_box">		
	<div class="indextj commargin1">
          <div class="product_item">
            <ul>
              <li <s:if test="#request.action=='mylist'"> class="choose" </s:if> id="index1"><a href="/web/userCenterManage!getCommentList.action">My Feedback(${requestScope.coutMyComment})</a></li>
              <li <s:if test="#request.action=='replylist'"> class="choose" </s:if> id="index2"><a href="/web/userCenterManage!getReplyList.action">Seller’s Reply(${requestScope.coutReplyComment })</a></li>
            </ul>
          </div>
          <s:if test="#request.action=='mylist'"> 
          <div class="tjcontent" id="f_Pic1">
				<div class="comment_list">
					  <ul class="title">
							<li class="wdx1">Order   No.</li>
							<li class="wdx2">Date</li>
							<li class="pl">Comment content</li>
					  </ul>
					  <s:iterator value="page.list" id="c">
				  		<ul>
							<li class="wdx1"><a href="/web/userOrder!viewOrderInfo?oid=${c.orderId}"><s:property value="orderId"/></a></li>
							<li class="wdx2"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></li>
							<li class="pl" style="word-break:break-all"> <s:property value="content"/></li>
					  	</ul>
			 		 </s:iterator>
					 <s:if test="page.totalCount>8">
						<ul class="page">
						  <u:page href="/web/userCenterManage!getCommentList.action?page.pageNum=@" language="en">
						  </u:page>
						   <script type="text/javascript">
							<!--
							function gotoPage(num){
							   window.location.href="/web/userCenterManage!getCommentList.action?page.pageNum="+num;
							
							}
							//-->
							</script>
						 
						</ul>	
					</s:if>				
		  		</div>
		  
		  </div>
		  </s:if>
          <!-- End 產品介紹-->
          <s:if test="#request.action=='replylist'">
          <div class="tjcontent2" id="f_Pic2">
		  
		  		<div class="comment_list">
					  <ul class="title">
							<li class="wdx1">Order   No.</li>
							<li class="wdx2">Date</li>
							<li class="pl">Comment content</li>
					  </ul>
					  <s:iterator value="page.list" id="c">
						  <ul>
								<li class="wdx1"><a href="/web/userOrder!viewOrderInfo?oid=${c.orderId}"><s:property value="orderId"/></a></li>
								<li class="wdx2"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></li>
								<li class="pl" style="word-break:break-all"> <s:property value="content"/></li>
						  </ul>
						  <s:iterator value="#request.replyList" id="r">
						  <s:if test="#r.replyId==#c.id">
							  <ul class="reply">
									<li><font class="redfont">Reply from moderators:</font></li>
									<li style="word-break:break-all"> <s:property value="#r.content"/></li>
							  </ul>
						  </s:if>
						  </s:iterator>
					  </s:iterator>
		  		</div>
				 <s:if test="page.totalCount>8">
					<ul class="page">
					  <u:page href="/web/userCenterManage!getReplyList.action?page.pageNum=@" language="en">
					  </u:page>
					   <script type="text/javascript">
						<!--
						function gotoPage(num){
						   window.location.href="/web/userCenterManage!getReplyList.action?page.pageNum="+num;
						
						}
						//-->
						</script>
					 
					</ul>	
				</s:if>	
	  
          </div>
          </s:if>
          <!-- End 規格參數-->

   
        </div>
				
  		</div>
	 <!--End right_Frame-->
</div>
<!--End content-->	
</div>		
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 