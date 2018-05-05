/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvLotteryPrize;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.util.CodeUtils;
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
 */@Controller("LvLotteryPrizeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvLotteryPrizeAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvLotteryPrizeAction.class);
	private LvLotteryPrize lvLotteryPrize = new LvLotteryPrize();
	private File img;//图片
	private String imgFileName;//图片名称


	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvLotteryPrize", lvLotteryPrize);
		
		page = (Pagination)this.doService("LvLotteryPrizeService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		dto.put("code",lvLotteryPrize.getActivityCode());
		LvActivity  lvActivity=(LvActivity) this.doService("LvActivityService", "findByCode", dto);
		this.getRequest().setAttribute("lvActivity", lvActivity);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String save()throws ActionException, FileNotFoundException, IOException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.save() method begin*****");
		}
		LvProduct lvProduct=new LvProduct();
		lvProduct.setStoreId("tvpadcn");
		dto.put("lvProduct", lvProduct);
		//商城产品详情主图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
			if (!(type.toLowerCase().equals(".jpg")
					|| type.toLowerCase().equals(".jpeg")
					|| type.toLowerCase().equals(".png")
					|| type.toLowerCase().equals(".gif"))) {
				json.setMessage("只能上传JPG、JPEG、PNG、GIF格式的图片文件，请重新选择！");
				json.setStatusCode(300);
				return AJAX;
			}
			if (new FileInputStream(img).available() > 1024 * 1024 * 2) {
				json.setMessage("文件大小过大，请压缩至2M以内！");
				json.setStatusCode(300);
				return AJAX;
			}
			String imgPath=(String) this.doService("LvProductService", "upload", dto);
			lvLotteryPrize.setPrizeImages(imgPath);
		}
		if(ObjectUtils.isNotEmpty(lvLotteryPrize.getGivenTypeName())){
			lvLotteryPrize.setPrizeName(lvLotteryPrize.getGivenTypeName());
		}
		if(ObjectUtils.isNotEmpty(lvLotteryPrize.getGivenProductCode())){
			lvLotteryPrize.setPrizeCode(lvLotteryPrize.getGivenProductCode());
		}
		
		//
		if(ObjectUtils.isNotEmpty(lvLotteryPrize.getPrizeType())&&lvLotteryPrize.getPrizeType()==1){
			dto.put("couponTypeCode", lvLotteryPrize.getPrizeCode());
			LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
			//剩余库存判断
			Integer num=lvCouponType.getTotal()-(lvCouponType.getNoGrantNumber()+lvCouponType.getUsedNumber()+lvCouponType.getGrantNumber());
			if(ObjectUtils.isNotEmpty(lvLotteryPrize.getPrizeTotal())&&lvLotteryPrize.getPrizeTotal()>num){
				 this.json.setMessage("添加奖项优惠券数量不能大于可分配库存！");
			     this.json.setStatusCode(300);
				 return AJAX;
			}
			
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		
		lvLotteryPrize.setGrantTotal(0);
		lvLotteryPrize.setCode(CodeUtils.getCode());//code设置
		lvLotteryPrize.setCreateTime(new Date());   //创建时间
		dto.put("lvLotteryPrize", lvLotteryPrize);
		dto.put("users", users);
		this.doService("LvLotteryPrizeService", "save", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.save() method end*****");
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
			logger.info("***** LvLotteryPrizeAction.view() method begin*****");
		}
		dto.put("lvLotteryPrize", lvLotteryPrize);
		lvLotteryPrize = (LvLotteryPrize)this.doService("LvLotteryPrizeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.view() method end*****");
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
			logger.info("***** LvLotteryPrizeAction.befEdit() method begin*****");
		}
		dto.put("lvLotteryPrize", lvLotteryPrize);
		lvLotteryPrize = (LvLotteryPrize)this.doService("LvLotteryPrizeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String edit()throws ActionException, FileNotFoundException, IOException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.edit() method begin*****");
		}
		LvProduct lvProduct=new LvProduct();
		lvProduct.setStoreId("tvpadcn");
		dto.put("lvProduct", lvProduct);
		//商城产品详情主图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
			if (!(type.toLowerCase().equals(".jpg")
					|| type.toLowerCase().equals(".jpeg")
					|| type.toLowerCase().equals(".png")
					|| type.toLowerCase().equals(".gif"))) {
				json.setMessage("只能上传JPG、JPEG、PNG、GIF格式的图片文件，请重新选择！");
				json.setStatusCode(300);
				return AJAX;
			}
			if (new FileInputStream(img).available() > 1024 * 1024 * 2) {
				json.setMessage("文件大小过大，请压缩至2M以内！");
				json.setStatusCode(300);
				return AJAX;
			}
			String imgPath=(String) this.doService("LvProductService", "upload", dto);
			lvLotteryPrize.setPrizeImages(imgPath);
		}
		
		//奖项物品追加数目
		if(ObjectUtils.isNotEmpty(lvLotteryPrize.getAddNumber())){
			lvLotteryPrize.setPrizeTotal(lvLotteryPrize.getPrizeTotal()+lvLotteryPrize.getAddNumber());
			if(lvLotteryPrize.getPrizeType()==1){
				dto.put("couponTypeCode", lvLotteryPrize.getPrizeCode());
				LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
				if(ObjectUtils.isNotEmpty(lvCouponType)){
				   //剩余库存判断
				   Integer num=lvCouponType.getTotal()-(lvCouponType.getNoGrantNumber()+lvCouponType.getUsedNumber()+lvCouponType.getGrantNumber());
				   if(lvLotteryPrize.getAddNumber()>num){
						 this.json.setMessage("添加奖项优惠券数量不能大于可分配库存！");
					     this.json.setStatusCode(300);
						 return AJAX;
				   }
				}
			}
		}
		
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvLotteryPrize.setModifyUserId(users.getId());
		lvLotteryPrize.setModifyUserName(users.getUserName());
		lvLotteryPrize.setModifyTime(new Date());
		dto.put("lvLotteryPrize", lvLotteryPrize);
		this.doService("LvLotteryPrizeService", "update", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.edit() method end*****");
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
			logger.info("***** LvLotteryPrizeAction.del() method begin*****");
		}
		dto.put("lvLotteryPrize", lvLotteryPrize);
		//删除lvLotteryPrize
		this.doService("LvLotteryPrizeService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.del() method end*****");
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
			logger.info("***** LvLotteryPrizeAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvLotteryPrize.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvLotteryPrize", lvLotteryPrize);
				//删除lvLotteryPrize
				this.doService("LvLotteryPrizeService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeAction.delList() method end*****");
		}
		return AJAX;
	}
	
	
	public LvLotteryPrize getLvLotteryPrize() {
		return lvLotteryPrize;
	}

	public void setLvLotteryPrize(LvLotteryPrize lvLotteryPrize) {
		this.lvLotteryPrize = lvLotteryPrize;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

}
