/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopSubject.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvExchangeRate;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvShopSubject.service.LvShopSubjectService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvShopSubjectService")
public class LvShopSubjectServiceImpl extends BaseServiceImpl implements LvShopSubjectService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvShopSubject> findAll(Dto dto) throws ServiceException {
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
		 LvShopSubject lvShopSubject = (LvShopSubject)dto.get("model");
		 Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvShopSubject t where 1=1 ");
        if(lvShopSubject!=null){
        	if(ObjectUtils.isNotEmpty(lvShopSubject.getSubjectName())) {
	        	sql.append(" and  t.subjectName like :subjectName");
	        	params.put("subjectName","%"+lvShopSubject.getSubjectName().trim()+"%" );
	        }
        	if(ObjectUtils.isNotEmpty(lvShopSubject.getStoreId())){
        		sql.append(" and t.storeId=:storeId");
        		params.put("storeId",lvShopSubject.getStoreId());
        	}
        }
        sql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
        sql.append(" order by t.orderValue desc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvShopSubject get(Dto dto) throws ServiceException {
		 LvShopSubject lvShopSubject = (LvShopSubject)dto.get("model");
		 lvShopSubject = (LvShopSubject)dao.load(LvShopSubject.class, lvShopSubject.getId());
		return  lvShopSubject;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvShopSubject lvShopSubject = get(dto);
		dao.delete(  lvShopSubject);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvShopSubject save(Dto dto) throws ServiceException {
		LvShopSubject lvShopSubject = (LvShopSubject) dto.get("model");
		dao.save(lvShopSubject);
		return lvShopSubject;
	}
	/**
	 * 更新
	 */
	public LvShopSubject update(Dto dto) throws ServiceException {
		 LvShopSubject lvShopSubject = (LvShopSubject)dto.get("model");
		dao.update(lvShopSubject);
		return  lvShopSubject;
	}

	/**
	 * 
	 * @Method: IsExistSubject 
	 * @Description:  [添加和修改前判断栏目名称是否存在]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-25 下午04:19:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-25 下午04:19:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean IsExistSubject(Dto dto)throws ServiceException{
		LvShopSubject lvShopSubject = (LvShopSubject)dto.get("model");
		String hql="from LvShopSubject where subjectName=:subjectName and storeId=:storeId";
		Map param=new HashMap();
		param.put("subjectName",lvShopSubject.getSubjectName());
		param.put("storeId",lvShopSubject.getStoreId());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){ 
			return true;
		}
		return false;
	}
	
	public Boolean isExistShopProductList(Dto dto)throws ServiceException{
		LvShopSubject lvShopSubject = (LvShopSubject)dto.get("model");
		lvShopSubject=this.get(dto);
		String hql="from LvShopProduct where subjectType=:subjectType";
		Map param=new HashMap();
		param.put("subjectType",lvShopSubject.getCode());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
}
