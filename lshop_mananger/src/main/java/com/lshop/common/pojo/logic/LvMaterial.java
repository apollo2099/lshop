package com.lshop.common.pojo.logic;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

public class LvMaterial extends BasePo implements java.io.Serializable 
{
    private Integer id;
    private String materialName;
    private String smallPath;
    private String bigPath;
    private String compressPath;
    private Integer status;
	
	public LvMaterial(){}
	
	
	public LvMaterial(Integer id, String materialName, String smallPath,
			String bigPath, String compressPath,Integer status) {
		super();
		this.id = id;
		this.materialName = materialName;
		this.smallPath = smallPath;
		this.bigPath = bigPath;
		this.compressPath = compressPath;
		this.status=status;
	}
  
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMaterialName() {
		return materialName;
	}


	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}


	public String getSmallPath() {
		return smallPath;
	}


	public void setSmallPath(String smallPath) {
		this.smallPath = smallPath;
	}


	public String getBigPath() {
		return bigPath;
	}


	public void setBigPath(String bigPath) {
		this.bigPath = bigPath;
	}


	public String getCompressPath() {
		return compressPath;
	}


	public void setCompressPath(String compressPath) {
		this.compressPath = compressPath;
	}
    
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
}
