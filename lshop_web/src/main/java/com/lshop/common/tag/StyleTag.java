package com.lshop.common.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvShopTags;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;

/**
 * 标签字体样式
 * @author zhengxue
 * @version 1.0 2012-12-19
 *
 */
@SuppressWarnings("serial")
public class StyleTag extends BodyTagSupport {
	
	public Dto dto = new BaseDto();
	
	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {

	    String serverName =  pageContext.getRequest().getServerName();;
	    String flag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
	    String  storeFlag=StoreHelp.getParentFlagByFlag(flag);
	    if(storeFlag==null){
	    	storeFlag=flag;
	    }
        JspWriter out=pageContext.getOut();  
        
        //从数据库中获取标签信息列表
        HibernateBaseDAO lvlogicReadDao =(HibernateBaseDAO) ServiceConstants.beanFactory.getBean("lvlogicReadDao");
        Map map=new HashMap();
        map.put("storeFlag", storeFlag);
        List<LvShopTags> tagList=(List<LvShopTags>)lvlogicReadDao.find("from LvShopTags where storeId=:storeFlag order by orderValue desc", map);
        
        StringBuilder html=new StringBuilder();
        if(null!=tagList && tagList.size()>0){
        	for(LvShopTags shopTag:tagList){
        		//加链接
        		html.append("<a href='"+shopTag.getTagUrl()+"'>");
        		//加颜色
        		html.append("<font color='"+shopTag.getTagFontColor()+"'>");
        		//加样式（粗体、斜体、下划线等）
        		if(null!=shopTag.getTagFontStyle() && !("").equals(shopTag.getTagFontStyle())){
        			if(shopTag.getTagFontStyle().indexOf("tagFontStyle")!=-1){
        				html.append(shopTag.getTagFontStyle().replace("tagFontStyle", shopTag.getTagName()));
        			}
        		}else{
        			html.append(shopTag.getTagName());
        		}
        		html.append("</font>");
        		html.append("</a>");
        		//标签与标签之间间隔两个空格
        		html.append("&nbsp;&nbsp;");
        	}
        	
        }
        
		try {
			out.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doEndTag();

	}
}

