	var img_flag=true;
	function dograde(v){
		for(i=1;i<=5;i++){
	   	 	document.getElementById('image_'+i).src="../res/images/wjx_h.gif";
	    }
		for(i=1;i<=v;i++){
			document.getElementById('image_'+i).src="../res/images/wjx.gif";
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
	
	function outStar(){
	    if(true==img_flag){
	      for(i=1;i<=5;i++){
	    	  document.getElementById('image_'+i).src="../res/images/wjx_h.gif";
	          }
	      checkBoxsFalse();
	     
	    }
	}
	function moveStar(v){
		 if(true==img_flag){
		for(i=1;i<=v;i++){
	                      document.getElementById('image_'+i).src="../res/images/wjx.gif";
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
	        alert("评论过长，不能大于500个字符！");
	        return;
	    }
	 }
	 
	function doAddComment(){
		var checkboxs=document.getElementsByName("grade");
		var f=false;
	    for(i=0;i<checkboxs.length;i++){
			if(checkboxs[i].checked==true){
	            f=true;
	            break;
	       }
	    }
	    if(f==false){
	        alert("请点击星星对商品进行评分！");
	        return;
	    }
	    var content=$.trim($('#contentid').val());
	    if(content==''||content=='0/500'){
	        alert("请输入评论正文！");
	        $('#contentid').focus();
	      	return;
	    }
	    if($('#contentid').val().length>500){
	        alert("评论过长，不能大于500个字符！");
	        return;
	    }
	    document.myform.submit();		      
	}