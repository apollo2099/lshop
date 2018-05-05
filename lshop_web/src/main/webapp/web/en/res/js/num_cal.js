NumCal = {
		round : function(arg1){
			tem = Math.round(parseFloat(arg1) * 100) / 100;
		    return tem;
		},
		precise : function(arg1) {
			var s = arg1.toString();  
		    var rs = s.indexOf('.');  
		    if (rs < 0) {  
		        rs = s.length;  
		        s += '.';  
		    }  
		    while (s.length <= rs + 1) {  
		        s += '0';  
		    }  
		    return s; 
		},
		add : function(arg1, arg2) {
			var tem = arg1*100+arg2*100;
		    tem = tem/100;
		    return tem;
		},
		sub : function(arg1, arg2) {
			var tem = arg1*100-arg2*100;
		    tem = tem/100;
		    return tem;
		},
		mul : function(arg1, arg2) {
			var tem = (arg1*100)*(arg2*100);
		    tem = tem/10000;
		    return round(tem);
		},
		div : function(arg1, arg2) {
			var tem = (arg1*100)/(arg2*100);
		    return round(tem);
		}
};