<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" >
                 <dl >
				    <dt>结算单号：</dt>
				    <dd>
				     ${lvExtendBalance.balanceId }
				    </dd>
				 </dl>
			     <dl>
					<dt>结算台数：</dt>
					<dd>
				        ${lvExtendBalance.balanceNum }
					</dd>				  
				</dl>
				
				
				<dl >
				<dt>结算金额：</dt>
					<dd>
					 ${lvExtendBalance.balancePrice }
					</dd>				  
				</dl>
				<dl>
				<dt>结算时间：</dt>
					<dd>
                    ${lvExtendBalance.balanceTime }
					</dd>				  
				</dl>
				<dl>
				<dt>优惠码：</dt>
					<dd>
                    ${lvExtendBalance.couponCode }
					</dd>				  
				</dl>
				<dl>
				<dt>用户Email：</dt>
					<dd>
                    ${lvExtendBalance.userEmail }
					</dd>				  
				</dl>
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <s:date name="lvExtendBalance.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                        ${lvExtendBalance.modifyUserName }
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<s:date name="lvExtendBalance.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
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
