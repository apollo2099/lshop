/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvNavigation.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvNavigation;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvNavigation.service.LvNavigationService;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvNavigationService")
public class LvNavigationServiceImpl extends BaseServiceImpl implements LvNavigationService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvNavigation> findAll(Dto dto) throws ServiceException {
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
		String storeFlag=dto.getAsString("flag");
		LvNavigation lvNavigation = (LvNavigation)dto.get("model");
		Map params=new HashMap();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t.id as id,t.storeId as storeId,t.navName as navName,t.navUrl as navUrl,t.orderValue as orderValue," +
        		" t.openTarget as openTarget,t.navImage as navImage,t.createTime as createTime,t.modifyTime as modifyTime,s.domainName as domainName" +
        		" from LvNavigation t,LvStore s where t.storeId=s.storeFlag ");
        if(lvNavigation !=null){
        	if(ObjectUtils.isNotEmpty(lvNavigation.getNavName())) {
	        	sql.append(" and  t.navName like :navName ");
	        	params.put("navName", "%"+lvNavigation.getNavName().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvNavigation.getStoreId())){
        		sql.append(" and t.storeId like :storeId");
        		params.put("storeId", "%"+lvNavigation.getStoreId().trim()+"%");
        	}
        }
	    sql.append(StoreHelpUtil.getStoreRelevanceString(storeFlag,"storeId","t"));
        sql.append(" order by t.storeId desc,t.orderValue desc");
		return dao.getMapListByHql(sql.toString(),  page.getPageNum(), page.getNumPerPage(), params);
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvNavigation get(Dto dto) throws ServiceException {
		 LvNavigation lvNavigation = (LvNavigation)dto.get("model");
		 lvNavigation = (LvNavigation)dao.load(LvNavigation.class, lvNavigation.getId());
		return  lvNavigation;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		LvNavigation lvNavigation = get(dto);
		if (ObjectUtils.isNotEmpty(lvNavigation)) {
			//级联删除子菜单
			String hql = "delete LvNavigation where navParentId=:navParentId";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("navParentId", lvNavigation.getId());
			dao.update(hql, param);
			dao.delete(lvNavigation);
		}
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvNavigation save(Dto dto) throws ServiceException {
		 LvNavigation lvNavigation = (LvNavigation)dto.get("model");
		 dao.save( lvNavigation);
		 return   lvNavigation;
	}
	/**
	 * 更新
	 */
	public LvNavigation update(Dto dto) throws ServiceException {
		 LvNavigation lvNavigation = (LvNavigation)dto.get("model");
		dao.update(lvNavigation);
		//级联更新子菜单的店铺标识
		String hql = "update LvNavigation set storeId=:storeId where navParentId=:navParentId";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("navParentId", lvNavigation.getId());
		param.put("storeId", lvNavigation.getStoreId());
		dao.update(hql, param);
		return  lvNavigation;
	}
	
	/**
	 * 
	 * @Method: upload 
	 * @Description:  [上传导航图片]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-10 下午5:29:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-10 下午5:29:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return String
	 */
	public String upload(Dto dto){
		
		String imgFileName=(String) dto.get("imgFileName");
		File img=(File) dto.get("img");
		HttpServletRequest request=(HttpServletRequest) dto.get("request");
		String storeId=(String) dto.get("storeId");
		
		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeId);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
	    String resPath = dto.getAsString("resPath"); //获取资源文件路径 
			    
//		String basepath=request.getRealPath("/web/"+storeId+"/res/images");//文件上传资源路径
	    String basepath=resPath+"/upload/"+storeId+"/res/images";//文件上传资源路径
		String imgName="nav_"+System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
		FileUpload.upload(img,basepath, imgName);
		
		return resDomain+"/upload/"+storeId+"/res/images/"+imgName;
//		return "/res/images/"+imgName;
	}
	@Override
	public List<LvNavigation> findPrimNaviByStoreId(String storeId) throws ServiceException {
		String hql = "from LvNavigation where storeId=:storeId and nav_parent_id=0";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeId", storeId);
		return dao.find(hql, param);
	}
	@Override
	public LvNavigation getById(Integer id) throws ServiceException {
		return (LvNavigation) dao.findUniqueByProperty(LvNavigation.class, "id", id);
	}

}
