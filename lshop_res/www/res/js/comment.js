	var img_flag=true;
	function dograde(v,realPath){
		for(i=1;i<=5;i++){
	   	 	document.getElementById('image_'+i).src=realPath+"/res/images/wjx_h.gif";
	    }
		for(i=1;i<=v;i++){
			document.getElementById('image_'+i).src=realPath+"/res/images/wjx.gif";
		}
		var checkboxs=document.getElementsByName("grade");
		checkBoxsFalse();
		if(v<3){
			checkboxs[2].checked=true;
			document.getElementById('gradeId').value=1;
		}else if(v==3){
			checkboxs[1].checked=true;
			document.getElementById('gradeId').value=2;
		}else if(v>3){
			checkboxs[0].checked=true;
			document.getElementById('gradeId').value=3;
		}
		document.getElementById('scoreid').value=v;
		img_flag=false;
	
	}
	function checkBoxsFalse(){
		var checkboxs=document.getElementsByName("grade");
	       	for(i=0;i<checkboxs.length;i++){
	          checkboxs[i].checked=false;
	     	}
	}
	
	function outStar(realPath){
	    if(true==img_flag){
	      for(i=1;i<=5;i++){
	    	  document.getElementById('image_'+i).src=realPath+"/res/images/wjx_h.gif";
	          }
	      checkBoxsFalse();
	     
	    }
	}
	function moveStar(v,realPath){
		 if(true==img_flag){
		for(i=1;i<=v;i++){
	                      document.getElementById('image_'+i).src=realPath+"/res/images/wjx.gif";
			}
		 checkBoxsFalse();
		 var checkboxs=document.getElementsByName("grade");
			if(v<3){
				checkboxs[2].checked=true;
			}else if(v==3){
				checkboxs[1].checked=true;
			}else if(v>3){
				checkboxs[0].checked=true;
			}
		 }
		}
		
	function changNumKeyValue(){
	 	var strLength=$('#contentid').val().length;
	 	$('#contentNumId').text(strLength);
	 	if(strLength>500){
	        alert("評論過長，不能大于500個字符！");
	        return;
	    }
	 }
	 
	
	function doAddComment(){
		var checkboxs=document.getElementsByName("grade");
		//KindEditor.sync("#contentid");
		var f=false;
	    for(i=0;i<checkboxs.length;i++){
			if(checkboxs[i].checked==true){
	            f=true;
	            break;
	       }
	    }
	    if(f==false){
	        alert("請點擊星星對商品進行評分！");
	        return;
	    }
	    var content=$.trim($('#contentid').val());
	    if(content==''||content=='0/500'){
	        alert("請輸入評論正文！");
	        $('#contentid').focus();
	      	return;
	    }
	    if($('#contentid').val().length>500){
	        alert("評論過長，不能大于500個字符！");
	        return;
	    }
	    document.myform.submit();		      
	}
	
	
	
	
	