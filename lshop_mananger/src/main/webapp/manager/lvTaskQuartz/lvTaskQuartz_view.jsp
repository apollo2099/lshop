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
					<dt>id：</dt>
					<dd>
						${lvTaskQuartz.id}
					</dd>				  
				</dl>
				<dl>
					<dt>任务名称：</dt>
					<dd>
						${lvTaskQuartz.taskName}
					</dd>				  
				</dl>
				<dl>
					<dt>目标实例：</dt>
					<dd>
						${lvTaskQuartz.targetObject}
					</dd>				  
				</dl>
				<dl>
					<dt>目标方法：</dt>
					<dd>
						${lvTaskQuartz.targetMethod}
					</dd>				  
				</dl>
				<dl>
					<dt>定时时间：</dt>
					<dd>
						${lvTaskQuartz.targetTime}
					</dd>				  
				</dl>
				<dl>
					<dt>描述：</dt>
					<dd>
						${lvTaskQuartz.description}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${lvTaskQuartz.modifyTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人id：</dt>
					<dd>
						${lvTaskQuartz.modifyUserId}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人：</dt>
					<dd>
						${lvTaskQuartz.modifyUserName}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvTaskQuartz.createTimeString}
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
