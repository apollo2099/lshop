<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" >
		<!--取值 -->
				<dl>
					<dt>邮件模板名称：</dt>
					<dd>
					${lvEmailTpl.tplDescribe  }
					</dd>				  
				</dl>
				<dl>
					<dt>id：</dt>
					<dd>
						${lvEmailTpl.id}
					</dd>				  
				</dl>
				<dl>
					<dt>key：</dt>
					<dd>
						${lvEmailTpl.tplKey }
					</dd>				  
				</dl>
				
						<dl class="nowrap">
					           <dt>邮件标题：</dt>
					            <dd>
						        ${lvEmailTpl.enTitle}
					           </dd>				  
				         </dl>
				          <dl class="nowrap">
					           <dt>邮件模板(主语言)：</dt>
					            <dd>
						         <textarea readonly="readonly" class="editor" name="lvEmailTpl.en"  rows="15" cols="100" >${lvEmailTpl.en}</textarea>
					            </dd>				  
				         </dl>
						<dl class="nowrap">
					           <dt>邮件模板(次语言)：</dt>
					           <dd>
						       <textarea readonly="readonly" class="editor" name="lvEmailTpl.zh"  rows="15" cols="100" >${lvEmailTpl.zh}</textarea>
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
