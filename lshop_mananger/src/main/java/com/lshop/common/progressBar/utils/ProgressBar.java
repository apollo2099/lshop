package com.lshop.common.progressBar.utils;

/**
 * 功能描述：进度条类
 * @return
 * @throws 
 */
public class ProgressBar {
	private String name;
	private long importCnt = 0;
	private long totalCnt = 0;
	private long currentCnt = 0;
	private long failCnt = 0;
	private long repeatCnt = 0;
	private int flag = 0;
	private String msg;
	
	public ProgressBar() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getImportCnt() {
		return importCnt;
	}
	public void setImportCnt(long importCnt) {
		this.importCnt = importCnt;
	}
	public long getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(long totalCnt) {
		this.totalCnt = totalCnt;
	}
	public long getCurrentCnt() {
		return currentCnt;
	}
	public void setCurrentCnt(long currentCnt) {
		this.currentCnt = currentCnt;
	}
	public long getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(long failCnt) {
		this.failCnt = failCnt;
	}
	public long getRepeatCnt() {
		return repeatCnt;
	}
	public void setRepeatCnt(long repeatCnt) {
		this.repeatCnt = repeatCnt;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
