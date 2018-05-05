<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<script type=text/javascript>
$(".menuclass").contextMenu('menuCM', {
      bindings:{
            addMenu:function(t){
               add(t.attr("tvalue"),"addMenu","创建菜单");
            },
            editMenu:function(t){
	            if(t.attr("level")==1){
	            return;
	            }else{
	                 edit(t.attr("tvalue"),"editMenu","修改菜单");
					}				
            },
            deleteMenu:function(t){
	            if(t.attr("level")==1){
	            return;
	            }else{
	                 del(t.attr("tvalue"));
					}				
            }
      },

      ctrSub:function(t,m){

            var madd = m.find("[rel='addMenu']");

            var mdel = m.find("[rel='deleteMenu']");
            
            var medit = m.find("[rel='editMenu']");
            if(t.attr("level")==1){
            mdel.addClass("disabled");
            medit.addClass("disabled");
            }
      }
});

function add(menuid,lablecode,lablename){
            var doptions = {height:250, width:450, minH:150, minW:410, total:20, max:false, mask:true,maxable:false};
           	var addurl = "baseMenu!bfAdd.action?menuParent="+menuid+"&json.navTabId="+$("#navTabId").val();
     		$.pdialog.open(addurl, lablecode, lablename, doptions);

}

function edit(menuid,lablecode,lablename){
            var doptions = {height:250, width:450, minH:150, minW:410, total:20, max:false, mask:true,maxable:false};
           	var addurl = "baseMenu!bfEdit.action?menuCode="+menuid+"&json.navTabId="+$("#navTabId").val();
     		$.pdialog.open(addurl, lablecode, lablename, doptions);

}
function del(menuid){
           	var deleteurl = "baseMenu!delete.action?menuCode="+menuid+"&json.navTabId="+$("#navTabId").val();
				alertMsg.confirm('确定要删除吗?此操作请慎重！', {
					okCall: function(){
						$.post(deleteurl, {ids: 1}, navTabAjaxDone, "json");
					}
				});	

}



</script>

<div class="page">
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
			<c:if test="${ empty MENU_LIST}">
				<li><a class="add"  href="rights/left_menu_add_root.jsp"  target="dialog" title="创建新菜单" rel="menu_add" width="450" height="200" mask="true"><span>创建新菜单</span></a></li>
			</c:if>
			</ul>
		</div>
		<input type="hidden" id="navTabId" name="json.navTabId" value="${json.navTabId}">
		<div layouth="200">
			<gv:tree name="menuCode" code="menuCode" data="${MENU_LIST}" parentflag="menuParent" rootnode="0" type="1" value="menuCode" isopen="false" labelFlag="menuName" treeclass="menuclass" rel="${json.navTabId}" ></gv:tree>
		</div>
	</div>
</div>
