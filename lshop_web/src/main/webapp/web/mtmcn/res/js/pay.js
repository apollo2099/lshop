$(function (){
	$('#paySubmit').click(function(){
		if(payMethod()) $('#payForm').submit();
	});

});

function payMethod(){
	var paymethod=$("input[name='paymethod']:checked");
    if(paymethod.length==0){
       $("#msgbox").html("请选择支付方式");
       return false;
    }
	return true;
}