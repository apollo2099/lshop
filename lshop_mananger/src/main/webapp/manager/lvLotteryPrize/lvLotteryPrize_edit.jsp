<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvLotteryPrizeAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input name="lvLotteryPrize.id" type="hidden" size="30" value="${lvLotteryPrize.id}"/>
				<input name="lvLotteryPrize.activityCode" type="hidden" size="30" value="${lvLotteryPrize.activityCode}"/>
				<input name="lvLotteryPrize.code" type="hidden" size="30" value="${lvLotteryPrize.code}"/>
				<input name="lvLotteryPrize.grantTotal" type="hidden" size="30" value="${lvLotteryPrize.grantTotal}"/>
				<input name="lvLotteryPrize.prizeCode" type="hidden" size="30" value="${lvLotteryPrize.prizeCode}"/>
				<input name="lvLotteryPrize.prizeType" type="hidden" size="30" value="${lvLotteryPrize.prizeType}"/>
				<input name="lvLotteryPrize.prizeImages" type="hidden" size="30" value="${lvLotteryPrize.prizeImages}"/>
				<input name="lvLotteryPrize.modifyTime" type="hidden" size="30" value="${lvLotteryPrize.modifyTime}"/>
				<input name="lvLotteryPrize.createTime" type="hidden" size="30" value="${lvLotteryPrize.createTime}"/>
				
				<dl>
				    <dt>奖项类型：</dt>
				    <dd>
				     <select disabled="disabled">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvLotteryPrize.prizeType==1 }">selected="selected"</c:if>>优惠券</option>
						<option value="2" <c:if test="${lvLotteryPrize.prizeType==2 }">selected="selected"</c:if>>实物</option>
					  </select>
				    </dd>
				</dl>

			    <dl>
					<dt>奖项名称：</dt>
					<dd>
						<input name="lvLotteryPrize.prizeName" type="text" size="32" maxlength="32" value="${lvLotteryPrize.prizeName}"/>
					</dd>				  
				</dl>

				<dl>
				    <dt>奖项数量：</dt>
				    <dd>
				     <input name="lvLotteryPrize.prizeTotal" type="text" size="32" maxlength="6" class="digits" value="${lvLotteryPrize.prizeTotal}" readonly="readonly"/>
				    </dd>
				</dl>

				<dl>
				    <dt>追加数量：</dt>
				    <dd>
				     <input name="lvLotteryPrize.addNumber" type="text"  maxlength="6"  size="32" class="digitsNoZore"/>
				    </dd>
				</dl>
				
				<dl>
				    <dt>奖项图片：</dt>
				    <dd>
				     <input name="img" type="file"  maxlength="64" size="30"/>
				    </dd>
				</dl>
				<dl>
				    <dt>是否可抽中的奖项：</dt>
				    <dd>
				     <select name="lvLotteryPrize.isDraw">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvLotteryPrize.isDraw==1 }">selected="selected"</c:if>>是</option>
						<option value="0" <c:if test="${lvLotteryPrize.isDraw==0 }">selected="selected"</c:if>>否</option>
					  </select>
				    </dd>
				</dl>
				<dl>
				    <dt>排序值：</dt>
				    <dd>
				   <input name="lvLotteryPrize.sortId" type="text" size="32" maxlength="5" class="required digits" value="${lvLotteryPrize.sortId }"/>
				    </dd>
				</dl>

			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="close">取消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>