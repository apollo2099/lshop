package com.lshop.manage.blog.service.impl;
import java.text.ParseException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvBlogSubscribe;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.manage.blog.action.EmailQuartzJob;
import com.lshop.manage.blog.service.BlogEmailSubscribeService;


@Service("BlogEmailSubscribeService")
@Lazy(false)
public class BlogEmailSubscribeServiceImpl implements BlogEmailSubscribeService{

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	private Logger logger = Logger.getLogger(this.getClass());
	private LvBlogSubscribe lvBlogSubscribe;
	private LvEmailTpl lvEmailTpl;

	SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	// 浠庡伐鍘傚垱寤簊cheduler瀵硅薄
	Scheduler scheduler=null;
	@Override
	public void run() {
        try {
			// 鍒涘缓Schedule Factory
        	scheduler = schedulerFactory.getScheduler();
			JobDetail jobDetail = new JobDetail("jobDetail2", "jobDetailGroup2", EmailQuartzJob.class);
			jobDetail.getJobDataMap().put("lvlogicReadDao",lvlogicReadDao);
			jobDetail.getJobDataMap().put("lvlogicWriteDao",lvlogicWriteDao);
			// Initiate CronTrigger with its name and group name
			CronTrigger cronTrigger = new CronTrigger("cronTrigger", "triggerGroup2");

			/**
			 * 閭欢璁㈤槄浠诲姟瀛楀吀璇存槑锛�
			 * 鍙戦�鍛ㄦ湡瀛楀吀瀹氫箟
			 * 0 姘镐笉鍙戦�銆�
			 * 1 姣忓ぉ銆�
			 * 2 姣忓懆涓��
			 * 3 姣忓懆涓夈�
			 * 4 姣忎釜鏈堬紙榛樿涓烘瘡鏈�鏃ュ彂閫侊級
			 */
			lvBlogSubscribe=this.getSubscribe();
			if(lvBlogSubscribe!=null){
			int sendCycle=lvBlogSubscribe.getSendCycle();
			String sendTime=lvBlogSubscribe.getSendTime().toString();
			String hour=null;
			String minute=null;
			String second=null;
			if(sendTime==null){
				hour="0";
				minute="0";
				second="0";
			}else{
				String [] times=sendTime.split(":");
				hour=times[0];
				minute=times[1];
				second=times[2];
				if(hour.substring(0, 1).equals("0")){
				   hour=hour.substring(1,2);
				}
				if(minute.substring(0, 1).equals("0")){
				   minute=minute.substring(1,2);
				}
				if(second.substring(0, 1).equals("0")){
				   second=second.substring(1,2);
				}
			}
			
			//鏍规嵁閰嶇疆鐨勫彂閫佸懆鏈熷拰鍙戦�鏃堕棿缁勮鎴愬畾鏃朵换鍔＄殑琛ㄨ揪寮�
			CronExpression cexp=null;
			if(sendCycle==0){
				cexp = new CronExpression(second+" "+minute+" "+hour+" * * 99999");
			}else if(sendCycle==1){
				cexp = new CronExpression(second+" "+minute+" "+hour+" * * ?");
			}else if(sendCycle==2){
				//cexp = new CronExpression("0 25 11 ? * WED"); TUES
				cexp = new CronExpression(second+" "+minute+" "+hour+" ? * WED");
			}else if(sendCycle==3){
				cexp = new CronExpression(second+" "+minute+" "+hour+" ? * MON");
			}else if(sendCycle==4){
				cexp = new CronExpression(second+" "+minute+" "+hour+" 1 * ?");
			}
			
			//cexp = new CronExpression("0/5 * * * * ?");
			cronTrigger.setCronExpression(cexp);
			scheduler.scheduleJob(jobDetail, cronTrigger);
			scheduler.start();
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


	@PostConstruct
	public void init(){
		new Thread(this).start();	
	}
	

	@Override
	public void init2(Dto dto) {
		if(scheduler!=null){
			try {
				scheduler.shutdown();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		new Thread(this).start();	
	}
	
	public HibernateBaseDAO getLvlogicReadDao() {
		return lvlogicReadDao;
	}

	public void setLvlogicReadDao(HibernateBaseDAO lvlogicReadDao) {
		this.lvlogicReadDao = lvlogicReadDao;
	}

	public HibernateBaseDAO getLvlogicWriteDao() {
		return lvlogicWriteDao;
	}

	public void setLvlogicWriteDao(HibernateBaseDAO lvlogicWriteDao) {
		this.lvlogicWriteDao = lvlogicWriteDao;
	}



	/**
	 * 鏌ヨ閰嶇疆鐨勯偖浠惰闃呴厤缃俊鎭�
	 */
	public LvBlogSubscribe getSubscribe()throws ServiceException {
		String hql="from LvBlogSubscribe";
		List list=this.lvlogicReadDao.find(Finder.create(hql),1, 1).getList();
		if(list!=null&&list.size()>0){
			lvBlogSubscribe=(LvBlogSubscribe) list.get(0);
		}
		return lvBlogSubscribe;
	}



	

	

}
