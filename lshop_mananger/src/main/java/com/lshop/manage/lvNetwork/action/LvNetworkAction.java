package com.lshop.manage.lvNetwork.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.pojo.logic.LvNetwork;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * 服务网点管理
 * @author zhengxue
 *
 */
@Controller("LvNetworkAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvNetworkAction  extends BaseManagerAction{
	
	private static final Log logger	= LogFactory.getLog(LvNetworkAction.class);
	private LvNetwork lvNetwork;


	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.list() method begin*****");
		}
		
		dto.put("page", page);
		dto.setDefaultPo(lvNetwork);
		page = (Pagination)this.doService("LvNetworkService", "findPage", dto);
		
		//查询所有的国家信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("areaList", areaList);
		
		//查询所有商城
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
		this.getRequest().setAttribute("storeList", storeList);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.list() method end*****");
		}
    	
		return LIST;
	}

	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		
		//查询所有的国家信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("areaList", areaList);
		
		//查询所有商城
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
		this.getRequest().setAttribute("storeList", storeList);
				
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.save() method begin*****");
		}

		//判断语种
		LvArea lvArea = new LvArea();
		lvArea.setId(lvNetwork.getContryId());
		dto.put("lvArea", lvArea);
		lvArea = (LvArea)this.doService("LvAreaService", "get", dto);
		String language = lvNetwork.getWebLanguage();
		if(language.equals("cn")){
			lvNetwork.setCountry(lvArea.getNamecn());
		}
		if(language.equals("en")){
			lvNetwork.setCountry(lvArea.getNameen());
		}
		if(language.equals("tw")){
			lvNetwork.setCountry(lvArea.getNametw());
		}
		if(language.equals("kn")){
			lvNetwork.setCountry(lvArea.getNamekn());
		}

		lvNetwork.setCreateTime(new Date());
		dto.setDefaultPo(lvNetwork);
		this.doService("LvNetworkService", "save", dto);
		json.setStatusCode(200);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.save() method end*****");
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
			logger.info("***** LvNetworkAction.view() method begin*****");
		}
		
		dto.setDefaultPo(lvNetwork);
		lvNetwork = (LvNetwork)this.doService("LvNetworkService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.view() method end*****");
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
			logger.info("***** LvNetworkAction.befEdit() method begin*****");
		}
		
		dto.setDefaultPo(lvNetwork);
		lvNetwork = (LvNetwork)this.doService("LvNetworkService", "get", dto);
		
		//查询所有的国家信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("areaList", areaList);
		
		//查询所有商城
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.befEdit() method end*****");
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
			logger.info("***** LvNetworkAction.edit() method begin*****");
		}
		
		//判断语种
		LvArea lvArea = new LvArea();
		lvArea.setId(lvNetwork.getContryId());
		dto.put("lvArea", lvArea);
		lvArea = (LvArea)this.doService("LvAreaService", "get", dto);
		String language = lvNetwork.getWebLanguage();
		if(language.equals("cn")){
			lvNetwork.setCountry(lvArea.getNamecn());
		}
		if(language.equals("en")){
			lvNetwork.setCountry(lvArea.getNameen());
		}
		if(language.equals("tw")){
			lvNetwork.setCountry(lvArea.getNametw());
		}
		if(language.equals("kn")){
			lvNetwork.setCountry(lvArea.getNamekn());
		}
		
		lvNetwork.setModifyTime(new Date());
		dto.setDefaultPo(lvNetwork);
		this.doService("LvNetworkService", "update", dto);
		json.setStatusCode(200);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.edit() method end*****");
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
			logger.info("***** LvNetworkAction.del() method begin*****");
		}
		
		dto.setDefaultPo(lvNetwork);
		this.doService("LvNetworkService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.del() method end*****");
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
			logger.info("***** LvNetworkAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				LvNetwork network = new LvNetwork();
				network.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.setDefaultPo(network);
				this.doService("LvNetworkService", "del", dto);
				}
			}
		}
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public LvNetwork getLvNetwork() {
		return lvNetwork;
	}
	
	public void setLvNetwork(LvNetwork lvNetwork) {
		this.lvNetwork = lvNetwork;
	}

}
