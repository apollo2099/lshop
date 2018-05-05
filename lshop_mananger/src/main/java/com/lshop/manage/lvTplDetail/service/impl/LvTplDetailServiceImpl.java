/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplDetail.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.file.FileManager;
import com.lshop.common.file.FileManagerImpl;
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.pojo.logic.LvTplModel;
import com.lshop.manage.lvTplDetail.service.LvTplDetailService;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvTplDetailService")
public class LvTplDetailServiceImpl extends BaseServiceImpl implements LvTplDetailService {

	@Resource private HibernateBaseDAO dao;
	public List<LvTplDetail> findAll(Dto dto) throws ServiceException {//根据模板块id获取模板明细
		 LvTplModel lvTplModel = (LvTplModel)dto.get("model");
		return dao.find("from LvTplDetail where isdel=0 and tplModelCode='"+lvTplModel.getCode()+"'", null);
	}

	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvTplDetail lvTplDetail = (LvTplDetail)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvTplDetail t where isdel=0 ");
		        if(ObjectUtils.isNotEmpty(lvTplDetail.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
		        if(ObjectUtils.isNotEmpty(lvTplDetail.getTplModelCode())) {
		        	sql.append(" and  t.tplModelCode ='"+lvTplDetail.getTplModelCode()+"'");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvTplDetail.getName())) {
		        	sql.append(" and  t.name like :name ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvTplDetail.getContent())) {
		        	sql.append(" and  t.content like :content ");
		        }
		        if(ObjectUtils.isNotEmpty(lvTplDetail.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvTplDetail.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvTplDetail.getPagePath())) {
		        	sql.append(" and  t.pagePath like :pagePath ");
		        }
        Map parms = BeanUtils.describeForHQL(lvTplDetail);
        parms.remove("tplModelCode");
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        }
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	public LvTplDetail get(Dto dto) throws ServiceException {
		 LvTplDetail lvTplDetail = (LvTplDetail)dto.get("model");
		 lvTplDetail = (LvTplDetail)dao.load(LvTplDetail.class, lvTplDetail.getId());
		return  lvTplDetail;
	}
   
	public void del(Dto dto) throws ServiceException {//软件删除
		LvTplDetail lvTplDetail = (LvTplDetail)dto.get("model");
		dao.update("update LvTplDetail set isdel=1 where id="+lvTplDetail.getId(), null);
	}
	public void delList(Dto dto) throws ServiceException {
//		dao.delete(BasePoList)
	}
	/**
	 * 保存模板详情
	 * 生成对应的模板文件
	 */
	public LvTplDetail save(Dto dto) throws ServiceException {
		 LvTplDetail lvTplDetail = (LvTplDetail)dto.get("model");
		 dao.save( lvTplDetail);
		 LvTplModel lvTplModel=(LvTplModel)dao.findUnique("from LvTplModel where code='"+lvTplDetail.getTplModelCode()+"'", null);
		 String templatePath=dto.getAsString("rootPath")+"/template"+lvTplModel.getId();
		 String nPath=lvTplDetail.getPagePath();
		 String nDir="";
		 String nFile="";
		 if(nPath.indexOf("/")!=-1){
			nDir=nPath.substring(0, nPath.lastIndexOf("/"));
			File f=new File(templatePath+"/"+nDir);
			if(!f.exists()){
				f.mkdirs();
			}
		}
		FileManager fileManager=new FileManagerImpl();
		fileManager.save(templatePath+"/"+nPath, lvTplDetail.getContent());//保存文件
		return   lvTplDetail;
	}
	/**
	 * 修改模板详情
	 * 并修改对应的模板文件
	 */
	public LvTplDetail update(Dto dto) throws ServiceException {
		LvTplDetail lvTplDetail = (LvTplDetail)dto.get("model");
	   	dao.update(lvTplDetail);
	    LvTplModel lvTplModel=(LvTplModel)dao.findUnique("from LvTplModel where code='"+lvTplDetail.getTplModelCode()+"'", null);
	    String templatePath=dto.getAsString("rootPath")+"/template"+lvTplModel.getId();
		FileManager fileManager=new FileManagerImpl();
		fileManager.update(templatePath+"/"+lvTplDetail.getPagePath(), lvTplDetail.getContent());
		return  lvTplDetail;
	}

	@Override
	public boolean findCheckNameOrPath(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		LvTplDetail lvTplDetail = (LvTplDetail)dto.get("model");
		Map params=new HashMap();
		params.put("tplModelCode", lvTplDetail.getTplModelCode());
		params.put("name", lvTplDetail.getName());
		params.put("pagePath", lvTplDetail.getPagePath());
		Object obj=dao.findUnique("from LvTplDetail where isdel=0 and tplModelCode=:tplModelCode and (name=:name or pagePath=:pagePath)",params);
		
		return obj==null?false:true;
	}

}
