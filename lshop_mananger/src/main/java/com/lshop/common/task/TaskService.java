package com.lshop.common.task;

import java.text.ParseException;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

public interface TaskService {
public void initTask()throws ServiceException,ParseException ;
public void addJob(Dto dto);
public void updateJob(Dto dto);
public void deleteJob(Dto dto);
public void pauseJob(Dto dto);
public void resumeJob(Dto dto);
}
