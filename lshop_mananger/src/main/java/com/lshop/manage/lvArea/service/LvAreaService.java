package com.lshop.manage.lvArea.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvArea;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvArea.service.LvAreaService.java]  
 * @ClassName:    [LvAreaService]   
 * @Description:  [区域信息管理--接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午02:53:31]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午02:53:31]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvAreaService {
	/**
	 * 
	 * @Method: getAll 
	 * @Description:  [查询所有的区域信息列表(只包含父级区域)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:55:17]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:55:17]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return List<LvArea>
	 */
	public List<LvArea> getAll(Dto dto)throws ServiceException ;
	/**
	 * 
	 * @Method: findCountryByCode 
	 * @Description:  [根据编码code查询国家信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-4-1 下午2:31:28]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-4-1 下午2:31:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param countryCode 国家code
	 * @return LvArea
	 */
	public LvArea findCountryByCode(String countryCode);
	/**
	 * 
	 * @Method: findProvinceByCode 
	 * @Description:  [根据国家id和省份code查询省份信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-4-1 下午2:32:00]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-4-1 下午2:32:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param parentid 国家id
	 * @param priviceCode 省份code
	 * @return LvArea
	 */
	public LvArea findProvinceByCode(Integer parentid,String priviceCode);
	/**
	 * 
	 * @Method: findProvinceByName 
	 * @Description:  [根据国家id和省份名称查询省份信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-4-1 下午2:32:04]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-4-1 下午2:32:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param parentid 国家id
	 * @param priviceName 省份名称
	 * @return LvArea
	 */
	public LvArea findProvinceByName(Integer parentid,String priviceName);
	public List<LvArea> getAllArea(Dto dto) throws ServiceException;
	public List<LvArea> getProvince(Dto dto ) throws ServiceException ;
	public LvArea get(Dto dto) throws ServiceException ;
}
