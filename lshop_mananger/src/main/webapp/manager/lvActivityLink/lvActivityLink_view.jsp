<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
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
							<gv:dictionary type="text" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}"/>
				    </dd>
				</dl>
                 <div class="divider"></div>
                  <div style="display:block" id="givenType">
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
	                 
	                 <c:if test="${lvActivityLink.givenTypeNum==1 }">赠送优惠券</c:if>
				    </dd>
				</dl>
				<dl>
				    <dt>选择优惠券：</dt>
				    <dd>
			         ${lvCouponType.name }
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				      ${lvActivityLink.grantTotal+lvActivityLink.uncollectedTotal}
				    </dd>
				</dl>
				<dl>
				    <dt>未领取数目：</dt>
				    <dd>
				     ${lvActivityLink.uncollectedTotal }
				    </dd>
				</dl>
				<dl>
				    <dt>已领取数目：</dt>
				    <dd>
				     ${lvActivityLink.grantTotal }
				    </dd>
				</dl>
                 <div class="divider"></div>
				<dl>
				    <dt>每用户最大领取次数：</dt>
				    <dd>
				      ${lvActivityLink.limitNum}
				    </dd>
				</dl>
				
				<dl>
				    <dt>领券策略：</dt>
				    <dd>
						 <gv:dictionary type="text" code="ACTIVITY_LINK_STRATEGYTYPE" name="lvActivityLink.strategyType" value="${lvActivityLink.strategyType }"/>
				    </dd>
				</dl>
                </div>
                <dl>
				    <dt>活动状态：</dt>
				    <dd>
						<gv:dictionary type="text" code="ACTIVITY_STATUS" name="lvActivity.status" value="${lvActivity.status }"/>
				    </dd>
				</dl>

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
				
				<dl class="nowrap">
					<dt>活动描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivity.remark">${lvActivity.remark }</textarea>
					</dd>				  
				</dl>
				
				<dl class="nowrap">
					<dt>活动代码：</dt>
					<dd>
				        <textarea rows="5" cols="60" >${lvActivityLink.activityHtml}</textarea>
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
		</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
