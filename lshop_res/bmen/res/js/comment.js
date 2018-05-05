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
		        ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		  		ymPrompt.alert({title:'Tips',message:'Your comment is too long, please make it within 500 characters!'}) ;
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
		        ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		  		ymPrompt.alert({title:'Tips',message:'Please click on the star to make comments!'}) ;
		        return;
		    }
		    var content=$.trim($('#contentid').val());
		    if(content==''||content=='0/500'){
		        ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		  		ymPrompt.alert({title:'Tips',message:'Please enter your comment!'}) ;
		        $('#contentid').focus();
		      	return;
		    }
		    if($('#contentid').val().length>500){
		    	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		  		ymPrompt.alert({title:'Tips',message:'Your comment is too long, please make it within 500 characters!'}) ;
		        return;
		    }
		    document.myform.submit();		      
		}