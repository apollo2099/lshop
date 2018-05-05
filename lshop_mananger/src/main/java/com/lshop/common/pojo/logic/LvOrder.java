package com.lshop.common.pojo.logic;

import java.util.Date;
import java.util.List;

import com.gv.core.datastructure.Key;


/**
 * LvOrder entity. @author MyEclipse Persistence Tools
 */

public class LvOrder extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	 private static final long serialVersionUID = 2691007949133353506L;
	 private Integer id;
     private String oid;
     private Integer couponNum=0;
     private Short payStatus;
     private Short status;
     private Integer memid;
     private String userEmail;
     private Short paymethod;
	 private String orderRemark;
     private String breakRemark;
     private String sendRemark;
     private Date overtime;
     private Float postprice=0f;
     private Float totalPrice=0f;
     private Float couponTotalPrice;
	 private String thirdPartyOrder;
	 private String thirdOrderNum;
	 private Short thirdOrderSource;
     private String expressCompany;
     private String expressName;
     private String expressNum;
     private Date shipTime;
	 private String customerNo;
     private String currency;
     private Short isdelete=0;
     private Short isServiceAudit;
     private String serviceAuditContent;
     private Integer isFinanceAudit;
     private String financeAuditContent;
     private Integer isGroup;
     private String groupCode;
     private java.lang.Integer flag;
     private Short isBalance=0;
     private Short isBalanceFirst=0;
     private Short isSyncSas;
     private Short isGift;
	 private String storeId;
     private String code;
     private Date createTime;
     private Date modifyTime;
     private Integer modifyUserId;
     private String modifyUserName;
     private String atrr;
     private String atrr1;
     private String atrr2;
     private String atrr3;

     //订单实体扩展属性
     private String startTime;//下单开始时间
	 private String endTime;  //下单结束时间
     private String startOverTime;//支付开始时间
     private String endOverTime;  //支付结束时间
     
     private Integer auditFlag;//审核标志
	 private Integer orderFlag;//已完成标志(菜单配置访问)
	 private Integer statusFlag;//发货状态和支付状态菜单权限访问标志(1表示可以访问，0标识禁用)
	 
	 private String detailsList;
	 private List<LvOrderDetails> list;
	 private String couponCodeList;
	 private List<LvOrderAddress> addressList;
	 
	 
    // Constructors


	/** default constructor */
    public LvOrder() {
    }

	/** minimal constructor */
    public LvOrder(String oid, Short payStatus, Short status, String userEmail, Short paymethod, Float postprice, Float totalPrice, Short isServiceAudit) {
        this.oid = oid;
        this.payStatus = payStatus;
        this.status = status;
        this.userEmail = userEmail;
        this.paymethod = paymethod;
        this.postprice = postprice;
        this.totalPrice = totalPrice;
        this.isServiceAudit = isServiceAudit;
    }
    
    /** full constructor */
    public LvOrder(String oid, Short payStatus, Integer couponNum,Short status, Integer memid, String userEmail, Short paymethod, 
    		String orderRemark, String breakRemark, Date overtime, Float postprice, Float totalPrice, 
    		String thirdPartyOrder, String expressCompany, String expressName, String expressNum, 
    		String sendRemark, String customerNo, String currency, Short isdelete, Short isServiceAudit, 
    		String serviceAuditContent, Integer isFinanceAudit, String financeAuditContent, Integer isGroup, 
    		String groupCode,Short isBalance,Short isBalanceFirst, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId,
    		String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.oid = oid;
        this.payStatus = payStatus;
        this.couponNum = couponNum;
        this.status = status;
        this.memid = memid;
        this.userEmail = userEmail;
        this.paymethod = paymethod;
        this.orderRemark = orderRemark;
        this.breakRemark = breakRemark;
        this.overtime = overtime;
        this.postprice = postprice;
        this.totalPrice = totalPrice;
        this.thirdPartyOrder = thirdPartyOrder;
        this.expressCompany = expressCompany;
        this.expressName = expressName;
        this.expressNum = expressNum;
        this.sendRemark = sendRemark;
        this.customerNo = customerNo;
        this.currency = currency;
        this.isdelete = isdelete;
        this.isServiceAudit = isServiceAudit;
        this.serviceAuditContent = serviceAuditContent;
        this.isFinanceAudit = isFinanceAudit;
        this.financeAuditContent = financeAuditContent;
        this.isGroup = isGroup;
        this.groupCode = groupCode;
        this.isBalance=isBalance;
        this.isBalanceFirst=isBalanceFirst;
        this.storeId = storeId;
        this.code = code;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.modifyUserId = modifyUserId;
        this.modifyUserName = modifyUserName;
        this.atrr = atrr;
        this.atrr1 = atrr1;
        this.atrr2 = atrr2;
        this.atrr3 = atrr3;
    }

   
    // Property accessors
	public List<LvOrderDetails> getList() {
		return list;
	}

	public void setList(List<LvOrderDetails> list) {
		this.list = list;
	}
	
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOid() {
        return this.oid;
    }
    
    public void setOid(String oid) {
        this.oid = oid;
    }

    public Short getPayStatus() {
        return this.payStatus;
    }
    
    public void setPayStatus(Short payStatus) {
        this.payStatus = payStatus;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getMemid() {
        return this.memid;
    }
    
    public void setMemid(Integer memid) {
        this.memid = memid;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Short getPaymethod() {
        return this.paymethod;
    }
    
    public void setPaymethod(Short paymethod) {
        this.paymethod = paymethod;
    }



    public Date getOvertime() {
        return this.overtime;
    }
    
    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public Float getPostprice() {
        return this.postprice;
    }
    
    public void setPostprice(Float postprice) {
        this.postprice = postprice;
    }

    public Float getTotalPrice() {
        return this.totalPrice;
    }
    
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getThirdPartyOrder() {
        return this.thirdPartyOrder;
    }
    
    public void setThirdPartyOrder(String thirdPartyOrder) {
        this.thirdPartyOrder = thirdPartyOrder;
    }

    public String getExpressCompany() {
        return this.expressCompany;
    }
    
    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressName() {
        return this.expressName;
    }
    
    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNum() {
        return this.expressNum;
    }
    
    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }
    
    public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}



    public String getCustomerNo() {
        return this.customerNo;
    }
    
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Short getIsdelete() {
        return this.isdelete;
    }
    
    public void setIsdelete(Short isdelete) {
        this.isdelete = isdelete;
    }

    public Short getIsServiceAudit() {
        return this.isServiceAudit;
    }
    
    public void setIsServiceAudit(Short isServiceAudit) {
        this.isServiceAudit = isServiceAudit;
    }

    public String getServiceAuditContent() {
        return this.serviceAuditContent;
    }
    
    public void setServiceAuditContent(String serviceAuditContent) {
        this.serviceAuditContent = serviceAuditContent;
    }

    public Integer getIsFinanceAudit() {
        return this.isFinanceAudit;
    }
    
    public void setIsFinanceAudit(Integer isFinanceAudit) {
        this.isFinanceAudit = isFinanceAudit;
    }

    public String getFinanceAuditContent() {
        return this.financeAuditContent;
    }
    
    public void setFinanceAuditContent(String financeAuditContent) {
        this.financeAuditContent = financeAuditContent;
    }

    public Integer getIsGroup() {
        return this.isGroup;
    }
    
    public void setIsGroup(Integer isGroup) {
        this.isGroup = isGroup;
    }

    public String getGroupCode() {
        return this.groupCode;
    }
    
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getStoreId() {
        return this.storeId;
    }
    
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyUserId() {
        return this.modifyUserId;
    }
    
    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyUserName() {
        return this.modifyUserName;
    }
    
    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }
    public Short getIsBalance() {
		return isBalance;
	}

	public void setIsBalance(Short isBalance) {
		this.isBalance = isBalance;
	}
	
	public Short getIsBalanceFirst() {
		return isBalanceFirst;
	}

	public void setIsBalanceFirst(Short isBalanceFirst) {
		this.isBalanceFirst = isBalanceFirst;
	}

    public String getAtrr() {
        return this.atrr;
    }
    
    public void setAtrr(String atrr) {
        this.atrr = atrr;
    }

    public String getAtrr1() {
        return this.atrr1;
    }
    
    public void setAtrr1(String atrr1) {
        this.atrr1 = atrr1;
    }

    public String getAtrr2() {
        return this.atrr2;
    }
    
    public void setAtrr2(String atrr2) {
        this.atrr2 = atrr2;
    }

    public String getAtrr3() {
        return this.atrr3;
    }
    
    public void setAtrr3(String atrr3) {
        this.atrr3 = atrr3;
    }
    public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

    public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getBreakRemark() {
		return breakRemark;
	}

	public void setBreakRemark(String breakRemark) {
		this.breakRemark = breakRemark;
	}

	public String getSendRemark() {
		return sendRemark;
	}

	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}
	 
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartOverTime() {
		return startOverTime;
	}

	public void setStartOverTime(String startOverTime) {
		this.startOverTime = startOverTime;
	}

	public String getEndOverTime() {
		return endOverTime;
	}

	public void setEndOverTime(String endOverTime) {
		this.endOverTime = endOverTime;
	}
	
    public Integer getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(Integer auditFlag) {
		this.auditFlag = auditFlag;
	}

	public Integer getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}
    public String getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(String detailsList) {
		this.detailsList = detailsList;
	}
	public String getCouponCodeList() {
		return couponCodeList;
	}

	public void setCouponCodeList(String couponCodeList) {
		this.couponCodeList = couponCodeList;
	}

	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}
	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public List<LvOrderAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<LvOrderAddress> addressList) {
		this.addressList = addressList;
	}
    public Float getCouponTotalPrice() {
		return couponTotalPrice;
	}

	public void setCouponTotalPrice(Float couponTotalPrice) {
		this.couponTotalPrice = couponTotalPrice;
	}
	
	
	public Short getIsSyncSas() {
		return isSyncSas;
	}

	public void setIsSyncSas(Short isSyncSas) {
		this.isSyncSas = isSyncSas;
	}

	
	public Short getIsGift() {
		return isGift;
	}

	public void setIsGift(Short isGift) {
		this.isGift = isGift;
	}

	
	public String getThirdOrderNum() {
		return thirdOrderNum;
	}

	public void setThirdOrderNum(String thirdOrderNum) {
		this.thirdOrderNum = thirdOrderNum;
	}

	public Short getThirdOrderSource() {
		return thirdOrderSource;
	}

	public void setThirdOrderSource(Short thirdOrderSource) {
		this.thirdOrderSource = thirdOrderSource;
	}

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
}