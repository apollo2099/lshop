/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvSpecialtyStore.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvSpecialtyStore;
import com.lshop.manage.lvSpecialtyStore.service.LvSpecialtyStoreService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvSpecialtyStoreService")
public class LvSpecialtyStoreServiceImpl extends BaseServiceImpl implements LvSpecialtyStoreService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvSpecialtyStore> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<LvSpecialtyStore> findAllByType(Dto dto)throws ServiceException{
		String storeTypeCode=(String) dto.get("storeTypeCode");
		if(ObjectUtils.isNotEmpty(storeTypeCode)){
			String hql=" from LvSpecialtyStore where storeTypeCode='"+storeTypeCode+"'";
			return (List<LvSpecialtyStore>)dao.find(hql, null);
		}
		return null;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvSpecialtyStore lvSpecialtyStore = (LvSpecialtyStore)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
	    Map<String ,Object> params=new HashMap<String ,Object>();
	    StringBuilder hql = new StringBuilder("select t.id as id,t.storeTypeCode as storeTypeCode,t.storeName as storeName,t.storeUrl as storeUrl," +
        		" t.storeLogo as storeLogo,t.orderValue as orderValue,t.createTime as createTime,t.modifyTime as modifyTime,st.parentCode as parentCode " +
        		" from LvSpecialtyStore t ,LvSpecialtyStoreType st where t.storeTypeCode=st.code ");
        if(lvSpecialtyStore!=null){
        	if(ObjectUtils.isNotEmpty(lvSpecialtyStore.getStoreTypeCode())) {
        		hql.append(" and  t.storeTypeCode=:storeTypeCode ");
        		params.put("storeTypeCode", lvSpecialtyStore.getStoreTypeCode());
	        }
        	if(ObjectUtils.isNotEmpty(lvSpecialtyStore.getStoreName())) {
        		hql.append(" and  t.storeName like :storeName ");
        		params.put("storeName", "%"+lvSpecialtyStore.getStoreName().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvSpecialtyStore.getParentCode())){
        		hql.append(" and  st.parentCode=:parentCode");
        		params.put("parentCode", lvSpecialtyStore.getParentCode());
        	}
        }
        hql.append("order by t.createTime desc ");
		Pagination pag =dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), params);
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvSpecialtyStore get(Dto dto) throws ServiceException {
		 LvSpecialtyStore lvSpecialtyStore = (LvSpecialtyStore)dto.get("model");
		 lvSpecialtyStore = (LvSpecialtyStore)dao.load(LvSpecialtyStore.class, lvSpecialtyStore.getId());
		return  lvSpecialtyStore;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvSpecialtyStore lvSpecialtyStore = get(dto);
		dao.delete(  lvSpecialtyStore);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvSpecialtyStore save(Dto dto) throws ServiceException {
		LvSpecialtyStore lvSpecialtyStore = (LvSpecialtyStore) dto.get("model");
		if (ObjectUtils.isNotEmpty(dto.getAsString("flag"))) {// 店铺标示
			lvSpecialtyStore.setStoreId(dto.getAsString("flag"));
		}
		dao.save(lvSpecialtyStore);
		return lvSpecialtyStore;
	}
	/**
	 * 更新
	 */
	public LvSpecialtyStore update(Dto dto) throws ServiceException {
		 LvSpecialtyStore lvSpecialtyStore = (LvSpecialtyStore)dto.get("model");
		dao.update(lvSpecialtyStore);
		return  lvSpecialtyStore;
	}
	
	/**
	 * 
	 * @Method: upload 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午06:25:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午06:25:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String upload(Dto dto){
		String imgFileName=(String) dto.get("imgFileName");
		File img=(File) dto.get("img");
		HttpServletRequest request=(HttpServletRequest) dto.get("request");
		String storeId="";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			storeId=dto.getAsString("flag");
		}

		String basepath=request.getRealPath("/web/"+storeId+"/res/images");//文件上传资源路径
		String imgName="store_"+System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
		FileUpload.upload(img,basepath, imgName);
		return "/res/images/"+imgName;
	}

}
