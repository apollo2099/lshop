<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" >
               <dl class="nowrap">
				 <dt>团购标题：</dt>
				    <dd>
				     ${lvGroupBuy.title }
				    </dd>
				 </dl>
			     <dl>
					<dt>团购产品：</dt>
					<dd>
				         <s:iterator value="#request.productList" id="product">
							    <c:if test="${lvGroupBuy.productCode==product.code }">${product.productName }</c:if>
						</s:iterator>
					</dd>				  
				</dl>
				
				
				<dl >
				<dt>原价：</dt>
					<dd>
					 ${lvGroupBuy.primeCost }
					</dd>				  
				</dl>
				<dl>
				<dt>折扣：</dt>
					<dd>
                    ${lvGroupBuy.discount }
					</dd>				  
				</dl>
				<dl>
				<dt>现价：</dt>
					<dd>
                    ${lvGroupBuy.presentPrice }
					</dd>				  
				</dl>
				<dl>
				<dt>最低成团人数：</dt>
					<dd>
                    ${lvGroupBuy.groupNum }
					</dd>				  
				</dl>
				<dl>
				<dt>已购买人数：</dt>
					<dd>
                    ${lvGroupBuy.purchasedNum }
					</dd>				  
				</dl>
				<dl>
				<dt>活动开始时间：</dt>
					<dd>
                    <s:date name="lvGroupBuy.startTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动结束时间：</dt>
					<dd>
                   <s:date name="lvGroupBuy.endTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>激活活动标志：</dt>
					<dd>
                     <c:if test="${lvGroupBuy.status==1}">启用</c:if>
                     <c:if test="${lvGroupBuy.status==-1}">停用</c:if>
         
					</dd>				  
				</dl>
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <s:date name="lvGroupBuy.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                        ${lvGroupBuy.modifyUserName }
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<s:date name="lvGroupBuy.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
				<dt>团购地址：</dt>
					<dd>
                       	${lvGroupBuy.groupUrl }
					</dd>				  
				</dl>
				<dl style="height: 100px;">
				<dt>团购图片：</dt>
					<dd>
                       	<c:if test="${lvGroupBuy.gimage!=''}"><img src="${lvGroupBuy.gimage}" width="100"  height="100"/></c:if>
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
