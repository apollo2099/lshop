package com.lshop.web.mac.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvAccountMac;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.CodeUtils;
import com.lshop.web.mac.service.AccountMacService;
import com.lshop.web.mac.service.MacService;

@Service("AccountMacService")
public class AccountMacServiceImpl implements AccountMacService {
	private static final Log logger	= LogFactory.getLog(AccountMacServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Resource
	private MacService macService;
	
	
	/**
	 * 
	 * @Method: findByMac 
	 * @Description:  [根据mac查找当前用户是否存在兑换行为]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月22日 上午11:59:56]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月22日 上午11:59:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param userEmail
	 * @param mac
	 * @return LvAccountMac
	 */
	public LvAccountMac findByMac(String userEmail,String mac){
		if (logger.isInfoEnabled()){
			logger.info("*****AccountMacServiceImpl.findByMac() method begin*****");
		}	
		LvAccountMac am=null;
		if(ObjectUtils.isNotEmpty(userEmail)&&ObjectUtils.isNotEmpty(mac)){
			String hql="from LvAccountMac where userEmail=:userEmail and mac=:mac and status=1 order by createTime desc";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("userEmail", userEmail);
			param.put("mac", mac);
			List<LvAccountMac> list= lvlogicReadDao.find(hql, param);
			if(ObjectUtils.isNotEmpty(list)){
				am=list.get(0);
			}
		}
	
		
		if (logger.isInfoEnabled()){
			logger.info("*****AccountMacServiceImpl.findByMac() method end*****");
		}	
		return am;
	}
	
	
	public LvAccountMac findByUserCode(String userCode){
		if (logger.isInfoEnabled()){
			logger.info("*****AccountMacServiceImpl.findByMac() method begin*****");
		}	
		LvAccountMac am=null;
		if(ObjectUtils.isNotEmpty(userCode)){
			String hql="from LvAccountMac where userCode=:userCode and status=1 order by createTime desc";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("userCode", userCode);
			List<LvAccountMac> list= lvlogicReadDao.find(hql, param);
			if(ObjectUtils.isNotEmpty(list)){
				am=list.get(0);
			}
		}

		if (logger.isInfoEnabled()){
			logger.info("*****AccountMacServiceImpl.findByMac() method end*****");
		}	
		return am;
	}
	
	@Override
	public Boolean saveAccountMac(LvAccountMac accountMac) {
		lvlogicWriteDao.save(accountMac);
		return true;
	}
	
	/**
	 * 
	 * @Method: saveAccountMac 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月22日 上午11:50:49]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月22日 上午11:50:49]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean saveAccountMac(String userEmail,String userCode,String contactPhone,String contactTel,String mac,String ip,String sourceUrl){
		if (logger.isInfoEnabled()){
			logger.info("*****AccountMacServiceImpl.saveAccountMac() method begin*****");
		}	
		if(ObjectUtils.isNotEmpty(userEmail)&&ObjectUtils.isNotEmpty(mac)){
			//判断当前mac是否已经已存在
			LvAccountMac am=this.findByMac(userEmail, mac);
			if(ObjectUtils.isNotEmpty(am)){
				//更新有效状态只有一个
				updateStatus(userEmail, mac);
			}
			
			LvAccountMac accountMac=new LvAccountMac();
			accountMac.setUserEmail(userEmail);
			accountMac.setMac(mac);
			accountMac.setContactPhone(contactPhone);
			accountMac.setContactTel(contactTel);
			accountMac.setIp(ip);
			accountMac.setSourceUrl(sourceUrl);
			accountMac.setCreateTime(new Date());
			accountMac.setUserCode(userCode);
			accountMac.setStatus((short)1);
			accountMac.setCode(CodeUtils.getCode());
			this.saveAccountMac(accountMac);

			//mac入库
			macService.saveMac(mac);
			return true;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("*****AccountMacServiceImpl.saveAccountMac() method end*****");
		}	
		return false;
	}


	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [更新用户mac使用状态]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月29日 上午11:05:22]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月29日 上午11:05:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void updateStatus(String userEmail, String mac) {
		if (logger.isInfoEnabled()){
			logger.info("*****AccountMacServiceImpl.updateStatus() method begin*****");
		}
		String hql=" update LvAccountMac set status=-1 "
				 + " where userEmail=:userEmail "
				 + " and mac=:mac "
				 + " and status=1";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("userEmail", userEmail);
		param.put("mac", mac);
		lvlogicWriteDao.update(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("*****AccountMacServiceImpl.updateStatus() method end*****");
		}
	}

}
