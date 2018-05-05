package com.lshop.common.progressBar.utils;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 功能描述：进度条工具类
 * @return
 * @throws 
 */
public class ProgressBarUtils{
	
	private static final Log logger	= LogFactory.getLog(ProgressBarUtils.class);
	
	/**
	 * 功能描述：初始化进度条
	 * @param flat 进度条状态 0=停止，1=运行中，2=成功结束，3=失败结束，4=错误
	 * @param progressName 进度条名称
	 * @param totalCnt 导入总条数
	 * @param session 会话
	 */
	public static void iniProgressBar(Integer flat,String progressName,Integer totalCnt,HttpSession session){
		if (logger.isInfoEnabled()){
			logger.info("***** ProgressBarUtils.iniProgressBar() method begin*****");
		}
		session.removeAttribute(progressName);
		ProgressBar progressBar = new ProgressBar();
		progressBar.setName(progressName);
		progressBar.setTotalCnt(totalCnt);
		progressBar.setFlag(flat);
		session.setAttribute(progressBar.getName(), progressBar);
		if (logger.isInfoEnabled()){
			logger.info("***** ProgressBarUtils.iniProgressBar() method end*****");
		}
	}
	/**
	 * 功能描述：初始化进度条
	 * @param progressBar 进度条对象
	 * @param session 会话
	 */
	public static void iniProgressBar(ProgressBar progressBar,HttpSession session){
		if (logger.isInfoEnabled()){
			logger.info("***** ProgressBarUtils.iniProgressBar() method begin*****");
		}
		session.removeAttribute(progressBar.getName());
		session.setAttribute(progressBar.getName(), progressBar);
		if (logger.isInfoEnabled()){
			logger.info("***** ProgressBarUtils.iniProgressBar() method end*****");
		}
	}
	/**
	 * 功能描述：进度条显示
	 * @param result ：1表示错误；2表示重复；其他表示成功
	 * @param currentCnt 当前条数
	 * @param session 	
	 * @return
	 * @throws 
	 */
	public static void drawProgressBar(Integer result,String progressName,HttpSession session){
		if (logger.isInfoEnabled()){
			logger.info("***** ProgressBarUtils.drawProgressBar() method begin*****");
		}
		ProgressBar  progressBar = (ProgressBar) session.getAttribute(progressName);
		progressBar.setCurrentCnt(progressBar.getCurrentCnt()+1);
		if(result == ProgressBarConstants.PROGRESS_RESULT_FAILURE){
			progressBar.setFailCnt(progressBar.getFailCnt() + 1);
		}else if(result == ProgressBarConstants.PROGRESS_RESULT_REPEAT){
			progressBar.setRepeatCnt(progressBar.getRepeatCnt() + 1);
		}else {
			progressBar.setImportCnt(progressBar.getImportCnt() + 1);
		}
		session.setAttribute(progressName, progressBar);
		if (logger.isInfoEnabled()){
			logger.info("***** ProgressBarUtils.drawProgressBar() method end*****");
		}
	}
	/**
	 * 功能描述：停止进度条
	 * @param progressName 进度条名称
	 * @param Msg 设置进度条文本信息 
	 * @param session 会话
	 */
	public static void stopProgressBar(String progressName, String Msg,HttpSession session) {
		if (logger.isInfoEnabled()){
			logger.info("***** ProgressBarUtils.drawProgressBar() method begin*****");
		}
		ProgressBar  progressBar = (ProgressBar) session.getAttribute(progressName);
		progressBar.setMsg(Msg);
		if(progressBar.getFailCnt()>0||progressBar.getRepeatCnt()>0){
			progressBar.setFlag(ProgressBarConstants.PROGRESS_FLAT_FINISH_ERROR);
		}else{
			progressBar.setFlag(ProgressBarConstants.PROGRESS_FLAT_FINISH_SUCCESS);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProgressBarUtils.drawProgressBar() method end*****");
		}
	}
	
}
