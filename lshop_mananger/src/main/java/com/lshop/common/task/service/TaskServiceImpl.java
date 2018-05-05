package com.lshop.common.task.service;

import java.text.ParseException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvTaskQuartz;
import com.lshop.common.task.TaskService;
import com.lshop.common.task.job.MyJob;
import com.lshop.common.task.job.MyTask;

@Lazy(false)
@Service("TaskService")
public class TaskServiceImpl implements TaskService {

	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private Scheduler scheduler;

	private String job = "job_";
	private String trigger = "cron_";

	@Override
	@PostConstruct
	public void initTask() throws ServiceException, ParseException {//web容器启动的时候加载任务
		// 获取任务列表的HQL语句
		List<LvTaskQuartz> listTask = dao.find("from LvTaskQuartz", null);
		if (listTask != null && listTask.size() > 0) {
			for (int i = 0; i < listTask.size(); i++) {
				LvTaskQuartz quartz = listTask.get(i);
				Dto dto = new BaseDto();
				dto.put("lvTaskQuartz", quartz);
				this.addJob(dto);
				if(quartz.getStatus()==0){
					this.pauseJob(dto);
				}
			}
		}
	}

	/**
	 * 增加任务
	 * 
	 * @param quartz
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	@Override
	public void addJob(Dto dto) {
		LvTaskQuartz ttaskQuartz = (LvTaskQuartz) dto.get("lvTaskQuartz");
		String jobName = this.job + ttaskQuartz.getId();
		String triggerName = this.trigger + ttaskQuartz.getId();
		try {

			JobDetail jobDetail = new JobDetail();
			jobDetail.setName(jobName);
			MyTask myTask = new MyTask();
			myTask.setTargetObject(ttaskQuartz.getTargetObject());
			myTask.setTargetMethod(ttaskQuartz.getTargetMethod());
			jobDetail.getJobDataMap().put("myTask", myTask);
			jobDetail.setJobClass(MyJob.class);
			scheduler.addJob(jobDetail, true);
			CronTrigger cronTrigger = new CronTrigger(triggerName, Scheduler.DEFAULT_GROUP, jobDetail
					.getName(), Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(ttaskQuartz.getTargetTime());
			scheduler.scheduleJob(cronTrigger);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除任务
	 */
	@Override
	public void deleteJob(Dto dto) {

		LvTaskQuartz ttaskQuartz = (LvTaskQuartz) dto.get("lvTaskQuartz");
		String jobName = this.job + ttaskQuartz.getId();
		String triggerName = this.trigger + ttaskQuartz.getId();

		try {
			scheduler.pauseTrigger(triggerName, Scheduler.DEFAULT_GROUP);// 停止触发器
			scheduler.unscheduleJob(triggerName, Scheduler.DEFAULT_GROUP);
			// 移除触发器
			scheduler.deleteJob(jobName, Scheduler.DEFAULT_GROUP);// 删除任务
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改任务
	 */
	@Override
	public void updateJob(Dto dto) {
		LvTaskQuartz ttaskQuartz = (LvTaskQuartz) dto.get("lvTaskQuartz");
		String jobName = this.job + ttaskQuartz.getId();
		String triggerName = this.trigger + ttaskQuartz.getId();
		try {
			Trigger trigger = scheduler.getTrigger(triggerName,
					Scheduler.DEFAULT_GROUP);
			if (trigger != null) {
				JobDetail jobDetail=scheduler.getJobDetail(jobName, Scheduler.DEFAULT_GROUP);
				MyTask myTask = (MyTask)jobDetail.getJobDataMap().get("myTask");;
				myTask.setTargetObject(ttaskQuartz.getTargetObject());
				myTask.setTargetMethod(ttaskQuartz.getTargetMethod());
				CronTrigger ct = (CronTrigger)trigger;  
				try {
					ct.setCronExpression(ttaskQuartz.getTargetTime());
				} catch (ParseException e) {
			
					e.printStackTrace();
				}
				scheduler.rescheduleJob(triggerName,Scheduler.DEFAULT_GROUP, trigger);	//重新按排
			}
		} catch (SchedulerException e) {
	
			e.printStackTrace();
		}
	}
    /**
     * 暂停任务
     */
	@Override
	public void pauseJob(Dto dto) {
		LvTaskQuartz ttaskQuartz = (LvTaskQuartz) dto.get("lvTaskQuartz");
		String jobName = this.job + ttaskQuartz.getId();
		String triggerName = this.trigger + ttaskQuartz.getId();
		Trigger trigger;
		try {
			trigger = scheduler.getTrigger(triggerName,
					Scheduler.DEFAULT_GROUP);
			if (trigger != null) {
				scheduler.pauseJob(jobName, Scheduler.DEFAULT_GROUP);
				scheduler.pauseTrigger(triggerName, Scheduler.DEFAULT_GROUP);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
    /**
     * 恢复任务
     */
	@Override
	public void resumeJob(Dto dto) {
		LvTaskQuartz ttaskQuartz = (LvTaskQuartz) dto.get("lvTaskQuartz");
		String triggerName = this.trigger + ttaskQuartz.getId();
		String jobName = this.job + ttaskQuartz.getId();
		Trigger trigger;
		try {
			trigger = scheduler.getTrigger(triggerName,
					Scheduler.DEFAULT_GROUP);
			if (trigger != null) {
				scheduler.resumeJob(jobName, Scheduler.DEFAULT_GROUP);
				scheduler.resumeTrigger(triggerName, Scheduler.DEFAULT_GROUP);
			}
		} catch (SchedulerException e) {

			e.printStackTrace();
		}
	
		
	}

}
