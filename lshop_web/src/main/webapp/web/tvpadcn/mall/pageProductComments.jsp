<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<ul class="title" id="productComment">
	<li class="title_s">買家</li>
	<li class="title_s">交易詳情</li>
	<li>評價</li>
</ul>
<c:if test="${not empty objList}">
<c:foreach items="${objList}" var="obj">
<ul class="mytest">
	<li class="short">${obj[1].nickname }<br /><img src="${resDomain }/tvpadcn${obj[0] }" /></li>
<li class="short">${obj[1].pnum }臺<br /><fmt:formatDate value="${obj[1].createTime }"  type="both" /></li>
<li>
	<c:foreach begin="1" end="${obj[1].score}">
		 <img src="${resDomain}/tvpadcn/res/images/wjx.gif" width="30" height="29" />
	</c:foreach>
	<br />
	</li>
	<li class="cImages">
	${obj[1].content }<br />
    <c:if test="${not empty  obj[1].commentImages}">
	<img src="${obj[1].commentImages }" width="200" height="150" />
	</c:if></li>
</ul>
</c:foreach>
<c:if test="${page.totalCount>page.numPerPage}">
<ul class="page">
	共有<span style="color:#f00">${page.totalCount}</span>條記錄&nbsp;
	<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage(1);">首頁</a></c:if><c:if test="${page.pageNum<=1}">首頁</c:if></span>&nbsp;
	<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage('${page.prePage}');">上一頁</a></c:if><c:if test="${page.pageNum<=1}">上一頁</c:if></span>&nbsp;
	<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.nextPage}');">下一頁</a></c:if><c:if test="${page.totalPage<=page.pageNum}">下一頁</c:if></span>&nbsp;
	<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.totalPage}');">尾頁</a></c:if><c:if test="${page.totalPage<=page.pageNum}">尾頁</c:if></span>&nbsp;
	<span style="margin-left:10px">頁次: <span style="color:#f00">${page.pageNum}</span> / ${page.totalPage}</span>&nbsp;
	<span style="margin-left:10px">本頁有<span style="color:red">${fn:length(page.list)}</span>條記錄</span>&nbsp;
	<span style="margin-left:10px">跳轉到
		<select onchange='gotoPage(this.options[this.selectedIndex].value)'>
			<c:foreach var="i" begin="1" end="${page.totalPage}">
				<option  value="${i}" <c:if test='${i==page.pageNum}'>selected="selected"</c:if>>第${i}頁</option>
			</c:foreach>
		</select>
	</span>
</ul>	
</c:if>
</c:if>