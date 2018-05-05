package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.gv.core.datastructure.Key;

/**
 * LvActivity entity. @author MyEclipse Persistence Tools
 */

public class LvActivity extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String activityTitle;
	private String activityTitleEn;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private Integer status;
	private String remark;
	private Integer typeKey;
	private String code;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors
	private Integer oldStatus;
	private String oldActivityTitle;
	private List<LvActivityProduct> acProcuctList;//活动商品数据集合




	/** default constructor */
	public LvActivity() {
	}

	/** full constructor */
	public LvActivity(String activityTitle, Timestamp startTime,
			Timestamp endTime, Integer status, String remark, Integer typeKey,
			String code, Timestamp createTime, Timestamp modifyTime,
			Integer modifyUserId, String modifyUserName) {
		this.activityTitle = activityTitle;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.remark = remark;
		this.typeKey = typeKey;
		this.code = code;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityTitle() {
		return this.activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}



	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTypeKey() {
		return this.typeKey;
	}

	public void setTypeKey(Integer typeKey) {
		this.typeKey = typeKey;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
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

	public Integer getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(Integer oldStatus) {
		this.oldStatus = oldStatus;
	}
	
	public String getOldActivityTitle() {
		return oldActivityTitle;
	}

	public void setOldActivityTitle(String oldActivityTitle) {
		this.oldActivityTitle = oldActivityTitle;
	}
	
	
	
	public String getActivityTitleEn() {
		return activityTitleEn;
	}

	public void setActivityTitleEn(String activityTitleEn) {
		this.activityTitleEn = activityTitleEn;
	}

	
	public List<LvActivityProduct> getAcProcuctList() {
		return acProcuctList;
	}

	public void setAcProcuctList(List<LvActivityProduct> acProcuctList) {
		this.acProcuctList = acProcuctList;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}