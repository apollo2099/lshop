/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubPackage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvPubPackage;
import com.lshop.common.pojo.logic.LvPubPackageDetails;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.util.CodeUtils;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvPubPackageAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvPubPackageAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvPubPackageAction.class);
	private LvPubPackage lvPubPackage = new LvPubPackage();


	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvPubPackage", lvPubPackage);
		
		page = (Pagination)this.doService("LvPubPackageService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.list() method end*****");
		}
		return LIST;
	}

	/**
	 * 跳转到单个页面查找带回套餐列表页面
	 * @return
	 * @throws ActionException
	 */
	public String selectSinglePackage()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvPubPackage", lvPubPackage);
		
		page = (Pagination)this.doService("LvPubPackageService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.list() method end*****");
		}
		return "selectSinglePackage";
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.save() method begin*****");
		}

		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvPubPackage.setCode(CodeUtils.getCode());
		lvPubPackage.setCreateTime(new Date());
		lvPubPackage.setStatus(Short.parseShort("0"));
		dto.put("lvPubPackage", lvPubPackage);
		
		//根据套餐名称判断是否存在
		Boolean isFlag=(Boolean) this.doService("LvPubPackageService", "isExistPubPackage", dto);
		if(isFlag){
			json.setMessage("该套餐名称已经存在!");
			json.setStatusCode(300);
			return AJAX;
		}
		
		this.doService("LvPubPackageService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.save() method end*****");
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
			logger.info("***** LvPubPackageAction.view() method begin*****");
		}
		//查询套餐信息
		dto.put("lvPubPackage", lvPubPackage);
		lvPubPackage = (LvPubPackage)this.doService("LvPubPackageService", "get", dto);
		
		//查询套餐详情信息
		dto.put("pubPackageCode", lvPubPackage.getCode());
		List<LvPubPackageDetails> packageDetailsList= (List<LvPubPackageDetails>)this.doService("LvPubPackageDetailsService", "findAll", dto);
		this.getRequest().setAttribute("packageDetailsList", packageDetailsList);
		
		//查询公共商品信息		
		List<LvPubProduct> pubProductList= (List<LvPubProduct>)this.doService("LvPubProductService", "findAll", dto);
		this.getRequest().setAttribute("pubProductList", pubProductList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.view() method end*****");
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
			logger.info("***** LvPubPackageAction.befEdit() method begin*****");
		}
		//查询套餐信息
		dto.put("lvPubPackage", lvPubPackage);
		lvPubPackage = (LvPubPackage)this.doService("LvPubPackageService", "get", dto);
		
		//查询套餐详情信息
		dto.put("pubPackageCode", lvPubPackage.getCode());
		List<LvPubPackageDetails> packageDetailsList= (List<LvPubPackageDetails>)this.doService("LvPubPackageDetailsService", "findAll", dto);
		this.getRequest().setAttribute("packageDetailsList", packageDetailsList);
		
		//查询公共商品信息		
		List<LvPubProduct> pubProductList= (List<LvPubProduct>)this.doService("LvPubProductService", "findAll", dto);
		this.getRequest().setAttribute("pubProductList", pubProductList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.befEdit() method end*****");
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
			logger.info("***** LvPubPackageAction.edit() method begin*****");
		}
		
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvPubPackage.setModifyUserId(users.getId());
		lvPubPackage.setModifyUserName(users.getUserName());
		lvPubPackage.setModifyTime(new Date());
		dto.put("lvPubPackage", lvPubPackage);
		
		if(ObjectUtils.isNotEmpty(lvPubPackage.getPackageName())&&ObjectUtils.isNotEmpty(lvPubPackage.getOldPackageName())){
			if(!lvPubPackage.getPackageName().equals(lvPubPackage.getOldPackageName())){
				//根据套餐名称判断是否存在
				Boolean isFlag=(Boolean) this.doService("LvPubPackageService", "isExistPubPackage", dto);
				if(isFlag){
					json.setMessage("该套餐名称已经存在!");
					json.setStatusCode(300);
					return AJAX;
				}				
			}
		}

		
		this.doService("LvPubPackageService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.edit() method end*****");
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
			logger.info("***** LvPubPackageAction.del() method begin*****");
		}
		dto.put("lvPubPackage", lvPubPackage);
		lvPubPackage = (LvPubPackage)this.doService("LvPubPackageService", "get", dto);
		dto.put("pubProductCode", lvPubPackage.getCode());
		Boolean isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
		if(isFlag){
			json.setMessage("店铺商品中含该套餐,无法删除！");
			json.setStatusCode(300);
			return AJAX;
		}
		//删除lvPubPackage
		this.doService("LvPubPackageService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.del() method end*****");
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
			logger.info("***** LvPubPackageAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			Integer num=0;
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
					lvPubPackage.setId(Integer.parseInt(temp_ids[i].trim()));
					dto.put("lvPubPackage", lvPubPackage);
					lvPubPackage = (LvPubPackage)this.doService("LvPubPackageService", "get", dto);
					dto.put("pubProductCode", lvPubPackage.getCode());
					Boolean isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
					if(!isFlag){
						//删除lvPubPackage
						this.doService("LvPubPackageService", "del", dto);
						num++;
					}
				}
			}
			
			if(num<=0){
				json.setMessage("删除失败，店铺商品中都含该套餐,无法删除!");
				json.setStatusCode(300);
				json.setCallbackType(null);
				return AJAX;
			}else{
				json.setMessage("删除公共套餐："+num+"条成功,"+(temp_ids.length-num)+"条失败!");
				json.setStatusCode(200);
				json.setCallbackType(null);
				return AJAX;
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubPackageAction.delList() method end*****");
		}
		return AJAX;
	}
	
	
	
	/**
	 * 
	 * @Method: toProductType 
	 * @Description:  [根据公共套餐code查询套餐详情和公共商品组合信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-13 下午3:27:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-13 下午3:27:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toPackageDetails(){
		PrintWriter out = null;
		Map<String,Object>  mapTmp=new HashMap<String,Object>();
		List listTmp=new ArrayList();
		if(lvPubPackage!=null&&ObjectUtils.isNotEmpty(lvPubPackage.getCode())){
			dto.put("lvPubPackage", lvPubPackage);
			dto.put("pubPackageCode", lvPubPackage.getCode());
			//根据套餐code查询套餐信息
			LvPubPackage pubPackage=(LvPubPackage) this.doService("LvPubPackageService", "findByCode", dto);
			mapTmp.put("pubPackage", pubPackage);
			
			
			//根据套餐code查询套餐详情信息
			Pagination page=(Pagination) this.doService("LvPubPackageDetailsService", "findByPackageCode", dto);
			List list= page.getList();
			for (int i = 0; i < list.size(); i++) {
				Map map= (Map) list.get(i);
				listTmp.add(map);
			}
			mapTmp.put("listTmp", listTmp);
			try {
				response.setContentType("text/html;charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");
			    out=this.getResponse().getWriter();
			    String jsonTmp = JSONObject.fromObject(mapTmp).toString();
			    logger.info("json_message"+jsonTmp);
				out.print(jsonTmp );
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	
	
	public LvPubPackage getLvPubPackage() {
		return lvPubPackage;
	}

	public void setLvPubPackage(LvPubPackage lvPubPackage) {
		this.lvPubPackage = lvPubPackage;
	}

}
