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
					<dt>活动英文名称：</dt>
					<dd>
						${lvActivity.activityTitleEn }
					</dd>				  
				</dl>
				<dl>
				    <dt>活动类型：</dt>
				    <dd>
			           <gv:dictionary type="text" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}"/>
				    </dd>
				</dl>
				

				
				
				<dl class="nowrap">
				<dt>指定商品：</dt>
				    <dd>
				    </dd>
				</dl>
				
				 
				<table id="tab" class="table" width="90%"  >
				   <thead>
				   <tr>
						<th width="5%">ID</th>
					    <th width="20%">所属关系</th>
					    <th width="45%">商品名称</th>
						<th width="35%">商品价格</th>
				   </tr>
				   </thead>
				   <tbody id="tabDetails">
				   <c:foreach items="${acProductList }" var="item">
				   <tr>
				     <td>${item.id }</td>
				     <td>${item.storeName }</td>
				     <td>${item.productName }</td>
				     <td>${item.currency} ${item.price}</td>
				   </tr>
				   </c:foreach>
				   </tbody>
			   </table>
			   
			  <div class="divider"></div>
			  	<dl>
				    <dt>活动优惠金额：</dt>
				    <dd>
				     ${lvActivityMac.amount }
				    </dd>
				</dl>
				<dl>
				    <dt>Mac兑换次数：</dt>
				    <dd>
				     ${lvActivityMac.exchangeNum }
				    </dd>
				</dl>
				


				<dl>
				    <dt>活动开始时间：</dt>
				    <dd>
                        <s:date name="lvActivity.startTime" format="yyyy-MM-dd HH:mm:ss"/>
				    </dd>
				</dl>
				
				<dl>
				    <dt>活动状态：</dt>
				    <dd>
						<gv:dictionary type="text" code="ACTIVITY_STATUS" value="${lvActivity.status}"/>
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
