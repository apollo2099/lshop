<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mtmcn/user_center/c_meta.jsp"%>
<title>TVpad商城_我的评论</title>
</head>
<body>
	<%@include file="/web/mtmcn/user_center/c_top.jsp"%>
	<article>
		<div class="mycomment" style="margin-top: 20px">
			<div class="commentbt">
			<s:if test="#request.action=='mylist'">
				<span id="curspan">我发表的评论（<i>${requestScope.coutMyComment}</i>）</span> 
				<span target="${MallPath}/web/userCenterManage!getReplyList.action">被回复的评论（<i>${requestScope.coutReplyComment }</i>）</span>
			</s:if>
			<s:if test="#request.action=='replylist'">
				<span target="${MallPath}/web/userCenterManage!getCommentList.action">我发表的评论（<i>${requestScope.coutMyComment}</i>）</span> 
				<span id="curspan">被回复的评论（<i>${requestScope.coutReplyComment }</i>）</span>
			</s:if>	
			</div>
			<div class="myconmmentsubdivbox c-pager" totalpage="${page.totalPage}">
			<s:iterator value="page.list" id="c">
				<div class="myconmmentsubdiv">
					<table width="94%" border="0" align="center" <s:if test="#request.action=='replylist'">class="myrecon"</s:if> >
						<tr>
							<td width="25%" height="20" align="left" valign="top">订&nbsp;&nbsp;单&nbsp;&nbsp;号：</td>
							<td width="75%" height="20" valign="top"
								style="text-decoration: underline; color: #0099ff"><s:property value="orderId"/></td>
						</tr>
						<tr>
							<td width="25%" height="20" align="left" valign="top">评论时间：</td>
							<td width="75%" height="20" valign="top"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td width="25%" align="left" valign="top">评论内容：</td>
							<td width="75%" valign="top"><s:property value="content"/></td>
						</tr>
					</table>
					<s:if test="#request.action=='replylist'">
					<div class="logis_tip">
					<s:iterator value="#request.replyList" id="r">
					<s:if test="#r.replyId==#c.id">
					<p>
					 管理员回复：<br />
					<s:property value="#r.content"/>
					</p>
					</s:if>
					</s:iterator>
					</div>
					</s:if>
				</div>
			</s:iterator>
			<c:if test="${page.totalPage>1}">
			<s:if test="#request.action=='mylist'">
			<div nextpage="2" build="buildComment" class="more"><span>查看更多</span></div>
			</s:if>
			<s:if test="#request.action=='replylist'">
			<div nextpage="2" build="buildReplyComment" class="more"><span>查看更多</span></div>
			</s:if>
			</c:if>
			<c:if test="${empty page.list}">
			<div class="myconmmentsubdiv" style="border:none; height:120px; font-size:14px; text-align:center; line-height:120px">
				<s:if test="#request.action=='replylist'">
				暂无回复
				</s:if>
				<s:else>
			            暂无评论
				</s:else>
		     </div>
			</c:if>
		    </div>
		
		</div>
	</article>

	<%@include file="/web/mtmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
		document.getElementById('c_title').innerHTML = '我的评论';
		$('.commentbt').on('click', 'span', function(e){
			var href = $(this).attr('target');
			if(href){
				window.location.href=href;
			}
		});
		//查看更多
		function buildComment(pageNo){
			//取得评论
			var html = '';
			var url = '/web/userCenterManage!pageCommentList.action?page.pageNum='+pageNo;
			$.ajax({
				url: url,
				async: false,
				dataType: 'html',
				success: function(da){
					html = da;
				}
			});
			return html;
		}
		function buildReplyComment(pageNo){
			var html = '';
			var url = '/web/userCenterManage!pageReplyList.action?page.pageNum='+pageNo;
			$.ajax({
				url: url,
				async: false,
				dataType: 'html',
				success: function(da){
					html = da;
				}
			});
			return html;
		}
	</script>
</body>
</html>
