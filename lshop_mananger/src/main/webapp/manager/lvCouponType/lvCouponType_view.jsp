<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo">
		<!--取值 -->
				<dl class="nowrap">
					<dt>优惠券名称：</dt>
					<dd>
						${lvCouponType.name }
					</dd>				  
				</dl>
				<dl >
					<dt>优惠券金额：</dt>
					<dd>
						${lvCouponType.amount }
					</dd>				  
				</dl>
				<dl >
					<dt>生成数量：</dt>
					<dd>
						${lvCouponType.total }
					</dd>				  
				</dl>
				<dl >
					<dt>未发放数目：</dt>
					<dd>
						${lvCouponType.noGrantNumber }
					</dd>				  
				</dl>
				<dl >
					<dt>已发放数目：</dt>
					<dd>
						${lvCouponType.grantNumber }
					</dd>				  
				</dl>
				<dl>
					<dt>已使用数目：</dt>
					<dd>
						${lvCouponType.usedNumber }
					</dd>				  
				</dl>
					<dl class="nowrap">
					<dt>有效期限：</dt>
					<dd>
					<s:date name="lvCouponType.startTime" format="yyyy-MM-dd"/>到<s:date name="lvCouponType.endTime" format="yyyy-MM-dd"/>
					</dd>				  
				</dl>
				<s:if test="lvCouponType.relationType==1">
				<dl class="nowrap">
					<dt>指定商品类别：</dt>
					<dd>
                        <table border="1" class="table" width="90%">
                        <thead>
							<tr>
							<th>所属关系</th>
							<th>商品类别</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="page.list" status="stat" id="item">
							<tr>
							<td>${item.name }</td>
							<td>${item.typeName }</td>
							</tr>
				            </s:iterator>
				        </tbody>
						</table>
					</dd>				  
				</dl>
				</s:if>
                <s:if test="lvCouponType.relationType==2">
				<dl class="nowrap">
					<dt>指定商品：</dt>
					<dd>
                        <table border="1" class="table" width="90%" >
                        <thead>
							<tr>
							<th >所属关系</th>
							<th >商品信息</th>
							<th >商品价格</th>
							<th >创建时间</th>
							<tr>
						</thead>
						<tbody>
							<s:iterator value="page.list" status="stat" id="item">
							<tr>
							<td>${item.name }</td>
							<td>${item.productName }</td>
							<td>${item.price }</td>
							<td><s:date name="lvCouponType.createTime" format="yyyy-MM-dd hh:mm:ss"/></td>
							</tr>
							</s:iterator>
						</tbody>
						</table>
					</dd>				  
				</dl>
				</s:if>
				
				
				<dl class="nowrap">
					<dt>指定金额：</dt>
					<dd>
						${lvCouponType.limitAmount }
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>重复使用：</dt>
					<dd>
					  <s:if test="lvCouponType.reuse==1">是</s:if>
					  <s:if test="lvCouponType.reuse==0">否</s:if>
					</dd>				  
				</dl>
				<c:if test="${lvCouponType.reuse==1 }">
				<dl class="nowrap" id="reuseNumDiv">
					<dt>重复使用次数：</dt>
					<dd>
						${lvCouponType.reuseNum }
					</dd>				  
				</dl>
				</c:if>
				
				<dl class="nowrap">
					<dt>是否启用：</dt>
					<dd>
					<c:if test="${lvCouponType.status==1 }">是</c:if>
					<c:if test="${lvCouponType.status==0 }">否</c:if>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>描述信息：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvProduct.description" readonly="readonly">${lvCouponType.remark }</textarea>
					</dd>				  
				</dl>
			    <dl>
					<dt>币种：</dt>
					<dd>
						${lvCouponType.currency }
					</dd>				  
				</dl>
				<dl >
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvCouponType.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
					<dl>
					<dt>修改人：</dt>
					<dd>
						${lvCouponType.modifyUserName }
					</dd>				  
				</dl>
					<dl>
					<dt>修改时间：</dt>
					<dd>
						<s:date name="lvCouponType.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
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

