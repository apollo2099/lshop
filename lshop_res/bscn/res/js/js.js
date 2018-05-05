function MainItem(suf_Id,total){
	 for(var i=0;i<total;i++){
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