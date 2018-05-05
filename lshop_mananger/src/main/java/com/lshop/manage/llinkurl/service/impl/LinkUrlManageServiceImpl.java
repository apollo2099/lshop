package com.lshop.manage.llinkurl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvLinkUrl;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.llinkurl.service.LinkUrlManageService;

@Service("LinkUrlManageService")
public class LinkUrlManageServiceImpl implements LinkUrlManageService {

	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private InitLinkDataService initLinkDataService;
	
	@Override
	public Pagination list(Dto dto) {
		StringBuilder hql=new StringBuilder("FROM LvLinkUrl t where 1=1" );
		LvLinkUrl linkUrl=(LvLinkUrl) dto.getDefaultPo();
		SimplePage page = (SimplePage)dto.get("page");
		Map<String ,Object> params=new HashMap<String ,Object>();
		if(linkUrl!=null){
			if(ObjectUtils.isNotEmpty(linkUrl.getStoreFlag())){
				hql.append(" and t.storeFlag =:storeFlag ");
				params.put("storeFlag", linkUrl.getStoreFlag());
			}
		}
		hql.append(StoreHelpUtil.getShopRelevanceString(dto.getAsString("flag"),"storeFlag","t"));
		hql.append(" order by t.storeFlag desc ");
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getNextPage(), page.getNumPerPage());
		
	}
	@Override
	public Integer addres(Dto dto) {
       Integer status=300;
       LvLinkUrl po=(LvLinkUrl) dto.getDefaultPo();
		try {
			if (po!=null) {
				//数据去掉空格，防止静态化连接失败
				po.setName(po.getName().trim());
				po.setDynamicurl(po.getDynamicurl().trim());
				po.setStaticurl(po.getStaticurl().trim());
				po.setHql(po.getHql().trim());
				po.setNote(po.getNote().trim());
				dao.save(po);
				status=200;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			status=300;
		}
		return status;
	}
	@Override
	public Integer updateres(Dto dto) {
		Integer status=300;
		LvLinkUrl po=(LvLinkUrl) dto.getDefaultPo();
		try {
			if (po!=null) {
				//数据去掉空格
				po.setName(po.getName().trim());
				po.setDynamicurl(po.getDynamicurl().trim());
				po.setStaticurl(po.getStaticurl().trim());
				po.setHql(po.getHql().trim());
				po.setNote(po.getNote().trim());
				dao.update(po);
				status=200;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			status=300;
		}
		return status;
	}
	@Override
	public Integer delres(Dto dto) {
		 	Integer status=300;
		 	String ids=dto.getAsString("ids");
			try {
				if (ids!=null) {
					dao.delete("DELETE LvLinkUrl WHERE id in ("+ids+")",null);
					status=200;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				status=300;
			}
			return status;
	}
	
	@Override
	public BasePo viewres(Dto dto) {
		Integer id=dto.getAsInteger("id");	
		return (BasePo)dao.load(LvLinkUrl.class, id);
	}
	
	/**
	 * 
	 * @Method: findAllLink 
	 * @Description:  [查询符合条件的静态化链接数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-15 下午6:24:08]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-15 下午6:24:08]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return 
	 * @return List<LvLinkUrl>
	 */
	public List<LvLinkUrl> findAllLink(Dto dto){
		String ids=(String) dto.get("ids");
		StringBuilder hql=new StringBuilder("FROM LvLinkUrl t where id in("+ids+")" );
		List<LvLinkUrl> list= dao.find(hql.toString(), null);
		return list;
	}
	
	
	/**
	 * 
	 * @Method: htmlStatic 
	 * @Description:  [单个静态化连接实现单组静态化功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-9-29 下午2:56:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-9-29 下午2:56:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void htmlStatic(Dto dto)throws ServiceException{
		LvLinkUrl link=(LvLinkUrl) this.viewres(dto);
		if(ObjectUtils.isNotEmpty(link.getHql())){
			String hql=link.getHql();
			List<Object> list= dao.find(hql, null);
			if(ObjectUtils.isNotEmpty(list)&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					Object obj=list.get(i);
					String staticURL=link.getStaticurl().replace("@", obj.toString());
					initLinkDataService.sendHtmlToWeb(staticURL, link.getStoreFlag());
				}
			}
		}else{
			initLinkDataService.sendHtmlToWeb(link.getStaticurl(), link.getStoreFlag());
		}
	}
	
	
	/**
	 * 
	 * @Method: htmlStaticBatch 
	 * @Description:  [根据页面选择批量静态化指令发送到前端执行静态化操作]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-15 下午6:34:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-15 下午6:34:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void htmlStaticBatch(Dto dto)throws ServiceException {
		List<LvLinkUrl> linkList=this.findAllLink(dto);
		if(ObjectUtils.isNotEmpty(linkList)){
			for (LvLinkUrl lvLinkUrl : linkList) {
				if(ObjectUtils.isNotEmpty(lvLinkUrl.getHql())){
					String hql=lvLinkUrl.getHql();
					List<Object> list= dao.find(hql, null);
					if(ObjectUtils.isNotEmpty(list)&&list.size()>0){
						for (int i = 0; i < list.size(); i++) {
							Object obj=list.get(i);
							String staticURL=lvLinkUrl.getStaticurl().replace("@", obj.toString());
							initLinkDataService.sendHtmlToWeb(staticURL, lvLinkUrl.getStoreFlag());
						}
					}
				}else{
					initLinkDataService.sendHtmlToWeb(lvLinkUrl.getStaticurl(), lvLinkUrl.getStoreFlag());
				}
			}
		}
	}
	
	

}
