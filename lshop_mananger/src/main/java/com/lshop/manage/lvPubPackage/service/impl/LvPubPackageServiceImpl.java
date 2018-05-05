/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubPackage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.lshop.manage.lvPubPackage.service.LvPubPackageService;
import com.lshop.common.pojo.logic.LvPubPackage;
import com.lshop.common.pojo.logic.LvPubPackageDetails;
import com.lshop.common.util.CodeUtils;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvPubPackageService")
public class LvPubPackageServiceImpl extends BaseServiceImpl implements LvPubPackageService {
	 private static final Log logger	= LogFactory.getLog(LvPubPackageServiceImpl.class);
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvPubPackage> findAll(Dto dto) throws ServiceException {
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
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageServiceImpl.findPage() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		 LvPubPackage lvPubPackage = (LvPubPackage)dto.get("lvPubPackage");
		 Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvPubPackage t where status=0 ");
	        	if(ObjectUtils.isNotEmpty(lvPubPackage.getPackageName())) {
		        	sql.append(" and  t.packageName like :packageName ");
		        	params.put("packageName", "%"+lvPubPackage.getPackageName().trim()+"%");
		        }
	        	if(ObjectUtils.isNotEmpty(lvPubPackage.getCode())) {
		        	sql.append(" and  t.code like :code ");
		        	params.put("code", "%"+lvPubPackage.getCode().trim()+"%");
		        }
	        	if(ObjectUtils.isNotEmpty(lvPubPackage.getPubPackageCode())){
	        		sql.append(" and t.code not in(:pubPackageCode)");
	        		params.put("pubPackageCode", lvPubPackage.getPubPackageCode());
	        	}
	        	
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageServiceImpl.findPage() method end*****");
		}
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvPubPackage get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.get() method begin*****");
		}
		LvPubPackage lvPubPackage = (LvPubPackage) dto.get("lvPubPackage");
		lvPubPackage = (LvPubPackage) dao.load(LvPubPackage.class,lvPubPackage.getId());

		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.get() method end*****");
		}
		return lvPubPackage;
	}
	
	/**
	 * 根据code获得单独的实体信息
	 */
	public LvPubPackage findByCode(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.get() method begin*****");
		}
		LvPubPackage lvPubPackage = (LvPubPackage) dto.get("lvPubPackage");
		lvPubPackage = (LvPubPackage) dao.findUniqueByProperty(LvPubPackage.class, "code", lvPubPackage.getCode());

		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.get() method end*****");
		}
		return lvPubPackage;
	}
	
	
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.del() method begin*****");
		}
		LvPubPackage lvPubPackage = (LvPubPackage) dto.get("lvPubPackage");
		String hql = "update LvPubPackage set status=:status where id=:id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", Short.parseShort("-1"));
		param.put("id", lvPubPackage.getId());
		dao.update(hql, param);
		
		lvPubPackage=this.get(dto);
		hql="delete from LvPubPackageDetails where pubPackageCode=:pubPackageCode";
		param.clear();
		param.put("pubPackageCode", lvPubPackage.getCode());
		dao.delete(hql, param);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.del() method end*****");
		}
	}


	/**
	 * 保存
	 */
	public LvPubPackage save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.del() method begin*****");
		}
		LvPubPackage lvPubPackage = (LvPubPackage) dto.get("lvPubPackage");
		//保存套餐信息
		dao.save(lvPubPackage);
		
		//保存套餐详情信息
		List<LvPubPackageDetails> detailsList= lvPubPackage.getDetailsList();
		if(ObjectUtils.isNotEmpty(detailsList)){
			for (LvPubPackageDetails pubPackageDetails : detailsList) {
				if(ObjectUtils.isNotEmpty(pubPackageDetails)){
					LvPubPackageDetails lvPubPackageDetails=new LvPubPackageDetails();
					lvPubPackageDetails.setPubPackageCode(lvPubPackage.getCode());
					lvPubPackageDetails.setPubProductCode(pubPackageDetails.getPubProductCode());
					lvPubPackageDetails.setProductNum(pubPackageDetails.getProductNum());
					lvPubPackageDetails.setCode(CodeUtils.getCode());
					lvPubPackageDetails.setCreateTime(new Date());
					dao.save(lvPubPackageDetails);
				}
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.del() method begin*****");
		}
		return lvPubPackage;
	}
	/**
	 * 更新
	 */
	public LvPubPackage update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.del() method begin*****");
		}
		LvPubPackage lvPubPackage = (LvPubPackage) dto.get("lvPubPackage");
		dao.update(lvPubPackage);

		//保存套餐详情信息
		List<LvPubPackageDetails> detailsList= lvPubPackage.getDetailsList();
		if(ObjectUtils.isNotEmpty(detailsList)){
			for (LvPubPackageDetails pubPackageDetails : detailsList) {
				if(ObjectUtils.isNotEmpty(pubPackageDetails)){
					if(ObjectUtils.isNotEmpty(pubPackageDetails.getId())){
						String hql="update LvPubPackageDetails set productNum=:productNum where id=:id";
						Map<String ,Object> param=new HashMap<String ,Object>();
						param.put("productNum", pubPackageDetails.getProductNum());
						param.put("id", pubPackageDetails.getId());
						dao.update(hql, param);
					}else{
						LvPubPackageDetails lvPubPackageDetails=new LvPubPackageDetails();
						lvPubPackageDetails.setPubPackageCode(lvPubPackage.getCode());
						lvPubPackageDetails.setPubProductCode(pubPackageDetails.getPubProductCode());
						lvPubPackageDetails.setProductNum(pubPackageDetails.getProductNum());
						lvPubPackageDetails.setCode(CodeUtils.getCode());
						lvPubPackageDetails.setCreateTime(new Date());
						dao.save(lvPubPackageDetails);
					}
				}
			}
			
            //删除多余的商品套餐详情信息
			List<Integer> orderPList=new ArrayList<Integer>();
			String tempIDS="";
			for (int m=0;m<detailsList.size();m++) {
				LvPubPackageDetails details=detailsList.get(m); 
				if(details!=null){
					orderPList.add(details.getId());
					if(m==detailsList.size()-1){
						tempIDS+=details.getId();
					}else{
						tempIDS+=details.getId()+",";
					}
					
					
				}
			}
			String productCodeList=orderPList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
			if (ObjectUtils.isNotEmpty(productCodeList)) {
				String hql="delete from LvPubPackageDetails where pubPackageCode='"+lvPubPackage.getCode()+"' and id not in("+tempIDS+")";
				dao.delete(hql, null);
			}
			
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubPackageServiceImpl.del() method begin*****");
		}
		return lvPubPackage;
	}
	
	/**
	 * 
	 * @Method: isExistPubPackage 
	 * @Description:  [根据套餐名称判断套餐是否存在]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-4-4 上午10:54:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-4-4 上午10:54:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean isExistPubPackage(Dto dto)throws ServiceException{
		LvPubPackage lvPubPackage=(LvPubPackage) dto.get("lvPubPackage");
		Map<String,Object> param=new HashMap<String, Object>();
    	if(ObjectUtils.isNotEmpty(lvPubPackage.getPackageName())) {
    		StringBuilder hql = new StringBuilder("select t from LvPubPackage t where status=0 ");
        	hql.append(" and  t.packageName =:packageName ");
        	param.put("packageName", lvPubPackage.getPackageName().trim());
        	List list=dao.find(hql.toString(), param);
        	if(list!=null&&list.size()>0){
        		return true;
        	}
        }
		return false;
	}

}
