/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPatternCountry.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvPatternCountry;
import com.lshop.manage.common.action.BaseManagerAction;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Controller("LvPatternCountryAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvPatternCountryAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvPatternCountryAction.class);
	private LvPatternCountry lvPatternCountry = new LvPatternCountry();
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvPatternCountry", lvPatternCountry);
		
		page = (Pagination)this.doService("LvPatternCountryService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.list() method end*****");
		}
		return LIST;
	}
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		//查询所以的国家信息
		List<LvArea> countryList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("countryList", countryList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.save() method begin*****");
		}

		//判断当前规格区域配置是否已经存在
		dto.put("countryId", lvPatternCountry.getCountryId());
		LvPatternCountry temp=(LvPatternCountry) this.doService("LvPatternCountryService", "findByCountryId", dto);
		if(ObjectUtils.isNotEmpty(temp)){
			json.setMessage("该地区的规格已经设置，请重新选择！");
			json.setStatusCode(300);
			return AJAX;
		}
		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");		
		lvPatternCountry.setCreatedBy(users.getUserName());
		lvPatternCountry.setCreatedDate(new Date());
		lvPatternCountry.setStatus(1);
		lvPatternCountry.setVersion(0);
		dto.put("lvPatternCountry", lvPatternCountry);
		this.doService("LvPatternCountryService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.save() method end*****");
		}
		return AJAX;
	}
	


	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.view() method begin*****");
		}
		dto.put("lvPatternCountry", lvPatternCountry);
		lvPatternCountry = (LvPatternCountry)this.doService("LvPatternCountryService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 跳入编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.befEdit() method begin*****");
		}
		dto.put("lvPatternCountry", lvPatternCountry);
		lvPatternCountry = (LvPatternCountry)this.doService("LvPatternCountryService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");	
		lvPatternCountry.setLastUpdatedBy(users.getUserName());
		lvPatternCountry.setLastUpdatedDate(new Date());
		dto.put("lvPatternCountry", lvPatternCountry);
		this.doService("LvPatternCountryService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.del() method begin*****");
		}
		dto.put("lvPatternCountry", lvPatternCountry);
		//删除lvPatternCountry
		this.doService("LvPatternCountryService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws ActionException
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvPatternCountry.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvPatternCountry", lvPatternCountry);
				//删除lvPatternCountry
				this.doService("LvPatternCountryService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPatternCountryAction.delList() method end*****");
		}
		return AJAX;
	}


	public LvPatternCountry getLvPatternCountry() {
		return lvPatternCountry;
	}

	public void setLvPatternCountry(LvPatternCountry lvPatternCountry) {
		this.lvPatternCountry = lvPatternCountry;
	}
	
}
