/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplModel.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import com.lshop.common.pojo.logic.LvContent;
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.pojo.logic.LvTplModel;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvContent.service.LvContentService;
import com.lshop.manage.lvTplModel.service.LvTplModelService;

import freemarker.template.TemplateException;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvTplModelService")
public class LvTplModelServiceImpl extends BaseServiceImpl implements LvTplModelService {

	@Resource private HibernateBaseDAO dao;
	@Resource private LvContentService contentService;
	public List<LvTplModel> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvTplModel lvTplModel = (LvTplModel)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
		Map params=new HashMap();
        StringBuilder sql = new StringBuilder("select t from LvTplModel t where 1=1 ");
        if(lvTplModel!=null){
        	if(ObjectUtils.isNotEmpty(lvTplModel.getId())) {
	        	sql.append(" and  t.id = :id ");
	        }	
        	if(ObjectUtils.isNotEmpty(lvTplModel.getModelName())) {
	        	sql.append(" and  t.modelName like :modelName" );
	        }
        	if(ObjectUtils.isNotEmpty(lvTplModel.getStoreFlag())){
        		sql.append(" and t.storeFlag like :storeFlag");
        	}
        	params = BeanUtils.describeForHQL(lvTplModel);
        }
		        
	    //组装SQL    	
	    sql.append(StoreHelpUtil.getShopRelevanceString(dto.getAsString("flag"),"storeFlag","t"));
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        }
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	public LvTplModel get(Dto dto) throws ServiceException {
	 LvTplModel lvTplModel = (LvTplModel)dto.get("model");
	 if(lvTplModel.getId()!=null){
		 lvTplModel = (LvTplModel)dao.load(LvTplModel.class, lvTplModel.getId());
	 }else{
		 lvTplModel=(LvTplModel)dao.findUnique("from LvTplModel where code='"+lvTplModel.getCode()+"'", null);
		 }
		return  lvTplModel;
	}
	
   /**
    * 删除模板块
    */
	public Integer del(Dto dto) throws ServiceException {
		 LvTplModel lvTplModel = get(dto);
		 List DetailList=dao.find("from LvTplDetail where isdel=0 and tplModelCode='"+lvTplModel.getCode()+"'", null);
		if(DetailList!=null&&DetailList.size()>0){
			return 2;
		}
		dao.delete(lvTplModel);
		return 0;
	}

	public void delList(Dto dto) throws ServiceException {

	}

	public LvTplModel save(Dto dto) throws ServiceException {
		 String storeFlag=dto.getAsString("flag");
		 LvTplModel lvTplModel = (LvTplModel)dto.get("model");
//		 lvTplModel.setStoreFlag(storeFlag);
		 dao.save( lvTplModel);
		 return   lvTplModel;
	}

	public LvTplModel update(Dto dto) throws ServiceException {
		LvTplModel lvTplModel = (LvTplModel)dto.get("model");
		dao.update(lvTplModel);
		return  lvTplModel;
	}
	/**
	 * 获取商铺所对应的模板模块
	 */
	@Override
	public LvTplModel getDefaultTplModel(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		 LvTplModel lvTplModel = (LvTplModel)dto.get("model");
		List<LvTplModel> list=dao.find("from LvTplModel where isDefault=1 and storeFlag='"+ lvTplModel.getStoreFlag()+"'", null);
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
		return null;
	}
   /**
    * 设置默认模板
    */
	@Override
	public void updateDefaultSet(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		 LvTplModel lvTplModel = (LvTplModel)dto.get("model");
		 lvTplModel.setStoreFlag(dto.getAsString("flag"));
		 if(!ObjectUtils.isNullOrEmptyString(lvTplModel.getStoreFlag())&&ObjectUtils.isNotEmpty(lvTplModel.getId())){
			 dao.update("update LvTplModel set isDefault=0 where storeFlag='"+lvTplModel.getStoreFlag()+"'", null);
			//把商铺所对应的模板设为默认
			 dao.update("update LvTplModel set isDefault=1 where id="+lvTplModel.getId(),null);
			 
		 }
		
	}
/**
 * 检查模板类别是否存在
 */
@Override
public boolean findCheckName(Dto dto) throws ServiceException {
	// TODO Auto-generated method stub
	String storeFlag=dto.getAsString("flag");
	LvTplModel lvTplModel = (LvTplModel)dto.get("model");
	Map params=new HashMap();
	params.put("storeFlag", storeFlag);
	params.put("modelName", lvTplModel.getModelName());
	String hql="from LvTplModel where storeFlag=:storeFlag and modelName=:modelName";
    Object obj=dao.findUnique(hql, params);
	return obj==null?false:true;
}

@Override
public void saveUpdateImportExcel(Dto dto) throws ServiceException, IOException, TemplateException {
	Integer tplFlag=dto.getAsInteger("tplFlag");//是否覆盖模板标识
	Integer contentFlag=dto.getAsInteger("contentFlag"); //是否覆盖内容标识
	Integer autoTplFlag=dto.getAsInteger("autoTplFlag");//是否自动生成模板页标识
	Integer autoContentFlag=dto.getAsInteger("autoContentFlag");//是否自动生成内容页标识
    List<String[]> list=(List<String[]>)dto.get("list");	
for(String []temp:list){
	String flag=temp[0];//店铺标记
	String tplName=temp[1];//模板名称
	String tplContext=temp[2];//模板内容
	String tplPath=temp[3];//模板路径
	String pageName=temp[4];//页面名称
	String pageTitle=temp[5];//页面标题
	String pageKeywords=temp[6];//页面关键字
	String pageDesc=temp[7];//页面描述
	String pagePath=temp[8];//页面路径
	Map param=new HashMap();
	param.put("storeFlag", flag);
	LvTplModel tplModel=(LvTplModel)dao.findUnique("from LvTplModel where storeFlag=:storeFlag and isDefault=1", param);
	if(tplModel==null){
		tplModel=new LvTplModel();
		tplModel.setModelName(flag);
		tplModel.setStoreFlag(flag);
		tplModel.setIsDefault((short)1);
		tplModel.setCreateTime(new Date());
		tplModel.setCode(CodeUtils.getCode());
		dao.save(tplModel);
	}
		param.put("tplModelCode", tplModel.getCode());
		param.put("pagePath", tplPath);
		LvTplDetail lvTplDetail=(LvTplDetail)dao.findUnique("from LvTplDetail where tplModelCode=:tplModelCode and storeFlag=:storeFlag and pagePath=:pagePath and isdel=0", param);
	    //保存模板明细内容
		if(lvTplDetail==null){
	    	lvTplDetail=new LvTplDetail();
	    	lvTplDetail.setTplModelCode(tplModel.getCode());
	    	lvTplDetail.setStoreFlag(flag);
	        lvTplDetail.setName(tplName);
	    	lvTplDetail.setContent(tplContext);
	    	lvTplDetail.setPagePath(tplPath);
	    	lvTplDetail.setCreateTime(new Date());
	    	lvTplDetail.setIsdel(0);
	    	dao.save(lvTplDetail);//保存模板内容
	    }else{
	    	 if(tplFlag==1){
	    	 lvTplDetail.setContent(tplContext);
	    	 lvTplDetail.setModifyTime(new Date());
	    	 dao.update(lvTplDetail);
	    	 }
	    }
	       //保存页面内容 
	    	param.clear();
	    	param.put("storeFlag", flag);
	    	param.put("pagePath", pagePath);
	    	LvContent lvContent =(LvContent)dao.findUnique("from LvContent where isdel=0 and storeFlag=:storeFlag and pagePath=:pagePath", param);
	    	if(lvContent==null){
	    		lvContent=new LvContent();
	    		lvContent.setIsdel((short)0);
	    		lvContent.setCreateTime(new Date());
	    		lvContent.setStoreFlag(flag);
	    		lvContent.setPageName(pageName);
	    		lvContent.setPageTitle(pageTitle);
	    		lvContent.setPageKeywords(pageKeywords);
	    		lvContent.setPageDescription(pageDesc);
	    		lvContent.setIsHasContent((short)0);
	    		lvContent.setPagePath(pagePath);
	    		lvContent.setTemplatePath(tplPath);
	    		dao.save(lvContent);
	    	}else{
	    		if(contentFlag==1){
	    		lvContent.setPageTitle(pageTitle);
	    		lvContent.setPageKeywords(pageKeywords);
	    		lvContent.setPageDescription(pageDesc);
	    		lvContent.setModifyTime(new Date());
	    		lvContent.setTemplatePath(tplPath);
	    		lvContent.setPagePath(pagePath);
	    		dao.update(lvContent);
	    		}
	    	}
	    	
	    	
	    	if(autoTplFlag==1||autoContentFlag==1){//生成模板文件
	    	String templatePath=dto.getAsString("rootPath")+"/"+tplModel.getStoreFlag()+"/tpl/template"+tplModel.getId();
	    	File file=new File(templatePath);
		    if(!file.exists()){//生成模板文件目录
				file.mkdirs();
			 }
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
	    	}
	    	if(autoContentFlag==1){
	    		dto.put("ids", lvContent.getId());
	    		contentService.doBuild(dto);//生成静态页面
	    			
	    	}
	    	
}
}
}
