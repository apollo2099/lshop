var pU_CBtn=document.getElementById("btn");
	function popUp(src){
		function $$(str){
			return document.getElementById(str);
		}
		var pU=$$("J-popUp"),
		    pUB=$$("J-popUpBox"),
			pU_img=$$("popUpImg"),
			objHtml=document.documentElement,
			objBody=document.body;
		
		pU.style.height=objBody.offsetHeight+"px";
		pUB.style.marginLeft=parseInt(objHtml.clientWidth-650)/2+"px";
		pUB.style.marginTop=parseInt(objHtml.clientHeight-600)/2+(objHtml.scrollTop||objBody.scrollTop)+"px";
		pU.style.width=objHtml.clientWidth+"px";
		pU_img.src=src;
		
		pU_img.onclick=function(){
			pU.style.display="none";
		}
		
		function loadBox(){
			pUB.style.marginTop=parseInt(objHtml.clientHeight-600)/2+(objHtml.scrollTop||objBody.scrollTop)+"px";
		}
		window.onscroll=loadBox;
		pU.style.display="block";
}


$(document).ready(function(){
		//找到li下所有img的值,单击图片事件
			$("div ul li:has(img)").click(function(){
				var photo_url=$(this).find("img").attr("src");
				popUp(photo_url);
			});
		});