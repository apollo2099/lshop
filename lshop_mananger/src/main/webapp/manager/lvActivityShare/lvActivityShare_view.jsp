<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" >
		<!--取值 -->
		
		
		
			    <dl>
					<dt>活动名称：</dt>
					<dd>
                       ${lvActivity.activityTitle }
					</dd>				  
				</dl>
				<dl>
				    <dt>活动类型：</dt>
				    <dd>
			           <gv:dictionary type="text" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}" />
				    </dd>
				</dl>
				<dl>
				<dt>活动体系：</dt>
				    <dd>
						<c:foreach items="${mallList }" var="s">
						 <c:if test="${s.mallSystemFlag==lvActivityShare.mallFlag }">${s.mallSystemName }</c:if>
						</c:foreach>
				    </dd>
				</dl>
                 <div class="divider"></div>
                  <div style="display:block" id="givenType">
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
	                   <c:if test="${lvActivityShare.givenTypeNum==1 }">赠送优惠券</c:if>
	                   <c:if test="${lvActivityShare.givenTypeNum==2 }">赠送抽奖机会</c:if>
				    </dd>
				</dl>
				<c:if test="${lvActivityShare.givenTypeNum==1 }">
				<dl>
				    <dt>优惠券：</dt>
				    <dd>
                       ${lvCouponType.name }
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     ${lvActivityShare.grantTotal+lvActivityShare.uncollectedTotal}
				    </dd>
				</dl>
				 <dl>
				    <dt>未领取数目：</dt>
				    <dd>
				     ${lvActivityShare.uncollectedTotal }
				    </dd>
				</dl>
				<dl>
				    <dt>已领取数目：</dt>
				    <dd>
				     ${lvActivityShare.grantTotal }
				    </dd>
			   </dl>
				</c:if>
				<c:if test="${lvActivityShare.givenTypeNum==2 }">
				<dl >
						<dt>抽奖活动：</dt>
						<dd>
				          ${lvActivityShare.givenTypeName }
						</dd>
					</dl>
				<dl>
					<dt>抽奖机会次数：</dt>
					<dd>
						${lvActivityShare.lotteryTotal}
					</dd>				  
				</dl>
				<dl>
					<dt>每日参与活动次数：</dt>
					<dd>
						${lvActivityShare.partakeNum}
					</dd>				  
				</dl>
				</c:if>
			  
                 <div class="divider"></div>
	
                </div>


				<dl>
				    <dt>活动开始时间：</dt>
				    <dd>
                        <s:date name="lvActivity.startTime" format="yyyy-MM-dd HH:mm:ss"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动结束时间：</dt>
				    <dd>
                       <s:date name="lvActivity.endTime" format="yyyy-MM-dd HH:mm:ss"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动状态：</dt>
				    <dd>
						<gv:dictionary type="text" code="ACTIVITY_STATUS" name="lvActivity.status" value="${lvActivity.status }" />
				    </dd>
				</dl>
				<dl class="nowrap">
				    <dt>微信分享连接：</dt>
				    <dd>
				      ${lvActivityShare.shareLink }
				    </dd>
				</dl>
				<dl class="nowrap" style="height: 60px;">
				    <dt>微博分享图片：</dt>
				    <dd>
                     
                      <img src=" ${lvActivityShare.shareImage}" width="60"  height="60"/>
				    </dd>
				</dl>
				<dl class="nowrap">
					<dt>微博分享描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivityShare.shareDesc">${lvActivityShare.shareDesc}</textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>活动描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivity.remark">${lvActivity.remark }</textarea>
					</dd>				  
				</dl>
				
				<div class="divider"></div>
				<dl >
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvActivity.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
					<dl>
					<dt>修改人：</dt>
					<dd>
						${lvActivity.modifyUserName }
					</dd>				  
				</dl>
					<dl>
					<dt>修改时间：</dt>
					<dd>
						<s:date name="lvActivity.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<%--
				<dl>
					<dt>id：</dt>
					<dd>
						${lvActivityShare.id}
					</dd>				  
				</dl>
				<dl>
					<dt>赠送类型名称：</dt>
					<dd>
						${lvActivityShare.givenTypeName}
					</dd>				  
				</dl>
				<dl>
					<dt>赠送物品关联：</dt>
					<dd>
						${lvActivityShare.givenProductCode}
					</dd>				  
				</dl>
				<dl>
					<dt>赠送物品已发放总数：</dt>
					<dd>
						${lvActivityShare.grantTotal}
					</dd>				  
				</dl>
				<dl>
					<dt>赠送物品未领取总数：</dt>
					<dd>
						${lvActivityShare.uncollectedTotal}
					</dd>				  
				</dl>
				<dl>
					<dt>赠送类型字典(1赠送优惠券,2赠送抽奖机会)：</dt>
					<dd>
						${lvActivityShare.givenTypeNum}
					</dd>				  
				</dl>
				<dl>
					<dt>抽奖机会次数：</dt>
					<dd>
						${lvActivityShare.lotteryTotal}
					</dd>				  
				</dl>
				<dl>
					<dt>每日参与活动次数：</dt>
					<dd>
						${lvActivityShare.partakeNum}
					</dd>				  
				</dl>
				<dl>
					<dt>分享图片：</dt>
					<dd>
						${lvActivityShare.shareImage}
					</dd>				  
				</dl>
				<dl>
					<dt>分享描述：</dt>
					<dd>
						
					</dd>				  
				</dl>
				<dl>
					<dt>code：</dt>
					<dd>
						${lvActivityShare.code}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvActivityShare.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${lvActivityShare.modifyTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人编号：</dt>
					<dd>
						${lvActivityShare.modifyUserId}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${lvActivityShare.modifyUserName}
					</dd>				  
				</dl>
				 --%>
		</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
