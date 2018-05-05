			
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
						 	required: "<font color='red'>Please enter the Payer!</font>",
							maxlength: "<font color='red'>Payer shall not be longer than 32 characters!</font>"
						 },
						'lvWesternInfo.remittance': {
						 	required: "<font color='red'>Please enter the Amount!</font>",
						 	number:"<font color='red'>Invalid Amount format, please enter figures only!</font>",
						 	maxlength:"<font color='red'>Amount shall not be longer than 11 characters!</font>"
						 },
						'lvWesternInfo.contryName': {
						 	required: "<font color='red'>Please enter the Country!</font>",
						 	maxlength:"<font color='red'>Country shall not be longer than 32 characters!</font>"
						 },
						 'lvWesternInfo.adress': {
						 	required: "<font color='red'>Please enter the City!</font>",
						 	maxlength:"<font color='red'>City shall not be longer than 48 characters</font>"
						 },
						 'lvWesternInfo.transferTime': {
						 	required: "<font color='red'>Please enter the Date!</font>",
						 	date: "<font color='red'>Incorrect Date format, correct format: 2012/01/01</font>",
						 	maxlength:"<font color='red'>Date shall not be longer than 48 characters!/font>"
						 },
						'lvWesternInfo.mtcn': {
						 	required: "<font color='red'>Please enter the MTCN!</font>",
						 	digits:"<font color='red'>MTCN format incorect, please enter round figures only!</font>",
						 	maxlength:"<font color='red'>MTCN shall not be longer than 10 characters!</font>"
						 }
					}
				});
			});