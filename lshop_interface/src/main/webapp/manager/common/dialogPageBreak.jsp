<%@ page language="java" pageEncoding="utf-8"%>

<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select class="combox" name="page.numPerPage" change="dialogPageBreak" param="numPerPage">			
			<option value="20">20</option>
			<option value="50">50</option>
			<option value="100">100</option>
			<option value="200">200</option>
		</select>
		<span>条，共${page.totalCount}条</span>
	</div>
	<div class="pagination" targetType="dialog"
		totalCount="${page.totalCount}" numPerPage="${page.numPerPage}"
		pageNumShown="10" currentPage="${page.pageNum}">
	</div>
</div>