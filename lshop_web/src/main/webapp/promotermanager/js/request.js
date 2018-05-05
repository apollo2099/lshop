function sendRequest()
      {
    	  var re = /^[1-9]*[1-9][0-9]*$/ ; 
          var num=document.getElementById("num").value;
          var result=re.test(num);
          if(!result)
          {
        	  alert("请输入正整数");
        	  document.getElementById("num").value="";
          }
          else
          {
    	  	document.getElementById("myform").action="rankpromt!sendRequest.action?num="+num;
    	  	document.forms["myform"].submit();
    	    alert("申请成功");
    		document.getElementById("num").value="";
    	   
          }
      }