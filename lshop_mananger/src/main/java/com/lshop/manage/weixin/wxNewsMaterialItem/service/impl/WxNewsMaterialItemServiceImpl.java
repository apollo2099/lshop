/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxNewsMaterialItem.service.impl;

import java.io.File;
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
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.util.Constants;
import com.lshop.manage.weixin.pojo.WxNewsMaterialItem;
import com.lshop.manage.weixin.wxNewsMaterialItem.service.WxNewsMaterialItemService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxNewsMaterialItemService")
public class WxNewsMaterialItemServiceImpl extends BaseServiceImpl implements WxNewsMaterialItemService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<WxNewsMaterialItem> findAll(Dto dto) throws ServiceException {
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
		 WxNewsMaterialItem wxNewsMaterialItem = (WxNewsMaterialItem)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxNewsMaterialItem t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxNewsMaterialItem.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxNewsMaterialItem.getNewsType())) {
		        	sql.append(" and  t.newsType = :newsType ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxNewsMaterialItem.getNewsId())) {
		        	sql.append(" and  t.newsId = :newsId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxNewsMaterialItem.getTitle())) {
		        	sql.append(" and  t.title like :title ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxNewsMaterialItem.getDescription())) {
		        	sql.append(" and  t.description like :description ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxNewsMaterialItem.getUrl())) {
		        	sql.append(" and  t.url like :url ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxNewsMaterialItem.getPicurl())) {
		        	sql.append(" and  t.picurl like :picurl ");
		        }
		        if(ObjectUtils.isNotEmpty(wxNewsMaterialItem.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
        Map parms = BeanUtils.describeForHQL(wxNewsMaterialItem);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public WxNewsMaterialItem get(Dto dto) throws ServiceException {
		 WxNewsMaterialItem wxNewsMaterialItem = (WxNewsMaterialItem)dto.get("model");
		 wxNewsMaterialItem = (WxNewsMaterialItem)dao.load(WxNewsMaterialItem.class, wxNewsMaterialItem.getId());
		return  wxNewsMaterialItem;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxNewsMaterialItem wxNewsMaterialItem = get(dto);
		dao.delete(  wxNewsMaterialItem);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxNewsMaterialItem save(Dto dto) throws ServiceException {
		 WxNewsMaterialItem wxNewsMaterialItem = (WxNewsMaterialItem)dto.get("model");
		dao.save( wxNewsMaterialItem);
		return   wxNewsMaterialItem;
	}
	/**
	 * 更新
	 */
	public WxNewsMaterialItem update(Dto dto) throws ServiceException {
		 WxNewsMaterialItem wxNewsMaterialItem = (WxNewsMaterialItem)dto.get("model");
		dao.update(wxNewsMaterialItem);
		return  wxNewsMaterialItem;
	}
	@Override
	public String upload(Dto dto) throws ServiceException {
		String imgFileName=(String) dto.get("imgFileName");
		File img=(File) dto.get("img");
		String storeFlag = (String)dto.get("storeFlag");
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
	    String resPath = dto.getAsString("resPath"); //获取资源文件路径 
	    String basepath=resPath+"/upload/"+storeFlag+"/res/images/weixin/";//文件上传资源路径
	    //看这个绝对路径是否存在
	    File file =new File(basepath);    
		 //如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{        
		     file .mkdir();    
		 } 
		String imgName="weixin_"+System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
		FileUpload.upload(img,basepath, imgName);
		return resDomain+"/upload/"+storeFlag+"/res/images/weixin/"+imgName;
	}

}
