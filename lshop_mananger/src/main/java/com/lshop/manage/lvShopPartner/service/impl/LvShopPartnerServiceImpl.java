/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopPartner.service.impl;

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
import com.lshop.common.pojo.logic.LvShopPartner;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvShopPartner.service.LvShopPartnerService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvShopPartnerService")
public class LvShopPartnerServiceImpl extends BaseServiceImpl implements LvShopPartnerService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvShopPartner> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
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
		LvShopPartner lvShopPartner = (LvShopPartner)dto.get("model");
		Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t.id as id,t.shopName as shopName,t.shopUrl as shopUrl,t.exhibitType as exhibitType,t.shopLogo as shopLogo," +
        		" t.orderValue as orderValue,t.createTime as createTime ,t.modifyTime as modifyTime,t.storeId as storeId,ls.domainName as domainName" +
        		" from LvShopPartner t,LvStore ls where t.storeId=ls.storeFlag ");
        if(lvShopPartner!=null){
        	if(ObjectUtils.isNotEmpty(lvShopPartner.getShopName())) {
	        	sql.append(" and  t.shopName like :shopName");
	        	params.put("shopName", "%"+lvShopPartner.getShopName().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvShopPartner.getStoreId())){
        		sql.append(" and  t.storeId=:storeId");
        		params.put("storeId", lvShopPartner.getStoreId());
        	}
        }
        sql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
        sql.append(" order by t.orderValue desc ");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag =dao.getMapListByHql(sql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvShopPartner get(Dto dto) throws ServiceException {
		 LvShopPartner lvShopPartner = (LvShopPartner)dto.get("model");
		 lvShopPartner = (LvShopPartner)dao.load(LvShopPartner.class, lvShopPartner.getId());
		return  lvShopPartner;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvShopPartner lvShopPartner = get(dto);
		dao.delete(  lvShopPartner);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvShopPartner save(Dto dto) throws ServiceException {
		LvShopPartner lvShopPartner = (LvShopPartner) dto.get("model");
		dao.save(lvShopPartner);
		return lvShopPartner;
	}
	/**
	 * 更新
	 */
	public LvShopPartner update(Dto dto) throws ServiceException {
		 LvShopPartner lvShopPartner = (LvShopPartner)dto.get("model");
		dao.update(lvShopPartner);
		return  lvShopPartner;
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
		String storeId=dto.getAsString("storeId");
		
		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeId);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
	    String resPath = dto.getAsString("resPath"); //获取资源文件路径 
			    
	    String basepath=resPath+"/upload/"+storeId+"/res/images";//文件上传资源路径
		String imgName="partner_"+System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
		FileUpload.upload(img,basepath, imgName);
		return resDomain+"/upload/"+storeId+"/res/images/"+imgName;
	}


}
