<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
<div class="pageContent">
    <form enctype="multipart/form-data" method="post" action="lvTplModelMngAction!importExcel.action" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
        <div class="pageFormContent" layoutH="58">
        <p>
                  <label>选择Excel文件：</label>
                   <input type="file" name="file" class="required"/>
         </p>
         <p>
         <label>是否覆盖模板：</label>
         <input type="radio" name="tplFlag" checked="checked" value="1">是<input  name="tplFlag" type="radio" value="0">否
         </p>
          <p>
         <label>是否覆盖内容：</label>
         <input type="radio" name="contentFlag" checked="checked" value="1">是<input name="contentFlag" type="radio" value="0">否
         </p>
         <p>
         <label>自动生成模板页：</label>
         <input type="radio" name="autoTplFlag" value="1" checked="checked">是<input name="autoTplFlag" type="radio" value="0">否
         </p>
         <p>
         <label>自动生成内容页：</label>
         <input type="radio" name="autoContentFlag" value="1" checked="checked">是<input name="autoContentFlag" type="radio" value="0">否
         </p>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>
</div>
</div>