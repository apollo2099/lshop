<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>
<title>banana商城_发表评价</title>
</head>
<body>
	<%@include file="/web/mbmcn/user_center/c_top.jsp"%>
	
	<article>
		<form action="${MallPath}/web/userOrder!saveComment.action" method="post">
			<input type="hidden" name="lvOrderComment.score" id="scoreid"  value="5"/>
			<input type="hidden" name="lvOrderComment.orderId"   value="${lvOrder.oid}"/>
			<input type="hidden" name="lvOrderComment.contryId" value="${lvOrderAdress.contryId}"/>
			<input type="hidden" name="lvOrderComment.grade" id="gradeId"  value="3"/>	
			<input type="hidden" name="shopFlag"   value="${lvOrder.storeId}"/>	
			<table width="94%" border="0" align="center" style="margin-top: 20px">
				<tr>
					<td width="12%" height="40" align="left" class="fonsi">评分</td>
					<td width="88%" height="40" colspan="2" class="star"><span></span><span></span><span></span><span></span><span></span>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="3" align="left" class="fonsi">评价内容</td>
				</tr>
				<tr>
					<td height="125" colspan="3" align="left" class="fonsi">
						<div class="inputd" style="height:auto;">
							<p class="inbox">
								<textarea class="teare" name="lvOrderComment.content" id="contentid">
长度在5-200个字之间
例如该商品或某功能为您带来的帮助，或使用过程中遇到的问题等
      </textarea>
							</p>
							<div class="tip">
								<em></em> <span>长度在5-200个字</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<td height="120" colspan="3"><input type="submit" value="提 交"
						class="return" /></td>
				</tr>
			</table>
		</form>
	</article>

	<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
		document.getElementById('c_title').innerHTML = '发表评价';
		//星级评论
		var GRADE_TBL = [1,1,2,3,3];
		$('.star').on('click', 'span', function(e){
			var sind = $(this).index();
			$('.star span:lt('+sind+')').attr('style', '');
			$(this).attr('style', '');
			$('.star span:gt('+sind+')').attr('style', 'background-position:-44px 0;');
			$('#gradeId').val(GRADE_TBL[sind]);
			$('#scoreid').val(sind+1);
		});
		//评论正文
		$('#contentid').one('focus', function(e){
			$(this).val('');
		});
		textareaMaxValidator('#contentid', '200', '评论过长，不能大于200个字符!');
		function beforeFormSubmit(form){
			var $c = $('#contentid');
			$c.focus();
			if($c.val().length < 5){
				alert('请输入长度在5-200个字的评论!');
				$c.addClass('error');
			} else {
				$c.removeClass('error');
			}
		}
	</script>
</body>
</html>
