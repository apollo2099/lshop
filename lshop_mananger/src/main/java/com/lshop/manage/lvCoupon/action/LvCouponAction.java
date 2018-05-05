/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvCoupon.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.progressBar.utils.ProgressBarConstants;
import com.lshop.common.progressBar.utils.ProgressBarUtils;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.FileUtil;
import com.lshop.common.util.POIExcelUtil;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.excel.ExcelWriter;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvCouponAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvCouponAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvCouponAction.class);
	private LvCoupon lvCoupon = new LvCoupon();
	private BufferedInputStream excelStream;
	private String progressName;//进度条名称

	private String couponCode;



	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvCoupon", lvCoupon);
		
		page = (Pagination)this.doService("LvCouponService", "findPage", dto);
		
		
		dto.put("couponTypeCode", lvCoupon.getCouponTypeCode());
		LvCouponType couponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
		this.getRequest().setAttribute("couponType", couponType);
		
		dto.put("givenProductCode", lvCoupon.getCouponTypeCode());
		List<LvActivity> listOrder=(List<LvActivity>) this.doService("LvActivityService", "findAllActivityLimitOrder", dto);
		List<LvActivity> listLink=(List<LvActivity>) this.doService("LvActivityService", "findAllActivityLink", dto);
		List<LvActivity> listReg=(List<LvActivity>) this.doService("LvActivityService", "findAllActivityRegister", dto);
		List<LvActivity> listShare=(List<LvActivity>) this.doService("LvActivityService", "findAllActivityShare", dto);
		List<LvActivity> listLottery=(List<LvActivity>) this.doService("LvActivityService", "findAllActivityLottery", dto);
		List<LvActivity> listPointProduct=(List<LvActivity>) this.doService("LvActivityService", "findAllActivityPointProduct", dto);
		
		

		
	    //将查询符合条件的活动的数据进行组装
		List<LvActivity> activityList=new ArrayList<LvActivity>();
		initActivityList(listOrder, activityList);
		initActivityList(listLink, activityList);
		initActivityList(listReg, activityList);
		initActivityList(listShare, activityList);
		initActivityList(listLottery, activityList);
		initActivityList(listPointProduct, activityList);
		this.getRequest().setAttribute("activityList", activityList);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvCouponAction.list() method end*****");
		}
		return LIST;
	}



	/**
	 * 
	 * @Method: initActivityList 
	 * @Description:  [组装活动数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 上午11:44:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 上午11:44:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param listOrder
	 * @param activityList 
	 * @return void
	 */
	private void initActivityList(List<LvActivity> listOrder,List<LvActivity> activityList) {
		if(ObjectUtils.isNotEmpty(listOrder)){
			for (LvActivity lvActivity : listOrder) {
				if(ObjectUtils.isNotEmpty(lvActivity)){
					activityList.add(lvActivity);
				}
			}
		}
	}

	/**
	 * 
	 * @Method: befExportCoupon 
	 * @Description:  [跳转到导出信息页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-23 下午4:17:02]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-23 下午4:17:02]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String befExportCoupon()throws ActionException{
		
		dto.put("givenProductCode", lvCoupon.getCouponTypeCode());
		List<LvActivity> listOrder=(List<LvActivity>) this.doService("LvActivityService", "findAllActivityLimitOrder", dto);
		List<LvActivity> listLink=(List<LvActivity>) this.doService("LvActivityService", "findAllActivityLink", dto);
		this.getRequest().setAttribute("listOrder", listOrder);
		this.getRequest().setAttribute("listLink", listLink);
		
		return "befExportCoupon";
	}
	
	
	/**
	 * 
	 * @Method: exportCouponTypeList 
	 * @Description:  [导出优惠码信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-20 下午2:26:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-20 下午2:26:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException
	 * @throws FileNotFoundException 
	 * @return String
	 */
	public String exportCouponList()throws ActionException, FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportOrder() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		List<String[]> reportList=new ArrayList();
		dto.put("lvCoupon", lvCoupon);
		//根据优惠券code查询优惠券详情
		dto.put("couponTypeCode", lvCoupon.getCouponTypeCode());
		LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
		
		//根据优惠券code查询优惠码列表信息
		List<LvCoupon> list= (List<LvCoupon>) this.doService("LvCouponService", "findAll", dto);
		if(ObjectUtils.isEmpty(list)){
			ProgressBarUtils.iniProgressBar(ProgressBarConstants.PROGRESS_FLAT_RUN_ERROR, progressName, 0, this.getSession());
		}
		//初始化进度条
		ProgressBarUtils.iniProgressBar(ProgressBarConstants.PROGRESS_FLAT_RUN, progressName, list.size(), this.getSession());
		
		//填充优惠券表格信息
		reportList= initPrintCouponType(reportList,lvCouponType,list.size(),users.getUserName());
		//描绘进度条
		String[] reportTitle = new String[1];
		reportTitle[0]="优惠码";
		reportList.add(reportTitle);
		for (int i = 0; i < list.size(); i++) {
			LvCoupon coupon=list.get(i);
			String[] reportArr = new String[1];
			reportArr[0]=coupon.getCouponCode();
			reportList.add(reportArr);
			//描绘进度条
			ProgressBarUtils.drawProgressBar(0, progressName, this.getSession());
		}
		String reportListFile =lvCouponType.getName()+"_"+DateUtils.formatDate(new Date(),"yyyyMMdd")+".xls";
		//生成文件路径
		String servletName=request.getServerName();
		int servetPort=request.getServerPort();
		String path = request.getRealPath("/report") + "/" + reportListFile;
		String resPath="http://"+servletName+":"+servetPort+"/report/"+reportListFile;
		if(reportList!=null&&reportList.size()>0){
			POIExcelUtil.createExcelForXLS(path, 0, reportList);
		}
		//终止进度条
	    ProgressBarUtils.stopProgressBar(progressName,resPath,this.getSession());
	    
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportOrder() method end*****");
		}
		return null;
	}







	private List initPrintCouponType(List<String[]> list,LvCouponType lvCouponType,Integer number,String userName) {
		String[] title = new String[2];
		title[0] = "优惠券名称";
		title[1] = lvCouponType.getName();
		list.add(title);
		
		//拼装优惠券使用规则
		StringBuffer applyString=new StringBuffer();
		applyString.append("限品类:");
		dto.put("couponTypeCode", lvCouponType.getCode());
		if(lvCouponType.getRelationType()==1){//当使用种类是商品类型
		   
		   page=(Pagination) this.doService("LvProductTypeService", "findListByCouponType", dto);
		   for (int i = 0; i < page.getList().size(); i++) {
			   Map map= (Map) page.getList().get(i);
			   applyString.append(map.get("typeName"));
			   applyString.append("(");
			   applyString.append(map.get("name"));
			   applyString.append(")\n");
		   }
	    	
		}else{//当使用种类是商品	
			page=(Pagination) this.doService("LvProductService", "findListByCouponType", dto);
			for (int i = 0; i < page.getList().size(); i++) {
			   Map map= (Map) page.getList().get(i);
			   applyString.append(map.get("productName"));
			   applyString.append("(");
			   applyString.append(map.get("name"));
			   applyString.append(")\n");
			}
		}

		applyString.append("商品总金额滿"+lvCouponType.getLimitAmount());
		
		String[] title2= new String[2];
		title2[0] = "使用条件";
		title2[1] = applyString.toString();
		list.add(title2);
		 
		
		String[] title3= new String[2];
		title3[0] = "优惠券金额";
		title3[1] = String.valueOf(lvCouponType.getAmount()) ;
		list.add(title3);
		
		String[] title4= new String[2];
		title4[0] = "有效时间";
		title4[1] = DateUtils.formatDate(lvCouponType.getStartTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(lvCouponType.getEndTime(), "yyyy-MM-dd");
		list.add(title4);
		
		String[] title5= new String[2];
		title5[0] = "本次下载数量";
		title5[1] = String.valueOf(number);
		list.add(title5);
		
		String[] title6= new String[2];
		title6[0] = "本次下载时间";
		title6[1] = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		list.add(title6);
		
		String[] title7= new String[2];
		title7[0] = "本次下载人";
		title7[1] = userName;
		list.add(title7);
		return list;
	}

	public String listForApply()throws ActionException{
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("couponCode", couponCode);
		
		page = (Pagination)this.doService("LvCouponService", "findPageForApply", dto);
		return "listForApply";
	}
	
	public String listForObtain()throws ActionException{
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("couponCode", couponCode);
		
		page = (Pagination)this.doService("LvCouponService", "findPageForObtain", dto);
		return "listForObtain";
	}
	
	public LvCoupon getLvCoupon() {
		return lvCoupon;
	}

	public void setLvCoupon(LvCoupon lvCoupon) {
		this.lvCoupon = lvCoupon;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getProgressName() {
		return progressName;
	}

	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}
	
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
}
