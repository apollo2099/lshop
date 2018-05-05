		//表单验证
		$().ready(function() {
			$("#myform").validate({
				rules: {
				    'lvWesternInfo.remitter':{required: true,maxlength:32},
					'lvWesternInfo.remittance':{required: true,number:true,maxlength:11},
					'lvWesternInfo.contryName':{required: true,maxlength:32},
					'lvWesternInfo.adress':{required: true,maxlength:48},
					'lvWesternInfo.transferTime':{required: true,date:true,maxlength:48},
					'lvWesternInfo.mtcn':{required: true,digits:true,maxlength:10}
				},
				messages: {
					'lvWesternInfo.remitter': {
					 	required: "<font color='red'>请输入匯款人</font>",
						maxlength: "<font color='red'>匯款人不能大于32位字符</font>"
					 },
					'lvWesternInfo.remittance': {
					 	required: "<font color='red'>请输入匯款金額，</font>",
					 	number:"<font color='red'>匯款金額格式不正確，只能為數字，</font>",
					 	maxlength:"<font color='red'>匯款金額不能大于11位字符，</font>"
					 },
					'lvWesternInfo.contryName': {
					 	required: "<font color='red'>请输入匯款國家</font>",
					 	maxlength:"<font color='red'>匯款國家不能大于32位字符</font>"
					 },
					 'lvWesternInfo.adress': {
					 	required: "<font color='red'>請輸入匯款城市</font>",
					 	maxlength:"<font color='red'>匯款城市不能大于48位字符</font>"
					 },
					 'lvWesternInfo.transferTime': {
					 	required: "<font color='red'>请输入匯款時間</font>",
					 	date: "<font color='red'>匯款時間格式不正確，正確格式請參照：2012/01/01</font>",
					 	maxlength:"<font color='red'>匯款時間不能大于48位字符</font>"
					 },
					'lvWesternInfo.mtcn': {
					 	required: "<font color='red'>請輸入MTCN</font>",
					 	digits:"<font color='red'>MTCN格式不正確，只能為整數</font>",
					 	maxlength:"<font color='red'>MTCN不能大于10位字符</font>"
					 }
				}
			});
		});