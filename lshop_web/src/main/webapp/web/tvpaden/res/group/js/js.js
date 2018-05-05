/* 


function setMainItem(suf_Id){
	 for(var i=0;i<4;i++){
		var num=i+1;
		var tempMenuId = "index_page"+num;
		var tempMainId ="focusPic"+num;
		document.getElementById(tempMenuId).className = num==suf_Id?"choose":"";
		document.getElementById(tempMainId).style.display = num==suf_Id?"block":"none";
	 }
 }
 
 */ 
 
 
 
 
function MainItem(suf_Id){
	 for(var i=0;i<5;i++){
		var num=i+1;
		var tempMenuId = "index"+num;
		var tempMainId ="f_Pic"+num;
		document.getElementById(tempMenuId).className = num==suf_Id?"choose":"";
		document.getElementById(tempMainId).style.display = num==suf_Id?"block":"none";
	 }
 }
 

 

function Main(suf_Id){
	 for(var i=0;i<4;i++){
		var num=i+1;
		var tempMenuId = "in_page"+num;
		var tempMainId ="fosPic"+num;
		document.getElementById(tempMenuId).className = num==suf_Id?"choose":"";
		document.getElementById(tempMainId).style.display = num==suf_Id?"block":"none";
	 }
 }
 
function setuserItem(suf_Id){
	 for(var i=0;i<4;i++){
		var num=i+1;
		var tempMenuId = "index_user"+num;
		var tempMainId ="userPic"+num;
		document.getElementById(tempMenuId).className = num==suf_Id?"choose":"";
		document.getElementById(tempMainId).style.display = num==suf_Id?"block":"none";
	 }
 }




/* 


function settvItem(suf_Id){
	 for(var i=0;i<3;i++){
		var num=i+1;
		var tempMenuId = "index_tv"+num;
		var tempMainId ="tvPic"+num;
		document.getElementById(tempMenuId).className = num==suf_Id?"choose":"nochoose";
		document.getElementById(tempMainId).style.display = num==suf_Id?"block":"none";
	 }
 }
 
function setorderItem(suf_Id){
 for(var i=0;i<3;i++){
	var num=i+1;
	var tempMenuId = "index_od"+num;
	var tempMainId ="order"+num;
	document.getElementById(tempMenuId).className = num==suf_Id?"choose":"nochoose";
	document.getElementById(tempMainId).style.display = num==suf_Id?"block":"none";
 }
}

function setrepeatItem(suf_Id){
	 for(var i=0;i<10;i++){
		var num=i+1;
		var tempMenuId = "index_rt"+num;
		var tempMainId ="repeat"+num;
		document.getElementById('index_rt'+suf_Id).style.display="none";
		document.getElementById('index_sq'+suf_Id).style.display="block";
		document.getElementById(tempMainId).style.display = num==suf_Id?"block":"none";
	 }
 }
 
 function setsqItem(suf_Id){
		document.getElementById('index_sq'+suf_Id).style.display="none";
		document.getElementById('index_rt'+suf_Id).style.display="block";
		document.getElementById('repeat'+suf_Id).style.display="none";
 } 
 
 */ 