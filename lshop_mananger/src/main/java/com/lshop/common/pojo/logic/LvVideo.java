package com.lshop.common.pojo.logic;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

public class LvVideo extends BasePo implements java.io.Serializable
{
    private Integer id;
    private String videoAdd;
    private String flashAdd;
    private String htmlAdd;
    private String videoName;
    private String videoDesc;
	
    public LvVideo() {
		// TODO Auto-generated constructor stub
	}
    
	
    public LvVideo(String videoAdd, String flashAdd, String htmlAdd,
			String videoName, String videoDesc) {
		super();
		this.videoAdd = videoAdd;
		this.flashAdd = flashAdd;
		this.htmlAdd = htmlAdd;
		this.videoName = videoName;
		this.videoDesc = videoDesc;
	}

    

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getVideoAdd() {
		return videoAdd;
	}


	public void setVideoAdd(String videoAdd) {
		this.videoAdd = videoAdd;
	}


	public String getFlashAdd() {
		return flashAdd;
	}


	public void setFlashAdd(String flashAdd) {
		this.flashAdd = flashAdd;
	}


	public String getHtmlAdd() {
		return htmlAdd;
	}


	public void setHtmlAdd(String htmlAdd) {
		this.htmlAdd = htmlAdd;
	}


	public String getVideoName() {
		return videoName;
	}


	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}


	public String getVideoDesc() {
		return videoDesc;
	}


	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}


	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
}
