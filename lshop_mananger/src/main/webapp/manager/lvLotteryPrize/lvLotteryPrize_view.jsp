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
				    <dt>奖项类型：</dt>
				    <dd>
			        <c:if test="${lvLotteryPrize.prizeType==1}">优惠券</c:if>
			        <c:if test="${lvLotteryPrize.prizeType==2}">实物</c:if>
				    </dd>
				</dl>

			    <dl>
					<dt>奖项名称：</dt>
					<dd>
                    ${lvLotteryPrize.prizeName}
					</dd>				  
				</dl>

				<dl>
				    <dt>奖项数目：</dt>
				    <dd>
                    ${lvLotteryPrize.prizeTotal}
				    </dd>
				</dl>
					<dl>
					<dt>已兑奖数量：</dt>
					<dd>
						${lvLotteryPrize.grantTotal}
					</dd>				  
				</dl>
				<dl>
				    <dt>是否可抽中的奖项：</dt>
				    <dd>
				    <c:if test="${lvLotteryPrize.isDraw==1}">是</c:if>
				    <c:if test="${lvLotteryPrize.isDraw==0}">否</c:if>
				    </dd>
				</dl>

				<dl style="height: 60px;">
				    <dt>奖项图片：</dt>
				    <dd>
                     
                      <img src=" ${lvLotteryPrize.prizeImages}" width="60"  height="60"/>
				    </dd>
				</dl>
				<dl>
					<dt>排序值：</dt>
					<dd>
						${lvLotteryPrize.sortId}
					</dd>				  
				</dl>
			   <dl>
					<dt>创建时间：</dt>
					<dd>
						 <s:date name="lvLotteryPrize.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						 <s:date name="lvLotteryPrize.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>

				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${lvLotteryPrize.modifyUserName}
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
