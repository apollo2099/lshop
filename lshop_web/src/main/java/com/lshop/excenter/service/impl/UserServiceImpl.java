package com.lshop.excenter.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.logic.LvUser;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserLog;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.excenter.service.PromoterManageService;
import com.lshop.excenter.service.UserService;

@Service("ExcenterUserService")
public class UserServiceImpl implements UserService {
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource PromoterManageService manageServiceImpl;
	private Logger logger=Logger.getLogger(this.getClass());
	@Override
	public LvUserPromoters getUserInfo(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String hql="from LvUserPromoters where uid="+dto.getAsInteger("uid");
		manageServiceImpl.befPay(dto);
		return (LvUserPromoters) lvlogicReadDao.find(hql,null).get(0);
	}
    
	@Override
	public LvUserCoupon getUserCoupon(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String hql="from LvUserCoupon where couponCode='"+dto.getAsString("uname")+"'";
		LvUserCoupon uc=(LvUserCoupon)lvlogicReadDao.findUnique(hql, null);
		if(uc!=null)
		{
			return uc;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public LvUser login(Dto dto) throws ServiceException {
//		String hql = "from LvUser as u where u.email = :email  and u.pwd = :pwd and userType=1";
		String hql = "from LvUser as u where u.email = :email and userType=1";
		Map param = new HashMap();
		param.put("email", dto.getAsString("uname"));
//		param.put("pwd", dto.getAsString("pwd"));
		LvUser user= (LvUser) lvlogicReadDao.findUnique(hql, param);
		if(user!=null && user.getPwd().equals(dto.getAsString("pwd"))){//根据电子邮件登录
			 SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 hql="update LvUser set lastTime='"+format.format(new Date())+"' where id="+user.getId();
			 lvlogicWriteDao.update(hql,null);
			 //日志记录
			 LvUserLog userLog = new LvUserLog();
			 userLog.setIp(dto.getAsString("ip"));
			 userLog.setUid(user.getId());
			 userLog.setCreateTime(new Date());
			 this.lvlogicWriteDao.save(userLog);
			 return user;
		}
		return user;
	}
	@Override
	public Boolean isExistsUser(Dto dto) throws ServiceException {
		LvUser lvUser = (LvUser) dto.getDefaultPo();
		Boolean flag = true;
		if (!StringUtil.IsNullOrEmpty(lvUser.getEmail())) {
			String hql = "select count(*) from LvUser where email = :email ";
			Map<String, String> params = new HashMap<String, String>(1);
			params.put("email", lvUser.getEmail().trim());
			Long rs = (Long) lvlogicReadDao.findUnique(hql, params);//检查推广商真实的帐户
			hql = "select count(*) from LvUserPromoters where email = :email ";
			Long p=(Long)lvlogicReadDao.findUnique(hql, params);//检查推广临时商帐户
			if (rs.intValue() > 0||p.intValue()>0) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * 添加申请注册用户
	 */
	@Override
	public Integer add(Dto dto) throws ServiceException {
		LvUserPromoters userp=(LvUserPromoters)dto.getDefaultPo();
		LvUser lvUser = new LvUser();
		lvUser.setEmail(userp.getEmail());
		dto.setDefaultPo(lvUser);
		Boolean flag = isExistsUser(dto);//检查用户是否在在
		if (false == flag) {
			Integer  id=(Integer)lvlogicWriteDao.save(userp);
			return id;
		}
		return -1;
	}
    /**
     * 修改资料
     */
	@SuppressWarnings("unchecked")
	@Override
	public void edit(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		LvUserPromoters userp = (LvUserPromoters) dto.getDefaultPo();
		Integer uid=dto.getAsInteger("uid");
		String hql="from LvUserPromoters where uid="+dto.getAsInteger("uid");
		LvUserPromoters info=(LvUserPromoters) lvlogicReadDao.find(hql,null).get(0);
		
		String realName=info.getRealName();
		if(null!=userp.getRealName()){
//			info.setRealName(userp.getRealName());
			realName=userp.getRealName();
		}
		
//		info.setAccountNumber(userp.getAccountNumber());
//		info.setAccountTypes(userp.getAccountTypes());
		
		String email=info.getEmail();
		if(userp.getEmail()!=null&&!userp.getEmail().equals("")){
		  Pattern pattern=Pattern.compile("^\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$");
		  Matcher matcher = pattern.matcher(userp.getEmail());
		  if (!matcher.matches()) {
			  dto.put("message", "邮箱有误,请重新输入!");
			  return;
		  }
			
		  String hqlString="from LvUserPromoters where uid!="+uid+" and email='"+userp.getEmail()+"'";
		  List<LvUserPromoters> userPromoters=lvlogicReadDao.find(hqlString, null);
		  if(userPromoters!=null&&userPromoters.size()>0){
			  dto.put("message", "邮箱已存在,请重新输入!");
			  return;
		  }
		  else{
//			  info.setEmail(userp.getEmail());
			  email=userp.getEmail();
		  }
		}

//		info.setTel(userp.getTel());
//		info.setAdress(userp.getAdress());
		  
		String description=info.getDescription();
		if(userp.getDescription()!=null&&!userp.getDescription().equals("")){
//			info.setDescription(userp.getDescription());
			description=userp.getDescription();
		}
		  
//		lvlogicWriteDao.update(info);
		
		Map param=new HashMap();
		param.put("realName", realName);
		param.put("accountNumber", userp.getAccountNumber());
		param.put("accountTypes", userp.getAccountTypes());
		param.put("email", email);
		param.put("tel", userp.getTel());
		param.put("adress", userp.getAdress());
		param.put("description", description);
		param.put("id", info.getId());
		String rhql="update LvUserPromoters set realName=:realName, accountNumber=:accountNumber,accountTypes=:accountTypes,email=:email,tel=:tel,adress=:adress,description=:description where id=:id";
		lvlogicWriteDao.update(rhql,param);

	}
	@Override
	public void editPwd(Dto dto) throws ServiceException{
		if(null==dto){
			logger.error("缺少必要参数 ！");
			throw new ServiceException("缺少参数,不能执行此操作!");
		}
		Integer uid = dto.getAsInteger("uid");
		String origPawd = dto.getAsString("origPwd");
		String newPawd = dto.getAsString("newPwd");
		if(null==uid || StringUtil.IsNullOrEmpty(origPawd) || StringUtil.IsNullOrEmpty(newPawd)){
			logger.error("缺少必要参数 ！userID["+uid+"] orgiPwd["+origPawd+"] newPwd[+"+newPawd+"]");
			throw new ServiceException("缺少参数,不能执行此操作!");
		}
		//修改新密码
		String hql = "update LvUser  set pwd =:pwd where id = :id";
		Map<String,Object> paraMap = new HashMap<String,Object>(2);
		paraMap.put("pwd", newPawd);
		paraMap.put("id", uid);
	    lvlogicWriteDao.update(hql, paraMap);
		logger.debug("修改密码成功！");
	}

	@Override
	public LvUser getUser(Dto dto) throws ServiceException {
		// TODO Auto-generated method stu
		return (LvUser) this.lvlogicReadDao.load(LvUser.class, dto.getAsInteger("uid"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public LvUser password(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String hql = "from LvUser as u where u.email = :email and userType=1";
		Map param = new HashMap();
		param.put("email", dto.getAsString("email").trim());
		LvUser lvUser= (LvUser) lvlogicReadDao.findUnique(hql, param);
		if(lvUser!=null)
		{
//			lvUser.setPwd(dto.getAsString("newPwd"));
//			lvlogicWriteDao.update(lvUser);
			String eHql="update LvUser set pwd=:pwd where id=:id";
			HashMap map=new HashMap();
			map.put("pwd", dto.getAsString("newPwd"));
			map.put("id", lvUser.getId());
			lvlogicWriteDao.update(eHql,map);
		}
		else
		{
		  	String hqlString="from LvUserPromoters where email='"+dto.getAsString("email").trim()+"'";
		  	LvUserPromoters userPromoters=(LvUserPromoters)lvlogicReadDao.findUnique(hqlString, null);
		  	if(userPromoters!=null)
		  	{
		  	  LvUser user=(LvUser)lvlogicReadDao.load(LvUser.class, userPromoters.getUid());	
			  if(user!=null)
			  {
//				user.setPwd(dto.getAsString("newPwd"));
//				lvlogicWriteDao.update(user);
				String nHql="update LvUser set pwd=:pwd where id=:id";
				HashMap map=new HashMap();
				map.put("pwd", dto.getAsString("newPwd"));
				map.put("id", user.getId());
				lvlogicWriteDao.update(nHql,map);
				lvUser=user;
				dto.put("eml", userPromoters.getEmail());
			  }
		  	}
		}
		return lvUser;
	}
}
