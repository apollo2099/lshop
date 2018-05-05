/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvActivityGift;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvActivityGiftService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvActivityGift> findAll(Dto dto) throws ServiceException;

    /**
     * 
     * @Method: saveBatch 
     * @Description:  [批量保存活动赠品信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2015-1-9 下午2:12:11]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2015-1-9 下午2:12:11]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param giftList 赠品集合
     * @param acitivityCode 活动编码
     * @return Boolean
     */
	public Boolean saveBatch(List<LvActivityGift> giftList,String acitivityCode);
	
	/**
	 * 
	 * @Method: updateGiftNum 
	 * @Description:  [更新活动赠品数目]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 上午11:12:29]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 上午11:12:29]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @param giftEveryNum 每次赠送数量
	 * @param giftTotalNum 赠品总数
	 * @param giftSerplusNum 剩余赠品数量
	 * @param orderValue 排序值
	 * @param id 活动赠品编号
	 * @throws ServiceException 
	 * @return boolean
	 */
	public boolean updateGiftNum(Integer giftEveryNum,Integer giftTotalNum,Integer giftSerplusNum,Integer orderValue,Integer id)throws ServiceException;
	
	/**
	 * 
	 * @Method: isExsitsAcivityByGiftCode 
	 * @Description:  [根据赠品编号判断是否存在有效的活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-29 下午3:18:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-29 下午3:18:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param giftCode 赠品编号
	 * @return boolean
	 */
	public boolean isExsitsAcivityByGiftCode(String giftCode);
}
