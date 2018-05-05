<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="panelBar">
	<div class="pages">
		<span>每页</span>

		<select name="numPerPage" onchange="dwzPageBreak({targetType:'${param.targetType }', rel:'${param.rel}',data: {numPerPage:this.value}})">
			<option value=20 <c:if test="${page.numPerPage == 20 }">selected</c:if>>20</option>
			<option value=50 <c:if test="${page.numPerPage == 50 }">selected</c:if>>50</option>
			<option value=100 <c:if test="${page.numPerPage == 100 }">selected</c:if>>100</option>
			<option value=200 <c:if test="${page.numPerPage == 200 }">selected</c:if>>200</option>
		</select>
		<span>条，共${page.totalCount}条</span>
	</div>
	<div class="pagination" targetType="${param.targetType }" rel="${param.rel}" totalCount="${page.totalCount}" numPerPage="${page.numPerPage}" pageNumShown="10" currentPage="${page.pageNum}"></div>
</div>