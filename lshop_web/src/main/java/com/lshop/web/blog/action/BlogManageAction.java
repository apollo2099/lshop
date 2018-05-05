package com.lshop.web.blog.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogLeave;
import com.lshop.common.pojo.logic.LvBlogTags;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.util.HtmlRegexpUtil;
import com.lshop.web.userCenter.UserConstant;

/**
 * 官方博客
 * @author zhengxue
 * @version 2.0 2012-06-29
 *
 */
@SuppressWarnings("serial")
@Controller("BlogManageAction")
@Scope("prototype")
public class BlogManageAction extends BaseAction {
	
	private Integer contentId;
	private LvBlogContent lvBlogContent;
	private LvBlogTags lvBlogTags;
	private LvBlogLeave lvBlogLeave;
//	private int type;
	private String keyword;
	private boolean reviewFlag=false;
	
	
	/**
	 * 博客列表首页显示信息
	 * @return
	 */
	public String list(){
		
		//查询所有的博客文章列表
		page.setNumPerPage(10);
		dto.setDefaultPage(page);
		dto.put("model", lvBlogContent);
		dto.put("storeFlag", this.getFlag());
		page=(Pagination)this.doService("BlogService", "list", dto);
		List list=page.getList();
		
		//将博客文章内容过滤掉HTML标签显示
		for(int i=0;i<list.size();i++){
			LvBlogContent lvBlogContent=(LvBlogContent)list.get(i);
			String content=HtmlRegexpUtil.filterHtml(lvBlogContent.getContent());
			content=content.replace("&nbsp;", "");
			lvBlogContent.setContent(content);
			
			//分割博客标签
			if(ObjectUtils.isNotEmpty(lvBlogContent.getKeyword())){
				List<String> taglist=new ArrayList<String>();
				String tagStr=lvBlogContent.getKeyword();
				String [] arr=tagStr.split(",");
				for (int num = 0; num < arr.length; num++) {
					if (ObjectUtils.isNotEmpty(arr[num])) {
						taglist.add(arr[num]);
					}
				}
				lvBlogContent.setTagList(taglist);
			}
			
			
		}
		page.setList(list);
		getRequest().setAttribute("pagination", page);
		
		//查询所有博客文章的条数
		Integer contentSum=(Integer) this.doService("BlogService", "sumContent", dto);
		this.getRequest().setAttribute("contentSum", contentSum);
		
		//查询所有博客文章分类
		List typeList=(List) this.doService("BlogService", "getTypeList", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
		//汇总所有博客文章分类的条数
		List typeCountList=(List) this.doService("BlogService", "getTypeCount", dto);
		this.getRequest().setAttribute("typeCountList", typeCountList);
		
		//查询热门标签信息(2o条)
		List tagsList=(List) doService("BlogService", "getTagsList", dto);
		this.getRequest().setAttribute("tagsList", tagsList);
		dto.clear();
		
		//查询熱點博文
		dto.put("storeFlag", this.getFlag());
		List contentList=(List) doService("BlogService", "getContentTop5", dto);
		this.getRequest().setAttribute("contentList", contentList);
		
		//首页博客文章导航
	    this.getRequest().setAttribute("lvBlogContent", lvBlogContent);
	    
		return "list";
	}
	
	/**
	 * 根据 博客编号查询博客文章的详细信息
	 * @return
	 */
	public String view(){
		dto.put("storeFlag", this.getFlag());
		//1.更新博客文章的浏览次数(+1)
		if(!reviewFlag){
			dto.put("model", lvBlogContent);
//			lvBlogContent=(LvBlogContent) this.doService("BlogService", "updateClickNum", dto);
		}
		
	    //2.查看博客文章信息
		dto.put("model", lvBlogContent);
		lvBlogContent=(LvBlogContent) this.doService("BlogService", "getContent", dto);
		this.getRequest().setAttribute("lvBlogContent", lvBlogContent);
		
		//3.查询所有博客文章的条数
		Integer contentSum=(Integer) this.doService("BlogService", "sumContent", dto);
		this.getRequest().setAttribute("contentSum", contentSum);
		
	    //4.(1)查询对应博客文章的评论信息
		page.setNumPerPage(10);
		dto.put("page", page);
		page=(Pagination)this.doService("BlogService", "leaveList", dto);
		getRequest().setAttribute("pagination", page);
		
		//  (2)根据文章评论查询管理员回复信息
		dto.put("pageModel", page);
		List manageLeaveList=(List)this.doService("BlogService", "manageLeaveList", dto);
		getRequest().setAttribute("manageLeaveList", manageLeaveList);
		
        //5.查询所有博客文章分类
		List typeList=(List) this.doService("BlogService", "getTypeList", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
		//6.汇总所有博客文章分类的条数
		List typeCountList=(List) this.doService("BlogService", "getTypeCount", dto);
		this.getRequest().setAttribute("typeCountList", typeCountList);
		
		//7.查询热门标签信息(2o条)
		List tagsList=(List) doService("BlogService", "getTagsList", dto);
		this.getRequest().setAttribute("tagsList", tagsList);
		
		//8.查询熱點博文
		List contentList=(List) doService("BlogService", "getContentTop5", dto);
		this.getRequest().setAttribute("contentList", contentList);
		
		//9.获取前一篇，后一篇博客文章信息
		LvBlogContent upContent= (LvBlogContent) doService("BlogService", "getUp", dto);
		this.getRequest().setAttribute("upContent", upContent);
		LvBlogContent nextContent= (LvBlogContent) doService("BlogService", "getNext", dto);
		this.getRequest().setAttribute("nextContent", nextContent);
		
		if(null!=lvBlogContent){
			if(null!=lvBlogContent.getType()){
	    		//获取该博客的分类信息
	    		dto.put("typeId", lvBlogContent.getType());
	    		dto.put("storeFlag", this.getFlag());
	    		LvBlogType blogType = (LvBlogType)this.doService("BlogService", "getBlogTypeById", dto);
	    		this.getRequest().setAttribute("blogType", blogType);
	    	}
		}
	    	
		//博客详情页面导航
	    this.getRequest().setAttribute("keyword", keyword);//博客热门标签
	    return "view";
	}
	
	/**
	 * 发表评论
	 * @return
	 */
	public String review() throws Exception{
	
		Map<String, String> userInfo = getCookieValueToMap(UserConstant.USER_CENTER,true);
		Integer userId=Integer.parseInt(userInfo.get("id"));
		String userName=userInfo.get("nickname");
	
		contentId=lvBlogContent.getId();
		reviewFlag=true;
		dto.put("storeFlag", this.getFlag());
		//修改文章博客的回复数（+1）
		dto.put("model", lvBlogContent);
		lvBlogContent=(LvBlogContent) this.doService("BlogService", "updateReplyNum", dto);
		
		//新增文章的回复评论
		lvBlogLeave.setCreateTime(new Date());
		lvBlogLeave.setStoreId(this.getFlag());
		lvBlogLeave.setUserId(userId);
		lvBlogLeave.setUserName(userName);
		dto.put("model", lvBlogLeave);
		lvBlogLeave=(LvBlogLeave) this.doService("BlogService", "saveBlogLeave", dto);	
		return "review";
	}
	
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public LvBlogContent getLvBlogContent() {
		return lvBlogContent;
	}
	public void setLvBlogContent(LvBlogContent lvBlogContent) {
		this.lvBlogContent = lvBlogContent;
	}
	public LvBlogTags getLvBlogTags() {
		return lvBlogTags;
	}
	public void setLvBlogTags(LvBlogTags lvBlogTags) {
		this.lvBlogTags = lvBlogTags;
	}
	public LvBlogLeave getLvBlogLeave() {
		return lvBlogLeave;
	}
	public void setLvBlogLeave(LvBlogLeave lvBlogLeave) {
		this.lvBlogLeave = lvBlogLeave;
	}
//	public int getType() {
//		return type;
//	}
//	public void setType(int type) {
//		this.type = type;
//	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public boolean isReviewFlag() {
		return reviewFlag;
	}
	public void setReviewFlag(boolean reviewFlag) {
		this.reviewFlag = reviewFlag;
	}
}
