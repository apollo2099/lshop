<%@ page language="java" pageEncoding="utf-8"%>

<form id="pagerForm" method="post"  action="${param.pagerFormAction}" >
	<input type="hidden" name="page.pageNum" value="${page.pageNum }" />
	<input type="hidden" name="rel" value="${param.rel}"/>
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>