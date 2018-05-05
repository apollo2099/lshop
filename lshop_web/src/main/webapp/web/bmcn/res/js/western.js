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
					 	required: "<font color='red'>请输入汇款人</font>",
						maxlength: "<font color='red'>汇款人不能大于32位字符</font>"
					 },
					'lvWesternInfo.remittance': {
					 	required: "<font color='red'>请输入汇款金额，</font>",
					 	number:"<font color='red'>汇款金额格式不正确，只能为数字，</font>",
					 	maxlength:"<font color='red'>汇款金额不能大于11位字符，</font>"
					 },
					'lvWesternInfo.contryName': {
					 	required: "<font color='red'>请输入汇款国家</font>",
					 	maxlength:"<font color='red'>汇款国家不能大于32位字符</font>"
					 },
					 'lvWesternInfo.adress': {
					 	required: "<font color='red'>请输入汇款城市</font>",
					 	maxlength:"<font color='red'>汇款城市不能大于48位字符</font>"
					 },
					 'lvWesternInfo.transferTime': {
					 	required: "<font color='red'>请输入汇款时间</font>",
					 	date: "<font color='red'>汇款时间格式不正确，正确格式请參照：2012/01/01</font>",
					 	maxlength:"<font color='red'>汇款时间不能大于48位字符</font>"
					 },
					'lvWesternInfo.mtcn': {
					 	required: "<font color='red'>请输入MTCN</font>",
					 	digits:"<font color='red'>MTCN格式不正确，只能为整数</font>",
					 	maxlength:"<font color='red'>MTCN不能大于10位字符</font>"
					 }
				}
			});
		});