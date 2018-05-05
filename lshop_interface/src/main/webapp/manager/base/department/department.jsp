<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>

<script type=text/javascript>
$(".departmentClass").contextMenu('departmentCM', {
      bindings:{
            addDepartment:function(t){
               add(t.attr("tvalue"),t.attr("level"),"addDepartment","创建部门");
            },
            editDepartment:function(t){
			   edit(t.attr("tvalue"),"editDepartment","修改部门");			
            },
            deleteDepartment:function(t){
	            if(t.attr("level")==1){
	               return;
	            }else{
	                 del(t.attr("tvalue"));
					}				
            }
      },

      ctrSub:function(t,m){

            var madd = m.find("[rel='addDepartment']");

            var mdel = m.find("[rel='deleteDepartment']");
            
            var medit = m.find("[rel='editDepartment']");
            if(t.attr("level")==1){
            mdel.addClass("disabled");
            }
      }
});

function add(departmentParent,level,lablecode,lablename){
            var doptions = {height:250, width:450, minH:150, minW:410, total:20, max:false, mask:true,maxable:false};
           	var addurl = "baseDepartment!bfAdd.action?departmentParent="+departmentParent+"&level="+level+"&json.navTabId="+$("#navTabId").val();
     		$.pdialog.open(addurl, lablecode, lablename, doptions);

}

function edit(departmentParent,lablecode,lablename){
            var doptions = {height:250, width:450, minH:150, minW:410, total:20, max:false, mask:true,maxable:false};
           	var addurl = "baseDepartment!bfEdit.action?departmentCode="+departmentParent+"&json.navTabId="+$("#navTabId").val();
     		$.pdialog.open(addurl, lablecode, lablename, doptions);

}
function del(departmentCode){
           	var deleteurl = "baseDepartment!delete.action?departmentCode="+departmentCode+"&json.navTabId="+$("#navTabId").val();
				alertMsg.confirm('确定要删除吗?此操作请慎重！', {
					okCall: function(){
						$.post(deleteurl, {ids: 1}, navTabAjaxDone, "json");
					}
				});	

}



</script>

<div class="pageContent" style="padding:5px">
	<div layoutH="12" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
	    <gv:tree name="departmentCode" code="departmentCode" data="${DEPARTMENT_LIST}" parentflag="departmentParent" rootnode="00" type="1" value="departmentCode" isopen="true" href="baseRoles!list.action" labelFlag="departmentName" treeclass="departmentClass" target="ajax" rel="departmentBox" ></gv:tree>
	</div>
	<input type="hidden" id="navTabId" name="json.navTabId" value="${json.navTabId}">
	<div id="departmentBox" class="unitBox" style="margin-left:246px;" layoutH="10">
		<!--#include virtual="list1.html" -->
	</div>
</div>

