package com.lshop.web.blog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogLeave;
import com.lshop.common.pojo.logic.LvBlogTags;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.web.blog.service.BlogService;

/**
 * 官方博客
 * @author zhengxue
 * @version 2.0 2012-06-29
 *
 */
@Service("BlogService")
public class BlogServiceImpl extends BaseServiceImpl implements BlogService {
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
    private LvBlogContent lvBlogContent;
    private LvBlogTags lvBlogTags;
    private LvBlogLeave lvBlogLeave;
    
    private static final Log logger	= LogFactory.getLog(BlogServiceImpl.class);

	/**
	 * 查看所有的博客文章信息
	 * @param dto
	 * @return Pagination
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination list(Dto dto)throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.list() method begin*****");
		}	
		
		page = dto.getDefaultPage();
		lvBlogContent=(LvBlogContent)dto.get("model");
		String storeFlag=dto.getAsString("storeFlag");
		
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);

		StringBuffer hql=new StringBuffer();
		hql.append("from LvBlogContent where isDelete=1 and storeId=:storeFlag");
		if(lvBlogContent!=null){
			if(lvBlogContent.getKeyword()!=null){
				hql.append(" and keyword like :keyword");
				param.put("keyword", "%"+lvBlogContent.getKeyword()+"%");
			}
			if(lvBlogContent.getType()!=null){
				hql.append(" and type=:type");
				param.put("type", lvBlogContent.getType());
			}
		}
		hql.append(" order by createTime desc , id desc");
		page = lvlogicReadDao.find(Finder.create(hql.toString()).setParams(param),page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.list() method end*****");
		}	
		
		return page;
	}
	
	/**
	 * 查询热门博客文章信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getContentTop5(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getContentTop5() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		String hql="from LvBlogContent where isDelete=1 and storeId=:storeFlag order by clickNum desc";
		List list=lvlogicReadDao.find(Finder.create(hql).setParams(param),1,5).getList();
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getContentTop5() method end*****");
		}	
		
		return list;
	}
	
	/**
	 * 查询热门标签信息
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getTagsList(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getTagsList() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		String hql="from LvBlogTags where isShow=1 and storeId=:storeFlag order by orderId desc ";
		List list = lvlogicReadDao.find(Finder.create(hql).setParams(param),1, 20).getList();
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getTagsList() method end*****");
		}	
		
		return list;
	}
	
	/**
	 * 查询博客类别信息
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getTypeList(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getTypeList() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		String hql="from LvBlogType where storeId=:storeFlag order by orderId desc";
		List list = lvlogicReadDao.getMapListByHql(hql, param).getList();
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getTypeList() method end*****");
		}	
		
		return list;
	}
	
	/**
	 * 查询热门标签信息
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	@Override
	public LvBlogTags getTags(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getTags() method begin*****");
		}	
		
		lvBlogTags=(LvBlogTags) dto.get("model");
		lvBlogTags=(LvBlogTags)lvlogicReadDao.load(LvBlogTags.class,lvBlogTags.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getTags() method end*****");
		}	
		
		return lvBlogTags;
	}
	
	/**
	 * 根据博客文章id查询博客信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvBlogContent getContent(Dto dto) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getContent() method begin*****");
		}	
		
		lvBlogContent=(LvBlogContent) dto.get("model");
		lvBlogContent=(LvBlogContent) lvlogicReadDao.load(LvBlogContent.class, lvBlogContent.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getContent() method end*****");
		}	
		
		return lvBlogContent;
	}
	
	/**
	 * 汇总所有文章分类的条数
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getTypeCount(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getTypeCount() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		
		HashMap map = new HashMap();
		map.put("storeFlag", storeFlag);
		String hql="select count(id) as num,type from LvBlogContent where isDelete=1 and storeId=:storeFlag group by type";
		List<Object[]> list=lvlogicReadDao.find(hql, map);
		
		List rlist=new ArrayList();
		Map param=null;
		for (Object []object : list) {
			param=new HashMap();
			param.put("type", object[1]);
			param.put("num", object[0]);
			rlist.add(param);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getTypeCount() method end*****");
		}	
		
		return rlist;
	}
	
	/**
	 * 统计所有博客文章的总数
	 * @param dto
	 * @return Integer
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer sumContent(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.sumContent() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		Finder finder = Finder.create("select count(id) from LvBlogContent where isDelete=1 and storeId=:storeFlag");
		int sum = lvlogicReadDao.countQueryResult(finder, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.sumContent() method end*****");
		}	
		
		return sum;
	}
	
	/**
	 * 根据文章查询文章回复信息(评论)
	 * @param dto
	 * @return Pagination
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination leaveList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.leaveList() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		SimplePage page=(SimplePage) dto.get("page");
		lvBlogContent=(LvBlogContent) dto.get("model");
		
		HashMap param = new HashMap();
		param.put("contentId", lvBlogContent.getId());
		param.put("storeFlag", storeFlag);
		String hql = "from LvBlogLeave where contentId=:contentId and whoId is null and storeId=:storeFlag order by createTime desc ";
		Pagination pa = lvlogicReadDao.find(Finder.create(hql).setParams(param),page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.leaveList() method end*****");
		}	
		
		return  pa;
	}
	
	/**
	 * 查询管理员对评论的回复
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List manageLeaveList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.manageLeaveList() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		page=(Pagination) dto.get("pageModel");
		List list=page.getList();
		String idList="";
		for(int i=0;i<list.size();i++){
			LvBlogLeave lv=(LvBlogLeave)list.get(i);
			if(i==list.size()-1){
				idList+=lv.getId();
			}else{
			    idList+=lv.getId()+",";
			}
		}
		String hql="";
		if(idList!=null&&idList.trim().length()>0){
			hql="from LvBlogLeave where whoId in("+idList+") and storeId=:storeFlag order by createTime desc";
		}else{
			return null;
		}
		
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		List rList= lvlogicReadDao.getMapListByHql(hql, param).getList();
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.manageLeaveList() method end*****");
		}	
		
		return rList;
	}
	
	/**
	 * 新增文章评论
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvBlogLeave saveBlogLeave(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.saveBlogLeave() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		lvBlogLeave=(LvBlogLeave) dto.get("model");
		lvBlogLeave.setStoreId(storeFlag);
		lvlogicWriteDao.save(lvBlogLeave);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.saveBlogLeave() method end*****");
		}	
		
		return lvBlogLeave;
	}
	
	/**
	 * 更新博客的浏览数
	 * @param dto
	 * @return LvBlogContent
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvBlogContent updateClickNum(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.updateClickNum() method begin*****");
		}	
		
		HashMap param = new HashMap();
		lvBlogContent=(LvBlogContent) dto.get("model");
		
		String hql="update LvBlogContent set clickNum=clickNum+1 where id=:id and clickNum is not null";
		param.put("id", lvBlogContent.getId());
		lvlogicWriteDao.update(hql,param);
		
		hql="update LvBlogContent set clickNum=1 where id=:id and clickNum is null";
		param.put("id", lvBlogContent.getId());
		lvlogicWriteDao.update(hql,param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.updateClickNum() method end*****");
		}	
		
		return lvBlogContent;
	}
	
	/**
	 * 更新博客的回复数
	 * @param dto
	 * @return LvBlogContent
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvBlogContent updateReplyNum(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.updateReplyNum() method begin*****");
		}	
		
		HashMap param = new HashMap();
		lvBlogContent=(LvBlogContent) dto.get("model");
		
		String hql="update LvBlogContent set replyNum=replyNum+1 where id=:id and replyNum is not null";
		param.put("id", lvBlogContent.getId());
		lvlogicWriteDao.update(hql,param);
		
		hql="update LvBlogContent set replyNum=1 where id=:id and replyNum is  null";
		param.put("id", lvBlogContent.getId());
		lvlogicWriteDao.update(hql,param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.updateReplyNum() method end*****");
		}	
		
		return lvBlogContent;
	}
	
	/**
	 * 获取下一篇博客信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvBlogContent getNext(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getNext() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		lvBlogContent=(LvBlogContent) dto.get("model");
		HashMap param = new HashMap();
		
		param.put("id", lvBlogContent.getId());
		param.put("storeFlag", storeFlag);
		String hql="from LvBlogContent  where id>:id and storeId=:storeFlag order by id  ";
		List list = lvlogicReadDao.find(Finder.create(hql).setParams(param),1, 1).getList();
		if(list!=null&&list.size()>0){
			lvBlogContent=(LvBlogContent) list.get(0);
			return lvBlogContent;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getNext() method end*****");
		}	
		
		return null;
	}


	/**
	 * 获取上一篇博客信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvBlogContent getUp(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getUp() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		lvBlogContent=(LvBlogContent) dto.get("model");
		HashMap param = new HashMap();
		
		String hql="from LvBlogContent  where id<:id and storeId=:storeFlag order by id DESC ";
		param.put("id", lvBlogContent.getId());
		param.put("storeFlag", storeFlag);
		List list = lvlogicReadDao.find(Finder.create(hql).setParams(param),1, 1).getList();
		if(list!=null&&list.size()>0){
			lvBlogContent=(LvBlogContent) list.get(0);
			return lvBlogContent;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogServiceImpl.getUp() method end*****");
		}	
		
		return null;
	}
	
	/**
	 * 获取默认博客分类（首页展示用，排序值最大，创建时间最新）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvBlogType getDefaultBlogType(Dto dto) throws ServiceException{
		
		String hql = "from LvBlogType where storeId=:storeId order by orderId desc, createTime desc limit 1 ";
		HashMap param = new HashMap();
		param.put("storeId", dto.getAsString("storeFlag"));
		List<LvBlogType> list = (List<LvBlogType>)lvlogicReadDao.find(hql, param);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据博客分类获取博客内容（前四个）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvBlogContent> getContentsByType(Dto dto) throws ServiceException{
		
		String hql = "from LvBlogContent where type=:type and storeId=:storeFlag and isDelete=1 order by createTime desc , id desc";
		HashMap param = new HashMap();
		param.put("type", dto.getAsInteger("typeId"));
		param.put("storeFlag", dto.getAsString("storeFlag"));
		return lvlogicReadDao.find(hql, param);
	}
	
	
	public List<LvBlogContent> getContentsByRecommend(String storeFlag) throws ServiceException{
		String hql = "from LvBlogContent where storeId=:storeFlag and isRecommend=1 and isDelete=1 order by createTime desc , id desc";
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		return lvlogicReadDao.find(hql, param);
	}
	
	/**
	 * 根据分类ID获取所属博客分类
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvBlogType getBlogTypeById(Dto dto) throws ServiceException{
		String hql = "from LvBlogType where id=:typeId and storeId=:storeId order by orderId desc, createTime desc limit 1 ";
		HashMap param = new HashMap();
		param.put("typeId", dto.getAsInteger("typeId"));
		param.put("storeId", dto.getAsString("storeFlag"));
		List<LvBlogType> list = (List<LvBlogType>)lvlogicReadDao.find(hql, param);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public HibernateBaseDAO getLvlogicReadDao() {
		return lvlogicReadDao;
	}
	public void setLvlogicReadDao(HibernateBaseDAO lvlogicReadDao) {
		this.lvlogicReadDao = lvlogicReadDao;
	}
	public HibernateBaseDAO getLvlogicWriteDao() {
		return lvlogicWriteDao;
	}
	public void setLvlogicWriteDao(HibernateBaseDAO lvlogicWriteDao) {
		this.lvlogicWriteDao = lvlogicWriteDao;
	}

}
