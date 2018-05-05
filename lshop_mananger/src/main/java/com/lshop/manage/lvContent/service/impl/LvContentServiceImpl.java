/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvContent.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvContent;
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.pojo.logic.LvTplModel;
import com.lshop.common.util.Constants;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvContent.service.LvContentService;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvContentService")
public class LvContentServiceImpl extends BaseServiceImpl implements LvContentService {

	@Resource private HibernateBaseDAO dao;
	public List<LvContent> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvContent lvContent = (LvContent)dto.get("model");
		 String storeFlag=dto.getAsString("flag");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
		Map params=new HashMap();
        StringBuilder sql = new StringBuilder("select t from LvContent t where   isdel=0 ");
        if(lvContent!=null){
        	if(ObjectUtils.isNotEmpty(lvContent.getPageName())) {
	        	sql.append(" and  t.pageName like :pageName");
	        }
        	if(ObjectUtils.isNotEmpty(lvContent.getStoreFlag())){
        		sql.append(" and t.storeFlag like :storeFlag");
        	}
        	params=BeanUtils.describeForHQL(lvContent);
        }
	    sql.append(StoreHelpUtil.getShopRelevanceString(dto.getAsString("flag"),"storeFlag","t"));
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	public LvContent get(Dto dto) throws ServiceException {
		 LvContent lvContent = (LvContent)dto.get("model");
		 lvContent = (LvContent)dao.load(LvContent.class, lvContent.getId());
		return  lvContent;
	}

	public void del(Dto dto) throws ServiceException {
		 LvContent lvContent = (LvContent)dto.get("model");
		 dao.update("update LvContent set isdel=1 where id="+lvContent.getId(), null);
	}

	public void delList(Dto dto) throws ServiceException {
	}

	public LvContent save(Dto dto) throws ServiceException {
		 LvContent lvContent = (LvContent)dto.get("model");
//		 lvContent.setStoreFlag(dto.getAsString("flag"));
		dao.save( lvContent);
		return   lvContent;
	}

	public LvContent update(Dto dto) throws ServiceException {
		 LvContent lvContent = (LvContent)dto.get("model");
		dao.update(lvContent);
		return  lvContent;
	}
	
	@Override
	public List findDefulatTplDetail(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String storeFlag=dto.getAsString("storeFlag");
		LvTplModel lvTplModel=(LvTplModel)dao.findUnique("from LvTplModel where isDefault=1 and storeFlag ='"+storeFlag+"'", null);
		if(lvTplModel!=null){
			return dao.find("from LvTplDetail where isdel=0 and tplModelCode='"+lvTplModel.getCode()+"'", null);
		}
		return null;
	}

	@Override
	public void doBuild(Dto dto) throws ServiceException, IOException, TemplateException {
		// TODO Auto-generated method stub
		
	  String ids=dto.getAsString("ids"); 	

	  if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
			      LvContent content =(LvContent)dao.load(LvContent.class, Integer.parseInt(temp_ids[i].trim()));
					/**
					 * freemarker 加载文件
					 */
					Configuration cfg = new Configuration();
					String storeFlag = content.getStoreFlag();
					String rootPath = dto.getAsString("rootPath");
					LvTplModel lvTplModel = (LvTplModel) dao.findUnique("from LvTplModel where isDefault=1 and storeFlag='"+ storeFlag + "'", null);
					// 加载freemarker模板文件
					cfg.setDirectoryForTemplateLoading(new File(rootPath+ "/" + storeFlag + "/tpl/template"+ lvTplModel.getId()));
					// 设置对象包装器
					cfg.setObjectWrapper(new DefaultObjectWrapper());
					// 设计异常处理器
					cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
					// 定义并设置数据
			      
			      
				  Map<String, Object> data = new HashMap<String, Object>();  
				  data.put("title", content.getPageTitle());
				  data.put("keywords", content.getPageKeywords());
				  data.put("description", content.getPageDescription());
				  if(content.getIsHasContent()==1){
					  data.put("content", content.getPageContent());
				  }
				  data.put("storeFlag", storeFlag);
//				  String resDomain = (String) PropertiesHelper.getConfiguration().getProperty("lshop.base.resource.domain");
				  String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
				  String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
				  String resDomain="http://res"+domainPostfix;
				  data.put("resDomain", resDomain);
			      Template template = cfg.getTemplate(content.getTemplatePath(),"UTF-8");  
				  File file= new File(rootPath+"/"+storeFlag+"/"+content.getPagePath());
				  File f=new File(file.getParent());//获取文件目录的绝对路径的父路径
				  if(!f.exists()){//不在在，就创建文件
					  f.mkdirs();
				  }
				  //定义输入文件，默认生成在工程根目录  
				  Writer out = new OutputStreamWriter(new FileOutputStream(rootPath+"/"+storeFlag+"/"+content.getPagePath()),"UTF-8");  
				  template.process(data, out);
				  out.flush();
				  out.close();
				}
			}
		}
  }
}