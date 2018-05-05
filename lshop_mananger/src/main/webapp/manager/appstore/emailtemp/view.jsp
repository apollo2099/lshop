<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<dt style="text-align: right;">店铺标识：</dt>
				<dd>
					<select name="templ.mallSign">
						<option value="bmcn"
							<c:if test="${templ.mallSign=='|bmcn|bscn|mbmcn|mbscn|' }">selected="selected"</c:if>>banana中文</option>
						<option value="bmen"
							<c:if test="${templ.mallSign=='|bmen|bsen|mbmen|mbsen|' }">selected="selected"</c:if>>banana英文</option>
						<option value="tvpadcn"
							<c:if test="${templ.mallSign=='|www|tvpadcn|mtmcn|mtscn|' }">selected="selected"</c:if>>华扬中文</option>
						<option value="tvpaden"
							<c:if test="${templ.mallSign=='|en|tvpaden|mtmen|mtsen|' }">selected="selected"</c:if>>华扬英文</option>
					</select>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt style="text-align: right;">模版分类：</dt>
				<dd>
					<select name="templ.ttype">
						<option value="1"
							<c:if test="${templ.ttype==1 }">selected="selected"</c:if>>开发者审核通过</option>
						<option value="2"
							<c:if test="${templ.ttype==2 }">selected="selected"</c:if>>开发者审核不通过</option>
						<option value="3"
							<c:if test="${templ.ttype==3 }">selected="selected"</c:if>>应用审核通过</option>
						<option value="4"
							<c:if test="${templ.ttype==4 }">selected="selected"</c:if>>应用审核不通过</option>
					</select>
				</dd>
			</dl>

			<dl>
				<dt style="text-align: right;">模版标题：</dt>
				<dd>
					<input name="templ.name" type="text" value="${templ.name }"
						style="width: 400px;" maxlength="32" class="required" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt style="text-align: right;">邮件标题：</dt>
				<dd>
					<input name="templ.title" type="text" value="${templ.title }"
						style="width: 400px;" maxlength="32" class="required" />
				</dd>
			</dl>

			<dl class="nowrap">
				<dt style="text-align: right;">邮件模板：</dt>
				<dd>
					<textarea class="editor" name="templ.content" rows="12" cols="80">${templ.content }</textarea>
				</dd>
			</dl>


		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>