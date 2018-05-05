<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
		<!--取值 -->
				<dl>
					<dt>抽奖活动名称：</dt>
					<dd>
						${lvLotteryLogs.activityName}
					</dd>				  
				</dl>
				<dl>
					<dt>中奖物品名称：</dt>
					<dd>
						${lvLotteryLogs.prizeName}
					</dd>				  
				</dl>
				<dl>
					<dt>中奖人名称：</dt>
					<dd>
						${lvLotteryLogs.userName}
					</dd>				  
				</dl>
				<dl>
					<dt>中奖时间：</dt>
					<dd>
						${lvLotteryLogs.createTime}
					</dd>				  
				</dl>
				<dl>
					<dt>是否虚拟中奖记录：</dt>
					<dd>
						<c:if test="${lvLotteryLogs.isSystemFlag==1 }">是</c:if>
						<c:if test="${lvLotteryLogs.isSystemFlag==0 }">否</c:if>
					</dd>				  
				</dl>
		</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
