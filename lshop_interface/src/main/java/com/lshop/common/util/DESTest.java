package com.lshop.common.util;

import com.gv.core.util.cryption.DES;

public class DESTest {

	/** 
	 * @Method: main 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-6 上午09:23:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-6 上午09:23:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param args 
	 * @return void
	 */
	public static void main(String[] args) {
		String result= DES.encrypt(DES.encrypt("gvtvadmin"));
        System.out.println(result);
        /*String back= DES.decrypt(DES.decrypt(result));
        System.out.println(back);
        
        String root= DES.encrypt(DES.encrypt("root"));
        System.out.println(root);
        String rootTmp= DES.decrypt(DES.decrypt(root));
        System.out.println(rootTmp);
        
        String admin= DES.encrypt(DES.encrypt("admin"));
        System.out.println(admin);
        String adminTmp= DES.decrypt(DES.decrypt("9309167137a4c1c96194924d9eb110dc6732560d31ee149d"));
        System.out.println(adminTmp);
        String adminTmp1= DES.decrypt(DES.decrypt("326017547b2b254f76090c6ca94f77adcbdcc77d383ee03b8260102043692b356732560d31ee149d"));
        System.out.println(adminTmp1);
        String adminTmp2= DES.decrypt(DES.decrypt("326017547b2b254f76090c6ca94f77adcbdcc77d383ee03b8260102043692b356732560d31ee149d"));
        System.out.println(adminTmp2);*/
        
        
	}

}
