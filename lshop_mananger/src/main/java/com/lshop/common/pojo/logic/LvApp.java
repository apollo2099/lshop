package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvApp entity. @author MyEclipse Persistence Tools
 */

public class LvApp extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 3106154132906882915L;
	private Integer id;
     private String name;
     private String version;
     private String applyModel;
     private String applyVersion;
     private String language;
     private String thirdParty;
     private String appSize;
     private Integer grade;
     private String download;
     private String introduce;
     private String appImage;
	 private String typeCode;
	 private Integer sortId;
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

     private String oldName;
    // Constructors

   


	/** default constructor */
    public LvApp() {
    }

    
    /** full constructor */
    public LvApp(String name, String version, String applyModel, String applyVersion, String language,
    		     String thirdParty, Integer grade, String download, String introduce, String typeCode,String appImage,Integer sortId,String storeId,
    		     String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName,
    		     String atrr, String atrr1, String atrr2, String atrr3) {
        this.name = name;
        this.version = version;
        this.applyModel = applyModel;
        this.applyVersion = applyVersion;
        this.language = language;
        this.thirdParty = thirdParty;
        this.grade = grade;
        this.download = download;
        this.introduce = introduce;
        this.typeCode=typeCode;
        this.appImage=appImage;
        this.sortId=sortId;
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
    public String getTypeCode() {
		return typeCode;
	}


	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
    public String getAppImage() {
		return appImage;
	}


	public void setAppImage(String appImage) {
		this.appImage = appImage;
	}


	public Integer getSortId() {
		return sortId;
	}


	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}



    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }

    public String getApplyModel() {
        return this.applyModel;
    }
    
    public void setApplyModel(String applyModel) {
        this.applyModel = applyModel;
    }

    public String getApplyVersion() {
        return this.applyVersion;
    }
    
    public void setApplyVersion(String applyVersion) {
        this.applyVersion = applyVersion;
    }

    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getThirdParty() {
        return this.thirdParty;
    }
    
    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    public Integer getGrade() {
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getDownload() {
        return this.download;
    }
    
    public void setDownload(String download) {
        this.download = download;
    }

    public String getIntroduce() {
        return this.introduce;
    }
    
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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


	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getAppSize() {
		return appSize;
	}


	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
   
	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}







}