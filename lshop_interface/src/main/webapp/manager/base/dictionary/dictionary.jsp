<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>

<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>

<script type=text/javascript>
$(".dictionarymenuclass").contextMenu('dictionaryMenuCM', {
      bindings:{
            addMenu:function(t){
	        if(t.attr("level")!=1){
                return;
	        }else{
               add(t.attr("code"),"addMenu","创建",t.attr("level"));
	        }
            },
            editMenu:function(t){
	           edit(t.attr("code"),t.attr("parentcode"),"editMenu","修改",t.attr("level"));
            },
            deleteMenu:function(t){
	            if(t.attr("level")==1){
	                return;
	            }else{
	                 del(t.attr("code"),t.attr("nodeid"));
				}
            }
      },

      ctrSub:function(t,m){
            var madd = m.find("[rel='addMenu']");
            var mdel = m.find("[rel='deleteMenu']");
            var medit = m.find("[rel='editMenu']");
            if(t.attr("level")==1){
                mdel.addClass("disabled");
            }
            if(t.attr("level")!=1){
                madd.addClass("disabled");
            }
      }
});

function add(code,lablecode,lablename,level){
		var doptions = {height:250, width:450, minH:150, minW:410, total:20, max:false, mask:true,maxable:false};
		var addurl = "baseDictionarys!bfAdd.action?dictionaryCode="+code+"&level="+level+"&json.navTabId="+$("#navTabId").val();
		$.pdialog.open(addurl, lablecode, lablename, doptions);
}

function edit(code,parentcode,lablecode,lablename,level){
        var doptions = {height:250, width:450, minH:150, minW:410, total:20, max:false, mask:true,maxable:false};
        var addurl = "baseDictionarys!bfEdit.action?dictionaryCode="+code+"&parentCode="+parentcode+"&level="+level+"&json.navTabId="+$("#navTabId").val();
     	$.pdialog.open(addurl, lablecode, lablename, doptions);
}

function del(code,nodeid){
	    var deleteurl = "baseDictionarys!deleteNode.action?dictionaryCode="+code+"&id="+nodeid+"&json.navTabId="+$("#navTabId").val();
		alertMsg.confirm('确定要删除吗?此操作请慎重！', {
			okCall: function(){
				$.post(deleteurl, null, navTabAjaxDone, "json");
			}
		});
}
</script>

<div class="pageContent" style="padding:5px">
    <input type="hidden" id="navTabId" name="json.navTabId" value="${json.navTabId}">
	<div layoutH="12" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
	<ul class="tree treeFolder expand ">
		<li><a href="#" class="dictionarymenuclass" level="${rootNode.levelNo}" code="${rootNode.dictionaryCode}" 
		       nodeid="${rootNode.id }" parentcode="${rootNode.parentCode }">${rootNode.codeName}</a>
			<ul>
			<c:foreach items="${levelOneNode}" var="dictionary">
			    <li>
				<a href="baseDictionarys!list.action?dictionaryCode=${dictionary.dictionaryCode}&level=${dictionary.levelNo}&json.navTabId=dictionaryBox" class="dictionarymenuclass" 
				   code="${dictionary.dictionaryCode}" parentcode="${dictionary.parentCode }" level="${dictionary.levelNo}" 
				   nodeid="${dictionary.id }" target="ajax" rel="dictionaryBox" >${dictionary.codeName}</a>
			    </li>
			</c:foreach>
			</ul>
		</li>
	</ul>
	</div>
	<div id="dictionaryBox" class="unitBox" style="margin-left:246px;" layoutH="10">
		<!--#include virtual="list1.html" -->
	</div>
</div>

