package com.lshop.common.util;

import java.util.UUID;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.common.util.CodeUtils.java]  
 * @ClassName:    [CodeUtils]   
 * @Description:  [用于生成字段唯一主键的算法]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-2 下午02:54:51]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-2 下午02:54:51]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
public class CodeUtils {
	
	public synchronized static String getCode(){
		
		String code=UUID.randomUUID().toString().replace("-", "");
		return code;
	}


}
