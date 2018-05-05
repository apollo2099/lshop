package com.lshop.excenter.action;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvPromtContent;
import com.lshop.common.util.CookieUtil;
import com.lshop.excenter.ExcenterConstant;

@SuppressWarnings({"serial","unchecked"})
@Controller("PromoterManageAction")
@Scope("prototype")
public class PromoterManageAction extends BaseAction
{
	private String couponcode;
	
	public String getCouponcode() {
		return couponcode;
	}
	
	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	
	private String getUid() throws Exception{
		return  CookieUtil.getValue(this.getRequest(), ExcenterConstant.EXUSER_ID, true,getText("domain.base.name"));
	}
	
	public String promtOrderList() throws Exception //订单详细
	{ 
		
		dto.put("uid",this.getUid());
		dto.put("page", page);
		Pagination pagination=(Pagination)super.doService("PromoterManageService", "getOrderDetail", dto);
		
		this.getRequest().setAttribute("pagination", pagination);
		this.getRequest().setAttribute("objList",pagination.getList());
		this.getRequest().setAttribute("couponCode", dto.getAsString("couponCode"));
		return "thePromtDetailOrder";//return "detailList";
	}
	
	public String promtCodeList() throws Exception    //我的推广码
	{
		dto.put("uid",this.getUid());
		dto.put("page", page);
		super.doService("PromoterManageService", "getCodeList", dto);
		Object[] obj=new Object[6];
		obj=(Object[])dto.get("obj");
		
		if(dto.get("settlementStatus")!=null)
		{
			short settlementStatus=Short.parseShort(dto.get("settlementStatus").toString());
		    this.getRequest().setAttribute("settlementStatus", settlementStatus); //存放申请状态，如果已提交申请，则将按钮屏蔽掉
		}
		if(dto.get("settlementedAmount")!=null)
		{
			double settlementedAmount=(Double)dto.get("settlementedAmount");
		    this.getRequest().setAttribute("settlementedAmount", settlementedAmount);
		}
		if(dto.get("nonSettlementAmount")!=null)
		{
			double nonSettlementAmount=(Double)dto.get("nonSettlementAmount");
			this.getRequest().setAttribute("nonSettlementAmount", nonSettlementAmount);
		}
		if(dto.get("settlementAmount")!=null)
		{
			double settlementAmount=(Double)dto.get("settlementAmount");
			this.getRequest().setAttribute("settlementAmount", settlementAmount);
		}
		
		 this.getRequest().setAttribute("mark", dto.getAsInteger("mark"));
	     
	     if(obj!=null)
	     {
	    	this.getRequest().setAttribute("obj",obj);
	     }
	     if(obj[3]!=null)
	     {
	    	  this.getRequest().setAttribute("num", obj[3]);
	     }
	     else
	     {
	    	 this.getRequest().setAttribute("num", 0);
		 }
	    String couponCode=dto.getAsString("couponCode");
	    this.getRequest().setAttribute("couponCode", couponCode);
		return "codeList";
	}
	
	public String befPayRequest() throws Exception //提交结算申请
	{
		dto.put("uid",this.getUid());//
	    dto=(Dto)super.doService("PromoterManageService", "befPay", dto);
	    int presentNum=dto.getAsInteger("presentNum");
	    int rateNum=dto.getAsInteger("rateNum");
	    int presentRate=dto.getAsInteger("presentRate");
	    int totalNum=dto.getAsInteger("totalNum");
	 
	    Integer userType=dto.getAsInteger("userType");
	    this.getRequest().setAttribute("userType", userType);
	    if(userType!=null&&userType==1)
	    {
	    	this.getRequest().setAttribute("specialAmount", dto.getAsString("specialAmount"));
	    	LvCoupon coupon=(LvCoupon)dto.get("coupon");
	    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	    	 this.getRequest().setAttribute("createTime",format.format(coupon.getCreateTime()));
	    	 this.getRequest().setAttribute("validitydate",format.format(coupon.getValidityDate()));
	    }
	    this.getRequest().setAttribute("presentNum", presentNum);
	    this.getRequest().setAttribute("rateNum", rateNum);
	    this.getRequest().setAttribute("presentRate", presentRate);
	    this.getRequest().setAttribute("totalNum", totalNum);
	
		return "befPayRequest";
	}
    
	public String payRequest() throws Exception //确认，提交结算申请
	{
		dto.put("id",this.getUid()); 
		super.doService("PromoterManageService", "payRequest", dto);
		return "payRequest";
	}
	
	public String getSettledList() throws Exception //结算查询
	{
		dto.put("uid",this.getUid());
		dto.put("page",page);
		Pagination pagination=(Pagination)super.doService("PromoterManageService", "getSettledList", dto);
		this.getRequest().setAttribute("size", pagination.getList().size());
		this.getRequest().setAttribute("pagination", pagination);
		this.getRequest().setAttribute("objList",pagination.getList());
		
		this.getRequest().setAttribute("couponCode", dto.getAsString("couponCode"));
		return "settleLog";
	}
	
	public String getDetailList() throws Exception //结算明细        根据aid查询单个明细
	{
		dto.put("uid",this.getUid());
		dto.put("balanceId", this.getRequest().getParameter("balanceId"));
		dto.put("page",page);
		Pagination pagination=(Pagination)super.doService("PromoterManageService", "getDetailList", dto);
		List<Object[]> objList=(List<Object[]>)pagination.getList();
		if(objList!=null&&objList.size()>0)
		{
			for(int i=0;i<objList.size();i++)
			{
				Object[] obj=objList.get(i);
				obj[1]=this.getRequest().getParameter("couponCode");
				if(obj[2]==null||obj[2].equals(""))
					obj[2]="null";
				else
					obj[2]=obj[2].toString().substring(0, obj[2].toString().length()-2);
			}
		}
		this.getRequest().setAttribute("objList",objList);
		
		this.getRequest().setAttribute("pagination",pagination);
		this.getRequest().setAttribute("couponCode", this.getRequest().getParameter("couponCode"));
		this.getRequest().setAttribute("balanceId", this.getRequest().getParameter("balanceId"));
		return "theSettledDetailOrder";  //return "detailList";
	}
	
	public String getSettleOrderList() throws Exception //可申请结算
	{
		dto.put("uid",this.getUid());
		dto.put("page", page);
		Pagination pagination=(Pagination)super.doService("PromoterManageService","getSettleOrderList", dto);
		
		this.getRequest().setAttribute("pagination", pagination);
		this.getRequest().setAttribute("objList",pagination.getList());
		this.getRequest().setAttribute("couponCode", dto.getAsString("couponCode"));
		return "theSettleOrder";   //return "detailList";
	}
	
	public String getNsettleOrderList() throws Exception//未满足15天
	{
		dto.put("uid",this.getUid());
		dto.put("page", page);
		Pagination pagination=(Pagination)super.doService("PromoterManageService","getNsettleOrderList", dto);

		this.getRequest().setAttribute("pagination", pagination);
		this.getRequest().setAttribute("objList",pagination.getList());
		this.getRequest().setAttribute("couponCode", dto.getAsString("couponCode"));
		return "theNsettleOrder";  
	}

	/**
	public String getFundRelate() throws Exception
	{
		dto.put("uid",this.getUid());
		dto.put("page", page);
		Pagination pagination=(Pagination)super.doService("PromoterManageService", "getFundRelate", dto);
	    List<Object[]> objList=(List<Object[]>)pagination.getList();
	      SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		  for(int i=0;i<objList.size();i++)
		  {
			  Object[] obj=objList.get(i);
			  obj[0]=dateFormat.format(obj[0]);
		  }
	    this.getRequest().setAttribute("objList", objList);
		String couponCode=dto.getAsString("couponCode");
		Integer num=dto.getAsInteger("num");
		String enablefund=dto.getAsString("enablefund");
		this.getRequest().setAttribute("couponCode",couponCode);
		this.getRequest().setAttribute("num",num);
		this.getRequest().setAttribute("enablefund",enablefund);
		this.getRequest().setAttribute("size", dto.getAsInteger("size"));
		this.getRequest().setAttribute("pagination", pagination);
		return "fundrelate";
	}*/
	
	public String getUserDetail() throws Exception
	{
	  List<Object[]> objList=(List<Object[]>)super.doService("PromoterManageService","getUserDetail", dto);	
	  this.getRequest().setAttribute("objList", objList);
	  return "userdetail";
	}
	
/**
	public String getToolDetail() throws Exception
	{
		dto.put("uid",this.getUid());
		
		super.doService("PromoterManageService", "getToolDetail", dto);
		List<LvPromtContent> promtContentModel1=(List<LvPromtContent>)dto.get("promtContentModel1");
		if(promtContentModel1!=null&&promtContentModel1.size()>0)
		{
			LvPromtContent promtContent=promtContentModel1.get(0);
			this.getRequest().setAttribute("promtContent1", promtContent);
		}
		List<LvPromtContent> promtContentModel2=(List<LvPromtContent>)dto.get("promtContentModel2");
		if(promtContentModel2!=null&&promtContentModel2.size()>0)
		{  
			LvPromtContent promtContent=promtContentModel2.get(0);
			this.getRequest().setAttribute("promtContent2",promtContent);
		}
		List<LvMaterial> materialList=(List<LvMaterial>)dto.get("materialList");
		List<LvVideo> videoList=(List<LvVideo>)dto.get("videoList");
		this.getRequest().setAttribute("videoList", videoList);
		this.getRequest().setAttribute("materialList", materialList);
		this.getRequest().setAttribute("promtContentModel1", promtContentModel1);
		this.getRequest().setAttribute("promtContentModel2", promtContentModel2);
		this.getRequest().setAttribute("couponCode", dto.getAsString("couponCode"));
		this.getRequest().setAttribute("url", dto.getAsString("url"));
		//this.getRequest().setAttribute("htmlContent",dto.getAsString("htmlContent"));
		return "tooldetail";
	}
 */		
	public String getContent() throws Exception
	{
		dto.put("id", this.getRequest().getParameter("id"));
		super.doService("PromoterManageService", "getContent", dto);
		LvPromtContent promtContent=(LvPromtContent)dto.get("promtContent");
		this.getRequest().setAttribute("promtContent", promtContent);
		return "content";
	}
	
	public String getHtmlContent() throws Exception
	{
	  dto.put("id", this.getRequest().getParameter("id"));	
	  super.doService("PromoterManageService", "getHtmlContent", dto);
	  LvPromtContent promtContent=(LvPromtContent)dto.get("promtContent");
	  this.getRequest().setAttribute("promtContent", promtContent);
	  return "htmlcontent";
	}
	
	public String getPromtPage() throws Exception
	{
//		dto.put("uid",this.getUid());
//		super.doService("PromoterManageService", "getPromtPage", dto);
//		String couponCode=dto.getAsString("couponCode");
		this.getRequest().setAttribute("couponCode", couponcode);
		
		return "promtpage";
	}
}
