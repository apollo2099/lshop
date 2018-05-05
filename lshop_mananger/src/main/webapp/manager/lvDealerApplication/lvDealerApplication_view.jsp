<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
		<!--取值 -->
				<dl>
					<dt>申请人/公司：</dt>
					<dd>
						${lvDealerApplication.applyCmp}
					</dd>				  
				</dl>
				<dl>
					<dt>联系人：</dt>
					<dd>
						${lvDealerApplication.applyName }
					</dd>				  
				</dl>
				<dl>
					<dt>联系电话：</dt>
					<dd>
						${lvDealerApplication.applyTel }
					</dd>				  
				</dl>
				<dl>
					<dt>email：</dt>
					<dd>
						${lvDealerApplication.applyEmail }
					</dd>				  
				</dl>
				<dl  style="height: 30px;">
					<dt>通迅地址：</dt>
					<dd>
						${lvDealerApplication.applyAddr }
					</dd>				  
				</dl>
				<dl>
					<dt>申请代理的区域：</dt>
					<dd>
						${lvDealerApplication.applyArea }
					</dd>				  
				</dl>
				<dl>
					<dt>申请理由：</dt>
					<dd>
						${lvDealerApplication.applyReason }
					</dd>				  
				</dl>
				<dl>
					<dt>申请人/公司计划：</dt>
					<dd>
						${lvDealerApplication.applyIntro }
					</dd>				  
				</dl>
				<dl>
					<dt>营销计划：</dt>
					<dd>
						${lvDealerApplication.applyPlan }
					</dd>				  
				</dl>
				<dl>
					<dt>对TVpad公司的建议：</dt>
					<dd>
						${lvDealerApplication.appySuggest }
					</dd>				  
				</dl>
				<dl>
					<dt>获取招商信息方式：</dt>
					<dd>
						${lvDealerApplication.type }
					</dd>				  
				</dl>
				<dl>
					<dt>其它方式：</dt>
					<dd>
						${lvDealerApplication.otherText }
					</dd>				  
				</dl>
				<dl>
					<dt>所属商城：</dt>
					<dd>
						<cus:store param="storeName" shopFlag="${lvDealerApplication.storeId }"></cus:store>
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<fmt:formatDate value="${lvDealerApplication.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
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
