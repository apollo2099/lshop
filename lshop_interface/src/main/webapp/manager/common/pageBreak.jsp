<%@ page language="java" pageEncoding="utf-8"%>

<form id="pagerForm" method="post" action="${param.pagerFormAction}">
	<input type="hidden" name="page.pageNum" value="${page.pageNum }" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderby}" />
</form>