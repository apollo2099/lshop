/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubProduct.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvPubProduct.service.LvPubProductService;
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
@Controller("LvPubProductAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvPubProductAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvPubProductAction.class);
	private LvPubProduct lvPubProduct = new LvPubProduct();

	@Resource
	private LvPubProductService lvPubProductService;
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvPubProduct", lvPubProduct);
		
		page = (Pagination)this.doService("LvPubProductService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.list() method end*****");
		}
		return LIST;
	}
	
	
	/**
	 * 查找带回单个产品时的列表
	 * @return
	 * @throws ActionException 
	 */
	public String selectSingleProduct()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvPubProduct", lvPubProduct);
		
		page = (Pagination)this.doService("LvPubProductService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.list() method end*****");
		}
		return "selectSingleProduct";
	}
	
	/**
	 * 查找带回多个产品时的列表
	 * @return
	 * @throws ActionException
	 */
	public String selectMultipleProduct()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvPubProduct", lvPubProduct);
		
		page = (Pagination)this.doService("LvPubProductService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.list() method end*****");
		}
		return "selectMultipleProduct";
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
			logger.info("***** LvPubProductAction.save() method begin*****");
		}
		//验证SAS对接编码唯一性
		if(ObjectUtils.isNotEmpty(lvPubProduct.getPcode())){
			LvPubProduct pubProduct=lvPubProductService.findByPcode(lvPubProduct.getPcode());
			if(ObjectUtils.isNotEmpty(pubProduct)){
				json.setMessage("SAS对接编码已经存在，请重新输入！");
				json.setStatusCode(300);
				return AJAX;
			}
		}
		
		//保存商品库信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvPubProduct.setCode(CodeUtils.getCode());
		lvPubProduct.setCreateTime(new Date());
		lvPubProduct.setStatus(Short.parseShort("0"));
		dto.put("lvPubProduct", lvPubProduct);
		this.doService("LvPubProductService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.save() method end*****");
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
			logger.info("***** LvPubProductAction.view() method begin*****");
		}
		dto.put("lvPubProduct", lvPubProduct);
		lvPubProduct = (LvPubProduct)this.doService("LvPubProductService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.view() method end*****");
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
			logger.info("***** LvPubProductAction.befEdit() method begin*****");
		}
		dto.put("lvPubProduct", lvPubProduct);
		lvPubProduct = (LvPubProduct)this.doService("LvPubProductService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.befEdit() method end*****");
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
			logger.info("***** LvPubProductAction.edit() method begin*****");
		}
		
		//验证SAS对接编码唯一性
		if(ObjectUtils.isNotEmpty(lvPubProduct.getPcode())&&ObjectUtils.isNotEmpty(lvPubProduct.getOldPcode())){
			if(!lvPubProduct.getPcode().equals(lvPubProduct.getOldPcode())){
				LvPubProduct pubProduct=lvPubProductService.findByPcode(lvPubProduct.getPcode());
				if(ObjectUtils.isNotEmpty(pubProduct)){
					json.setMessage("SAS对接编码已经存在，请重新输入！");
					json.setStatusCode(300);
					return AJAX;
				}
			}
		}
		
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvPubProduct.setModifyUserId(users.getId());
		lvPubProduct.setModifyUserName(users.getUserName());
		lvPubProduct.setModifyTime(new Date());
		dto.put("lvPubProduct", lvPubProduct);
		this.doService("LvPubProductService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.edit() method end*****");
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
			logger.info("***** LvPubProductAction.del() method begin*****");
		}
		dto.put("lvPubProduct", lvPubProduct);
		lvPubProduct = (LvPubProduct)this.doService("LvPubProductService", "get", dto);
		//判断当前公共产品是否包含店铺商品信息
		dto.put("pubProductCode", lvPubProduct.getCode());
		Boolean isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
		if(isFlag){
			json.setMessage("店铺商品中含该商品,无法删除！");
			json.setStatusCode(300);
			return AJAX;
		}
		//验证是否存在套餐详情
		isFlag=(Boolean) this.doService("LvPubPackageDetailsService", "isExistPubPackageDetail", dto);
		if(isFlag){
			json.setMessage("商品库套装信息中含该商品,无法删除！");
			json.setStatusCode(300);
			return AJAX;
		}
		
		//删除lvPubProduct
		this.doService("LvPubProductService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.del() method end*****");
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
			logger.info("***** LvPubProductAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			Integer num=0;
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
					lvPubProduct.setId(Integer.parseInt(temp_ids[i].trim()));
					dto.put("lvPubProduct", lvPubProduct);
					lvPubProduct = (LvPubProduct)this.doService("LvPubProductService", "get", dto);
					//判断当前公共产品是否包含店铺商品信息
					dto.put("pubProductCode", lvPubProduct.getCode());
					Boolean isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
					isFlag=(Boolean) this.doService("LvPubPackageDetailsService", "isExistPubPackageDetail", dto);
					if(!isFlag){
						//删除lvPubProduct
						this.doService("LvPubProductService", "del", dto);
						num++;
					}
				}
			}
			
			if(num<=0){
				json.setMessage("删除失败，店铺商品中都含该商品,无法删除!");
				json.setStatusCode(300);
				json.setCallbackType(null);
				return AJAX;
			}else{
				json.setMessage("删除公共商品："+num+"条成功,"+(temp_ids.length-num)+"条失败!");
				json.setStatusCode(200);
				json.setCallbackType(null);
				return AJAX;
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public LvPubProduct getLvPubProduct() {
		return lvPubProduct;
	}

	public void setLvPubProduct(LvPubProduct lvPubProduct) {
		this.lvPubProduct = lvPubProduct;
	}

}
